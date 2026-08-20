package net.firemuffin303.thaidelight.mixin.foodEffect;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.firemuffin303.thaidelight.util.ModUtils;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {




    @ModifyReturnValue(method = "canFreeze", at = @At(value = "RETURN", ordinal = 1))
    public boolean muffins$canFreezeCheck(boolean original) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        boolean bl = true;
        if (livingEntity instanceof Player) {
            bl = !PlatformUtil.getDurianHeatComponent(livingEntity).isHeatUp();
        }

        return original && bl;
    }

    @ModifyVariable(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/WalkAnimationState;setSpeed(F)V"), argsOnly = true)
    public float muffins$calculateDamage(float value, @Local(argsOnly = true) DamageSource damageSource){
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if(livingEntity instanceof Player){
            if(PlatformUtil.getDurianHeatComponent(livingEntity).isHeatUp() && damageSource.is(DamageTypeTags.IS_FIRE)){
                value *= 2f;
            }

            if(PlatformUtil.getSpicyTime(livingEntity) > 0 && damageSource.is(ModTags.SPICY_RESISTANT_TO)){
                value *= 0.80f;
            }
        }

        return value;
    }

    @Inject(method = "onEffectAdded",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;sendEffectToPassengers(Lnet/minecraft/world/effect/MobEffectInstance;)V"))
    public void muffins$onFireResistanceAdded(MobEffectInstance mobEffectInstance, Entity entity, CallbackInfo ci){
        if(mobEffectInstance.getEffect() == MobEffects.FIRE_RESISTANCE){
            PlatformUtil.setSpicyTimeForge(200,(LivingEntity) (Object)this);
        }
    }

}