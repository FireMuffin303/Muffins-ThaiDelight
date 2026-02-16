package net.firemuffin303.muffinsthaidelightfabric.common.attachments;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.firemuffin303.muffinsthaidelightfabric.network.packet.DurianHeatPacket;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModAttachments;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class DurianHeatAttachment implements SpecialEffectAttachment {
    public int timer;
    public boolean isHeatedUp;

    public static Codec<DurianHeatAttachment> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.fieldOf("timer").forGetter(durianHeatAttachment -> durianHeatAttachment.timer),
                    Codec.BOOL.fieldOf("is_drank_fermented_drink").forGetter(durianHeatAttachment -> durianHeatAttachment.isHeatedUp)
            ).apply(instance,DurianHeatAttachment::new)
    );

    public DurianHeatAttachment(int timer, boolean isHeatedUp){
        this.timer = timer;
        this.isHeatedUp = isHeatedUp;
    }

    public void addTime(int value,LivingEntity livingEntity){
        this.setTime(value + this.timer, livingEntity);
    }

    public void setTime(int value,LivingEntity livingEntity){
        this.timer = value;
        if(livingEntity instanceof ServerPlayer player){
            ServerPlayNetworking.send(player,new DurianHeatPacket(this));
        }
    }

    public void setHeatedUp(boolean value, LivingEntity livingEntity){
        this.isHeatedUp = value;

        if(livingEntity instanceof ServerPlayer player){
            ServerPlayNetworking.send(player,new DurianHeatPacket(this));
        }
    }


    public void tick(LivingEntity livingEntity){
        if(this.timer > 0){
            if(this.isHeatedUp){
                if( livingEntity.tickCount % 20 == 0 && !livingEntity.level().isClientSide){
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER,200,14));

                    SpicyAttachment spicyAttachment = livingEntity.getAttached(ModAttachments.SPICY);
                    if(spicyAttachment != null && spicyAttachment.timer > 0 && !(livingEntity.fireImmune() || livingEntity.hasEffect(MobEffects.FIRE_RESISTANCE))){
                        livingEntity.setSecondsOnFire(5);
                    }
                }
            }




            this.timer = Math.max(0,this.timer - 1);
        }else{
            if(this.isHeatedUp){
                this.setHeatedUp(false,livingEntity);
            }
        }


    }

    @Override
    public int getTimer() {
        return this.timer;
    }
}
