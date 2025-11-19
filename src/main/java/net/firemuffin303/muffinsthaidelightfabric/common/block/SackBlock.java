package net.firemuffin303.muffinsthaidelightfabric.common.block;

import net.firemuffin303.muffinsthaidelightfabric.common.block.blockEntity.SackBlockEntity;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SackBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    private static final VoxelShape BOX = Block.box(1.0,0.0,1.0,15.0,16.0,15.0);
    public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final ResourceLocation CONTENTS = new ResourceLocation("contents");
    public SackBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(HORIZONTAL_FACING,Direction.NORTH)
                .setValue(WATERLOGGED,false)
                .setValue(OPEN,false)
        );
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(level.isClientSide){
            return InteractionResult.SUCCESS;
        }
        BlockEntity block = level.getBlockEntity(blockPos);
        if(block instanceof SackBlockEntity sackBlockEntity){
            player.openMenu(sackBlockEntity);
            PiglinAi.angerNearbyPiglins(player, true);
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        BlockEntity blockEntity = serverLevel.getBlockEntity(blockPos);
        if (blockEntity instanceof SackBlockEntity sackBlockEntity) {
            sackBlockEntity.recheckOpen();
        }
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity instanceof SackBlockEntity sackBlockEntity) {
            if (!level.isClientSide && player.isCreative() && !sackBlockEntity.isEmpty()) {
                ItemStack itemStack = new ItemStack(ModItems.SACK);
                blockEntity.saveToItem(itemStack);
                if (sackBlockEntity.hasCustomName()) {
                    itemStack.setHoverName(sackBlockEntity.getCustomName());
                }
                ItemEntity itemEntity = new ItemEntity(level, (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5, itemStack);
                itemEntity.setDefaultPickUpDelay();
                level.addFreshEntity(itemEntity);
            } else {
                sackBlockEntity.unpackLootTable(player);
            }
        }
        super.playerWillDestroy(level, blockPos, blockState, player);
    }

    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        BlockEntity blockEntity = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof SackBlockEntity sackBlockEntity) {
            builder = builder.withDynamicDrop(CONTENTS, consumer -> {
                for (int i = 0; i < sackBlockEntity.getContainerSize(); ++i) {
                    consumer.accept(sackBlockEntity.getItem(i));
                }
            });
        }
        return super.getDrops(blockState, builder);
    }

    public boolean hasAnalogOutputSignal(BlockState blockState) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(blockPos));
    }

    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(HORIZONTAL_FACING, rotation.rotate(blockState.getValue(HORIZONTAL_FACING)));
    }

    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(HORIZONTAL_FACING)));
    }

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        LevelAccessor levelAccessor = blockPlaceContext.getLevel();
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        return this.defaultBlockState()
                .setValue(WATERLOGGED, levelAccessor.getFluidState(blockPos).getType() == Fluids.WATER)
                .setValue(HORIZONTAL_FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING,WATERLOGGED,OPEN);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return BOX;
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SackBlockEntity(blockPos,blockState);
    }
}
