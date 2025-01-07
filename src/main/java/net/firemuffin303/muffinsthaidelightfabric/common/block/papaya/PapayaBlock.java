package net.firemuffin303.muffinsthaidelightfabric.common.block.papaya;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class PapayaBlock extends HorizontalDirectionalBlock implements BonemealableBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    protected static final VoxelShape[] EAST_AABB;
    protected static final VoxelShape[] WEST_AABB;
    protected static final VoxelShape[] NORTH_AABB;
    protected static final VoxelShape[] SOUTH_AABB;

    public PapayaBlock(Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH)).setValue(AGE, 0));
    }

    public boolean isRandomlyTicking(BlockState blockState) {
        return (Integer)blockState.getValue(AGE) < 2;
    }

    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (serverLevel.random.nextInt(5) == 0) {
            int i = (Integer)blockState.getValue(AGE);
            if (i < 2) {
                serverLevel.setBlock(blockPos, (BlockState)blockState.setValue(AGE, i + 1), 2);
            }
        }
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        int i = (Integer)blockState.getValue(AGE);
        boolean flag = i == 2;
        if (!flag && player.getItemInHand(interactionHand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (i > 0) {
            popResource(level, blockPos, new ItemStack(flag ? ModItems.PAPAYA : ModItems.RAW_PAPAYA, 1));
            level.playSound((Player)null, blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            BlockState blockstate = Blocks.AIR.defaultBlockState();
            level.setBlock(blockPos, blockstate, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, blockstate));
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
        }
    }

    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockState blockState2 = levelReader.getBlockState(blockPos.relative((Direction)blockState.getValue(FACING)));
        return blockState2.is(ModBlocks.PAPAYA_LOG);
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        int i = (Integer)blockState.getValue(AGE);
        switch((Direction)blockState.getValue(FACING)) {
            case SOUTH:
                return SOUTH_AABB[i];
            case NORTH:
            default:
                return NORTH_AABB[i];
            case WEST:
                return WEST_AABB[i];
            case EAST:
                return EAST_AABB[i];
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockState blockState = this.defaultBlockState();
        LevelReader levelReader = blockPlaceContext.getLevel();
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        Direction[] var5 = blockPlaceContext.getNearestLookingDirections();
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Direction direction = var5[var7];
            if (direction.getAxis().isHorizontal()) {
                blockState = (BlockState)blockState.setValue(FACING, direction);
                if (blockState.canSurvive(levelReader, blockPos)) {
                    return blockState;
                }
            }
        }

        return null;
    }

    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        return direction == blockState.getValue(FACING) && !blockState.canSurvive(levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
        return (Integer)blockState.getValue(AGE) < 2;
    }

    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        serverLevel.setBlock(blockPos, (BlockState)blockState.setValue(AGE, (Integer)blockState.getValue(AGE) + 1), 2);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, AGE});
    }

    public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType) {
        return false;
    }

    static {
        EAST_AABB = new VoxelShape[]{Block.box(9.0D, 5.0D, 6.0D, 13.0D, 10.D, 10.0D), Block.box(8.0D, 0.0D, 5.0D, 14.0D, 10.0D, 11.0D), Block.box(8.0D, 0.0D, 5.0D, 14.0D, 10.0D, 11.0D)};
        WEST_AABB = new VoxelShape[]{Block.box(3.0D, 5.0D, 6.0D, 7.0D, 10.0D, 10.0D), Block.box(2.0D, 0.0D, 5.0D, 8.0D, 10.0D, 11.0D), Block.box(2.0D, 0.0D, 5.0D, 8.0D, 10.0D, 11.0D)};
        NORTH_AABB = new VoxelShape[]{Block.box(6.0D, 5.0D, 3.0D, 10.0D, 10.0D, 7.0D), Block.box(5.0D, 0.0D, 2.0D, 11.0D, 10.0D, 8.0D), Block.box(5.0D, 0.0D, 2.0D, 11.0D, 10.0D, 8.0D)};
        SOUTH_AABB = new VoxelShape[]{Block.box(6.0D, 5.0D, 9.0D, 10.0D, 10.0D, 13.0D), Block.box(5.0D, 0.0D, 8.0D, 11.0D, 10.0D, 14.0D), Block.box(5.0D, 0.0D, 8.0D, 11.0D, 10.0D, 14.0D)};

    }

}
