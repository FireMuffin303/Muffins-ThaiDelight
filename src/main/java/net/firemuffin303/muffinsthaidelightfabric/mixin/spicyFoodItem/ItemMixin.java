package net.firemuffin303.muffinsthaidelightfabric.mixin.spicyFoodItem;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModTags;
import net.firemuffin303.muffinsthaidelightfabric.util.CommonEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.item.ConsumableItem;

@Mixin(ConsumableItem.class)
public abstract class ItemMixin {

    @Inject(method = "finishUsingItem",at = @At(value = "INVOKE", target = "Lvectorwing/farmersdelight/common/item/ConsumableItem;affectConsumer(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)V"))
    public void muffins$fermentedDrinksEffect(ItemStack stack, Level level, LivingEntity consumer, CallbackInfoReturnable<ItemStack> cir){
        if(stack.is(ModTags.FERMENTED_DRINKS)){
            CommonEvents.onDrinkFermentedDrinks(level,stack,consumer);
        }
    }
}
