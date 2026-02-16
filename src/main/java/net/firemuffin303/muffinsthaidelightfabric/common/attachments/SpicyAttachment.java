package net.firemuffin303.muffinsthaidelightfabric.common.attachments;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.firemuffin303.muffinsthaidelightfabric.network.packet.SpicyPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class SpicyAttachment implements SpecialEffectAttachment {
    public int timer;

    public static Codec<SpicyAttachment> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.fieldOf("timer").forGetter(spicyAttachment -> spicyAttachment.timer)
            ).apply(instance,SpicyAttachment::new)
    );


    public SpicyAttachment(int timer){
        this.timer = timer;
    }

    public void addTime(int value,LivingEntity livingEntity){
        this.setTime(value + this.timer, livingEntity);
    }

    public void setTime(int value,LivingEntity livingEntity){
        this.timer = value;
        if(livingEntity instanceof ServerPlayer player){
            ServerPlayNetworking.send(player,new SpicyPacket(this.timer));
        }
    }

    public void tick(LivingEntity livingEntity){
        int reductionRate = 1;

        if(this.timer > 0){
            if(livingEntity.fireImmune() || livingEntity.hasEffect(MobEffects.FIRE_RESISTANCE)){
                reductionRate = 6;
            }

            this.timer = Math.max(0,this.timer - reductionRate);
        }
    }

    @Override
    public int getTimer() {
        return this.timer;
    }
}
