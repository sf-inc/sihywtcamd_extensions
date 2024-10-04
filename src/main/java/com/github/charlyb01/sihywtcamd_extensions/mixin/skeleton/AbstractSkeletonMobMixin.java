package com.github.charlyb01.sihywtcamd_extensions.mixin.skeleton;

import com.github.charlyb01.sihywtcamd_extensions.cardinal.MyComponents;
import com.github.charlyb01.sihywtcamd_extensions.config.ModConfig;
import com.github.charlyb01.sihywtcamd_extensions.entity.BowQuickAttackGoal;
import com.github.charlyb01.sihywtcamd_extensions.mixin.MobEntityMixin;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.BowAttackGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractSkeletonEntity.class)
public abstract class AbstractSkeletonMobMixin extends MobEntityMixin implements RangedAttackMob {
    @Shadow @Final private BowAttackGoal<AbstractSkeletonEntity> bowAttackGoal;

    @Shadow public abstract void updateAttackType();

    @Unique
    private final Goal bowQuickAttackGoal = new BowQuickAttackGoal<>((HostileEntity & RangedAttackMob) (Object) this,
            1.0, 30, 3, 15f);

    protected AbstractSkeletonMobMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/AbstractSkeletonEntity;updateAttackType()V"))
    private void canSpawnBaby(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
                              EntityData entityData, CallbackInfoReturnable<EntityData> cir) {
        this.setBaby(ModConfig.get().skeletons.general.baby && this.random.nextFloat() < 0.1f);
    }

    @Inject(method = "updateAttackType", at = @At("TAIL"))
    private void replaceBowAttackGoal(CallbackInfo ci) {
        this.goalSelector.remove(this.bowQuickAttackGoal);
        if (!ModConfig.get().skeletons.general.babyQuickAttackGoal) return;

        ItemStack itemStack = this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, Items.BOW));
        if (this.isBaby() && itemStack.isOf(Items.BOW)) {
            this.goalSelector.remove(this.bowAttackGoal);
            this.goalSelector.add(4, this.bowQuickAttackGoal);
        }
    }

    @WrapOperation(method = "shootAt", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/PersistentProjectileEntity;setVelocity(DDDFF)V"))
    private void updateVelocityQuickAttackGoal(PersistentProjectileEntity instance, double x, double y, double z,
                                               float speed, float divergence, Operation<Void> original,
                                               LivingEntity target, float pullProgress) {
        if (ModConfig.get().skeletons.general.babyQuickAttackGoal && this.isBaby()) {
            original.call(instance, x, y, z, 2.2f * pullProgress, divergence + 2.f);
        } else {
            original.call(instance, x, y, z, speed, divergence);
        }
    }

    @Override
    protected void onSetBaby(boolean baby, CallbackInfo ci) {
        MyComponents.BABY_COMPONENT.get(this).setBaby(baby);
        this.updateAttackType();
    }
}
