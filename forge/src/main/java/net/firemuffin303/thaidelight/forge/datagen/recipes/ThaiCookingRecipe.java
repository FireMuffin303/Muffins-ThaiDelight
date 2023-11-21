package net.firemuffin303.thaidelight.forge.datagen.recipes;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.forge.common.registry.ModItemsForge;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
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

        CookingPotRecipeBuilder.cookingPotRecipe(ModBlocks.SPICY_MINCED_PORK_SALAD_FEAST,1,CookingRecipes.NORMAL_COOKING,0.35f, Items.BOWL)
                .addIngredient(Items.COOKED_PORKCHOP)
                .addIngredient(ModItems.PEPPER)
                .addIngredient(Items.SUGAR)
                .addIngredient(ModItems.FISH_SAUCE_BOTTLE)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedByAnyIngredient(Items.COOKED_PORKCHOP)
                .build(consumer,ForgeRegistries.ITEMS.getKey(Item.byBlock(ModBlocks.SPICY_MINCED_PORK_SALAD_FEAST)));
    }
}
