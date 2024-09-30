package net.firemuffin303.thaidelight.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModLootTable;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class LootTableDataGen extends SimpleFabricLootTableProvider {
    //protected static final EntityPredicate.Builder ENTITY_ON_FIRE = EntityPredicate.Builder.entity().flags(net.minecraft.advancements.critereon.EntityFlagsPredicate.Builder.flags().setOnFire(true).build());

    public LootTableDataGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.ENTITY);
    }


    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> biConsumer) {
       /* biConsumer.accept(ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,"entities/dragonfly"),
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(LootItem.lootTableItem(ModItems.DRAGONFLY.get())
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)))
                                .apply(SmeltItemFunction.smelted()
                                        .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,ENTITY_ON_FIRE)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                        )));

        biConsumer.accept(ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,"entities/flower_crab"),
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(LootItem.lootTableItem(ModItems.CRAB_MEAT.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,2.0f)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,ENTITY_ON_FIRE)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 3.0F)))
                        )));
*/
    }

    static class ChestDataGen extends SimpleFabricLootTableProvider{

        public ChestDataGen(FabricDataOutput output,CompletableFuture<HolderLookup.Provider> registryLookup) {
            super(output, registryLookup, LootContextParamSets.CHEST);
        }

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> biConsumer) {
            LootTable.Builder villageLoot = LootTable.lootTable().withPool(LootPool.lootPool()
                    .setRolls(UniformGenerator.between(1,4))
                    .add(LootItem.lootTableItem(ModItems.PEPPER_SEED.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f,4.0f))))
                    .add(LootItem.lootTableItem(ModBlocks.LIME_SAPLING.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f,4.0f))))
                    .add(LootItem.lootTableItem(ModBlocks.PAPAYA_SAPLING.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f,4.0f)))));

            biConsumer.accept(ModLootTable.INJECT_VILLAGE_DESERT_HOUSE,villageLoot);
            biConsumer.accept(ModLootTable.INJECT_VILLAGE_PLAINS_HOUSE,villageLoot);
            biConsumer.accept(ModLootTable.INJECT_VILLAGE_TAIGA_HOUSE,villageLoot);
            biConsumer.accept(ModLootTable.INJECT_VILLAGE_SNOWY_HOUSE,villageLoot);
            biConsumer.accept(ModLootTable.INJECT_VILLAGE_SAVANNA_HOUSE,villageLoot);

            biConsumer.accept(ModLootTable.INJECT_ABANDONED_MINESHAFT,
                    LootTable.lootTable().withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(1,2))
                            .add(LootItem.lootTableItem(ModItems.PEPPER_SEED.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                            .add(LootItem.lootTableItem(ModBlocks.LIME_SAPLING.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                            .add(LootItem.lootTableItem(ModBlocks.PAPAYA_SAPLING.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f)))))
            );

            biConsumer.accept(ModLootTable.INJECT_PILLAGER_OUTPOST,
                    LootTable.lootTable().withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(1,2))
                            .add(LootItem.lootTableItem(ModItems.PEPPER_SEED.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                            .add(LootItem.lootTableItem(ModBlocks.LIME_SAPLING.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                            .add(LootItem.lootTableItem(ModBlocks.PAPAYA_SAPLING.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f)))))
            );

            biConsumer.accept(ModLootTable.VILLAGE_THAI_HOUSE,
                    LootTable.lootTable()
                            .withPool(LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(1,6))
                                    .add(LootItem.lootTableItem(ModItems.PEPPER_SEED.get())
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                                    .add(LootItem.lootTableItem(ModBlocks.LIME_SAPLING.get())
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                                    .add(LootItem.lootTableItem(ModBlocks.PAPAYA_SAPLING.get())
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                                    .add(LootItem.lootTableItem(vectorwing.farmersdelight.common.registry.ModItems.CABBAGE_SEEDS.get())
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                                    .add(LootItem.lootTableItem(vectorwing.farmersdelight.common.registry.ModItems.TOMATO_SEEDS.get())
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                                    .add(LootItem.lootTableItem(Items.WHEAT_SEEDS)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,4.0f))))
                                    .add(LootItem.lootTableItem(Items.BEETROOT_SEEDS)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,5.0f))))
                                    .add(LootItem.lootTableItem(Items.CARROT)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,4.0f))))
                                    .add(LootItem.lootTableItem(Items.POTATO)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,4.0f))))
                            ));
        }
    }
}
