package net.firemuffin303.thaidelight.datagen.provider.recipe;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;

import java.util.function.Consumer;

public class ThaiDelightCookingPotRecipeProvider implements IRecipeProvider {
    @Override
    public void generate(Consumer<FinishedRecipe> exporter) {
        cookingPot(exporter);
    }

    private void cookingPot(Consumer<FinishedRecipe> exporter){
        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.CRAB_FRIED_RICE_FEAST.get(),1,200,0.35f,Items.BOWL)
                .addIngredient(ModTags.FLOWER_CRAB_MEAT)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .addIngredient(ModTags.COMMON_EGGS)
                .addIngredient(Items.CARROT)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.ONION.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedBy("has_rice",IRecipeProvider.has(vectorwing.farmersdelight.common.registry.ModItems.RICE.get()))
                .build(exporter, ThaiDelightCommon.modid("cooking_pot/crab_fried_rice_feast"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.CRAB_FRIED_RICE_FEAST.get(),1,100,0.35f,Items.BOWL)
                .addIngredient(ModTags.FLOWER_CRAB_MEAT)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.FRIED_RICE.get())
                .unlockedBy("has_rice", IRecipeProvider.has(vectorwing.farmersdelight.common.registry.ModItems.RICE.get()))
                .build(exporter,ThaiDelightCommon.modid("cooking_pot/crab_fried_rice_feast_from_rice"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.LARB_FEAST.get(),1,200,0.35f,Items.BOWL)
                .addIngredient(ModTags.COMMON_COOKED_MEATS)
                .addIngredient(ModTags.COMMON_COOKED_MEATS)
                .addIngredient(ModTags.PEPPER)
                .addIngredient(Items.SUGAR)
                .addIngredient(ModItems.FISH_SAUCE_BOTTLE.get())
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.ONION.get())
                .unlockedBy("has_pepper",IRecipeProvider.has(ModTags.PEPPER))
                .build(exporter,ThaiDelightCommon.modid("cooking_pot/larb_feast"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.FISH_SAUCE_BOTTLE.get(),1,100,0.35f,Items.GLASS_BOTTLE)
                .addIngredient(ModTags.COMMON_RAW_FISHES)
                .unlockedBy("has_raw_fishes",IRecipeProvider.has(ModTags.COMMON_RAW_FISHES))
                .build(exporter,ThaiDelightCommon.modid("cooking_pot/fish_sauce_bottle"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.PHAT_KAPHRAO_FEAST.get(),1,200,0.35f,Items.BOWL)
                .addIngredient(ModTags.COMMON_COOKED_MEATS)
                .addIngredient(ModItems.BASIL.get())
                .addIngredient(ModTags.PEPPER)
                .addIngredient(ModItems.FISH_SAUCE_BOTTLE.get())
                .addIngredient(Items.EGG)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedBy("has_basil",IRecipeProvider.has(ModItems.BASIL.get()))
                .build(exporter,ThaiDelightCommon.modid("cooking_pot/phat_khaphrao_feast"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.DURIAN_CURRY.get(), 1, 200, 0.35f, Items.BOWL)
                .addIngredient(ModTags.DURIAN)
                .addIngredient(ModItems.COCONUT_MILK_BOTTLE.get())
                .addIngredient(ModTags.PEPPER)
                .addIngredient(ModItems.FISH_SAUCE_BOTTLE.get())
                .addIngredient(ModTags.COMMON_COOKED_MEATS)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedBy("has_durian", IRecipeProvider.has(ModTags.DURIAN))
                .build(exporter, ThaiDelightCommon.modid("cooking_pot/durian_curry_feast"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.COCONUT_JELLY.get(), 4, 200, 0.35f, Items.AIR)
                .addIngredient(ModItems.COCONUT_SLICE.get())
                .addIngredient(Items.SLIME_BALL)
                .addIngredient(Items.SUGAR)
                .addIngredient(ModItems.COCONUT_WATER.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .unlockedBy("has_coconut_slice", IRecipeProvider.has(ModItems.COCONUT_SLICE.get()))
                .build(exporter, ThaiDelightCommon.modid("cooking_pot/coconut_jelly"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.KHANOM_BABIN.get(), 4, 200, 0.35f, Items.AIR)
                .addIngredient(ModItems.COCONUT_SLICE.get())
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .addIngredient(Items.SUGAR)
                .addIngredient(ModItems.COCONUT_MILK_BOTTLE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .unlockedBy("has_coconut_slice", IRecipeProvider.has(ModItems.COCONUT_SLICE.get()))
                .build(exporter, ThaiDelightCommon.modid("cooking_pot/khanom_babin"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.OMELETTE_FEAST.get(), 1, 200, 0.35f, Items.BOWL)
                .addIngredient(Items.EGG)
                .addIngredient(Items.EGG)
                .addIngredient(ModItems.FISH_SAUCE_BOTTLE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedBy("has_egg", IRecipeProvider.has(Items.EGG))
                .build(exporter, ThaiDelightCommon.modid("cooking_pot/omelette_feast"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BASIL_OMELETTE_FEAST.get(), 1, 200, 0.35f, Items.BOWL)
                .addIngredient(Items.EGG)
                .addIngredient(Items.EGG)
                .addIngredient(ModItems.FISH_SAUCE_BOTTLE.get())
                .addIngredient(ModItems.BASIL.get())
                .addIngredient(ModTags.PEPPER)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedBy("has_egg", IRecipeProvider.has(Items.EGG))
                .build(exporter, ThaiDelightCommon.modid("cooking_pot/basil_omelette_feast"));


        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.PINEAPPLE_FRIED_RICE_FEAST.get(),1,200,0.35f,Items.BOWL)
                .addIngredient(ModTags.PINEAPPLE)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.RICE.get())
                .addIngredient(Items.EGG)
                .addIngredient(Items.CARROT)
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.ONION.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                //.conditions(DefaultResourceConditions.tagsPopulated(ModTags.PINEAPPLE))
                .unlockedBy("has_pineapple",IRecipeProvider.has(ModTags.PINEAPPLE))
                .build(exporter,ThaiDelightCommon.modid("cooking_pot/pineapple_fried_rice_feast"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BAMBOO_SHOOT_SOUP.get(), 1, 200, 0.35f, Items.BOWL)
                .addIngredient(ModItems.BAMBOO_SHOOT.get())
                .addIngredient(ModTags.PEPPER)
                .addIngredient(ModItems.FERMENTED_FISH.get())
                .addIngredient(vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get())
                .addIngredient(ModItems.BASIL.get())
                .addIngredient(Items.BROWN_MUSHROOM)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .unlockedBy("has_bamboo_shoot", IRecipeProvider.has(ModItems.BAMBOO_SHOOT.get()))
                .build(exporter, ThaiDelightCommon.modid("cooking_pot/bamboo_shoot_soup"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BANANA_IN_COCONUT_MILK.get(),1,200,0.35f,Items.BOWL)
                .addIngredient(ModTags.BANANA)
                .addIngredient(ModItems.COCONUT_MILK_BOTTLE.get())
                .addIngredient(Items.SUGAR)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                //.conditions(DefaultResourceConditions.tagsPopulated(ModTags.BANANA))
                .unlockedBy("has_banana",IRecipeProvider.has(ModTags.BANANA))
                .build(exporter,ThaiDelightCommon.modid("cooking_pot/banana_in_coconut_milk"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.KHANOM_CHAN.get(), 4, 200, 0.35f, Items.AIR)
                .addIngredient(ModTags.COMMON_MILKS)
                .addIngredient(Items.SUGAR)
                .addIngredient(Items.WHEAT)
                .addIngredient(ModItems.COCONUT_MILK_BOTTLE.get())
                .unlockedBy("has_coconut_milk_bottle", IRecipeProvider.has(ModItems.COCONUT_MILK_BOTTLE.get()))
                .build(exporter, ThaiDelightCommon.modid("cooking_pot/khanom_chan"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.LIME_JUICE.get(), 1, 200, 0.35f, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.LIME)
                .addIngredient(ModTags.LIME)
                .addIngredient(Items.SUGAR)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .unlockedBy("has_lime", IRecipeProvider.has(ModItems.LIME.get()))
                .build(exporter, ThaiDelightCommon.modid("cooking_pot/lime_juice"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.HONEY_LIME_JUICE.get(), 1, 200, 0.35f, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.LIME)
                .addIngredient(ModTags.LIME)
                .addIngredient(Items.SUGAR)
                .addIngredient(Items.HONEY_BOTTLE)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .unlockedBy("has_lime", IRecipeProvider.has(ModItems.LIME.get()))
                .build(exporter, ThaiDelightCommon.modid("cooking_pot/honey_lime_juice"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.HONEY_LIME_JUICE.get(), 1, 100, 0.35f, Items.GLASS_BOTTLE)
                .addIngredient(ModItems.LIME_JUICE.get())
                .addIngredient(Items.HONEY_BOTTLE)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .unlockedBy("has_lime", IRecipeProvider.has(ModItems.LIME.get()))
                .build(exporter, ThaiDelightCommon.modid("cooking_pot/honey_lime_juice_from_lime_juice"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.PAPAYA_JUICE.get(), 1, 200, 0.35f, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.RIPE_PAPAYA)
                .addIngredient(ModTags.RIPE_PAPAYA)
                .addIngredient(Items.SUGAR)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .unlockedBy("has_papaya", IRecipeProvider.has(ModTags.PAPAYA))
                .build(exporter, ThaiDelightCommon.modid("cooking_pot/papaya_juice"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.COCONUT_WATER.get(), 1, 200, 0.35f, Items.GLASS_BOTTLE)
                .addIngredient(ModTags.COCONUT)
                .addIngredient(ModTags.COCONUT)
                .addIngredient(Items.SUGAR)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .unlockedBy("has_coconut", IRecipeProvider.has(ModTags.COCONUT))
                .build(exporter, ThaiDelightCommon.modid("cooking_pot/coconut_water"));

        CookingPotRecipeBuilder.cookingPotRecipe(ModItems.BUTTERFLY_PEA_TEA.get(), 1, 200, 0.35f, Items.GLASS_BOTTLE)
                .addIngredient(ModItems.BUTTERFLY_PEA.get())
                .addIngredient(ModItems.BUTTERFLY_PEA.get())
                .addIngredient(Items.SUGAR)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
                .unlockedBy("has_butterfly_pea", IRecipeProvider.has(ModItems.BUTTERFLY_PEA.get()))
                .build(exporter, ThaiDelightCommon.modid("cooking_pot/butterfly_pea_tea"));
    }


}
