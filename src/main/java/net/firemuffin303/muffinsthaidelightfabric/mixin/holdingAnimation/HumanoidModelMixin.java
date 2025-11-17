package net.firemuffin303.muffinsthaidelightfabric.mixin.holdingAnimation;

import net.firemuffin303.muffinsthaidelightfabric.util.ModASMEarlyRiser;
import net.firemuffin303.muffinsthaidelightfabric.util.ModAnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public abstract class HumanoidModelMixin {

    @Shadow public HumanoidModel.ArmPose rightArmPose;

    @Shadow public HumanoidModel.ArmPose leftArmPose;

    @Inject(method = "poseRightArm",at = @At("TAIL"))
    public <T extends LivingEntity> void muffins$poseRightArm(T livingEntity, CallbackInfo ci){
        HumanoidModel<T> humanoidModel = (HumanoidModel<T>) (Object)this;
        if(this.rightArmPose == ModASMEarlyRiser.getDurianCatcherHoldArmPose()){
            ModAnimationUtils.handleCatcherBagHold(humanoidModel,true);
        } else if (this.rightArmPose == ModASMEarlyRiser.getDurianCatcherSwingArmPose()) {
            ModAnimationUtils.handleCatcherBagReady(humanoidModel,true);
        }
    }

    @Inject(method = "poseLeftArm",at = @At("TAIL"))
    public <T extends LivingEntity> void muffins$poseLeftArm(T livingEntity,CallbackInfo ci){
        HumanoidModel<T> humanoidModel = (HumanoidModel<T>) (Object)this;
        if(this.leftArmPose == ModASMEarlyRiser.getDurianCatcherHoldArmPose()){
            ModAnimationUtils.handleCatcherBagHold(humanoidModel,false);
        } else if (this.leftArmPose == ModASMEarlyRiser.getDurianCatcherSwingArmPose()) {
            ModAnimationUtils.handleCatcherBagReady(humanoidModel,false);
        }
    }
}
