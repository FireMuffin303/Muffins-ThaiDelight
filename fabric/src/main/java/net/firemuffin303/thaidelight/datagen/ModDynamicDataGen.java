package net.firemuffin303.thaidelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModDamageTypes;
import net.firemuffin303.thaidelight.common.registry.fabric.ModDamageTypeImpl;
import net.firemuffin303.thaidelight.common.registry.fabric.ModFeaturesImpl;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModDynamicDataGen extends FabricDynamicRegistryProvider {
    public ModDynamicDataGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider provider, Entries entries) {
        ModFeaturesImpl.dataGen(provider,entries);
        ModDamageTypeImpl.dataGen(provider,entries);
    }

    @Override
    public String getName() {
        return ThaiDelightCommon.MOD_ID;
    }
}
