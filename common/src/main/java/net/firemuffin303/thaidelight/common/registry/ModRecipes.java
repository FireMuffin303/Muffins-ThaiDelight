package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.common.recipe.MortarRecipe;
import net.minecraft.client.gui.components.toasts.RecipeToast;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipes {
    public static RecipeType<MortarRecipe> MORTAR;

    private static RecipeType<?> register(Recipe<?> recipe){
        return recipe.getType();
    }
}
