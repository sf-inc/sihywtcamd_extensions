package com.github.charlyb01.sihywtcamd_extensions.data;

import com.github.charlyb01.sihywtcamd_extensions.SihywtcamdExtensions;
import com.github.charlyb01.sihywtcamd_extensions.block.BlockRegistry;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class DataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(AdvancementsProvider::new);
    }

    static class AdvancementsProvider extends FabricAdvancementProvider {
        protected AdvancementsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            super(output, registryLookup);
        }

        @Override
        public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
            AdvancementEntry enterMessyCobweb = Advancement.Builder.create()
                    .parent(Identifier.ofVanilla("adventure/honey_block_slide"))
                    .display(
                            Items.COBWEB,
                            Text.translatable("advancements.enter_messy_cobweb.title"),
                            Text.translatable("advancements.enter_messy_cobweb.description"),
                            null,
                            AdvancementFrame.TASK,
                            true,
                            true,
                            false
                    )
                    .criterion("enter_messy_cobweb", EnterBlockCriterion.Conditions.block(BlockRegistry.MESSY_COBWEB))
                    .build(consumer, SihywtcamdExtensions.MOD_ID + "/enter_messy_cobweb");

            AdvancementEntry babySpiderSpawn = Advancement.Builder.create()
                    .parent(Identifier.ofVanilla("adventure/kill_a_mob"))
                    .display(
                            Items.SPIDER_SPAWN_EGG,
                            Text.translatable("advancements.baby_spider_spawn.title"),
                            Text.translatable("advancements.baby_spider_spawn.description"),
                            null,
                            AdvancementFrame.TASK,
                            true,
                            true,
                            false
                    )
                    .criterion("baby_spider_spawn", SummonedEntityCriterion.Conditions.create(
                            EntityPredicate.Builder.create()
                                    .type(EntityType.SPIDER)
                                    .flags(EntityFlagsPredicate.Builder.create().isBaby(true))
                    ))
                    .build(consumer, SihywtcamdExtensions.MOD_ID + "/baby_spider_spawn");

            AdvancementEntry babyCaveSpiderSpawn = Advancement.Builder.create()
                    .parent(babySpiderSpawn)
                    .display(
                            Items.CAVE_SPIDER_SPAWN_EGG,
                            Text.translatable("advancements.baby_cave_spider_spawn.title"),
                            Text.translatable("advancements.baby_cave_spider_spawn.description"),
                            null,
                            AdvancementFrame.TASK,
                            true,
                            true,
                            false
                    )
                    .criterion("baby_cave_spider_spawn", SummonedEntityCriterion.Conditions.create(
                            EntityPredicate.Builder.create()
                                    .type(EntityType.CAVE_SPIDER)
                                    .flags(EntityFlagsPredicate.Builder.create().isBaby(true))
                    ))
                    .build(consumer, SihywtcamdExtensions.MOD_ID + "/baby_cave_spider_spawn");
        }
    }
}
