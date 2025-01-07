package net.firemuffin303.muffinsthaidelightfabric.common.block.lime;

import net.firemuffin303.muffinsthaidelightfabric.common.block.papaya.PapayaBlock;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class LimeLeavesBlock extends LeavesBlock implements BonemealableBlock {
    public static final BooleanProperty CAN_PRODUCE = BooleanProperty.create("can_produce");

    public LimeLeavesBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(CAN_PRODUCE,false)
                .setValue(WATERLOGGED,false)
                .setValue(DISTANCE,7)
                .setValue(PERSISTENT,true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CAN_PRODUCE);
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return blockState.getValue(CAN_PRODUCE);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            Direction direction2 = direction.getOpposite();
            BlockPos blockPos2 = blockPos.offset(direction2.getStepX(), 0, direction2.getStepZ());
            if (serverLevel.getBlockState(blockPos2).isAir() && randomSource.nextInt(5) == 0) {
                serverLevel.setBlock(blockPos2, ModBlocks.LIME_BLOCK.defaultBlockState().setValue(LimeBlock.AGE, 0).setValue(LimeBlock.FACING, direction), 2);
                return;
            }
        }
    }

    //--- Placement ---
    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        if(blockState.getValue(LimeLeavesBlock.CAN_PRODUCE)){
            BlockState blockBelow = levelReader.getBlockState(blockPos.below());
            return blockBelow.is(ModBlocks.LIME_BUSH);
        }
        return super.canSurvive(blockState, levelReader, blockPos);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        LevelAccessor levelAccessor = blockPlaceContext.getLevel();
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        FluidState fluidState = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());
        return this.defaultBlockState().setValue(PERSISTENT, true).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER).setValue(CAN_PRODUCE,levelAccessor.getBlockState(blockPos.below()).is(ModBlocks.LIME_BUSH));
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        return blockState.getValue(CAN_PRODUCE) && !canSurvive(blockState,levelAccessor,blockPos) ? Blocks.AIR.defaultBlockState() :  super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    //--- Bone Meal ---
    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
        if(blockState.getValue(CAN_PRODUCE)){
            for(Direction direction : Direction.Plane.HORIZONTAL){
                Direction direction2 = direction.getOpposite();
                BlockPos blockPos2 = blockPos.offset(direction2.getStepX(), 0, direction2.getStepZ());
                if(levelReader.getBlockState(blockPos2).isAir()){
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return blockState.getValue(CAN_PRODUCE);
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            Direction direction2 = direction.getOpposite();
            BlockPos blockPos2 = blockPos.offset(direction2.getStepX(), 0, direction2.getStepZ());
            if (serverLevel.getBlockState(blockPos2).isAir()) {
                serverLevel.setBlock(blockPos2, ModBlocks.LIME_BLOCK.defaultBlockState().setValue(LimeBlock.AGE, 0).setValue(LimeBlock.FACING, direction), 2);
                return;
            }
        }
    }
}
