package net.firemuffin303.thaidelight.common.block.vegetations.pepper;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class PepperCropBlock extends CropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D)};
    public PepperCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        if(blockState.getValue(AGE) >= getMaxAge()){
            if(!level.isClientSide){
                drop(ModLootTables.PEPPER_HARVEST, (ServerLevel) level,ItemStack.EMPTY,blockState,blockPos);
                level.playSound(null,blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS);
                level.setBlock(blockPos, blockState.setValue(getAgeProperty(), 0), 2);
            }
            return InteractionResult.SUCCESS;
        }

        return super.useWithoutItem(blockState, level, blockPos, player, blockHitResult);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(blockState.getValue(AGE) >= getMaxAge()){
            if(!level.isClientSide){
                drop(ModLootTables.PEPPER_HARVEST, (ServerLevel) level,itemStack,blockState,blockPos);
                level.playSound(null,blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS);
                level.setBlock(blockPos, blockState.setValue(getAgeProperty(), 0), 2);
            }
            return ItemInteractionResult.SUCCESS;
        }

        return super.useItemOn(itemStack, blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    private void drop(ResourceLocation resourceLocation, ServerLevel serverLevel, ItemStack itemStack, BlockState blockState, BlockPos blockPos){
        LootTable lootTable = serverLevel.getServer().reloadableRegistries().getLootTable(ResourceKey.create(Registries.LOOT_TABLE,resourceLocation));
        LootParams params = new LootParams.Builder(serverLevel)
                .withParameter(LootContextParams.BLOCK_STATE,blockState)
                .withParameter(LootContextParams.ORIGIN,blockPos.getCenter())
                .withParameter(LootContextParams.TOOL,itemStack)
                .create(LootContextParamSets.BLOCK);

        List<ItemStack> list = lootTable.getRandomItems(params);

        for (ItemStack dropStack : list) {
            ItemEntity itemEntity = new ItemEntity(serverLevel, (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), dropStack);
            itemEntity.setDefaultPickUpDelay();
            serverLevel.addFreshEntity(itemEntity);
        }
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.PEPPER_SEED.get();
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 2;
    }

    @Override
    protected int getBonemealAgeIncrease(Level level) {
        return super.getBonemealAgeIncrease(level) / 3;
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if(state.getValue(this.getAgeProperty()) >= 2){
            return SHAPE_BY_AGE[2];
        }else if(state.getValue(this.getAgeProperty()) >= 1){
            return SHAPE_BY_AGE[1];
        }
        return SHAPE_BY_AGE[0];
    }
}
