package net.firemuffin303.muffinsthaidelightfabric.registry;

import net.fabricmc.loader.impl.discovery.ModLoadCondition;
import net.firemuffin303.muffinsthaidelightfabric.common.block.FermentedFishCauldronBlock;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
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
    static Map<Item, CauldronInteraction> FERMENTED_FISH = CauldronInteraction.newInteractionMap();

    static CauldronInteraction MAKE_FERMENTED_FISH = ((blockState, level, blockPos, player, interactionHand, itemStack) -> {
        if(!level.isClientSide) {
            if (itemStack.is(ItemTags.FISHES)) {
                if(!player.isCreative()){
                    itemStack.shrink(1);
                }
                level.setBlockAndUpdate(blockPos,ModBlocks.FERMENTED_FISH_CAULDRON.defaultBlockState()
                        .setValue(FermentedFishCauldronBlock.FERMENT,0)
                        .setValue(FermentedFishCauldronBlock.LEVEL,blockState.getValue(LayeredCauldronBlock.LEVEL)));
                level.playSound(null,blockPos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS,1.0f,1.0f);
                level.gameEvent(null, GameEvent.FLUID_PLACE,blockPos);
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
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
        WATER.put(Items.COD,MAKE_FERMENTED_FISH);
        WATER.put(Items.SALMON,MAKE_FERMENTED_FISH);
        WATER.put(Items.TROPICAL_FISH,MAKE_FERMENTED_FISH);
        WATER.put(Items.PUFFERFISH,MAKE_FERMENTED_FISH);
        //WATER.put(vectorwing.farmersdelight.common.registry.ModItems.COD_SLICE.get(), MAKE_FERMENTED_FISH);
        //WATER.put(vectorwing.farmersdelight.common.registry.ModItems.SALMON_SLICE.get(), MAKE_FERMENTED_FISH);

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
    }
}
