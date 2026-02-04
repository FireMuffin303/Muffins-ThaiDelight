package net.firemuffin303.muffinsthaidelightfabric.mixin.dispenser;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsthaidelightfabric.common.block.cauldron.CoconutCauldron;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin {

    @Shadow @Final private Fluid content;

    @ModifyExpressionValue(method = "emptyContents",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;isAir()Z"))
    public boolean muffins$AddCoconutCauldron(boolean original, @Local BlockState blockState){
        return original || (blockState.is(ModBlocks.COCONUT_CAULDRON) && blockState.getValue(CoconutCauldron.LEVEL) == 3);
    }

    @Inject(method = "emptyContents",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"), cancellable = true)
    public void muffins$fillCoconutCauldron(Player player, Level level, BlockPos blockPos, BlockHitResult blockHitResult,
                                            CallbackInfoReturnable<Boolean> cir){
        BlockState blockState = level.getBlockState(blockPos);
        if(this.content == Fluids.WATER && blockState.is(ModBlocks.COCONUT_CAULDRON) && blockState.getValue(CoconutCauldron.LEVEL) == 3){
            level.setBlock(blockPos,ModBlocks.COCONUT_MILK_CAULDRON.defaultBlockState().setValue(CoconutCauldron.LEVEL,3),3);
            cir.setReturnValue(true);
        }
    }
}
