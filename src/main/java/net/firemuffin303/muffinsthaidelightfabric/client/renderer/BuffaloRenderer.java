package net.firemuffin303.muffinsthaidelightfabric.client.renderer;

import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.client.model.BuffoloModel;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.BuffaloEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BuffaloRenderer extends MobRenderer<BuffaloEntity, BuffoloModel<BuffaloEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ThaiDelight.MOD_ID,"textures/entity/buffolo/buffolo.png");
    public BuffaloRenderer(EntityRendererProvider.Context context) {
        super(context, new BuffoloModel<>(context.bakeLayer(BuffoloModel.LAYER_LOCATION)), 0.8f);
    }

    @Override
    public ResourceLocation getTextureLocation(BuffaloEntity entity) {
        return TEXTURE;
    }

}
