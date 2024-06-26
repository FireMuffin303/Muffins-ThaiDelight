package net.firemuffin303.thaidelight.forge.common.item;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.DrinkableItem;

public class PapayaJuiceItem extends DrinkableItem {
    public PapayaJuiceItem(Item.Properties settings) {
        super(settings,false,true);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level world, LivingEntity user) {
        super.affectConsumer(stack, world, user);
        user.removeEffect(MobEffects.HUNGER);
    }
}
