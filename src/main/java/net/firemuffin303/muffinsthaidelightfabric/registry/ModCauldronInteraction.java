package net.firemuffin303.muffinsthaidelightfabric.registry;

import com.mojang.logging.LogUtils;
import net.fabricmc.loader.impl.discovery.ModLoadCondition;
import net.firemuffin303.muffinsthaidelightfabric.common.block.FermentedFishCauldronBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.cauldron.CoconutCauldron;
import net.firemuffin303.muffinsthaidelightfabric.common.block.cauldron.CoconutMilkCauldron;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static net.minecraft.core.cauldron.CauldronInteraction.*;

public class ModCauldronInteraction {
    static Map<Item, CauldronInteraction> FERMENTED_FISH = CauldronInteraction.newInteractionMap();
    public static Map<Item, CauldronInteraction> COCONUT = CauldronInteraction.newInteractionMap();
    public static Map<Item, CauldronInteraction> COCONUT_MILK = CauldronInteraction.newInteractionMap();

    public static CauldronInteraction MAKE_FERMENTED_FISH = ((blockState, level, blockPos, player, interactionHand, itemStack) -> {
        if(!level.isClientSide) {
            if(!player.isCreative()){
                itemStack.shrink(1);
            }
            level.setBlockAndUpdate(blockPos,ModBlocks.FERMENTED_FISH_CAULDRON.defaultBlockState()
                    .setValue(FermentedFishCauldronBlock.FERMENT,0)
                    .setValue(FermentedFishCauldronBlock.LEVEL,blockState.getValue(LayeredCauldronBlock.LEVEL)));
            level.playSound(null,blockPos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS,1.0f,1.0f);
            level.gameEvent(null, GameEvent.FLUID_PLACE,blockPos);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    });

    public static CauldronInteraction SET_COCONUT_CAULDRON = (blockState, level, blockPos, player, interactionHand, itemStack) -> {
        if(!player.isCreative()){
            if(itemStack.getItem().hasCraftingRemainingItem()){
                player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack,player,new ItemStack(itemStack.getItem().getCraftingRemainingItem(),1)));
            }else{
                itemStack.shrink(1);
            }
        }

        level.setBlockAndUpdate(blockPos,ModBlocks.COCONUT_CAULDRON.defaultBlockState());
        level.playSound(null,blockPos,SoundEvents.COMPOSTER_FILL,SoundSource.BLOCKS,0.5f,1.0f);
        level.gameEvent(null,GameEvent.BLOCK_CHANGE,blockPos);
        return InteractionResult.sidedSuccess(level.isClientSide);
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

            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    };

    public static CauldronInteraction MAKE_COCONUT_MILK = ((blockState, level, blockPos, player, interactionHand, itemStack) ->{
        if(blockState.getValue(CoconutCauldron.LEVEL) == 3){
            return emptyBucket(level,blockPos,player,interactionHand,itemStack,
                    ModBlocks.COCONUT_MILK_CAULDRON.defaultBlockState().setValue(CoconutMilkCauldron.LEVEL, 3),
                    SoundEvents.BUCKET_EMPTY);
        }
        return InteractionResult.PASS;
    });



    static CauldronInteraction FERMENTED_FISH_BOTTLE = (blockState, level, blockPos, player, interactionHand, itemStack) -> {
        if(!level.isClientSide){
            if(itemStack.is(Items.GLASS_BOTTLE) && blockState.getValue(FermentedFishCauldronBlock.FERMENT) == 2){
                level.setBlockAndUpdate(blockPos, Blocks.CAULDRON.defaultBlockState());

            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    };

    public static void init(){
        CauldronInteraction.addDefaultInteractions(FERMENTED_FISH);
        CauldronInteraction.addDefaultInteractions(COCONUT);
        CauldronInteraction.addDefaultInteractions(COCONUT_MILK);

        FERMENTED_FISH.put(Items.BOWL,(blockState, level, blockPos, player, interactionHand, itemStack) -> {
            if(blockState.getValue(FermentedFishCauldronBlock.FERMENT) == 2){
                if(!level.isClientSide){
                    Item item = itemStack.getItem();
                    player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack,player,new ItemStack(ModItems.FERMENTED_FISH,1)));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    FermentedFishCauldronBlock.lowerFillLevel(blockState,level,blockPos);
                    level.playSound(null,blockPos,SoundEvents.BOTTLE_FILL,SoundSource.BLOCKS,1.0f,1.0f);
                    level.gameEvent(null,GameEvent.FLUID_PICKUP,blockPos);

                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            }

            return InteractionResult.PASS;
        });

        COCONUT.put(Items.BOWL,(blockState, level, blockPos, player, interactionHand, itemStack) -> {
            if(!level.isClientSide){
                Item item = itemStack.getItem();
                player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack,player,new ItemStack(ModItems.COCONUT_SLICE,1)));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                LayeredCauldronBlock.lowerFillLevel(blockState,level,blockPos);
                level.playSound(null,blockPos,SoundEvents.COMPOSTER_FILL,SoundSource.BLOCKS,1.0f,1.0f);
                level.gameEvent(null,GameEvent.FLUID_PICKUP,blockPos);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        });
        COCONUT.put(Items.WATER_BUCKET,MAKE_COCONUT_MILK);


        COCONUT_MILK.put(Items.GLASS_BOTTLE, (blockState, level, blockPos, player, interactionHand, itemStack) -> {
            if(!level.isClientSide){
                Item item = itemStack.getItem();
                player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack,player,new ItemStack(ModItems.COCONUT_MILK_BOTTLE,1)));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                CoconutMilkCauldron.lowerFillLevel(blockState,level,blockPos);
                level.playSound(null,blockPos,SoundEvents.BOTTLE_FILL,SoundSource.BLOCKS,1.0f,1.0f);
                level.gameEvent(null,GameEvent.FLUID_PICKUP,blockPos);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        });

    }
}
