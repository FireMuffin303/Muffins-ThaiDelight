package net.firemuffin303.thaidelight.data;

import com.google.common.collect.Lists;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class MortarRecipeBuilder {
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private Item result;
    private int count;
    private float experience;
    private Item container;
    private Ingredient tool;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    private MortarRecipeBuilder(ItemLike resultIn, int count, @Nullable ItemLike container,Ingredient tool) {
        this.result = resultIn.asItem();
        this.count = count;
        this.container = container != null ? container.asItem() : null;
    }



    public static MortarRecipeBuilder mortarRecipe(ItemLike mainResult, int count,Ingredient tool) {
        return new MortarRecipeBuilder(mainResult, count, (ItemLike)null,tool);
    }

    public MortarRecipeBuilder addIngredient(TagKey<Item> tagIn) {
        return this.addIngredient(Ingredient.of(tagIn));
    }

    public MortarRecipeBuilder addIngredient(ItemLike itemIn) {
        return this.addIngredient((ItemLike)itemIn, 1);
    }

    public MortarRecipeBuilder addIngredient(ItemLike itemIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.addIngredient(Ingredient.of(new ItemLike[]{itemIn}));
        }

        return this;
    }

    public MortarRecipeBuilder addIngredient(Ingredient ingredientIn) {
        return this.addIngredient((Ingredient)ingredientIn, 1);
    }

    public MortarRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.ingredients.add(ingredientIn);
        }

        return this;
    }


}
