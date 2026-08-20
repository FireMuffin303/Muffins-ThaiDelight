package net.firemuffin303.thaidelight.forge.common.capabilities;

import com.mojang.logging.LogUtils;
import net.firemuffin303.thaidelight.forge.network.SpicyPacket;
import net.firemuffin303.thaidelight.forge.network.ThaiDelightPacketHandler;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpicyProvider implements ICapabilitySerializable<CompoundTag>,ISpicy {
    public static Capability<ISpicy> SPICY_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    int timer = 0;

    private final LazyOptional<ISpicy> optional = LazyOptional.of(() -> this);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
        return SPICY_CAPABILITY.orEmpty(capability,optional);
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
    public void setTimer(int value,LivingEntity livingEntity) {
        this.timer = value;
        this.update(livingEntity);
    }

    @Override
    public void addTimer(int value,LivingEntity livingEntity) {
        setTimer(this.timer+value,livingEntity);
    }

    private void update(LivingEntity livingEntity){
        if(livingEntity instanceof ServerPlayer player){
            ThaiDelightPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player),new SpicyPacket(this.timer));
        }
    }

    @Override
    public void tick(LivingEntity livingEntity){
        int reductionRate = 1;

        if(this.timer > 0){
            this.timer = Math.max(0,this.timer - reductionRate);
        }
    }
}
