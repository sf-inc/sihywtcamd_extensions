package com.github.charlyb01.sihywtcamd_extensions.mixin.spider;

import com.github.charlyb01.sihywtcamd_extensions.SihywtcamdExtensions;
import com.github.charlyb01.sihywtcamd_extensions.block.BlockRegistry;
import com.github.charlyb01.sihywtcamd_extensions.cardinal.MyComponents;
import com.github.charlyb01.sihywtcamd_extensions.config.ModConfig;
import com.github.charlyb01.sihywtcamd_extensions.entity.CobwebAttackGoal;
import com.github.charlyb01.sihywtcamd_extensions.entity.CobwebProjectileEntity;
import com.github.charlyb01.sihywtcamd_extensions.mixin.MobEntityMixin;
import com.github.charlyb01.sihywtcamd_extensions.sound.SoundRegistry;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpiderEntity.class)
public abstract class SpiderMobMixin extends MobEntityMixin implements RangedAttackMob {
    @Unique
    private Goal sihywtcamd_cobwebAttackGoal;

    @Unique
    private static final Identifier BABY_MODIFIER_ID = SihywtcamdExtensions.id("baby_spawn_malus");
    @Unique
    private static final EntityAttributeModifier BABY_MODIFIER = new EntityAttributeModifier(
                    BABY_MODIFIER_ID, -0.5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE);

    protected SpiderMobMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals", at = @At("HEAD"))
    private void addSpiderGoals(CallbackInfo ci) {
        if (ModConfig.get().arthropods.generalSpiders.webProjectileGoal) {
            this.sihywtcamd_cobwebAttackGoal = new CobwebAttackGoal<>((HostileEntity & RangedAttackMob) (Object) this, 1.0D, 40, 15.0F);
            this.goalSelector.add(4, this.sihywtcamd_cobwebAttackGoal);
        }
    }

    @Inject(method = "initialize", at = @At("TAIL"))
    private void spawnBaby(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
                              EntityData entityData, CallbackInfoReturnable<EntityData> cir) {
        if (ModConfig.get().arthropods.spider.babySpawnGroup
                && !this.isBaby()
                && !this.hasPassengers()
                && !SpawnReason.isAnySpawner(spawnReason)
                && this.random.nextFloat() < 0.1F) {
            this.setBaby(true);

            for (int i = 0; i < 2; ++i) {
                MobEntity mob = (MobEntity) this.getType().spawn((ServerWorld) this.getWorld(), this.getBlockPos(), SpawnReason.NATURAL);
                if (mob != null) {
                    mob.setBaby(true);
                }
            }
        }
    }

    @Override
    protected void onSetBaby(boolean baby, CallbackInfo ci) {
        MyComponents.BABY_COMPONENT.get(this).setBaby(baby);

        EntityAttributeInstance attackDamage = this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        if (attackDamage != null) attackDamage.removeModifier(BABY_MODIFIER_ID);
        EntityAttributeInstance maxHealth = this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        if (maxHealth != null) maxHealth.removeModifier(BABY_MODIFIER_ID);

        if (baby) {
            if (attackDamage != null) attackDamage.addTemporaryModifier(BABY_MODIFIER);
            if (maxHealth != null) maxHealth.addTemporaryModifier(BABY_MODIFIER);

            if (ModConfig.get().arthropods.generalSpiders.webProjectileGoal) {
                this.goalSelector.remove(this.sihywtcamd_cobwebAttackGoal);
            }
        }
    }

    @ModifyExpressionValue(method = "slowMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    public boolean slowMovement(boolean original, BlockState state, Vec3d multiplier) {
        return original || state.isOf(BlockRegistry.MESSY_COBWEB);
    }

    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        CobwebProjectileEntity cobwebProjectileEntity = CobwebProjectileEntity.create(this.getWorld(), this);
        double d = target.getX() - this.getX();
        double e = target.getBodyY(0.3333333333333333D) - this.getY();
        double f = target.getZ() - this.getZ();
        double g = Math.sqrt(d * d + f * f);
        cobwebProjectileEntity.setVelocity(d, e + g * 0.20000000298023224D, f, 1.0F, (float) (14 - this.getWorld().getDifficulty().getId() * 2));
        this.playSound(SoundRegistry.SPIDER_SPIT_EVENT, 0.33F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.getWorld().spawnEntity(cobwebProjectileEntity);
    }
}
