package net.firemuffin303.muffinsthaidelightfabric.common.item;

import com.chocohead.mm.api.ClassTinkerers;
import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.component.SackTooltipComponent;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
        CompoundTag compoundTag = itemStack.getTag();
        if(compoundTag == null){
            return Optional.empty();
        }

        if(!compoundTag.contains("BlockEntityTag")){
            return Optional.empty();
        }

        CompoundTag blockEntityTag = compoundTag.getCompound("BlockEntityTag");
        if(!blockEntityTag.contains("Items")){
            return Optional.empty();
        }
        List<ItemStack> itemStackStream = blockEntityTag.getList("Items",10).stream().map(CompoundTag.class::cast).map(ItemStack::of).toList();
        if(itemStackStream.isEmpty()){
            return Optional.empty();
        }

        int amount = 0;
        NonNullList<ItemStack> nonNullList = NonNullList.create();
        for(ItemStack itemStack1 : itemStackStream){
            nonNullList.add(itemStack1);
            amount += itemStack1.getCount();
        }


        return Optional.of(new SackTooltipComponent.SackToolTip(nonNullList.get(0).getItem(),amount));
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
        CompoundTag compoundTag = itemStack.getTag();
        if(compoundTag == null || !compoundTag.contains("BlockEntityTag")){
            return false;
        }

        CompoundTag blockEntityTag = compoundTag.getCompound("BlockEntityTag");
        if(!blockEntityTag.contains("Items")){
            return false;
        }

        List<ItemStack> itemStackStream = blockEntityTag.getList("Items",10).stream().map(CompoundTag.class::cast).map(ItemStack::of).toList();
        if(itemStackStream.isEmpty()){
            return false;
        }
        int amount = 0;
        int maxAmount = 0;
        for(ItemStack itemStack1 : itemStackStream){
            amount += itemStack1.getCount();
            maxAmount = itemStack1.getMaxStackSize();
        }

        return amount >= maxAmount*5;
    }
}
