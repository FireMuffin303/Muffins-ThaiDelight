package net.firemuffin303.thaidelight.common.block;

import com.mojang.serialization.MapCodec;
import net.firemuffin303.thaidelight.common.block.blockentity.SackBlockEntity;
import net.firemuffin303.thaidelight.common.block.stations.MortarBlock;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
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
import net.minecraft.world.level.gameevent.GameEvent;
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
    private static final VoxelShape FILLED_BOX = Block.box(1.0,0.0,1.0,15.0,16.0,15.0);
    private static final VoxelShape BOX = Block.box(1.0,0.0,1.0,15.0,12.0,15.0);
    public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty FILLED = BooleanProperty.create("filled");
    public static final ResourceLocation CONTENTS = ResourceLocation.parse("contents");

    public static final MapCodec<SackBlock> CODEC = simpleCodec(SackBlock::new);

    public SackBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(HORIZONTAL_FACING, Direction.NORTH)
                .setValue(WATERLOGGED,false)
                .setValue(FILLED,false)
        );
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if(blockEntity instanceof SackBlockEntity sackBlockEntity){
            if(itemStack.isEmpty()){
              this.removeItem(level,player,blockPos,sackBlockEntity);
            } else if(sackBlockEntity.canInsertItem(itemStack)){
                if(!level.isClientSide){
                    ItemStack excessItem = sackBlockEntity.addItem(itemStack.copy());
                    if(!player.getAbilities().instabuild){
                        player.setItemInHand(interactionHand,excessItem);
                    }
                    this.playCatchFallingBlockEffect(level, blockPos);
                }
            }
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }

    public boolean removeItem(Level level,Player player,BlockPos blockPos,SackBlockEntity sackBlockEntity){
        if(level.isClientSide){
            return false;
        }
        ItemStack itemStack = sackBlockEntity.popItem();
        if(itemStack.isEmpty()){
            return false;
        }
        if(!player.getInventory().add(itemStack)){
            player.drop(itemStack,false);
        }
        this.playCatchFallingBlockEffect(level, blockPos);
        level.gameEvent(player, GameEvent.BLOCK_CHANGE,blockPos);
        PiglinAi.angerNearbyPiglins(player,true);
        return true;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState blockState) {
        return true;
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity instanceof SackBlockEntity sackBlockEntity) {
            if (!level.isClientSide && player.isCreative() && !sackBlockEntity.isEmpty()) {
                ItemStack itemStack = new ItemStack(ModItems.SACK.get());
                itemStack.applyComponents(blockEntity.collectComponents());
                ItemEntity itemEntity = new ItemEntity(level, (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5, itemStack);
                itemEntity.setDefaultPickUpDelay();
                level.addFreshEntity(itemEntity);
            }
        }
        return super.playerWillDestroy(level, blockPos, blockState, player);
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

        ItemContainerContents itemContainerContents = blockPlaceContext.getItemInHand().get(DataComponents.CONTAINER);
        int amount = 0;

        if(itemContainerContents != null){
            for(ItemStack itemStack : itemContainerContents.stream().toList()){
                if(itemStack.getCount() >= itemStack.getMaxStackSize()){
                    amount++;
                }
            }
        }

        return this.defaultBlockState()
                .setValue(WATERLOGGED, levelAccessor.getFluidState(blockPos).getType() == Fluids.WATER)
                .setValue(HORIZONTAL_FACING, blockPlaceContext.getHorizontalDirection().getOpposite())
                .setValue(FILLED,amount >= 5);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING,WATERLOGGED,FILLED);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return blockState.getValue(FILLED) ? FILLED_BOX : BOX;
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

    public boolean insertFallingBlock(Item item, Level level, BlockPos blockPos){
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        ItemStack itemStack = new ItemStack(item);
        if(blockEntity instanceof SackBlockEntity sackBlockEntity){
            if(sackBlockEntity.canInsertItem(itemStack)){
                if(!level.isClientSide){
                    sackBlockEntity.addItem(itemStack);
                    this.playCatchFallingBlockEffect(level, blockPos);
                    return true;
                }
            }
        }

        return false;
    }

    public void playCatchFallingBlockEffect(Level level,BlockPos blockPos){
        level.levelEvent(2009,blockPos,0);
        level.playSound(null,blockPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS);
    }
}
