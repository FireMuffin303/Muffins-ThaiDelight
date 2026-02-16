package net.firemuffin303.muffinsthaidelightfabric.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.attachments.DurianHeatAttachment;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModAttachments;
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
import net.minecraft.world.entity.player.Player;

public class DurianHeatRendererLayer<T extends LivingEntity,M extends EntityModel<T> & HeadedModel> extends RenderLayer<T,M> {
    public static final ResourceLocation RED_CHEEK = ThaiDelight.modid("textures/entity/player/red_cheek.png");

    public DurianHeatRendererLayer(RenderLayerParent<T, M> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, T entity, float f, float g, float h, float j, float k, float l) {
        if(entity instanceof Player player) {
            DurianHeatAttachment durianHeatAttachment = player.getAttached(ModAttachments.DURIAN_HEAT);
            if (durianHeatAttachment.isHeatedUp) {
                poseStack.pushPose();

                VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityCutout(RED_CHEEK));

                ModelPart modelPart = this.getParentModel().getHead();
                float n = 1.1f;
                poseStack.scale(n, n, n);
                modelPart.render(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);

                poseStack.popPose();
            }
        }
    }
}
