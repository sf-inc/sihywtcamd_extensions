package com.github.charlyb01.sihywtcamd_extensions;

import com.github.charlyb01.sihywtcamd_extensions.config.ModConfig;
import com.github.charlyb01.sihywtcamd_extensions.block.BlockRegistry;
import com.github.charlyb01.sihywtcamd_extensions.entity.EntityRegistry;
import com.github.charlyb01.sihywtcamd_extensions.sound.SoundRegistry;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class SihywtcamdExtensions implements ModInitializer {
    public static final boolean DEBUG = false;
    public static boolean areConfigsInit = false;
    public static final String MOD_ID = "sihywtcamd_extensions";

    @Override
    public void onInitialize() {
        if (!SihywtcamdExtensions.areConfigsInit) {
            AutoConfig.register(ModConfig.class, PartitioningSerializer.wrap(GsonConfigSerializer::new));
            SihywtcamdExtensions.areConfigsInit = true;
        }

        BlockRegistry.init();
        EntityRegistry.init();
        SoundRegistry.init();
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
