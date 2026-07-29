package net.firemuffin303.thaidelight.common.block.vegetations.durian;

import com.mojang.serialization.MapCodec;
import net.firemuffin303.muffinsmcapi.api.extension.Stackable;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SmallDurianBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock, Stackable {
    public static IntegerProperty STACKS = IntegerProperty.create("durians",1,3);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final MapCodec<SmallDurianBlock> CODEC = simpleCodec(SmallDurianBlock::new);

    public SmallDurianBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(WATERLOGGED,false)
                .setValue(STACKS,1)
                .setValue(FACING,Direction.NORTH)
        );
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        if(level.isClientSide){
            if(takeItem(level, blockPos, blockState, player).consumesAction()){
                return InteractionResult.SUCCESS;
            }
        }

        return takeItem(level, blockPos, blockState, player);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED,STACKS,FACING);
    }

    @Override
    public boolean canBeReplaced(BlockState blockState, BlockPlaceContext blockPlaceContext) {
        if(!blockPlaceContext.isSecondaryUseActive() && blockPlaceContext.getItemInHand().getItem() == this.asItem() && blockState.getValue(STACKS) < 3){
            return true;
        }
        return super.canBeReplaced(blockState,blockPlaceContext);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return Block.canSupportCenter(levelReader, blockPos.below(), Direction.UP);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        return !blockState.canSurvive(levelAccessor,blockPos) ? Blocks.AIR.defaultBlockState() :
                super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockState blockState = blockPlaceContext.getLevel().getBlockState(blockPlaceContext.getClickedPos());
        if (blockState.is(this)) {
            return blockState.cycle(STACKS);
        }

        FluidState fluidState = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());
        boolean bl = fluidState.getType() == Fluids.WATER;
        return super.getStateForPlacement(blockPlaceContext).setValue(WATERLOGGED, bl)
                .setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        boolean bl = blockState.getValue(FACING) == Direction.EAST || blockState.getValue(FACING) == Direction.WEST;
        return switch (blockState.getValue(STACKS)) {
            case 2 -> bl ? Block.box(2.0, 0.0, 0.0, 14.0, 8.0, 16.0) : Block.box(0.0, 0.0, 2.0, 16.0, 8.0, 14.0);
            case 3 -> bl ? Block.box(2.0, 0.0, 0.0, 14.0, 16.0, 16.0) : Block.box(0.0, 0.0, 2.0, 16.0, 16.0, 14.0);
            default -> Block.box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);
        };
    }

    @Override
    public IntegerProperty getStackProperty() {
        return STACKS;
    }

    @Override
    public ItemLike getPickUpItem() {
        return ModItems.SMALL_DURIAN.get();
    }
}
