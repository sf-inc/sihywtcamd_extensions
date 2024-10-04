package com.github.charlyb01.sihywtcamd_extensions.mixin;

import com.github.charlyb01.sihywtcamd_extensions.SihywtcamdExtensions;
import com.github.charlyb01.sihywtcamd_extensions.config.ModConfig;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.render.entity.PhantomEntityRenderer;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PhantomEntityRenderer.class)
public abstract class PhantomRendererMixin {
    @Unique
    private static final Identifier PHANTOM_TEXTURE = SihywtcamdExtensions.id("textures/entity/phantom.png");

    @ModifyReturnValue(method = "getTexture(Lnet/minecraft/entity/mob/PhantomEntity;)Lnet/minecraft/util/Identifier;", at = @At("RETURN"))
    private Identifier getTexture(Identifier original, PhantomEntity phantomEntity) {
        return ModConfig.get().cosmetics.translucentPhantom
                ? PHANTOM_TEXTURE
                : original;
    }
}
