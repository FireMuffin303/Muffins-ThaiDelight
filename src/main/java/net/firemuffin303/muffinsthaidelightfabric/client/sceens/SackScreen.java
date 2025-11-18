package net.firemuffin303.muffinsthaidelightfabric.client.sceens;

import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.menu.SackMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SackScreen extends AbstractContainerScreen<SackMenu> {
    private static final ResourceLocation CONTAINER_BACKGROUND = ThaiDelight.modid("textures/gui/sack.png");
    int size;

    public SackScreen(SackMenu abstractContainerMenu, Inventory inventory, Component component) {
        super(abstractContainerMenu, inventory, component);
        this.size = abstractContainerMenu.getSize();
        this.imageHeight = 114 +  18;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, i, j, f);
        this.renderFakeItems(guiGraphics,i,j,f);
        this.renderTooltip(guiGraphics, i, j);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float f, int i, int j) {
        int k = (this.width - this.imageWidth) / 2;
        int l = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(CONTAINER_BACKGROUND, k, l, 0, 0, this.imageWidth, this.imageHeight);
    }

    protected void renderFakeItems(GuiGraphics guiGraphics, int i, int j, float f){
        int k = (this.width - this.imageWidth) / 2;
        int l = (this.height - this.imageHeight) / 2;
        ItemStack itemStack = this.menu.firstStack();
        if(!itemStack.isEmpty()){
            for(int m = 1; m < this.size; m++){
                Slot slot = this.menu.getSlot(m);
                if(!slot.hasItem()){
                    int xa = slot.x + k;
                    int ya = slot.y + l;
                    guiGraphics.fill(xa, ya, xa + 16, ya + 16, 0x30FF0000);
                    guiGraphics.renderFakeItem(itemStack,xa,ya);
                    guiGraphics.fill(RenderType.guiGhostRecipeOverlay(), xa, ya, xa + 16, ya + 16, 0x60222222);
                }
            }

        }
    }
}
