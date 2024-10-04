package com.github.charlyb01.sihywtcamd_extensions.mixin.spider;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HostileEntity.class)
public class HostileMixin extends PathAwareEntity {
    protected HostileMixin(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyReturnValue(method = "shouldDropLoot", at = @At("RETURN"))
    protected boolean updateDropLoot(boolean original) {
        return original;
    }
}
