package net.firemuffin303.thaidelight.mixin.fabric.itemRender;

import net.minecraft.client.model.HumanoidModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(HumanoidModel.ArmPose.class)
public enum ArmPoseMixin {
    MUFFINS_THAIDELIGHT_SACK_SWING(true),
    MUFFINS_THAIDELIGHT_SACK_HOLD(true),
    MUFFINS_THAIDELIGHT_FULL_SACK_HOLD(false);

    @Shadow
    ArmPoseMixin(boolean bl){

    }
}
