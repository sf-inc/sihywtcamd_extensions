package com.github.charlyb01.sihywtcamd_extensions.mixin;

import com.github.charlyb01.sihywtcamd_extensions.SihywtcamdExtensions;
import com.github.charlyb01.sihywtcamd_extensions.config.ModConfig;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.render.entity.AllayEntityRenderer;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AllayEntityRenderer.class)
public abstract class AllayRendererMixin {
    @Unique
    private static final Identifier ALLAY_TEXTURE = SihywtcamdExtensions.id("textures/entity/allay.png");

    @ModifyReturnValue(method = "getTexture(Lnet/minecraft/entity/passive/AllayEntity;)Lnet/minecraft/util/Identifier;", at = @At("RETURN"))
    private Identifier getTexture(Identifier original, AllayEntity allayEntity) {
        return ModConfig.get().cosmetics.translucentAllay
                ? ALLAY_TEXTURE
                : original;
    }
}
