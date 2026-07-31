package net.firemuffin303.thaidelight.datagen.builder;

import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CuttingBoardRecipeBuilder implements RecipeBuilder {
    private final List<ChanceResult> results = new ArrayList<>();
    private final Ingredient tool;
    private final Ingredient input;

    public CuttingBoardRecipeBuilder(ItemStack result, Ingredient input, Ingredient tool){
        this.results.add(new ChanceResult(result,1f));
        this.input = input;
        this.tool = tool;
    }

    public CuttingBoardRecipeBuilder(ItemStack result, Ingredient input, Ingredient tool,float chance){
        this.results.add(new ChanceResult(result,chance));
        this.input = input;
        this.tool = tool;
    }

    public static CuttingBoardRecipeBuilder cutting(Item result,int count, Ingredient input, Ingredient tool){
        return cutting(result,count, input, tool,1.0f);
    }

    public static CuttingBoardRecipeBuilder cutting(Item result,int count, Ingredient input, Ingredient tool,float chance){
        return new CuttingBoardRecipeBuilder(new ItemStack(result,count),input,tool,chance);
    }

    public CuttingBoardRecipeBuilder addResults(ItemStack result){
        this.addResults(result,1.0f);
        return this;
    }

    public CuttingBoardRecipeBuilder addResults(ItemStack result,float chance){
        this.results.add(new ChanceResult(result,chance));
        return this;
    }

    @Override
    public RecipeBuilder unlockedBy(String string, Criterion<?> criterion) {
        return null;
    }

    @Override
    public RecipeBuilder group(@Nullable String string) {
        return null;
    }

    @Override
    public Item getResult() {
        return this.results.get(0).stack().getItem();
    }

    @Override
    public void save(RecipeOutput consumer, ResourceLocation resourceLocation) {
        Advancement.Builder builder = consumer.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceLocation))
                .rewards(AdvancementRewards.Builder.recipe(resourceLocation))
                .requirements(AdvancementRequirements.Strategy.OR);

        NonNullList<ChanceResult> results = NonNullList.create();
        results.addAll(this.results);
        CuttingBoardRecipe cuttingBoardRecipe = new CuttingBoardRecipe("",this.input,this.tool,results, Optional.empty());
        consumer.accept(resourceLocation,cuttingBoardRecipe,builder.build(resourceLocation.withPrefix("recipe/")));
    }

}
