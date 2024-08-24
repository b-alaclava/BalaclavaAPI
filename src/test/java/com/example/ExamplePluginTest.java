package com.example;

import com.example.Plugins.BSuperheat.BSuperheatPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ExamplePluginTest {
    public static void main(String[] args) throws Exception {
        ExternalPluginManager.loadBuiltin(BSuperheatPlugin.class);

        RuneLite.main(args);
    }
}
