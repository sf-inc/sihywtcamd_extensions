package com.github.charlyb01.sihywtcamd_extensions.cardinal;

import com.github.charlyb01.sihywtcamd_extensions.SihywtcamdExtensions;
import com.github.charlyb01.sihywtcamd_extensions.cardinal.api.*;
import com.github.charlyb01.sihywtcamd_extensions.cardinal.impl.*;
import net.minecraft.entity.mob.*;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public final class MyComponents implements EntityComponentInitializer {
    public static final ComponentKey<BabyComponentAPI> BABY_COMPONENT =
            ComponentRegistry.getOrCreate(SihywtcamdExtensions.id("baby_component"), BabyComponentAPI.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(AbstractSkeletonEntity.class, BABY_COMPONENT, BabyComponent::new);
        registry.registerFor(SpiderEntity.class, BABY_COMPONENT, BabyComponent::new);
    }
}
