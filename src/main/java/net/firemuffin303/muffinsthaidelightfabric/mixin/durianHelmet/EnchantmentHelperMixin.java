package net.firemuffin303.muffinsthaidelightfabric.mixin.durianHelmet;

import net.firemuffin303.muffinsthaidelightfabric.util.CommonEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {

    @Inject(method = "doPostHurtEffects",at = @At("HEAD"))
    private static void muffins$durianThorns(LivingEntity livingEntity, Entity entity, CallbackInfo ci){
        CommonEvents.durianHelmetThorns(livingEntity,entity);
    }

}
