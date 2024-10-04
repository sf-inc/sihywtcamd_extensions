package com.github.charlyb01.sihywtcamd_extensions.mixin.skeleton;

import com.github.charlyb01.sihywtcamd_extensions.config.ModConfig;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WitherSkeletonEntity.class)
public abstract class WitherSkeletonMixin extends AbstractSkeletonEntity {
    protected WitherSkeletonMixin(EntityType<? extends AbstractSkeletonEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/WitherSkeletonEntity;updateAttackType()V"))
    private void canSpawnBaby(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
                              EntityData entityData, CallbackInfoReturnable<EntityData> cir) {
        this.setBaby(ModConfig.get().skeletons.witherSkeleton.baby && this.random.nextFloat() < 0.2F);
    }
}
