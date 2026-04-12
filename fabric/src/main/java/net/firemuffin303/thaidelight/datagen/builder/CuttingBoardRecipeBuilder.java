package net.firemuffin303.thaidelight.datagen.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;
import vectorwing.farmersdelight.common.registry.ModRecipeSerializers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
    public RecipeBuilder unlockedBy(String string, CriterionTriggerInstance criterionTriggerInstance) {
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
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(resourceLocation,this.results,this.input,this.tool));
    }

    static class Result implements FinishedRecipe{
        public final ResourceLocation id;
        public final List<ChanceResult> results;
        public final Ingredient tool;
        public final Ingredient input;

        protected Result(ResourceLocation resourceLocation,List<ChanceResult> results, Ingredient input, Ingredient tool){
            this.id = resourceLocation;
            this.results = results;
            this.tool = tool;
            this.input = input;
        }


        @Override
        public void serializeRecipeData(JsonObject jsonObject) {
            JsonArray resultArray = new JsonArray();
            for(ChanceResult chanceResult : this.results){
                resultArray.add(chanceResult.serialize());
            }

            jsonObject.add("result",resultArray);
            jsonObject.add("tool",this.tool.toJson());

            JsonArray inputArray = new JsonArray();
            inputArray.add(this.input.toJson());

            jsonObject.add("ingredients",inputArray);
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipeSerializers.CUTTING.get();
        }

        @Override
        public @Nullable JsonObject serializeAdvancement() {
            return null;
        }

        @Override
        public @Nullable ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
