package net.firemuffin303.muffinsthaidelightfabric.common.entity;

import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModEntityTypes;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BuffaloEntity extends AbstractHorse implements ContainerListener, HasCustomInventoryScreen,Saddleable,PlayerRideableJumping {
    protected float playerJumpPendingScale;
    protected BuffaloRideState state;

    public BuffaloEntity(EntityType<? extends AbstractHorse> entityType, Level level) {
        super(entityType, level);
        this.setMaxUpStep(1.0f);
        state = BuffaloRideState.GROUND;

    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, Ingredient.of(ModTags.WATER_BUFFALO_FOOD), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 15.0).add(Attributes.MOVEMENT_SPEED, 0.20000000298023224);
    }

    //Interaction Logic
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if(!itemStack.isEmpty()){
            if (itemStack.is(Items.BUCKET) && !this.isBaby()) {
                player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
                ItemStack itemStack2 = ItemUtils.createFilledResult(itemStack, player, Items.MILK_BUCKET.getDefaultInstance());
                player.setItemInHand(interactionHand, itemStack2);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            } else{
                InteractionResult interactionResult =  itemStack.interactLivingEntity(player,this, interactionHand);
                if(interactionResult.consumesAction()){
                    return interactionResult;
                }
                
            }
        }else{
            if(this.isSaddled()){
                this.doPlayerRide(player);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
        }
        return super.mobInteract(player,interactionHand);
    }

    protected void doPlayerRide(Player player) {
        if (!this.level().isClientSide) {
            player.setYRot(this.getYRot());
            player.setXRot(this.getXRot());
            player.startRiding(this);
        }
    }

    @Override
    public boolean isImmobile() {
        return super.isImmobile() && this.isVehicle() && this.isSaddled();
    }

    //Data
    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        if(!this.inventory.getItem(0).isEmpty()){
            compoundTag.put("SaddleItem",this.inventory.getItem(0).save(new CompoundTag()));
        }
    }



    //--- Sound ---
    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.COW_AMBIENT;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.COW_HURT;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.COW_DEATH;
    }


    @Override
    protected void playStepSound(BlockPos blockPos, BlockState blockState) {
        this.playSound(SoundEvents.COW_STEP,0.15f,1.0f);
    }

    @Override
    protected float getSoundVolume() {
        return 0.4f;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return ModEntityTypes.BUFFALO.create(serverLevel);
    }

    //--- Ride Logic ---
    public BuffaloRideState getState() {
        return state;
    }

    public void setState(BuffaloRideState state) {
        this.state = state;
    }

    private void checkRideState(){
        if(this.isInWater() && this.getFluidHeight(FluidTags.WATER) > this.getFluidJumpThreshold()){
            this.setState(BuffaloRideState.IN_WATER);
        } else if (this.playerJumpPendingScale > 0.0f) {
            if(this.state == BuffaloRideState.GROUND || this.state == BuffaloRideState.CHARGE){
                this.setState(BuffaloRideState.CHARGE);
            }
        } else {
            this.setState(BuffaloRideState.GROUND);
        }
    }

    private float getStateFriction(){
       return switch (this.getState()){
            case CHARGE -> 1.2f;
            case IN_WATER -> 0.5f;
            case UNDERWATER -> 0.25f;
            default -> 0.8f;
        };
    }

    //Riding State Logic
    @Override
    protected void tickRidden(Player player, Vec3 vec3) {
        this.checkRideState();
        LogUtils.getLogger().info(this.getState() + "");

        if(this.playerJumpPendingScale > 0.0f){
           if(this.getState().equals(BuffaloRideState.GROUND)){
              this.hasImpulse = true;
           }else if(this.getState().equals(BuffaloRideState.CHARGE)) {
              double d = this.getRiddenSpeed(player) * 5.5f;
              Vec3 vec32 = new Vec3(0,0,(0.98) * d);
              this.travel(vec32);
               this.playerJumpPendingScale -= 0.02f;
           }
        }else {
            if(!this.getState().equals(BuffaloRideState.CHARGE)){
                Vec2 vec2 = this.getRiddenRotation(player);
                this.setRot(vec2.y, vec2.x);
                this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
                this.playerJumpPendingScale = 0.0f;
            }

            if(this.getState().equals(BuffaloRideState.IN_WATER)){
                Vec3 currentDelta = this.getDeltaMovement();
                double waterLevel = (currentDelta.y + this.getFluidHeight(FluidTags.WATER) * 0.062223);
                Vec3 vec4 = new Vec3(currentDelta.x,waterLevel * 0.75,currentDelta.z);
                this.setDeltaMovement(vec4);
            }

        }
    }

    protected Vec2 getRiddenRotation(LivingEntity livingEntity) {
        return new Vec2(livingEntity.getXRot() * 0.5F, livingEntity.getYRot());
    }


    @Override
    protected float getRiddenSpeed(Player player) {
        return (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED);
    }

    //Movement via State
    @Override
    protected @NotNull Vec3 getRiddenInput(Player player, Vec3 vec3) {
        if(!this.getState().equals(BuffaloRideState.CHARGE)){
            float f = player.xxa * 0.5F;
            float g = player.zza * 0.75F;
            if (g <= 0.0F) {
                g *= 0.25F;
            }
            return new Vec3(f, 0.0, g);
        }
        return this.getDeltaMovement();
    }



    @Override
    @Nullable
    public LivingEntity getControllingPassenger() {
        Entity var3 = this.getFirstPassenger();
        if (var3 instanceof Mob mob) {
            return mob;
        } else {
            if (this.isSaddled()) {
                var3 = this.getFirstPassenger();

                if (var3 instanceof Player player) {
                    return player;
                }
            }

            return null;
        }
    }


    //---- Jumping ----
    @Override
    public void onPlayerJump(int i) {
        if(this.isSaddled()){
            if(i < 0){
                i = 0;
            }{
                if (i >= 90) {
                    this.playerJumpPendingScale = 1.0F;
                } else {
                    this.playerJumpPendingScale = 0.4F + 0.4F * (float)i / 90.0F;
                }
            }
        }

    }

    @Override
    public boolean canJump() {
        return this.isSaddled() && !this.getState().equals(BuffaloRideState.CHARGE) || ! this.getState().equals(BuffaloRideState.IN_WATER);
    }

    @Override
    public void handleStartJump(int i) {

    }

    @Override
    public void handleStopJump() {

    }

    //--- Container Changed---
    @Override
    public void containerChanged(@NotNull Container container) {
        super.containerChanged(container);
        //Chest or Kantai
    }



    @Override
    public void openCustomInventoryScreen(Player player) {
        if (!this.level().isClientSide) {
            player.openHorseInventory(this, this.inventory);
        }
    }

    //Abstract Horse


    @Override
    public boolean isTamed() {
        return true;
    }

    public enum BuffaloRideState {
        GROUND,
        CHARGE,
        IN_WATER,
        UNDERWATER
    }
}
