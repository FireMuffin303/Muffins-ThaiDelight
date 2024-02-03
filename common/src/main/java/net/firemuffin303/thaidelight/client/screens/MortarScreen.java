package net.firemuffin303.thaidelight.client.screens;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.menu.MortarMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.Slot;

public class MortarScreen extends AbstractContainerScreen<MortarMenu> implements RecipeUpdateListener {
    private static final ResourceLocation CRAFTING_TABLE_LOCATION = new ResourceLocation(ThaiDelight.MOD_ID,"textures/gui/mortar.png");
    private static final ResourceLocation RECIPE_BUTTON_LOCATION = new ResourceLocation("textures/gui/recipe_button.png");

    private final RecipeBookComponent recipeBookComponent = new RecipeBookComponent();
    private boolean widthTooNarrow;

    public MortarScreen(MortarMenu abstractContainerMenu, Inventory inventory, Component component) {
        super(abstractContainerMenu, inventory, component);
    }

    protected void init() {
        super.init();
        this.widthTooNarrow = this.width < 379;
        this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, (RecipeBookMenu)this.menu);
        this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
        this.addRenderableWidget(new ImageButton(this.leftPos + 5, this.height / 2 - 49, 20, 18, 0, 0, 19, RECIPE_BUTTON_LOCATION, (button) -> {
            this.recipeBookComponent.toggleVisibility();
            this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
            button.setPosition(this.leftPos + 5, this.height / 2 - 49);
        }));
        this.addWidget(this.recipeBookComponent);
        this.setInitialFocus(this.recipeBookComponent);
        this.titleLabelX = 29;
    }

    public void containerTick() {
        super.containerTick();
        this.recipeBookComponent.tick();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float f, int i, int j) {
        int k = this.leftPos;
        int l = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(CRAFTING_TABLE_LOCATION, k, l, 0, 0, this.imageWidth, this.imageHeight);
    }

    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        this.renderBackground(guiGraphics);
        if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
            this.renderBg(guiGraphics, f, i, j);
            this.recipeBookComponent.render(guiGraphics, i, j, f);
        } else {
            this.recipeBookComponent.render(guiGraphics, i, j, f);
            super.render(guiGraphics, i, j, f);
            this.recipeBookComponent.renderGhostRecipe(guiGraphics, this.leftPos, this.topPos, true, f);
        }

        this.renderTooltip(guiGraphics, i, j);
        this.recipeBookComponent.renderTooltip(guiGraphics, this.leftPos, this.topPos, i, j);
    }

    protected void slotClicked(Slot slot, int i, int j, ClickType clickType) {
        super.slotClicked(slot, i, j, clickType);
        this.recipeBookComponent.slotClicked(slot);
    }

    protected boolean isHovering(int i, int j, int k, int l, double d, double e) {
        return (!this.widthTooNarrow || !this.recipeBookComponent.isVisible()) && super.isHovering(i, j, k, l, d, e);
    }

    public boolean mouseClicked(double d, double e, int i) {
        if (this.recipeBookComponent.mouseClicked(d, e, i)) {
            this.setFocused(this.recipeBookComponent);
            return true;
        } else {
            return this.widthTooNarrow && this.recipeBookComponent.isVisible() ? true : super.mouseClicked(d, e, i);
        }
    }

    protected boolean hasClickedOutside(double d, double e, int i, int j, int k) {
        boolean bl = d < (double)i || e < (double)j || d >= (double)(i + this.imageWidth) || e >= (double)(j + this.imageHeight);
        return this.recipeBookComponent.hasClickedOutside(d, e, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, k) && bl;
    }

    @Override
    public void recipesUpdated() {
        this.recipeBookComponent.recipesUpdated();
    }

    @Override
    public RecipeBookComponent getRecipeBookComponent() {
        return this.recipeBookComponent;
    }
}
