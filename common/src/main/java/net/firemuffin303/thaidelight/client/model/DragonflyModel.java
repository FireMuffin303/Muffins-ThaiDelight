package net.firemuffin303.thaidelight.client.model;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.entity.Dragonfly;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DragonflyModel extends GeoModel<Dragonfly> {
    @Override
    public ResourceLocation getModelResource(Dragonfly animatable) {
        return new ResourceLocation(ThaiDelight.MOD_ID,"geo/dragonfly.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Dragonfly animatable) {
        return new ResourceLocation(ThaiDelight.MOD_ID,"textures/entity/dragonfly/dragonfly.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Dragonfly animatable) {
        return new ResourceLocation(ThaiDelight.MOD_ID,"animations/dragonfly.animation.json");
    }
}
