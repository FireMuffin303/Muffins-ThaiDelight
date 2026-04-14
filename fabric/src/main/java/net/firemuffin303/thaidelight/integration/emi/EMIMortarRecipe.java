package net.firemuffin303.thaidelight.integration.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.firemuffin303.thaidelight.client.sceens.MortarScreen;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EMIMortarRecipe implements EmiRecipe {
    private final ResourceLocation id;
    private final List<EmiIngredient> ingredients;
    private final EmiStack container;
    private final List<EmiStack> result;

    public EMIMortarRecipe(MortarRecipe mortarRecipe){
        this.id = mortarRecipe.getId();
        this.container = EmiStack.of(mortarRecipe.getContainer());
        this.result = List.of(EmiStack.of(mortarRecipe.getResult()));

        List<EmiIngredient> list = new ArrayList<>();
        for(Ingredient ingredient:mortarRecipe.getIngredients()){
            list.add(EmiIngredient.of(ingredient));
        }
        this.ingredients = list;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return ThaiDelightEMI.MORTAR;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return this.id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return Stream.concat(this.ingredients.stream(),Stream.of(this.container)).collect(Collectors.toList());
    }

    @Override
    public List<EmiStack> getOutputs() {
        return this.result;
    }

    @Override
    public int getDisplayWidth() {
        return 169;
    }

    @Override
    public int getDisplayHeight() {
        return 70;
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        widgetHolder.addTexture(MortarScreen.CRAFTING_TABLE_LOCATION,0,0,169,70,4,7);

        for(int i = 0;i < this.ingredients.size(); i++){
            widgetHolder.addSlot(this.ingredients.get(i),
                    i > 1 ? 34 + Mth.clamp(i-2,0,1) *18 : 34+ i *18,
                    i > 1 ? 26+10: 18).drawBack(false);

        }
        widgetHolder.addSlot(this.container,79,46).drawBack(false);
        widgetHolder.addSlot(this.result.get(0),119,27).drawBack(false).recipeContext(this);
    }
}
