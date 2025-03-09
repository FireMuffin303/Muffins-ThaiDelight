package net.firemuffin303.muffinsthaidelightfabric.mixin.spicy;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModComponents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "canFreeze",at = @At("HEAD"), cancellable = true)
    public void muffins_thaidelight$canFreeze(CallbackInfoReturnable<Boolean> cir){
        if(ModComponents.SPICY.get(this).getSpicyLevel() > 0){
            cir.setReturnValue(false);
        }
    }


    @ModifyVariable(method = "hurt", at = @At(value = "HEAD"),argsOnly = true)
    public float muffins_thaiDelight$getDamageAfterArmorAbsorb(float f, DamageSource damageSource){
        if(ModComponents.SPICY.get(this).getSpicyLevel() > 0 && damageSource.is(DamageTypeTags.IS_FIRE)){
            float spicyMultiplier = 1.5f;
            return f * spicyMultiplier;
        }

        return f;
    }
}
