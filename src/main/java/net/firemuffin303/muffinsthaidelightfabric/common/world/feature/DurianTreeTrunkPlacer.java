package net.firemuffin303.muffinsthaidelightfabric.common.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class DurianTreeTrunkPlacer extends TrunkPlacer {
    private static final Codec<UniformInt> BRANCH_START_CODEC = ExtraCodecs.validate(
            UniformInt.CODEC,
            uniformInt -> uniformInt.getMaxValue() - uniformInt.getMinValue() < 1
                    ? DataResult.error(() -> "Need at least 2 blocks variation for the branch starts to fit both branches")
                    : DataResult.success(uniformInt)
    );

    public static final Codec<DurianTreeTrunkPlacer> CODEC = RecordCodecBuilder.create(instance ->{
        return trunkPlacerParts(instance)
                .and(instance.group(
                        IntProvider.codec(-16,0,BRANCH_START_CODEC).fieldOf("branch_start_offset_from_top").forGetter(durianTreeTrunkPlacer -> durianTreeTrunkPlacer.branchStartOffsetFromTop),
                        IntProvider.codec(2,16).fieldOf("branch_horizontal_length").forGetter(placer -> placer.branchLength),
                        IntProvider.codec(1,5).fieldOf("branch_count").forGetter(placer -> placer.branchCount)
                    )
                )
                .apply(instance,DurianTreeTrunkPlacer::new);
    });

    private final UniformInt branchStartOffsetFromTop;
    private final IntProvider branchLength;
    private final IntProvider branchCount;

    public DurianTreeTrunkPlacer(int baseHeight, int heightRanA, int heightRanB,UniformInt branchStartOffsetFromTop,IntProvider branchLength,IntProvider branchCount) {
        super(baseHeight, heightRanA, heightRanB);
        this.branchStartOffsetFromTop = branchStartOffsetFromTop;
        this.branchLength = branchLength;
        this.branchCount = branchCount;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModFeatures.DURIAN_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader levelSimulatedReader, BiConsumer<BlockPos, BlockState> biConsumer, RandomSource randomSource, int treeHeight, BlockPos blockPos, TreeConfiguration treeConfiguration) {

        List<FoliagePlacer.FoliageAttachment> list = new ArrayList<>();

        int branchCount = this.branchCount.sample(randomSource);

        setDirtAt(levelSimulatedReader, biConsumer, randomSource, blockPos.below(), treeConfiguration);
        int j = Math.max(0,treeHeight -1 +this.branchStartOffsetFromTop.sample(randomSource));

        //place straight log
        for(int n = 0;n < treeHeight; ++n){
            BlockPos currentPosition = blockPos.above(n);

            if(TreeFeature.validTreePos(levelSimulatedReader, currentPosition)){
                this.placeLog(levelSimulatedReader, biConsumer, randomSource, currentPosition, treeConfiguration);
            }


        }

        list.add(new FoliagePlacer.FoliageAttachment(blockPos.above(treeHeight),1,false));

        List<Direction> directions = Direction.Plane.HORIZONTAL.shuffledCopy(randomSource);


        for(int bcount = 0; bcount < branchCount;++bcount){
            Direction direction = directions.get(bcount);
            int branchLength = this.branchLength.sample(randomSource);
            int branchOffset = this.branchStartOffsetFromTop.sample(randomSource);
            //place branch
            BlockPos tempPos = blockPos.above(treeHeight).mutable().above(branchOffset);
            BlockPos foliagePos = tempPos.mutable().move(direction,branchLength).above(2);
            for(int n = 0; n < branchLength; ++n){
                BlockPos currentPosition = tempPos.mutable().move(direction,n+1);
                if(TreeFeature.validTreePos(levelSimulatedReader,currentPosition)){
                    this.placeLog(levelSimulatedReader,biConsumer,randomSource,currentPosition,treeConfiguration, blockState -> blockState.trySetValue(RotatedPillarBlock.AXIS,direction.getAxis()));
                }

                if(n+1 == branchLength && TreeFeature.validTreePos(levelSimulatedReader,currentPosition.above(1)) && randomSource.nextBoolean()){
                    foliagePos = currentPosition.above(2);
                    this.placeLog(levelSimulatedReader,biConsumer,randomSource,currentPosition.above(1),treeConfiguration);
                }
            }

            list.add(new FoliagePlacer.FoliageAttachment(foliagePos,0,false));

        }

        return list;
    }
}
