package net.firemuffin303.muffinsthaidelightfabric.common.block.lime;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LimePlantBlock extends DoublePlantBlock implements BonemealableBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

    public LimePlantBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        return new ItemStack(ModItems.LIME_SAPLING);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(!level.isClientSide){
            if(blockState.getValue(AGE) >= 3){
                BlockPos lowerPos = isLower(blockState) ? blockPos : blockPos.below();

                int j = 2 + level.random.nextInt(2);
                popResource(level,lowerPos.above(),new ItemStack(ModItems.LIME,j));
                level.playSound((Player)null, player, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);

                level.setBlock(lowerPos,blockState.setValue(AGE,0).setValue(HALF,DoubleBlockHalf.LOWER),2);
                level.setBlock(lowerPos.above(),
                        copyWaterloggedFrom(level, lowerPos, blockState.setValue(AGE, 0)
                                .setValue(HALF, DoubleBlockHalf.UPPER)), 3);
                level.gameEvent(GameEvent.BLOCK_CHANGE, lowerPos, GameEvent.Context.of(player, blockState));
                return InteractionResult.SUCCESS;
            }
        }

        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
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
        if (serverLevel.getRawBrightness(blockPos, 0) >= 9) {
            int i = blockState.getValue(AGE);
            if (i < 3) {
                float f = CropBlock.getGrowthSpeed(this, serverLevel, blockPos);
                if (randomSource.nextInt((int)(25.0F / f) + 1) == 0) {
                    serverLevel.setBlock(blockPos,  blockState.setValue(AGE,blockState.getValue(AGE)+1), 2);
                }
            }
        }
    }

    public boolean canGrow(LevelReader levelReader,BlockState blockState,BlockPos blockPos){
        return blockState.getValue(AGE) < 3  && sufficientLight(levelReader, blockPos);
    }

    private static boolean sufficientLight(LevelReader levelReader, BlockPos blockPos) {
        return levelReader.getRawBrightness(blockPos, 0) >= 8 || levelReader.canSeeSky(blockPos);
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
        return blockState.getValue(HALF) == DoubleBlockHalf.LOWER && blockState.is(ModBlocks.LIME_PLANT);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
        return blockState.getValue(AGE) < 3;
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
