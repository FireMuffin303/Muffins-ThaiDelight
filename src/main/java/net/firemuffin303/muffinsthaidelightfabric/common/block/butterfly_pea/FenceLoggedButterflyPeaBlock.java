package net.firemuffin303.muffinsthaidelightfabric.common.block.butterfly_pea;

import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.firemuffin303.muffinsthaidelightfabric.common.block.blockEntity.FenceLoggedButterflyPeaBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class FenceLoggedButterflyPeaBlock extends FenceBlock implements EntityBlock {
    public FenceLoggedButterflyPeaBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!level.isClientSide) {
            ItemStack itemStack = player.getItemInHand(interactionHand);
            if (itemStack.is(ConventionalItemTags.SHEARS)) {
                setNormalFence(level,blockPos,blockState);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }

        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    public boolean triggerEvent(BlockState blockState, Level level, BlockPos blockPos, int i, int j) {
        super.triggerEvent(blockState, level, blockPos, i, j);
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity == null) {
            return false;
        }
        return blockEntity.triggerEvent(i, j);
    }

    private void setNormalFence(Level level,BlockPos blockPos,BlockState currentState){
        BlockState fenceState = Blocks.OAK_FENCE.defaultBlockState();
       if(level.getBlockEntity(blockPos) instanceof FenceLoggedButterflyPeaBlockEntity fenceLoggedButterflyPeaBlockEntity){
           fenceState = fenceLoggedButterflyPeaBlockEntity.blockState;
       }

        fenceState = fenceState.setValue(NORTH,currentState.getValue(NORTH))
                .setValue(EAST,currentState.getValue(EAST))
                .setValue(SOUTH,currentState.getValue(SOUTH))
                .setValue(WEST,currentState.getValue(WEST))
                .setValue(WATERLOGGED,currentState.getValue(WATERLOGGED));

        level.setBlock(blockPos,fenceState,3);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new FenceLoggedButterflyPeaBlockEntity(blockPos,blockState);
    }
}
