package net.firemuffin303.thaidelight.neoforge.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FireBlock.class)
public abstract class FireBlockMixin {

    @ModifyReturnValue(method = "getBurnOdds",at = @At(value = "RETURN"))
    public int muffins$getBurnOdds(int original, @Local(argsOnly = true)BlockState blockState){
        if(blockState.hasProperty(BlockStateProperties.WATERLOGGED) && blockState.getValue(BlockStateProperties.WATERLOGGED)){
            return 0;
        }

        if(ThaiDelightCommon.BURN_MAP.containsKey(blockState.getBlock())){
            return ThaiDelightCommon.BURN_MAP.get(blockState.getBlock()).burn();
        }
        return original;
    }

    @ModifyReturnValue(method = "getIgniteOdds(Lnet/minecraft/world/level/block/state/BlockState;)I",at = @At(value = "RETURN"))
    public int muffins$getIgniteOdds(int original, @Local(argsOnly = true)BlockState blockState){
        if(blockState.hasProperty(BlockStateProperties.WATERLOGGED) && blockState.getValue(BlockStateProperties.WATERLOGGED)){
            return 0;
        }

        if(ThaiDelightCommon.BURN_MAP.containsKey(blockState.getBlock())){
            return ThaiDelightCommon.BURN_MAP.get(blockState.getBlock()).spread();
        }
        return original;
    }
}
