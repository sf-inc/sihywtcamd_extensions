package com.github.charlyb01.sihywtcamd_extensions.mixin;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {
    @Shadow @Final protected GoalSelector goalSelector;

    @Shadow public abstract void setBaby(boolean baby);

    protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }


    @Inject(method = "setBaby", at = @At("TAIL"))
    protected void onSetBaby(boolean baby, CallbackInfo ci) {
    }
}
