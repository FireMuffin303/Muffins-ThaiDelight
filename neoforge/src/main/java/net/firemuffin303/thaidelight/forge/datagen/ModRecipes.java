package net.firemuffin303.thaidelight.forge.datagen;

import net.firemuffin303.thaidelight.forge.datagen.recipes.ThaiCookingRecipe;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ModRecipes extends RecipeProvider {

    public ModRecipes(PackOutput arg) {
        super(arg);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ThaiCookingRecipe.register(consumer);
    }
}
