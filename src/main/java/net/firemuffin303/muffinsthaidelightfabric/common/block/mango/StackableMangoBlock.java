package net.firemuffin303.muffinsthaidelightfabric.common.block.mango;

import net.firemuffin303.muffinsthaidelightfabric.common.block.AbstractStackableBlock;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StackableMangoBlock extends AbstractStackableBlock {
    public static final IntegerProperty STACKS = IntegerProperty.create("mangoes",1,3);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public StackableMangoBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(WATERLOGGED,false)
                .setValue(STACKS,1)
                .setValue(FACING, Direction.NORTH));
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
        return switch (blockState.getValue(STACKS)) {
            case 2 -> bl ? Block.box(2.0, 0.0, 0.0, 14.0, 8.0, 16.0) : Block.box(0.0, 0.0, 2.0, 16.0, 8.0, 14.0);
            case 3 -> Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
            default -> switch (blockState.getValue(FACING)){
                case SOUTH -> Block.box(5.0, 0.0, 5.0, 11.0, 8.0, 13.0);
                case WEST -> Block.box(3.0, 0.0, 5.0, 11.0, 8.0, 11.0);
                case EAST -> Block.box(5.0, 0.0, 5.0, 13.0, 8.0, 11.0);
                default -> Block.box(5.0, 0.0, 3.0, 11.0, 8.0, 11.0);
            };
        };
    }

    @Override
    protected int getMaxStack() {
        return 3;
    }

    @Override
    public IntegerProperty getStackProperty() {
        return STACKS;
    }

    @Override
    public ItemLike getPickUpItem() {
        return ModItems.MANGO;
    }
}
