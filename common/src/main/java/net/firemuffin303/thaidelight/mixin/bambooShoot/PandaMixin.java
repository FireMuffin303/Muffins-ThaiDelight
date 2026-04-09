package net.firemuffin303.thaidelight.mixin.bambooShoot;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Panda.class)
public abstract class PandaMixin {
    @ModifyExpressionValue(method = "method_6504",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z",ordinal = 0))
    private static boolean muffins$addBambooShootForPanda(boolean original, @Local ItemStack itemStack){
        return itemStack.is(ModItems.BAMBOO_SHOOT.get()) || itemStack.is(ModItems.DURIAN_CAKE.get()) || itemStack.is(ModItems.DURIAN_CAKE_SLICE.get()) || original;
    }

    @ModifyReturnValue(method = "isFood",at = @At("RETURN"))
    public boolean muffins$addFood(boolean original, @Local(argsOnly = true) ItemStack itemStack){
        return itemStack.is(ModItems.BAMBOO_SHOOT.get()) || original;
    }

    @ModifyReturnValue(method = "isFoodOrCake",at = @At("RETURN"))
    public boolean muffins$addCake(boolean original,@Local(argsOnly = true) ItemStack itemStack){
        return itemStack.is(ModItems.DURIAN_CAKE.get()) || itemStack.is(ModItems.DURIAN_CAKE_SLICE.get()) || original;
    }
}
