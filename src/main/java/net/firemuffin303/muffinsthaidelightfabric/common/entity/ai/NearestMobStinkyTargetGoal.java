package net.firemuffin303.muffinsthaidelightfabric.common.entity.ai;

import net.firemuffin303.muffinsthaidelightfabric.integration.midnightLib.ThaiDelightConfig;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModMobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

public class NearestMobStinkyTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

    public NearestMobStinkyTargetGoal(Mob mob, Class<T> class_, boolean bl) {
        super(mob, class_, bl,livingEntity -> livingEntity.hasEffect(ModMobEffects.STINKY) && ThaiDelightConfig.stinkyShouldTriggerNeutral);
    }

    @Override
    public boolean canUse() {
        if(this.mob instanceof AgeableMob ageableMob && ageableMob.isBaby()){
            return false;
        }

        return super.canUse();
    }
}
