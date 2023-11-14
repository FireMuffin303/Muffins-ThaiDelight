package net.firemuffin303.thaidelight.client.renderer;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.client.model.DragonflyModel;
import net.firemuffin303.thaidelight.client.model.FlowerCrabModel;
import net.firemuffin303.thaidelight.common.entity.Dragonfly;
import net.firemuffin303.thaidelight.common.entity.FlowerCrabEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DragonflyRenderer extends MobRenderer<Dragonfly, DragonflyModel<Dragonfly>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ThaiDelight.MOD_ID,"textures/entity/dragonfly/dragonfly.png");

    public DragonflyRenderer(EntityRendererProvider.Context context) {
        super(context, new DragonflyModel<>(context.bakeLayer(DragonflyModel.LAYER)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(Dragonfly entity) {
        return TEXTURE;
    }
}
