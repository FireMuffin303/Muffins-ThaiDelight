package net.firemuffin303.muffinsthaidelightfabric.common.block;

import net.firemuffin303.muffinsthaidelightfabric.common.entity.FlowerCrabEntity;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CrabEggBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED;
    private static int minHatchTickDelay = 3600;
    private static int maxHatchTickDelay = 12000;
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.5D, 16.0D);

    public CrabEggBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState) this.defaultBlockState().setValue(WATERLOGGED, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return mayPlaceOn(levelReader, blockPos.below());
    }

    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        level.scheduleTick(blockPos, this, getHatchDelay(level.getRandom()));
    }

    private static int getHatchDelay(RandomSource randomSource) {
        return randomSource.nextInt(minHatchTickDelay, maxHatchTickDelay);
    }

    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        if ((Boolean)blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return !this.canSurvive(blockState, levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (!this.canSurvive(blockState, serverLevel, blockPos)) {
            this.destroyBlock(serverLevel, blockPos);
        } else {
            this.hatchFrogspawn(serverLevel, blockPos, randomSource);
        }
    }

    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        if (entity.getType().equals(EntityType.FALLING_BLOCK)) {
            this.destroyBlock(level, blockPos);
        }

    }

    private static boolean mayPlaceOn(BlockGetter blockGetter, BlockPos blockPos) {
        FluidState fluidState = blockGetter.getFluidState(blockPos);
        FluidState fluidState2 = blockGetter.getFluidState(blockPos.above());
        return fluidState2.getType() == Fluids.WATER;
    }

    private void hatchFrogspawn(ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        this.destroyBlock(serverLevel, blockPos);
        serverLevel.playSound((Player)null, blockPos, SoundEvents.FROGSPAWN_HATCH, SoundSource.BLOCKS, 1.0F, 1.0F);
        this.spawnCrabs(serverLevel, blockPos, randomSource);
    }

    private void destroyBlock(Level level, BlockPos blockPos) {
        level.destroyBlock(blockPos, false);
    }

    private void spawnCrabs(ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        int i = randomSource.nextInt(2, 6);
        for(int j = 0 ; j <= i;++j){
            FlowerCrabEntity crabEntity = ModEntityTypes.FLOWER_CRAB.create(serverLevel);
            if (crabEntity != null) {
                double d = (double)blockPos.getX() + this.getRandomPositionOffset(randomSource);
                double e = (double)blockPos.getZ() + this.getRandomPositionOffset(randomSource);
                int k = randomSource.nextInt(1, 361);
                crabEntity.moveTo(d, blockPos.getY(), e, (float)k, 0.0F);
                crabEntity.setBaby(true);
                crabEntity.setPersistenceRequired();
                serverLevel.addFreshEntity(crabEntity);
            }
        }



    }

    private double getRandomPositionOffset(RandomSource randomSource) {
        double d = (double)(0.8f / 2.0F);
        return Mth.clamp(randomSource.nextDouble(), d, 1.0D - d);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        LevelAccessor levelAccessor = blockPlaceContext.getLevel();
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        return (BlockState)((BlockState)this.defaultBlockState().setValue(WATERLOGGED, levelAccessor.getFluidState(blockPos).getType() == Fluids.WATER));
    }

    public FluidState getFluidState(BlockState blockState) {
        return (Boolean)blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    static{
        WATERLOGGED = BlockStateProperties.WATERLOGGED;

    }
}
