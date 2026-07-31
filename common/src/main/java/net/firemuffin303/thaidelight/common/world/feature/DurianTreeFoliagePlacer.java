package net.firemuffin303.thaidelight.common.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.firemuffin303.thaidelight.common.registry.ModFeatures;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class DurianTreeFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<DurianTreeFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            foliagePlacerParts(instance).and(
                    instance.group(
                            Codec.floatRange(0.0f,1.0f).fieldOf("hanging_leaves_chance").forGetter(placer -> placer.hangingLeavesChance),
                            Codec.floatRange(0.0f,1.0f).fieldOf("hanging_leaves_extension_chance").forGetter(placer -> placer.hangingLeavesExtensionChance)
                    )
            ).apply(instance,DurianTreeFoliagePlacer::new));

    private final float hangingLeavesChance;
    private final float hangingLeavesExtensionChance;

    public DurianTreeFoliagePlacer(IntProvider intProvider, IntProvider intProvider2,float hangingLeavesChance,float hangingLeavesExtensionChance) {
        super(intProvider, intProvider2);
        this.hangingLeavesChance = hangingLeavesChance;
        this.hangingLeavesExtensionChance = hangingLeavesExtensionChance;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFeatures.DURIAN_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader levelSimulatedReader, FoliageSetter foliageSetter, RandomSource randomSource, TreeConfiguration treeConfiguration, int treeHeight, FoliageAttachment foliageAttachment, int foliageHeight, int foliageRadius, int offsetRandom) {
        this.placeLeavesRowWithHangingLeavesBelow(levelSimulatedReader,foliageSetter,randomSource,treeConfiguration,foliageAttachment.pos(),foliageRadius + foliageAttachment.radiusOffset(),-2,foliageAttachment.doubleTrunk(),this.hangingLeavesChance,this.hangingLeavesExtensionChance);
        //Top
        this.placeLeavesRow(levelSimulatedReader,foliageSetter,randomSource,treeConfiguration,foliageAttachment.pos(), Math.max(foliageRadius + foliageAttachment.radiusOffset()-1 ,1) ,0,foliageAttachment.doubleTrunk());
        this.placeLeavesRow(levelSimulatedReader,foliageSetter,randomSource,treeConfiguration,foliageAttachment.pos(), Math.max(foliageRadius + foliageAttachment.radiusOffset() ,1) ,-1,foliageAttachment.doubleTrunk());
    }

    @Override
    public int foliageHeight(RandomSource randomSource, int i, TreeConfiguration treeConfiguration) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource randomSource, int i, int j, int k, int l, boolean isDoubleTrunk) {
        return i == l && k == l;
    }
}
