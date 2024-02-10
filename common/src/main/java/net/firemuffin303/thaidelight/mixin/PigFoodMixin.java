package net.firemuffin303.thaidelight.mixin;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Pig.class)
public class PigFoodMixin {

    @Inject(method = "isFood",at = @At("TAIL"), cancellable = true)
    public void isFood(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir){
        if(!Boolean.TRUE.equals(cir.getReturnValue())){
            cir.setReturnValue(Ingredient.of(ModItems.PAPAYA,ModItems.RAW_PAPAYA,ModItems.LIME).test(itemStack));
        }
    }
}
