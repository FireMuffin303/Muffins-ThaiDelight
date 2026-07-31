package net.firemuffin303.thaidelight.common.cardinalcomponents;

import net.firemuffin303.thaidelight.common.registry.ModCardinalComponents;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class SpicyComponent implements AutoSyncedComponent, CommonTickingComponent {
    public int timer;
    public LivingEntity livingEntity;

    public SpicyComponent(LivingEntity livingEntity){
        this.livingEntity = livingEntity;
    }

    @Override
    public void readFromNbt(CompoundTag compoundTag, HolderLookup.Provider provider) {
        this.timer = compoundTag.getInt("timer");
    }

    @Override
    public void writeToNbt(CompoundTag compoundTag, HolderLookup.Provider provider) {
        compoundTag.putInt("timer",this.timer);
    }

    @Override
    public void tick() {
        int reductionRate = 1;

        if(this.timer > 0){
            if(livingEntity.fireImmune() || livingEntity.hasEffect(MobEffects.FIRE_RESISTANCE)){
                reductionRate = 6;
            }

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
