package net.firemuffin303.muffinsthaidelightfabric.common.item;

import net.firemuffin303.muffinsthaidelightfabric.common.entity.DragonflyEntity;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModEntityTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DragonflyBottleItem extends MobBottleItem {
    public DragonflyBottleItem(Properties properties) {
        super(ModEntityTypes.DRAGONFLY, SoundEvents.BOTTLE_EMPTY, properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, list, tooltipFlag);
        CompoundTag compoundTag = itemStack.getTag();
        if(compoundTag != null && compoundTag.contains("Variant")){
            int varaint = compoundTag.getInt("Variant");
            ChatFormatting[] chatFormattings = new ChatFormatting[]{ChatFormatting.ITALIC,ChatFormatting.GRAY};
            String string = "dragonfly.variant.muffins_thaidelight." + DragonflyEntity.DragonflyVariant.byId(varaint).getName();
            list.add(Component.translatable(string).withStyle(chatFormattings));
        }
    }

    public static void setVariant(ItemStack itemStack, DragonflyEntity.DragonflyVariant variant){
        itemStack.getOrCreateTag().putInt("Variant",variant.getId());
    }
}
