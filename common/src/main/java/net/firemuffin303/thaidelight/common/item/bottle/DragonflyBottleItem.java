package net.firemuffin303.thaidelight.common.item.bottle;

import net.firemuffin303.thaidelight.common.entity.Dragonfly;
import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DragonflyBottleItem extends MobBottleItem{
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
            String string = "dragonfly.variant.muffins_thaidelight." + Dragonfly.DragonflyVariant.byId(varaint).getName();
            list.add(Component.translatable(string).withStyle(chatFormattings));
        }
    }
}
