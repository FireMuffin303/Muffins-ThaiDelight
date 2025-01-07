package net.firemuffin303.muffinsthaidelightfabric.mixin;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModMobEffects;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Villager.class)
public abstract class VillagerMixin {

    @Shadow protected abstract void setUnhappy();

    @Inject(method = "mobInteract",at = @At("HEAD"),cancellable = true)
    public void muffinsThaiDelight$mobInteract(Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir){
        if(player.hasEffect(ModMobEffects.STINKY)){
            if(!player.level().isClientSide){
                this.setUnhappy();
            }

            cir.setReturnValue(InteractionResult.sidedSuccess(player.level().isClientSide));
        }
    }
}
