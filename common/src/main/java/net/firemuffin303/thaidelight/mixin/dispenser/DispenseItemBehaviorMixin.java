package net.firemuffin303.thaidelight.mixin.dispenser;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net.minecraft.core.dispenser.DispenseItemBehavior$14")
public abstract class DispenseItemBehaviorMixin extends OptionalDispenseItemBehavior {


    @Shadow protected abstract ItemStack takeLiquid(BlockSource par1, ItemStack par2, ItemStack par3);

    @ModifyReturnValue(method = "execute",at = @At(value = "RETURN",ordinal = 2))
    public ItemStack muffins$takeCoconutMilk(ItemStack original, @Local(argsOnly = true) BlockSource blockSource,@Local(argsOnly = true) ItemStack itemStack){
        ServerLevel serverLevel = blockSource.level();
        BlockPos blockPos = blockSource.pos().relative(blockSource.state().getValue(DispenserBlock.FACING));
        BlockState blockState = serverLevel.getBlockState(blockPos);
        if(blockState.is(ModBlocks.COCONUT_MILK_CAULDRON.get())){
            LayeredCauldronBlock.lowerFillLevel(blockState,serverLevel,blockPos);
            this.setSuccess(true);
            return  (this.takeLiquid(blockSource,itemStack,new ItemStack(ModItems.COCONUT_MILK_BOTTLE.get())));
        }

        return original;
    }
}
