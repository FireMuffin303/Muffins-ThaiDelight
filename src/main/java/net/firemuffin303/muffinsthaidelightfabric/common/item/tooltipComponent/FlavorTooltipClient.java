package net.firemuffin303.muffinsthaidelightfabric.common.item.tooltipComponent;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.joml.Matrix4f;

import java.util.Objects;

public class FlavorTooltipClient implements ClientTooltipComponent {
    private final boolean sour;
    public FlavorTooltipClient(FlavorTooltipComponent tooltipComponent){
        Objects.requireNonNull(Minecraft.getInstance().font);
        this.sour = tooltipComponent.isSour();
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
        if(this.sour){
            font.drawInBatch("Sour",i,j, gray,true,matrix4f,bufferSource, Font.DisplayMode.NORMAL,0,15728880);
        }
    }

    public record FlavorTooltipComponent(boolean isSour) implements TooltipComponent{
        public static final String FLAVOR_NBT = "Flavors";
        public static final String SOUR_NBT = "Sour";

        public FlavorTooltipComponent(boolean isSour){
            this.isSour = isSour;
        }

    }
}
