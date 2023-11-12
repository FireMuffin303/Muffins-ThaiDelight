package net.firemuffin303.thaidelight.fabric.mixin;

import com.nhoryzon.mc.farmersdelight.item.KnifeItem;
import com.nhoryzon.mc.farmersdelight.item.SkilletItem;
import com.nhoryzon.mc.farmersdelight.registry.EnchantmentsRegistry;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.fabric.common.item.PastleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin({EnchantmentHelper.class})
public class ModEnchantmentHelperMixin {
    @Inject(method = {"getAvailableEnchantmentResults"}, at = {@At("RETURN")})
    private static void getPossibleEntriesEnhanced(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentInstance>> returnCallback) {
        List<EnchantmentInstance> possibleEnchantmentList = (List)returnCallback.getReturnValue();
        if (stack.getItem() instanceof PastleItem) {
            possibleEnchantmentList.removeIf((enchantmentLevelEntry) -> {
                return ModItems.DISALLOW_PESTLE_ENCHANTMENT.contains(enchantmentLevelEntry.enchantment);
            });
        }

    }

    @Unique
    private static void addEntry(List<EnchantmentInstance> entries, int power, Enchantment enchantment) {
        for(int level = enchantment.getMaxLevel(); level >= enchantment.getMinLevel(); --level) {
            if (enchantment.getMinCost(level) <= power && power <= enchantment.getMaxCost(level)) {
                entries.add(new EnchantmentInstance(enchantment, level));
                break;
            }
        }

    }

    @Unique
    private static boolean containsEnchantment(List<EnchantmentInstance> entries, Enchantment enchantment) {
        return entries.stream().anyMatch((enchantmentLevelEntry) -> {
            return enchantmentLevelEntry.enchantment == enchantment;
        });
    }
}
