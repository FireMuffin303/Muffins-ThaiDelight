package net.firemuffin303.muffinsthaidelightfabric.mixin.spicyFoodItem;

import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsthaidelightfabric.common.attachments.DurianHeatAttachment;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModAttachments;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModTags;
import net.firemuffin303.muffinsthaidelightfabric.util.CommonEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin{

    @Inject(method = "aiStep",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getTicksFrozen()I"))
    public void muffins$removeDurianHeat(CallbackInfo ci){
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        DurianHeatAttachment durianHeat = livingEntity.getAttachedOrSet(ModAttachments.DURIAN_HEAT,new DurianHeatAttachment(0,false));
        durianHeat.tick(livingEntity);
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

}
