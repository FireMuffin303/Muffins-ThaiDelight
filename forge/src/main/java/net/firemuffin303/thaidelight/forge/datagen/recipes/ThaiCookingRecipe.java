package net.firemuffin303.thaidelight.forge.datagen.recipes;

import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModTags;
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
                .unlockedByAnyIngredient(
                        Items.GLASS_BOTTLE,
                        Items.COD,
                        Items.SALMON
                )
                .build(consumer,ForgeRegistries.ITEMS.getKey(ModItems.FISH_SAUCE_BOTTLE));

        CookingPotRecipeBuilder.cookingPotRecipe(ModBlocks.CRAB_FRIED_RICE_FEAST,1,CookingRecipes.NORMAL_COOKING,0.35f, Items.BOWL)
                .addIngredient(ModTags.FLOWER_CRAB_MEAT)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .addIngredient(ForgeTags.EGGS)
                .addIngredient(Items.CARROT)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.ONION.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(
                        Items.CARROT,
                        vectorwing.farmersdelight.common.registry.ModItems.ONION.get(),
                        Items.EGG,
                        ModItems.CRAB_MEAT,
                        ModItems.COOKED_CRAB_MEAT,
                        vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .build(consumer,ForgeRegistries.ITEMS.getKey(ModItemsForge.CRAB_FRIED_RICE.get()));

        CookingPotRecipeBuilder.cookingPotRecipe(ModBlocks.CRAB_FRIED_RICE_FEAST,1,CookingRecipes.NORMAL_COOKING,0.35f, Items.BOWL)
                .addIngredient(ModTags.FLOWER_CRAB_MEAT)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.FRIED_RICE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(
                        ModItems.CRAB_MEAT,
                        ModItems.COOKED_CRAB_MEAT,
                        vectorwing.farmersdelight.common.registry.ModItems.FRIED_RICE.get()
                        )
                .build(consumer,ForgeRegistries.ITEMS.getKey(ModItemsForge.CRAB_FRIED_RICE.get())+"_from_fried_rice");

        CookingPotRecipeBuilder.cookingPotRecipe(ModBlocks.SPICY_MINCED_MEAT_SALAD_FEAST,1,CookingRecipes.NORMAL_COOKING,0.35f, Items.BOWL)
                .addIngredient(ForgeTags.COOKED_BEEF)
                .addIngredient(ForgeTags.COOKED_BEEF)
                .addIngredient(ModItems.PEPPER)
                .addIngredient(Items.SUGAR)
                .addIngredient(ModItems.FISH_SAUCE_BOTTLE)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.ONION.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(
                        Items.COOKED_BEEF,
                        vectorwing.farmersdelight.common.registry.ModItems.BEEF_PATTY.get(),
                        ModItems.PEPPER,
                        Items.SUGAR,
                        vectorwing.farmersdelight.common.registry.ModItems.ONION.get(),
                        ModItems.FISH_SAUCE_BOTTLE
                )
                .build(consumer,ForgeRegistries.ITEMS.getKey(Item.byBlock(ModBlocks.SPICY_MINCED_MEAT_SALAD_FEAST))+"_from_cooked_beef");

        CookingPotRecipeBuilder.cookingPotRecipe(ModBlocks.SPICY_MINCED_MEAT_SALAD_FEAST,1,CookingRecipes.NORMAL_COOKING,0.35f, Items.BOWL)
                .addIngredient(ForgeTags.COOKED_PORK)
                .addIngredient(ForgeTags.COOKED_PORK)
                .addIngredient(ModItems.PEPPER)
                .addIngredient(Items.SUGAR)
                .addIngredient(ModItems.FISH_SAUCE_BOTTLE)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.ONION.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(
                        Items.COOKED_PORKCHOP,
                        vectorwing.farmersdelight.common.registry.ModItems.COOKED_BACON.get(),
                        ModItems.PEPPER,
                        Items.SUGAR,
                        ModItems.FISH_SAUCE_BOTTLE
                )
                .build(consumer,ForgeRegistries.ITEMS.getKey(Item.byBlock(ModBlocks.SPICY_MINCED_MEAT_SALAD_FEAST))+"_from_cooked_pork");

        CookingPotRecipeBuilder.cookingPotRecipe(ModBlocks.SPICY_MINCED_MEAT_SALAD_FEAST,1,CookingRecipes.NORMAL_COOKING,0.35f, Items.BOWL)
                .addIngredient(ForgeTags.COOKED_MUTTON)
                .addIngredient(ForgeTags.COOKED_MUTTON)
                .addIngredient(ModItems.PEPPER)
                .addIngredient(Items.SUGAR)
                .addIngredient(ModItems.FISH_SAUCE_BOTTLE)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.ONION.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(
                        Items.COOKED_MUTTON,
                        ModItems.PEPPER,
                        Items.SUGAR,
                        ModItems.FISH_SAUCE_BOTTLE
                )
                .build(consumer,ForgeRegistries.ITEMS.getKey(Item.byBlock(ModBlocks.SPICY_MINCED_MEAT_SALAD_FEAST))+"_from_cooked_mutton");

        CookingPotRecipeBuilder.cookingPotRecipe(ModBlocks.SPICY_MINCED_MEAT_SALAD_FEAST,1,CookingRecipes.NORMAL_COOKING,0.35f, Items.BOWL)
                .addIngredient(ForgeTags.COOKED_CHICKEN)
                .addIngredient(ForgeTags.COOKED_CHICKEN)
                .addIngredient(ModItems.PEPPER)
                .addIngredient(Items.SUGAR)
                .addIngredient(ModItems.FISH_SAUCE_BOTTLE)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.ONION.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(
                        Items.COOKED_CHICKEN,
                        ModItems.PEPPER,
                        Items.SUGAR,
                        ModItems.FISH_SAUCE_BOTTLE
                )
                .build(consumer,ForgeRegistries.ITEMS.getKey(Item.byBlock(ModBlocks.SPICY_MINCED_MEAT_SALAD_FEAST))+"_from_cooked_chicken");

        CookingPotRecipeBuilder.cookingPotRecipe(ModBlocks.SPICY_MINCED_MEAT_SALAD_FEAST,1,CookingRecipes.NORMAL_COOKING,0.35f, Items.BOWL)
                .addIngredient(ForgeTags.COOKED_FISHES)
                .addIngredient(ForgeTags.COOKED_FISHES)
                .addIngredient(ModItems.PEPPER)
                .addIngredient(Items.SUGAR)
                .addIngredient(ModItems.FISH_SAUCE_BOTTLE)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.ONION.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(
                        Items.COOKED_COD,
                        Items.COOKED_SALMON,
                        ModItems.PEPPER,
                        Items.SUGAR,
                        ModItems.FISH_SAUCE_BOTTLE
                )
                .build(consumer,ForgeRegistries.ITEMS.getKey(Item.byBlock(ModBlocks.SPICY_MINCED_MEAT_SALAD_FEAST))+"_from_cooked_fishes");

        CookingPotRecipeBuilder.cookingPotRecipe(ModBlocks.SPICY_MINCED_MEAT_SALAD_FEAST,1,CookingRecipes.NORMAL_COOKING,0.35f, Items.BOWL)
                .addIngredient(ModItems.COOKED_CRAB_MEAT)
                .addIngredient(ModItems.COOKED_CRAB_MEAT)
                .addIngredient(ModItems.PEPPER)
                .addIngredient(Items.SUGAR)
                .addIngredient(ModItems.FISH_SAUCE_BOTTLE)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.ONION.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(
                        ModItems.COOKED_CRAB_MEAT,
                        ModItems.PEPPER,
                        Items.SUGAR,
                        ModItems.FISH_SAUCE_BOTTLE
                )
                .build(consumer,ForgeRegistries.ITEMS.getKey(Item.byBlock(ModBlocks.SPICY_MINCED_MEAT_SALAD_FEAST))+"_from_cooked_flower_crab");

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.LIME), Ingredient.of(ForgeTags.TOOLS_KNIVES),ModItems.SLICED_LIME,2).build(consumer,ForgeRegistries.ITEMS.getKey(ModItems.SLICED_LIME));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.RAW_PAPAYA), Ingredient.of(ForgeTags.TOOLS_KNIVES),ModItems.RAW_PAPAYA_SLICE,4).build(consumer,ForgeRegistries.ITEMS.getKey(ModItems.RAW_PAPAYA_SLICE));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.PAPAYA), Ingredient.of(ForgeTags.TOOLS_KNIVES),ModItems.SLICED_PAPAYA,2).build(consumer,ForgeRegistries.ITEMS.getKey(ModItems.SLICED_PAPAYA));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.PAPAYA_JUICE,1,CookingRecipes.NORMAL_COOKING,1.0f)
                .addIngredient(ModTags.RIPE_PAPAYA)
                .addIngredient(ModTags.RIPE_PAPAYA)
                .addIngredient(Items.SUGAR)
                .unlockedByItems("has_sliced_papaya", ModItems.SLICED_PAPAYA)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .build(consumer,ForgeRegistries.ITEMS.getKey(ModItems.PAPAYA_JUICE));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.LIME_JUICE,1,CookingRecipes.NORMAL_COOKING,1.0f)
                .addIngredient(ModTags.LIME)
                .addIngredient(ModTags.LIME)
                .addIngredient(Items.SUGAR)
                .unlockedByItems("has_sliced_lime", ModItems.SLICED_LIME)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .build(consumer,ForgeRegistries.ITEMS.getKey(ModItems.LIME_JUICE));
    }
}
