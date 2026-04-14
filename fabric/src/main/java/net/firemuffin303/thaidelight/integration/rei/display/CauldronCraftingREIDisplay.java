package net.firemuffin303.thaidelight.integration.rei.display;

import me.shedaniel.math.Point;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import net.firemuffin303.thaidelight.integration.rei.ThaiDelightClientREI;
import net.minecraft.network.chat.Component;

import java.util.List;

public class CauldronCraftingREIDisplay extends BasicDisplay {
    private EntryIngredient container;
    private List<Component> tooltips;

    public CauldronCraftingREIDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs,EntryIngredient container,List<Component> list) {
        super(inputs, outputs);
        this.container = container;
        this.tooltips = list;
    }

    public List<Widget> renderExtra(List<Widget> widgets,Point origin, Point startPoint) {
        return List.of();
    }

    public EntryIngredient getContainer() {
        return container;
    }

    public List<Component> getTooltips() {
        return tooltips;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return ThaiDelightClientREI.CAULDRON_ID;
    }
}
