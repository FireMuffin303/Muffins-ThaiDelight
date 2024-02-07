package net.firemuffin303.thaidelight.forge.datagen.recipes;

import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.forge.common.registry.ModItemsForge;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;
import vectorwing.farmersdelight.data.recipe.CookingRecipes;

import java.util.function.Consumer;

public class ThaiCookingRecipe {
    public static void register(Consumer<FinishedRecipe> consumer){
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FISH_SAUCE_BOTTLE,1,CookingRecipes.FAST_COOKING,0.35f, Items.GLASS_BOTTLE)
                .addIngredient(ForgeTags.RAW_FISHES)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .unlockedByAnyIngredient(Items.GLASS_BOTTLE)
                .build(consumer,ForgeRegistries.ITEMS.getKey(ModItems.FISH_SAUCE_BOTTLE));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItemsForge.CRAB_FRIED_RICE.get(),1,CookingRecipes.NORMAL_COOKING,0.35f, Items.BOWL)
                .addIngredient(ModItems.CRAB_MEAT)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .addIngredient(ForgeTags.EGGS)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(ModItems.CRAB_MEAT)
                .build(consumer,ForgeRegistries.ITEMS.getKey(ModItemsForge.CRAB_FRIED_RICE.get()));

        CookingPotRecipeBuilder.cookingPotRecipe(ModBlocks.SPICY_MINCED_MEAT_SALAD_FEAST,1,CookingRecipes.NORMAL_COOKING,0.35f, Items.BOWL)
                .addIngredient(Items.COOKED_PORKCHOP)
                .addIngredient(ModItems.PEPPER)
                .addIngredient(Items.SUGAR)
                .addIngredient(ModItems.FISH_SAUCE_BOTTLE)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(Items.COOKED_PORKCHOP)
                .build(consumer,ForgeRegistries.ITEMS.getKey(Item.byBlock(ModBlocks.SPICY_MINCED_MEAT_SALAD_FEAST)));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.LIME), Ingredient.of(ForgeTags.TOOLS_KNIVES),ModItems.SLICED_LIME,2).build(consumer,ForgeRegistries.ITEMS.getKey(ModItems.SLICED_LIME));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.RAW_PAPAYA), Ingredient.of(ForgeTags.TOOLS_KNIVES),ModItems.RAW_PAPAYA_SLICE,4).build(consumer,ForgeRegistries.ITEMS.getKey(ModItems.RAW_PAPAYA_SLICE));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.PAPAYA), Ingredient.of(ForgeTags.TOOLS_KNIVES),ModItems.SLICED_PAPAYA,2).build(consumer,ForgeRegistries.ITEMS.getKey(ModItems.SLICED_PAPAYA));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.PAPAYA_JUICE,1,CookingRecipes.NORMAL_COOKING,1.0f)
                .addIngredient(ModItems.SLICED_PAPAYA)
                .addIngredient(ModItems.SLICED_PAPAYA)
                .addIngredient(Items.SUGAR)
                .unlockedByItems("has_sliced_papaya", new ItemLike[]{ModItems.SLICED_PAPAYA})
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .build(consumer,ForgeRegistries.ITEMS.getKey(ModItems.PAPAYA_JUICE));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.LIME_JUICE,1,CookingRecipes.NORMAL_COOKING,1.0f)
                .addIngredient(ModItems.SLICED_LIME)
                .addIngredient(Items.SUGAR)
                .unlockedByItems("has_sliced_lime", new ItemLike[]{ModItems.SLICED_LIME})
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .build(consumer,ForgeRegistries.ITEMS.getKey(ModItems.LIME_JUICE));
    }
}
