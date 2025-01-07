package net.firemuffin303.muffinsthaidelightfabric.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.DragonflyEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class DragonflyModel <T extends DragonflyEntity> extends HierarchicalModel<T> {
    private final ModelPart body;
    private final ModelPart leftFrontWing;
    private final ModelPart rightFrontWing;
    private final ModelPart leftBackWing;
    private final ModelPart rightBackWing;
    private final ModelPart frontLeg;
    private final ModelPart midLeg;
    private final ModelPart hindLeg;
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(ThaiDelight.MOD_ID,"dragonfly"),"main");


    public DragonflyModel(ModelPart body) {
        this.body = body.getChild("body");
        this.leftFrontWing = this.body.getChild("left_frontwing");
        this.rightFrontWing = this.body.getChild("right_frontwing");
        this.leftBackWing = this.body.getChild("left_backwing");
        this.rightBackWing = this.body.getChild("right_backwing");
        this.frontLeg = this.body.getChild("front_leg");
        this.midLeg = this.body.getChild("middle_leg");
        this.hindLeg = this.body.getChild("hind_leg");
    }

    @Override
    public ModelPart root() {
        return this.body;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 12).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 0.0F));

        PartDefinition head = Body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 24).addBox(-5.0F, -6.0F, -7.0F, 10.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 0.0F));

        PartDefinition tail = Body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 5.0F));

        PartDefinition cube_r1 = tail.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition front_leg = Body.addOrReplaceChild("front_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, -3.0F));

        PartDefinition cube_r2 = front_leg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 12).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition middle_leg = Body.addOrReplaceChild("middle_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 0.0F));

        PartDefinition cube_r3 = middle_leg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 10).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition hind_leg = Body.addOrReplaceChild("hind_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 3.0F));

        PartDefinition cube_r4 = hind_leg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 8).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition left_wing = Body.addOrReplaceChild("left_frontwing", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -2.0F));

        PartDefinition left_backwing = Body.addOrReplaceChild("left_backwing", CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, 0.0F, -2.0F, 8.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 2.0F));

        PartDefinition right_backwing = Body.addOrReplaceChild("right_backwing", CubeListBuilder.create().texOffs(10, 8).addBox(-8.0F, 0.0F, -2.0F, 8.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 2.0F));

        PartDefinition right_fly = Body.addOrReplaceChild("right_frontwing", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -2.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }


    @Override
    public void setupAnim(T entity, float f, float g, float h, float i, float j) {
        float k = h * 120.32113F * 0.017453292F;
        this.rightFrontWing.yRot = 0.0F;
        this.rightFrontWing.zRot = Mth.cos(k) * 3.1415927F * 0.15F;
        this.leftFrontWing.xRot = this.rightFrontWing.xRot;
        this.leftFrontWing.yRot = this.rightFrontWing.yRot;
        this.leftFrontWing.zRot = -this.rightFrontWing.zRot;

        this.rightBackWing.yRot = 0.0f;
        this.rightBackWing.zRot = -Mth.cos(k) * 3.1415927F * 0.15F;
        this.leftBackWing.xRot = this.rightBackWing.xRot;
        this.leftBackWing.yRot = this.rightBackWing.yRot;
        this.leftBackWing.zRot = -this.rightBackWing.zRot;

        this.body.xRot = 0.0F;
        this.body.yRot = 0.0F;
        this.body.zRot = 0.0F;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
        if (this.young) {
            poseStack.pushPose();
            poseStack.scale(0.5f,0.5f,0.5f);
            poseStack.translate(0.0F, 1.5f, 0.0F);
            this.root().render(poseStack, vertexConsumer, i, j, f, g, h, k);
            poseStack.popPose();
        } else {
            this.root().render(poseStack, vertexConsumer, i, j, f, g, h, k);
        }

    }
}
