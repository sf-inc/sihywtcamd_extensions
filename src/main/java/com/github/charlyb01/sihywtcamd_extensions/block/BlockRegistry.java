package com.github.charlyb01.sihywtcamd_extensions.block;

import com.github.charlyb01.sihywtcamd_extensions.SihywtcamdExtensions;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

public class BlockRegistry {
    public static final Block MESSY_COBWEB = new MessyCobweb(AbstractBlock.Settings.create()
            .mapColor(MapColor.WHITE_GRAY).sounds(BlockSoundGroup.COBWEB).solid().noCollision().requiresTool()
            .strength(3.0F).pistonBehavior(PistonBehavior.DESTROY));

    public static void init() {
        Registry.register(Registries.BLOCK, SihywtcamdExtensions.id("messy_cobweb"), MESSY_COBWEB);
    }
}
