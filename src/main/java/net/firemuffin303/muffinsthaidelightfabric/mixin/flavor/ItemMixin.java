package net.firemuffin303.muffinsthaidelightfabric.mixin.flavor;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.muffinsthaidelightfabric.common.component.FlavorItemComponent;
import net.firemuffin303.muffinsthaidelightfabric.common.item.tooltipComponent.FlavorTooltipClient;
import net.firemuffin303.muffinsthaidelightfabric.common.manager.FlavorManager;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModComponents;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(Item.class)
public abstract class ItemMixin {

    @ModifyExpressionValue(method = "use",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;canEat(Z)Z"))
    public boolean muffins_thaiDelight$canEat(boolean original,@Local ItemStack itemStack){
        if(ModComponents.FLAVOR.get(itemStack).getSourLevel() > 0){
            return true;
        }else if(FlavorManager.FLAVORS.containsKey((Item)(Object) this)){
            return FlavorManager.FLAVORS.get((Item)(Object) this).sour() > 0 || original;
        }
        return original;
    }

    @ModifyReturnValue(method = "getTooltipImage",at = @At("RETURN"))
    public Optional<TooltipComponent> muffins_thaiDelight$getTooltipImage(Optional<TooltipComponent> original,@Local(argsOnly = true)ItemStack itemStack){
        if((itemStack.isEdible() || itemStack.is(ModItems.SAUCE_BOWL)) && ModComponents.FLAVOR.get(itemStack).isFlavored()){
            FlavorItemComponent flavorItemComponent = ModComponents.FLAVOR.get(itemStack);
            return Optional.of(new FlavorTooltipClient.FlavorTooltipComponent(flavorItemComponent.getFlavor()));
        } else if (FlavorManager.FLAVORS.containsKey(itemStack.getItem())) {
            FlavorManager.FlavorEntry flavorEntry = FlavorManager.FLAVORS.get(itemStack.getItem());
            return Optional.of(new FlavorTooltipClient.FlavorTooltipComponent(flavorEntry.toNBT()));
        }
        return original;
    }
}
