package net.firemuffin303.thaidelight.fabric.mixin;

import com.nhoryzon.mc.farmersdelight.item.KnifeItem;
import com.nhoryzon.mc.farmersdelight.item.SkilletItem;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.fabric.common.item.PastleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;

@Mixin(Enchantment.class)
public class ModEnchantmentMixin {

    @Inject(method = {"canEnchant"}, at = {@At("RETURN")}, cancellable = true)
    private void getPossibleEntriesEnhanced(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        boolean canBeEnchanted = false;
        Iterator var4;
        Enchantment enchantment;
        if (stack.getItem() instanceof PastleItem) {
            var4 = ModItems.DISALLOW_PESTLE_ENCHANTMENT.iterator();

            while(var4.hasNext()) {
                enchantment = (Enchantment)var4.next();
                if (((Enchantment) (Object)this) == enchantment) {
                    canBeEnchanted = false;
                    break;
                }else{
                    canBeEnchanted = true;
                }
            }

            cir.setReturnValue(canBeEnchanted);
        }

    }
}
