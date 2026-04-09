package net.firemuffin303.thaidelight.client.model.armor;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class DurianHelmetModel<T extends LivingEntity> extends HumanoidModel<T> {
    public static ModelLayerLocation DURIAN_HELMET = new ModelLayerLocation(ThaiDelightCommon.modid("durian_armor"),"main");

    public DurianHelmetModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static LayerDefinition createLayer(){
        MeshDefinition meshDefinition = HumanoidModel.createMesh(new CubeDeformation(1.0f),0.0f);
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0,0)
                        .addBox(-4F, -8.0F, -5.0F, 8.0F, 8.0F, 10F,new CubeDeformation(0.8f,1f,1.25f))
                        .texOffs(0,18).addBox(-5.0F, -8F, -4.0F, 10.0F, 8F, 8.0F,new CubeDeformation(1f))
                , PartPose.offset(0.0f,0.0f,0.0f));
        partDefinition.addOrReplaceChild("hat",CubeListBuilder.create().texOffs(64,32).addBox(0f,0f,0f,1f,1f,1f),PartPose.ZERO);
        return LayerDefinition.create(meshDefinition,64,64);
    }
}
