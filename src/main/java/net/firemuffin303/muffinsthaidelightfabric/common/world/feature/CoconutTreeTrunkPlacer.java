package net.firemuffin303.muffinsthaidelightfabric.common.world.feature;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class CoconutTreeTrunkPlacer extends TrunkPlacer {
    public static final Codec<CoconutTreeTrunkPlacer> CODEC = RecordCodecBuilder.create(instance ->{
        return trunkPlacerParts(instance)
                .apply(instance,CoconutTreeTrunkPlacer::new);
    });

    public CoconutTreeTrunkPlacer(int i, int j, int k) {
        super(i, j, k);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModFeatures.COCONUT_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader levelSimulatedReader, BiConsumer<BlockPos, BlockState> biConsumer, RandomSource randomSource, int treeHeight, BlockPos lowestLogPos, TreeConfiguration treeConfiguration) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(randomSource);
        int j = treeHeight - 1;
        BlockPos.MutableBlockPos mutableBlockPos = lowestLogPos.mutable();
        BlockPos blockPos2 = mutableBlockPos.below();
        setDirtAt(levelSimulatedReader, biConsumer, randomSource, blockPos2, treeConfiguration);
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        for (int k = 0; k <= j; k++) {
            if (k + 1 >= j + randomSource.nextInt(2)) {
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
}
