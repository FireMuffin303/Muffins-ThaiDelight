package net.firemuffin303.thaidelight.forge.common.capabilities;

import net.firemuffin303.thaidelight.forge.network.SpicyPacket;
import net.firemuffin303.thaidelight.forge.network.ThaiDelightPacketHandler;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DurianHeatProvider implements IDurianHeat, ICapabilitySerializable<CompoundTag> {
    public static Capability<IDurianHeat> DURIAN_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    int timer = 0;
    boolean heatUp = false;


    @Override
    public void setTimer(int value) {
        this.timer = value;
    }

    @Override
    public void addTimer(int value) {
        setTimer(this.timer + value);
    }

    @Override
    public int getTimer() {
        return this.timer;
    }

    @Override
    public void setHeat(boolean value) {
        this.heatUp = value;
    }

    @Override
    public boolean isHeatUp() {
        return this.heatUp;
    }

    @Override
    public void tick(LivingEntity livingEntity) {
        if(this.timer > 0){
            if(this.heatUp){
                if(livingEntity.tickCount % 20 == 0 && !livingEntity.level().isClientSide){
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER,200,14,true,true));

                    livingEntity.getCapability(SpicyProvider.SPICY_CAPABILITY).ifPresent(spicy -> {
                        if(spicy.getTimer() > 0 && !(livingEntity.fireImmune() || livingEntity.hasEffect(MobEffects.FIRE_RESISTANCE))){
                            livingEntity.setSecondsOnFire(5);
                        }
                    });
                }
            }
            this.timer = Math.max(0,this.timer - 1);
        }else{
            if(this.heatUp){
                this.setHeat(false);
            }
        }
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
        return DURIAN_CAPABILITY.orEmpty(capability,LazyOptional.of(() -> this));
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
