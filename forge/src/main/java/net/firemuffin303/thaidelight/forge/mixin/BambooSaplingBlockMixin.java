package net.firemuffin303.thaidelight.forge.mixin;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.BambooSaplingBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.common.extensions.IForgeBlock;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BambooSaplingBlock.class)
public abstract class BambooSaplingBlockMixin implements IForgeBlock {


    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if(toolAction.equals(ToolActions.HOE_TILL) && context.getLevel().getBlockState(context.getClickedPos().above()).isAir()){
            Block.popResource(context.getLevel(),context.getClickedPos(),new ItemStack(ModItems.BAMBOO_SHOOT.get()));

            return Blocks.AIR.defaultBlockState();
        }
        return null;

    }
}
