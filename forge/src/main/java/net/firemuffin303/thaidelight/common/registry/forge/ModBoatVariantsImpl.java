package net.firemuffin303.thaidelight.common.registry.forge;

import net.firemuffin303.muffinsmcapi.api.BoatRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBoatVariantsImpl {
    public static final DeferredRegister<OvenBoatVariant> BOAT_VARIANT = DeferredRegister.create(BoatRegistry.OVEN_BOAT_VARIANT, ThaiDelightCommon.MOD_ID);

    public static Supplier<OvenBoatVariant> register(String id, Supplier<OvenBoatVariant> ovenBoatVariant) {
        return BOAT_VARIANT.register(id,ovenBoatVariant);
    }
}
