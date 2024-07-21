package net.firemuffin303.thaidelight.common.block.cauldron;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.apache.commons.lang3.Validate;

import java.util.Map;

public class FermentedFishCauldron extends AbstractCauldronBlock {
    public static final int MAX_FERMENT_LEVEL = 2;
    public static final IntegerProperty FERMENT;
    private static final int FERMENTING_TIMER = 200;

    public FermentedFishCauldron(Properties properties, Map<Item, CauldronInteraction> map) {
        super(properties, map);
        this.registerDefaultState((BlockState) this.stateDefinition.any().setValue(FERMENT,0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(new Property[]{FERMENT});
    }


    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if(blockState.getValue(FERMENT) < 2) {
            serverLevel.playSound(null, blockPos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 0.7f, 0.9f + randomSource.nextFloat() * 0.2f);
            serverLevel.setBlock(blockPos, blockState.setValue(FERMENT, blockState.getValue(FERMENT) + 1), 2);
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return blockState.getValue(FERMENT) < 2;
    }

    @Override
    protected double getContentHeight(BlockState blockState) {
        return 0.9375D;
    }

    @Override
    public boolean isFull(BlockState blockState) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        return Mth.ceil(blockState.getValue(FERMENT) * 7.5f);
    }

    static {
        FERMENT = IntegerProperty.create("fermented",0,MAX_FERMENT_LEVEL);
    }
}
