package net.firemuffin303.thaidelight.neoforge.common.block.vegetation.pepper;

import net.firemuffin303.thaidelight.common.block.vegetations.pepper.PepperCropBlock;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import vectorwing.farmersdelight.common.block.BuddingBushBlock;

public class ForgeBuddingPepperBlock extends BuddingBushBlock implements BonemealableBlock {
    public static final IntegerProperty PEPPER_AGE = BlockStateProperties.AGE_1;
    public ForgeBuddingPepperBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PEPPER_AGE,0));
    }

    /*
    @Override
    public BlockState getPlant(BlockGetter world, BlockPos pos) {
        return ModBlocks.BUDDING_PEPPER_CROP.get().defaultBlockState();
    }

     */

    @Override
    public boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(vectorwing.farmersdelight.common.registry.ModBlocks.RICH_SOIL_FARMLAND.get()) || pState.is(Blocks.FARMLAND);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        //if (state.getValue(AGE) == 2) {
        //    level.setBlock(currentPos, ModBlocks.PEPPER_CROP.defaultBlockState(), 3);
        //}
        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public boolean canGrowPastMaxAge() {
        return true;
    }

    @Override
    public void growPastMaxAge(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.setBlockAndUpdate(pos, ModBlocks.PEPPER_CROP.get().defaultBlockState());
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return PEPPER_AGE;
    }

    @Override
    public int getMaxAge() {
        return 1;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PEPPER_AGE);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    protected int getBonemealAgeIncrease(Level level) {
        return Mth.nextInt(level.random, 1, 2);
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int maxAge = getMaxAge();
        int ageGrowth = Math.min(getAge(state) + getBonemealAgeIncrease(level), 7);
        if (ageGrowth <= maxAge) {
            level.setBlockAndUpdate(pos, state.setValue(PEPPER_AGE, ageGrowth));
        } else {
            int remainingGrowth = ageGrowth - maxAge - 1;
            level.setBlockAndUpdate(pos, ModBlocks.PEPPER_CROP.get().defaultBlockState().setValue(PepperCropBlock.AGE, remainingGrowth));
        }
    }
}
