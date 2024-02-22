package net.firemuffin303.thaidelight.common.entity;

import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.function.IntFunction;

public class Dragonfly extends Animal implements VariantHolder<Dragonfly.DragonflyVariant>, Bottleable, FlyingAnimal {
    private static final Ingredient FOOD_ITEMS = Ingredient.of(ModTags.DRAGONFLY_FOOD);
    private static final EntityDataAccessor<Integer> DATA_VARIANT = SynchedEntityData.defineId(Dragonfly.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> FROM_BOTTLE = SynchedEntityData.defineId(Dragonfly.class,EntityDataSerializers.BOOLEAN);

    public Dragonfly(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new DragonflyMoveControl(this);

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.FLYING_SPEED, 0.7500000238418579D).add(Attributes.MOVEMENT_SPEED, 0.40000001192092896D);
    }

    @Override
    public void travel(Vec3 vec3) {
        if (this.isControlledByLocalInstance()) {
            if (this.isInWater()) {
                this.moveRelative(0.02F, vec3);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.800000011920929D));
            } else if (this.isInLava()) {
                this.moveRelative(0.02F, vec3);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5D));
            } else {
                float f = 0.91F;
                if (this.onGround()) {
                    f = this.level().getBlockState(this.getBlockPosBelowThatAffectsMyMovement()).getBlock().getFriction() * 0.91F;
                }

                float g = 0.16277137F / (f * f * f);
                f = 0.91F;
                if (this.onGround()) {
                    f = this.level().getBlockState(this.getBlockPosBelowThatAffectsMyMovement()).getBlock().getFriction() * 0.91F;
                }

                this.moveRelative(this.onGround() ? 0.1F * g : 0.02F, vec3);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale((double)f));
            }
        }

        this.calculateEntityAnimation(false);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return ModEntityTypes.DRAGONFLY.create(serverLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT, 0);
        this.entityData.define(FROM_BOTTLE, false);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(7, new DragonflyLookGoal(this));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new FlyWanderGoal(this));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        return Bottleable.tryBottle(player,interactionHand,this).orElse(super.mobInteract(player,interactionHand));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("Variant", this.getVariant().getId());
        compoundTag.putBoolean("FromBottle",this.entityData.get(FROM_BOTTLE));
    }

    @Override
    protected void ageBoundaryReached() {
        super.ageBoundaryReached();
        if (!this.isBaby() && this.level().getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            this.spawnAtLocation(Items.PHANTOM_MEMBRANE, 1);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.entityData.set(DATA_VARIANT,compoundTag.getInt("Variant"));
        this.entityData.set(FROM_BOTTLE,compoundTag.getBoolean("FromBucket"));
    }

    @Override
    protected void checkFallDamage(double d, boolean bl, BlockState blockState, BlockPos blockPos) {
    }

    @Override
    public void setVariant(DragonflyVariant object) {
        this.entityData.set(DATA_VARIANT,object.getId());
    }

    @Override
    public DragonflyVariant getVariant() {
        return DragonflyVariant.byId(this.entityData.get(DATA_VARIANT));
    }

    @Override
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return FOOD_ITEMS.test(itemStack);
    }

    @Override
    public boolean isFromBottle() {
        return this.entityData.get(FROM_BOTTLE);
    }

    @Override
    public void setFromBottle(boolean fromBottle) {
        this.entityData.set(FROM_BOTTLE,fromBottle);
    }

    @Override
    public void copyDataToStack(ItemStack stack) {
        Bottleable.copyDataToStack(this,stack);
        CompoundTag compoundTag = stack.getOrCreateTag();
        compoundTag.putInt("Variant",this.getVariant().getId());
    }

    @Override
    public void copyDataFromNbt(CompoundTag nbt) {
        Bottleable.copyDataFromNbt(this,nbt);
        this.setVariant(DragonflyVariant.byId(nbt.getInt("Variant")));
    }

    @Override
    public ItemStack getBottleItem() {
        return new ItemStack(ModItems.DRAGONFLY_BOTTLE);
    }

    @Override
    public SoundEvent getBottleFillSound() {
        return SoundEvents.BOTTLE_FILL;
    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    @Override
    public boolean onClimbable() { return false; }

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

    public static enum DragonflyVariant {
        RED(0,"red",true),
        GREEN(1,"green",true),
        BLUE(2,"blue",true),
        YELLOW(3,"yellow",true);

        private final int id;
        private final String name;
        private final boolean common;
        private static final IntFunction<DragonflyVariant> BY_ID = ByIdMap.continuous(DragonflyVariant::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);


        private DragonflyVariant(int j, String string2, boolean bl) {
            this.id = j;
            this.name = string2;
            this.common = bl;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public static DragonflyVariant byId(int i) {
            return BY_ID.apply(i);
        }

    }
}
