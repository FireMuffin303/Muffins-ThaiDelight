package net.firemuffin303.thaidelight.common.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.firemuffin303.thaidelight.common.block.ModBlockStateProperties;
import net.firemuffin303.thaidelight.common.registry.ModFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.material.Fluids;

public class PapayaLeavesFoliagePlacer extends FoliagePlacer {
    protected final int height;
    protected final BlockStateProvider stemProvider;

    public static final MapCodec<PapayaLeavesFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            foliagePlacerParts(instance).and(
                            instance.group(
                                    Codec.intRange(0,16).fieldOf("height").forGetter(placer -> placer.height),
                                    BlockStateProvider.CODEC.fieldOf("stem_provider").forGetter(placer -> placer.stemProvider)
                            ))
                    .apply(instance,PapayaLeavesFoliagePlacer::new));

    public PapayaLeavesFoliagePlacer(IntProvider intProvider, IntProvider intProvider2,int height,BlockStateProvider stemProvider) {
        super(intProvider, intProvider2);
        this.height = height;
        this.stemProvider = stemProvider;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFeatures.PAPAYA_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader levelSimulatedReader,
                                 FoliageSetter foliageSetter,
                                 RandomSource randomSource,
                                 TreeConfiguration treeConfiguration,
                                 int maxFreeTreeHeight,
                                 FoliageAttachment foliageAttachment,
                                 int foliageHeight,
                                 int foliageRadius, int offset) {
        BlockPos blockPos = foliageAttachment.pos();
        BlockState blockState = treeConfiguration.foliageProvider.getState(randomSource,blockPos);
        if(blockState.hasProperty(ModBlockStateProperties.PAPAYA_LEAVES_FACING)){
            blockState.setValue(ModBlockStateProperties.PAPAYA_LEAVES_FACING,Direction.UP);
        }

        foliageSetter.set(blockPos,blockState);

        for(int i = this.height ; i > 0 ; i--){
            this.placeLeaves(
                    levelSimulatedReader,
                    foliageSetter,
                    randomSource,
                    treeConfiguration,
                    foliageAttachment.pos().below(i),
                    foliageRadius - (this.height - i),
                    offset,
                    foliageAttachment.doubleTrunk()
            );

        }
    }

    protected void placeLeaves(
            LevelSimulatedReader levelSimulatedReader,
            FoliageSetter foliageSetter,
            RandomSource randomSource,
            TreeConfiguration treeConfiguration,
            BlockPos centerPos,
            int radius,
            int yPos,
            boolean bl
    ) {
        int k = bl ? 1 : 0;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        BlockState leavesState = treeConfiguration.foliageProvider.getState(randomSource,mutableBlockPos);
        BlockState stemState = this.stemProvider.getState(randomSource,mutableBlockPos);

        for(int i = 0; i < 2; i++){
            for(int l = -radius; l <= radius;l++){
                int xPos = i == 0 ? l : 0;
                int zPos = i == 1 ? l : 0;
                if (!this.shouldSkipLocationSigned(randomSource, xPos, yPos, zPos, radius, bl)){
                    mutableBlockPos.setWithOffset(centerPos,xPos,yPos,zPos);
                    tryPlacePapayaLeavesHorizontal(
                            levelSimulatedReader,
                            foliageSetter,
                            leavesState,
                            stemState,
                            randomSource,
                            treeConfiguration,
                            mutableBlockPos,
                            centerPos,
                            (l == -radius || l == radius)
                    );
                }
            }
        }
    }

    protected static boolean tryPlacePapayaLeavesHorizontal(
            LevelSimulatedReader levelSimulatedReader,
            FoliageSetter foliageSetter,
            BlockState leavesState,
            BlockState stemState,
            RandomSource randomSource,
            TreeConfiguration treeConfiguration,
            BlockPos foliagePos,
            BlockPos centerPos,
            boolean isEnd
    ) {
        if (!TreeFeature.validTreePos(levelSimulatedReader, foliagePos)) {
            return false;
        } else {
            if(isEnd){
                if(levelSimulatedReader.isStateAtPosition(foliagePos,blockState -> blockState.is(stemState.getBlock()))){
                    return false;
                }
            }

            BlockState blockState = isEnd ? leavesState : stemState;
            if (blockState.hasProperty(BlockStateProperties.WATERLOGGED)) {
                blockState = blockState.setValue(
                        BlockStateProperties.WATERLOGGED, levelSimulatedReader.isFluidAtPosition(foliagePos,
                                fluidState -> fluidState.isSourceOfType(Fluids.WATER))
                );
            }

            if(blockState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)){
                BlockPos blockPos = new BlockPos(centerPos.getX() - foliagePos.getX(),centerPos.getY() - foliagePos.getY(),centerPos.getZ() - foliagePos.getZ());
                Direction direction = Direction.getNearest(blockPos.getX(),blockPos.getY(),blockPos.getZ());
                blockState = blockState.setValue(BlockStateProperties.HORIZONTAL_FACING,direction.getOpposite());
            }

            if(blockState.hasProperty(ModBlockStateProperties.PAPAYA_LEAVES_FACING)){
                BlockPos blockPos = new BlockPos(centerPos.getX() - foliagePos.getX(),centerPos.getY() - foliagePos.getY(),centerPos.getZ() - foliagePos.getZ());
                Direction direction = Direction.getNearest(blockPos.getX(),blockPos.getY(),blockPos.getZ());
                blockState = blockState.setValue(ModBlockStateProperties.PAPAYA_LEAVES_FACING,direction.getOpposite());
            }


            foliageSetter.set(foliagePos, blockState);
            return true;
        }
    }

    @Override
    public int foliageHeight(RandomSource randomSource, int i, TreeConfiguration treeConfiguration) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource randomSource, int i, int j, int k, int l, boolean bl) {
        return false;
    }
}
