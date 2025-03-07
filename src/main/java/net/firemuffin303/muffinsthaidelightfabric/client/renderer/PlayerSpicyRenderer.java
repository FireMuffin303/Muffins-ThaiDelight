package net.firemuffin303.muffinsthaidelightfabric.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.firemuffin303.muffinsthaidelightfabric.common.data.SpicyData;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModComponents;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class PlayerSpicyRenderer extends RenderLayer<LivingEntity, EntityModel<LivingEntity>> {
    private final EntityModel<LivingEntity> model;
    private final LivingEntityRenderer<LivingEntity,EntityModel<LivingEntity>> livingEntityRenderer;
    private static final float color[] = {0.9098039215686275f,0.4352941176470588f,0.4235294117647059f};

    public PlayerSpicyRenderer(RenderLayerParent<LivingEntity, EntityModel<LivingEntity>> renderLayerParent, LivingEntityRenderer<LivingEntity,EntityModel<LivingEntity>> livingEntityRenderer) {
        super(renderLayerParent);
        this.model = this.getParentModel();
        this.livingEntityRenderer = livingEntityRenderer;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, LivingEntity entity, float f, float g, float h, float j, float k, float l) {
        if(!entity.isInvisible()){
            if(ModComponents.SPICY.get(entity).getSpicyLevel() > 0){
                ResourceLocation resourceLocation = this.livingEntityRenderer.getTextureLocation(entity);
                VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityTranslucentCull(resourceLocation));

                int m = LivingEntityRenderer.getOverlayCoords(entity, 0.0F);
                float percent = ModComponents.SPICY.get(entity).getPercent() * 0.6f;

                if(this.model instanceof HeadedModel headedModel) {
                    headedModel.getHead().render(poseStack, vertexConsumer, i, m, color[0],color[1],color[2], percent);
                    if(this.model instanceof PlayerModel<LivingEntity> playerModel){
                        playerModel.hat.render(poseStack, vertexConsumer, i, m,  color[0],color[1],color[2], percent);
                    }
                }
            }
        }
    }

    private float colorCalculate(float color, float percent){
        return 1 + (color - 1) * percent;
    }
}
