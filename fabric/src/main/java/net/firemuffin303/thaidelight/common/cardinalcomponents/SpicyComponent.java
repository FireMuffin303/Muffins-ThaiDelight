package net.firemuffin303.thaidelight.common.cardinalcomponents;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.firemuffin303.thaidelight.common.registry.ModCardinalComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class SpicyComponent implements AutoSyncedComponent, CommonTickingComponent {
    public int timer;
    public LivingEntity livingEntity;

    public SpicyComponent(LivingEntity livingEntity){
        this.livingEntity = livingEntity;
    }

    @Override
    public void readFromNbt(CompoundTag compoundTag) {
        this.timer = compoundTag.getInt("timer");
    }

    @Override
    public void writeToNbt(CompoundTag compoundTag) {
        compoundTag.putInt("timer",this.timer);
    }

    @Override
    public void tick() {
        int reductionRate = 1;

        if(this.timer > 0){
            this.timer = Math.max(0,this.timer - reductionRate);
        }
    }

    public void addTime(int value){
        this.setTime(value + this.timer);
    }

    public void setTime(int value){
        this.timer = value;
        ModCardinalComponents.SPICY_HEAT.sync(this.livingEntity);
    }
}
