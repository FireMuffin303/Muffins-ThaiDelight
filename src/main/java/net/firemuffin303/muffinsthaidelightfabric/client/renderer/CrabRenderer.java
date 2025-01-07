package net.firemuffin303.muffinsthaidelightfabric.client.renderer;

import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.client.model.FlowerCrabModel;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.FlowerCrabEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CrabRenderer extends MobRenderer<FlowerCrabEntity, FlowerCrabModel<FlowerCrabEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ThaiDelight.MOD_ID,"textures/entity/flower_crab/flower_crab.png");


    public CrabRenderer(EntityRendererProvider.Context context) {
        super(context, new FlowerCrabModel<>(context.bakeLayer(FlowerCrabModel.LAYER)), 0.4F);

    }

    @Override
    public ResourceLocation getTextureLocation(FlowerCrabEntity entity) {
        return TEXTURE;
    }
}
