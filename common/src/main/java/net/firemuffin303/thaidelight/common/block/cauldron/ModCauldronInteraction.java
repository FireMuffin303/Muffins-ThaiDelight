package net.firemuffin303.thaidelight.common.block.cauldron;

import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Map;

import static net.minecraft.core.cauldron.CauldronInteraction.*;

public class ModCauldronInteraction {
    static Map<Item, CauldronInteraction> SEAFOOD = newInteractionMap();
    static Map<Item, CauldronInteraction> FISH_SAUCE = newInteractionMap();


    static CauldronInteraction FILL_SEAFOOD = (blockState, level, blockPos, player, interactionHand, itemStack) -> {
        return emptyBucket(level, blockPos, player, interactionHand, itemStack, ModBlocks.SEAFOOD_CAULDRON.get().defaultBlockState().setValue(LayeredCauldronBlock.LEVEL,3), SoundEvents.BUCKET_EMPTY);
    };

    static CauldronInteraction CRAB_MEAT = (blockState, level, blockPos, player, interactionHand, itemStack) -> {
        if(itemStack.is(ModItems.COOKED_CRAB_MEAT.get()) && !level.isClientSide()){
            if(!player.getAbilities().instabuild){
                itemStack.shrink(1);
                player.setItemInHand(interactionHand,ItemUtils.createFilledResult(itemStack,player,new ItemStack(ModItems.CRAB_MEAT_WITH_SEAFOOD.get()),level.isClientSide));
                player.awardStat(Stats.USE_CAULDRON);
                LayeredCauldronBlock.lowerFillLevel(blockState,level,blockPos);
                level.playSound((Player) null,blockPos,SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS,1.0f,1.0f);
                level.gameEvent((Entity) null, GameEvent.FLUID_PICKUP,blockPos);
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    };


    public static void init(){
        CauldronInteraction.addDefaultInteractions(SEAFOOD);
        EMPTY.put(ModItems.SEAFOOD_BUCKET.get(),FILL_SEAFOOD);
        WATER.put(ModItems.SEAFOOD_BUCKET.get(),FILL_SEAFOOD);
        LAVA.put(ModItems.SEAFOOD_BUCKET.get(),FILL_SEAFOOD);
        POWDER_SNOW.put(ModItems.SEAFOOD_BUCKET.get(),FILL_SEAFOOD);

        SEAFOOD.put(ModItems.COOKED_CRAB_MEAT.get(),CRAB_MEAT);
        SEAFOOD.put(Items.BUCKET,(blockState, level, blockPos, player, interactionHand, itemStack) -> {
            return fillBucket(blockState,level,blockPos,player,interactionHand,itemStack,new ItemStack(ModItems.SEAFOOD_BUCKET.get()),(blockState1 -> {
              return (Integer)blockState1.getValue(LayeredCauldronBlock.LEVEL) == 3;
            }),SoundEvents.BUCKET_FILL);
        });

        FISH_SAUCE.put(Items.GLASS_BOTTLE,(blockState, level, blockPos, player, interactionHand, itemStack) -> {
            if(!level.isClientSide){
                Item item = itemStack.getItem();
                player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack, player, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER)));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                LayeredCauldronBlock.lowerFillLevel(blockState, level, blockPos);
                level.playSound((Player)null, blockPos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, blockPos);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        });
    }
}
