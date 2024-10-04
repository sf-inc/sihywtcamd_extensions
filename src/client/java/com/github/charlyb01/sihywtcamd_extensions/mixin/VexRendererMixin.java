package com.github.charlyb01.sihywtcamd_extensions.mixin;

import com.github.charlyb01.sihywtcamd_extensions.SihywtcamdExtensions;
import com.github.charlyb01.sihywtcamd_extensions.config.ModConfig;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.render.entity.VexEntityRenderer;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(VexEntityRenderer.class)
public abstract class VexRendererMixin {
    @Unique
    private static final Identifier VEX_TEXTURE = SihywtcamdExtensions.id("textures/entity/vex.png");
    @Unique
    private static final Identifier VEX_CHARGING_TEXTURE = SihywtcamdExtensions.id("textures/entity/vex_charging.png");

    @ModifyReturnValue(method = "getTexture(Lnet/minecraft/entity/mob/VexEntity;)Lnet/minecraft/util/Identifier;", at = @At("RETURN"))
    private Identifier getTexture(Identifier original, VexEntity vexEntity) {
        return ModConfig.get().cosmetics.translucentVex
                ? (vexEntity.isCharging() ? VEX_CHARGING_TEXTURE : VEX_TEXTURE)
                : original;
    }
}
