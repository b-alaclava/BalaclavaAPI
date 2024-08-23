package com.example.BalaclavaAPI.TestPlugin;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("Csuperheat")
public interface TestConfig extends Config {

    @ConfigItem(
            keyName = "bar",
            name = "Bar",
            description = "Bar to create with superheat"
    )
    default Bar bar() {
        return Bar.GOLD_BAR;
    }




}
