package net.firemuffin303.thaidelight.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipe;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarSerializer;
import net.firemuffin303.thaidelight.common.recipe.mortar.RegularMortarRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

public class ModRecipes {
    public static Supplier<RecipeType<MortarRecipe>> MORTAR = registerRecipe("mortar", () -> new RecipeType<>() {
        @Override
        public String toString() {
            return "mortar";
        }
    });
    public static Supplier<RecipeSerializer<RegularMortarRecipe>> MORTAR_SERIALIZER = registerSerializer("mortar", MortarSerializer::new);

    @ExpectPlatform
    public static <T extends Recipe<?>> Supplier<RecipeType<T>> registerRecipe(String id, Supplier<RecipeType<T>> recipeSerializer){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Recipe<?>> Supplier<RecipeSerializer<T>> registerSerializer(String id, Supplier<RecipeSerializer<T>> recipeSerializer){
        throw new AssertionError();
    }


    public static void init(){}


}
