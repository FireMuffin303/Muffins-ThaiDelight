package net.firemuffin303.thaidelight.common.block.vegetations.papaya;

import com.mojang.serialization.MapCodec;
import net.firemuffin303.thaidelight.common.block.ModBlockStateProperties;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
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
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class PapayaLeavesBlock extends BushBlock implements SimpleWaterloggedBlock, BonemealableBlock {
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty PAPAYA_LEAVES_FACING = ModBlockStateProperties.PAPAYA_LEAVES_FACING;

    public static final MapCodec<PapayaLeavesBlock> CODEC = simpleCodec(PapayaLeavesBlock::new);

    public PapayaLeavesBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(WATERLOGGED,false)
                .setValue(PAPAYA_LEAVES_FACING,Direction.UP)
        );
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED,PAPAYA_LEAVES_FACING);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        boolean bl = blockState.getValue(PAPAYA_LEAVES_FACING) == Direction.UP;

        return bl ? Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0) : Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        Direction direction = blockState.getValue(PAPAYA_LEAVES_FACING);
        BlockState parentState = levelReader.getBlockState(blockPos.relative(direction.getOpposite()));
        return mayPlaceOn(parentState,levelReader,blockPos,direction);
    }

    public boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos,Direction direction) {
        if(blockState.is(ModBlocks.PAPAYA_LEAVES.get()) || blockState.is(ModBlocks.PAPAYA_LEAVES_STEM.get())){
            return blockState.getValue(PAPAYA_LEAVES_FACING) == direction;
        }

        return blockState.isFaceSturdy(blockGetter,blockPos,direction) || super.mayPlaceOn(blockState, blockGetter, blockPos);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }


        if(!blockState.canSurvive(levelAccessor,blockPos)){
            return Blocks.AIR.defaultBlockState();
        } else if(direction == blockState.getValue(PAPAYA_LEAVES_FACING)){
            if(blockState2.is(this)){
                return ModBlocks.PAPAYA_LEAVES_STEM.get().defaultBlockState()
                        .setValue(PapayaLeavesStemBlock.PAPAYA_LEAVES_FACING,blockState.getValue(PAPAYA_LEAVES_FACING));
            }
        }

        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        FluidState fluidState = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());
        boolean bl = fluidState.getType() == Fluids.WATER;

        BlockState placeState = super.getStateForPlacement(blockPlaceContext);
        if(bl){
            placeState.setValue(WATERLOGGED,bl);
        }

        Direction direction = blockPlaceContext.getClickedFace();
        if(direction != Direction.DOWN){
            return placeState.setValue(PAPAYA_LEAVES_FACING,direction);
        }
        return null;
    }

    @Override
    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        Direction direction = blockState.getValue(PapayaLeavesBlock.PAPAYA_LEAVES_FACING);
        return levelReader.getBlockState(blockPos.relative(direction)).is(Blocks.AIR);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        Direction direction = blockState.getValue(PAPAYA_LEAVES_FACING);

        serverLevel.setBlock(blockPos,ModBlocks.PAPAYA_LEAVES_STEM.get().defaultBlockState()
                .setValue(PapayaLeavesStemBlock.PAPAYA_LEAVES_FACING,direction),2);

        serverLevel.setBlock(blockPos.relative(direction,1),
                blockState.setValue(PapayaLeavesBlock.PAPAYA_LEAVES_FACING,direction),
                2
        );
    }
}
