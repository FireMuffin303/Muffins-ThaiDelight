package net.firemuffin303.thaidelight.datagen.provider.recipe;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.function.Consumer;

public class ThaiDelightCuttingBoardRecipeProvider implements IRecipeProvider {

    @Override
    public void generate(Consumer<FinishedRecipe> consumer) {
        cuttingBoard(consumer);
    }

    private void cuttingBoard(Consumer<FinishedRecipe> exporter){
        Ingredient knivesTag = Ingredient.of(ModTags.KNIVES);

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.LIME.get()),knivesTag,ModItems.SLICED_LIME.get(),2)
                .build(exporter, ThaiDelightCommon.modid("cutting/lime_slice"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.RAW_PAPAYA.get()),knivesTag,ModItems.RAW_PAPAYA_SLICE.get(),2)
                .build(exporter,ThaiDelightCommon.modid("cutting/raw_papaya_slice"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.PAPAYA.get()),knivesTag,ModItems.SLICED_PAPAYA.get(),2)
                .build(exporter,ThaiDelightCommon.modid("cutting/papaya_slice"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.SMALL_DURIAN.get()),knivesTag,ModItems.DURIAN_PULP.get(),1)
                .addResult(ModItems.DURIAN_PEEL.get())
                .build(exporter,ThaiDelightCommon.modid("cutting/durian_pulp_from_small_durian"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.DURIAN.get()),knivesTag,ModItems.DURIAN_PULP.get(),2)
                .addResult(ModItems.DURIAN_PEEL.get(),2)
                .build(exporter,ThaiDelightCommon.modid("cutting/durian_pulp_from_durian"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.MANGO.get()),knivesTag,ModItems.MANGO_SLICE.get(),2)
                .build(exporter,ThaiDelightCommon.modid("cutting/mango_slice"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.COCONUT.get()),Ingredient.of(ItemTags.AXES),ModItems.STRIPPED_COCONUT.get(),1)
                .addResult(vectorwing.farmersdelight.common.registry.ModItems.TREE_BARK.get(),2)
                .build(exporter,ThaiDelightCommon.modid("cutting/stripped_coconut_from_coconut"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.COCONUT.get()),knivesTag,ModItems.COCONUT_SLICE.get(), 2)
                .build(exporter,ThaiDelightCommon.modid("cutting/coconut_slice_from_coconut"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.STRIPPED_COCONUT.get()),knivesTag,ModItems.COCONUT_SLICE.get(),2 )
                .build(exporter,ThaiDelightCommon.modid("cutting/coconut_slice_from_stripped_coconut"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.DURIAN_CAKE.get()),knivesTag,ModItems.DURIAN_CAKE_SLICE.get(),7)
                .build(exporter,ThaiDelightCommon.modid("cutting/durian_cake_slice"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.MANGO_CHEESECAKE.get()),knivesTag,ModItems.MANGO_CHEESECAKE_SLICE.get(),4)
                .build(exporter,ThaiDelightCommon.modid("cutting/mango_pudding_slice"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.COCONUT_PIE.get()),knivesTag,ModItems.COCONUT_PIE_SLICE.get(),4)
                .build(exporter,ThaiDelightCommon.modid("cutting/coconut_pie_slice"));

        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(ModItems.HONEY_COCONUT_PIE.get()),knivesTag,ModItems.HONEY_COCONUT_PIE_SLICE.get(),4)
                .build(exporter,ThaiDelightCommon.modid("cutting/honey_coconut_pie_slice"));
    }

}
