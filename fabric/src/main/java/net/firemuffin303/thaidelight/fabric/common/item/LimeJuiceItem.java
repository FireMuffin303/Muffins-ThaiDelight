package net.firemuffin303.thaidelight.fabric.common.item;

import com.nhoryzon.mc.farmersdelight.item.DrinkableItem;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LimeJuiceItem extends DrinkableItem {
    public LimeJuiceItem(Properties settings) {
        super(settings,false,true);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level world, LivingEntity user) {
        super.affectConsumer(stack, world, user);
        user.removeEffect(MobEffects.BLINDNESS);
    }
}
