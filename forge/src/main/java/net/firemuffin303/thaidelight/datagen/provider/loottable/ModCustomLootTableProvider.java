package net.firemuffin303.thaidelight.datagen.provider.loottable;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModLootTables;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class ModCustomLootTableProvider implements LootTableSubProvider {

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
        biConsumer.accept(ModLootTables.LIME_HARVEST,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.LIME.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,4)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(6)))
                ));

        biConsumer.accept(ModLootTables.LIME_SHEARS,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.LIME_SAPLING.get()))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(2)))
                ));

        biConsumer.accept(ModLootTables.BASIL_HARVEST,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BASIL.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1,3)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(6)))
                ));

        /*
        biConsumer.accept(ModLootTables.BASIL_SHEARS,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BASIL_SAPLING))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(2)))
                ));
         */

        biConsumer.accept(ModLootTables.HOLY_BASIL_HARVEST,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.HOLY_BASIL.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1,3)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(6)))
                ));

        /*
        biConsumer.accept(ModLootTables.HOLY_BASIL_SHEARS,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.HOLY_BASIL_SAPLING))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(2)))
                ));

         */

        biConsumer.accept(ModLootTables.BUTTERFLY_PEA_HARVEST,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BUTTERFLY_PEA.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,4)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(6)))
                ));

        biConsumer.accept(ModLootTables.PEPPER_HARVEST,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.PEPPER.get()))
                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,3)))
                        .apply(LimitCount.limitCount(IntRange.upperBound(6)))
                ));

        biConsumer.accept(ModLootTables.BUTTERFLY_PEA_SHEARS,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BUTTERFLY_PEA_SEEDS.get()))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(2)))
                ).withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BUTTERFLY_PEA.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,4)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(6)))
                )
        );
    }
}
