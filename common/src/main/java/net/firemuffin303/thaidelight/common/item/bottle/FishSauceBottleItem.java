package net.firemuffin303.thaidelight.common.item.bottle;

import net.firemuffin303.thaidelight.common.item.DrinkableItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class FishSauceBottleItem extends DrinkableItem {
    public FishSauceBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public void affectAfter(Level level, LivingEntity user) {
        user.addEffect(new MobEffectInstance(MobEffects.HUNGER,5*20,0));
    }
}
