package net.firemuffin303.thaidelight.datagen.provider.recipe;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class ThaiDelightFurnaceRecipeProvider implements IRecipeProvider {
    /*
    @Override
    public void generate(Consumer<FinishedRecipe> exporter) {
        furnace(exporter);
        cook(ModItems.CRAB_MEAT.get(), ModItems.COOKED_CRAB_MEAT.get(), 0.35f, 200, exporter);
        cook(ModItems.DRAGONFLY.get(), ModItems.COOKED_DRAGONFLY.get(), 0.35f, 200, exporter);
        cook(ModItems.DURIAN_PULP.get(),ModItems.FRIED_DURIAN.get(),0.35f,150,exporter);
    }

    private void furnace(Consumer<FinishedRecipe> exporter){
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.DURIAN_PEEL.get()), RecipeCategory.MISC, Items.CHARCOAL,0.15f,200)
                .unlockedBy("has_durian_peel",IRecipeProvider.has(ModItems.DURIAN_PEEL.get()))
                .save(exporter, ThaiDelightCommon.modid("smelting/charcoal_from_durian_peel"));
    }

    private void cook(ItemLike ingredient, Item result, float exp, int cookTicks, Consumer<FinishedRecipe> exporter) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.FOOD, result, exp, cookTicks)
                .unlockedBy(IRecipeProvider.getHasName(ingredient), IRecipeProvider.has(ingredient))
                .save(exporter, ThaiDelightCommon.modid( "smelting/"+IRecipeProvider.getItemName(result) + "_from_smelting" ));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, exp, cookTicks / 2)
                .unlockedBy(IRecipeProvider.getHasName(ingredient), IRecipeProvider.has(ingredient))
                .save(exporter, ThaiDelightCommon.modid("cooking/"+IRecipeProvider.getItemName(result) + "_from_cooking"));

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, exp, cookTicks * 3)
                .unlockedBy(IRecipeProvider.getHasName(ingredient), IRecipeProvider.has(ingredient))
                .save(exporter, ThaiDelightCommon.modid("campfire/"+IRecipeProvider.getItemName(result) + "_from_campfire"));
    }



     */
}
