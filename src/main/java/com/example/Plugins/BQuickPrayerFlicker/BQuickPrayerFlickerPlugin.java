package com.example.Plugins.BQuickPrayerFlicker;


import com.example.BalaclavaAPI.InteractionUtility.PrayerUtility;
import com.example.BalaclavaAPI.Utility.DoAction;
import com.example.EthanApi.Collections.Widgets;
import com.example.EthanApi.EthanApi;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.client.RuneLite;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;


import java.awt.*;

@PluginDescriptor(
        name = "<html><font color=\"#6DF030\">B.</font> Quick Prayer Flicker</html>",
        description = "One tick flicks quick prayers",
        tags = {"prayer","flicker","Balaclava"}
)
@Slf4j
public class BQuickPrayerFlickerPlugin extends Plugin {
    private final Client client = RuneLite.getInjector().getInstance(Client.class);

    @Override
    protected void startUp() throws Exception {
        if(client.getGameState().equals(GameState.LOGGED_IN)) {
            //change login timer to green when its on
            client.getWidget(10616865).setTextColor(Color.GREEN.getRGB());
        }
        DoAction.setPrintMenuActions(true);
        EthanApi.init();
    }
    @Override
    protected void shutDown() throws Exception {
        if(client.getGameState().equals(GameState.LOGGED_IN)) {
            //back to white when its off
            client.getWidget(10616865).setTextColor(Color.WHITE.getRGB());
        }
    }



    @Subscribe
    public void onGameTick(GameTick event) {

        if(client.getBoostedSkillLevel(Skill.PRAYER) <= 0 ){
            endPlugin();
        }

        if(EthanApi.isQuickPrayerEnabled()){
            PrayerUtility.toggleQuickPrayer();
        }
        PrayerUtility.toggleQuickPrayer();


    }




    @SneakyThrows
    private void endPlugin(){
        //switch plugin off
        EthanApi.stopPlugin(this);

        if(Widgets.search().withId(10616865).first().isPresent()) {
            //change color of the login timer back to white
            client.getWidget(10616865).setTextColor(Color.WHITE.getRGB());
        }

    }

}
