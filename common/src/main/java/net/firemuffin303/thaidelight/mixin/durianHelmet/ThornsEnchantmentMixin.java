package net.firemuffin303.thaidelight.mixin.durianHelmet;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.ThornsEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThornsEnchantment.class)
public abstract class ThornsEnchantmentMixin {

    @Inject(method = "doPostHurt",at = @At("HEAD"))
    public void muffins$durianHelmet(LivingEntity livingEntity, Entity entity, int i, CallbackInfo ci, @Local(argsOnly = true)LocalIntRef localIntRef){
        if(livingEntity.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.DURIAN_HELMET.get())){
            localIntRef.set(localIntRef.get() + 1);
        }
    }
}
