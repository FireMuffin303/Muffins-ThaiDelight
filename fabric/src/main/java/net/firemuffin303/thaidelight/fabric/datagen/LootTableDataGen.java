package net.firemuffin303.thaidelight.fabric.datagen;

import com.nhoryzon.mc.farmersdelight.registry.ItemsRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class LootTableDataGen extends SimpleFabricLootTableProvider {
    protected static final EntityPredicate.Builder ENTITY_ON_FIRE = EntityPredicate.Builder.entity().flags(net.minecraft.advancements.critereon.EntityFlagsPredicate.Builder.flags().setOnFire(true).build());

    public LootTableDataGen(FabricDataOutput output) {
        super(output, LootContextParamSets.ENTITY);
    }


    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
        biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"entities/dragonfly"),
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(LootItem.lootTableItem(ModItems.DRAGONFLY)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,ENTITY_ON_FIRE)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                        )));

        biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"entities/flower_crab"),
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(LootItem.lootTableItem(ModItems.CRAB_MEAT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,2.0f)))
                                .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,ENTITY_ON_FIRE)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 3.0F)))
                        )));
    }

    static class ChestDataGen extends SimpleFabricLootTableProvider{

        public ChestDataGen(FabricDataOutput output) {
            super(output, LootContextParamSets.CHEST);
        }

        @Override
        public void generate(BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
            LootTable.Builder villageLoot = LootTable.lootTable().withPool(LootPool.lootPool()
                    .setRolls(UniformGenerator.between(1,4))
                    .add(LootItem.lootTableItem(ModItems.PEPPER_SEED)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f,4.0f))))
                    .add(LootItem.lootTableItem(ModBlocks.LIME_SAPLING)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f,4.0f))))
                    .add(LootItem.lootTableItem(ModItems.PAPAYA_SEED)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f,4.0f)))));

            biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"inject/chests/village/village_desert_house"),villageLoot);
            biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"inject/chests/village/village_plains_house"),villageLoot);
            biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"inject/chests/village/village_taiga_house"),villageLoot);
            biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"inject/chests/village/village_snowy_house"),villageLoot);
            biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"inject/chests/village/village_savanna_house"),villageLoot);

            biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"inject/chests/abandoned_mineshaft"),
                    LootTable.lootTable().withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(1,2))
                            .add(LootItem.lootTableItem(ModItems.PEPPER_SEED)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                            .add(LootItem.lootTableItem(ModBlocks.LIME_SAPLING)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                            .add(LootItem.lootTableItem(ModItems.PAPAYA_SEED)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f)))))
            );

            biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"inject/chests/pillager_outpost"),
                    LootTable.lootTable().withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(1,2))
                            .add(LootItem.lootTableItem(ModItems.PEPPER_SEED)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                            .add(LootItem.lootTableItem(ModBlocks.LIME_SAPLING)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                            .add(LootItem.lootTableItem(ModItems.PAPAYA_SEED)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f)))))
            );

            biConsumer.accept(new ResourceLocation(ThaiDelight.MOD_ID,"chests/village/village_thai_house"),
                    LootTable.lootTable()
                            .withPool(LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1))
                                    .add(LootItem.lootTableItem(ModItems.ESAN_MUSIC_DISC)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f,1.0f)))))
                            .withPool(LootPool.lootPool()
                                    .setRolls(UniformGenerator.between(1,6))
                                    .add(LootItem.lootTableItem(ModItems.PEPPER_SEED)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                                    .add(LootItem.lootTableItem(ModBlocks.LIME_SAPLING)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                                    .add(LootItem.lootTableItem(ModItems.PAPAYA_SEED)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                                    .add(LootItem.lootTableItem(ItemsRegistry.CABBAGE_SEEDS.get())
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                                    .add(LootItem.lootTableItem(ItemsRegistry.TOMATO_SEEDS.get())
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
