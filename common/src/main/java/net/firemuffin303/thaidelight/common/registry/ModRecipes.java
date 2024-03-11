package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.common.recipe.MortarRecipe;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.client.gui.components.toasts.RecipeToast;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.ArrayList;

public class ModRecipes {
    public static ArrayList<RecipeType<?>> RECIPES = new ArrayList<>();
    public static RecipeType<MortarRecipe> MORTAR = new RecipeType<MortarRecipe>() {
        @Override
        public String toString() {
            return "mortar";
        }
    };

    public static void init(){
        register("mortar",MORTAR);
    }

    private static void register(String id,RecipeType<?> recipeType){
        ModPlatform.registerRecipeType(id,recipeType);
        RECIPES.add(recipeType);

    }

    public static class ModRecipeSerializer{
        public static ArrayList<RecipeSerializer<?>> RECIPE_SERIALIZERS = new ArrayList<>();
        public static RecipeSerializer<MortarRecipe> MORTAR_SERIALIZER = new MortarRecipe.Serializer();

        public static void init(){
            register("mortar",MORTAR_SERIALIZER);
        }

        private static void register(String id,RecipeSerializer<?> recipeSerializer){
            ModPlatform.registerRecipeSerializer(id,recipeSerializer);
            RECIPE_SERIALIZERS.add(recipeSerializer);

        }
    }


}
