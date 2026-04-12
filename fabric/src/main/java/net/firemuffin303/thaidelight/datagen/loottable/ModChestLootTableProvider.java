package net.firemuffin303.thaidelight.datagen.loottable;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModLootTables;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class ModChestLootTableProvider extends SimpleFabricLootTableProvider {
    private final ResourceLocation[] VILLAGE_LOOT = {
            ModLootTables.INJECT_VILLAGE_PLAINS_HOUSE,
            ModLootTables.INJECT_VILLAGE_DESERT_HOUSE,
            ModLootTables.INJECT_VILLAGE_SAVANNA_HOUSE,
            ModLootTables.INJECT_VILLAGE_TAIGA_HOUSE,
            ModLootTables.INJECT_VILLAGE_SNOWY_HOUSE
    };

    public ModChestLootTableProvider(FabricDataOutput output) {
        super(output, LootContextParamSets.CHEST);
    }

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
        LootTable.Builder villageLoot = LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(UniformGenerator.between(1,4))
                .add(LootItem.lootTableItem(ModItems.PEPPER_SEED.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f,4.0f))))
                .add(LootItem.lootTableItem(ModItems.LIME_SAPLING.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f,4.0f))))
                .add(LootItem.lootTableItem(ModBlocks.PAPAYA_SAPLING.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f,4.0f)))));

        for (ResourceLocation resourceLocation : VILLAGE_LOOT){
            biConsumer.accept(resourceLocation,villageLoot);
        }

        biConsumer.accept(ModLootTables.INJECT_ABANDONED_MINESHAFT,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(1,2))
                        .add(LootItem.lootTableItem(ModItems.PEPPER_SEED.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                        .add(LootItem.lootTableItem(ModItems.LIME_SAPLING.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                        .add(LootItem.lootTableItem(ModBlocks.PAPAYA_SAPLING.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f)))))
        );

        biConsumer.accept(ModLootTables.INJECT_PILLAGER_OUTPOST,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(1,2))
                        .add(LootItem.lootTableItem(ModItems.PEPPER_SEED.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                        .add(LootItem.lootTableItem(ModItems.LIME_SAPLING.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                        .add(LootItem.lootTableItem(ModBlocks.PAPAYA_SAPLING.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f)))))
        );

        biConsumer.accept(ModLootTables.VILLAGE_THAI_HOUSE,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1,6))
                                .add(LootItem.lootTableItem(ModItems.PEPPER_SEED.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f))))
                                .add(LootItem.lootTableItem(ModItems.LIME_SAPLING.get())
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
