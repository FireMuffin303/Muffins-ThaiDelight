package net.firemuffin303.thaidelight.datagen.provider.recipe;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ThaiDelightRecipeProvider extends RecipeProvider {
    List<IRecipeProvider> list = new ArrayList<>();

    public ThaiDelightRecipeProvider(PackOutput arg,List<IRecipeProvider> providers) {
        super(arg);
        list = providers;
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        list.forEach(iRecipeProvider -> iRecipeProvider.generate(consumer));
    }
}
