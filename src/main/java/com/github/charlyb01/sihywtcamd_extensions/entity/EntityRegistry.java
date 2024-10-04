package com.github.charlyb01.sihywtcamd_extensions.entity;

import com.github.charlyb01.sihywtcamd_extensions.SihywtcamdExtensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class EntityRegistry {
    public static EntityType<CobwebProjectileEntity> COBWEB  = EntityType.Builder
            .create(CobwebProjectileEntity::new, SpawnGroup.MISC)
            .dimensions(0.5F, 0.5F)
            .build();

    public static void init() {
        Registry.register(Registries.ENTITY_TYPE, SihywtcamdExtensions.id("cobweb"), COBWEB);
    }
}
