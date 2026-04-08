package net.firemuffin303.thaidelight.common.block.vegetations.papaya;

import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PapayaLeavesStemBlock extends BushBlock implements SimpleWaterloggedBlock, BonemealableBlock {
    public static final DirectionProperty PAPAYA_LEAVES_FACING = ModBlockStateProperties.PAPAYA_LEAVES_FACING;
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape Y_AXIS_AABB = Block.box(6, 0.0, 6, 10, 16.0, 10);
    protected static final VoxelShape Z_AXIS_AABB = Block.box(6, 6, 0.0, 10, 10, 16.0);
    protected static final VoxelShape X_AXIS_AABB = Block.box(0.0, 6, 6, 16.0, 10, 10);

    public PapayaLeavesStemBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(WATERLOGGED,false)
                .setValue(PAPAYA_LEAVES_FACING,Direction.UP)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED,PAPAYA_LEAVES_FACING);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return switch (blockState.getValue(PAPAYA_LEAVES_FACING).getAxis()) {
            case Z -> Z_AXIS_AABB;
            case Y -> Y_AXIS_AABB;
            default -> X_AXIS_AABB;
        };
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        return !blockState.canSurvive(levelAccessor,blockPos) ? Blocks.AIR.defaultBlockState() :
                super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        FluidState fluidState = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());
        boolean bl = fluidState.getType() == Fluids.WATER;
        return super.getStateForPlacement(blockPlaceContext).setValue(WATERLOGGED, bl);
    }

    @Override
    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        Direction direction = blockState.getValue(PAPAYA_LEAVES_FACING);
        BlockState parentState = levelReader.getBlockState(blockPos.relative(direction.getOpposite()));
        BlockState nextBlockState = levelReader.getBlockState(blockPos.relative(direction));
        return mayPlaceOn(parentState,levelReader,blockPos) && ( mayPlaceOn(nextBlockState,levelReader,blockPos) || nextBlockState.is(ModBlocks.PAPAYA_LEAVES.get()) );
    }

    @Override
    public boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.is(ModTags.PAPAYA_LOGS) || blockState.is(this) || super.mayPlaceOn(blockState, blockGetter, blockPos);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return super.getCollisionShape(blockState, blockGetter, blockPos, collisionContext);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
        Direction direction = blockState.getValue(PAPAYA_LEAVES_FACING);
        Optional<BlockPos> optional = BlockUtil.getTopConnectedBlock(levelReader, blockPos, blockState.getBlock(), direction, ModBlocks.PAPAYA_LEAVES.get());
        if (optional.isEmpty()) {
            return false;
        }
        BlockPos blockPos2 = optional.get().relative(direction);
        BlockState blockState2 = levelReader.getBlockState(blockPos2);
        return !levelReader.isOutsideBuildHeight(blockPos) && blockState2.isAir() || blockState2.is(Blocks.WATER);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        Direction direction = blockState.getValue(PAPAYA_LEAVES_FACING);
        Optional<BlockPos> optional = BlockUtil.getTopConnectedBlock(serverLevel, blockPos, blockState.getBlock(), direction, ModBlocks.PAPAYA_LEAVES.get());
        if(optional.isEmpty()){
            return;
        }

        BlockPos blockPos2 = optional.get();

        serverLevel.setBlock(blockPos2,blockState
                .setValue(PapayaLeavesStemBlock.PAPAYA_LEAVES_FACING,direction),2);

        serverLevel.setBlock(blockPos2.relative(direction,1),
                ModBlocks.PAPAYA_LEAVES.get().defaultBlockState().setValue(PapayaLeavesBlock.PAPAYA_LEAVES_FACING,direction),
                2
        );
    }
}
