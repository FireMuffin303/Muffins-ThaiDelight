package net.firemuffin303.thaidelight.mixin.foodEffect;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin{

    @Inject(method = "baseTick",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;fireImmune()Z"))
    public void muffins$durianHeatTick(CallbackInfo ci){
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if(livingEntity instanceof Player){
            DurianHeatAttachment durianHeat = livingEntity.getAttachedOrSet(ModAttachments.DURIAN_HEAT,new DurianHeatAttachment(0,false));
            durianHeat.tick(livingEntity);
        }
    }

    @Inject(method = "baseTick",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;fireImmune()Z"))
    public void muffins$spicyTick(CallbackInfo ci){

        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if(livingEntity instanceof Player){
            SpicyAttachment spicyAttachment = livingEntity.getAttachedOrSet(ModAttachments.SPICY,new SpicyAttachment(0));
            spicyAttachment.tick(livingEntity);
        }
    }

    @Inject(method = "addEatEffect",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item;getFoodProperties()Lnet/minecraft/world/food/FoodProperties;"))
    public void muffins$addEatEffect(ItemStack itemStack, Level level, LivingEntity livingEntity, CallbackInfo ci){

        if(itemStack.is(ModTags.SPICY_FOODS)){
            CommonEvents.onEatSpicyFood(itemStack,livingEntity);
        }

        if (itemStack.is(ModTags.DURIAN_FOOD)) {
            CommonEvents.onEatDurian(itemStack,livingEntity);
        }
    }


    @ModifyReturnValue(method = "canFreeze",at = @At(value = "RETURN",ordinal = 1))
    public boolean muffins$canFreezeCheck(boolean original){
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        boolean bl = true;
        if(livingEntity instanceof Player){
            DurianHeatAttachment durianHeat = livingEntity.getAttached(ModAttachments.DURIAN_HEAT);
            if(durianHeat != null){
                bl = !durianHeat.isHeatedUp;
            }
        }

        return original && bl;
    }

    @ModifyVariable(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/WalkAnimationState;setSpeed(F)V"), argsOnly = true)
    public float muffins$calculateDamage(float value, @Local(argsOnly = true) DamageSource damageSource){
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if(livingEntity instanceof Player){
            DurianHeatAttachment durianHeat = livingEntity.getAttached(ModAttachments.DURIAN_HEAT);
            if(durianHeat != null){
                if(durianHeat.isHeatedUp && damageSource.is(DamageTypeTags.IS_FIRE)){
                    value *= 2f;
                }
            }

            SpicyAttachment spicyAttachment = livingEntity.getAttached(ModAttachments.SPICY);
            if(spicyAttachment != null){
                if(spicyAttachment.timer > 0 && damageSource.is(ModTags.SPICY_RESISTANT_TO)){
                    value *= 0.80f;
                }
            }
        }

        return value;
    }

    @Inject(method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z",at = @At("HEAD"))
    public void muffins$setPlayer(MobEffectInstance mobEffectInstance, Entity entity, CallbackInfoReturnable<Boolean> cir, @Share("player") LocalRef<Player> localRef){
        LivingEntity livingEntity = (LivingEntity)(Object)this;
        if(livingEntity instanceof Player player1){
            localRef.set(player1);
        }
    }

    @Inject(method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z",
            at = @At(value = "INVOKE", target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"))
    public void muffins$reduceIfSpicy(MobEffectInstance mobEffectInstance, Entity entity, CallbackInfoReturnable<Boolean> cir,
                                      @Local(argsOnly = true)LocalRef<MobEffectInstance> mobEffectInstanceLocalRef, @Share("player") LocalRef<Player> localRef){
        if(!mobEffectInstanceLocalRef.get().isAmbient()){
            SpicyAttachment spicyAttachment = localRef.get().getAttached(ModAttachments.SPICY);
            if(spicyAttachment != null){
                if(spicyAttachment.timer > 0){
                    mobEffectInstanceLocalRef.set(new MobEffectInstance(mobEffectInstance.getEffect(),mobEffectInstance.getDuration() - mobEffectInstance.getDuration()/3));
                }
            }
        }
    }
}
