package net.firemuffin303.muffinsthaidelightfabric.mixin.spicy;

import net.firemuffin303.muffinsthaidelightfabric.common.data.SpicyData;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MilkBucketItem.class)
public abstract class MilkBucketItemMixin {

    @Inject(method = "finishUsingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;removeAllEffects()Z",shift = At.Shift.AFTER))
    public void muffinsThaiDelight$finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity, CallbackInfoReturnable<ItemStack> cir){
        ModComponents.SPICY.get(livingEntity).setSpicyLevel(0);
    }
}
