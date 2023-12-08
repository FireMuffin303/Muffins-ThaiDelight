package net.firemuffin303.thaidelight.common.block.crops;

import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PapayaStem extends BushBlock implements BonemealableBlock {
    private static final VoxelShape SAPLING_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
    private static final VoxelShape GROWTH_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
    public static final BooleanProperty SUPPORTING = BooleanProperty.create("supporting");
    private static final IntegerProperty AGE = BlockStateProperties.AGE_3;

    public PapayaStem(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(SUPPORTING,false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{AGE,SUPPORTING});
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        return new ItemStack(ModItems.PAPAYA_SEED);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        super.randomTick(blockState, serverLevel, blockPos, randomSource);
        if(serverLevel.hasChunksAt(blockPos.offset(-1, -1, -1), blockPos.offset(1, 1, 1))){
            if (serverLevel.getMaxLocalRawBrightness(blockPos.above(), 0) >= 6 && blockState.getValue(AGE) <= 3 && randomSource.nextInt(3) == 0) {
                this.randomGrowTick(blockState, serverLevel, blockPos, randomSource);
            }
        }
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        BlockState superState = super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
        if(direction == Direction.UP){
            return superState.setValue(SUPPORTING,this.isSupportingPapayaUpper(blockState));
        }
        return superState;
    }

    public boolean isSupportingPapayaUpper(BlockState topState) {
        return topState.is(ModBlocks.PAPAYA_TOP_STEM);
    }

    private void randomGrowTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand) {
        int currentAge = state.getValue(AGE);
        if (currentAge <= 3 && rand.nextInt(3) == 0) {
            if (currentAge == 3) {
                UpperPapayaBlock papayaCrop = (UpperPapayaBlock) ModBlocks.PAPAYA_TOP_STEM;
                if (papayaCrop.defaultBlockState().canSurvive(world, pos.above()) && world.isEmptyBlock(pos.above())) {
                    world.setBlockAndUpdate(pos.above(), papayaCrop.defaultBlockState());
                }
            } else {
                world.setBlock(pos, state.setValue(AGE,currentAge + 1), 2);
            }
        }

    }

    protected int getBonemealAgeIncrease(Level world) {
        return Mth.nextInt(world.getRandom(), 1, 4);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return blockState.getValue(AGE) > 0 ? GROWTH_SHAPE : SAPLING_SHAPE;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
        BlockState upperState = levelReader.getBlockState(blockPos.above());
        Block var7 = upperState.getBlock();
        if (var7 instanceof UpperPapayaBlock upperPapayaBlock) {
            return !upperPapayaBlock.isValidBonemealTarget(levelReader, blockPos, blockState, bl);
        } else {
            return true;
        }
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        int ageGrowth = Math.min(blockState.getValue(AGE) + this.getBonemealAgeIncrease(serverLevel), 7);
        if(ageGrowth <= 3){
            serverLevel.setBlockAndUpdate(blockPos,blockState.setValue(AGE,ageGrowth));
        }else{
            BlockState top = serverLevel.getBlockState(blockPos.above());
            if(top.getBlock() == ModBlocks.PAPAYA_TOP_STEM){
                BonemealableBlock growable = (BonemealableBlock)serverLevel.getBlockState(blockPos.above()).getBlock();
                if (growable.isValidBonemealTarget(serverLevel, blockPos.above(), top, false)) {
                    growable.performBonemeal(serverLevel, serverLevel.getRandom(), blockPos.above(), top);
                }
            }else {
                UpperPapayaBlock riceUpper = (UpperPapayaBlock) ModBlocks.PAPAYA_TOP_STEM;
                int remainingGrowth = ageGrowth - 3 - 1;
                if (riceUpper.defaultBlockState().canSurvive(serverLevel, blockPos.above()) && serverLevel.isEmptyBlock(blockPos.above())) {
                    serverLevel.setBlockAndUpdate(blockPos, (BlockState)blockState.setValue(AGE, 3));
                    serverLevel.setBlock(blockPos.above(), (BlockState)riceUpper.defaultBlockState().setValue(UpperPapayaBlock.AGE, remainingGrowth), 2);
                }
            }
        }
    }

}
