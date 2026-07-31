package net.firemuffin303.thaidelight.datagen.provider;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModFeatures;
import net.firemuffin303.thaidelight.common.registry.forge.ModFeaturesImpl;
import net.firemuffin303.thaidelight.neoforge.common.ModBiomeModifiers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ThaiDelightBiomeModifierProvider extends DatapackBuiltinEntriesProvider {
    private static final RegistrySetBuilder REGISTRY_SET_BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModFeaturesImpl::bootstrapConfiguredFeature)
            .add(Registries.PLACED_FEATURE, ModFeatures::bootstrapPlacedFeature)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
            //.add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap)
            ;

    public ThaiDelightBiomeModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries,REGISTRY_SET_BUILDER, Set.of(ThaiDelightCommon.MOD_ID));
    }
}
