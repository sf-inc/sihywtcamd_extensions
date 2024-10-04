package com.github.charlyb01.sihywtcamd_extensions.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "skeletons")
public class SkeletonsConfig implements ConfigData {
    @ConfigEntry.Gui.CollapsibleObject
    public General general = new General();

    public static class General {
        public boolean baby = true;
        public boolean babyQuickAttackGoal = true;
    }

    @ConfigEntry.Gui.CollapsibleObject
    public WitherSkeleton witherSkeleton = new WitherSkeleton();

    public static class WitherSkeleton {
        public boolean baby = true;
    }
}
