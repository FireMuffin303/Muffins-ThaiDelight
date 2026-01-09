package net.firemuffin303.muffinsthaidelightfabric.datagen.builder;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.registry.ModRecipeSerializers;

import java.util.List;
import java.util.function.Consumer;

public class CookingPotRecipeBuilder implements RecipeBuilder {
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final Item result;
    private final int count;
    private final float experience;
    private final int cookTime;

    private String group;
    private Item container;
    private CookingPotRecipeBookTab cookingPotRecipeBookTab;
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    public CookingPotRecipeBuilder(ItemLike result,int count,float experience,int cookTime){
        this.result = result.asItem();
        this.count = count;
        this.experience = experience;
        this.cookTime = cookTime;

    }

    public static CookingPotRecipeBuilder cookingPot(ItemLike result,int count){
        return cookingPot(result,count,200,1.0f);
    }

    public static CookingPotRecipeBuilder cookingPot(ItemLike result,int count,int cookTime){
        return cookingPot(result,count,cookTime,1.0f);
    }

    public static CookingPotRecipeBuilder cookingPot(ItemLike result,int count,int cookTime ,float experience){
        return new CookingPotRecipeBuilder(result,count,experience,cookTime);
    }

    public CookingPotRecipeBuilder requires(TagKey<Item> tagKey) {
        return this.requires(Ingredient.of(tagKey));
    }

    public CookingPotRecipeBuilder requires(Ingredient ingredient) {
        return this.requires(ingredient, 1);
    }

    public CookingPotRecipeBuilder requires(ItemLike arg) {
        return this.requires(arg, 1);
    }

    public CookingPotRecipeBuilder requires(ItemLike arg, int i) {
        for(int j = 0; j < i; ++j) {
            this.ingredients.add(Ingredient.of(arg));
        }

        return this;
    }

    public CookingPotRecipeBuilder requires(Ingredient ingredient, int i) {
        for(int j = 0; j < i; ++j) {
            this.ingredients.add(ingredient);
        }

        return this;
    }

    public CookingPotRecipeBuilder container(ItemLike itemLike){
        this.container = itemLike.asItem();
        return this;
    }

    public CookingPotRecipeBuilder recipeTab(CookingPotRecipeBookTab cookingPotRecipeBookTab){
        this.cookingPotRecipeBookTab = cookingPotRecipeBookTab;
        return this;
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

    private void ensureValid(ResourceLocation arg) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + arg);
        }
    }

    @Override
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        this.ensureValid(resourceLocation);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT)
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceLocation))
                .rewards(AdvancementRewards.Builder.recipe(resourceLocation))
                .requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(resourceLocation,
                this.group == null ? "" : this.group,
                this.result,
                this.count,
                this.cookTime,
                this.experience,
                this.ingredients,
                this.container,
                this.cookingPotRecipeBookTab,
                this.advancement,
                resourceLocation.withPrefix("recipes/")
                ));
    }

    static class Result implements FinishedRecipe{
        private final ResourceLocation id;
        private final String group;
        private final List<Ingredient> ingredients;
        private final Item result;
        private final int count;
        private final int cookingTime;
        private final float experience;
        private final Item container;
        private final CookingPotRecipeBookTab cookingPotRecipeBookTab;

        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id,
                      String group,
                      Item result,
                      int count,
                      int cookingTime,
                      float experience,
                      List<Ingredient> ingredients,
                      Item container,
                      @Nullable CookingPotRecipeBookTab cookingPotRecipeBookTab,
                      Advancement.Builder advancement,
                      ResourceLocation advancementId
                      ){
            this.id = id;
            this.group = group;
            this.ingredients = ingredients;
            this.result = result;
            this.count = count;
            this.cookingTime = cookingTime;
            this.experience = experience;
            this.container = container;
            this.cookingPotRecipeBookTab = cookingPotRecipeBookTab;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject jsonObject) {
            JsonObject result = new JsonObject();
            result.addProperty("item",BuiltInRegistries.ITEM.getKey(this.result).toString());
            result.addProperty("count",this.count);
            jsonObject.add("result",result);

            jsonObject.addProperty("cookingtime",this.cookingTime);
            jsonObject.addProperty("experience",this.experience);

            JsonArray jsonArray = new JsonArray();

            for (Ingredient ingredient : this.ingredients) {
                jsonArray.add(ingredient.toJson());
            }

            jsonObject.add("ingredients", jsonArray);

            if(this.cookingPotRecipeBookTab != null){
                jsonObject.addProperty("recipe_book_tab",this.cookingPotRecipeBookTab.name);
            }

            if(!this.group.isEmpty()){
                jsonObject.addProperty("group",this.group);
            }

            if(this.container != Items.AIR){
                JsonObject container = new JsonObject();
                container.addProperty("item", BuiltInRegistries.ITEM.getKey(this.container).toString());
                jsonObject.add("container", container);
            }
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipeSerializers.COOKING.get();
        }

        @Override
        public @Nullable JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Override
        public @Nullable ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
