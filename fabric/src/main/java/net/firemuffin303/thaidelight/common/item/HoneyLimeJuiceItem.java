package net.firemuffin303.thaidelight.common.item;

import net.firemuffin303.thaidelight.common.registry.ModFoods;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.registry.ModItems;

public class HoneyLimeJuiceItem extends DrinkableItem {
    public HoneyLimeJuiceItem() {
        super(ModItems.drinkItem().food(ModFoods.HONEY_LIME_JUICE),false,true);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        super.affectConsumer(stack, level, consumer);
        consumer.removeEffect(MobEffects.BLINDNESS);
        consumer.removeEffect(MobEffects.POISON);
    }
}
