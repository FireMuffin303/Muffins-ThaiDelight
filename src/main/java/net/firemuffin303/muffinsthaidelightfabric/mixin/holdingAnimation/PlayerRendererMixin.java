package net.firemuffin303.muffinsthaidelightfabric.mixin.holdingAnimation;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.firemuffin303.muffinsthaidelightfabric.util.ModASMEarlyRiser;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin {

    @Inject(method = "getArmPose",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z"))
    private static void muffins$shareItemBecauseOrdinal9CantDetectItemStackForSomeReason(
            AbstractClientPlayer abstractClientPlayer,
            InteractionHand interactionHand,
            CallbackInfoReturnable<HumanoidModel.ArmPose> cir,
            @Local ItemStack itemStack,
            @Share("shareItemStack") LocalRef<ItemStack> itemStackLocalRef
            ){
            itemStackLocalRef.set(itemStack);
    }


    @Inject(method = "getArmPose",at = @At(value = "RETURN",ordinal = 9), cancellable = true)
    private static void muffins$catchingBagHoldAnim(AbstractClientPlayer abstractClientPlayer,
                                                    InteractionHand interactionHand,
                                                    CallbackInfoReturnable<HumanoidModel.ArmPose> cir,
                                                    @Share("shareItemStack") LocalRef<ItemStack> itemStackLocalRef
                                                ){
        if(!abstractClientPlayer.swinging && itemStackLocalRef.get().is(ModItems.CATCHER_BAG)){
            cir.setReturnValue(ModASMEarlyRiser.getDurianCatcherHoldArmPose());
        }
    }

    @Inject(method = "getArmPose",at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/item/ItemStack;getUseAnimation()Lnet/minecraft/world/item/UseAnim;",shift = At.Shift.AFTER), cancellable = true)
    private static void muffins$catchingBagSwingAnim(AbstractClientPlayer abstractClientPlayer,
                                                     InteractionHand interactionHand,
                                                     CallbackInfoReturnable<HumanoidModel.ArmPose> cir,
                                                     @Local UseAnim useAnim
                                                     ){
        if(useAnim == ModASMEarlyRiser.getDurianCatcherUseAnim()){
            cir.setReturnValue(ModASMEarlyRiser.getDurianCatcherSwingArmPose());
        }
    }
}
