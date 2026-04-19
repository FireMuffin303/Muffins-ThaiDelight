package net.firemuffin303.thaidelight.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.muffinsmcapi.api.BoatRegistry;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.firemuffin303.thaidelight.ThaiDelightCommon;

import java.util.function.Supplier;

public class ModBoatVariants {
    public static final ResourceRegistry<OvenBoatVariant> BOAT_VARIANT = ResourceRegistry.create(BoatRegistry.OVEN_BOAT_VARIANT, ThaiDelightCommon.MOD_ID);

    public static final Supplier<OvenBoatVariant> DURIAN = BOAT_VARIANT.register("durian",() -> new OvenBoatVariant.Builder(ModItems.DURIAN_BOAT,ModItems.DURIAN_CHEST_BOAT,ModBlocks.DURIAN_PLANKS).build());
    public static final Supplier<OvenBoatVariant> MANGO = BOAT_VARIANT.register("mango",() -> new OvenBoatVariant.Builder(ModItems.MANGO_BOAT,ModItems.MANGO_CHEST_BOAT,ModBlocks.MANGO_PLANKS).build());
    public static final Supplier<OvenBoatVariant> COCONUT = BOAT_VARIANT.register("coconut",() -> new OvenBoatVariant.Builder(ModItems.COCONUT_BOAT,ModItems.COCONUT_CHEST_BOAT,ModBlocks.COCONUT_PLANKS).build());

    public static void init(){
        BOAT_VARIANT.init();
    }

    @ExpectPlatform
    public static Supplier<OvenBoatVariant> register(String id, Supplier<OvenBoatVariant> ovenBoatVariant){
        throw new AssertionError();
    }
}
