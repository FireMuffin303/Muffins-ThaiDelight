package net.firemuffin303.thaidelight.common.registry.forge;

import net.firemuffin303.muffinsmcapi.api.BoatRegistry;
import net.firemuffin303.muffinsmcapi.forge.api.registry.ForgeOvenRegistry;
import net.firemuffin303.muffinsmcapi.forge.common.ModBoatVariants;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBoatVariantsImpl {
    public static final DeferredRegister<OvenBoatVariant> BOAT_VARIANT = DeferredRegister.create(BoatRegistry.OVEN_BOAT_VARIANT, ThaiDelightCommon.MOD_ID);

    public static Supplier<OvenBoatVariant> register(String id, Supplier<OvenBoatVariant> ovenBoatVariant) {
        return BOAT_VARIANT.register(id,ovenBoatVariant);
    }
}
