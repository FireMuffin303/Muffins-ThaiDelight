package net.firemuffin303.thaidelight.common.menu;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.List;

public record MortarInput(List<ItemStack> itemStacks,ItemStack container) implements RecipeInput {

    @Override
    public ItemStack getItem(int slot) {
        if (slot >= 0 && slot <= 3) {
            return itemStacks.get(slot);
        }

        return this.container;
    }

    @Override
    public int size() {
        return 5;
    }

    @Override
    public boolean isEmpty() {
        return (this.itemStacks.isEmpty() || this.itemStacks.stream().allMatch(ItemStack::isEmpty)) && this.container.isEmpty();
    }
}
