package net.firemuffin303.muffinsthaidelightfabric.common.block.coconut;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class CoconutSaplingCropBlock extends CropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    public CoconutSaplingCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModBlocks.COCONUT;
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    @Override
    public BlockState getStateForAge(int i) {
        return i == 3 ? ModBlocks.COCONUT_SAPLING.defaultBlockState() : super.getStateForAge(i);
    }

    @Override
    protected int getBonemealAgeIncrease(Level level) {
        return 1;
    }
}
