package net.firemuffin303.thaidelight.forge.common.capabilities;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

public interface IDurianHeat {
    void setTimer(int value);

    void addTimer(int value);

    int getTimer();

    void setHeat(boolean value);

    boolean isHeatUp();

    void tick(LivingEntity livingEntity);
}
