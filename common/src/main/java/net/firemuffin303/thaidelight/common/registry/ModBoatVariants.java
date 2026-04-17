package net.firemuffin303.thaidelight.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.muffinsmcapi.impl.entity.boat.OvenBoatVariant;

import java.util.function.Supplier;

public class ModBoatVariants {
    public static final Supplier<OvenBoatVariant> DURIAN = register("durian",() -> new OvenBoatVariant.Builder().planks(ModItems.DURIAN_PLANKS).chestBoat(ModItems.DURIAN_CHEST_BOAT).boat(ModItems.DURIAN_BOAT).build());
    public static final Supplier<OvenBoatVariant> MANGO = register("mango",() -> new OvenBoatVariant.Builder().planks(ModItems.MANGO_PLANKS).chestBoat(ModItems.MANGO_CHEST_BOAT).boat(ModItems.MANGO_BOAT).build());
    public static final Supplier<OvenBoatVariant> COCONUT = register("coconut",() -> new OvenBoatVariant.Builder().planks(ModItems.COCONUT_PLANKS).chestBoat(ModItems.COCONUT_CHEST_BOAT).boat(ModItems.COCONUT_BOAT).build());

    public static void init(){}

    @ExpectPlatform
    public static Supplier<OvenBoatVariant> register(String id, Supplier<OvenBoatVariant> ovenBoatVariant){
        throw new AssertionError();
    }
}
