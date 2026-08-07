package net.firemuffin303.thaidelight.mixin.fabric.durianHelmet;

import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.thaidelight.ThaiDelightFabric;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow public abstract boolean isDeadOrDying();

    @Inject(method = "hurt",at = @At("TAIL"))
    public void afterDamage(DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir, @Local(argsOnly = false,ordinal = 0) boolean block){
        if(!isDeadOrDying()){
            ThaiDelightFabric.onEntityHurt((LivingEntity)(Object)this,damageSource,block);
        }
    }
}
