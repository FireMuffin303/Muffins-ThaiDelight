package net.firemuffin303.muffinsthaidelightfabric.common.entity;

import net.firemuffin303.muffinsthaidelightfabric.common.Bottleable;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModEntityTypes;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.function.IntFunction;

public class DragonflyEntity extends Animal implements VariantHolder<DragonflyEntity.DragonflyVariant>, Bottleable, FlyingAnimal {
    private static final Ingredient FOOD_ITEMS = Ingredient.of(ModTags.DRAGONFLY_FOOD);
    private static final EntityDataAccessor<Integer> DATA_VARIANT = SynchedEntityData.defineId(DragonflyEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> FROM_BOTTLE = SynchedEntityData.defineId(DragonflyEntity.class,EntityDataSerializers.BOOLEAN);

    public DragonflyEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new DragonflyMoveControl(this);
        this.lookControl = new LookControl(this);
        this.setPathfindingMalus(BlockPathTypes.FENCE,-1.0f);
        this.setPathfindingMalus(BlockPathTypes.COCOA,-1.0f);
        this.setPathfindingMalus(BlockPathTypes.WATER,-1.0f);
        this.setPathfindingMalus(BlockPathTypes.LAVA,-1.0f);

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.FLYING_SPEED, 0.6500000238418579D).add(Attributes.MOVEMENT_SPEED, 0.40000001192092896D).add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation flyingPathNavigation = new FlyingPathNavigation(this,level){
            public boolean isStableDestination(BlockPos blockPos) {
                return !this.level.getBlockState(blockPos.below()).isAir();
            }
        };

        flyingPathNavigation.setCanOpenDoors(false);
        flyingPathNavigation.setCanFloat(false);
        flyingPathNavigation.setCanPassDoors(true);
        return flyingPathNavigation;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        DragonflyEntity dragonfly = ModEntityTypes.DRAGONFLY.create(serverLevel);
        if(dragonfly != null){
            dragonfly.setVariant(this.random.nextBoolean() ? this.getVariant() : ((DragonflyEntity)ageableMob ).getVariant());
            dragonfly.setPersistenceRequired();
        }

        return dragonfly;
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
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new DragonflyWanderGoal());


    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        return Bottleable.tryBottle(player,interactionHand,this).orElse(super.mobInteract(player,interactionHand));
    }

    //Spawn
    public static boolean checkSpawnRules(EntityType<DragonflyEntity> dragonflyEntityEntityType, ServerLevelAccessor serverLevelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
        return serverLevelAccessor.getBlockState(blockPos.below()).is(Blocks.WATER) && isBrightEnoughToSpawn(serverLevelAccessor,blockPos);
    }

    public float getWalkTargetValue(BlockPos blockPos, LevelReader levelReader) {
        return levelReader.getBlockState(blockPos).isAir() ? 10.0F : 0.0F;
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
    protected void pushEntities() {
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void doPush(Entity entity) {
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.BEE_HURT;
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
        compoundTag.putInt("Age",this.age);
    }

    @Override
    public void copyDataFromNbt(CompoundTag nbt) {
        Bottleable.copyDataFromNbt(this,nbt);
        this.setVariant(DragonflyVariant.byId(nbt.getInt("Variant")));
        this.setAge(nbt.getInt("Age"));
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

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {
        if (mobSpawnType == MobSpawnType.BUCKET) {
            return (SpawnGroupData) spawnGroupData;
        }
        RandomSource randomSource = serverLevelAccessor.getRandom();
        this.setVariant(DragonflyVariant.byId(randomSource.nextInt(0,4)));
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance,mobSpawnType,spawnGroupData, compoundTag);
    }

    static class DragonflyMoveControl extends MoveControl {
        private final DragonflyEntity dragonfly;

        public DragonflyMoveControl(DragonflyEntity mob) {
            super(mob);
            this.dragonfly = mob;
        }


        public void tick(){
            if (this.operation == Operation.MOVE_TO) {
                this.operation = Operation.WAIT;

                this.mob.setNoGravity(true);

                double d = this.wantedX - this.mob.getX();
                double e = this.wantedY - this.mob.getY();
                double f = this.wantedZ - this.mob.getZ();
                double g = d * d + e * e + f * f;
                if (g < 2.500000277905201E-7D) {
                    this.mob.setYya(0.0F);
                    this.mob.setZza(0.0F);
                    return;
                }
                float h = (float)(Mth.atan2(f, d) * 57.2957763671875D) - 90.0F;
                this.mob.setYRot(this.rotlerp(this.mob.getYRot(), h, 90.0F));

                float i = (float) (this.mob.getAttributeBaseValue(Attributes.FLYING_SPEED));
                this.mob.setSpeed(i);

                double j = Math.sqrt(d * d + f * f);
                if (Math.abs(e) > 9.999999747378752E-6D || Math.abs(j) > 9.999999747378752E-6D) {
                    float k = (float)(-(Mth.atan2(e, j) * 57.2957763671875D));
                    this.mob.setXRot(this.rotlerp(this.mob.getXRot(), k, 10));
                    this.mob.setYya(e > 0.0D ? i : -i);
                }

            }else{
                this.mob.setYya(0.0F);
                this.mob.setZza(0.0F);
            }
        }

    }

    static class FlyWanderGoal extends Goal {
        private final DragonflyEntity dragonfly;

        FlyWanderGoal(DragonflyEntity dragonfly) {
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
            double d = this.dragonfly.getX() + (double)((randomSource.nextFloat() * 2.0F - 1.0F) * 3);
            double e = this.dragonfly.getY() + (double)((randomSource.nextFloat() * 2.0F - 1.0F));
            double f = this.dragonfly.getZ() + (double)((randomSource.nextFloat() * 2.0F - 1.0F) * 3);

            this.dragonfly.navigation.moveTo(d, e, f, 1.0D);
        }
    }

    static class DragonflyLookGoal extends Goal{
        private final DragonflyEntity dragonfly;

        public DragonflyLookGoal(DragonflyEntity dragonfly){
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

    class DragonflyWanderGoal extends Goal {
        private static final int WANDER_THRESHOLD = 22;

        DragonflyWanderGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            return DragonflyEntity.this.navigation.isDone() && DragonflyEntity.this.random.nextInt(10) == 0;
        }

        public boolean canContinueToUse() {
            return DragonflyEntity.this.navigation.isInProgress();
        }

        public void start() {
            Vec3 vec3 = this.findPos();
            if (vec3 != null) {
                DragonflyEntity.this.navigation.moveTo(DragonflyEntity.this.navigation.createPath(BlockPos.containing(vec3), 1), 1.2D);
            }
        }

        @Nullable
        private Vec3 findPos() {
            Vec3 vec32;
            vec32 = DragonflyEntity.this.getViewVector(0.0F);

            Vec3 vec33 = HoverRandomPos.getPos(DragonflyEntity.this, 8, 2, vec32.x, vec32.z, 1.5707964F, 3, 1);
            return vec33 != null ? vec33 : AirAndWaterRandomPos.getPos(DragonflyEntity.this, 8, 4, -2, vec32.x, vec32.z, 1.5707963705062866D);
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
