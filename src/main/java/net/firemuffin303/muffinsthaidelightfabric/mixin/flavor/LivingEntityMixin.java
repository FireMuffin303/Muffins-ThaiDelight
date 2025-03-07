package net.firemuffin303.muffinsthaidelightfabric.mixin.flavor;

import net.firemuffin303.muffinsthaidelightfabric.common.manager.FlavorManager;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "eat",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;addEatEffect(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)V"))
    public void muffins_thaidelight$eat(Level level, ItemStack itemStack, CallbackInfoReturnable<ItemStack> cir){
        if(FlavorManager.FLAVORS.containsKey(itemStack.getItem())){
            ModComponents.SPICY.get(this).addSpicyLevel(FlavorManager.FLAVORS.get(itemStack.getItem()).spicy() * 10);
        }
    }
}
