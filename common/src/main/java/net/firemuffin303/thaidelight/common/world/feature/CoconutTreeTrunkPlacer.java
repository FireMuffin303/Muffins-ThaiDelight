package net.firemuffin303.thaidelight.common.world.feature;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.firemuffin303.thaidelight.common.registry.ModFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class CoconutTreeTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<CoconutTreeTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->{
        return trunkPlacerParts(instance)
                .and(instance.group(
                        IntProvider.codec(1,16).fieldOf("trunk_section_height").forGetter(getter -> getter.trunkSectionHeight),
                        Codec.floatRange(0,1).fieldOf("trunk_bend_chance").forGetter(g -> g.trunkBendChance)))
                .apply(instance,CoconutTreeTrunkPlacer::new);
    });

    float trunkBendChance;
    IntProvider trunkSectionHeight;

    public CoconutTreeTrunkPlacer(int i, int j, int k,IntProvider trunkSectionHeight,float trunkBendChance) {
        super(i, j, k);
        this.trunkSectionHeight = trunkSectionHeight;
        this.trunkBendChance = trunkBendChance;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModFeatures.COCONUT_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader levelSimulatedReader, BiConsumer<BlockPos, BlockState> biConsumer, RandomSource randomSource, int treeHeight, BlockPos lowestLogPos, TreeConfiguration treeConfiguration) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(randomSource);
        int j = treeHeight - 1;
        BlockPos.MutableBlockPos mutableBlockPos = lowestLogPos.mutable();
        BlockPos blockPos2 = mutableBlockPos.below();
        if(this.isDirt(levelSimulatedReader,blockPos2)){
            biConsumer.accept(blockPos2,treeConfiguration.dirtProvider.getState(randomSource,blockPos2));
        }
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        int i = this.trunkSectionHeight.sample(randomSource);
        for (int k = 0; k <= j; k++) {
            if (k == i && randomSource.nextFloat() < this.trunkBendChance) {
                mutableBlockPos.move(direction);
            }

            if (TreeFeature.validTreePos(levelSimulatedReader, mutableBlockPos)) {
                this.placeLog(levelSimulatedReader, biConsumer, randomSource, mutableBlockPos, treeConfiguration);
            }

            if (k >= j) {
                list.add(new FoliagePlacer.FoliageAttachment(mutableBlockPos.immutable(), 0, false));
            }


            mutableBlockPos.move(Direction.UP);
        }


        return list;
    }

    private boolean isDirt(LevelSimulatedReader levelSimulatedReader, BlockPos blockPos) {
        return levelSimulatedReader.isStateAtPosition(blockPos, Feature::isDirt);
    }
}
