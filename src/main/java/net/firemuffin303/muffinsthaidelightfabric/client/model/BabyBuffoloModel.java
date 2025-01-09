package net.firemuffin303.muffinsthaidelightfabric.client.model;

import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.BuffaloEntity;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BabyBuffoloModel<T extends BuffaloEntity> extends HierarchicalModel<T> implements HeadedModel {
    public static final ModelLayerLocation BABY_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ThaiDelight.MOD_ID, "buffolo"), "baby");
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart body2;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;

    public BabyBuffoloModel(ModelPart root){
        this.body = root.getChild("root");
        this.head = this.body.getChild("head");
        this.body2 = this.body.getChild("body");
        this.rightHindLeg = this.body.getChild("hind_right_leg");
        this.leftHindLeg = this.body.getChild("hind_left_leg");
        this.rightFrontLeg = this.body.getChild("front_right_leg");
        this.leftFrontLeg = this.body.getChild("front_left_leg");
    }

    public static LayerDefinition createBabyBodyLayer(){
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone3 = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        bone3.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 14).addBox(6.0F, -4.0F, -6.5F, 4.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(16, 26).addBox(5.0F, -3.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 28).addBox(10.0F, -3.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(30, 0).addBox(4.0F, -2.0F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(30, 1).addBox(10.0F, -2.0F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -9.0F, -4.0F, 0.3054F, 0.0F, 0.0F));

        bone3.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.5F, -4.5F, 6.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -8.5F, 0.5F));

        bone3.addOrReplaceChild("hind_right_leg", CubeListBuilder.create().texOffs(8, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -6.0F, 4.0F));
        bone3.addOrReplaceChild("hind_left_leg", CubeListBuilder.create().texOffs(0, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -6.0F, 4.0F));
        bone3.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(22, 22).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -6.0F, -3.0F));
        bone3.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(22, 14).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -6.0F, -3.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public ModelPart getHead() {
        return this.head;
    }

    @Override
    public ModelPart root() {
        return this.body;
    }

    @Override
    public void setupAnim(T entity, float f, float g, float h, float i, float j) {
        this.head.xRot = j * 0.013453292F;
        this.head.yRot = i * 0.017453292F;
        this.rightHindLeg.xRot = Mth.cos(f * 0.6662F) * 1.4F * g;
        this.leftHindLeg.xRot = Mth.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
        this.rightFrontLeg.xRot = Mth.cos(f * 0.6662F + 3.1415927F) * 1.4F * g;
        this.leftFrontLeg.xRot = Mth.cos(f * 0.6662F) * 1.4F * g;
    }
}
