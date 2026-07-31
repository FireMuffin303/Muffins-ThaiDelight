package net.firemuffin303.thaidelight.common.block.feast;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;

public class DurianSliceFeastBlock extends FeastBlock {

    final VoxelShape[] PLATE_SHAPE = {
            Block.box(1.0D, 0.0D, 2.0D, 15.0D, 6.0D, 14.0D),
            Block.box(2.0D, 0.0D, 1.0D, 14.0D, 6.0D, 15.0D)
    };
    //final VoxelShape PIE_SHAPE = Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(2.0D, 2.0D, 2.0D, 14.0D, 4.0D, 14.0D), BooleanOp.OR);


    public DurianSliceFeastBlock() {
        super(Properties.ofFullCopy(Blocks.CAKE), ModItems.DURIAN_PULP, true);
    }

    @Override
    protected ItemInteractionResult takeServing(LevelAccessor level, BlockPos pos, BlockState state, Player player, InteractionHand hand) {
        int servings = state.getValue(this.getServingsProperty());
        if(servings == 0){
            popResource((Level) level,pos,new ItemStack(ModItems.DURIAN_PEEL.get(),4));
        }
        return super.takeServing(level, pos, state, player, hand);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {

        if(state.getValue(FACING) == Direction.WEST || state.getValue(FACING) == Direction.EAST){
            return PLATE_SHAPE[0];
        } else if(state.getValue(FACING) == Direction.WEST || state.getValue(FACING) == Direction.EAST){
            return PLATE_SHAPE[1];
        }
        return PLATE_SHAPE[1];
    }
}
