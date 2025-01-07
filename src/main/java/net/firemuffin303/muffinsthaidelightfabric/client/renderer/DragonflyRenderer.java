package net.firemuffin303.muffinsthaidelightfabric.client.renderer;

import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.client.model.DragonflyModel;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.DragonflyEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DragonflyRenderer extends MobRenderer<DragonflyEntity, DragonflyModel<DragonflyEntity>> {


    public DragonflyRenderer(EntityRendererProvider.Context context) {
        super(context, new DragonflyModel<>(context.bakeLayer(DragonflyModel.LAYER)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(DragonflyEntity entity) {
        return new ResourceLocation(ThaiDelight.MOD_ID,String.format("textures/entity/dragonfly/%s.png",entity.getVariant().getName()));
    }
}
