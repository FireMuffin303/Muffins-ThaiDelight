package net.firemuffin303.thaidelight.common.recipe;

import net.firemuffin303.thaidelight.common.registry.ModRecipes;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public interface MortarRecipe extends Recipe<Container> {
    @Override
    default RecipeType<?> getType(){
        return ModRecipes.MORTAR;
    }

    ItemStack getResult();

    ItemStack getContainer();

    MortarRecipeBookTab getRecipeBookTab();
}
