package net.firemuffin303.muffinsthaidelightfabric.common.block.papaya;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PapayaLogBlock extends RotatedPillarBlock implements BonemealableBlock {

    public PapayaLogBlock(Properties properties) {
        super(properties);
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
