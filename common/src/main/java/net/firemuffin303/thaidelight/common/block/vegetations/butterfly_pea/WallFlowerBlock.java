package net.firemuffin303.thaidelight.common.block.vegetations.butterfly_pea;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class WallFlowerBlock extends MultifaceBlock implements SimpleWaterloggedBlock, SuspiciousEffectHolder,BonemealableBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final MultifaceSpreader spreader = new MultifaceSpreader(this);

    private final MobEffect suspiciousStewEffect;
    private final int effectDuration;
    private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
            Direction.NORTH, Block.box(0f, 0.0f, 14.0f, 16f, 16.0f, 16.0f),
            Direction.SOUTH, Block.box(0f, 0.0f, 0.0f, 16f, 16.0f, 2.0f),
            Direction.WEST, Block.box(14f, 0.0f, 0.0f, 16, 16.0f, 16f),
            Direction.EAST, Block.box(0f, 0.0f, 0.0f, 2f, 16.0f, 16f))
    );


    public WallFlowerBlock(Properties properties,MobEffect mobEffect,int effectDuration) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
        this.suspiciousStewEffect = mobEffect;
        if (mobEffect.isInstantenous()) {
            this.effectDuration = effectDuration;
        } else {
            this.effectDuration = effectDuration * 20;
        }
    }

    @Override
    public BlockState updateShape(
            BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2
    ) {
        if ((Boolean)blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Override
    public boolean canBeReplaced(BlockState blockState, BlockPlaceContext blockPlaceContext) {
        return !blockPlaceContext.getItemInHand().is(ModItems.BUTTERFLY_PEA.get()) || super.canBeReplaced(blockState, blockPlaceContext);
    }

    @Override
    public MultifaceSpreader getSpreader() {
        return this.spreader;
    }

    @Override
    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }

    @Override
    public MobEffect getSuspiciousEffect() {
        return this.suspiciousStewEffect;
    }

    @Override
    public int getEffectDuration() {
        return this.effectDuration;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
        return Direction.stream().anyMatch((direction) -> {
            return this.spreader.canSpreadInAnyDirection(blockState, levelReader, blockPos, direction.getOpposite());
        });
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        this.spreader.spreadFromRandomFaceTowardRandomDirection(blockState,serverLevel,blockPos,randomSource);
    }
}
