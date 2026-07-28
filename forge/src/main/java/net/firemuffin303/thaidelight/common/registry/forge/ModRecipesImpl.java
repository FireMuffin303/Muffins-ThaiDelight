package net.firemuffin303.thaidelight.common.registry.forge;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipesImpl {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPE = DeferredRegister.create(Registries.RECIPE_TYPE, ThaiDelightCommon.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(Registries.RECIPE_SERIALIZER,ThaiDelightCommon.MOD_ID);

    public static <T extends Recipe<?>> Supplier<RecipeType<T>> registerRecipe(String id, Supplier<RecipeType<T>> recipeSerializer) {
        return RECIPE_TYPE.register(id,recipeSerializer);
    }

    public static <T extends Recipe<?>> Supplier<RecipeSerializer<T>> registerSerializer(String id, Supplier<RecipeSerializer<T>> recipeSerializer) {
        return RECIPE_SERIALIZER.register(id,recipeSerializer);
    }
}
