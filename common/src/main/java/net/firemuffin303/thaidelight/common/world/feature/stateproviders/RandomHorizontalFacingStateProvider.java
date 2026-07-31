package net.firemuffin303.thaidelight.common.world.feature.stateproviders;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.firemuffin303.thaidelight.common.registry.ModBlockStateProviderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;

public class RandomHorizontalFacingStateProvider extends BlockStateProvider {
    final BlockState block;

    public static final MapCodec<RandomHorizontalFacingStateProvider> CODEC = BlockState.CODEC
            .fieldOf("block")
            .xmap(RandomHorizontalFacingStateProvider::new, simpleStateProvider -> simpleStateProvider.block);

    public RandomHorizontalFacingStateProvider(BlockState block){
        this.block = block;
    }

    @Override
    protected BlockStateProviderType<?> type() {
        return ModBlockStateProviderTypes.RANDOM_HORIZONTAL_FACING.get();
    }

    @Override
    public BlockState getState(RandomSource randomSource, BlockPos blockPos) {
        BlockState blockState = this.block;
        return blockState.setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.Plane.HORIZONTAL.getRandomDirection(randomSource));
    }
}
