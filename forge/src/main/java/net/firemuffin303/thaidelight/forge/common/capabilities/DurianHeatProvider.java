package net.firemuffin303.thaidelight.forge.common.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DurianHeatProvider implements IDurianHeat, ICapabilitySerializable<CompoundTag> {
    public static Capability<IDurianHeat> DURIAN_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    int timer = 0;
    boolean heatUp = false;


    @Override
    public void setTimer(int value) {

    }

    @Override
    public void addTimer(int value) {

    }

    @Override
    public int getTimer() {
        return 0;
    }

    @Override
    public void setHeat(boolean value) {

    }

    @Override
    public boolean isHeatUp() {
        return false;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
        return DURIAN_CAPABILITY.orEmpty(capability,LazyOptional.of(DurianHeatProvider::new));
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putInt("timer",this.timer);
        compoundTag.putBoolean("heat_up",this.heatUp);
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag arg) {
        this.timer = arg.getInt("timer");
        this.heatUp = arg.getBoolean("heat_up");
    }
}
