package net.firemuffin303.muffinsthaidelightfabric.registry;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageTypes {
    public static final ResourceKey<DamageType> FALLING_DURIAN = ResourceKey.create(Registries.DAMAGE_TYPE, ThaiDelight.modid("durian"));

    public static void init(){}

    public static void bootstrap(BootstapContext<DamageType> damageTypeBootstapContext) {
        damageTypeBootstapContext.register(FALLING_DURIAN,new DamageType("durian",0.1f));
    }

    public static void dataGen(HolderLookup.Provider provider, FabricDynamicRegistryProvider.Entries entries) {
        entries.add(provider.lookupOrThrow(Registries.DAMAGE_TYPE),FALLING_DURIAN);
    }
}
