package net.firemuffin303.muffinsthaidelightfabric.common.block.butterfly_pea;

import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.firemuffin303.muffinsthaidelightfabric.common.block.blockEntity.FenceLoggedButterflyPeaBlockEntity;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class FenceLoggedButterflyPeaBlock extends FenceBlock implements EntityBlock,BonemealableBlock {
    public FenceLoggedButterflyPeaBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!level.isClientSide) {
            ItemStack itemStack = player.getItemInHand(interactionHand);
            if (itemStack.is(ConventionalItemTags.SHEARS)) {
                setNormalFence(level,blockPos,blockState);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }

        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    public boolean triggerEvent(BlockState blockState, Level level, BlockPos blockPos, int i, int j) {
        super.triggerEvent(blockState, level, blockPos, i, j);
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity == null) {
            return false;
        }
        return blockEntity.triggerEvent(i, j);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        if(blockGetter.getBlockEntity(blockPos) instanceof FenceLoggedButterflyPeaBlockEntity fenceLoggedButterflyPeaBlockEntity){
            return new ItemStack(fenceLoggedButterflyPeaBlockEntity.fenceState.getBlock().asItem());
        }

        return super.getCloneItemStack(blockGetter, blockPos, blockState);
    }

    private void setNormalFence(Level level, BlockPos blockPos, BlockState currentState){
        BlockState fenceState = Blocks.OAK_FENCE.defaultBlockState();
       if(level.getBlockEntity(blockPos) instanceof FenceLoggedButterflyPeaBlockEntity fenceLoggedButterflyPeaBlockEntity){
           fenceState = fenceLoggedButterflyPeaBlockEntity.fenceState;
       }

        fenceState = fenceState.setValue(NORTH,currentState.getValue(NORTH))
                .setValue(EAST,currentState.getValue(EAST))
                .setValue(SOUTH,currentState.getValue(SOUTH))
                .setValue(WEST,currentState.getValue(WEST))
                .setValue(WATERLOGGED,currentState.getValue(WATERLOGGED));

        level.setBlock(blockPos,fenceState,3);
    }

    public static BlockState copyFence(BlockState blockState){
        BlockState fenceState = ModBlocks.FENCE_LOGGED_BUTTERFLY_PEA.defaultBlockState();

        fenceState = fenceState.setValue(NORTH,blockState.getValue(NORTH))
                .setValue(EAST,blockState.getValue(EAST))
                .setValue(SOUTH,blockState.getValue(SOUTH))
                .setValue(WEST,blockState.getValue(WEST))
                .setValue(WATERLOGGED,blockState.getValue(WATERLOGGED));
        return fenceState;
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        BlockState fenceState = Blocks.OAK_FENCE.defaultBlockState();
        BlockEntity blockEntity = levelAccessor.getBlockEntity(blockPos);
        if(blockEntity instanceof FenceLoggedButterflyPeaBlockEntity fenceLoggedButterflyPeaBlockEntity){
            fenceState = fenceLoggedButterflyPeaBlockEntity.fenceState;
        }

        BlockState updateState = super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);

        BlockEntity blockEntity1 = levelAccessor.getBlockEntity(blockPos);
        if(blockEntity1 instanceof FenceLoggedButterflyPeaBlockEntity fenceLoggedButterflyPeaBlockEntity){
            fenceLoggedButterflyPeaBlockEntity.fenceState = fenceState;
        }

        return updateState;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new FenceLoggedButterflyPeaBlockEntity(blockPos,blockState);
    }

    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
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
        for(Direction direction : Direction.Plane.HORIZONTAL){
            if(blockState.getValue(PROPERTY_BY_DIRECTION.get(direction))){
                BlockState fenceState = serverLevel.getBlockState(blockPos.relative(direction));

                serverLevel.setBlock(blockPos.relative(direction),copyFence(fenceState),3);

                BlockEntity blockEntity = serverLevel.getBlockEntity(blockPos.relative(direction));
                if(blockEntity instanceof FenceLoggedButterflyPeaBlockEntity fenceLoggedButterflyPeaBlockEntity){
                    fenceLoggedButterflyPeaBlockEntity.fenceState = fenceState;
                }
            }
        }

        for(Direction direction : Direction.Plane.VERTICAL){
            BlockState verticalBlockState = serverLevel.getBlockState(blockPos.relative(direction));
            if(verticalBlockState.is(BlockTags.FENCES)){
                serverLevel.setBlock(blockPos.relative(direction),copyFence(verticalBlockState),3);
                BlockEntity blockEntity = serverLevel.getBlockEntity(blockPos.relative(direction));
                if(blockEntity instanceof FenceLoggedButterflyPeaBlockEntity fenceLoggedButterflyPeaBlockEntity){
                    fenceLoggedButterflyPeaBlockEntity.fenceState = verticalBlockState;
                }
            }
        }
    }
}
