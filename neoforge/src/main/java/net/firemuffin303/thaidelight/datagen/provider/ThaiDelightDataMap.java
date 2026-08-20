package net.firemuffin303.thaidelight.datagen.provider;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Strippable;

import java.util.concurrent.CompletableFuture;

public class ThaiDelightDataMap extends DataMapProvider {
    public ThaiDelightDataMap(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(ModItems.DURIAN_PEEL,new FurnaceFuel(200),false)
                .add(ModItems.DURIAN_PEEL_BLOCK,new FurnaceFuel(1800),false)
                .add(ModItems.COCONUT_LEAF_BLOCK,new FurnaceFuel(4001),false)
                .add(ModItems.PAPAYA_LEAVES,new FurnaceFuel(100),false)
                .add(ModItems.DURIAN_CABINET,new FurnaceFuel(300),false)
                .add(ModItems.MANGO_CABINET,new FurnaceFuel(300),false)
                .add(ModItems.COCONUT_CABINET,new FurnaceFuel(300),false);

        builder(NeoForgeDataMaps.STRIPPABLES)
                .add(ModBlocks.DURIAN_LOG,new Strippable(ModBlocks.STRIPPED_DURIAN_LOG.get()),false)
                .add(ModBlocks.DURIAN_WOOD,new Strippable(ModBlocks.STRIPPED_DURIAN_WOOD.get()),false)
                .add(ModBlocks.MANGO_LOG,new Strippable(ModBlocks.STRIPPED_MANGO_LOG.get()),false)
                .add(ModBlocks.MANGO_WOOD,new Strippable(ModBlocks.STRIPPED_MANGO_WOOD.get()),false)
                .add(ModBlocks.COCONUT_LOG,new Strippable(ModBlocks.STRIPPED_COCONUT_LOG.get()),false)
                .add(ModBlocks.COCONUT_WOOD,new Strippable(ModBlocks.STRIPPED_COCONUT_WOOD.get()),false)
                .add(ModBlocks.PAPAYA_LOG,new Strippable(ModBlocks.STRIPPED_PAPAYA_LOG.get()),false)
                .add(ModBlocks.PAPAYA_WOOD,new Strippable(ModBlocks.STRIPPED_PAPAYA_WOOD.get()),false);

        builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(ModItems.PEPPER_SEED,new Compostable(0.3f),false)
                .add(ModItems.PAPAYA_LEAVES,new Compostable(0.3f),false)
                .add(ModItems.LIME_SAPLING,new Compostable(0.3f),false)
                .add(ModItems.PAPAYA_SAPLING,new Compostable(0.3f),false)
                .add(ModItems.PEPPER,new Compostable(0.65f),false)
                .add(ModItems.LIME,new Compostable(0.65f),false)
                .add(ModItems.RAW_PAPAYA,new Compostable(0.65f),false)
                .add(ModItems.PAPAYA,new Compostable(0.65f),false)

                .add(ModItems.SLICED_LIME,new Compostable(0.4f),false)
                .add(ModItems.RAW_PAPAYA_SLICE,new Compostable(0.4f),false)
                .add(ModItems.SLICED_PAPAYA,new Compostable(0.4f),false)

                .add(ModItems.PAPAYA_LOG,new Compostable(0.8f),false)
                .add(ModItems.STRIPPED_PAPAYA_LOG,new Compostable(0.8f),false)
                .add(ModItems.PAPAYA_WOOD,new Compostable(0.8f),false)
                .add(ModItems.STRIPPED_PAPAYA_WOOD,new Compostable(0.8f),false)
                .add(ModItems.DURIAN_PEEL,new Compostable(0.8f),false);
    }
}
