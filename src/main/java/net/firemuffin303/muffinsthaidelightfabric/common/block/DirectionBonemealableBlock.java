package net.firemuffin303.muffinsthaidelightfabric.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

public interface DirectionBonemealableBlock extends BonemealableBlock {

    boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState,Direction direction, boolean isClient);
    boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState, Direction direction);
    void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState,Direction direction);

    @Override
    default boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState){
        return false;
    }

    @Override
    default boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl){
        return false;
    }

    @Override
    default void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState){}
}
