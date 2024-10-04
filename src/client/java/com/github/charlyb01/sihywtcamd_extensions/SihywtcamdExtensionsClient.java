package com.github.charlyb01.sihywtcamd_extensions;

import com.github.charlyb01.sihywtcamd_extensions.init.BlockRenderRegistry;
import com.github.charlyb01.sihywtcamd_extensions.init.EntityRenderRegistry;
import net.fabricmc.api.ClientModInitializer;

public class SihywtcamdExtensionsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderRegistry.init();
        EntityRenderRegistry.init();
    }
}
