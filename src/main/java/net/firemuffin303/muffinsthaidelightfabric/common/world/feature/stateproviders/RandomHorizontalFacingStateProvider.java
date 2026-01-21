package net.firemuffin303.muffinsthaidelightfabric.common.world.feature.stateproviders;

import com.mojang.serialization.Codec;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlockStateProviderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;

public class RandomHorizontalFacingStateProvider extends BlockStateProvider {
    final BlockState block;

    public static final Codec<RandomHorizontalFacingStateProvider> CODEC = BlockState.CODEC
            .fieldOf("block")
            .xmap(RandomHorizontalFacingStateProvider::new, simpleStateProvider -> simpleStateProvider.block)
            .codec();

    public RandomHorizontalFacingStateProvider(BlockState block){
        this.block = block;
    }

    @Override
    protected BlockStateProviderType<?> type() {
        return ModBlockStateProviderTypes.RANDOM_HORIZONTAL_FACING;
    }

    @Override
    public BlockState getState(RandomSource randomSource, BlockPos blockPos) {
        BlockState blockState = this.block;
        return blockState.setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.Plane.HORIZONTAL.getRandomDirection(randomSource));
    }
}
