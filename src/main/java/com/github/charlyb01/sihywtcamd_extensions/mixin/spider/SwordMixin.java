package com.github.charlyb01.sihywtcamd_extensions.mixin.spider;

import com.github.charlyb01.sihywtcamd_extensions.block.BlockRegistry;
import com.github.charlyb01.sihywtcamd_extensions.config.ModConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;

@Mixin(SwordItem.class)
public class SwordMixin {
    @WrapOperation(method = "createToolComponent", at = @At(value = "INVOKE", target = "Lnet/minecraft/component/type/ToolComponent$Rule;ofAlwaysDropping(Ljava/util/List;F)Lnet/minecraft/component/type/ToolComponent$Rule;"))
    private static ToolComponent.Rule addMessyCobwebComponent(List<Block> blocks, float speed, Operation<ToolComponent.Rule> original) {
        if (!ModConfig.get().arthropods.generalSpiders.webProjectileGoal) {
            return original.call(blocks, speed);
        }

        ArrayList<Block> list = new ArrayList<>(blocks);
        list.add(BlockRegistry.MESSY_COBWEB);
        return original.call(list, speed);
    }
}
