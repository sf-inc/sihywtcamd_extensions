package com.github.charlyb01.sihywtcamd_extensions.sound;

import com.github.charlyb01.sihywtcamd_extensions.SihywtcamdExtensions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundRegistry {
    public static final Identifier SPIDER_SPIT_ID = SihywtcamdExtensions.id("spider_spit");
    public static SoundEvent SPIDER_SPIT_EVENT = SoundEvent.of(SPIDER_SPIT_ID);

    public static void init() {
        Registry.register(Registries.SOUND_EVENT, SPIDER_SPIT_ID, SPIDER_SPIT_EVENT);
    }
}
