package net.firemuffin303.muffinsthaidelightfabric.registry;

import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.recipe.SourCraftingRecipe;
import net.firemuffin303.muffinsthaidelightfabric.common.recipe.MortarRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.*;

public class ModRecipes {
    public static RecipeType<MortarRecipe> MORTAR = register("mortar",new RecipeType<>() {
        @Override
        public String toString() {
            return "mortar";
        }
    });
    public static <T extends Recipe<?>> RecipeType<T> register(String id,RecipeType<T> recipeSerializer){
        return Registry.register(BuiltInRegistries.RECIPE_TYPE,new ResourceLocation(ThaiDelight.MOD_ID,id),recipeSerializer);
    }


    public static void init(){}


    public static class ModSerializer{
        public static RecipeSerializer<MortarRecipe> MORTAR_SERIALIZER = register("mortar",new MortarRecipe.Serializer());
        public static RecipeSerializer<SourCraftingRecipe> TASTY_CRAFTING_RECIPE = register("crafting_special_tasty",new SimpleCraftingRecipeSerializer<>(SourCraftingRecipe::new));

        public static void init(){}

        public static <T extends Recipe<?>> RecipeSerializer<T> register(String id,RecipeSerializer<T> recipeSerializer){
            return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,new ResourceLocation(ThaiDelight.MOD_ID,id),recipeSerializer);
        }
    }
}
