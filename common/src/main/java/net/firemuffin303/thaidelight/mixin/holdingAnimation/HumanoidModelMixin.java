package net.firemuffin303.thaidelight.mixin.holdingAnimation;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.thaidelight.common.item.SackItem;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.util.ModAnimationUtils;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public abstract class HumanoidModelMixin {

    @Shadow public HumanoidModel.ArmPose rightArmPose;

    @Shadow public HumanoidModel.ArmPose leftArmPose;

    @Inject(method = "poseRightArm",at = @At("TAIL"))
    public <T extends LivingEntity> void muffins$poseRightArm(T livingEntity, CallbackInfo ci){
        HumanoidModel<T> humanoidModel = (HumanoidModel<T>) (Object)this;
        if(this.rightArmPose == PlatformUtil.getDurianCatcherHoldArmPose()){
            ModAnimationUtils.handleCatcherBagHold(humanoidModel,true);

        } else if (this.rightArmPose == PlatformUtil.getDurianCatcherSwingArmPose()) {
            ModAnimationUtils.handleCatcherBagReady(humanoidModel,true);

        } else if (this.rightArmPose == PlatformUtil.getSackShoulderPose()) {
            ModAnimationUtils.handleSackShoulderHold(humanoidModel,true);
        }
    }

    @Inject(method = "poseLeftArm",at = @At("TAIL"))
    public <T extends LivingEntity> void muffins$poseLeftArm(T livingEntity,CallbackInfo ci){
        HumanoidModel<T> humanoidModel = (HumanoidModel<T>) (Object)this;
        if(this.leftArmPose == PlatformUtil.getDurianCatcherHoldArmPose()){
            ModAnimationUtils.handleCatcherBagHold(humanoidModel,false);
        } else if (this.leftArmPose == PlatformUtil.getDurianCatcherSwingArmPose()) {
            ModAnimationUtils.handleCatcherBagReady(humanoidModel,false);

        } else if (this.leftArmPose == PlatformUtil.getSackShoulderPose()) {
            ModAnimationUtils.handleSackShoulderHold(humanoidModel,false);
        }
    }

    @ModifyVariable(method = "setupAttackAnimation",at = @At(value = "STORE"), ordinal = 2)
    public <T extends LivingEntity> float muffins$sackAttackAnimationSetupX(float original, @Local(argsOnly = true) T livingEntity){
        ItemStack mainHandItem = livingEntity.getMainHandItem();
        if(mainHandItem.is(ModItems.SACK.get()) && SackItem.isFull(mainHandItem)){
            return -original;
        }

        return original;
    }

    @ModifyConstant(method = "setupAttackAnimation",constant = @Constant(floatValue = 2.0f))
    public <T extends LivingEntity> float muffins$sackAttackAnimationSetupY(float original, @Local(argsOnly = true) T livingEntity){
        ItemStack mainHandItem = livingEntity.getMainHandItem();
        if(mainHandItem.is(ModItems.SACK.get()) && SackItem.isFull(mainHandItem)){
            return -original;
        }

        return original;
    }

    @ModifyExpressionValue(method = "setupAttackAnimation",at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;sin(F)F",ordinal = 5))
    public <T extends LivingEntity> float muffins$sackAttackAnimationSetupZ(float original, @Local(argsOnly = true) T livingEntity){
        ItemStack mainHandItem = livingEntity.getMainHandItem();
        if(mainHandItem.is(ModItems.SACK.get()) && SackItem.isFull(mainHandItem)){
            return -original;
        }

        return original;
    }
}
