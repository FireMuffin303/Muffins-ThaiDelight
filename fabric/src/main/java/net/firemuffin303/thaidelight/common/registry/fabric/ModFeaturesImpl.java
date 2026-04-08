package net.firemuffin303.thaidelight.common.registry.fabric;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.function.Supplier;

public class ModFeaturesImpl {
    public static <P extends TrunkPlacer> Supplier<TrunkPlacerType<P>> registerTrunkPlacer(String id, Supplier<TrunkPlacerType<P>> supplier) {
        TrunkPlacerType<P> registeredTrunkPlacer = Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, ThaiDelightCommon.modid(id),supplier.get());
        return () -> registeredTrunkPlacer;
    }

    public static <P extends FoliagePlacer> Supplier<FoliagePlacerType<P>> registerFoliagePlacer(String id, Supplier<FoliagePlacerType<P>> supplier) {
        FoliagePlacerType<P> registeredFoliagePlacer = Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE,ThaiDelightCommon.modid(id),supplier.get());
        return () -> registeredFoliagePlacer;
    }
}
