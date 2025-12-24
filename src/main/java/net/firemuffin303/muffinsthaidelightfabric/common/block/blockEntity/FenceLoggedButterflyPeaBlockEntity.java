package net.firemuffin303.muffinsthaidelightfabric.common.block.blockEntity;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FenceLoggedButterflyPeaBlockEntity extends BlockEntity {
    public BlockState blockState;

    public FenceLoggedButterflyPeaBlockEntity( BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntityTypes.FENCE_LOGGED_BUTTERFLY_PEA_BLOCK_ENTITY, blockPos, blockState);
        this.blockState = Blocks.OAK_FENCE.defaultBlockState();
    }
}
