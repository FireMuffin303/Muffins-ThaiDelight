package net.firemuffin303.muffinsthaidelightfabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModFeatures;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModelDataGen::new);
        pack.addProvider(ModBlockLootTableData::new);
        pack.addProvider(ModBlockLootTableData.ModEntityLootTableData::new);
        pack.addProvider(ModBlockLootTableData.ModChestLootTableData::new);
        pack.addProvider(AdvancementDataGen::new);
        pack.addProvider(LangDataGen::new);
        pack.addProvider(LangDataGen.ThaiLangData::new);
        pack.addProvider(ModTagDataGen.ModBlockTagDataGen::new);
        pack.addProvider(ModTagDataGen.ModItemTagDataGen::new);
        pack.addProvider(ModTagDataGen.ModEntityTypesTagDataGen::new);
        pack.addProvider(ModRecipeDataGen::new);
        pack.addProvider(ModDynamicDataGen::new);

    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, ModFeatures::bootstrapConfiguredFeature);
        registryBuilder.add(Registries.PLACED_FEATURE, ModFeatures::bootstrapPlacedFeature);
    }
}
