package net.firemuffin303.muffinsthaidelightfabric.mixin.catachableFallingBlock;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.firemuffin303.muffinsthaidelightfabric.common.block.SackBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.item.SackItem;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModTags;
import net.firemuffin303.muffinsthaidelightfabric.util.CommonEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.Predicate;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin extends Entity {
    @Shadow private BlockState blockState;
    @Shadow public boolean dropItem;
    @Shadow public int time;
    @Shadow @Nullable public CompoundTag blockData;
    @Unique private boolean isBagCatch = false;

    public FallingBlockEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyExpressionValue(method = "causeFallDamage",at = @At(value = "INVOKE", target = "Ljava/util/function/Predicate;and(Ljava/util/function/Predicate;)Ljava/util/function/Predicate;"))
    public Predicate<Entity> muffins$ModifyPredicate(Predicate<Entity> original){
        boolean bl = this.blockState.is(ModTags.SACK_CATCHABLE);
        if(bl){
            return original.and(entity -> {
                if(entity instanceof Player player){
                    return !player.isUsingItem() || !player.getItemInHand(player.getUsedItemHand()).is(ModItems.SACK);
                }
                return true;
            });
        }

        return original;
    }

    @Inject(method = "tick",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/FallingBlockEntity;isNoGravity()Z"))
    public void muffins$shareBlock(CallbackInfo ci,@Local Block block,@Share("shareBlock") LocalRef<Block> blockLocalRef){
        blockLocalRef.set(block);
    }

    @Inject(method = "tick",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/FallingBlockEntity;onGround()Z")
    )
    public void muffins$checkBagDrop(CallbackInfo ci, @Share("shareBlock") LocalRef<Block> block, @Local BlockPos blockPos){
        if(!this.onGround() && this.blockState.is(ModTags.SACK_CATCHABLE)){

            Predicate<Entity> predicate = EntitySelector.NO_SPECTATORS.and(EntitySelector.LIVING_ENTITY_STILL_ALIVE).and(entity -> {
                if(entity instanceof Player player){
                    ItemStack itemStack = player.getItemInHand(player.getUsedItemHand());
                    return player.isUsingItem() && itemStack.is(ModItems.SACK) && !SackItem.isFull(itemStack);
                }
                return false;
            });
            List<Entity> list = this.level().getEntities(this,this.getBoundingBox().inflate(0.5,-0.3,0.5),predicate);
            if(!list.isEmpty()){
                if(this.dropItem){
                    if(list.get(0) instanceof Player player){
                        if(!SackItem.onCatchingFallingBlock( player.getUseItem(), this.blockState.getBlock().asItem(), (ServerPlayer) player )){
                            this.spawnAtLocation(block.get());
                        }
                    }
                }

                this.discard();
                list.forEach(entity -> ((Player)entity).getCooldowns().addCooldown(ModItems.SACK,10));
                if(this.blockState.is(ModTags.SACK_HEAVY_CATCHABLE)){
                    CommonEvents.playDurianCatchingSound((ServerLevel) this.level(),this.position(),this.blockPosition());
                }else{
                    this.level().levelEvent(2009,blockPos,0);
                    this.level().playSound(null,blockPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS);
                }
            }
        }
    }

    @Inject(method = "tick",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/FallingBlockEntity;discard()V",ordinal = 3))
    public void muffins$checkLandOnBlock(CallbackInfo ci,@Local Block block,@Local BlockPos blockPos){
        BlockState blockState1 = this.level().getBlockState(blockPos);
        if(this.blockState.is(ModTags.SACK_CATCHABLE) && (blockState1.is(ModBlocks.SACK) && !blockState1.getValue(SackBlock.FILLED))){
            SackBlock sackBlock = (SackBlock) blockState1.getBlock();
            if(sackBlock.insertFallingBlock(this.blockState.getBlock().asItem(),this.level(),blockPos)){
                this.dropItem = false;
                sackBlock.playCatchFallingBlockEffect(this.level(),blockPos);
            }
        }
    }

    @Inject(method = "addAdditionalSaveData",at = @At("TAIL"))
    public void muffins$addData(CompoundTag compoundTag, CallbackInfo ci){
        compoundTag.putBoolean("IsBagCatch",this.isBagCatch);
    }

    @Inject(method = "readAdditionalSaveData",at = @At("TAIL"))
    public void muffins$readData(CompoundTag compoundTag, CallbackInfo ci){
        this.isBagCatch = compoundTag.getBoolean("IsBagCatch");
    }
}
