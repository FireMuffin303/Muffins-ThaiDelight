package net.firemuffin303.muffinsthaidelightfabric.mixin.dispenser;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(targets = "net.minecraft.core.dispenser.DispenseItemBehavior$24")
public abstract class DispenseItemBehaviorMixin extends OptionalDispenseItemBehavior {


    @Shadow protected abstract ItemStack takeLiquid(BlockSource par1, ItemStack par2, ItemStack par3);

    @Inject(method = "execute",at = @At(value = "INVOKE", target = "Lnet/minecraft/core/dispenser/OptionalDispenseItemBehavior;execute(Lnet/minecraft/core/BlockSource;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;"), cancellable = true)
    public void muffins$takeCoconutMilk(BlockSource blockSource, ItemStack itemStack, CallbackInfoReturnable<ItemStack> cir){
        ServerLevel serverLevel = blockSource.getLevel();
        BlockPos blockPos = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));
        BlockState blockState = serverLevel.getBlockState(blockPos);
        if(blockState.is(ModBlocks.COCONUT_MILK_CAULDRON)){
            LayeredCauldronBlock.lowerFillLevel(blockState,serverLevel,blockPos);
            this.setSuccess(true);
            cir.setReturnValue(this.takeLiquid(blockSource,itemStack,new ItemStack(ModItems.COCONUT_MILK_BOTTLE)));
        }

    }
}
