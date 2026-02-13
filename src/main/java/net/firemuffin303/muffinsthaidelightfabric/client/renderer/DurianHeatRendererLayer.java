package net.firemuffin303.muffinsthaidelightfabric.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class DurianHeatRendererLayer<T extends LivingEntity,M extends EntityModel<T> & HeadedModel> extends RenderLayer<T,M> {
    public static final ResourceLocation RED_CHEEK = ThaiDelight.modid("textures/entity/player/red_cheek.png");

    public DurianHeatRendererLayer(RenderLayerParent<T, M> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, T entity, float f, float g, float h, float j, float k, float l) {
        poseStack.pushPose();

        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityCutout(RED_CHEEK));

        ModelPart modelPart = this.getParentModel().getHead();
        float n = 1.1f;
        poseStack.scale(n, n, n);
        modelPart.render(poseStack,vertexConsumer,i, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();
    }
}
