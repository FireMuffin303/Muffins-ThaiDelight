package net.firemuffin303.muffinsthaidelightfabric.common.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class SpicyComponent implements AutoSyncedComponent, CommonTickingComponent {
    public final String SPICY = "spicy_level";
    public final int MAX_SPICY = 100;
    private int spicyLevel = 0;
    private LivingEntity livingEntity;

    public SpicyComponent(LivingEntity livingEntity){
        this.livingEntity = livingEntity;
    }

    public void addSpicyLevel(int spicyLevel){
        this.setSpicyLevel(this.getSpicyLevel() + spicyLevel);
    }

    public void setSpicyLevel(int spicyLevel) {
        this.spicyLevel = Mth.clamp(spicyLevel,0,MAX_SPICY);
    }

    public int getSpicyLevel() {
        return spicyLevel;
    }

    public float getPercent(){
        return (float) Math.min(this.spicyLevel, this.MAX_SPICY) /this.MAX_SPICY;
    }

    @Override
    public void readFromNbt(CompoundTag compoundTag) {
        if(compoundTag.contains(SPICY, CompoundTag.TAG_INT)){
            this.spicyLevel = compoundTag.getInt(SPICY);
        }
    }

    @Override
    public void writeToNbt(CompoundTag compoundTag) {
        compoundTag.putInt(SPICY,this.spicyLevel);
    }

    @Override
    public void tick() {
        if(this.livingEntity.hasEffect(MobEffects.FIRE_RESISTANCE)){
            this.setSpicyLevel(0);
        }

        if(this.livingEntity.tickCount % 20 == 0 && this.spicyLevel > 0){
            this.spicyLevel--;
        }
    }
}
