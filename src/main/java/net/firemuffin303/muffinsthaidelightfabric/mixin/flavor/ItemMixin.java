package net.firemuffin303.muffinsthaidelightfabric.mixin.flavor;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.muffinsthaidelightfabric.common.data.FlavorItemData;
import net.firemuffin303.muffinsthaidelightfabric.common.item.tooltipComponent.FlavorTooltipClient;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Inject(method = "getTooltipImage", at = @At("TAIL"), cancellable = true)
    public void muffinsThaiDelight$getTooltipImage(ItemStack itemStack, CallbackInfoReturnable<Optional<TooltipComponent>> cir){
        if(itemStack.getItem().isEdible() && FlavorItemData.hasFlavorTag(itemStack)){
            cir.setReturnValue(Optional.of(new FlavorTooltipClient.FlavorTooltipComponent(FlavorItemData.getFromNBT(itemStack))));
        }
    }

    @ModifyExpressionValue(method = "use",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;canEat(Z)Z"))
    public boolean muffinsThaiDelight$useCanEat(boolean original, @Local ItemStack itemStack){
        if(itemStack.isEdible() && FlavorItemData.hasFlavorTag(itemStack)){
            return FlavorItemData.getSourLevel(itemStack) > 0 || original;
        }
        return original;
    }

    @ModifyReturnValue(method = "getUseDuration",at = @At("RETURN"))
    public int muffinsThaiDelight$getUseDuration(int original,@Local(argsOnly = true) ItemStack itemStack){
        if(FlavorItemData.hasFlavorTag(itemStack) && FlavorItemData.getSourLevel(itemStack) > 0){
            return (int) Math.max(14,Math.floor(original - (original * FlavorItemData.getSourLevel(itemStack))));
        }
        return original;
    }
}
