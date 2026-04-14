package net.firemuffin303.thaidelight.integration.rei.category;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Slot;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.firemuffin303.thaidelight.client.sceens.MortarScreen;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.integration.rei.ThaiDelightClientREI;
import net.firemuffin303.thaidelight.integration.rei.display.MortarREIDisplay;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class MortarREICategory implements DisplayCategory<MortarREIDisplay> {
    @Override
    public CategoryIdentifier<? extends MortarREIDisplay> getCategoryIdentifier() {
        return ThaiDelightClientREI.MORTAR_ID;
    }

    @Override
    public Component getTitle() {
        return ModBlocks.MORTAR.get().getName();
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.MORTAR.get());
    }

    @Override
    public int getDisplayHeight() {
        return 72;
    }

    @Override
    public int getDisplayWidth(MortarREIDisplay display) {
        return 162;
    }

    @Override
    public List<Widget> setupDisplay(MortarREIDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        List<EntryIngredient> recipeIngredients = display.getInputEntries();
        List<EntryIngredient> resultStack = display.getOutputEntries();
        Point startPoint = new Point(bounds.getCenterX() - (162/2.8f), bounds.getCenterY() - (72f/2.5f) );
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createTexturedWidget(MortarScreen.CRAFTING_TABLE_LOCATION,startPoint.x,startPoint.y,36.0F, 14.0F, 124, 57));
        widgets.add(Widgets.createSlot(new Point(startPoint.x+ 88,startPoint.y+20)).entries(resultStack.get(0)).disableBackground());

        int inputIndex = 0;
        for(int row = 0; row < 2; ++row){
            for(int column = 0; column < 2; ++column){
                if(inputIndex < recipeIngredients.size()){
                    widgets.add(Widgets.createSlot(new Point(startPoint.x+3+(column * 18),startPoint.y+12+(row * 18))).entries(display.getInputEntries().get(inputIndex)).disableBackground());
                }
                inputIndex++;
            }
        }

        Slot containerSlot = Widgets.createSlot(new Point(startPoint.x+48,startPoint.y+40)).disableBackground();
        if(!display.getOutputContainer().isEmpty()){
            containerSlot.entries(display.getOutputContainer());
        }

        widgets.add(containerSlot);

        return widgets;
    }
}
