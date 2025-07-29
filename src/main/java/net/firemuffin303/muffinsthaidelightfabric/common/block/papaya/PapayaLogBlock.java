package net.firemuffin303.muffinsthaidelightfabric.common.block.papaya;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class PapayaLogBlock extends RotatedPillarBlock implements BonemealableBlock {
    public static final BooleanProperty BOTTOM = BlockStateProperties.BOTTOM;


    public PapayaLogBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BOTTOM,false));
    }


    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
        for(Direction direction : Direction.Plane.HORIZONTAL){
            Direction direction2 = direction.getOpposite();
            BlockPos blockPos2 = blockPos.offset(direction2.getStepX(), 0, direction2.getStepZ());
            if(levelReader.getBlockState(blockPos2).isAir()){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BOTTOM);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        boolean isPapayaLogAbove = levelAccessor.getBlockState(blockPos.above()).is(ModBlocks.PAPAYA_LOG);
        boolean isDirtBelow = levelAccessor.getBlockState(blockPos.below()).is(BlockTags.DIRT);
        boolean isVertical = blockState.getValue(AXIS).isVertical();
        if(isPapayaLogAbove && isDirtBelow && isVertical){
            return blockState.setValue(BOTTOM,true);
        }
        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2).setValue(BOTTOM,false);
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            Direction direction2 = direction.getOpposite();
            BlockPos blockPos2 = blockPos.offset(direction2.getStepX(), 0, direction2.getStepZ());
            if (serverLevel.getBlockState(blockPos2).isAir()) {
                serverLevel.setBlock(blockPos2, (BlockState) ((BlockState) ModBlocks.PAPAYA.defaultBlockState().setValue(PapayaBlock.AGE, 0).setValue(PapayaBlock.FACING, direction)), 2);
                return;
            }
        }
    }
}
