package net.firemuffin303.muffinsthaidelightfabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.firemuffin303.muffinsthaidelightfabric.datagen.loottable.ModBlockLootTableProvider;
import net.firemuffin303.muffinsthaidelightfabric.datagen.loottable.ModChestLootTableProvider;
import net.firemuffin303.muffinsthaidelightfabric.datagen.loottable.ModCustomLootTableProvider;
import net.firemuffin303.muffinsthaidelightfabric.datagen.loottable.ModEntityLootTableProvider;
import net.firemuffin303.muffinsthaidelightfabric.datagen.tag.ModDamageTypeTagDataGen;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModDamageTypes;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModFeatures;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class ModDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModelDataGen::new);
        //LootTable
        pack.addProvider(ModBlockLootTableProvider::new);
        pack.addProvider(ModEntityLootTableProvider::new);
        pack.addProvider(ModChestLootTableProvider::new);
        pack.addProvider(ModCustomLootTableProvider::new);

        pack.addProvider(AdvancementDataGen::new);
        pack.addProvider(LangDataGen::new);
        pack.addProvider(LangDataGen.ThaiLangData::new);

        pack.addProvider(ModTagDataGen.ModBlockTagDataGen::new);
        pack.addProvider(ModTagDataGen.ModItemTagDataGen::new);
        pack.addProvider(ModTagDataGen.ModEntityTypesTagDataGen::new);
        pack.addProvider(ModTagDataGen.ModBiomeTagDataGen::new);
        pack.addProvider(ModDamageTypeTagDataGen::new);

        pack.addProvider(ModRecipeDataGen::new);
        pack.addProvider(ModDynamicDataGen::new);

    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, ModFeatures::bootstrapConfiguredFeature);
        registryBuilder.add(Registries.PLACED_FEATURE, ModFeatures::bootstrapPlacedFeature);
        registryBuilder.add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap);
    }
}
