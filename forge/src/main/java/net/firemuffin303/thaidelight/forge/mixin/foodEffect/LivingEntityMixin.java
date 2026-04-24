package net.firemuffin303.thaidelight.forge.mixin.foodEffect;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {




    @Inject(method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z",
            at = @At(value = "INVOKE", target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"))
    public void muffins$reduceIfSpicy(MobEffectInstance mobEffectInstance, Entity entity, CallbackInfoReturnable<Boolean> cir,
                                      @Local(argsOnly = true)LocalRef<MobEffectInstance> mobEffectInstanceLocalRef){
        LivingEntity livingEntity = (LivingEntity)(Object)this;
        if(!mobEffectInstanceLocalRef.get().isAmbient()){
            if(PlatformUtil.getSpicyTime(livingEntity) > 0){
                mobEffectInstanceLocalRef.set(new MobEffectInstance(mobEffectInstance.getEffect(),mobEffectInstance.getDuration() - mobEffectInstance.getDuration()/3));
            }
        }
    }
}
