package net.firemuffin303.thaidelight.common.block.cauldron;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Predicate;

public class SeafoodCauldronBlock extends LayeredCauldronBlock {
    public static final Predicate<Biome.Precipitation> NONE;

    public SeafoodCauldronBlock(Properties properties) {
        super(properties, NONE, ModCauldronInteraction.SEAFOOD);
    }

    protected void handleEntityOnFireInside(BlockState blockState, Level level, BlockPos blockPos) {
        lowerFillLevel((BlockState) Blocks.WATER_CAULDRON.defaultBlockState().setValue(LEVEL, (Integer)blockState.getValue(LEVEL)), level, blockPos);
    }

    static{
        NONE = (precipitation) -> {
            return precipitation == Biome.Precipitation.NONE;
        };
    }
}
