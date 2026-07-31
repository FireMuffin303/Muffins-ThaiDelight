package net.firemuffin303.thaidelight.datagen.loottable;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModCustomLootTableProvider extends SimpleFabricLootTableProvider {

    protected final HolderLookup.Provider registries;

    public ModCustomLootTableProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.BLOCK);
        this.registries = registryLookup.join();
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> biConsumer) {


        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        biConsumer.accept(ModLootTables.LIME_HARVEST,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.LIME.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,4)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.upperBound(6)))
                ));

        biConsumer.accept(ModLootTables.LIME_SHEARS,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.LIME_SAPLING.get()))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.upperBound(2)))
                ));

        biConsumer.accept(ModLootTables.BASIL_HARVEST,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BASIL.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1,3)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.upperBound(6)))
                ));

        biConsumer.accept(ModLootTables.BUTTERFLY_PEA_HARVEST,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BUTTERFLY_PEA.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,4)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.upperBound(6)))
                ));

        biConsumer.accept(ModLootTables.PEPPER_HARVEST,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.PEPPER.get()))
                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(registryLookup.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,3)))
                        .apply(LimitCount.limitCount(IntRange.upperBound(6)))
                ));

        biConsumer.accept(ModLootTables.BUTTERFLY_PEA_SHEARS,
                new LootTable.Builder().withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BUTTERFLY_PEA_SEEDS.get()))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.upperBound(2)))
                ).withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BUTTERFLY_PEA.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,4)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.upperBound(6)))
                )
        );
    }
}
