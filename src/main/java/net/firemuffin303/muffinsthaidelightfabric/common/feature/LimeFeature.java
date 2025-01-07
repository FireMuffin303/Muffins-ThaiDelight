package net.firemuffin303.muffinsthaidelightfabric.common.feature;

import com.mojang.serialization.Codec;
import net.firemuffin303.muffinsthaidelightfabric.common.block.lime.LimeLeavesBlock;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class LimeFeature extends Feature<NoneFeatureConfiguration> {
    public LimeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {
        LevelAccessor levelAccessor = featurePlaceContext.level();
        BlockPos blockPos = featurePlaceContext.origin();
        BlockState limeBush = ModBlocks.LIME_BUSH.defaultBlockState();
        if(limeBush.canSurvive(levelAccessor,blockPos)){
            levelAccessor.setBlock(blockPos, limeBush, 2);
            if(levelAccessor.getBlockState(blockPos.above()).is(Blocks.AIR)){
                levelAccessor.setBlock(blockPos.above(1),ModBlocks.LIME_LEAVES.defaultBlockState().setValue(LimeLeavesBlock.CAN_PRODUCE,true), 2);

            }
            return true;
        }

        return false;
    }
}
