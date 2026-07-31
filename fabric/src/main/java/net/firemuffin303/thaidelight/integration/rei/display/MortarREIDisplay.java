package net.firemuffin303.thaidelight.integration.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipe;
import net.firemuffin303.thaidelight.integration.rei.ThaiDelightClientREI;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.List;

public class MortarREIDisplay extends BasicDisplay {
    private EntryIngredient container;

    public MortarREIDisplay(RecipeHolder<MortarRecipe> mortarRecipe) {
        this(EntryIngredients.ofIngredients(mortarRecipe.value().getIngredients()), List.of(EntryIngredients.of(mortarRecipe.value().getResult())),EntryIngredients.of(mortarRecipe.value().getContainer()));
    }

    public MortarREIDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs,EntryIngredient container){
        super(inputs,outputs);
        this.container = container;
    }


    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return ThaiDelightClientREI.MORTAR_ID;
    }

    public EntryIngredient getOutputContainer() {
        return this.container;
    }
}
