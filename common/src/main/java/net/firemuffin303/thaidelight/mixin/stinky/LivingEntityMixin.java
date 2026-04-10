package net.firemuffin303.thaidelight.mixin.stinky;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.firemuffin303.thaidelight.common.registry.ModMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @ModifyReturnValue(method = "getVisibilityPercent",at = @At("RETURN"))
    public double muffins$modifyVisibilityPercent(double original){
        LivingEntity livingEntity = ((LivingEntity)(Object)this);
        if( livingEntity.hasEffect(ModMobEffects.STINKY.get()) ){
            float decreaseScentRate = 1.0f;
            if(livingEntity.isUnderWater()){
                decreaseScentRate = 0.8f;
            }

            MobEffectInstance effect = livingEntity.getEffect(ModMobEffects.STINKY.get());
            return original * ((1.5f * (Objects.requireNonNull(effect).getAmplifier()+1) ) * decreaseScentRate);
        }

        return original;
    }
}
