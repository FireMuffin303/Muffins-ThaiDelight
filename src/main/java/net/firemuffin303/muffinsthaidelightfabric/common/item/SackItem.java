package net.firemuffin303.muffinsthaidelightfabric.common.item;

import com.chocohead.mm.api.ClassTinkerers;
import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.component.SackTooltipComponent;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlockEntityTypes;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModCriteriaTriggers;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.List;
import java.util.Optional;

public class SackItem extends BlockItem {
    public SackItem(Properties properties) {
        super(ModBlocks.SACK,properties);
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return 1200;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return ClassTinkerers.getEnum(UseAnim.class,"CATCHING_BAG");
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

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack itemStack) {
        NonNullList<ItemStack> nonNullList = NonNullList.withSize(5,ItemStack.EMPTY);


        CompoundTag compoundTag = BlockItem.getBlockEntityData(itemStack);
        if(compoundTag != null){
            ContainerHelper.loadAllItems(compoundTag,nonNullList);
        }

        return Optional.of(new SackTooltipComponent.SackToolTip(nonNullList));
    }

    @Override
    public boolean canFitInsideContainerItems() {
        return false;
    }

    @Override
    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int i) {
        super.releaseUsing(itemStack, level, livingEntity, i);
    }

    public static boolean isFull(ItemStack itemStack){
        if (!itemStack.is(ModItems.SACK)){
            return false;
        }

        CompoundTag compoundTag = BlockItem.getBlockEntityData(itemStack);
        if(compoundTag == null){
            return false;
        }

        ListTag listTag = compoundTag.getList("Items",10);
        if(listTag.isEmpty()){
            return false;
        }

        return listTag.size() >= 5 && listTag.stream().map(CompoundTag.class::cast).map(ItemStack::of).allMatch(itemStack1 -> {
            return itemStack1.getCount() >= itemStack1.getMaxStackSize() && !itemStack1.isEmpty();
                }
            );
    }

    public static boolean onCatchingFallingBlock(ItemStack sackItem, Item item, ServerPlayer serverPlayer){
        CompoundTag compoundTag = BlockItem.getBlockEntityData(sackItem);
        ItemStack newStack = new ItemStack(item);
        if(compoundTag == null){
            compoundTag = new CompoundTag();
        }

        NonNullList<ItemStack> itemStacks = NonNullList.withSize(5,ItemStack.EMPTY);

        if(compoundTag.contains("Items")){
            ContainerHelper.loadAllItems(compoundTag,itemStacks);
        }

        if(!itemStacks.stream().allMatch(ItemStack::isEmpty)){
            boolean bl = itemStacks.stream().anyMatch(itemStack -> ItemStack.isSameItemSameTags(itemStack,newStack));
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

            BlockItem.setBlockEntityData(sackItem,ModBlockEntityTypes.SACK_BLOCK_ENTITY,ContainerHelper.saveAllItems(compoundTag,itemStacks));
            ModCriteriaTriggers.SACK_CATCH.trigger(serverPlayer,newStack);
            return true;
        }

        itemStacks.set(0,newStack);
        BlockItem.setBlockEntityData(sackItem,ModBlockEntityTypes.SACK_BLOCK_ENTITY,ContainerHelper.saveAllItems(compoundTag,itemStacks));
        ModCriteriaTriggers.SACK_CATCH.trigger(serverPlayer,newStack);
        return true;
    }
}
