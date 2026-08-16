package net.firemuffin303.thaidelight.common.recipe.mortar;

import net.firemuffin303.thaidelight.common.menu.MortarInput;
import net.firemuffin303.thaidelight.common.registry.ModRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

public interface MortarRecipe extends Recipe<MortarInput> {
    @Override
    default RecipeType<?> getType() {
        return ModRecipes.MORTAR.get();
    }

    ItemStack getResult();

    ItemStack getContainer();

    MortarRecipeBookTab getRecipeBookTab();
}
