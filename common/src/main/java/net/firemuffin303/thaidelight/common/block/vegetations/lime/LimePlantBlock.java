package net.firemuffin303.thaidelight.common.block.vegetations.lime;

import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModLootTables;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class LimePlantBlock extends DoublePlantBlock implements BonemealableBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    private static final int MAX_AGE = 2;

    public LimePlantBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return new ItemStack(ModItems.LIME_SAPLING.get());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        BlockPos lowerPos = isLower(blockState) ? blockPos : blockPos.below();
        if(!level.isClientSide){
            if(blockState.getValue(AGE) >= MAX_AGE) {
                drop(ModLootTables.LIME_HARVEST, (ServerLevel) level,player,ItemStack.EMPTY,blockState,lowerPos);
                return InteractionResult.SUCCESS;
            }
        }

        return super.useWithoutItem(blockState, level, blockPos, player, blockHitResult);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(!level.isClientSide){
            BlockPos lowerPos = isLower(blockState) ? blockPos : blockPos.below();
            ItemInteractionResult result = ItemInteractionResult.FAIL;
            if (itemStack.is(PlatformUtil.shearTag()) && blockState.getValue(AGE) >= 1) {
                drop(ModLootTables.LIME_SHEARS, (ServerLevel) level, player, itemStack, blockState, lowerPos);
                result = ItemInteractionResult.SUCCESS;
            }

            if(blockState.getValue(AGE) >= MAX_AGE) {
                drop(ModLootTables.LIME_HARVEST, (ServerLevel) level,player,itemStack,blockState,lowerPos);
                result = ItemInteractionResult.SUCCESS;
            }

            return result;
        }

        return super.useItemOn(itemStack, blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    private void drop(ResourceKey<LootTable> resourceLocation, ServerLevel serverLevel, Player player, ItemStack itemStack, BlockState blockState, BlockPos blockPos){
        LootTable lootTable = serverLevel.getServer().reloadableRegistries().getLootTable(resourceLocation);
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

        serverLevel.playSound(null,blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS);
        serverLevel.setBlock(blockPos,blockState.setValue(AGE,0).setValue(HALF,DoubleBlockHalf.LOWER),2);
        serverLevel.setBlock(blockPos.above(),
                copyWaterloggedFrom(serverLevel, blockPos, blockState.setValue(AGE, 0)
                        .setValue(HALF, DoubleBlockHalf.UPPER)), 3);
        serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, blockState));
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return blockState.getValue(HALF) == DoubleBlockHalf.UPPER ? Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0) : super.getShape(blockState, blockGetter, blockPos, collisionContext);
    }

    @Override
    public boolean canBeReplaced(BlockState blockState, BlockPlaceContext blockPlaceContext) {
        return false;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
        super.setPlacedBy(level, blockPos, blockState, livingEntity, itemStack);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (canGrow(serverLevel,blockState,blockPos)) {
            BlockPos lowerPos = blockPos;
            BlockState lowerState = blockState;

            if(!isLower(lowerState)){
                lowerPos = blockPos.below();
                lowerState = serverLevel.getBlockState(blockPos.below());
            }

            if(isLower(lowerState)){
                if (randomSource.nextInt(5) == 0 && serverLevel.getRawBrightness(blockPos.above(), 0) >= 9) {
                    grow(serverLevel,lowerState,lowerPos);
                }
            }
        }
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        if (entity instanceof LivingEntity livingEntity && entity.getType() != EntityType.FOX && entity.getType() != EntityType.BEE) {
            entity.makeStuckInBlock(blockState, new Vec3(0.800000011920929, 0.75, 0.800000011920929));

            if (!level.isClientSide && livingEntity.getItemBySlot(EquipmentSlot.CHEST).isEmpty() && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
                double d = Math.abs(entity.getX() - entity.xOld);
                double e = Math.abs(entity.getZ() - entity.zOld);
                if (d >= 0.003000000026077032 || e >= 0.003000000026077032) {
                    entity.hurt(level.damageSources().sweetBerryBush(), 1.0F);
                }
            }
        }
    }


    public boolean canGrow(LevelReader levelReader, BlockState blockState, BlockPos blockPos){
        return blockState.getValue(AGE) < MAX_AGE  && sufficientLight(levelReader, blockPos);
    }

    private static boolean sufficientLight(LevelReader levelReader, BlockPos blockPos) {
        return levelReader.getRawBrightness(blockPos, 0) >= 9;
    }

    public void grow(ServerLevel serverLevel,BlockState blockState,BlockPos blockPos){
        int i = blockState.getValue(AGE);
        if(this.canGrow(serverLevel,blockState,blockPos)){
            serverLevel.setBlock(blockPos,blockState.setValue(AGE,i+1),2);
            serverLevel.setBlock(blockPos.above(),
                    copyWaterloggedFrom(serverLevel, blockPos, this.defaultBlockState().setValue(AGE, i+1)
                            .setValue(HALF, DoubleBlockHalf.UPPER)), 3);
        }
    }

    private static boolean isLower(BlockState blockState){
        return blockState.getValue(HALF) == DoubleBlockHalf.LOWER && blockState.is(ModBlocks.LIME_PLANT.get());
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return blockState.getValue(AGE) < MAX_AGE;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        if(isLower(blockState)){
            this.grow(serverLevel,blockState,blockPos);
        }else{
            BlockState blockState1 = serverLevel.getBlockState(blockPos.below());
            if(isLower(blockState1)){
                this.grow(serverLevel,blockState1,blockPos.below());
            }
        }
    }
}
