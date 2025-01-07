package net.firemuffin303.muffinsthaidelightfabric.client.sceens;

import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.menu.MortarMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;

public class MortarScreen extends AbstractContainerScreen<MortarMenu> {
    public static final ResourceLocation CRAFTING_TABLE_LOCATION = new ResourceLocation(ThaiDelight.MOD_ID,"textures/gui/mortar.png");
    private static final ResourceLocation RECIPE_BUTTON_LOCATION = new ResourceLocation("textures/gui/recipe_button.png");

    public static final Rect2i CLICK_AREA = new Rect2i(41, 25, 26, 25);

    //private final RecipeBookComponent recipeBookComponent = new RecipeBookComponent();
    private boolean widthTooNarrow;

    public MortarScreen(MortarMenu abstractContainerMenu, Inventory inventory, Component component) {
        super(abstractContainerMenu, inventory, component);
    }

    protected void init() {
        super.init();
        this.widthTooNarrow = this.width < 379;
        //this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, (RecipeBookMenu)this.menu);
        //this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
        //this.addRenderableWidget(new ImageButton(this.leftPos + 5, this.height / 2 - 49, 20, 18, 0, 0, 19, RECIPE_BUTTON_LOCATION, (button) -> {
        //    this.recipeBookComponent.toggleVisibility();
        //    this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
        //    button.setPosition(this.leftPos + 5, this.height / 2 - 49);
        //}));
        //this.addWidget(this.recipeBookComponent);
        //this.setInitialFocus(this.recipeBookComponent);
        this.titleLabelX = 29;
    }

    public void containerTick() {
        super.containerTick();
        //this.recipeBookComponent.tick();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float f, int i, int j) {
        int k = this.leftPos;
        int l = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(CRAFTING_TABLE_LOCATION, k, l, 0, 0, this.imageWidth, this.imageHeight);
    }

    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        this.renderBackground(guiGraphics);
        this.renderBg(guiGraphics, f, i, j);
        super.render(guiGraphics, i, j, f);
        this.renderTooltip(guiGraphics, i, j);
    }

    protected void slotClicked(Slot slot, int i, int j, ClickType clickType) {
        super.slotClicked(slot, i, j, clickType);
    }

    protected boolean isHovering(int i, int j, int k, int l, double d, double e) {
        return super.isHovering(i, j, k, l, d, e);
    }

    public boolean mouseClicked(double d, double e, int i) {
        return super.mouseClicked(d, e, i);
    }

    protected boolean hasClickedOutside(double d, double e, int i, int j, int k) {
        boolean bl = d < (double)i || e < (double)j || d >= (double)(i + this.imageWidth) || e >= (double)(j + this.imageHeight);
        return bl;
    }

}
