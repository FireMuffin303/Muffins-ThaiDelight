package net.firemuffin303.muffinsthaidelightfabric.client.model;

import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.BuffaloEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BuffoloModel <T extends BuffaloEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ThaiDelight.MOD_ID, "buffolo"), "main");
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart body2;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;

    public BuffoloModel(ModelPart root){
        this.body = root.getChild("root");
        this.head = this.body.getChild("head");
        this.body2 = this.body.getChild("body");
        this.rightHindLeg = this.body.getChild("hind_right_leg");
        this.leftHindLeg = this.body.getChild("hind_left_leg");
        this.rightFrontLeg = this.body.getChild("front_right_leg");
        this.leftFrontLeg = this.body.getChild("front_left_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone2 = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, -8.0F));

        PartDefinition head2 = bone2.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 29).addBox(-3.5F, -2.0F, -10.0F, 7.0F, 6.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(46, 0).addBox(9.5F, -2.0F, -3.0F, 3.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(46, 20).addBox(3.5F, -2.0F, -3.0F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(46, 10).addBox(-12.5F, -2.0F, -3.0F, 3.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(50, 26).addBox(-9.5F, -2.0F, -3.0F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(50, 39).addBox(3.5F, 1.0F, -2.2F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 39).addBox(-6.5F, 1.0F, -2.2F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition body2 = bone2.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -10.0F, -6.0F, 12.0F, 18.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(50, 32).addBox(-2.0F, 2.0F, -7.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 10.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition leg5 = bone2.addOrReplaceChild("hind_right_leg", CubeListBuilder.create().texOffs(34, 29).addBox(-2.0F, 1.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 8.0F, 15.0F));

        PartDefinition leg6 = bone2.addOrReplaceChild("hind_left_leg", CubeListBuilder.create().texOffs(34, 44).addBox(-2.0F, 1.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 8.0F, 15.0F));

        PartDefinition leg7 = bone2.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(0, 45).addBox(-2.0F, 1.0F, -1.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 8.0F, 2.0F));

        PartDefinition leg8 = bone2.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(16, 45).addBox(-2.0F, 1.0F, -1.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 8.0F, 2.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public ModelPart root() {
        return this.body;
    }

    public ModelPart getHead() {
        return head;
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
