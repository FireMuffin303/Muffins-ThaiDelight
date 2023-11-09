package net.firemuffin303.thaidelight.fabric.common.item;

import com.nhoryzon.mc.farmersdelight.item.ConsumableItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SomtamItem extends ConsumableItem {
    public SomtamItem(Properties settings) {
        super(settings);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level world, LivingEntity user) {
        user.setTicksFrozen(0);
    }
}
