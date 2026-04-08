package net.firemuffin303.thaidelight.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.recipe.MortarRecipe;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.core.registries.BuiltInRegistries;
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
    public static Supplier<RecipeSerializer<RegularMortarRecipe>> MORTAR_SERIALIZER = registerSerializer("mortar",new MortarSerializer());

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
