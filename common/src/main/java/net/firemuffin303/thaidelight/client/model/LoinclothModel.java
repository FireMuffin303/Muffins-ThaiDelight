package net.firemuffin303.thaidelight.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class LoinclothModel extends HumanoidModel<LivingEntity> {
    private final ModelPart loincloth = body.getChild("loincloth");
    public LoinclothModel(ModelPart modelPart) {
        super(modelPart);
    }

    @Override
    public void prepareMobModel(LivingEntity livingEntity, float f, float g, float h) {
        super.prepareMobModel(livingEntity, f, g, h);
    }
}
