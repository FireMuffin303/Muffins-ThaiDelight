package net.firemuffin303.muffinsthaidelightfabric.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.entitydata.SpicyData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

@Environment(EnvType.CLIENT)
public class ModHudRenderer {
    private static final ResourceLocation SPICY_OUTLINE_RESOURCE = new ResourceLocation(ThaiDelight.MOD_ID,"textures/misc/spicy_outline.png");

    public static void init(GuiGraphics guiGraphics, float deltaTracker){
        spicyOutline(guiGraphics,deltaTracker);
    }

    public static void spicyOutline(GuiGraphics guiGraphics,float deltaTracker){
        Minecraft minecraft = Minecraft.getInstance();
        SpicyData spicyData = ((SpicyData.SpicyAccessor) minecraft.player).muffinsThaiDelight$access();
        if(spicyData.getSpicyLevel() > 0){
            renderTextureOverlay(guiGraphics, SPICY_OUTLINE_RESOURCE,spicyData.getPercent());
        }
    }

    private static void renderTextureOverlay(GuiGraphics guiGraphics, ResourceLocation resourceLocation, float f) {

        guiGraphics.pose().pushPose();

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();

        float g = Mth.lerp(f, 2.0F, 1.0F);

        guiGraphics.pose().translate((float)guiGraphics.guiWidth() / 2.0F, (float)guiGraphics.guiHeight() / 2.0F, 0.0F);
        guiGraphics.pose().scale(g, g, g);
        guiGraphics.pose().translate((float)(-guiGraphics.guiWidth()) / 2.0F, (float)(-guiGraphics.guiHeight()) / 2.0F, 0.0F);
        float h = 0.2F * f;
        float k = 0.4F * f;
        float l = 0.2F * f;
        guiGraphics.setColor(h, k, l, f);
        guiGraphics.blit(resourceLocation, 0, 0, -90, 0.0F, 0.0F, guiGraphics.guiWidth(), guiGraphics.guiHeight(), guiGraphics.guiWidth(), guiGraphics.guiHeight());
        RenderSystem.setShader(GameRenderer::getRendertypeEndPortalShader);
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);

        guiGraphics.pose().popPose();
    }
}
