package net.firemuffin303.thaidelight.datagen.provider.recipe;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipeBookTab;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.firemuffin303.thaidelight.datagen.provider.recipe.builder.MortarRecipeBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class ThaiDelightMortarRecipeProvider implements IRecipeProvider {
    @Override
    public void generate(Consumer<FinishedRecipe> consumer) {
        mortar(consumer);
    }

    private void mortar(Consumer<FinishedRecipe> exporter){
        MortarRecipeBuilder.mortar(ModBlocks.SOMTAM_FEAST.get())
                .requires(ModTags.PEPPER)
                .requires(vectorwing.farmersdelight.common.registry.ModItems.TOMATO.get())
                .requires(ModTags.RAW_PAPAYA)
                .requires(ModItems.FERMENTED_FISH.get())
                .container(Items.BOWL)
                .recipeTab(MortarRecipeBookTab.MEALS)
                .unlockedBy(IRecipeProvider.getHasName(ModItems.FERMENTED_FISH.get()),IRecipeProvider.has(ModItems.FERMENTED_FISH.get()))
                .save(exporter, ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(ModBlocks.SOMTAM_FEAST.get())));

        MortarRecipeBuilder.mortar(ModItems.PESTO_SAUCE.get())
                .requires(ModItems.BASIL.get())
                .requires(ModItems.BASIL.get())
                .container(Items.BOWL)
                .recipeTab(MortarRecipeBookTab.MEALS)
                .unlockedBy(IRecipeProvider.getHasName(ModItems.BASIL.get()),IRecipeProvider.has(ModItems.BASIL.get()))
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(ModItems.PESTO_SAUCE.get())));

        MortarRecipeBuilder.mortar(Items.BONE_MEAL,4).requires(Items.BONE,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.BONE),IRecipeProvider.has(Items.BONE))
                .group("bone_meal")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.BONE_MEAL)+"_by_bone"));

        MortarRecipeBuilder.mortar(Items.BONE_MEAL,2)
                .requires(Items.NAUTILUS_SHELL,1)
                .group("bone_meal")
                .unlockedBy(IRecipeProvider.getHasName(Items.NAUTILUS_SHELL),IRecipeProvider.has(Items.NAUTILUS_SHELL))
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.BONE_MEAL)+"_by_nautilus_shell"));

        MortarRecipeBuilder.mortar(Items.BLAZE_POWDER,3)
                .requires(Items.BLAZE_ROD,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.BLAZE_ROD),IRecipeProvider.has(Items.BLAZE_ROD))
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.BLAZE_POWDER)+"_by_blaze_rod"));

        MortarRecipeBuilder.mortar(Items.SUGAR,2)
                .requires(Items.SUGAR_CANE,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.SUGAR_CANE),IRecipeProvider.has(Items.SUGAR_CANE))
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.SUGAR)+"_by_sugar_cane"));

        MortarRecipeBuilder.mortar(Items.BLACK_DYE,2)
                .requires(Items.COAL,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.COAL),IRecipeProvider.has(Items.COAL))
                .group("black_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.BLACK_DYE)+"_by_coal"));

        MortarRecipeBuilder.mortar(Items.BLACK_DYE,2)
                .requires(Items.CHARCOAL,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.CHARCOAL),IRecipeProvider.has(Items.CHARCOAL))
                .group("black_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.BLACK_DYE)+"_by_charcoal"));

        MortarRecipeBuilder.mortar(Items.WHITE_DYE,2)
                .requires(Items.BONE_MEAL,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.BONE_MEAL),IRecipeProvider.has(Items.BONE_MEAL))
                .group("white_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.WHITE_DYE)+"_by_bone_meal"));

        MortarRecipeBuilder.mortar(Items.WHITE_DYE,2).requires(Items.LILY_OF_THE_VALLEY,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.LILY_OF_THE_VALLEY),IRecipeProvider.has(Items.LILY_OF_THE_VALLEY))
                .group("white_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.WHITE_DYE)+"_by_lily_of_the_valley"));

        MortarRecipeBuilder.mortar(Items.LIGHT_GRAY_DYE,2)
                .requires(Items.OXEYE_DAISY,1)
                .group("light_gray_dye")
                .unlockedBy(IRecipeProvider.getHasName(Items.OXEYE_DAISY),IRecipeProvider.has(Items.OXEYE_DAISY))
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.LIGHT_GRAY_DYE)+"_by_oxeye_daisy"));

        MortarRecipeBuilder.mortar(Items.LIGHT_GRAY_DYE,2)
                .requires(Items.AZURE_BLUET,1)
                .group("light_gray_dye")
                .unlockedBy(IRecipeProvider.getHasName(Items.AZURE_BLUET),IRecipeProvider.has(Items.AZURE_BLUET))
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.LIGHT_GRAY_DYE)+"_by_azure_bluet"));
        MortarRecipeBuilder.mortar(Items.LIGHT_GRAY_DYE,2)
                .requires(Items.WHITE_TULIP,1)
                .group("light_gray_dye")
                .unlockedBy(IRecipeProvider.getHasName(Items.WHITE_TULIP),IRecipeProvider.has(Items.WHITE_TULIP))
                .save(exporter, ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.LIGHT_GRAY_DYE)+"_by_white_tulip"));

        MortarRecipeBuilder.mortar(Items.BLACK_DYE,2).requires(Items.INK_SAC,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.INK_SAC),IRecipeProvider.has(Items.INK_SAC))
                .group("black_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.BLACK_DYE)+"_by_ink_sac"));
        MortarRecipeBuilder.mortar(Items.BLACK_DYE,2).requires(Items.WITHER_ROSE,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.WITHER_ROSE),IRecipeProvider.has(Items.WITHER_ROSE))
                .group("black_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.BLACK_DYE)+"_by_wither_rose"));

        MortarRecipeBuilder.mortar(Items.BROWN_DYE,2).requires(Items.COCOA_BEANS,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.COCOA_BEANS),IRecipeProvider.has(Items.COCOA_BEANS))
                .group("brown_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.BROWN_DYE)+"_by_cocoa"));

        MortarRecipeBuilder.mortar(Items.RED_DYE,2).requires(Items.POPPY,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.POPPY),IRecipeProvider.has(Items.POPPY))
                .group("red_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.RED_DYE)+"_by_poppy"));

        MortarRecipeBuilder.mortar(Items.RED_DYE,2).requires(Items.BEETROOT,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.BEETROOT),IRecipeProvider.has(Items.BEETROOT))
                .group("red_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.RED_DYE)+"_by_beetroot"));

        MortarRecipeBuilder.mortar(Items.RED_DYE,2).requires(Items.RED_TULIP,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.RED_TULIP),IRecipeProvider.has(Items.RED_TULIP))
                .group("red_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.RED_DYE)+"_by_red_tulip"));

        MortarRecipeBuilder.mortar(Items.RED_DYE,2).requires(Items.ROSE_BUSH,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.ROSE_BUSH),IRecipeProvider.has(Items.ROSE_BUSH))
                .group("red_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.RED_DYE)+"_by_rose_bush"));

        MortarRecipeBuilder.mortar(Items.ORANGE_DYE,2).requires(Items.TORCHFLOWER,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.TORCHFLOWER),IRecipeProvider.has(Items.TORCHFLOWER))
                .group("orange_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.ORANGE_DYE)+"_by_torchflower"));

        MortarRecipeBuilder.mortar(Items.ORANGE_DYE,2).requires(Items.ORANGE_TULIP,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.ORANGE_TULIP),IRecipeProvider.has(Items.ORANGE_TULIP))
                .group("orange_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.ORANGE_DYE)+"_by_orange_tulip"));

        MortarRecipeBuilder.mortar(Items.YELLOW_DYE,2).requires(Items.DANDELION,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.DANDELION),IRecipeProvider.has(Items.DANDELION))
                .group("yellow_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.YELLOW_DYE)+"_by_dandelion"));
        MortarRecipeBuilder.mortar(Items.YELLOW_DYE,3).requires(Items.SUNFLOWER,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.SUNFLOWER),IRecipeProvider.has(Items.SUNFLOWER))
                .group("yellow_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.YELLOW_DYE)+"_by_sunflower"));

        MortarRecipeBuilder.mortar(Items.CYAN_DYE,3).requires(Items.PITCHER_PLANT,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.PITCHER_PLANT),IRecipeProvider.has(Items.PITCHER_PLANT))
                .group("cyan_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.CYAN_DYE)+"_by_pitcher_plant"));

        MortarRecipeBuilder.mortar(Items.LIGHT_BLUE_DYE,2).requires(Items.BLUE_ORCHID,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.BLUE_ORCHID),IRecipeProvider.has(Items.BLUE_ORCHID))
                .group("light_blue_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.LIGHT_BLUE_DYE)+"_by_blue_orchid"));

        MortarRecipeBuilder.mortar(Items.BLUE_DYE,2).requires(Items.CORNFLOWER,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.CORNFLOWER),IRecipeProvider.has(Items.CORNFLOWER))
                .group("blue_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.BLUE_DYE)+"_by_cornflower"));

        MortarRecipeBuilder.mortar(Items.BLUE_DYE,2).requires(Items.LAPIS_LAZULI,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.LAPIS_LAZULI),IRecipeProvider.has(Items.LAPIS_LAZULI))
                .group("blue_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.BLUE_DYE)+"_by_lapis_lazuli"));

        MortarRecipeBuilder.mortar(Items.PURPLE_DYE,1).requires(Items.CHORUS_FRUIT,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.CHORUS_FRUIT),IRecipeProvider.has(Items.CHORUS_FRUIT))
                .group("purple_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.PURPLE_DYE)+"_by_chorus_fruit"));

        MortarRecipeBuilder.mortar(Items.MAGENTA_DYE,2).requires(Items.ALLIUM,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.ALLIUM),IRecipeProvider.has(Items.ALLIUM))
                .group("magenta_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.MAGENTA_DYE)+"_by_allium"));
        MortarRecipeBuilder.mortar(Items.MAGENTA_DYE,3).requires(Items.LILAC,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.LILAC),IRecipeProvider.has(Items.LILAC))
                .group("magenta_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.MAGENTA_DYE)+"_by_lilac"));

        MortarRecipeBuilder.mortar(Items.PINK_DYE,2).requires(Items.PINK_PETALS,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.PINK_PETALS),IRecipeProvider.has(Items.PINK_PETALS))
                .group("pink_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.PINK_DYE)+"_by_pink_petals"));
        MortarRecipeBuilder.mortar(Items.PINK_DYE,2).requires(Items.PINK_TULIP,1)
                .group("pink_dye")
                .unlockedBy(IRecipeProvider.getHasName(Items.PINK_TULIP),IRecipeProvider.has(Items.PINK_TULIP))
                .group("pink_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.PINK_DYE)+"_by_pink_tulip"));
        MortarRecipeBuilder.mortar(Items.PINK_DYE,3).requires(Items.PEONY,1)
                .unlockedBy(IRecipeProvider.getHasName(Items.PEONY),IRecipeProvider.has(Items.PEONY))
                .group("pink_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.PINK_DYE)+"_by_peony"));

        MortarRecipeBuilder.mortar(Items.BLUE_DYE,3)
                .requires(ModItems.BUTTERFLY_PEA.get())
                .recipeTab(MortarRecipeBookTab.MISC)
                .unlockedBy(IRecipeProvider.getHasName(ModItems.BUTTERFLY_PEA.get()),IRecipeProvider.has(ModItems.BUTTERFLY_PEA.get()))
                .group("blue_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.BLUE_DYE)+"_from_butterfly_pea"));

        MortarRecipeBuilder.mortar(Items.LIME_DYE,2)
                .requires(ModItems.LIME.get())
                .recipeTab(MortarRecipeBookTab.MISC)
                .unlockedBy(IRecipeProvider.getHasName(ModItems.LIME.get()),IRecipeProvider.has(ModItems.LIME.get()))
                .group("lime_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.LIME_DYE)+"_from_lime"));

        MortarRecipeBuilder.mortar(Items.LIME_DYE,1)
                .requires(ModItems.SLICED_LIME.get())
                .recipeTab(MortarRecipeBookTab.MISC)
                .unlockedBy(IRecipeProvider.getHasName(ModItems.SLICED_LIME.get()),IRecipeProvider.has(ModItems.SLICED_LIME.get()))
                .group("lime_dye")
                .save(exporter,ThaiDelightCommon.modid("mortar/"+IRecipeProvider.getItemName(Items.LIME_DYE)+"_from_lime_slice"));

        MortarRecipeBuilder.mortar(ModItems.STEAMED_BAMBOO_SHOOT.get(),1)
                .requires(ModItems.BAMBOO_SHOOT.get())
                .requires(ModTags.PEPPER)
                .requires(ModItems.FERMENTED_FISH.get())
                .requires(ModTags.COMMON_COOKED_MEATS)
                .container(Items.BOWL)
                .unlockedBy(IRecipeProvider.getHasName(ModItems.BAMBOO_SHOOT.get()),IRecipeProvider.has(ModItems.BAMBOO_SHOOT.get()))
                .save(exporter,ThaiDelightCommon.modid("mortar/steamed_bamboo_shoot"));
    }

}
