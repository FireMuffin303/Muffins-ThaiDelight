package net.firemuffin303.thaidelight.common.block.saucebowl;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public interface SauceBowlInteraction {
    Map<Item, SauceBowlInteraction> EMPTY = newInteractionMap();
    Map<Item, SauceBowlInteraction> SEAFOOD = newInteractionMap();
    Map<Item, SauceBowlInteraction> FISH_SAUCE = newInteractionMap();
    Map<Item, SauceBowlInteraction> HONEY = newInteractionMap();

/*
    SauceBowlInteraction FILL_SEAFOOD = ((blockState, level, blockPos, player, interactionHand, itemStack) -> {
        return emptyBottle(level,blockPos,player,interactionHand,itemStack, ModBlocks.SEAFOOD_SAUCE_BOWL.defaultBlockState().setValue(LevelSauceBowl.LEVEL,4), SoundEvents.BOTTLE_EMPTY);
    });

    SauceBowlInteraction FILL_FISH_SAUCE = ((blockState, level, blockPos, player, interactionHand, itemStack) -> {
       return emptyBottle(level,blockPos,player,interactionHand,itemStack,ModBlocks.FISH_SAUCE_SAUCE_BOWL.defaultBlockState().setValue(LevelSauceBowl.LEVEL,4),SoundEvents.BOTTLE_EMPTY);
    });

    SauceBowlInteraction FILL_HONEY = ((blockState, level, blockPos, player, interactionHand, itemStack) -> {
        return emptyBottle(level,blockPos,player,interactionHand,itemStack,ModBlocks.HONEY_SAUCE_BOWL.defaultBlockState().setValue(LevelSauceBowl.LEVEL,4),SoundEvents.BOTTLE_EMPTY);
    });
*/

    static InteractionResult emptyBottle(Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, ItemStack itemStack, BlockState blockState, SoundEvent soundEvent) {
        if (!level.isClientSide) {
            Item item = itemStack.getItem();
            player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack, player, new ItemStack(Items.GLASS_BOTTLE)));
            player.awardStat(Stats.FILL_CAULDRON);
            player.awardStat(Stats.ITEM_USED.get(item));
            level.setBlockAndUpdate(blockPos, blockState);
            level.playSound((Player)null, blockPos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent((Entity)null, GameEvent.FLUID_PLACE, blockPos);
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }
/*
    static InteractionResult fillGlass(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, ItemStack itemStack, ItemStack itemStack2, Predicate<BlockState> predicate, SoundEvent soundEvent) {
        if (!predicate.test(blockState)) {
            return InteractionResult.PASS;
        } else {
            if (!level.isClientSide) {
                Item item = itemStack.getItem();
                player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack, player, itemStack2));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                level.setBlockAndUpdate(blockPos, ModBlocks.SAUCE_BOWL.defaultBlockState());
                level.playSound((Player)null, blockPos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, blockPos);
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }
*/
    static Object2ObjectOpenHashMap<Item, SauceBowlInteraction> newInteractionMap() {
        return Util.make(new Object2ObjectOpenHashMap<Item,SauceBowlInteraction>(), (object2ObjectOpenHashMap) -> {
            object2ObjectOpenHashMap.defaultReturnValue((blockState, level, blockPos, player, interactionHand, itemStack) -> {
                return InteractionResult.PASS;
            });
        });
    }

    InteractionResult interact(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, ItemStack itemStack);



    static void init(){
        /*
        EMPTY.put(ModItems.SEAFOOD_BOTTLE,FILL_SEAFOOD);
        EMPTY.put(ModItems.FISH_SAUCE_BOTTLE,FILL_FISH_SAUCE);
        EMPTY.put(Items.HONEY_BOTTLE,FILL_HONEY);

        SEAFOOD.put(Items.GLASS_BOTTLE,((blockState, level, blockPos, player, interactionHand, itemStack) -> {
            return fillGlass(blockState,level,blockPos,player,interactionHand,itemStack,new ItemStack(ModItems.SEAFOOD_BOTTLE),(blockStatex -> {
                return blockStatex.getValue(LevelSauceBowl.LEVEL) == 4;
            }),SoundEvents.BOTTLE_FILL);
        }));

        FISH_SAUCE.put(Items.GLASS_BOTTLE,((blockState, level, blockPos, player, interactionHand, itemStack) -> {
            return fillGlass(blockState,level,blockPos,player,interactionHand,itemStack,new ItemStack(ModItems.FISH_SAUCE_BOTTLE),(blockStatex -> {
                return blockStatex.getValue(LevelSauceBowl.LEVEL) == 4;
            }),SoundEvents.BOTTLE_FILL);
        }));

        HONEY.put(Items.GLASS_BOTTLE,((blockState, level, blockPos, player, interactionHand, itemStack) -> {
            return fillGlass(blockState,level,blockPos,player,interactionHand,itemStack,new ItemStack(Items.HONEY_BOTTLE),(blockStatex -> {
                return blockStatex.getValue(LevelSauceBowl.LEVEL) == 4;
            }),SoundEvents.BOTTLE_FILL);
        }));
    */
    }
}
