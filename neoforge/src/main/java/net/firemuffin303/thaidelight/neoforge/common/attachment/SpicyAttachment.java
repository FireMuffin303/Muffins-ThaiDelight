package net.firemuffin303.thaidelight.neoforge.common.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class SpicyAttachment {
    public static final Codec<SpicyAttachment> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("time").forGetter(SpicyAttachment::getTimer)
    ).apply(instance,SpicyAttachment::new));

    private int timer = 0;

    public SpicyAttachment(int timer){
        this.timer = timer;
    }

    public void tick(LivingEntity livingEntity){
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
    }

    public int getTimer() {
        return timer;
    }
}
