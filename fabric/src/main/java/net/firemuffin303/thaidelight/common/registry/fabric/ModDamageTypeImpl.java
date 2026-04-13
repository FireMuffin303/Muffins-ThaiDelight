package net.firemuffin303.thaidelight.common.registry.fabric;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.firemuffin303.thaidelight.common.registry.ModDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;

public class ModDamageTypeImpl {
    public static void dataGen(HolderLookup.Provider provider, FabricDynamicRegistryProvider.Entries entries) {
        entries.add(provider.lookupOrThrow(Registries.DAMAGE_TYPE), ModDamageTypes.FALLING_DURIAN);
    }
}
