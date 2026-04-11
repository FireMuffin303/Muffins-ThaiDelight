package net.firemuffin303.thaidelight.forge.common.item.drinks;

import net.firemuffin303.thaidelight.common.registry.ModFoods;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.registry.ModItems;

public class ButterflyPeaJuiceItem extends DrinkableItem {
    public ButterflyPeaJuiceItem() {
        super(ModItems.drinkItem().food(ModFoods.COCONUT_WATER),false,true);
    }

    @Override
    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
        super.affectConsumer(stack, level, consumer);
    }
}
