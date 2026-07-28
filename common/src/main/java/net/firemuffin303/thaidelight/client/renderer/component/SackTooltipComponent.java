package net.firemuffin303.thaidelight.client.renderer.component;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SackTooltipComponent implements ClientTooltipComponent {
    private final NonNullList<ItemStack> items;
    private final List<ItemStack> itemTypes = new ArrayList<>();

    public SackTooltipComponent(SackToolTip sackToolTip){
        this.items = sackToolTip.itemStacks;

        ItemStack showedItem = ItemStack.EMPTY;
        for(ItemStack itemStack : this.items){
            if (ItemStack.isSameItemSameComponents(itemStack, showedItem) || itemStack.isEmpty()) continue;
            showedItem = itemStack;
            itemTypes.add(itemStack);
        }
    }

    @Override
    public int getHeight() {
        if(this.items.stream().allMatch(ItemStack::isEmpty) ){
            return Minecraft.getInstance().font.lineHeight + 1;
        }

        return itemTypes.size() <= 1 ? 18 : 28 ;
    }

    @Override
    public int getWidth(Font font) {
        if(this.items.stream().allMatch(ItemStack::isEmpty)){
            return font.width("Empty");
        }

        int amount = 0;
        int maxAmount = 0;
        for (ItemStack item : this.items) {
            amount += item.getCount();
            maxAmount += item.getMaxStackSize();
        }


        int width = font.width("%d / %d".formatted(amount,maxAmount));

        return itemTypes.size() <= 1 ? width + 26 : width;
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics) {
        if(this.items.stream().allMatch(ItemStack::isEmpty)){
            guiGraphics.drawString(font, Component.literal("Empty").withStyle(ChatFormatting.GRAY),x,y,0);
            return;
        }

        int amount = 0;
        int maxAmount = 0;
        for (int i = 0 ; i < this.itemTypes.size() ; i++) {
            guiGraphics.renderItem(this.items.get(i), x + (i * 16), y, 0);
        }

        for (ItemStack item : this.items) {
            amount += item.getCount();
            maxAmount += item.getMaxStackSize();
        }

        int amount_text_y = this.itemTypes.size() <= 1 ? 4 : 18;

        guiGraphics.drawString(font,Component.literal("%d / %d".formatted(amount,maxAmount)).withStyle(ChatFormatting.GRAY),x + 23,y + amount_text_y,0);

    }

    public static record SackToolTip(NonNullList<ItemStack> itemStacks) implements TooltipComponent{

    }
}
