package net.firemuffin303.muffinsthaidelightfabric.mixin.flavor;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.firemuffin303.muffinsthaidelightfabric.common.manager.FlavorManager;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Item.class)
public abstract class ItemMixin {

    @ModifyExpressionValue(method = "use",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;canEat(Z)Z"))
    public boolean muffins_thaiDelight$canEat(boolean original){
        if(FlavorManager.FLAVORS.containsKey((Item)(Object) this)){
            return FlavorManager.FLAVORS.get((Item)(Object) this).sour() > 0 || original;
        }
        return original;
    }
}
