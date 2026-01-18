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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import vectorwing.farmersdelight.common.block.BuddingBushBlock;
import vectorwing.farmersdelight.common.block.TomatoVineBlock;
public class BuddingButterflyPeaBlock extends BuddingBushBlock implements BonemealableBlock {

    public static final IntegerProperty BUDDING_AGE = BlockStateProperties.AGE_2;
    public BuddingButterflyPeaBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getPlant(BlockGetter world, BlockPos pos) {
        return ModBlocks.BUDDING_BUTTERFLY_PEA_BLOCK.defaultBlockState();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return BUDDING_AGE;
    }

    @Override
    public int getMaxAge() {
        return 2;
    }

    @Override
    public boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(vectorwing.farmersdelight.common.registry.ModBlocks.RICH_SOIL_FARMLAND.get()) || pState.is(Blocks.FARMLAND);
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BUDDING_AGE);
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
        return Mth.nextInt(level.random, 1, 3);
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int maxAge = getMaxAge();
        int ageGrowth = Math.min(getAge(state) + getBonemealAgeIncrease(level), 4);
        if (ageGrowth <= maxAge) {
            level.setBlockAndUpdate(pos, state.setValue(BUDDING_AGE, ageGrowth));
        } else {
            int remainingGrowth = Mth.clamp(ageGrowth - maxAge - 1,0,2);
            level.setBlockAndUpdate(pos, ModBlocks.BUTTERFLY_PEA_BLOCK.defaultBlockState().setValue(ButterflyPeaVineBlock.VINE_AGE, remainingGrowth));
        }
    }
}
