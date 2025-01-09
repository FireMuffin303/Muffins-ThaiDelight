package net.firemuffin303.muffinsthaidelightfabric.mixin.flavor;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.muffinsthaidelightfabric.common.data.FlavorItemData;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FoodData.class)
public abstract class FoodDataMixin {
    @Shadow public abstract void eat(int i, float f);


    @Shadow private int foodLevel;

    @WrapOperation(method = "eat(Lnet/minecraft/world/item/Item;Lnet/minecraft/world/item/ItemStack;)V",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;eat(IF)V"))
    public void muffinsThaiDelight$eat(FoodData instance, int i, float f, Operation<Void> original, @Local(argsOnly = true)ItemStack itemStack){
        if(FlavorItemData.hasFlavorTag(itemStack) && FlavorItemData.getSourLevel(itemStack) > 0){
            float souredSaturation = f - (f * FlavorItemData.getSourLevel(itemStack));
            this.eat(i,souredSaturation);

        }else{
            original.call(instance,i,f);
        }
    }
}
