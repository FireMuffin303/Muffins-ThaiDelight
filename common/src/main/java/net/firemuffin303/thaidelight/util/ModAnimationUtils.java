package net.firemuffin303.thaidelight.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;

public class ModAnimationUtils {
    public static <T extends LivingEntity> void handleCatcherBagHold(HumanoidModel<T> model, boolean isRightHand){
        ModelPart holdingArm = isRightHand ? model.rightArm : model.leftArm;
        ModelPart other = isRightHand ? model.leftArm : model.rightArm;
        holdingArm.xRot = (float) (-Math.PI/2) + 0.5f;
        other.xRot = (float) (-Math.PI/2) + 0.5f;
    }

    public static <T extends LivingEntity> void handleCatcherBagReady(HumanoidModel<T> model,boolean isRightHand){
        ModelPart holdingArm = isRightHand ? model.rightArm : model.leftArm;
        ModelPart other = isRightHand ? model.leftArm : model.rightArm;
        holdingArm.xRot = (float) (-Math.PI/2) + 0.2f;
        other.xRot = (float) (-Math.PI/2) + 0.2f;
    }

    public static <T extends LivingEntity> void handleSackShoulderHold(HumanoidModel<T> model,boolean isRightHand){
        ModelPart holdingArm = isRightHand ? model.rightArm : model.leftArm;
        ModelPart other = isRightHand ? model.leftArm : model.rightArm;
        holdingArm.xRot = (float) (Math.PI);
        holdingArm.zRot =  isRightHand ? -1.35f : 1.35F;
        //other.xRot = (float) (Math.PI/2);
    }

    public static void handleUsingCatchingBag(PoseStack poseStack, HumanoidArm humanoidArm, float i){
        if(humanoidArm == HumanoidArm.RIGHT){
            poseStack.translate(0.56,-0.22 + (i * -0.55f),-0.72F);
        }else{
            poseStack.translate(-0.56,-0.22 + (i * -0.55f),-0.72F);

        }
    }

    public static void sackAttackAnimation(PoseStack poseStack, HumanoidArm humanoidArm, float f){
        int i = humanoidArm == HumanoidArm.RIGHT ? 1 : -1;
        float g = Mth.sin(f * f * (float)Math.PI);
        poseStack.mulPose(Axis.YP.rotationDegrees((float)i * (45.0f + g * -20.0f)));
        float h = Mth.sin(Mth.sqrt(f) * (float)Math.PI);
        poseStack.mulPose(Axis.ZP.rotationDegrees((float)i * h * -10.0f));
        poseStack.mulPose(Axis.XP.rotationDegrees(h * -10.0f));
        poseStack.mulPose(Axis.YP.rotationDegrees((float)i * -15.0f));
        poseStack.translate(0,0,-0.25f);
    }
}
