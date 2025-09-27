package net.firemuffin303.muffinsthaidelightfabric.common.world.trees;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class DurianTreeGrower extends AbstractTreeGrower {
    @Override
    protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean bl) {
        return bl ? ModFeatures.FEATURE_COCONUT_TREE_BEE : ModFeatures.FEATURE_COCONUT_TREE;
    }
}
