package net.firemuffin303.thaidelight.fabric.datagen;

import net.firemuffin303.thaidelight.common.recipe.MortarRecipe;
import net.minecraft.advancements.*;
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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class MortarRecipeBuilder implements RecipeBuilder {
    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    private final Item result;
    private final int count;

    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
    @Nullable
    private String group;

    public MortarRecipeBuilder(ItemLike result,int count){
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

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation resourceLocation) {
        this.ensureValid(resourceLocation);
        Advancement.Builder builder = recipeOutput.advancement().addCriterion("has_the_recipe",RecipeUnlockedTrigger.unlocked(resourceLocation)).rewards(AdvancementRewards.Builder.recipe(resourceLocation)).requirements(AdvancementRequirements.Strategy.OR);
        Map<String, Criterion<?>> map = this.criteria;
        Objects.requireNonNull(builder);
        map.forEach(builder::addCriterion);

        MortarRecipe mortarRecipe = new MortarRecipe(Objects.requireNonNull(this.group,""), this.ingredients,new ItemStack(this.result,this.count));
        recipeOutput.accept(resourceLocation,mortarRecipe,builder.build(resourceLocation.withPrefix("recipes/")));

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


    private void ensureValid(ResourceLocation arg) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + arg);
        }
    }
}
