package net.firemuffin303.thaidelight.forge.common.capabilities;

import net.minecraft.world.entity.LivingEntity;

public interface ISpicy {

    int getTimer();

    void setTimer(int value, LivingEntity livingEntity);

    void addTimer(int value,LivingEntity livingEntity);

    void tick(LivingEntity livingEntity);
}
