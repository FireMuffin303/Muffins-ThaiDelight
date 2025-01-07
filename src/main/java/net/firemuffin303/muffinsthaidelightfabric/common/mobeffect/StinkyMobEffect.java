package net.firemuffin303.muffinsthaidelightfabric.common.mobeffect;

import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class StinkyMobEffect extends MobEffect {
    public StinkyMobEffect(MobEffectCategory mobEffectCategory, int i) {
        super(mobEffectCategory, i);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int i) {
        if(livingEntity.level() instanceof ServerLevel serverLevel){
            Vec3 vec3 = livingEntity.position();
            AABB aabb = new AABB(vec3,vec3.add(0.0,1.0,0.0));
            aabb = aabb.inflate(5.0d+i);
            List<ServerPlayer> playerList = serverLevel.getEntitiesOfClass(ServerPlayer.class,aabb);
            for (ServerPlayer serverPlayer : playerList){
                if(!livingEntity.is(serverPlayer)){
                    serverPlayer.addEffect(new MobEffectInstance(MobEffects.CONFUSION,5*20,i));
                }

            }
        }

        super.applyEffectTick(livingEntity, i);
    }

    @Override
    public boolean isDurationEffectTick(int i, int j) {
        return i % 20 == 0;
    }
}
