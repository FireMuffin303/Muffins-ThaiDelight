package net.firemuffin303.thaidelight.common.world.feature;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.firemuffin303.thaidelight.common.registry.ModFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformFloat;
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

public class MegaDurianTrunkPlacer extends TrunkPlacer {

    private static final Codec<UniformInt> BRANCH_START_CODEC = UniformInt.CODEC.codec().validate(uniformInt -> uniformInt.getMaxValue() - uniformInt.getMinValue() < 1
            ? DataResult.error(() -> "Need at least 2 blocks variation for the branch starts to fit both branches")
            : DataResult.success(uniformInt));

    public static final MapCodec<MegaDurianTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->{
        return trunkPlacerParts(instance)
                .and(instance.group(
                                Codec.INT.fieldOf("branch_sections").forGetter(placer -> placer.branchSections),
                                IntProvider.validateCodec(-16,0,BRANCH_START_CODEC).fieldOf("branch_start_offset_from_top").forGetter(durianTreeTrunkPlacer -> durianTreeTrunkPlacer.branchSectionOffset),
                                IntProvider.codec(2,16).fieldOf("branch_horizontal_length").forGetter(placer -> placer.branchLength),
                                IntProvider.codec(1,5).fieldOf("branch_count").forGetter(placer -> placer.branchCountPerSection)
                        )
                )
                .apply(instance,MegaDurianTrunkPlacer::new);
    });

    int branchSections;
    UniformInt branchSectionOffset;
    IntProvider branchLength;
    IntProvider branchCountPerSection;


    public MegaDurianTrunkPlacer(int baseHeight, int heightRanA, int heightRanB,
                                 int branchSections,
                                 UniformInt branchSectionOffset,
                                 IntProvider branchLength,
                                 IntProvider branchCountPerSection) {
        super(baseHeight, heightRanA,heightRanB);
        this.branchSections = branchSections;
        this.branchSectionOffset = branchSectionOffset;
        this.branchLength = branchLength;
        this.branchCountPerSection = branchCountPerSection;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModFeatures.MEGA_DURIAN_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader levelSimulatedReader, BiConsumer<BlockPos, BlockState> biConsumer, RandomSource randomSource, int treeHeight, BlockPos blockPos, TreeConfiguration treeConfiguration) {
        List<FoliagePlacer.FoliageAttachment> list = new ArrayList<>();


        //Setting Dirt
        setDirtAt(levelSimulatedReader, biConsumer, randomSource, blockPos.below(), treeConfiguration);
        setDirtAt(levelSimulatedReader, biConsumer, randomSource, blockPos.below().north(), treeConfiguration);
        setDirtAt(levelSimulatedReader, biConsumer, randomSource, blockPos.below().east(), treeConfiguration);
        setDirtAt(levelSimulatedReader, biConsumer, randomSource, blockPos.below().west(), treeConfiguration);
        setDirtAt(levelSimulatedReader, biConsumer, randomSource, blockPos.below().south(), treeConfiguration);

        for(int n = 0;n < treeHeight; ++n){
            BlockPos currentPosition = blockPos.above(n);

            if(TreeFeature.validTreePos(levelSimulatedReader, currentPosition)){
                this.placeLog(levelSimulatedReader, biConsumer, randomSource, currentPosition, treeConfiguration);
            }
        }

        for(Direction direction: Direction.Plane.HORIZONTAL){
            for (int base = 0 ; base < (treeHeight/4) - randomSource.nextInt(3); ++ base){
                BlockPos currentPosition = blockPos.relative(direction).above(base);
                if (TreeFeature.validTreePos(levelSimulatedReader,currentPosition)){
                    this.placeLog(levelSimulatedReader, biConsumer, randomSource, currentPosition, treeConfiguration);
                }
            }
        }

        list.add(new FoliagePlacer.FoliageAttachment(blockPos.above(treeHeight),1,false));


        List<Direction> directions = Direction.Plane.HORIZONTAL.shuffledCopy(randomSource);

        BlockPos pos = blockPos.above(treeHeight);

        for(int branchRow = 0; branchRow < this.branchSections; ++branchRow){
            int branchCount = this.branchCountPerSection.sample(randomSource);
            int branchOffset = this.branchSectionOffset.sample(randomSource);
            pos = pos.above(branchOffset);
            for(int branchCountIndex = 0;branchCountIndex < branchCount; ++branchCountIndex){
                Direction direction = directions.get(branchCountIndex);
                int branchLengthSampled = this.branchLength.sample(randomSource);
                BlockPos foliagePos = pos.relative(direction,branchLengthSampled).above(2);
                for(int length = 0; length < branchLengthSampled; ++length){
                    BlockPos currentPos = pos.relative(direction,length+1);
                    if(TreeFeature.validTreePos(levelSimulatedReader,currentPos)){
                        this.placeLog(levelSimulatedReader,biConsumer,randomSource,currentPos,treeConfiguration, blockState -> blockState.trySetValue(RotatedPillarBlock.AXIS,direction.getAxis()));
                    }

                    if(length+1 == branchLengthSampled && TreeFeature.validTreePos(levelSimulatedReader,currentPos.above(1)) && randomSource.nextBoolean()){
                        foliagePos = currentPos.above(2);
                        this.placeLog(levelSimulatedReader,biConsumer,randomSource,currentPos.above(1),treeConfiguration);
                    }
                }

                list.add(new FoliagePlacer.FoliageAttachment(foliagePos,0,false));
            }
        }

        return list;
    }
}
