package net.firemuffin303.thaidelight.forge.common.item;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;

public class PastleItem extends SwordItem {
    public PastleItem(Tier arg, int attackDamage, float attackSpeed, Properties arg2) {
        super(arg, attackDamage, attackSpeed, arg2);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return !ModItems.DISALLOW_PESTLE_ENCHANTMENT.contains(enchantment) && enchantment.category.canEnchant(stack.getItem());
    }
}
