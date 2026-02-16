package net.firemuffin303.muffinsthaidelightfabric.mixin.foodEffect;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.muffinsthaidelightfabric.common.attachments.DurianHeatAttachment;
import net.firemuffin303.muffinsthaidelightfabric.common.attachments.SpicyAttachment;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModAttachments;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModTags;
import net.firemuffin303.muffinsthaidelightfabric.util.CommonEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
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
    public float muffins$additionalFireDamage(float value, @Local(argsOnly = true) DamageSource damageSource){
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if(livingEntity instanceof Player){
            DurianHeatAttachment durianHeat = livingEntity.getAttached(ModAttachments.DURIAN_HEAT);
            if(durianHeat != null){
                if(durianHeat.isHeatedUp && damageSource.is(DamageTypeTags.IS_FIRE)){
                    value *= 2f;
                }
            }
        }

        return value;
    }

}
