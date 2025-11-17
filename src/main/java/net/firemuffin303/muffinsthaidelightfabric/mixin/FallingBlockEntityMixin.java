package net.firemuffin303.muffinsthaidelightfabric.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Debug;
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
    @Unique private boolean isBagCatch = false;

    public FallingBlockEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyExpressionValue(method = "causeFallDamage",at = @At(value = "INVOKE", target = "Ljava/util/function/Predicate;and(Ljava/util/function/Predicate;)Ljava/util/function/Predicate;"))
    public Predicate<Entity> muffins$ModifyPredicate(Predicate<Entity> original){
        boolean bl = this.blockState.is(ModTags.CATCHER_BAG_CATCHABLE);
        if(bl){
            return original.and(entity -> {
                if(entity instanceof Player player){
                    return !player.isUsingItem() || !player.getItemInHand(player.getUsedItemHand()).is(ModItems.CATCHER_BAG);
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
        if(!this.onGround() && this.blockState.is(ModTags.CATCHER_BAG_CATCHABLE)){

            Predicate<Entity> predicate = EntitySelector.NO_SPECTATORS.and(EntitySelector.LIVING_ENTITY_STILL_ALIVE).and(entity -> {
                if(entity instanceof Player player){
                    return player.isUsingItem() && player.getItemInHand(player.getUsedItemHand()).is(ModItems.CATCHER_BAG);
                }
                return false;
            });
            List<Entity> list = this.level().getEntities(this,this.getBoundingBox().inflate(0.5,-0.3,0.5),predicate);
            if(!list.isEmpty()){
                if(this.dropItem){
                    this.spawnAtLocation(block.get());
                }
                this.discard();
                list.forEach(entity -> ((Player)entity).getCooldowns().addCooldown(ModItems.CATCHER_BAG,10));
                this.level().levelEvent(1045,this.blockPosition(),0);
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
