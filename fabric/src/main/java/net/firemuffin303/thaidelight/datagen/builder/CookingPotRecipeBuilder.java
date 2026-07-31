package net.firemuffin303.thaidelight.datagen.builder;

import com.google.common.collect.Lists;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;

import java.util.*;

public class CookingPotRecipeBuilder implements RecipeBuilder {
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final Item result;
    private final int count;
    private final float experience;
    private final int cookTime;

    private String group;
    private Item container;
    private CookingPotRecipeBookTab cookingPotRecipeBookTab;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap();

    private final List<ResourceCondition> conditions = new ArrayList<>();

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

    public CookingPotRecipeBuilder conditions(ResourceCondition condition){
        this.conditions.add(condition);
        return this;
    }

    @Override
    public RecipeBuilder unlockedBy(String string, Criterion<?> criterion) {
        this.criteria.put(string,criterion);
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
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + arg);
        }
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation resourceLocation) {
        this.ensureValid(resourceLocation);
        Advancement.Builder builder = recipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceLocation))
                .rewards(AdvancementRewards.Builder.recipe(resourceLocation))
                .requirements(AdvancementRequirements.Strategy.OR);

        NonNullList<Ingredient> ingredients1 = NonNullList.create();
        ingredients1.addAll(this.ingredients);

        CookingPotRecipe cookingPotRecipe = new CookingPotRecipe(Objects.requireNonNull(this.group,""),this.cookingPotRecipeBookTab,ingredients1,new ItemStack(this.result,this.count),new ItemStack(this.container),this.experience,this.cookTime);
        recipeOutput.accept(resourceLocation,cookingPotRecipe,builder.build(resourceLocation.withPrefix("recipes/")));
    }
}
