package net.firemuffin303.thaidelight.fabric.common.item;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.state.BlockState;

public class PastleItem extends SwordItem {
    public PastleItem(Tier arg, int attackDamage, float attackSpeed, Properties arg2) {
        super(arg, attackDamage, attackSpeed, arg2);
    }

    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        return 1.0f;
    }

}
