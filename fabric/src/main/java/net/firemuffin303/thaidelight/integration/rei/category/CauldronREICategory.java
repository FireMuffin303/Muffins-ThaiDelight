package net.firemuffin303.thaidelight.integration.rei.category;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.integration.rei.ThaiDelightClientREI;
import net.firemuffin303.thaidelight.integration.rei.display.CauldronCraftingREIDisplay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public class CauldronREICategory implements DisplayCategory<CauldronCraftingREIDisplay> {
    public static final ResourceLocation BACKGROUND = ThaiDelightCommon.modid("textures/gui/jei/fermented_fish_jei.png");
    private static final ModelBlockRenderer MODEL_BLOCK_RENDERER = Minecraft.getInstance().getBlockRenderer().getModelRenderer();


    @Override
    public CategoryIdentifier<? extends CauldronCraftingREIDisplay> getCategoryIdentifier() {
        return ThaiDelightClientREI.CAULDRON_ID;
    }

    @Override
    public Component getTitle() {
        return Blocks.CAULDRON.getName();
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(Blocks.CAULDRON);
    }

    @Override
    public int getDisplayHeight() {
        return 72;
    }

    @Override
    public int getDisplayWidth(CauldronCraftingREIDisplay display) {
        return 162;
    }

    @Override
    public List<Widget> setupDisplay(CauldronCraftingREIDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        List<EntryIngredient> recipeIngredients = display.getInputEntries();
        List<EntryIngredient> resultStack = display.getOutputEntries();

        Point origin = new Point(bounds.x,bounds.y);
        Point startPoint = new Point(bounds.getCenterX() - (148/2f), bounds.getCenterY() - (65f/2f) );
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createTexturedWidget(BACKGROUND,startPoint.x,startPoint.y,6.0F, 0.0F, 148, 65));
        widgets.add(Widgets.createSlot(new Point(startPoint.x+ 117,startPoint.y+33)).entries(resultStack.get(0)).disableBackground());
        widgets.add(Widgets.createSlot(new Point(startPoint.x+ 16,startPoint.y+33)).entries(recipeIngredients.get(0)).disableBackground());
        widgets.add(Widgets.createTexturedWidget(BACKGROUND,startPoint.x+91,startPoint.y+11,154f,22f,18,18));
        widgets.add(Widgets.createSlot(new Point(startPoint.x +92,startPoint.y+12)).entries(display.getContainer()).disableBackground());
        widgets.add(Widgets.createTooltip(new Rectangle(startPoint.x+54,startPoint.y+27,34,29),display.getTooltips()));

        widgets = display.renderExtra(widgets,new Point(bounds.x,bounds.y),startPoint);

        return widgets;
    }
}
