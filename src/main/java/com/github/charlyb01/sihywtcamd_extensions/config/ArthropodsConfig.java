package com.github.charlyb01.sihywtcamd_extensions.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "arthropods")
public class ArthropodsConfig implements ConfigData {
    @ConfigEntry.Gui.CollapsibleObject
    public GeneralSpiders generalSpiders = new GeneralSpiders();

    public static class GeneralSpiders {
        @ConfigEntry.Gui.RequiresRestart
        public boolean webProjectileGoal = true;
    }

    @ConfigEntry.Gui.CollapsibleObject
    public Spider spider = new Spider();

    public static class Spider {
        public boolean babyOnDeath = true;
        public boolean babySpawnGroup = true;
    }

    @ConfigEntry.Gui.CollapsibleObject
    public CaveSpider caveSpider = new CaveSpider();

    public static class CaveSpider {
        public boolean babyOnDeath = true;
        public boolean babySpawnGroup = true;
    }
}
