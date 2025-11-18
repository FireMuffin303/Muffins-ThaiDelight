package net.firemuffin303.muffinsthaidelightfabric.client.renderer.component;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SackTooltipComponent implements ClientTooltipComponent {
    private final Item item;
    private final int amount;

    public SackTooltipComponent(SackToolTip sackToolTip){
        this.item = sackToolTip.item;
        this.amount = sackToolTip.amount;
    }

    @Override
    public int getHeight() {
        return  20 ;
    }

    @Override
    public int getWidth(Font font) {
        return 20;
    }

    @Override
    public void renderImage(Font font, int i, int j, GuiGraphics guiGraphics) {
        ItemStack itemStack = new ItemStack(this.item,this.amount);
        guiGraphics.renderItem(itemStack,i,j,0);
        guiGraphics.renderItemDecorations(font,itemStack,i,j);
    }

    public static record SackToolTip(Item item, int amount) implements TooltipComponent{

    }
}
