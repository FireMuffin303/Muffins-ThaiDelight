package net.firemuffin303.muffinsthaidelightfabric.common.block.papaya;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class PapayaFlowerBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock, SuspiciousEffectHolder, BonemealableBlock {
    public static final IntegerProperty FLOWERS = IntegerProperty.create("flowers",1,3);
    public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public PapayaFlowerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED,false)
                .setValue(FLOWERS,1)
                .setValue(HANGING,false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED,FLOWERS,HANGING);
    }

    @Override
    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Override
    public BlockState updateShape(
            BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2
    ) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        return direction == blockState.getValue(FACING).getOpposite() && !blockState.canSurvive(levelAccessor, blockPos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Override
    public boolean canBeReplaced(BlockState blockState, BlockPlaceContext blockPlaceContext) {
        if(!blockPlaceContext.isSecondaryUseActive() && blockPlaceContext.getItemInHand().getItem() == this.asItem() && blockState.getValue(FLOWERS) < 3){
            return true;
        }
        return super.canBeReplaced(blockState,blockPlaceContext);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        LevelAccessor levelAccessor = blockPlaceContext.getLevel();
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        FluidState fluidState = levelAccessor.getFluidState(blockPos);

        BlockState blockState = levelAccessor.getBlockState(blockPos);
        if (blockState.is(this)) {
            return blockState.cycle(FLOWERS);
        }

        for (Direction direction : blockPlaceContext.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                BlockState theBlockState = this.defaultBlockState().setValue(HANGING, direction == Direction.UP);
                if (theBlockState.canSurvive(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos())) {
                    return theBlockState
                            .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER)
                            .setValue(FACING,blockPlaceContext.getHorizontalDirection().getOpposite());
                }
            }
        }

        return null;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return blockState.getValue(HANGING) ? Block.box(0,14,0,16,16,16) : Block.box(0,0,0,16,2,16);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        Direction direction = (blockState.getValue(HANGING) ? Direction.DOWN : Direction.UP).getOpposite();
        return Block.canSupportCenter(levelReader, blockPos.relative(direction), direction.getOpposite());
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        int flowers = blockState.getValue(FLOWERS);
        if(blockState.getValue(FLOWERS) < 3){
            serverLevel.setBlock(blockPos,blockState.setValue(FLOWERS,flowers+1),2);
            return;
        }
        popResource(serverLevel,blockPos,new ItemStack(ModItems.PAPAYA_FLOWER));
    }

    @Override
    public MobEffect getSuspiciousEffect() {
        return MobEffects.REGENERATION;
    }

    @Override
    public int getEffectDuration() {
        return 3*20;
    }
}
