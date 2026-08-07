package net.firemuffin303.thaidelight.client.sceens;

import com.mojang.logging.LogUtils;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipe;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class MortarRecipeBookComponent extends RecipeBookComponent {
    protected static final WidgetSprites RECIPE_BOOK_LOCATION = new WidgetSprites(
            ThaiDelightCommon.modid("recipe_book/mortar_enabled"),
            ThaiDelightCommon.modid("recipe_book/mortar_disabled"),
            ThaiDelightCommon.modid("recipe_book/mortar_enabled_highlighted"),
            ThaiDelightCommon.modid("recipe_book/mortar_disabled_highlighted")
    );
    @Override
    protected void initFilterButtonTextures() {
        this.filterButton.initTextureValues(RECIPE_BOOK_LOCATION);
    }

    @Override
    public void setupGhostRecipe(RecipeHolder<?> recipe, List<Slot> slots) {
        ItemStack resultStack = recipe.value().getResultItem(this.minecraft.level.registryAccess());
        this.ghostRecipe.setRecipe(recipe);

        if (slots.get(5).getItem().isEmpty()) {
            this.ghostRecipe.addIngredient(Ingredient.of(resultStack), (slots.get(5)).x, (slots.get(5)).y);
        }

        if (recipe.value() instanceof MortarRecipe mortarRecipe) {
            ItemStack containerStack = mortarRecipe.getContainer();
            if (!containerStack.isEmpty()) {
                this.ghostRecipe.addIngredient(Ingredient.of(containerStack), (slots.get(4)).x, (slots.get(4)).y);
            }

        }

        this.placeRecipe(this.menu.getGridWidth(), this.menu.getGridHeight(), this.menu.getResultSlotIndex(), recipe, recipe.value().getIngredients().iterator(), 0);
    }
}
