package com.github.charlyb01.sihywtcamd_extensions.cardinal.impl;

import com.github.charlyb01.sihywtcamd_extensions.cardinal.MyComponents;
import com.github.charlyb01.sihywtcamd_extensions.cardinal.api.BabyComponentAPI;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;

public class BabyComponent implements BabyComponentAPI {
    private final Object provider;
    private boolean isBaby = false;

    public BabyComponent(Object provider) {
        this.provider = provider;
    }

    @Override
    public boolean isBaby() {
        return this.isBaby;
    }

    @Override
    public void setBaby(boolean baby) {
        this.isBaby = baby;
        if (this.provider instanceof Entity entity) {
            entity.calculateDimensions();
        }
        MyComponents.BABY_COMPONENT.sync(this.provider);
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        this.setBaby(tag.getBoolean("IsBaby"));
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putBoolean("IsBaby", this.isBaby);
    }

    @Override
    public void applySyncPacket(RegistryByteBuf buf) {
        this.setBaby(buf.readBoolean());
    }

    @Override
    public void writeSyncPacket(RegistryByteBuf buf, ServerPlayerEntity player) {
        buf.writeBoolean(this.isBaby);
    }
}
