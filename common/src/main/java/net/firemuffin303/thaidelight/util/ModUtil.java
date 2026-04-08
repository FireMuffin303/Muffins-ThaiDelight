package net.firemuffin303.thaidelight.util;

import net.minecraft.world.item.ItemStack;

public class ModUtil {
    public static ItemStack getCraftRemainder(ItemStack itemStack){
        return itemStack.getItem().hasCraftingRemainingItem() ? itemStack.getItem().getCraftingRemainingItem().getDefaultInstance() : ItemStack.EMPTY;
    }
}
