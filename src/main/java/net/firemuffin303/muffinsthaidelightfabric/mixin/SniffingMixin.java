package net.firemuffin303.muffinsthaidelightfabric.mixin;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModMobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.warden.Sniffing;
import net.minecraft.world.entity.monster.warden.Warden;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Sniffing.class)
public abstract class SniffingMixin {
    @Inject(method = "method_42159" , at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/warden/Warden;increaseAngerAt(Lnet/minecraft/world/entity/Entity;)V",shift = At.Shift.AFTER))
    private static void muffinsThaiDelight$stop(Warden warden, LivingEntity livingEntity, CallbackInfo ci){
        if(livingEntity.hasEffect(ModMobEffects.STINKY)){
            warden.increaseAngerAt(livingEntity,150,false);
        }
    }
}
