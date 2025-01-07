package net.firemuffin303.muffinsthaidelightfabric.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.firemuffin303.muffinsthaidelightfabric.common.entitydata.SpicyData;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class PlayerSpicyRenderer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {


    public PlayerSpicyRenderer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, AbstractClientPlayer entity, float f, float g, float h, float j, float k, float l) {
        if(!entity.isInvisible()){
            if(((SpicyData.SpicyAccessor)entity).muffinsThaiDelight$access().getSpicyLevel() > 0){
                VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityCutout(entity.getSkinTextureLocation()));
                int m = LivingEntityRenderer.getOverlayCoords(entity, 0.0F);
                float percent = ((SpicyData.SpicyAccessor)entity).muffinsThaiDelight$access().getPercent();

                this.getParentModel().head.render(poseStack,vertexConsumer,i,m,colorCalculate(0.992156862745098f,percent),colorCalculate(0.5568627450980392f,percent),colorCalculate(0.5019607843137255f,percent),1.0f);
                this.getParentModel().hat.render(poseStack,vertexConsumer,i,m,colorCalculate(0.992156862745098f,percent),colorCalculate(0.5568627450980392f,percent),colorCalculate(0.5019607843137255f,percent),1.0f);
            }
        }
    }

    private float colorCalculate(float color, float percent){
        return 1 + (color - 1) * percent;
    }
}
