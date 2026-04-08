package net.firemuffin303.thaidelight.common.world.feature;

import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

import java.util.List;
import java.util.Optional;

public class ModTreeConfiguration extends TreeConfiguration {
    public ModTreeConfiguration(BlockStateProvider blockStateProvider, TrunkPlacer trunkPlacer, BlockStateProvider blockStateProvider2, FoliagePlacer foliagePlacer, Optional<RootPlacer> optional, BlockStateProvider blockStateProvider3, FeatureSize featureSize, List<TreeDecorator> list, boolean bl, boolean bl2) {
        super(blockStateProvider, trunkPlacer, blockStateProvider2, foliagePlacer, optional, blockStateProvider3, featureSize, list, bl, bl2);
    }
}
