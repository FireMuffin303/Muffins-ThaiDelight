package net.firemuffin303.thaidelight.neoforge.common.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class DurianHeatAttachment {
    public static final Codec<DurianHeatAttachment> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("timer").forGetter(DurianHeatAttachment::getTimer),
            Codec.BOOL.fieldOf("is_heat_up").forGetter(DurianHeatAttachment::isHeatUp)
    ).apply(instance,DurianHeatAttachment::new));


    private int timer = 0;
    private boolean isHeatUp = false;

    public DurianHeatAttachment(int timer,boolean isHeatUp){
        this.timer = timer;
        this.isHeatUp = isHeatUp;
    }


    public void tick(LivingEntity livingEntity){
        if(this.timer > 0){
            if(this.isHeatUp){
                if(livingEntity.tickCount % 20 == 0 && !livingEntity.level().isClientSide){
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER,200,14,true,true));

                    int spicy = livingEntity.getData(ModAttachments.SPICY).getTimer();

                    if(spicy > 0 && !(livingEntity.fireImmune() || livingEntity.hasEffect(MobEffects.FIRE_RESISTANCE))){
                        livingEntity.setRemainingFireTicks(5);
                    }


                }
            }
            this.timer = Math.max(0,this.timer - 1);
        }else{
            if(this.isHeatUp){
                this.setHeat(false);
            }
        }
    }

    public void setHeat(boolean value) {
        this.isHeatUp = value;
    }

    public boolean isHeatUp() {
        return this.isHeatUp;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getTimer() {
        return timer;
    }
}
