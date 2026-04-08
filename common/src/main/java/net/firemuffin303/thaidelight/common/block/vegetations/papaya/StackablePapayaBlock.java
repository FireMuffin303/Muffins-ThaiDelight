package net.firemuffin303.thaidelight.common.block.vegetations.papaya;

import net.firemuffin303.thaidelight.common.block.AbstractStackableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class StackablePapayaBlock extends AbstractStackableBlock {
    public static final IntegerProperty STACKS = IntegerProperty.create("papayas",1,2);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private final Supplier<ItemLike> itemLike;

    public StackablePapayaBlock(BlockBehaviour.Properties properties, Supplier<ItemLike> item) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(WATERLOGGED,false)
                .setValue(STACKS,1)
                .setValue(FACING, Direction.NORTH));

        this.itemLike = item;
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
        boolean bl = blockState.getValue(FACING) == Direction.EAST || blockState.getValue(FACING) == Direction.WEST;
        if(blockState.getValue(STACKS) == 2){
            return bl ? Block.box(3.0, 0.0, 1.0, 14.0, 6.0, 15.0) : Block.box(1.0, 0.0, 2.0, 15.0, 6.0, 13.0);
        }

        return bl ? Block.box(3.0, 0.0, 5.0, 13.0, 6.0, 11.0) : Block.box(5.0, 0.0, 3.0, 11.0, 6.0, 13.0);
    }

    @Override
    protected int getMaxStack() {
        return 2;
    }

    @Override
    public IntegerProperty getStackProperty() {
        return STACKS;
    }

    @Override
    public ItemLike getPickUpItem() {
        return this.itemLike.get();
    }
}
