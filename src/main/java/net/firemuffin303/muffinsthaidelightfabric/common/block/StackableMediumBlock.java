package net.firemuffin303.muffinsthaidelightfabric.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class StackableMediumBlock extends Block implements SimpleWaterloggedBlock {
    public static IntegerProperty STACKS = IntegerProperty.create("medium_stacks",1,2);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private Supplier<Item> pickUpItem;

    public StackableMediumBlock(Properties properties, Supplier<Item> pickUpItem) {
        super(properties);
        this.pickUpItem = pickUpItem;
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(WATERLOGGED,false)
                .setValue(STACKS,1)
        );
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(!level.isClientSide){
            if(takeItem(level, blockPos, blockState, player, interactionHand).consumesAction()){
                return InteractionResult.SUCCESS;
            }
        }

        return takeItem(level, blockPos, blockState, player, interactionHand);
    }

    private InteractionResult takeItem(Level level,BlockPos blockPos, BlockState blockState,Player player,InteractionHand interactionHand){
        if(player.getAbilities().mayBuild && player.getItemInHand(interactionHand).isEmpty()){
            int i = blockState.getValue(STACKS);
            ItemStack itemStack = new ItemStack(pickUpItem.get());
            if (!player.getInventory().add(itemStack)) {
                player.drop(itemStack, false);
            }
            if(blockState.getValue(STACKS) == 1){
                level.removeBlock(blockPos,false);
                level.gameEvent(GameEvent.BLOCK_DESTROY, blockPos, GameEvent.Context.of(blockState));
                return InteractionResult.SUCCESS;
            }

            level.setBlockAndUpdate(blockPos, blockState.setValue(STACKS,i-1));
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(blockState));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED,STACKS);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return Block.canSupportCenter(levelReader, blockPos.below(), Direction.UP);
    }

    @Override
    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
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
        BlockState blockState = blockPlaceContext.getLevel().getBlockState(blockPlaceContext.getClickedPos());
        if (blockState.is(this)) {
            return blockState.cycle(STACKS);
        }

        FluidState fluidState = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());
        boolean bl = fluidState.getType() == Fluids.WATER;
        return super.getStateForPlacement(blockPlaceContext).setValue(WATERLOGGED, Boolean.valueOf(bl));
    }

    @Override
    public boolean canBeReplaced(BlockState blockState, BlockPlaceContext blockPlaceContext) {
        if(!blockPlaceContext.isSecondaryUseActive() && blockPlaceContext.getItemInHand().getItem() == this.asItem() && blockState.getValue(STACKS) < 2){
            return true;
        }
        return super.canBeReplaced(blockState,blockPlaceContext);
    }
}
