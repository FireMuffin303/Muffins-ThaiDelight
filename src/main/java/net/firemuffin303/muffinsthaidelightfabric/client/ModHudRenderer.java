package net.firemuffin303.muffinsthaidelightfabric.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

@Environment(EnvType.CLIENT)
public class ModHudRenderer {
    private static final ResourceLocation SPICY_OUTLINE_RESOURCE = new ResourceLocation(ThaiDelight.MOD_ID,"textures/misc/spicy_outline.png");
    private static final ResourceLocation NAUSEA_LOCATION = new ResourceLocation("textures/misc/nausea.png");

    public static void init(GuiGraphics guiGraphics, float deltaTracker){
    }

    public static void spicyOutline(GuiGraphics guiGraphics,float deltaTracker){
        Minecraft minecraft = Minecraft.getInstance();

        if(ModComponents.SPICY.get(minecraft.player).getSpicyLevel() > 0){
            renderSpicyOverlay(guiGraphics,deltaTracker,ModComponents.SPICY.get(minecraft.player).getPercent());
        }
    }

    private static void renderSpicyOverlay(GuiGraphics guiGraphics,float delta,float percent){
        int i = guiGraphics.guiWidth();
        int j = guiGraphics.guiHeight();
        guiGraphics.pose().pushPose();

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        float h = 0.5F * percent;
        float k = 0.2F * percent;
        float l = 0.2F * percent;
        guiGraphics.setColor(h,k,l,percent);
        guiGraphics.blit(NAUSEA_LOCATION,0,0,-90,0.f,0.f,i,j,i,j);

        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();

        guiGraphics.pose().popPose();
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
