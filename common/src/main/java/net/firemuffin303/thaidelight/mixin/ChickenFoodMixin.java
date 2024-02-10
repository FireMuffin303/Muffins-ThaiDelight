package net.firemuffin303.thaidelight.mixin;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Chicken.class)
public class ChickenFoodMixin {

    @Inject(method = "isFood",at = @At("TAIL"),cancellable = true)
    public void isFoodInject(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir){
        if(!Boolean.TRUE.equals(cir.getReturnValue())){
            cir.setReturnValue(Ingredient.of(ModItems.PAPAYA_SEED,ModItems.PEPPER_SEED).test(itemStack));
        }
    }
}
