package com.example;

import com.example.BalaclavaAPI.TestPlugin.TestPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ExamplePluginTest {
    public static void main(String[] args) throws Exception {
        ExternalPluginManager.loadBuiltin(TestPlugin.class);

        RuneLite.main(args);
    }
}
