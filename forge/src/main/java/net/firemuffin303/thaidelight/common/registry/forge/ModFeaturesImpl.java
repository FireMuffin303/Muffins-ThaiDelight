package net.firemuffin303.thaidelight.common.registry.forge;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModFeaturesImpl {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, ThaiDelightCommon.MOD_ID);
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE,ThaiDelightCommon.MOD_ID);

    public static <P extends TrunkPlacer> Supplier<TrunkPlacerType<P>> registerTrunkPlacer(String id, Supplier<TrunkPlacerType<P>> supplier) {
        return TRUNK_PLACER.register(id,supplier);
    }

    public static <P extends FoliagePlacer> Supplier<FoliagePlacerType<P>> registerFoliagePlacer(String id, Supplier<FoliagePlacerType<P>> supplier) {
        return FOLIAGE_PLACER.register(id,supplier);
    }
}
