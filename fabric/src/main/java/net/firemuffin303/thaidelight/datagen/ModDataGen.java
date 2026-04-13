package net.firemuffin303.thaidelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.firemuffin303.thaidelight.common.registry.ModDamageTypes;
import net.firemuffin303.thaidelight.common.registry.ModFeatures;
import net.firemuffin303.thaidelight.common.registry.fabric.ModFeaturesImpl;
import net.firemuffin303.thaidelight.datagen.loottable.*;
import net.firemuffin303.thaidelight.datagen.tag.ModBlockTagDataGen;
import net.firemuffin303.thaidelight.datagen.tag.ModDamageTypeTagDataGen;
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

        pack.addProvider(ModBlockTagDataGen::new);
        pack.addProvider(ModTagDataGen.ModItemTagDataGen::new);
        pack.addProvider(ModTagDataGen.ModEntityTypesTagDataGen::new);
        pack.addProvider(ModTagDataGen.ModBiomeTagDataGen::new);
        pack.addProvider(ModDamageTypeTagDataGen::new);

        pack.addProvider(ModRecipeDataGen::new);
        pack.addProvider(ModDynamicDataGen::new);

    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, ModFeaturesImpl::bootstrapConfiguredFeature);
        registryBuilder.add(Registries.PLACED_FEATURE, ModFeatures::bootstrapPlacedFeature);
        registryBuilder.add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap);
    }
}
