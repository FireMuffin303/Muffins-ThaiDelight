package net.firemuffin303.thaidelight.common.registry.fabric;

import net.firemuffin303.muffinsmcapi.fabric.api.FabricOvenRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

import java.util.function.Supplier;

public class ModBoatVariantsImpl {
    public static Supplier<OvenBoatVariant> register(String id, Supplier<OvenBoatVariant> ovenBoatVariant) {
        OvenBoatVariant variant = Registry.register(FabricOvenRegistry.OVEN_BOAT_VARIANT_REGISTRY, ResourceKey.create(FabricOvenRegistry.OVEN_BOAT_VARIANT_REGISTRY.key(),ThaiDelightCommon.modid(id)),ovenBoatVariant.get());
        return () -> variant;
    }
}
