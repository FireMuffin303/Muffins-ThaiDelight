package net.firemuffin303.thaidelight.forge.mixin.foodEffect;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.firemuffin303.thaidelight.util.ModUtils;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "addEatEffect",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getFoodProperties(Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/food/FoodProperties;"))
    public void muffins$addEatEffect(ItemStack itemStack, Level level, LivingEntity livingEntity, CallbackInfo ci){

        if(itemStack.is(ModTags.SPICY_FOODS)){
            ModUtils.onEatSpicyFood(itemStack,livingEntity);
        }

        if (itemStack.is(ModTags.DURIAN_FOOD)) {
            ModUtils.onEatDurian(itemStack,livingEntity);
        }
    }


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
