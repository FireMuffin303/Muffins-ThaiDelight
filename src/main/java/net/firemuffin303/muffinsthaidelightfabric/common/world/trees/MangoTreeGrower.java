package net.firemuffin303.muffinsthaidelightfabric.common.world.trees;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class MangoTreeGrower extends AbstractTreeGrower {
    @Override
    protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean bl) {
        //if(randomSource.nextInt(10) == 0){
        //}

        return bl ? ModFeatures.FEATURE_FANCY_MANGO_TREE_BEE : ModFeatures.FEATURE_FANCY_MANGO_TREE;
        //return bl ? ModFeatures.FEATURE_MANGO_TREE_BEE : ModFeatures.FEATURE_MANGO_TREE;
    }
}
