package net.firemuffin303.muffinsthaidelightfabric.common.block.durian;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DurianBlock extends Block {
    private static final VoxelShape BOX = Block.box(2.0,0.0,2.0,14.0,14.0,14.0);
    public DurianBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return BOX;
    }
}
