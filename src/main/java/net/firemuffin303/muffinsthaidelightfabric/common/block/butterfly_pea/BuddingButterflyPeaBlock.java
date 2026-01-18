package net.firemuffin303.muffinsthaidelightfabric.common.block.butterfly_pea;

import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.block.BuddingBushBlock;
import vectorwing.farmersdelight.common.block.TomatoVineBlock;
public class BuddingButterflyPeaBlock extends BuddingBushBlock implements BonemealableBlock {

    public BuddingButterflyPeaBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getPlant(BlockGetter world, BlockPos pos) {
        return ModBlocks.BUDDING_BUTTERFLY_PEA_BLOCK.defaultBlockState();
    }

    @Override
    public boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(vectorwing.farmersdelight.common.registry.ModBlocks.RICH_SOIL_FARMLAND.get()) || pState.is(Blocks.FARMLAND);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(AGE) == 4) {
            level.setBlock(currentPos, ModBlocks.BUTTERFLY_PEA_BLOCK.defaultBlockState(), 3);
        }
        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public boolean canGrowPastMaxAge() {
        return true;
    }

    @Override
    public void growPastMaxAge(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.setBlockAndUpdate(pos, ModBlocks.BUTTERFLY_PEA_BLOCK.defaultBlockState());
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    protected int getBonemealAgeIncrease(Level level) {
        return Mth.nextInt(level.random, 1, 4);
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int maxAge = getMaxAge();
        int ageGrowth = Math.min(getAge(state) + getBonemealAgeIncrease(level), 7);
        if (ageGrowth <= maxAge) {
            level.setBlockAndUpdate(pos, state.setValue(AGE, ageGrowth));
        } else {
            int remainingGrowth = Mth.clamp(ageGrowth - maxAge - 1,0,2);
            level.setBlockAndUpdate(pos, ModBlocks.BUTTERFLY_PEA_BLOCK.defaultBlockState().setValue(ButterflyPeaVineBlock.VINE_AGE, remainingGrowth));
        }
    }
}
