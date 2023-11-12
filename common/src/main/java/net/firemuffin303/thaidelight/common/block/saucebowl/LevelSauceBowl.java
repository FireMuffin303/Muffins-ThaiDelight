package net.firemuffin303.thaidelight.common.block.saucebowl;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Map;

public class LevelSauceBowl extends AbstractSauceBowl{
    public static final IntegerProperty LEVEL = IntegerProperty.create("sauce_bowl_level",0,4);

    public LevelSauceBowl(Properties properties, Map<Item,SauceBowlInteraction> map) {
        super(properties,map);
        this.registerDefaultState((BlockState) this.stateDefinition.any().setValue(LEVEL,1));
    }


    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        return blockState.getValue(LEVEL);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{LEVEL});
    }
}
