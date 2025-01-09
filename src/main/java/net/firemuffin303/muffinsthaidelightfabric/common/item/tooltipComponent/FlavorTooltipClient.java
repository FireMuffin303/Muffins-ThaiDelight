package net.firemuffin303.muffinsthaidelightfabric.common.item.tooltipComponent;

import net.firemuffin303.muffinsthaidelightfabric.common.data.FlavorItemData;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.joml.Matrix4f;

import java.util.Objects;

public class FlavorTooltipClient implements ClientTooltipComponent {
    private final FlavorItemData flavorItemData;
    public FlavorTooltipClient(FlavorTooltipComponent flavorTooltipComponent){
        this.flavorItemData = flavorTooltipComponent.flavorItemData();
    }

    @Override
    public int getHeight() {
        return 25;
    }

    @Override
    public int getWidth(Font font) {
        return 12;
    }

    @Override
    public void renderText(Font font, int i, int j, Matrix4f matrix4f, MultiBufferSource.BufferSource bufferSource) {
        Integer color = ChatFormatting.GRAY.getColor();
        int gray = color == null ? -1 : color;
        if(this.flavorItemData.getSourLevel() > 0){
            Component component = Component.literal("Sour "+this.flavorItemData.getSourLevel());
            font.drawInBatch(component,i,j, gray,true,matrix4f,bufferSource, Font.DisplayMode.NORMAL,0,15728880);
        }
    }

    public record FlavorTooltipComponent(FlavorItemData flavorItemData) implements TooltipComponent{

        public FlavorTooltipComponent(FlavorItemData flavorItemData){
            this.flavorItemData = flavorItemData;
        }

    }
}
