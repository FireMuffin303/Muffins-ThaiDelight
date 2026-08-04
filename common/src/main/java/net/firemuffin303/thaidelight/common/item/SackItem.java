package net.firemuffin303.thaidelight.common.item;

import net.firemuffin303.thaidelight.client.renderer.component.SackTooltipComponent;
import net.firemuffin303.thaidelight.common.advancement.SackCatchTrigger;
import net.firemuffin303.thaidelight.common.registry.ModBlockEntityTypes;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModCriteriaTriggers;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class SackItem extends BlockItem {
    public SackItem(Properties properties) {
        super(ModBlocks.SACK.get(),properties);
    }


    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        return 1200;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return PlatformUtil.getDurianCatcherUseAnim();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if(isFull(itemStack)){
            return InteractionResultHolder.pass(itemStack);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        return ItemUtils.startUsingInstantly(level, player, interactionHand);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        return itemStack;
    }

    /*
    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack itemStack) {
        NonNullList<ItemStack> nonNullList = NonNullList.withSize(5,ItemStack.EMPTY);

        for(ItemStack itemStack1 : itemStack.get(DataComponents.CONTAINER).nonEmptyItems()){
            nonNullList.add(itemStack1);
        }

        return !itemStack.has(DataComponents.HIDE_TOOLTIP) && !itemStack.has(DataComponents.HIDE_ADDITIONAL_TOOLTIP) ? Optional.of(new SackTooltipComponent.SackToolTip(nonNullList)) : Optional.empty();
    }

     */

    @Override
    public boolean canFitInsideContainerItems() {
        return false;
    }

    @Override
    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int i) {
        super.releaseUsing(itemStack, level, livingEntity, i);
    }

    public static boolean isFull(ItemStack itemStack){
        if (!itemStack.is(ModItems.SACK.get())){
            return false;
        }

        if(!itemStack.has(DataComponents.CONTAINER)){
            return false;
        }

        List<ItemStack> itemStackList = itemStack.get(DataComponents.CONTAINER).nonEmptyStream().toList();
        if(itemStackList.isEmpty()){
            return false;
        }

        return itemStackList.size() >= 5 && itemStackList.stream().allMatch(itemStack1 -> {
                    return itemStack1.getCount() >= itemStack1.getMaxStackSize() && !itemStack1.isEmpty();
                }
        );
    }

    //TODO: fix component
    @SuppressWarnings("unchecked")
    public static boolean onCatchingFallingBlock(ItemStack sackItem, Item item, ServerPlayer serverPlayer){
        /*
        CompoundTag compoundTag = BlockItem.getBlockEntityData(sackItem);
        ItemStack newStack = new ItemStack(item);
        if(compoundTag == null){
            compoundTag = new CompoundTag();
        }

        NonNullList<ItemStack> itemStacks = NonNullList.withSize(5, ItemStack.EMPTY);

        if(compoundTag.contains("Items")){
            ContainerHelper.loadAllItems(compoundTag,itemStacks);
        }

        if(!itemStacks.stream().allMatch(ItemStack::isEmpty)){
            boolean bl = itemStacks.stream().anyMatch(itemStack -> ItemStack.isSameItemSameComponents(itemStack,newStack));
            if(!bl){
                return false;
            }

            for(int i = 0;i < 5;i++){
                ItemStack itemStack = itemStacks.get(i);
                if(itemStacks.size() < i || itemStack.isEmpty()){
                    itemStacks.set(i,newStack);
                    break;
                }


                if(itemStack.getCount() < itemStack.getMaxStackSize()){
                    itemStack.grow(1);

                    break;
                }

            }

            BlockItem.setBlockEntityData(sackItem, ModBlockEntityTypes.SACK_BLOCK_ENTITY.get(),ContainerHelper.saveAllItems(compoundTag,itemStacks));
            ModCriteriaTriggers.SACK_CATCH.get().trigger(serverPlayer,newStack);
            return true;
        }

        itemStacks.set(0,newStack);
        BlockItem.setBlockEntityData(sackItem,ModBlockEntityTypes.SACK_BLOCK_ENTITY.get(),ContainerHelper.saveAllItems(compoundTag,itemStacks));
        ModCriteriaTriggers.SACK_CATCH.get().trigger(serverPlayer,newStack);
        return true;
        */

        return false;
    }

}
