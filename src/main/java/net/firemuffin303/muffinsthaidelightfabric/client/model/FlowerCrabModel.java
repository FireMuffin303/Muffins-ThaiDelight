package net.firemuffin303.muffinsthaidelightfabric.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.FlowerCrabEntity;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class FlowerCrabModel<T extends FlowerCrabEntity> extends HierarchicalModel<T> {
    private final ModelPart body;

    public FlowerCrabModel(ModelPart body) {
        this.body = body;
    }

    public static final ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(ThaiDelight.MOD_ID,"crab"),"main");


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 26).addBox(-5.0F, -6.0F, -7.0F, 10.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-7.0F, -6.0F, -3.0F, 14.0F, 6.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 6).addBox(-2.0F, -3.0F, -7.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 21.0F, 0.0F));

        PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(7.0F, -1.0F, 0.0F));

        PartDefinition cube_r1 = left_leg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(14, 16).addBox(-2.5F, 1.0F, -3.0F, 7.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 1.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

        PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-7.0F, -1.0F, 0.0F));

        PartDefinition cube_r2 = right_leg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 16).addBox(-4.5F, 1.0F, -3.0F, 7.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 1.0F, 0.0F, 0.0F, 0.0F, -0.6981F));

        PartDefinition left_claw = body.addOrReplaceChild("left_claw", CubeListBuilder.create().texOffs(0, 36).addBox(-1.064F, -3.1271F, -3.91F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -2.0F, -4.0F, 0.0436F, -0.3054F, 0.0F));

        PartDefinition left_claw_upper = left_claw.addOrReplaceChild("left_claw_upper", CubeListBuilder.create().texOffs(38, 16).addBox(-5.064F, -2.1271F, -6.91F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -1.0F, -3.0F));

        PartDefinition left_claw_lower = left_claw.addOrReplaceChild("left_claw_lower", CubeListBuilder.create().texOffs(34, 40).addBox(-1.564F, -0.1271F, -5.91F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 1.0F, -4.0F));

        PartDefinition right_claw = body.addOrReplaceChild("right_claw", CubeListBuilder.create().texOffs(28, 26).addBox(-2.8897F, -3.1402F, -4.2104F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -2.0F, -4.0F, 0.0436F, 0.3054F, 0.0F));

        PartDefinition right_claw_upper = right_claw.addOrReplaceChild("right_claw_upper", CubeListBuilder.create().texOffs(38, 0).addBox(-1.8897F, -1.1402F, -5.2104F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -2.0F, -5.0F));

        PartDefinition right_claw_lower = right_claw.addOrReplaceChild("right_claw_lower", CubeListBuilder.create().texOffs(20, 38).addBox(-2.3897F, -0.1402F, -5.2104F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 1.0F, -5.0F));

        PartDefinition eyes = body.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -12.0F, -8.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(2.0F, -12.0F, -8.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }


    @Override
    public ModelPart root() {
        return this.body;
    }

    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of();
    }

    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body);
    }


    @Override
    public void setupAnim(FlowerCrabEntity entity, float f, float g, float h, float i, float j) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animateWalk(CrabAnimation.WALK,f,g,9.0f,100f);

        this.animate(entity.idleAnimationState, CrabAnimation.IDLE, h, 1.0F);
        this.animate(entity.danceAnimationState,CrabAnimation.DANCE,h,1.0f);

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
        if (this.young) {
            poseStack.pushPose();
            poseStack.scale(0.45F, 0.45F, 0.45F);
            poseStack.translate(0.0F, 1.834375F, 0.0F);
            this.root().render(poseStack, vertexConsumer, i, j, f, g, h, k);
            poseStack.popPose();
        } else {
            this.root().render(poseStack, vertexConsumer, i, j, f, g, h, k);
        }
    }

    public static class CrabAnimation{

        public static final AnimationDefinition IDLE = AnimationDefinition.Builder.withLength(2f)
                .addAnimation("body",
                        new AnimationChannel(AnimationChannel.Targets.POSITION,
                                new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.CATMULLROM),
                                new Keyframe(1f, KeyframeAnimations.posVec(0f, -1f, 0f),
                                        AnimationChannel.Interpolations.CATMULLROM),
                                new Keyframe(2f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.CATMULLROM)))
                .addAnimation("left_leg",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.CATMULLROM),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, -10f),
                                        AnimationChannel.Interpolations.CATMULLROM),
                                new Keyframe(2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.CATMULLROM)))
                .addAnimation("right_leg",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.CATMULLROM),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 10f),
                                        AnimationChannel.Interpolations.CATMULLROM),
                                new Keyframe(2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.CATMULLROM)))
                .addAnimation("left_claw_lower",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.CATMULLROM),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(12.5f, 0f, 0f),
                                        AnimationChannel.Interpolations.CATMULLROM),
                                new Keyframe(2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.CATMULLROM)))
                .addAnimation("right_claw_lower",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.CATMULLROM),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(12.5f, 0f, 0f),
                                        AnimationChannel.Interpolations.CATMULLROM),
                                new Keyframe(2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.CATMULLROM))).build();

        public static final AnimationDefinition WALK = AnimationDefinition.Builder.withLength(0.25f).looping()
                .addAnimation("body",
                        new AnimationChannel(AnimationChannel.Targets.POSITION,
                                new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.125f, KeyframeAnimations.posVec(0f, -0.5f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("body",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -90f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("left_leg",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 10f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.125f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 10f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("right_leg",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.125f, KeyframeAnimations.degreeVec(0f, 0f, -10f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR))).build();

        public static final AnimationDefinition DANCE = AnimationDefinition.Builder.withLength(1f).looping()
                .addAnimation("body",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(-15.22f, 9.66f, -2.61f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.25f, KeyframeAnimations.degreeVec(2.28f, 9.66f, -2.61f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.5f, KeyframeAnimations.degreeVec(-15.22f, -9.66f, 2.61f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.75f, KeyframeAnimations.degreeVec(1.37f, -10.49f, 1.68f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(-15.22f, 9.66f, -2.61f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("left_leg",
                        new AnimationChannel(AnimationChannel.Targets.POSITION,
                                new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, 1f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, -1f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.75f, KeyframeAnimations.posVec(0f, 0f, -0.5f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 1f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("left_leg",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(15.35f, -12.07f, -3.28f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.25f, KeyframeAnimations.degreeVec(0.43f, -8.79f, 0.27f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.5f, KeyframeAnimations.degreeVec(15.12f, 7.24f, 1.95f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.75f, KeyframeAnimations.degreeVec(-2.44f, 12.49f, -0.53f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(15.22f, -9.66f, -2.61f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("right_leg",
                        new AnimationChannel(AnimationChannel.Targets.POSITION,
                                new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, -1f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 2f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.75f, KeyframeAnimations.posVec(0f, 0f, 1.5f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, -1f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("right_leg",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(15.35f, -12.07f, -3.28f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.25f, KeyframeAnimations.degreeVec(0.43f, -8.79f, 0.27f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.5f, KeyframeAnimations.degreeVec(15.12f, 7.24f, 1.95f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.75f, KeyframeAnimations.degreeVec(-2.44f, 12.49f, -0.53f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(15.22f, -9.66f, -2.61f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("left_claw",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(-61.03f, 15.21f, 3.8f),
                                        AnimationChannel.Interpolations.CATMULLROM),
                                new Keyframe(0.25f, KeyframeAnimations.degreeVec(5.4f, 9.67f, 9.48f),
                                        AnimationChannel.Interpolations.CATMULLROM),
                                new Keyframe(0.5f, KeyframeAnimations.degreeVec(-72.75f, -10.73f, 13.03f),
                                        AnimationChannel.Interpolations.CATMULLROM),
                                new Keyframe(0.7916766f, KeyframeAnimations.degreeVec(11.46f, -25.86f, 18.42f),
                                        AnimationChannel.Interpolations.CATMULLROM),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(-61.03f, 15.21f, 3.8f),
                                        AnimationChannel.Interpolations.CATMULLROM)))
                .addAnimation("left_claw_lower",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.25f, KeyframeAnimations.degreeVec(25f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.75f, KeyframeAnimations.degreeVec(25f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("right_claw",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(-65.32f, 27.61f, -16.98f),
                                        AnimationChannel.Interpolations.CATMULLROM),
                                new Keyframe(0.25f, KeyframeAnimations.degreeVec(-5.99f, 1.4f, -13.59f),
                                        AnimationChannel.Interpolations.CATMULLROM),
                                new Keyframe(0.5f, KeyframeAnimations.degreeVec(-76.65f, -24.8f, -10.21f),
                                        AnimationChannel.Interpolations.CATMULLROM),
                                new Keyframe(0.7916766f, KeyframeAnimations.degreeVec(4.66f, 0.39f, -21.66f),
                                        AnimationChannel.Interpolations.CATMULLROM),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(-65.32f, 27.61f, -16.98f),
                                        AnimationChannel.Interpolations.CATMULLROM)))
                .addAnimation("right_claw_lower",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.25f, KeyframeAnimations.degreeVec(25f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.75f, KeyframeAnimations.degreeVec(25f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR))).build();
    }
}
