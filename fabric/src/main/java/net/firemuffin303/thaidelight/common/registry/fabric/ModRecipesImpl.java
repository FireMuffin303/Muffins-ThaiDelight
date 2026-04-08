package net.firemuffin303.thaidelight.common.registry.fabric;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

public class ModRecipesImpl {
    public static <T extends Recipe<?>> Supplier<RecipeType<T>> registerRecipe(String id, Supplier<RecipeType<T>> recipeSerializer) {
        RecipeType<T> registeredRecipeType = Registry.register(BuiltInRegistries.RECIPE_TYPE, ThaiDelightCommon.modid(id),recipeSerializer.get());
        return () -> registeredRecipeType;
    }


    public static <T extends Recipe<?>> Supplier<RecipeSerializer<T>> registerSerializer(String id, Supplier<RecipeSerializer<T>> recipeSerializer) {
        RecipeSerializer<T> registeredRecipe = Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,ThaiDelightCommon.modid(id),recipeSerializer.get());
        return () -> registeredRecipe;
    }
}
