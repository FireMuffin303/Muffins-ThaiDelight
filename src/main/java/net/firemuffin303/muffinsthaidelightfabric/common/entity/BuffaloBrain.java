package net.firemuffin303.muffinsthaidelightfabric.common.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBrain;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModEntityTypes;
import net.minecraft.core.GlobalPos;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.schedule.Activity;

import java.util.Map;

public class BuffaloBrain {

    protected static Brain<?> makeBrain(Brain<BuffaloEntity> brain){
        initCoreActivity(brain);
        initIdleActivity(brain);
        initPlayingActivity(brain);
        return brain;
    }

    private static void initCoreActivity(Brain<BuffaloEntity> brain){
        brain.addActivity(Activity.CORE,0,
                ImmutableList.of(
                        new Swim(0.8f),
                        new LookAtTargetSink(45,90),
                        new MoveToTargetSink(),
                        new CountDownCooldownTicks(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)
                )
        );
    }

    private static void initIdleActivity(Brain<BuffaloEntity> brain){
        brain.addActivity(Activity.IDLE,ImmutableList.of(
                Pair.of(0, SetEntityLookTargetSometimes.create(EntityType.PLAYER,6.0f, UniformInt.of(30,60))),
                Pair.of(1,new AnimalMakeLove(ModEntityTypes.BUFFALO,1.0f)),
                Pair.of(2,new FollowTemptation(livingEntity -> 2.5f)),
                Pair.of(4, new RandomLookAround(UniformInt.of(150, 250), 30.0f, 0.0f, 0.0f))
        ));
    }

    private static void initPlayingActivity(Brain<BuffaloEntity> brain){
        brain.addActivity(Activity.PLAY,ImmutableList.of());
    }

    private static BehaviorControl<BuffaloEntity> createSearchForMud(){
        return BehaviorBuilder.create(instance ->
            instance.group(instance.registered(ModBrain.BUFFALO_MUD_POSITION)).apply(instance,muGlobalPosMemoryAccessor -> (serverLevel,buffalo,l) ->{
                //find mud position
                return false;
            })
        );
    }
}
