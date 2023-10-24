package net.firemuffin303.thaidelight.client.model.blockentity;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.block.entity.MortarBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MortarBlockModel extends GeoModel<MortarBlockEntity> {
    @Override
    public ResourceLocation getModelResource(MortarBlockEntity animatable) {
        return new ResourceLocation(ThaiDelight.MOD_ID,"geo/mortar.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MortarBlockEntity animatable) {
        return new ResourceLocation(ThaiDelight.MOD_ID,"textures/block/mortar.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MortarBlockEntity animatable) {
        return new ResourceLocation(ThaiDelight.MOD_ID,"animations/mortar.animation.json");
    }
}
