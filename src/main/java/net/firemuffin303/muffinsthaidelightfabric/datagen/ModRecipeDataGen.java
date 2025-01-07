package net.firemuffin303.muffinsthaidelightfabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModRecipes;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModTags;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.client.gui.CookingPotRecipeBookComponent;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;

import java.util.function.Consumer;

public class ModRecipeDataGen extends FabricRecipeProvider {
    public ModRecipeDataGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> exporter) {
        craft(exporter);
        smithing(exporter);
        cook(ModItems.CRAB_MEAT, ModItems.COOKED_CRAB_MEAT, 0.35f, 200, exporter);
        cook(ModItems.DRAGONFLY, ModItems.COOKED_DRAGONFLY, 0.35f, 200, exporter);
        mortar(exporter);
        SpecialRecipeBuilder.special(ModRecipes.ModSerializer.TASTY_CRAFTING_RECIPE).save(exporter,"tasty_food_crafting");



    }

    private void cook(ItemLike ingredient, Item result, float exp, int cookTicks, Consumer<FinishedRecipe> exporter) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.FOOD, result, exp, cookTicks).unlockedBy(getHasName(ingredient), has(ingredient)).save(exporter, ("smelting/")+getItemName(result) + ("_from_smelting"));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, exp, cookTicks / 2).unlockedBy(getHasName(ingredient), has(ingredient)).save(exporter, ("cooking/")+getItemName(result) + ("_from_cooking"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, exp, cookTicks * 3).unlockedBy(getHasName(ingredient), has(ingredient)).save(exporter, ("campfire/")+getItemName(result) + ("_from_campfire"));
    }

    private void craft(Consumer<FinishedRecipe> exporter){
        bigPackingCraft(Item.byBlock(ModBlocks.LIME_CRATE),1,ModItems.LIME,exporter);
        bigPackingCraft(Item.byBlock(ModBlocks.PEPPER_CRATE),1,ModItems.PEPPER,exporter);
        bigPackingCraft(Item.byBlock(ModBlocks.RAW_PAPAYA_CRATE),1,ModItems.RAW_PAPAYA,exporter);
        bigPackingCraft(Item.byBlock(ModBlocks.PAPAYA_CRATE),1,ModItems.PAPAYA,exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.LIME,9).requires(ModBlocks.LIME_CRATE).unlockedBy(getHasName(ModBlocks.LIME_CRATE),has(ModBlocks.LIME_CRATE)).save(exporter,"crafting/"+getItemName(ModItems.LIME)+"_from_crafting");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.PEPPER,9).requires(ModBlocks.PEPPER_CRATE).unlockedBy(getHasName(ModBlocks.PEPPER_CRATE),has(ModBlocks.PEPPER_CRATE)).save(exporter,"crafting/"+getItemName(ModItems.PEPPER)+"_from_crafting");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.RAW_PAPAYA,9).requires(ModBlocks.RAW_PAPAYA_CRATE).unlockedBy(getHasName(ModBlocks.RAW_PAPAYA_CRATE),has(ModBlocks.RAW_PAPAYA_CRATE)).save(exporter,"crafting/"+getItemName(ModItems.RAW_PAPAYA)+"_from_crafting");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD,ModItems.PAPAYA,9).requires(ModBlocks.PAPAYA_CRATE).unlockedBy(getHasName(ModBlocks.PAPAYA_CRATE),has(ModBlocks.PAPAYA_CRATE)).save(exporter,"crafting/"+getItemName(ModItems.PAPAYA)+"_from_crafting");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PEPPER_SEED,4).requires(ModItems.PEPPER).unlockedBy(getHasName(ModItems.PEPPER),has(ModItems.PEPPER)).save(exporter,"crafting/"+getItemName(ModItems.PEPPER_SEED)+"_from_crafting");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PAPAYA_SEEDS,4).requires(ModItems.PAPAYA).unlockedBy(getHasName(ModItems.PAPAYA),has(ModItems.PAPAYA)).save(exporter,"crafting/"+getItemName(ModBlocks.PAPAYA_SAPLING)+"_by_papaya_from_crafting");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PAPAYA_SEEDS,4).requires(ModItems.RAW_PAPAYA).unlockedBy(getHasName(ModItems.RAW_PAPAYA),has(ModItems.RAW_PAPAYA)).save(exporter,"crafting/"+getItemName(ModBlocks.PAPAYA_SAPLING)+"_by_unripe_papaya_from_crafting");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PAPAYA_SEEDS,2).requires(ModItems.SLICED_PAPAYA).unlockedBy(getHasName(ModItems.SLICED_PAPAYA),has(ModItems.SLICED_PAPAYA)).save(exporter,"crafting/"+getItemName(ModBlocks.PAPAYA_SAPLING)+"_by_sliced_papaya_from_crafting");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.STIR_FRIED_NOODLE).requires(vectorwing.farmersdelight.common.registry.ModItems.ONION.get()).requires(vectorwing.farmersdelight.common.registry.ModItems.RAW_PASTA.get()).requires(Items.SUGAR).requires(Items.BOWL).requires(ModTags.LIME).unlockedBy(getHasName(vectorwing.farmersdelight.common.registry.ModItems.RAW_PASTA.get()),has(vectorwing.farmersdelight.common.registry.ModItems.RAW_PASTA.get())).save(exporter,"crafting/"+getItemName(ModItems.STIR_FRIED_NOODLE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, vectorwing.farmersdelight.common.registry.ModItems.MIXED_SALAD.get()).requires(ModTags.RAW_PAPAYA).requires(vectorwing.farmersdelight.common.registry.ModItems.TOMATO.get()).requires(Items.BEETROOT).requires(Items.BOWL).unlockedBy(getHasName(Items.BOWL),has(Items.BOWL)).save(exporter,"crafting/"+getItemName(vectorwing.farmersdelight.common.registry.ModItems.MIXED_SALAD.get())+"by_raw_papaya");

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModBlocks.MORTAR).define('A',Items.BRICK).define('B',Items.STICK).pattern("ABA").pattern("AAA").unlockedBy(getHasName(Items.STICK),has(Items.STICK)).save(exporter,"crafting/"+getItemName(ModBlocks.MORTAR));
    }

    private void smithing(Consumer<FinishedRecipe> exporter){
        //SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),Ingredient.of(ModItems.DIAMOND_PASTLE),Ingredient.of(Items.NETHERITE_INGOT),RecipeCategory.TOOLS,ModItems.NETHERITE_PASTLE).unlocks(getHasName(Items.NETHERITE_INGOT),has(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)).save(exporter,"smithing/"+getItemName(ModItems.NETHERITE_PASTLE)+"_from_smithing");
    }

    private void bigPackingCraft(Item result,int resultAmount,ItemLike ingredient,Consumer<FinishedRecipe> exporter){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,result,resultAmount).define('A',ingredient).pattern("AAA").pattern("AAA").pattern("AAA").unlockedBy(getHasName(ingredient),has(ingredient)).save(exporter,"crafting/"+getItemName(result)+"_from_crafting");
    }

    private void mortar(Consumer<FinishedRecipe> exporter){
        MortarRecipeBuilder.mortar(ModBlocks.SOMTAM_FEAST).requires(ModItems.PEPPER).requires(vectorwing.farmersdelight.common.registry.ModItems.TOMATO.get()).requires(ModTags.RAW_PAPAYA).requires(ModItems.FERMENTED_FISH).unlockedBy(getHasName(ModItems.RAW_PAPAYA),has(ModItems.RAW_PAPAYA)).unlockedBy(getHasName(ModItems.FERMENTED_FISH),has(ModItems.FERMENTED_FISH)).save(exporter,"mortar/"+getItemName(ModBlocks.SOMTAM_FEAST));

        MortarRecipeBuilder.mortar(Items.BONE_MEAL,4).requires(Items.BONE,1).unlockedBy(getHasName(Items.BONE),has(Items.BONE)).save(exporter,"mortar/"+getItemName(Items.BONE_MEAL)+"_by_bone");
        MortarRecipeBuilder.mortar(Items.BONE_MEAL,2).requires(Items.NAUTILUS_SHELL,1).unlockedBy(getHasName(Items.NAUTILUS_SHELL),has(Items.NAUTILUS_SHELL)).save(exporter,"mortar/"+getItemName(Items.BONE_MEAL)+"_by_nautilus_shell");
        MortarRecipeBuilder.mortar(Items.BLAZE_POWDER,3).requires(Items.BLAZE_ROD,1).unlockedBy(getHasName(Items.BLAZE_ROD),has(Items.BLAZE_ROD)).save(exporter,"mortar/"+getItemName(Items.BLAZE_POWDER)+"_by_blaze_rod");
        MortarRecipeBuilder.mortar(Items.SUGAR,2).requires(Items.SUGAR_CANE,1).unlockedBy(getHasName(Items.SUGAR_CANE),has(Items.SUGAR_CANE)).save(exporter,"mortar/"+getItemName(Items.SUGAR)+"_by_sugar_cane");
        MortarRecipeBuilder.mortar(Items.BLACK_DYE,2).requires(Items.COAL,1).unlockedBy(getHasName(Items.COAL),has(Items.COAL)).save(exporter,"mortar/"+getItemName(Items.BLACK_DYE)+"_by_coal");
        MortarRecipeBuilder.mortar(Items.BLACK_DYE,2).requires(Items.CHARCOAL,1).unlockedBy(getHasName(Items.CHARCOAL),has(Items.CHARCOAL)).save(exporter,"mortar/"+getItemName(Items.BLACK_DYE)+"_by_charcoal");

        MortarRecipeBuilder.mortar(Items.WHITE_DYE,2).requires(Items.BONE_MEAL,1).unlockedBy(getHasName(Items.BONE_MEAL),has(Items.BONE_MEAL)).save(exporter,"mortar/"+getItemName(Items.WHITE_CARPET)+"_by_bone_meal");
        MortarRecipeBuilder.mortar(Items.WHITE_DYE,2).requires(Items.LILY_OF_THE_VALLEY,1).unlockedBy(getHasName(Items.LILY_OF_THE_VALLEY),has(Items.LILY_OF_THE_VALLEY)).save(exporter,"mortar/"+getItemName(Items.WHITE_CARPET)+"_by_lily_of_the_valley");

        MortarRecipeBuilder.mortar(Items.LIGHT_GRAY_DYE,2).requires(Items.OXEYE_DAISY,1).unlockedBy(getHasName(Items.OXEYE_DAISY),has(Items.OXEYE_DAISY)).save(exporter,"mortar/"+getItemName(Items.LIGHT_GRAY_DYE)+"_by_oxeye_daisy");
        MortarRecipeBuilder.mortar(Items.LIGHT_GRAY_DYE,2).requires(Items.AZURE_BLUET,1).unlockedBy(getHasName(Items.AZURE_BLUET),has(Items.AZURE_BLUET)).save(exporter,"mortar/"+getItemName(Items.LIGHT_GRAY_DYE)+"_by_azure_bluet");
        MortarRecipeBuilder.mortar(Items.LIGHT_GRAY_DYE,2).requires(Items.WHITE_TULIP,1).unlockedBy(getHasName(Items.WHITE_TULIP),has(Items.WHITE_TULIP)).save(exporter, "mortar/"+getItemName(Items.LIGHT_GRAY_DYE)+"_by_white_tulip");

        MortarRecipeBuilder.mortar(Items.BLACK_DYE,2).requires(Items.INK_SAC,1).unlockedBy(getHasName(Items.INK_SAC),has(Items.INK_SAC)).save(exporter,"mortar/"+getItemName(Items.BLACK_DYE)+"_by_ink_sac");
        MortarRecipeBuilder.mortar(Items.BLACK_DYE,2).requires(Items.WITHER_ROSE,1).unlockedBy(getHasName(Items.WITHER_ROSE),has(Items.WITHER_ROSE)).save(exporter,"mortar/"+getItemName(Items.BLACK_DYE)+"_by_wither_rose");

        MortarRecipeBuilder.mortar(Items.BROWN_DYE,2).requires(Items.COCOA_BEANS,1).unlockedBy(getHasName(Items.COCOA_BEANS),has(Items.COCOA_BEANS)).save(exporter,"mortar/"+getItemName(Items.BROWN_DYE)+"_by_cocoa");

        MortarRecipeBuilder.mortar(Items.RED_DYE,2).requires(Items.POPPY,1).unlockedBy(getHasName(Items.POPPY),has(Items.POPPY)).save(exporter,"mortar/"+getItemName(Items.RED_DYE)+"_by_poppy");
        MortarRecipeBuilder.mortar(Items.RED_DYE,2).requires(Items.BEETROOT,1).unlockedBy(getHasName(Items.BEETROOT),has(Items.BEETROOT)).save(exporter,"mortar/"+getItemName(Items.RED_DYE)+"_by_beetroot");
        MortarRecipeBuilder.mortar(Items.RED_DYE,2).requires(Items.RED_TULIP,1).unlockedBy(getHasName(Items.RED_TULIP),has(Items.RED_TULIP)).save(exporter,"mortar/"+getItemName(Items.RED_DYE)+"_by_red_tulip");
        MortarRecipeBuilder.mortar(Items.RED_DYE,2).requires(Items.ROSE_BUSH,1).unlockedBy(getHasName(Items.ROSE_BUSH),has(Items.ROSE_BUSH)).save(exporter,"mortar/"+getItemName(Items.RED_DYE)+"_by_rose_bush");

        MortarRecipeBuilder.mortar(Items.ORANGE_DYE,2).requires(Items.TORCHFLOWER,1).unlockedBy(getHasName(Items.TORCHFLOWER),has(Items.TORCHFLOWER)).save(exporter,"mortar/"+getItemName(Items.ORANGE_DYE)+"_by_torchflower");
        MortarRecipeBuilder.mortar(Items.ORANGE_DYE,2).requires(Items.ORANGE_TULIP,1).unlockedBy(getHasName(Items.ORANGE_TULIP),has(Items.ORANGE_TULIP)).save(exporter,"mortar/"+getItemName(Items.ORANGE_DYE)+"_by_orange_tulip");

        MortarRecipeBuilder.mortar(Items.YELLOW_DYE,2).requires(Items.DANDELION,1).unlockedBy(getHasName(Items.DANDELION),has(Items.DANDELION)).save(exporter,"mortar/"+getItemName(Items.YELLOW_DYE)+"_by_dandelion");
        MortarRecipeBuilder.mortar(Items.YELLOW_DYE,3).requires(Items.SUNFLOWER,1).unlockedBy(getHasName(Items.SUNFLOWER),has(Items.SUNFLOWER)).save(exporter,"mortar/"+getItemName(Items.YELLOW_DYE)+"_by_sunflower");

        MortarRecipeBuilder.mortar(Items.CYAN_DYE,3).requires(Items.PITCHER_PLANT,1).unlockedBy(getHasName(Items.PITCHER_PLANT),has(Items.PITCHER_PLANT)).save(exporter,"mortar/"+getItemName(Items.CYAN_DYE)+"_by_pitcher_plant");

        MortarRecipeBuilder.mortar(Items.LIGHT_BLUE_DYE,2).requires(Items.BLUE_ORCHID,1).unlockedBy(getHasName(Items.BLUE_ORCHID),has(Items.BLUE_ORCHID)).save(exporter,"mortar/"+getItemName(Items.LIGHT_BLUE_DYE)+"_by_blue_orchid");

        MortarRecipeBuilder.mortar(Items.BLUE_DYE,2).requires(Items.CORNFLOWER,1).unlockedBy(getHasName(Items.CORNFLOWER),has(Items.CORNFLOWER)).save(exporter,"mortar/"+getItemName(Items.BLUE_DYE)+"_by_cornflower");
        MortarRecipeBuilder.mortar(Items.BLUE_DYE,2).requires(Items.LAPIS_LAZULI,1).unlockedBy(getHasName(Items.LAPIS_LAZULI),has(Items.LAPIS_LAZULI)).save(exporter,"mortar/"+getItemName(Items.BLUE_DYE)+"_by_lapis_lazuli");

        MortarRecipeBuilder.mortar(Items.PURPLE_DYE,1).requires(Items.CHORUS_FRUIT,1).unlockedBy(getHasName(Items.CHORUS_FRUIT),has(Items.CHORUS_FRUIT)).save(exporter,"mortar/"+getItemName(Items.PURPLE_DYE)+"_by_chorus_fruit");

        MortarRecipeBuilder.mortar(Items.MAGENTA_DYE,2).requires(Items.ALLIUM,1).unlockedBy(getHasName(Items.ALLIUM),has(Items.ALLIUM)).save(exporter,"mortar/"+getItemName(Items.MAGENTA_DYE)+"_by_allium");
        MortarRecipeBuilder.mortar(Items.MAGENTA_DYE,3).requires(Items.LILAC,1).unlockedBy(getHasName(Items.LILAC),has(Items.LILAC)).save(exporter,"mortar/"+getItemName(Items.MAGENTA_DYE)+"_by_lilac");

        MortarRecipeBuilder.mortar(Items.PINK_DYE,2).requires(Items.PINK_PETALS,1).unlockedBy(getHasName(Items.PINK_PETALS),has(Items.PINK_PETALS)).save(exporter,"mortar/"+getItemName(Items.PINK_DYE)+"_by_pink_petals");
        MortarRecipeBuilder.mortar(Items.PINK_DYE,2).requires(Items.PINK_TULIP,1).unlockedBy(getHasName(Items.PINK_TULIP),has(Items.PINK_TULIP)).save(exporter,"mortar/"+getItemName(Items.PINK_DYE)+"_by_pink_tulip");
        MortarRecipeBuilder.mortar(Items.PINK_DYE,3).requires(Items.PEONY,1).unlockedBy(getHasName(Items.PEONY),has(Items.PEONY)).save(exporter,"mortar/"+getItemName(Items.PINK_DYE)+"_by_peony");

    }
}

