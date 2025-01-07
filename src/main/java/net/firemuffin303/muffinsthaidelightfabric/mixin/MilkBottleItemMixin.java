package net.firemuffin303.muffinsthaidelightfabric.mixin;

import net.firemuffin303.muffinsthaidelightfabric.common.entitydata.SpicyData;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.item.MilkBottleItem;

@Mixin(MilkBottleItem.class)
public abstract class MilkBottleItemMixin {

    @Inject(method = "affectConsumer" ,at = @At("TAIL"))
    public void muffinsThaiDelight$affectConsumer(ItemStack stack, Level level, LivingEntity consumer, CallbackInfo ci){
        ((SpicyData.SpicyAccessor)consumer).muffinsThaiDelight$access().reset();
    }
}
