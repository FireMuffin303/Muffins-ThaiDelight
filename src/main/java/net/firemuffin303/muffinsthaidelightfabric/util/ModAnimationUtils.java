package net.firemuffin303.muffinsthaidelightfabric.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
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

    public static void handleUsingCatchingBag(PoseStack poseStack, HumanoidArm humanoidArm,float i){
        poseStack.translate(0.56,-0.22 + (i * -0.55f),-0.72F);
    }
}
