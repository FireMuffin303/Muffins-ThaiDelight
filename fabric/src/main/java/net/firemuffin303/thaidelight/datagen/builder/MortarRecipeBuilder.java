package net.firemuffin303.thaidelight.datagen.builder;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.logging.LogUtils;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipeBookTab;
import net.firemuffin303.thaidelight.common.recipe.mortar.RegularMortarRecipe;
import net.firemuffin303.thaidelight.common.registry.ModRecipes;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

public class MortarRecipeBuilder implements RecipeBuilder {
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private Item container;
    private final Item result;
    private final int count;
    private MortarRecipeBookTab mortarRecipeBookTab = MortarRecipeBookTab.MISC;

    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    private String group;

    public MortarRecipeBuilder(ItemLike result, int count){
        this.result = result.asItem();
        this.count = count;
    }

    public static MortarRecipeBuilder mortar( ItemLike result, int count){
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

    public RecipeBuilder recipeTab(MortarRecipeBookTab mortarRecipeBookTab){
        this.mortarRecipeBookTab = mortarRecipeBookTab;
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

    public MortarRecipeBuilder container(ItemLike itemLike){
        this.container = itemLike.asItem();
        return this;
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

        RegularMortarRecipe recipe = new RegularMortarRecipe(Objects.requireNonNullElse(this.group,""),ingredients1,this.container == null ? ItemStack.EMPTY : new ItemStack(this.container),new ItemStack(this.result,this.count),this.mortarRecipeBookTab);

        recipeOutput.accept(resourceLocation,recipe,builder.build(resourceLocation.withPrefix("recipes/")));

    }

    private void ensureValid(ResourceLocation arg) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + arg);
        }
    }
}
