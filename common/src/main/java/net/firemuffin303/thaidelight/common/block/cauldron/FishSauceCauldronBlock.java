package net.firemuffin303.thaidelight.common.block.cauldron;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.function.Predicate;

public class FishSauceCauldronBlock extends LayeredCauldronBlock {
    public static final BooleanProperty FERMENTED;
    public static final Predicate<Biome.Precipitation> NONE;

    public FishSauceCauldronBlock(Properties properties) {
        super(properties, NONE, ModCauldronInteraction.FISH_SAUCE);
    }

    protected void handleEntityOnFireInside(BlockState blockState, Level level, BlockPos blockPos) {
        lowerFillLevel((BlockState) Blocks.WATER_CAULDRON.defaultBlockState().setValue(LEVEL, (Integer)blockState.getValue(LEVEL)), level, blockPos);
    }

    @Override
    public void handlePrecipitation(BlockState blockState, Level level, BlockPos blockPos, Biome.Precipitation precipitation) {
        if(precipitation != Biome.Precipitation.RAIN){
             boolean bl = level.getRandom().nextFloat() < 0.1f;
             if(bl && blockState.getValue(LEVEL) >= 3 && !blockState.getValue(FERMENTED)){
                 BlockState blockstate2 = blockState.setValue(FERMENTED,true);
                 level.setBlockAndUpdate(blockPos,blockstate2);
                 level.gameEvent(GameEvent.BLOCK_CHANGE,blockPos, GameEvent.Context.of(blockstate2));
             }
        }
    }



    static{
        NONE = (precipitation) -> {
            return precipitation == Biome.Precipitation.NONE;
        };

        FERMENTED = BooleanProperty.create("fermented");
    }
}
