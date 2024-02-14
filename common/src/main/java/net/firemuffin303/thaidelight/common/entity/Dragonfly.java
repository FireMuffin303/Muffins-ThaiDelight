package net.firemuffin303.thaidelight.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class Dragonfly extends FlyingMob {


    public Dragonfly(EntityType<? extends FlyingMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new DragonflyMoveControl(this);

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.FLYING_SPEED, 0.9100000238418579D).add(Attributes.MOVEMENT_SPEED, 0.50000001192092896D).add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(7, new DragonflyLookGoal(this));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new FlyWanderGoal(this));
    }
    @Override
    protected void checkFallDamage(double d, boolean bl, BlockState blockState, BlockPos blockPos) {
    }

    static class DragonflyMoveControl extends MoveControl{
        private final Dragonfly dragonfly;

        public DragonflyMoveControl(Dragonfly mob) {
            super(mob);
            this.dragonfly = mob;
        }


        public void tick(){
            if (this.operation == Operation.MOVE_TO) {
                Vec3 vec3 = new Vec3(this.wantedX - dragonfly.getX(), this.wantedY - dragonfly.getY(), this.wantedZ - dragonfly.getZ());
                double d = vec3.length();
                vec3 = vec3.normalize();
                if (this.canReach(vec3, Mth.ceil(d))) {
                    dragonfly.setDeltaMovement(dragonfly.getDeltaMovement().add(vec3.scale(0.05d)));
                }else {
                    this.operation = Operation.WAIT;
                }
            }
        }

        private boolean canReach(Vec3 vec3, int i) {
            AABB aABB = dragonfly.getBoundingBox();

            for(int j = 1; j < i; ++j) {
                aABB = aABB.move(vec3);
                if (!this.dragonfly.level().noCollision(this.dragonfly, aABB)) {
                    return false;
                }
            }

            return true;
        }
    }

    static class FlyWanderGoal extends Goal {
        private final Dragonfly dragonfly;

        FlyWanderGoal(Dragonfly dragonfly) {
            this.setFlags(EnumSet.of(Flag.MOVE));
            this.dragonfly = dragonfly;
        }

        public boolean canUse() {
            MoveControl moveControl = this.dragonfly.getMoveControl();
            if (!moveControl.hasWanted()) {
                return true;
            } else {
                double d = moveControl.getWantedX() - this.dragonfly.getX();
                //double e = moveControl.getWantedY() - this.dragonfly.getY();
                double f = moveControl.getWantedZ() - this.dragonfly.getZ();
                double g = d * d  + f * f;
                return g < 1.0D || g > 1300.0D;
            }
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void start() {
            RandomSource randomSource = this.dragonfly.getRandom();
            double d = this.dragonfly.getX() + (double)((randomSource.nextFloat() * 2.0F - 1.0F) * 2);
            double e = this.dragonfly.getY() + (double)((randomSource.nextFloat() * 2.0F - 1.0F));
            double f = this.dragonfly.getZ() + (double)((randomSource.nextFloat() * 2.0F - 1.0F) * 2);
            this.dragonfly.getMoveControl().setWantedPosition(d, e, f, 1.0D);
        }
    }

    static class DragonflyLookGoal extends Goal{
        private final Dragonfly dragonfly;

        public DragonflyLookGoal(Dragonfly dragonfly){
            this.dragonfly = dragonfly;
            this.setFlags(EnumSet.of(Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return true;
        }

        public void tick() {
            if (this.dragonfly.getTarget() == null) {
                Vec3 vec3 = this.dragonfly.getDeltaMovement();
                this.dragonfly.setYRot(-((float)Mth.atan2(vec3.x, vec3.z)) * 57.295776F);
                this.dragonfly.yBodyRot = this.dragonfly.getYRot();
            }
        }
    }
}
