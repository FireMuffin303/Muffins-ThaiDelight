package net.firemuffin303.thaidelight.mixin.fabric.dyeableItem;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

public interface DyeableLeatherItemMixin {
 /*
    @ModifyReturnValue(method = "getColor",at = @At(value = "RETURN",ordinal = 1))
    default int muffins$getColor(int original, @Local(argsOnly = true)ItemStack itemStack){
        if(itemStack.is(ModItems.COCONUT_MILK_ICE_CREAM.get()) || itemStack.is(ModItems.KHANOM_CHAN.get())){
            return 0xffffff;
        }
        return original;
    }

  */
}
