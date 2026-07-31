package net.firemuffin303.thaidelight.common.block.cauldron;

import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModCauldronInteraction;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.firemuffin303.thaidelight.mixin.cauldron.LayeredCauldronAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;

public class CoconutCauldron extends LayeredCauldronBlock {

    public CoconutCauldron(Properties properties) {
        super(Biome.Precipitation.RAIN,ModCauldronInteraction.COCONUT,properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(itemStack.is(ModTags.COCONUT) && !ModCauldronInteraction.COCONUT.map().containsKey(itemStack.getItem()) && !isFull(blockState)){
            return ModCauldronInteraction.INSERT_COCONUT.interact(blockState, level, blockPos, player, interactionHand, itemStack);
        }

        return super.useItemOn(itemStack, blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {}

    @Override
    public void handlePrecipitation(BlockState blockState, Level level, BlockPos blockPos, Biome.Precipitation precipitation) {
        if(shouldHandlePrecipitation(level, precipitation) && isFull(blockState) && ((LayeredCauldronAccessor)this).getFillPredicate() == precipitation ){
            BlockState coconutMilkState = ModBlocks.COCONUT_MILK_CAULDRON.get().defaultBlockState().setValue(LEVEL,3);
            level.setBlockAndUpdate(blockPos,coconutMilkState);
            level.gameEvent(GameEvent.BLOCK_CHANGE,blockPos,GameEvent.Context.of(coconutMilkState));
        }
    }

    @Override
    protected void receiveStalactiteDrip(BlockState blockState, Level level, BlockPos blockPos, Fluid fluid) {
        if(isFull(blockState)){
            BlockState coconutMilkState = ModBlocks.COCONUT_MILK_CAULDRON.get().defaultBlockState().setValue(LEVEL,3);
            level.setBlockAndUpdate(blockPos,coconutMilkState);
            level.gameEvent(GameEvent.BLOCK_CHANGE,blockPos,GameEvent.Context.of(coconutMilkState));
            level.levelEvent(1047, blockPos, 0);
        }
    }

    protected static boolean shouldHandlePrecipitation(Level level, Biome.Precipitation precipitation) {
        if (precipitation == Biome.Precipitation.RAIN) {
            return level.getRandom().nextFloat() < 0.05F;
        } else {
            return precipitation == Biome.Precipitation.SNOW ? level.getRandom().nextFloat() < 0.1F : false;
        }
    }

    @Override
    public Item asItem() {
        return Items.CAULDRON;
    }
}
