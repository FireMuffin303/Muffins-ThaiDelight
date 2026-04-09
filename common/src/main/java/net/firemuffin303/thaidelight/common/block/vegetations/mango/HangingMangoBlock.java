package net.firemuffin303.thaidelight.common.block.vegetations.mango;

import net.firemuffin303.thaidelight.common.block.FallableExtension;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
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
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HangingMangoBlock extends FallingBlock implements SimpleWaterloggedBlock, BonemealableBlock, FallableExtension {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    protected static final VoxelShape[] SHAPES = {
            Block.box(6.0, 0.0, 6.0,10.0, 6.0, 10.0),
            Block.box(5.0, 0.0, 5.0,11.0,8.0,11.0),
            Block.box(5.0, 0.0, 5.0,11.0,8.0,11.0)
    };
    protected static final VoxelShape[] HANGING_SHAPES = {
            Block.box(6.0, 9.0, 6.0,10.0, 15.0, 10.0),
            Block.box(5.0, 6.0, 5.0,11.0, 14.0, 11.0),
            Block.box(5.0, 6.0, 5.0,11.0, 14.0, 11.0)
    };

    public HangingMangoBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(WATERLOGGED,false)
                .setValue(AGE,0)
                .setValue(FACING,Direction.NORTH)
        );
    }


    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return levelReader.getBlockState(blockPos.above()).is(BlockTags.LEAVES) || levelReader.getBlockState(blockPos.above()).isFaceSturdy(levelReader, blockPos.above(), Direction.DOWN, SupportType.CENTER);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        return new ItemStack(ModItems.MANGO.get());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED).add(AGE).add(FACING);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(!level.isClientSide){
            if(blockState.getValue(AGE) >= 2 && level.getBlockState(blockPos.above()).is(ModBlocks.MANGO_LEAVES.get())){
                this.harvest((ServerLevel) level,blockPos,blockState,player);
                return InteractionResult.SUCCESS;
            }
        }

        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (
                shouldFall(serverLevel.getBlockState(blockPos.above())) &&
                blockPos.getY() >= serverLevel.getMinBuildHeight() &&
                blockState.getValue(AGE) == 2
        ) {
            FallingBlockEntity fallingBlockEntity = FallingBlockEntity.fall(serverLevel, blockPos, blockState);
            this.falling(fallingBlockEntity);
        }
    }

    @Override
    public void onProjectileHit(Level level, BlockState blockState, BlockHitResult blockHitResult, Projectile projectile) {
        BlockPos blockPos = blockHitResult.getBlockPos();
        if (!level.isClientSide && projectile.mayInteract(level, blockPos) &&
                projectile.getType().is(EntityTypeTags.IMPACT_PROJECTILES) &&
                level.getBlockState(blockPos.below()).isAir() && blockState.getValue(AGE) == 2) {

            FallingBlockEntity fallingBlockEntity = FallingBlockEntity.fall(level, blockPos, blockState);
            this.falling(fallingBlockEntity);
            level.setBlock(blockPos,blockState.setValue(AGE,0),2);
        }
    }


    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        //cancel particle
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if(blockState.getValue(AGE) < 2 && randomSource.nextInt(7) == 0 && serverLevel.getBlockState(blockPos.above()).is(ModBlocks.MANGO_LEAVES.get())){
            serverLevel.setBlock(blockPos,blockState.cycle(AGE),2);
        }
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        if (!blockState.canSurvive(levelAccessor, blockPos) && blockState.getValue(AGE) < 2) {
            return Blocks.AIR.defaultBlockState();
        }

        if ((Boolean)blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }


        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        int age = blockState.getValue(AGE);
        return HANGING_SHAPES[age];
    }

    @Override
    public FluidState getFluidState(BlockState blockState) {
        return (Boolean)blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    private static boolean shouldFall(BlockState blockState){
        return (blockState.isAir() || blockState.is(BlockTags.FIRE) || blockState.liquid() || blockState.canBeReplaced());
    }


    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
        return blockState.getValue(AGE) < 2;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return blockState.getValue(AGE) < 2;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        if(blockState.getValue(AGE) < 2){
            serverLevel.setBlock(blockPos,blockState.cycle(AGE),2);
        }
    }

    private void harvest(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState, Entity entity){
        serverLevel.playSound(null,blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS);
        BlockState afterHarvest = blockState.setValue(AGE,0);
        serverLevel.setBlock(blockPos,afterHarvest,2);
        popResource(serverLevel,blockPos,new ItemStack(ModItems.MANGO.get()));
        serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(entity, afterHarvest));
    }

    @Override
    public boolean checkFallenOnBlock(Level level, BlockState fallingBlock, BlockState fallenOnBlock, BlockPos blockPos) {
        if((fallenOnBlock.is(ModBlocks.STACKABLE_MANGO_BLOCK.get()) && fallenOnBlock.getValue(StackableMangoBlock.STACKS) < 3)){
            return true;
        }
         return level.getBlockState(blockPos.below()).isFaceSturdy(level,blockPos.below(), Direction.UP) && fallenOnBlock.is(Blocks.AIR);
    }

    @Override
    public boolean onLandOnBlock(Level level, BlockState fallingBlock,BlockState fallenOnBlock, BlockPos blockPos) {
        if(!fallenOnBlock.is(ModBlocks.STACKABLE_MANGO_BLOCK.get())){
            BlockState blockState = ModBlocks.STACKABLE_MANGO_BLOCK.get().defaultBlockState();
            level.setBlock(blockPos,blockState.setValue(StackableMangoBlock.FACING,fallingBlock.getValue(FACING)).setValue(StackableMangoBlock.STACKS,1),2);
            return true;
        }

        level.setBlock(blockPos,fallenOnBlock.cycle(StackableMangoBlock.STACKS),2);
        return true;
    }
}
