package net.firemuffin303.thaidelight.integration.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipe;
import net.firemuffin303.thaidelight.integration.rei.ThaiDelightClientREI;

import java.util.List;

public class MortarREIDisplay extends BasicDisplay {
    private EntryIngredient container;

    public MortarREIDisplay(MortarRecipe mortarRecipe) {
        this(EntryIngredients.ofIngredients(mortarRecipe.getIngredients()), List.of(EntryIngredients.of(mortarRecipe.getResult())),EntryIngredients.of(mortarRecipe.getContainer()));
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
