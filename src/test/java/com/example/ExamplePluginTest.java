package com.example;

import com.example.BalaclavaAPI.TestPlugin.TestPlugin;
import com.example.Plugins.BButterflyCatcher.BButterflyCatcherPlugin;
import com.example.Plugins.BCooker.BCookerPlugin;
import com.example.Plugins.BHighAlch.BHighAlchPlugin;
import com.example.Plugins.BQuickPrayerFlicker.BQuickPrayerFlickerPlugin;
import com.example.Plugins.BSuperheat.BSuperheatPlugin;
import com.example.Plugins.LunatikTitheFarm.AutoTitheFarmPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ExamplePluginTest {
    public static void main(String[] args) throws Exception {
        ExternalPluginManager.loadBuiltin(
                TestPlugin.class,
                AutoTitheFarmPlugin.class,
                BCookerPlugin.class,
                BButterflyCatcherPlugin.class,
                BHighAlchPlugin.class,
                BQuickPrayerFlickerPlugin.class,
                BSuperheatPlugin.class);

        RuneLite.main(args);
    }
}
