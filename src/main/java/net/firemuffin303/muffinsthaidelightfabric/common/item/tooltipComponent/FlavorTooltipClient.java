package net.firemuffin303.muffinsthaidelightfabric.common.item.tooltipComponent;

import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.manager.FlavorManager;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import java.util.List;

public class FlavorTooltipClient implements ClientTooltipComponent {
    private static final ResourceLocation FLAVOR_TEXTURE = ThaiDelight.modid("textures/gui/flavor.png");
    private final ListTag flavorItemData;
    public FlavorTooltipClient(FlavorTooltipComponent flavorTooltipComponent){
        this.flavorItemData = flavorTooltipComponent.flavor;
    }

    @Override
    public int getHeight() {
        return 12;
    }

    @Override
    public int getWidth(Font font) {
        return 12;
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics) {
        int afterX = 0;
        for(int i = 0 ; i < this.flavorItemData.size(); i++){
            int u;
            CompoundTag compoundTag = this.flavorItemData.getCompound(i);
            u = switch (compoundTag.getString("id")){
                case "sour" -> 8;
                case "spicy" -> 16;
                case "salt" -> 24;
                case "sweet" -> 32;
                default -> 0;
            };

            for (int j = 0 ;j < compoundTag.getInt("level");j++){
                guiGraphics.blit(FLAVOR_TEXTURE,x + (afterX * 8),y,u,0,8,8,40,8);
                afterX++;
            }
        }
    }

    public record FlavorTooltipComponent(ListTag flavor) implements TooltipComponent{

        public FlavorTooltipComponent(ListTag flavor){
            this.flavor = flavor;
        }

    }
}
