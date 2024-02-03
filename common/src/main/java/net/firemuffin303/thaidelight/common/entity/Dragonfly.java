package net.firemuffin303.thaidelight.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class Dragonfly extends PathfinderMob implements FlyingAnimal {

    public Dragonfly(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 1, true);

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.FLYING_SPEED, 0.9100000238418579D).add(Attributes.MOVEMENT_SPEED, 0.50000001192092896D).add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(8, new Dragonfly.FlyWanderGoal());
        this.goalSelector.addGoal(9, new FloatGoal(this));
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation flyingPathNavigation = new FlyingPathNavigation(this, level) {
            public boolean isStableDestination(BlockPos blockPos) {
                return !this.level.getBlockState(blockPos.below()).isAir();
            }
        };
        flyingPathNavigation.setCanOpenDoors(false);
        flyingPathNavigation.setCanFloat(false);
        flyingPathNavigation.setCanPassDoors(true);
        return flyingPathNavigation;
    }

    @Override
    protected void checkFallDamage(double d, boolean bl, BlockState blockState, BlockPos blockPos) {
    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }


    class DragonflyMoveControl extends FlyingMoveControl{
        private final Dragonfly dragonfly;

        public DragonflyMoveControl(Dragonfly mob, int i, boolean bl) {
            super(mob, i, bl);
            this.dragonfly = mob;
        }


        public void tick(){

        }
    }

    class FlyWanderGoal extends Goal {
        private static final int WANDER_THRESHOLD = 22;

        FlyWanderGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            return Dragonfly.this.navigation.isDone() && Dragonfly.this.random.nextInt(10) == 0;
        }

        public boolean canContinueToUse() {
            return Dragonfly.this.navigation.isInProgress();
        }

        public void start() {
            Vec3 vec3 = this.findPos();
            if (vec3 != null) {
                Dragonfly.this.navigation.moveTo(Dragonfly.this.navigation.createPath(BlockPos.containing(vec3), 1), 1.0D);
            }

        }

        @Nullable
        private Vec3 findPos() {
            Vec3 vec32;
            vec32 = Dragonfly.this.getViewVector(0.0F);

            Vec3 vec33 = HoverRandomPos.getPos(Dragonfly.this, 8, 7, vec32.x, vec32.z, 1.5707964F, 3, 1);
            return vec33 != null ? vec33 : AirAndWaterRandomPos.getPos(Dragonfly.this, 8, 4, -2, vec32.x, vec32.z, 1.5707963705062866D);
        }
    }
}
