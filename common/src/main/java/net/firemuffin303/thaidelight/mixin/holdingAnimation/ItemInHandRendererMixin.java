package net.firemuffin303.thaidelight.mixin.holdingAnimation;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import net.firemuffin303.thaidelight.common.item.SackItem;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {

    @Inject(method = "renderArmWithItem",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getUseAnimation()Lnet/minecraft/world/item/UseAnim;"))
    public void muffins$UsingBag(AbstractClientPlayer abstractClientPlayer,
                                 float f, float g,
                                 InteractionHand interactionHand, float h,
                                 ItemStack itemStack, float i, PoseStack poseStack, MultiBufferSource multiBufferSource,
                                 int j, CallbackInfo ci, @Local HumanoidArm humanoidArm){
        if(itemStack.getUseAnimation() == ModASMEarlyRiser.getDurianCatcherUseAnim()){
            ModAnimationUtils.handleUsingCatchingBag(poseStack,humanoidArm,i);
        }
    }

    @ModifyReturnValue(method = "evaluateWhichHandsToRender",at = @At(value = "RETURN",ordinal = 0))
    private static ItemInHandRenderer.HandRenderSelection muffins$renderHands(ItemInHandRenderer.HandRenderSelection original, @Local(ordinal = 0) ItemStack itemStack, @Local(ordinal = 1) ItemStack itemStack2){
        if(itemStack.isEmpty() && itemStack2.is(ModItems.SACK)){
            return ItemInHandRenderer.HandRenderSelection.RENDER_OFF_HAND_ONLY;
        }else if((itemStack.is(ModItems.SACK) && !SackItem.isFull(itemStack)) && (itemStack2.is(ModItems.SACK) && !SackItem.isFull(itemStack2)) ){
            return ItemInHandRenderer.HandRenderSelection.RENDER_MAIN_HAND_ONLY;
        }
        return original;
    }

    @WrapWithCondition(method = "renderArmWithItem",
            at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;applyItemArmAttackTransform(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/entity/HumanoidArm;F)V",
            ordinal = 1))

    public boolean muffins$sackFPSAttackAnimation(ItemInHandRenderer instance, PoseStack poseStack, HumanoidArm humanoidArm, float f,@Local(argsOnly = true) ItemStack itemStack){
        if(itemStack.is(ModItems.SACK.get()) && SackItem.isFull(itemStack)){
            ModAnimationUtils.sackAttackAnimation(poseStack, humanoidArm, f);
            return false;
        }
        return true;
    }
}
