package net.firemuffin303.muffinsthaidelightfabric.common.block.butterfly_pea;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

public class WallFlowerBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock, SuspiciousEffectHolder {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final MobEffect suspiciousStewEffect;
    private final int effectDuration;
    public WallFlowerBlock(Properties properties,MobEffect mobEffect,int effectDuration) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false).setValue(FACING, Direction.NORTH));
        this.suspiciousStewEffect = mobEffect;
        if (mobEffect.isInstantenous()) {
            this.effectDuration = effectDuration;
        } else {
            this.effectDuration = effectDuration * 20;
        }
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        Direction direction = blockState.getValue(FACING);
        BlockPos blockPos2 = blockPos.relative(direction.getOpposite());
        return levelReader.getBlockState(blockPos2).isFaceSturdy(levelReader, blockPos2, direction);
    }

    @Override
    public BlockState updateShape(
            BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2
    ) {
        if ((Boolean)blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        return direction == ((Direction)blockState.getValue(FACING)).getOpposite() && !blockState.canSurvive(levelAccessor, blockPos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        LevelAccessor levelAccessor = blockPlaceContext.getLevel();
        BlockPos blockPos = blockPlaceContext.getClickedPos();

        return this.defaultBlockState()
                .setValue(WATERLOGGED, Boolean.valueOf(levelAccessor.getFluidState(blockPos).getType() == Fluids.WATER))
                .setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACING);
    }

    @Override
    public MobEffect getSuspiciousEffect() {
        return this.suspiciousStewEffect;
    }

    @Override
    public int getEffectDuration() {
        return this.effectDuration;
    }
}
