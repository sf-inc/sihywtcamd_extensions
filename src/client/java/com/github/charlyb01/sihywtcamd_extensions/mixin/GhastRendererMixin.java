package com.github.charlyb01.sihywtcamd_extensions.mixin;

import com.github.charlyb01.sihywtcamd_extensions.SihywtcamdExtensions;
import com.github.charlyb01.sihywtcamd_extensions.config.ModConfig;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.render.entity.GhastEntityRenderer;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GhastEntityRenderer.class)
public abstract class GhastRendererMixin {
    @Unique
    private static final Identifier GHAST_TEXTURE = SihywtcamdExtensions.id("textures/entity/ghast.png");
    @Unique
    private static final Identifier GHAST_SHOOTING_TEXTURE = SihywtcamdExtensions.id("textures/entity/ghast_shooting.png");

    @ModifyReturnValue(method = "getTexture(Lnet/minecraft/entity/mob/GhastEntity;)Lnet/minecraft/util/Identifier;", at = @At("RETURN"))
    private Identifier getTexture(Identifier original, GhastEntity ghastEntity) {
        return ModConfig.get().cosmetics.translucentGhast
                ? (ghastEntity.isShooting() ? GHAST_SHOOTING_TEXTURE : GHAST_TEXTURE)
                : original;
    }
}
