package net.firemuffin303.thaidelight.common.block.vegetations.papaya;

import com.mojang.serialization.MapCodec;
import net.firemuffin303.muffinsmcapi.api.CommonEvents;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BuddingPapayaFlowerBlock extends HorizontalDirectionalBlock implements BonemealableBlock {
    public static final MapCodec<BuddingPapayaFlowerBlock> CODEC = simpleCodec(BuddingPapayaFlowerBlock::new);
    public BuddingPapayaFlowerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
        );
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockState blockState2 = levelReader.getBlockState(blockPos.relative(blockState.getValue(FACING)));
        return blockState2.is(ModBlocks.PAPAYA_LOG.get());
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        return direction == blockState.getValue(FACING) && !blockState.canSurvive(levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (serverLevel.getRawBrightness(blockPos, 0) >= 9) {
            float f = CommonEvents.getGrowthSpeed(this, serverLevel, blockPos);
            if(randomSource.nextInt((int)(25.0F / f) + 1) == 0){
                serverLevel.setBlock(blockPos,
                        ModBlocks.PAPAYA.get().defaultBlockState()
                                .setValue(FACING,blockState.getValue(FACING))
                                .setValue(PapayaBlock.AGE,1),
                        2
                );
            }

        }

    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return switch (blockState.getValue(FACING)){
            case EAST -> Block.box(14,0,0,16,16,16);
            case WEST -> Block.box(0,0,0,2,16,16);
            case SOUTH -> Block.box(0,0,14,16,16,16);
            default -> Block.box(0,0,0,16,16,2);
        };
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getClickedFace().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        int age = randomSource.nextInt(1,2);
        serverLevel.setBlock(blockPos,
                ModBlocks.PAPAYA.get().defaultBlockState()
                        .setValue(FACING,blockState.getValue(FACING))
                        .setValue(PapayaBlock.AGE,age),
                2
        );
    }
}
