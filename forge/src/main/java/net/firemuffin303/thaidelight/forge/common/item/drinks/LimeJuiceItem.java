package net.firemuffin303.thaidelight.forge.common.item.drinks;

import net.firemuffin303.thaidelight.common.registry.ModFoods;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.registry.ModItems;

public class LimeJuiceItem extends DrinkableItem {
    public LimeJuiceItem() {
        super(ModItems.drinkItem().food(ModFoods.LIME_JUICE),false,true);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        super.affectConsumer(stack, level, consumer);
        consumer.removeEffect(MobEffects.BLINDNESS);
    }
}
