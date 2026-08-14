package net.firemuffin303.thaidelight.neoforge.common.attachment;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class SpicyAttachment {
    private int timer = 0;

    public void tick(LivingEntity livingEntity){
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
    }

    public int getTimer() {
        return timer;
    }
}
