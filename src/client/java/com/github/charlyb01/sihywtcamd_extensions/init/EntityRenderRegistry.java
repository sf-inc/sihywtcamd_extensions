package com.github.charlyb01.sihywtcamd_extensions.init;

import com.github.charlyb01.sihywtcamd_extensions.entity.CobwebEntityRenderer;
import com.github.charlyb01.sihywtcamd_extensions.entity.EntityRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class EntityRenderRegistry {
    public static void init() {
        EntityRendererRegistry.register(EntityRegistry.COBWEB, CobwebEntityRenderer::new);
    }
}
