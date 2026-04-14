package net.firemuffin303.thaidelight.forge.mixin.fallingBlock;

import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.thaidelight.common.block.SackBlock;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin extends Entity {

    @Shadow
    private BlockState blockState;
    @Shadow public boolean dropItem;

    public FallingBlockEntityMixin(EntityType<?> arg, Level arg2) {
        super(arg, arg2);
    }

    @Inject(method = "tick",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/FallingBlockEntity;discard()V",ordinal = 4))
    public void muffins$checkLandOnBlock(CallbackInfo ci){
        BlockState blockState1 = this.level().getBlockState(this.blockPosition());
        if(this.blockState.is(ModTags.SACK_CATCHABLE) && (blockState1.is(ModBlocks.SACK.get()) && !blockState1.getValue(SackBlock.FILLED))){
            SackBlock sackBlock = (SackBlock) blockState1.getBlock();
            if(sackBlock.insertFallingBlock(this.blockState.getBlock().asItem(),this.level(),this.blockPosition())){
                this.dropItem = false;
                sackBlock.playCatchFallingBlockEffect(this.level(),this.blockPosition());
            }
        }
    }
}
