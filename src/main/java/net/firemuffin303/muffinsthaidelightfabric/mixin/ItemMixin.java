package net.firemuffin303.muffinsthaidelightfabric.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.muffinsthaidelightfabric.common.item.tooltipComponent.FlavorTooltipClient;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow @Final private static Logger LOGGER;

    @Inject(method = "getTooltipImage", at = @At("TAIL"), cancellable = true)
    public void muffinsThaiDelight$getTooltipImage(ItemStack itemStack, CallbackInfoReturnable<Optional<TooltipComponent>> cir){
        if(itemStack.getItem().isEdible() && (itemStack.hasTag() && itemStack.getTag().contains(FlavorTooltipClient.FlavorTooltipComponent.FLAVOR_NBT))){
            CompoundTag compoundTag = itemStack.getTag().getCompound(FlavorTooltipClient.FlavorTooltipComponent.FLAVOR_NBT);
            boolean isSour = compoundTag.getBoolean(FlavorTooltipClient.FlavorTooltipComponent.SOUR_NBT);
            cir.setReturnValue(Optional.of(new FlavorTooltipClient.FlavorTooltipComponent(isSour)));
        }
    }

    @ModifyExpressionValue(method = "use",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;canEat(Z)Z"))
    public boolean muffinsThaiDelight$useCanEat(boolean original, @Local ItemStack itemStack){
        if(itemStack.hasTag() && itemStack.getTag().contains(FlavorTooltipClient.FlavorTooltipComponent.FLAVOR_NBT)){
            CompoundTag compoundTag = itemStack.getTag().getCompound(FlavorTooltipClient.FlavorTooltipComponent.FLAVOR_NBT);
            return compoundTag.getBoolean(FlavorTooltipClient.FlavorTooltipComponent.SOUR_NBT) || original;
        }
        return original;
    }
}
