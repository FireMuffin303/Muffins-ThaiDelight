package net.firemuffin303.muffinsthaidelightfabric.common.attachments;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModAttachments;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class DurianHeatAttachment {
    public int timer;
    public boolean isDrankFermentedDrink;

    public static Codec<DurianHeatAttachment> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.fieldOf("timer").forGetter(durianHeatAttachment -> durianHeatAttachment.timer),
                    Codec.BOOL.fieldOf("is_drank_fermented_drink").forGetter(durianHeatAttachment -> durianHeatAttachment.isDrankFermentedDrink)
            ).apply(instance,DurianHeatAttachment::new)
    );

    public DurianHeatAttachment(int timer, boolean isDrankFermentedDrink){
        this.timer = timer;
        this.isDrankFermentedDrink = isDrankFermentedDrink;
    }

    public void addTime(int value){
        this.timer += value;
    }

    public void setDrankFermentedDrink(boolean value){
        this.isDrankFermentedDrink = value;
    }


    public void tick(LivingEntity livingEntity){
        if(this.timer > 0){
            if(this.isDrankFermentedDrink && livingEntity.tickCount % 20 == 0 && !livingEntity.level().isClientSide && !livingEntity.hasEffect(MobEffects.FIRE_RESISTANCE)){
                livingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER,200,14));
            }
            this.timer = Math.max(0,this.timer - 1);
        }else{
            if(this.isDrankFermentedDrink){
                this.setDrankFermentedDrink(false);
            }
        }


    }
}
