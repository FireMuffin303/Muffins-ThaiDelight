package net.firemuffin303.muffinsthaidelightfabric.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface FallableExtension {

    boolean checkFallenOnBlock(Level level, BlockState fallingBlock,BlockState fallenOnBlock, BlockPos blockPos);

    //return cancel drop
    boolean onLandOnBlock(Level level, BlockState fallingBlock,BlockState fallenOnBlock, BlockPos blockPos);
}
