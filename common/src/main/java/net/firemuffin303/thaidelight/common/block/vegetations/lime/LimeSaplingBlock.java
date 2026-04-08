package net.firemuffin303.thaidelight.common.block.vegetations.lime;

import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import static net.minecraft.world.level.block.DoublePlantBlock.copyWaterloggedFrom;

public class LimeSaplingBlock extends BushBlock implements BonemealableBlock {
    private static final VoxelShape SAPLING_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);


    public LimeSaplingBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SAPLING_SHAPE;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (randomSource.nextInt(5) == 0 && serverLevel.getRawBrightness(blockPos.above(), 0) >= 9) {
            this.grow(serverLevel, blockPos);
            //serverLevel.setBlock(blockPos, (BlockState) ModBlocks.LIME_BUSH.defaultBlockState().setValue(LimeCropBlock.AGE, 0), 3);

        }
    }

    public boolean canGrow(LevelReader levelReader, BlockPos blockPos){
        return levelReader.getRawBrightness(blockPos, 0) >= 8 && levelReader.getBlockState(blockPos.above()).isAir();
    }



    public void grow(ServerLevel serverLevel,BlockPos blockPos){
        if(this.canGrow(serverLevel,blockPos)){
            BlockState blockState = ModBlocks.LIME_PLANT.get().defaultBlockState().setValue(LimePlantBlock.AGE,0);
            serverLevel.setBlock(blockPos,blockState.setValue(LimePlantBlock.HALF,DoubleBlockHalf.LOWER),2);
            serverLevel.setBlock(blockPos.above(),
                    copyWaterloggedFrom(serverLevel, blockPos, blockState.setValue(LimePlantBlock.HALF, DoubleBlockHalf.UPPER)), 3);
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
        return this.canGrow(levelReader, blockPos);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        this.grow(serverLevel, blockPos);
        //serverLevel.setBlock(blockPos, (BlockState) ModBlocks.LIME_BUSH.defaultBlockState().setValue(LimeCropBlock.AGE, 0), 3);
    }
}
