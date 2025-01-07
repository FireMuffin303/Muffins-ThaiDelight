package net.firemuffin303.muffinsthaidelightfabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModFeatures;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModDynamicDataGen extends FabricDynamicRegistryProvider {
    public ModDynamicDataGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider provider, Entries entries) {
        ModFeatures.dataGen(provider,entries);
    }

    @Override
    public String getName() {
        return ThaiDelight.MOD_ID;
    }
}
