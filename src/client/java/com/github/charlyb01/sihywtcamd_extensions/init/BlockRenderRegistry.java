package com.github.charlyb01.sihywtcamd_extensions.init;

import com.github.charlyb01.sihywtcamd_extensions.block.BlockRegistry;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class BlockRenderRegistry {
    public static void init() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.MESSY_COBWEB, RenderLayer.getCutout());
    }
}
