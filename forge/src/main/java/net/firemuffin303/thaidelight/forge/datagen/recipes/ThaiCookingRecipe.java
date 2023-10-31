package net.firemuffin303.thaidelight.forge.datagen.recipes;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.recipe.CookingRecipes;

import java.util.function.Consumer;

public class ThaiCookingRecipe {
    public static void register(Consumer<FinishedRecipe> consumer){
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FISH_SAUCE_BOTTLE,1,CookingRecipes.FAST_COOKING,0.35f,
                Items.GLASS_BOTTLE).addIngredient(ForgeTags.RAW_FISHES).setRecipeBookTab(CookingPotRecipeBookTab.DRINKS).build(consumer);

        CookingPotRecipeBuilder.cookingPotRecipe(Items.DIAMOND,1,CookingRecipes.FAST_COOKING,1.0f)
                .addIngredient(Items.DIRT)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .unlockedByAnyIngredient(Items.DIRT).build(consumer);
    }
}
