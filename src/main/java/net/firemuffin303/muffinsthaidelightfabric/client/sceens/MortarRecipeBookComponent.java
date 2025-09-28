package net.firemuffin303.muffinsthaidelightfabric.client.sceens;

import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.recipe.mortar.MortarRecipe;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;

import java.util.List;

public class MortarRecipeBookComponent extends RecipeBookComponent {
    protected static final ResourceLocation RECIPE_BOOK_LOCATION = ThaiDelight.modid("textures/gui/mortar.png");
    @Override
    protected void initFilterButtonTextures() {
        this.filterButton.initTextureValues(22, 168, 28, 18, RECIPE_BOOK_LOCATION);
    }

    @Override
    public void setupGhostRecipe(Recipe<?> recipe, List<Slot> slots) {
        ItemStack resultStack = recipe.getResultItem(this.minecraft.level.registryAccess());
        this.ghostRecipe.setRecipe(recipe);

        if (slots.get(0).getItem().isEmpty()) {
            this.ghostRecipe.addIngredient(Ingredient.of(resultStack), (slots.get(0)).x, (slots.get(0)).y);
        }

        if (recipe instanceof MortarRecipe mortarRecipe) {
            ItemStack containerStack = mortarRecipe.getContainer();
            if (!containerStack.isEmpty()) {
                this.ghostRecipe.addIngredient(Ingredient.of(containerStack), (slots.get(5)).x, (slots.get(5)).y);
            }

        }

        this.placeRecipe(this.menu.getGridWidth(), this.menu.getGridHeight(), this.menu.getResultSlotIndex(), recipe, recipe.getIngredients().iterator(), 0);
    }
}
