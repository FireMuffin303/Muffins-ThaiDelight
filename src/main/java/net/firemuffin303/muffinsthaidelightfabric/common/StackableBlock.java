package net.firemuffin303.muffinsthaidelightfabric.common;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;

public interface StackableBlock {

    IntegerProperty getStackProperty();

    ItemLike getPickUpItem();

    default InteractionResult takeItem(Level level, BlockPos blockPos, BlockState blockState, Player player, InteractionHand interactionHand){
        if(player.getAbilities().mayBuild && player.getItemInHand(interactionHand).isEmpty()){
            int i = blockState.getValue(getStackProperty());
            ItemStack itemStack = new ItemStack(getPickUpItem());
            if (!player.getInventory().add(itemStack)) {
                player.drop(itemStack, false);
            }

            level.playSound(null,blockPos, SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.BLOCKS);

            if(blockState.getValue(getStackProperty()) == 1){
                level.removeBlock(blockPos,false);
                level.gameEvent(GameEvent.BLOCK_DESTROY, blockPos, GameEvent.Context.of(blockState));
                return InteractionResult.SUCCESS;
            }

            level.setBlockAndUpdate(blockPos, blockState.setValue(getStackProperty(),i-1));
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(blockState));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
