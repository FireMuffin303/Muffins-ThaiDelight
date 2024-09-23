package net.firemuffin303.thaidelight.common.item.bottle;

import net.firemuffin303.thaidelight.common.entity.Dragonfly;
import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DragonflyBottleItem extends MobBottleItem{
    public DragonflyBottleItem(Properties properties) {
        super(ModEntityTypes.DRAGONFLY.get(), SoundEvents.BOTTLE_EMPTY, properties);
    }

    public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        CustomData customData = itemStack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA,CustomData.EMPTY);
        if(customData.isEmpty()){return;}
        CompoundTag compoundTag = customData.copyTag();
        if(compoundTag.contains("Variant")){
            int variant = compoundTag.getInt("Variant");
            ChatFormatting[] chatFormattings = new ChatFormatting[]{ChatFormatting.ITALIC,ChatFormatting.GRAY};
            String string = "dragonfly.variant.muffins_thaidelight." + Dragonfly.DragonflyVariant.byId(variant).getName();
            list.add(Component.translatable(string).withStyle(chatFormattings));
        }
    }

    public static void setVariant(ItemStack itemStack, Dragonfly.DragonflyVariant variant){
        CustomData customData = itemStack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA,CustomData.EMPTY);
        customData.update(compoundTag1 -> {
            compoundTag1.putInt("Variant",variant.getId());
        });

        itemStack.set(DataComponents.BUCKET_ENTITY_DATA,customData);
    }
}
