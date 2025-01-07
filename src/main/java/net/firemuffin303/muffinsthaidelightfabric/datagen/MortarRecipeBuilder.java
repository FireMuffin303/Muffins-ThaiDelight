package net.firemuffin303.muffinsthaidelightfabric.datagen;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModRecipes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class MortarRecipeBuilder implements RecipeBuilder {
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final Item result;
    private final int count;

    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
    @Nullable
    private String group;

    public MortarRecipeBuilder(ItemLike result, int count){
        this.result = result.asItem();
        this.count = count;
    }

    public static MortarRecipeBuilder mortar(ItemLike result,int count){
        return new MortarRecipeBuilder(result, count);
    }


    public static MortarRecipeBuilder mortar(ItemLike result){
        return new MortarRecipeBuilder(result, 1);
    }

    @Override
    public RecipeBuilder unlockedBy(String string, CriterionTriggerInstance criterionTriggerInstance) {
        this.advancement.addCriterion(string,criterionTriggerInstance);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String string) {
        this.group = string;
        return this;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    public MortarRecipeBuilder requires(TagKey<Item> tagKey) {
        return this.requires(Ingredient.of(tagKey));
    }

    public MortarRecipeBuilder requires(Ingredient ingredient) {
        return this.requires(ingredient, 1);
    }

    public MortarRecipeBuilder requires(ItemLike arg) {
        return this.requires(arg, 1);
    }

    public MortarRecipeBuilder requires(ItemLike arg, int i) {
        for(int j = 0; j < i; ++j) {
            this.ingredients.add(Ingredient.of(arg));
        }

        return this;
    }

    public MortarRecipeBuilder requires(Ingredient ingredient, int i) {
        for(int j = 0; j < i; ++j) {
            this.ingredients.add(ingredient);
        }

        return this;
    }

    @Override
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        this.ensureValid(resourceLocation);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceLocation)).rewards(net.minecraft.advancements.AdvancementRewards.Builder.recipe(resourceLocation)).requirements(RequirementsStrategy.OR);
        consumer.accept(new MortarRecipeBuilder.Result(resourceLocation,this.group, this.ingredients,this.result,this.count,this.advancement,resourceLocation.withPrefix("recipes/mortar/")));
    }

    private void ensureValid(ResourceLocation arg) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + arg);
        }
    }

    static class Result implements FinishedRecipe{
        private final ResourceLocation id;
        private final String group;
        private final List<Ingredient> ingredients;
        private final Item result;
        private final int count;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;


        public Result(ResourceLocation resourceLocation, String group, List<Ingredient> ingredients, Item itemStack, int count, Advancement.Builder builder,ResourceLocation advancementId){
            this.id = resourceLocation;
            this.group = group;
            this.ingredients = ingredients;
            this.result = itemStack;
            this.count = count;
            this.advancement = builder;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject jsonObject) {
            //if(!this.group.isEmpty()){
            //    jsonObject.addProperty("group",this.group);
            //}

            JsonArray jsonArray = new JsonArray();

            for (Ingredient ingredient : this.ingredients) {
                jsonArray.add(ingredient.toJson());
            }

            jsonObject.add("ingredients", jsonArray);

            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("item", BuiltInRegistries.ITEM.getKey(this.result).toString());
            if (this.count > 1) {
                jsonObject2.addProperty("count", this.count);
            }

            jsonObject.add("result", jsonObject2);

        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipes.ModSerializer.MORTAR_SERIALIZER;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
