package net.firemuffin303.thaidelight.common.block.saucebowl;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public abstract class AbstractSauceBowl extends Block {
    private static final VoxelShape SHAPE = box(2.0d,0.0d,2.0d,14.0f,8.0f,14.0f);
    private final Map<Item, SauceBowlInteraction> interactions;

    public AbstractSauceBowl(Properties properties, Map<Item,SauceBowlInteraction> map) {
        super(properties);
        this.interactions = map;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        SauceBowlInteraction sauceBowlInteraction = this.interactions.get(itemStack.getItem());
        return sauceBowlInteraction.interact(blockState,level,blockPos,player,interactionHand,itemStack);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState blockState) {
        return true;
    }
}
