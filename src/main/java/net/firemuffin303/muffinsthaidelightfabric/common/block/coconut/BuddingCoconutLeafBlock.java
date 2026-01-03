package net.firemuffin303.muffinsthaidelightfabric.common.block.coconut;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
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
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BuddingCoconutLeafBlock extends Block implements SimpleWaterloggedBlock, BonemealableBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty COCONUT = BooleanProperty.create("coconut");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public BuddingCoconutLeafBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(COCONUT,false)
                .setValue(WATERLOGGED,false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, COCONUT,WATERLOGGED);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(!level.isClientSide){
            if(blockState.getValue(COCONUT)){
                level.playSound(null,blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS);
                if(level.getBlockState(blockPos.below()).isAir()){
                    this.harvest((ServerLevel) level,blockPos,blockState,player);
                }else{
                    popResource(level,blockPos,new ItemStack(ModItems.COCONUT));
                    level.setBlock(blockPos,blockState.setValue(COCONUT,false),2);
                }
                return InteractionResult.SUCCESS;
            }
        }

        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if(serverLevel.getMaxLocalRawBrightness(blockPos.above()) >= 9 && randomSource.nextInt(7) == 0 && !blockState.getValue(COCONUT)){
            serverLevel.setBlock(blockPos,blockState.setValue(COCONUT,true),2);
        }
    }

    @Override
    public void onProjectileHit(Level level, BlockState blockState, BlockHitResult blockHitResult, Projectile projectile) {
        BlockPos blockPos = blockHitResult.getBlockPos();
        if (!level.isClientSide && blockState.getValue(COCONUT) && projectile.mayInteract(level, blockPos) && projectile.getType().is(EntityTypeTags.IMPACT_PROJECTILES)) {
            level.playSound(null,blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS);
            if(level.getBlockState(blockPos.below()).isAir()){
                this.harvest((ServerLevel) level,blockPos,blockState,projectile.getOwner());
            }else{
                popResource(level,blockPos,new ItemStack(ModItems.COCONUT));
                level.setBlock(blockPos,blockState.setValue(COCONUT,false),2);
            }
        }
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        Direction direction = blockState.getValue(FACING).getOpposite();
        BlockPos blockPos2 = blockPos.relative(direction);
        BlockState blockState2 = levelReader.getBlockState(blockPos2);
        return blockState2.isFaceSturdy(levelReader, blockPos2, direction) || ( blockState2.is(ModBlocks.COCONUT_LEAF) || blockState2.is(ModBlocks.BUDDING_COCONUT_LEAF) );
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
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
        VoxelShape shape;
        shape = switch (blockState.getValue(FACING)){
            case NORTH -> Block.box(3.0, 4.0, 6.0,13.0 , 14.0, 16.0);
            case SOUTH -> Block.box(3.0, 4.0, 0.0,13.0 , 14.0, 10.0);
            case WEST -> Block.box(6.0, 4.0, 3.0, 16.0, 14.0, 13.0);
            default -> Block.box(0.0, 4.0, 3.0, 10.0, 14.0, 13.0);
        };

        return blockState.getValue(COCONUT) ? Shapes.or(
                Block.box(0.0, 13.0, 0.0, 16.0, 16.0, 16.0), shape) :
                Block.box(0.0, 13.0, 0.0, 16.0, 16.0, 16.0);
    }

    private void harvest(ServerLevel level, BlockPos blockPos, BlockState blockState, Entity entity) {
        FallingBlockEntity fallingBlockEntity = FallingBlockEntity.fall(level, blockPos, ModBlocks.COCONUT.defaultBlockState());
        fallingBlockEntity.setHurtsEntities(0.2f,4);
        level.setBlock(blockPos,blockState.setValue(COCONUT,false),2);
    }


    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
        return !blockState.getValue(COCONUT);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        if(blockState.getValue(COCONUT)){
            return;
        }

        serverLevel.setBlock(blockPos,blockState.setValue(COCONUT,true),2);
    }
}
