package net.firemuffin303.thaidelight.common.cardinalcomponents;

import net.firemuffin303.thaidelight.common.registry.ModCardinalComponents;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class DurianHeatComponent implements AutoSyncedComponent, CommonTickingComponent {
    public int timer;
    public boolean isHeatedUp;
    public LivingEntity livingEntity;

    /*
    public static Codec<DurianHeatComponent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.fieldOf("timer").forGetter(durianHeatAttachment -> durianHeatAttachment.timer),
                    Codec.BOOL.fieldOf("is_drank_fermented_drink").forGetter(durianHeatAttachment -> durianHeatAttachment.isHeatedUp)
            ).apply(instance,DurianHeatComponent::new)
    );
     */

    public DurianHeatComponent(LivingEntity livingEntity){
        this.livingEntity = livingEntity;
    }

    @Override
    public void readFromNbt(CompoundTag compoundTag, HolderLookup.Provider provider) {
        this.isHeatedUp = compoundTag.getBoolean("is_heat_up");
        this.timer = compoundTag.getInt("timer");
    }

    @Override
    public void writeToNbt(CompoundTag compoundTag, HolderLookup.Provider provider) {
        compoundTag.putInt("timer",this.timer);
        compoundTag.putBoolean("is_heat_up",this.isHeatedUp);
    }

    @Override
    public void tick() {
        if(this.timer > 0){
            if(this.isHeatedUp){
                if(this.livingEntity.tickCount % 20 == 0 && !this.livingEntity.level().isClientSide){
                    this.livingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER,200,14,true,true));

                    SpicyComponent spicyAttachment = ModCardinalComponents.SPICY_HEAT.get(this.livingEntity);
                    if(spicyAttachment.timer > 0 && !(this.livingEntity.fireImmune() || this.livingEntity.hasEffect(MobEffects.FIRE_RESISTANCE))){
                        this.livingEntity.setRemainingFireTicks(5);
                    }
                }
            }




            this.timer = Math.max(0,this.timer - 1);
        }else{
            if(this.isHeatedUp){
                this.setHeatedUp(false);
            }
        }
    }

    public void setHeatedUp(boolean value){
        this.isHeatedUp = value;
        ModCardinalComponents.DURIAN_HEAT.sync(this.livingEntity);
    }

    public void addTimer(int value){
        this.timer += value;
        ModCardinalComponents.DURIAN_HEAT.sync(this.livingEntity);
    }

    public void setTimer(int value){
        this.timer = value;
        ModCardinalComponents.DURIAN_HEAT.sync(this.livingEntity);
    }
}
