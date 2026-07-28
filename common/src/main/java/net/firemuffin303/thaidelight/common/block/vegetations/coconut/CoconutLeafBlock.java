package net.firemuffin303.thaidelight.common.block.vegetations.coconut;

import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.util.ModUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
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
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CoconutLeafBlock extends Block implements SimpleWaterloggedBlock,BonemealableBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty END = BooleanProperty.create("end");

    public CoconutLeafBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED,false)
                .setValue(END,true)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED,END);
    }

    // State Logic
    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        Direction direction = blockState.getValue(FACING).getOpposite();
        BlockPos blockPos2 = blockPos.relative(direction);
        BlockState blockState2 = levelReader.getBlockState(blockPos2);
        return blockState2.isFaceSturdy(levelReader, blockPos2, direction) || ((blockState2.is(ModBlocks.COCONUT_LEAF.get()) || blockState2.is(ModBlocks.BUDDING_COCONUT_LEAF.get()) ) && blockState2.getValue(FACING) == blockState.getValue(FACING) );
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        if(direction == blockState.getValue(FACING)){
            boolean bl = false;
            if(blockState2.is(this)){
                bl = blockState2.getValue(FACING) == blockState.getValue(FACING);
            }
            return blockState.setValue(END,!bl);
        }

        if ((direction == blockState.getValue(FACING) || direction == blockState.getValue(FACING).getOpposite() && !blockState.canSurvive(levelAccessor, blockPos))) {
            levelAccessor.scheduleTick(blockPos, this, 1);
        }

        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (!blockState.canSurvive(serverLevel, blockPos)) {
            serverLevel.destroyBlock(blockPos, true);
        }
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        LevelAccessor levelAccessor = blockPlaceContext.getLevel();
        BlockPos blockPos = blockPlaceContext.getClickedPos();


        return this.defaultBlockState()
                .setValue(WATERLOGGED, Boolean.valueOf(levelAccessor.getFluidState(blockPos).getType() == Fluids.WATER))
                .setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
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
    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return blockState.getValue(END) ? Shapes.empty() : Block.box(0.0, 13.0, 0.0, 16.0, 16.0, 16.0);
    }

    protected static boolean canReplace(BlockState blockState) {
        return blockState.isAir() || blockState.is(Blocks.WATER);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        Direction direction = blockState.getValue(FACING);
        if(!blockState.getValue(END)){
            Optional<BlockPos> optional = ModUtils.getTopConnectedBlock(
                    levelReader,
                    blockPos,
                    blockState.setValue(CoconutLeafBlock.FACING,direction).setValue(END,false),
                    blockState.getValue(FACING),
                    blockState.setValue(FACING,direction).setValue(END,true));

            if(optional.isEmpty()){
                return false;
            }

            BlockPos blockPos2 = optional.get().relative(direction);
            BlockState blockState2 = levelReader.getBlockState(blockPos2);
            return !levelReader.isOutsideBuildHeight(blockPos) && canReplace(blockState2);

        }

        BlockPos blockPos2 = blockPos.relative(direction);
        BlockState blockState2 = levelReader.getBlockState(blockPos2);
        return !levelReader.isOutsideBuildHeight(blockPos) && canReplace(blockState2);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        Direction direction = blockState.getValue(FACING);
        if(!blockState.getValue(END)){
            Optional<BlockPos> optional = ModUtils.getTopConnectedBlock(
                    serverLevel,
                    blockPos,
                    blockState.setValue(CoconutLeafBlock.FACING,direction).setValue(END,false),
                    blockState.getValue(FACING),
                    blockState.setValue(FACING,direction).setValue(END,true));

            if(optional.isEmpty()){
                return;
            }

            BlockPos blockPos2 = optional.get();
            serverLevel.setBlock(blockPos2,blockState.setValue(CoconutLeafBlock.FACING,direction).setValue(END,false),2);

            serverLevel.setBlock(blockPos2.relative(direction,1), blockState.setValue(CoconutLeafBlock.FACING,direction).setValue(END,true), 2);
            return;
        }

        serverLevel.setBlock(blockPos,blockState.setValue(CoconutLeafBlock.FACING,direction).setValue(END,false),2);

        serverLevel.setBlock(blockPos.relative(direction,1), blockState.setValue(CoconutLeafBlock.FACING,direction).setValue(END,true), 2
        );
    }

    @Override
    public Item asItem() {
        return ModItems.COCONUT_LEAF.get();
    }
}
