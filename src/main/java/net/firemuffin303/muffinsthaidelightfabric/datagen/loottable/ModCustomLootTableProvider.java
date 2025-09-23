package net.firemuffin303.muffinsthaidelightfabric.datagen.loottable;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModLootTables;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class ModCustomLootTableProvider extends SimpleFabricLootTableProvider {
    public ModCustomLootTableProvider(FabricDataOutput output) {
        super(output, LootContextParamSets.BLOCK);
    }

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
        biConsumer.accept(ModLootTables.LIME_HARVEST,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.LIME))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,4)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(6)))
                ));

        biConsumer.accept(ModLootTables.LIME_SHEARS,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.LIME_SAPLING))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(2)))
                ));

        biConsumer.accept(ModLootTables.BASIL_HARVEST,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BASIL))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,4)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(6)))
                ));

        biConsumer.accept(ModLootTables.BASIL_SHEARS,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BASIL_SAPLING))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(2)))
                ));

        biConsumer.accept(ModLootTables.HOLY_BASIL_HARVEST,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.HOLY_BASIL))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,4)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(6)))
                ));

        biConsumer.accept(ModLootTables.HOLY_BASIL_SHEARS,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.HOLY_BASIL_SAPLING))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                        .apply(LimitCount.limitCount(IntRange.upperBound(2)))
                ));
    }
}
