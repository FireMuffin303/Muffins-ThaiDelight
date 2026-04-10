package net.firemuffin303.thaidelight.mixin.axeItem;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin {

    @Inject(method = "useOn",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V",ordinal = 0))
    public void muffins$useOn(UseOnContext useOnContext, CallbackInfoReturnable<InteractionResult> cir,
                              @Local(ordinal = 0) Optional<BlockState> blockState,
                              @Local Level level,
                              @Local BlockPos blockPos){
        if(blockState.get().is(ModBlocks.STRIPPED_COCONUT.get())){
            Block.popResource(level,blockPos,new ItemStack(PlatformUtil.getTreeBarkItem().get()));
        }
    }

    @ModifyReturnValue(method = "getStripped",at = @At("RETURN"))
    public Optional<BlockState> muffins$getStripped(Optional<BlockState> original, @Local(argsOnly = true) BlockState blockState){
        if(blockState.is(ModBlocks.COCONUT.get())){
            return Optional.of(ModBlocks.STRIPPED_COCONUT.get().defaultBlockState().setValue(BlockStateProperties.WATERLOGGED,blockState.getValue(BlockStateProperties.WATERLOGGED)));
        }
        return original;
    }
}
