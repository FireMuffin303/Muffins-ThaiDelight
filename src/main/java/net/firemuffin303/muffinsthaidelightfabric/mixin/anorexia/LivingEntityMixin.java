package net.firemuffin303.muffinsthaidelightfabric.mixin.anorexia;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModMobEffects;
import net.firemuffin303.muffinsthaidelightfabric.util.CommonEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract boolean hasEffect(MobEffect mobEffect);

    @ModifyExpressionValue(method = "startUsingItem",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getUseDuration()I"))
    public int muffins$modifyEatDuration(int original, @Local ItemStack itemStack){
        if(itemStack.isEdible() && this.hasEffect(ModMobEffects.APPETITE_LOSS)){
            return CommonEvents.calculateEatingWithAnorexiaEffect((LivingEntity) (Object)this,original);
        }
        return original;
    }

    @ModifyExpressionValue(method = "shouldTriggerItemUseEffects",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getUseDuration()I"))
    public int muffins$modifyTriggerEffect(int original){
        if(this.hasEffect(ModMobEffects.APPETITE_LOSS)){
            return CommonEvents.calculateEatingWithAnorexiaEffect((LivingEntity) (Object)this,original);
        }
        return original;
    }
}
