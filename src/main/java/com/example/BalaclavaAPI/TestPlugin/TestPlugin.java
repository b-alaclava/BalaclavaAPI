package com.example.BalaclavaAPI.TestPlugin;


import com.example.BalaclavaAPI.Utility.DoAction;
import com.example.EthanApi.Collections.Widgets;
import com.example.EthanApi.EthanApi;
import com.google.inject.Inject;
import com.google.inject.Provides;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.client.RuneLite;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.awt.*;

@PluginDescriptor(
        name = "<html><font color=\"#6DF030\">B.</font> Test</html>",
        description = "Testing api development",
        tags = {"superheat","Cal"}
)
@Slf4j
public class TestPlugin extends Plugin {
    private final Client client = RuneLite.getInjector().getInstance(Client.class);
    private final ItemManager itemManager = RuneLite.getInjector().getInstance(ItemManager.class);
    @Inject
    private TestConfig config;

    @Provides
    private TestConfig getConfig(ConfigManager configManager) {
        return configManager.getConfig(TestConfig.class);
    }

    @Override
    protected void startUp() throws Exception {
        if (client.getGameState().equals(GameState.LOGGED_IN)) {
            //change login timer to green when its on
            client.getWidget(10616865).setTextColor(Color.GREEN.getRGB());
        }

        EthanApi.init();
        DoAction.setPrintMenuActions(true);

    }

    @Override
    protected void shutDown() throws Exception {
        if (client.getGameState().equals(GameState.LOGGED_IN)) {
            //back to white when its off
            client.getWidget(10616865).setTextColor(Color.WHITE.getRGB());
        }
    }


    @Subscribe
    public void onGameTick(GameTick e){

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
