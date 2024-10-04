package com.github.charlyb01.sihywtcamd_extensions.mixin.spider;

import com.github.charlyb01.sihywtcamd_extensions.cardinal.MyComponents;
import com.github.charlyb01.sihywtcamd_extensions.config.UtilsConfig;
import com.github.charlyb01.sihywtcamd_extensions.mixin.LivingEntityMixin;
import net.minecraft.entity.mob.SpiderEntity;
import org.ladysnake.cca.api.v3.component.ComponentProvider;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SpiderEntity.class)
public abstract class SpiderLivingMixin extends LivingEntityMixin {
    @Override
    public boolean updateBaby(boolean original) {
        return (UtilsConfig.babySpidersEnabled() || UtilsConfig.babyCaveSpidersEnabled())
                && ((ComponentProvider) this).getComponentContainer() != null
                ? MyComponents.BABY_COMPONENT.get(this).isBaby()
                : original;
    }

    @Override
    protected float updateScaleFactor(float original) {
        if (UtilsConfig.babySpidersEnabled() && this.isBaby()) {
            return 0.33F;
        } else {
            return original;
        }
    }
}
