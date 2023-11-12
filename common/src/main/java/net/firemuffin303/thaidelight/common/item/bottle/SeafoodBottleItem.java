package net.firemuffin303.thaidelight.common.item.bottle;

import net.firemuffin303.thaidelight.common.item.DrinkableItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class SeafoodBottleItem extends DrinkableItem {
    public SeafoodBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public void affectAfter(Level level, LivingEntity user) {
        user.setTicksFrozen(0);
    }
}
