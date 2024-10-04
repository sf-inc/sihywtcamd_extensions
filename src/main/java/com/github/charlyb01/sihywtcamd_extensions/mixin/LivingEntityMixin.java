package com.github.charlyb01.sihywtcamd_extensions.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract boolean isBaby();

    @ModifyReturnValue(method = "isBaby", at = @At("RETURN"))
    protected boolean updateBaby(boolean original) {
        return original;
    }

    @ModifyReturnValue(method = "getScaleFactor", at = @At("RETURN"))
    protected float updateScaleFactor(float original) {
        return original;
    }
}
