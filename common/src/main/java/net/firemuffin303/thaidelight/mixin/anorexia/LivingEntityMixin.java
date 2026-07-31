package net.firemuffin303.thaidelight.mixin.anorexia;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.thaidelight.common.registry.ModMobEffects;
import net.firemuffin303.thaidelight.util.ModUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow public abstract boolean hasEffect(Holder<MobEffect> holder);

    @ModifyExpressionValue(method = "startUsingItem",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getUseDuration(Lnet/minecraft/world/entity/LivingEntity;)I"))
    public int muffins$modifyEatDuration(int original, @Local ItemStack itemStack){
        if(itemStack.has(DataComponents.FOOD) && this.hasEffect(ModMobEffects.APPETITE_LOSS)){
            return ModUtils.calculateEatingWithAnorexiaEffect((LivingEntity) (Object)this,original);
        }
        return original;
    }

    @ModifyExpressionValue(method = "shouldTriggerItemUseEffects",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getUseDuration(Lnet/minecraft/world/entity/LivingEntity;)I"))
    public int muffins$modifyTriggerEffect(int original){
        if(this.hasEffect(ModMobEffects.APPETITE_LOSS)){
            return ModUtils.calculateEatingWithAnorexiaEffect((LivingEntity) (Object)this,original);
        }
        return original;
    }
}
