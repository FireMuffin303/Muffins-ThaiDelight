package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipe;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

public class ModRecipes {
    public static final ResourceRegistry<RecipeType<?>> RECIPE_TYPE = ResourceRegistry.create(Registries.RECIPE_TYPE, ThaiDelightCommon.MOD_ID);
    public static final ResourceRegistry<RecipeSerializer<?>> RECIPE_SERIALIZER = ResourceRegistry.create(Registries.RECIPE_SERIALIZER, ThaiDelightCommon.MOD_ID);


    @SuppressWarnings("unchecked")
    public static Supplier<RecipeType<MortarRecipe>> MORTAR = (Supplier<RecipeType<MortarRecipe>>) (Supplier<?>)registerRecipe("mortar", () -> new RecipeType<>() {
        @Override
        public String toString() {
            return "mortar";
        }
    });
    public static Supplier<RecipeSerializer<?>> MORTAR_SERIALIZER = registerSerializer("mortar", MortarSerializer::new);

    public static Supplier<RecipeType<?>> registerRecipe(String id, Supplier<RecipeType<?>> recipeSerializer){
        return RECIPE_TYPE.register(id,recipeSerializer);
    }

    public static Supplier<RecipeSerializer<?>> registerSerializer(String id, Supplier<RecipeSerializer<?>> recipeSerializer){
        return RECIPE_SERIALIZER.register(id,recipeSerializer);
    }


    public static void init(){
        RECIPE_TYPE.init();
        RECIPE_SERIALIZER.init();
    }


}
