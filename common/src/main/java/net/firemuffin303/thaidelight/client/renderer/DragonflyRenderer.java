package net.firemuffin303.thaidelight.client.renderer;

import net.firemuffin303.thaidelight.client.model.DragonflyModel;
import net.firemuffin303.thaidelight.common.entity.Dragonfly;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DragonflyRenderer extends GeoEntityRenderer<Dragonfly> {
    public DragonflyRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DragonflyModel());
    }
}
