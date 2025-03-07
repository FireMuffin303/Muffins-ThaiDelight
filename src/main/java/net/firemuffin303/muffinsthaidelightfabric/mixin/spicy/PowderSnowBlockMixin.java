package net.firemuffin303.muffinsthaidelightfabric.mixin.spicy;

import net.firemuffin303.muffinsthaidelightfabric.common.data.SpicyData;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModComponents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public abstract class PowderSnowBlockMixin {
    @Inject(method = "canEntityWalkOnPowderSnow",at = @At("HEAD"), cancellable = true)
    private static void muffins_thaidelight$canEntityWalkOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> cir){
        if(entity instanceof LivingEntity livingEntity && ModComponents.SPICY.get(livingEntity).getSpicyLevel() > 0){
            cir.setReturnValue(true);
        }
    }
}
