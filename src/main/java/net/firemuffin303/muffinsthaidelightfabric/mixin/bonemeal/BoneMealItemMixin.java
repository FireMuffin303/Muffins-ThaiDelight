package net.firemuffin303.muffinsthaidelightfabric.mixin.bonemeal;

import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.muffinsthaidelightfabric.common.block.DirectionBonemealableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoneMealItem.class)
public class BoneMealItemMixin{

    @Unique
    private static Direction clickFaceCache = Direction.UP;

    @Inject(method = "useOn",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/context/UseOnContext;getLevel()Lnet/minecraft/world/level/Level;"))
    private void muffins$applyClickFaceCache(UseOnContext useOnContext, CallbackInfoReturnable<InteractionResult> cir){
        clickFaceCache = useOnContext.getClickedFace();
    }

    @Inject(method = "growCrop",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getBlock()Lnet/minecraft/world/level/block/Block;",ordinal = 1), cancellable = true)
    private static void muffins$growCrop(ItemStack itemStack, Level level, BlockPos blockPos, CallbackInfoReturnable<Boolean> cir, @Local BlockState blockState){
        if(blockState.getBlock() instanceof DirectionBonemealableBlock directionBonemealableBlock){
            if(directionBonemealableBlock.isValidBonemealTarget(level,blockPos,blockState,clickFaceCache,level.isClientSide)){
                if(level instanceof ServerLevel serverLevel){
                    if(directionBonemealableBlock.isBonemealSuccess(level,level.random,blockPos,blockState,clickFaceCache)){
                        directionBonemealableBlock.performBonemeal(serverLevel,level.random,blockPos,blockState,clickFaceCache);
                    }

                    itemStack.shrink(1);
                }
                clickFaceCache = Direction.UP;
                cir.setReturnValue(true);
            }
        }
        clickFaceCache = Direction.UP;
    }

}
