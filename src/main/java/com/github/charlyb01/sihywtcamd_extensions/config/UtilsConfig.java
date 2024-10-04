package com.github.charlyb01.sihywtcamd_extensions.config;

public class UtilsConfig {
    public static boolean babySpidersEnabled() {
        return ModConfig.get().arthropods.spider.babyOnDeath
                || ModConfig.get().arthropods.spider.babySpawnGroup;
    }

    public static boolean babyCaveSpidersEnabled() {
        return ModConfig.get().arthropods.caveSpider.babyOnDeath
                || ModConfig.get().arthropods.caveSpider.babySpawnGroup;
    }
}
