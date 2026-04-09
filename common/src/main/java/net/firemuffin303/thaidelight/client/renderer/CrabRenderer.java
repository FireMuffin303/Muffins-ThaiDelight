package net.firemuffin303.thaidelight.client.renderer;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.client.ThaiDelightCommonClient;
import net.firemuffin303.thaidelight.client.model.entity.FlowerCrabModel;
import net.firemuffin303.thaidelight.common.entity.FlowerCrabEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CrabRenderer extends MobRenderer<FlowerCrabEntity, FlowerCrabModel<FlowerCrabEntity>> {
    private static final ResourceLocation TEXTURE = ThaiDelightCommon.modid("textures/entity/flower_crab/flower_crab.png");


    public CrabRenderer(EntityRendererProvider.Context context) {
        super(context, new FlowerCrabModel<>(context.bakeLayer(FlowerCrabModel.LAYER)), 0.4F);

    }

    @Override
    public ResourceLocation getTextureLocation(FlowerCrabEntity entity) {
        return TEXTURE;
    }
}
