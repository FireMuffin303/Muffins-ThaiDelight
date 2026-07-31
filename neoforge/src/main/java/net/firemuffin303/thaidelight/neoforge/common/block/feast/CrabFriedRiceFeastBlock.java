package net.firemuffin303.thaidelight.neoforge.common.block.feast;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;

public class CrabFriedRiceFeastBlock extends FeastBlock {
    final VoxelShape PLATE_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);
    final VoxelShape PIE_SHAPE = Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(3.0D, 2.0D, 3.0D, 13.0D, 5.0D, 13.0D), BooleanOp.OR);

    public CrabFriedRiceFeastBlock() {
        super(Properties.ofFullCopy(Blocks.CAKE), ModItems.CRAB_FRIED_RICE, true);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        var SHAPE = Shapes.joinUnoptimized(PIE_SHAPE,Block.box(4.0D, 5.0D, 4.0D, 12.0D, 8.0D, 12.0D), BooleanOp.OR);

        return (Integer)state.getValue(SERVINGS) == 0 ? PLATE_SHAPE : SHAPE;
    }
}
