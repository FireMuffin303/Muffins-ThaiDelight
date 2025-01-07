package net.firemuffin303.muffinsthaidelightfabric.common.entity;

import net.firemuffin303.muffinsthaidelightfabric.common.block.CrabEggBlock;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModEntityTypes;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModTags;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

public class FlowerCrabEntity extends Animal implements Bucketable {
    private static final EntityDataAccessor<Boolean> HAS_EGG;
    private static final Ingredient FOOD_ITEMS;
    private static final EntityDataAccessor<Boolean> LAYING_EGG;
    private static final EntityDataAccessor<Boolean> FROM_BUCKET;

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState danceAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;
    int layEggCounter;

    @Nullable
    private BlockPos jukebox;



    public FlowerCrabEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 16.0D).add(Attributes.MOVEMENT_SPEED, 0.20000000298023224D);
    }


    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putBoolean("HasEgg", this.hasEgg());
        compoundTag.putBoolean("FromBucket", this.fromBucket());

    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setHasEgg(compoundTag.getBoolean("HasEgg"));
        this.setFromBucket(compoundTag.getBoolean("FromBucket"));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HAS_EGG, false);
        this.entityData.define(LAYING_EGG, false);
        this.entityData.define(FROM_BUCKET, false);

    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(1, new CrabBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new CrabLayEggGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this,1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        return (InteractionResult)Bucketable.bucketMobPickup(player, interactionHand, this).orElse(super.mobInteract(player, interactionHand));
    }

    public boolean isFood(ItemStack arg) {
        return FOOD_ITEMS.test(arg);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    @Override
    public void aiStep() {
        if (this.jukebox == null || !this.jukebox.closerToCenterThan(this.position(), 3.46D) || !this.level().getBlockState(this.jukebox).is(Blocks.JUKEBOX)) {
            this.jukebox = null;
            this.danceAnimationState.stop();
        }

        super.aiStep();
    }


    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

    }

    @Override
    public void setRecordPlayingNearby(BlockPos blockPos, boolean bl) {
        this.jukebox = blockPos;
        if(blockPos != null && bl){
            this.danceAnimationState.startIfStopped(this.tickCount);
        }else {
            this.danceAnimationState.stop();
        }

    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public boolean hasEgg() {
        return (Boolean)this.entityData.get(HAS_EGG);
    }

    void setHasEgg(boolean bl) {
        this.entityData.set(HAS_EGG, bl);
    }

    public boolean isLayingEgg() {
        return (Boolean)this.entityData.get(LAYING_EGG);
    }

    void setLayingEgg(boolean bl) {
        this.layEggCounter = bl ? 1 : 0;
        this.entityData.set(LAYING_EGG, bl);
    }


    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return ModEntityTypes.FLOWER_CRAB.create(serverLevel);

    }


    static {
        HAS_EGG = SynchedEntityData.defineId(FlowerCrabEntity.class, EntityDataSerializers.BOOLEAN);
        LAYING_EGG = SynchedEntityData.defineId(FlowerCrabEntity.class, EntityDataSerializers.BOOLEAN);
        FOOD_ITEMS = Ingredient.of(ModTags.FLOWER_CRAB_FOOD);
        FROM_BUCKET = SynchedEntityData.defineId(FlowerCrabEntity.class, EntityDataSerializers.BOOLEAN);
    }

    @Override
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean bl) {
        this.entityData.set(FROM_BUCKET, bl);
    }

    @Override
    public void saveToBucketTag(ItemStack arg) {
        Bucketable.saveDefaultDataToBucketTag(this, arg);
        CompoundTag compoundTag = arg.getOrCreateTag();
        compoundTag.putInt("Age", this.getAge());


    }

    public void loadFromBucketTag(CompoundTag arg) {
        Bucketable.loadDefaultDataFromBucketTag(this, arg);
        if (arg.contains("Age")) {
            this.setAge(arg.getInt("Age"));
        }
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.CRAB_BUCKET);
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_EMPTY_FISH;
    }

    public static boolean checkSpawnRules(EntityType<FlowerCrabEntity> crabEntityEntityType, ServerLevelAccessor serverLevelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
        return serverLevelAccessor.getBlockState(blockPos.below()).is(BlockTags.FROGS_SPAWNABLE_ON) && isBrightEnoughToSpawn(serverLevelAccessor,blockPos);
    }


    private static class CrabBreedGoal extends BreedGoal {
        private final FlowerCrabEntity crab;


        public CrabBreedGoal(FlowerCrabEntity crabEntity, double d) {
            super(crabEntity, d);
            this.crab =crabEntity;
        }

        public boolean canUse() {
            return super.canUse() && !this.crab.hasEgg();
        }

        protected void breed() {
            ServerPlayer serverPlayer = this.animal.getLoveCause();
            if (serverPlayer == null && this.partner.getLoveCause() != null) {
                serverPlayer = this.partner.getLoveCause();
            }

            if (serverPlayer != null) {
                serverPlayer.awardStat(Stats.ANIMALS_BRED);
                CriteriaTriggers.BRED_ANIMALS.trigger(serverPlayer, this.animal, this.partner, (AgeableMob)null);
            }

            this.crab.setHasEgg(true);
            this.animal.setAge(6000);
            this.partner.setAge(6000);
            this.animal.resetLove();
            this.partner.resetLove();
            RandomSource randomSource = this.animal.getRandom();
            if (this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
                this.level.addFreshEntity(new ExperienceOrb(this.level, this.animal.getX(), this.animal.getY(), this.animal.getZ(), randomSource.nextInt(7) + 1));
            }

        }
    }

    static class CrabLayEggGoal extends MoveToBlockGoal {
        private final FlowerCrabEntity crab;

        CrabLayEggGoal(FlowerCrabEntity crab, double d) {
            super(crab, d, 16);
            this.crab = crab;
        }

        public boolean canUse() {
            return this.crab.hasEgg() && super.canUse();
        }

        public boolean canContinueToUse() {
            return super.canContinueToUse() && this.crab.hasEgg();
        }

        public void tick() {
            super.tick();
            BlockPos blockPos = this.crab.blockPosition();
            if (this.crab.isInWater() && this.isReachedTarget()) {
                if (this.crab.layEggCounter < 1) {
                    this.crab.setLayingEgg(true);
                } else if (this.crab.layEggCounter > this.adjustedTickDelay(200)) {
                    Level level = this.crab.level();
                    level.playSound((Player)null, blockPos, SoundEvents.TURTLE_LAY_EGG, SoundSource.BLOCKS, 0.3F, 0.9F + level.random.nextFloat() * 0.2F);
                    BlockPos blockPos2 = this.blockPos.above();
                    FluidState fluidState = level.getFluidState(blockPos2);
                    BlockState blockState = (BlockState) ModBlocks.CRAB_EGG.defaultBlockState().setValue(CrabEggBlock.WATERLOGGED,fluidState.is(Fluids.WATER));
                    level.setBlock(blockPos2, blockState, 3);
                    level.gameEvent(GameEvent.BLOCK_PLACE, blockPos2, GameEvent.Context.of(this.crab, blockState));
                    this.crab.setHasEgg(false);
                    this.crab.setLayingEgg(false);
                    this.crab.setInLoveTime(600);
                }

                if (this.crab.isLayingEgg()) {
                    ++this.crab.layEggCounter;
                }
            }



        }

        protected boolean isValidTarget(LevelReader levelReader, BlockPos blockPos) {
            return levelReader.isWaterAt(blockPos.above());
        }
    }
}
