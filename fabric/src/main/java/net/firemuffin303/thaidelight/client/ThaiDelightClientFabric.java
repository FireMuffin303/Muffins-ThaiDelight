package net.firemuffin303.thaidelight.client;

import io.github.fabricators_of_create.porting_lib.recipe_book_categories.RecipeBookRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.firemuffin303.thaidelight.common.recipe.MortarRecipe;
import net.firemuffin303.thaidelight.common.recipe.MortarRecipeBookTab;
import net.firemuffin303.thaidelight.common.registry.ModRecipes;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.inventory.RecipeBookType;

import java.util.List;

public class ThaiDelightClientFabric implements ClientModInitializer {
    public static final RecipeBookType MORTAR_RECIPE_BOOK_TYPE = RecipeBookType.valueOf("MORTAR_RECIPE_BOOK_TYPE");
    public static final RecipeBookCategories MORTAR_SEARCH = RecipeBookCategories.valueOf("MORTAR_SEARCH");
    public static final RecipeBookCategories MORTAR_MEALS = RecipeBookCategories.valueOf("MORTAR_MEALS");
    public static final RecipeBookCategories MORTAR_MISC = RecipeBookCategories.valueOf("MORTAR_MISC");


    @Override
    public void onInitializeClient() {
        RecipeBookRegistry.registerBookCategories(ThaiDelightClientFabric.MORTAR_RECIPE_BOOK_TYPE,
                List.of(ThaiDelightClientFabric.MORTAR_SEARCH,ThaiDelightClientFabric.MORTAR_MEALS,ThaiDelightClientFabric.MORTAR_MISC));
        RecipeBookRegistry.registerAggregateCategory(ThaiDelightClientFabric.MORTAR_SEARCH,List.of(ThaiDelightClientFabric.MORTAR_MEALS,ThaiDelightClientFabric.MORTAR_MISC));
        RecipeBookRegistry.registerRecipeCategoryFinder(ModRecipes.MORTAR,recipe -> {
            if(recipe instanceof MortarRecipe mortarRecipe){
                MortarRecipeBookTab mortarRecipeBookTab = mortarRecipe.getRecipeBookTab();
                if(mortarRecipeBookTab != null){
                    return switch (mortarRecipeBookTab){
                        case MEALS -> MORTAR_MEALS;
                        case MISC -> MORTAR_MISC;
                    };
                }
            }

            return ThaiDelightClientFabric.MORTAR_MISC;
        });
    }
}
