package com.github.charlyb01.sihywtcamd_extensions.mixin.spider;

import com.github.charlyb01.sihywtcamd_extensions.config.UtilsConfig;
import com.github.charlyb01.sihywtcamd_extensions.mixin.LivingEntityMixin;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.CaveSpiderEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CaveSpiderEntity.class)
public abstract class CaveSpiderLivingMixin extends LivingEntityMixin {
    @Override
    protected float updateScaleFactor(float original) {
        if (UtilsConfig.babyCaveSpidersEnabled() && this.isBaby()) {
            return 0.5F;
        } else {
            return original;
        }
    }

    @ModifyExpressionValue(method = "tryAttack", at = @At(value = "NEW", target = "(Lnet/minecraft/registry/entry/RegistryEntry;II)Lnet/minecraft/entity/effect/StatusEffectInstance;"))
    private StatusEffectInstance adaptivePoison(StatusEffectInstance original) {
        if (UtilsConfig.babyCaveSpidersEnabled() && this.isBaby()) {
            int duration = original.getDuration() / 5;
            return new StatusEffectInstance(original.getEffectType(), duration, original.getAmplifier());
        } else {
            return original;
        }
    }
}
