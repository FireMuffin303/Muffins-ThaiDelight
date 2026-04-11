package net.firemuffin303.thaidelight.forge.common.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpicyProvider implements ICapabilitySerializable<CompoundTag>,ISpicy {
    public static Capability<ISpicy> SPICY_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    int timer = 0;

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
        return SPICY_CAPABILITY.orEmpty(capability,LazyOptional.of(SpicyProvider::new));
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putInt("timer",this.timer);
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag arg) {
        this.timer = arg.getInt("timer");
    }


    @Override
    public int getTimer() {
        return this.timer;
    }

    @Override
    public void setTimer(int value) {
        this.timer = value;
    }

    @Override
    public void addTimer(int value) {
        this.timer += value;
    }
}
