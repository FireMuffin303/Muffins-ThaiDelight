package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.common.block.cauldron.FermentedFishCauldronBlock;
import net.firemuffin303.thaidelight.common.block.cauldron.CoconutCauldron;
import net.firemuffin303.thaidelight.common.block.cauldron.CoconutMilkCauldron;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Map;

import static net.minecraft.core.cauldron.CauldronInteraction.*;

public class ModCauldronInteraction {
    static InteractionMap FERMENTED_FISH = CauldronInteraction.newInteractionMap("fermented_fish");
    public static InteractionMap COCONUT = CauldronInteraction.newInteractionMap("coconut");
    public static InteractionMap COCONUT_MILK = CauldronInteraction.newInteractionMap("coconut_milk");

    public static CauldronInteraction MAKE_FERMENTED_FISH = ((blockState, level, blockPos, player, interactionHand, itemStack) -> {
        if(!level.isClientSide) {
            if(!player.isCreative()){
                itemStack.shrink(1);
            }
            level.setBlockAndUpdate(blockPos,ModBlocks.FERMENTED_FISH_CAULDRON.get().defaultBlockState()
                    .setValue(FermentedFishCauldronBlock.FERMENT,0)
                    .setValue(FermentedFishCauldronBlock.LEVEL,blockState.getValue(LayeredCauldronBlock.LEVEL)));
            level.playSound(null,blockPos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS,1.0f,1.0f);
            level.gameEvent(null, GameEvent.FLUID_PLACE,blockPos);
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    });

    public static CauldronInteraction SET_COCONUT_CAULDRON = (blockState, level, blockPos, player, interactionHand, itemStack) -> {
        if(!player.isCreative()){
            if(itemStack.getItem().hasCraftingRemainingItem()){
                player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack,player,new ItemStack(itemStack.getItem().getCraftingRemainingItem(),1)));
            }else{
                itemStack.shrink(1);
            }
        }

        level.setBlockAndUpdate(blockPos,ModBlocks.COCONUT_CAULDRON.get().defaultBlockState());
        level.playSound(null,blockPos,SoundEvents.COMPOSTER_FILL,SoundSource.BLOCKS,0.5f,1.0f);
        level.gameEvent(null,GameEvent.BLOCK_CHANGE,blockPos);
        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    };

    public static CauldronInteraction INSERT_COCONUT = (blockState, level, blockPos, player, interactionHand, itemStack) -> {
        int cauldronLevel = blockState.getValue(CoconutCauldron.LEVEL);
        if(itemStack.is(ModTags.COCONUT)){
            if(!player.isCreative()){
                if(itemStack.getItem().hasCraftingRemainingItem()){
                    player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack,player,new ItemStack(itemStack.getItem().getCraftingRemainingItem(),1)));
                }else{
                    itemStack.shrink(1);
                }

            }
            level.setBlockAndUpdate(blockPos,
                    blockState.setValue(CoconutCauldron.LEVEL,cauldronLevel+1));
            level.playSound(null,blockPos,SoundEvents.COMPOSTER_FILL,SoundSource.BLOCKS,0.8f,1.0f);
            level.gameEvent(null,GameEvent.BLOCK_CHANGE,blockPos);

            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    };

    public static CauldronInteraction MAKE_COCONUT_MILK = ((blockState, level, blockPos, player, interactionHand, itemStack) ->{
        if(blockState.getValue(CoconutCauldron.LEVEL) == 3){
            return emptyBucket(level,blockPos,player,interactionHand,itemStack,
                    ModBlocks.COCONUT_MILK_CAULDRON.get().defaultBlockState().setValue(CoconutMilkCauldron.LEVEL, 3),
                    SoundEvents.BUCKET_EMPTY);
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    });



    static CauldronInteraction FERMENTED_FISH_BOTTLE = (blockState, level, blockPos, player, interactionHand, itemStack) -> {
        if(!level.isClientSide){
            if(itemStack.is(Items.GLASS_BOTTLE) && blockState.getValue(FermentedFishCauldronBlock.FERMENT) == 2){
                level.setBlockAndUpdate(blockPos, Blocks.CAULDRON.defaultBlockState());

            }
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    };

    public static void init(){
        CauldronInteraction.addDefaultInteractions(FERMENTED_FISH.map());
        CauldronInteraction.addDefaultInteractions(COCONUT.map());
        CauldronInteraction.addDefaultInteractions(COCONUT_MILK.map());

        FERMENTED_FISH.map().put(Items.BOWL,(blockState, level, blockPos, player, interactionHand, itemStack) -> {
            if(blockState.getValue(FermentedFishCauldronBlock.FERMENT) == 2){
                if(!level.isClientSide){
                    Item item = itemStack.getItem();
                    player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack,player,new ItemStack(ModItems.FERMENTED_FISH.get(),1)));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    FermentedFishCauldronBlock.lowerFillLevel(blockState,level,blockPos);
                    level.playSound(null,blockPos,SoundEvents.BOTTLE_FILL,SoundSource.BLOCKS,1.0f,1.0f);
                    level.gameEvent(null,GameEvent.FLUID_PICKUP,blockPos);

                }
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }

            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        });

        COCONUT.map().put(Items.BOWL,(blockState, level, blockPos, player, interactionHand, itemStack) -> {
            if(!level.isClientSide){
                Item item = itemStack.getItem();
                player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack,player,new ItemStack(ModItems.COCONUT_SLICE.get(),1)));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                LayeredCauldronBlock.lowerFillLevel(blockState,level,blockPos);
                level.playSound(null,blockPos,SoundEvents.COMPOSTER_FILL,SoundSource.BLOCKS,1.0f,1.0f);
                level.gameEvent(null,GameEvent.FLUID_PICKUP,blockPos);
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        });
        COCONUT.map().put(Items.WATER_BUCKET,MAKE_COCONUT_MILK);


        COCONUT_MILK.map().put(Items.GLASS_BOTTLE, (blockState, level, blockPos, player, interactionHand, itemStack) -> {
            if(!level.isClientSide){
                Item item = itemStack.getItem();
                player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack,player,new ItemStack(ModItems.COCONUT_MILK_BOTTLE.get(),1)));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                CoconutMilkCauldron.lowerFillLevel(blockState,level,blockPos);
                level.playSound(null,blockPos,SoundEvents.BOTTLE_FILL,SoundSource.BLOCKS,1.0f,1.0f);
                level.gameEvent(null,GameEvent.FLUID_PICKUP,blockPos);
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        });

    }
}
