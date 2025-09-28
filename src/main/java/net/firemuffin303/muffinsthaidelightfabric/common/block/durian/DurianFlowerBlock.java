package net.firemuffin303.muffinsthaidelightfabric.common.block.durian;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DurianFlowerBlock extends Block implements SimpleWaterloggedBlock, BonemealableBlock {
    private static final VoxelShape HANGING_SHAPE = Block.box(2.0, 13.0, 2.0, 14.0, 16.0, 14.0);
    private static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 3.0, 14.0);
    public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public DurianFlowerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(HANGING,false)
                .setValue(WATERLOGGED,false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HANGING,WATERLOGGED);
    }

    //Placement
    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return isHanging(blockState) ? Block.canSupportCenter(levelReader,blockPos.above(), Direction.DOWN) || levelReader.getBlockState(blockPos.above()).is(BlockTags.LEAVES) : canSupportCenter(levelReader, blockPos.below(), Direction.UP);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        return !blockState.canSurvive(levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        FluidState fluidState = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());
        for (Direction direction : blockPlaceContext.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                BlockState blockState = this.defaultBlockState().setValue(HANGING, direction == Direction.UP);
                if (blockState.canSurvive(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos())) {
                    return blockState.setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
                }
            }
        }
        return null;
    }

    //Growing
    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if(isHanging(blockState) && serverLevel.getBlockState(blockPos.above()).is(ModBlocks.DURIAN_LEAVES)){
            if(randomSource.nextInt(7) == 0){
                grow(serverLevel,blockPos,blockState);
            }
        }
    }

    private void grow(Level level,BlockPos blockPos,BlockState blockState){
        level.setBlock(blockPos,ModBlocks.DURIAN_BLOCK.defaultBlockState()
                        .setValue(HangingDurianBlock.HANGING,true)
                        .setValue(HangingDurianBlock.AGE,0)
                        .setValue(HangingDurianBlock.WATERLOGGED,blockState.getValue(WATERLOGGED))
                ,2);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return isHanging(blockState) ? HANGING_SHAPE : SHAPE;
    }

    private static boolean isHanging(BlockState blockState){
        return blockState.getValue(HANGING);
    }

    @Override
    public FluidState getFluidState(BlockState blockState) {
        return (Boolean)blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }


    // ------------- Bone meal -----------
    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
        return isHanging(blockState) && levelReader.getBlockState(blockPos.above()).is(ModBlocks.DURIAN_LEAVES);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return isHanging(blockState) && level.getBlockState(blockPos.above()).is(ModBlocks.DURIAN_LEAVES);
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        grow(serverLevel,blockPos,blockState);
    }
}
