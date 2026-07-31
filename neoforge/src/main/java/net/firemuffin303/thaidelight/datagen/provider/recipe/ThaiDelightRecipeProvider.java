package net.firemuffin303.thaidelight.datagen.provider.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ThaiDelightRecipeProvider extends RecipeProvider {

    public ThaiDelightRecipeProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(arg, completableFuture);
    }


}
