package net.firemuffin303.thaidelight.client.model;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.entity.Dragonfly;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;


public class DragonflyModel <T extends Dragonfly> extends HierarchicalModel<T> {
    private final ModelPart body;
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(ThaiDelight.MOD_ID,"dragonfly"),"main");

    public DragonflyModel(ModelPart body) {
        this.body = body;
    }

    @Override
    public ModelPart root() {
        return this.body;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.5F, -4.0F, 4.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.5F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(21, 22).addBox(-2.0F, -6.0F, -7.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.5F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 13).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 5.0F));

        PartDefinition front_leg = body.addOrReplaceChild("front_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 2.5F, -3.0F));

        PartDefinition cube_r1 = front_leg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 4).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition middle_leg = body.addOrReplaceChild("middle_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 2.5F, 0.0F));

        PartDefinition cube_r2 = middle_leg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 2).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition hind_leg = body.addOrReplaceChild("hind_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 2.5F, 3.0F));

        PartDefinition cube_r3 = hind_leg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition left_wing = body.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(12, 4).addBox(0.0F, -1.0F, -2.0F, 12.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, -2.0F));

        PartDefinition left_backwing = body.addOrReplaceChild("left_backwing", CubeListBuilder.create().texOffs(12, 15).addBox(0.0F, -1.0F, -1.0F, 8.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 2.0F));

        PartDefinition right_backwing = body.addOrReplaceChild("right_backwing", CubeListBuilder.create().texOffs(12, 13).addBox(-8.0F, -1.0F, -1.0F, 8.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 2.0F));

        PartDefinition right_fly = body.addOrReplaceChild("right_fly", CubeListBuilder.create().texOffs(12, 0).addBox(-12.0F, -1.0F, -2.0F, 12.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, -2.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }


    @Override
    public void setupAnim(T entity, float f, float g, float h, float i, float j) {

    }
}
