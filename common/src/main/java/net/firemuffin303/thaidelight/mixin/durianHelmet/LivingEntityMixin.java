package net.firemuffin303.thaidelight.mixin.durianHelmet;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract ItemStack getItemBySlot(EquipmentSlot var1);

    @Inject(method = "hurt",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/WalkAnimationState;setSpeed(F)V"))
    public void muffins$modifyThornsDamage(DamageSource damageSource, float f, CallbackInfoReturnable<Boolean> cir, @Local(argsOnly = true) LocalFloatRef damage){
        if(this.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.DURIAN_HELMET.get()) && damageSource.is(DamageTypes.THORNS)){
            damage.set(damage.get() /2f);
        }
    }
}
