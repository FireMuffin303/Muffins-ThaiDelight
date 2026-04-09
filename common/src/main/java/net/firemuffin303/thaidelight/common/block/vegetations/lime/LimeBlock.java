package net.firemuffin303.thaidelight.common.block.vegetations.lime;

import net.firemuffin303.thaidelight.common.block.AbstractStackableBlock;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LimeBlock extends AbstractStackableBlock {

    public static final IntegerProperty STACKS = IntegerProperty.create("limes",1,4);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public LimeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(WATERLOGGED,false)
                .setValue(STACKS,1)
                .setValue(FACING,Direction.NORTH)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED,STACKS,FACING);
    }

    @Override
    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    @Override
    protected BlockState modifyStatePlacement(BlockPlaceContext blockPlaceContext, BlockState blockState) {
        return super.modifyStatePlacement(blockPlaceContext,blockState).setValue(FACING,blockPlaceContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return switch (blockState.getValue(STACKS)) {
            case 2 -> Block.box(3.0, 0.0, 3.0, 13.0, 4.0, 13.0);
            case 3 -> Block.box(2.0, 0.0, 2.0, 14.0, 4.0, 14.0);
            case 4 -> Block.box(2.0, 0.0, 2.0, 14.0, 8.0, 14.0);
            default -> Block.box(6.0, 0.0, 6.0, 10.0, 4.0, 10.0);
        };
    }

    @Override
    protected int getMaxStack() {
        return 4;
    }

    @Override
    public IntegerProperty getStackProperty() {
        return STACKS;
    }

    @Override
    public ItemLike getPickUpItem() {
        return ModItems.LIME.get();
    }
}
