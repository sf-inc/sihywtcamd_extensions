package com.github.charlyb01.sihywtcamd_extensions.mixin.skeleton;

import com.github.charlyb01.sihywtcamd_extensions.cardinal.MyComponents;
import com.github.charlyb01.sihywtcamd_extensions.config.ModConfig;
import com.github.charlyb01.sihywtcamd_extensions.mixin.LivingEntityMixin;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import org.ladysnake.cca.api.v3.component.ComponentProvider;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractSkeletonEntity.class)
public abstract class AbstractSkeletonLivingMixin extends LivingEntityMixin {
    @Override
    protected boolean updateBaby(boolean original) {
        return (ModConfig.get().skeletons.general.baby || ModConfig.get().skeletons.witherSkeleton.baby)
                && ((ComponentProvider) this).getComponentContainer() != null
                ? MyComponents.BABY_COMPONENT.get(this).isBaby()
                : original;
    }
}
