package net.firemuffin303.thaidelight.forge.common.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class SomtamItem extends ConsumableItem {
    public SomtamItem(Item.Properties settings) {
        super(settings);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level world, LivingEntity user) {
        user.setTicksFrozen(0);
    }
}
