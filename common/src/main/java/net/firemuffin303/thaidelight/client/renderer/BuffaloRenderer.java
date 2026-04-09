package net.firemuffin303.thaidelight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.client.model.entity.BabyBuffoloModel;
import net.firemuffin303.thaidelight.client.model.entity.BuffoloModel;
import net.firemuffin303.thaidelight.common.entity.BuffaloEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BuffaloRenderer extends MobRenderer<BuffaloEntity, BuffoloModel<BuffaloEntity>> {
    private static final ResourceLocation TEXTURE = ThaiDelightCommon.modid("textures/entity/buffolo/buffolo.png");
    private final BabyBuffoloModel<BuffaloEntity> baby;
    public BuffaloRenderer(EntityRendererProvider.Context context) {
        super(context, new BuffoloModel<>(context.bakeLayer(BuffoloModel.LAYER_LOCATION)), 0.8f);
        this.baby = new BabyBuffoloModel<>(context.bakeLayer(BabyBuffoloModel.BABY_LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(BuffaloEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BuffaloEntity mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if(this.model.young){
            //this.model = this.baby;
        }
        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
}
