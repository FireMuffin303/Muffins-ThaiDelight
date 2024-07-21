package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.common.block.cauldron.FermentedFishCauldron;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Map;

import static net.minecraft.core.cauldron.CauldronInteraction.WATER;
import static net.minecraft.core.cauldron.CauldronInteraction.fillBucket;

public class ModCauldronInteraction {
    static Map<Item, CauldronInteraction> FERMENTED_FISH = CauldronInteraction.newInteractionMap();

    static CauldronInteraction MAKE_FERMENTED_FISH = ((blockState, level, blockPos, player, interactionHand, itemStack) -> {
        if(!level.isClientSide) {
            if (itemStack.is(ItemTags.FISHES) && blockState.getValue(LayeredCauldronBlock.LEVEL) == 3) {
                if(!player.isCreative()){
                    itemStack.shrink(1);
                }
                level.setBlockAndUpdate(blockPos,ModBlocks.FERMENTED_FISH_CAULDRON.defaultBlockState().setValue(FermentedFishCauldron.FERMENT,0));
                level.playSound(null,blockPos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS,1.0f,1.0f);
                level.gameEvent(null, GameEvent.FLUID_PLACE,blockPos);
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    });

    static CauldronInteraction FERMENTED_FISH_BOTTLE = (blockState, level, blockPos, player, interactionHand, itemStack) -> {
      if(!level.isClientSide){
          if(itemStack.is(Items.GLASS_BOTTLE) && blockState.getValue(FermentedFishCauldron.FERMENT) == 2){
              level.setBlockAndUpdate(blockPos, Blocks.CAULDRON.defaultBlockState());

          }
      }
        return InteractionResult.sidedSuccess(level.isClientSide);
    };

    public static void init(){
        CauldronInteraction.addDefaultInteractions(FERMENTED_FISH);
        WATER.put(Items.COD,MAKE_FERMENTED_FISH);
        WATER.put(Items.SALMON,MAKE_FERMENTED_FISH);
        WATER.put(Items.TROPICAL_FISH,MAKE_FERMENTED_FISH);
        WATER.put(Items.PUFFERFISH,MAKE_FERMENTED_FISH);

        FERMENTED_FISH.put(Items.GLASS_BOTTLE,(blockState, level, blockPos, player, interactionHand, itemStack) -> {
           return fillBucket(blockState,level,blockPos,player,interactionHand,itemStack,new ItemStack(ModItems.FERMENTED_FISH_BOTTLE),(blockState1 -> {
               return blockState1.getValue(FermentedFishCauldron.FERMENT) == 2;
           }),SoundEvents.BOTTLE_FILL);
        });
    }
}
