package net.firemuffin303.muffinsthaidelightfabric.common.world.feature;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModFeatures;
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
import net.minecraft.world.level.material.Fluids;

public class CoconutLeavesFoliagePlacer extends FoliagePlacer {
    protected final int height;
    protected final int rand_radius_a;
    protected final int rand_radius_b;

    public static final Codec<CoconutLeavesFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
            foliagePlacerParts(instance).and(
                    instance.group(
                            Codec.intRange(0,16).fieldOf("height").forGetter(placer -> placer.height),
                            Codec.intRange(0,16).fieldOf("rand_radius_a").forGetter(placer -> placer.rand_radius_a),
                            Codec.intRange(0,16).fieldOf("rand_radius_b").forGetter(placer -> placer.rand_radius_b)
                    ))
                    .apply(instance,CoconutLeavesFoliagePlacer::new));

    public CoconutLeavesFoliagePlacer(IntProvider radius, IntProvider offset,int height,int rand_radius_a,int rand_radius_b) {
        super(radius, offset);
        this.height = height;
        this.rand_radius_a = rand_radius_a;
        this.rand_radius_b = rand_radius_b;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFeatures.COCONUT_FOLIAGE_PLACER;
    }

    @Override
    protected void createFoliage(
            LevelSimulatedReader levelSimulatedReader,
            FoliageSetter foliageSetter,
            RandomSource randomSource,
            TreeConfiguration treeConfiguration,
            int maxFreeTreeHeight,
            FoliageAttachment foliageAttachment,
            int foliageHeight,
            int foliageRadius,
            int offset
    ) {
        this.placeLeaves(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, foliageAttachment.pos(), foliageRadius, offset, foliageAttachment.doubleTrunk());
    }

    @Override
    public int foliageHeight(RandomSource randomSource, int i, TreeConfiguration treeConfiguration) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource randomSource, int i, int j, int k, int l, boolean bl) {
        return false;
    }

    protected void placeLeaves(
            LevelSimulatedReader levelSimulatedReader,
            FoliagePlacer.FoliageSetter foliageSetter,
            RandomSource randomSource,
            TreeConfiguration treeConfiguration,
            BlockPos centerPos,
            int radius,
            int yPos,
            boolean bl
    ) {
        int k = bl ? 1 : 0;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();



        for(int i = 0; i < 2; i++){
            int newRadius = radius + randomSource.nextInt(rand_radius_a,rand_radius_b);
            for(int l = -newRadius; l <= newRadius;l++){
                int xPos = i == 0 ? l : 0;
                int zPos = i == 1 ? l : 0;
                if (!this.shouldSkipLocationSigned(randomSource, xPos, yPos, zPos, newRadius, bl)){
                    mutableBlockPos.setWithOffset(centerPos,xPos,yPos,zPos);
                    tryPlaceCoconutLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, mutableBlockPos,centerPos,(l == -newRadius || l == newRadius));
                }
            }
        }
    }

    protected static boolean tryPlaceCoconutLeaf(
            LevelSimulatedReader levelSimulatedReader,
            FoliagePlacer.FoliageSetter foliageSetter,
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
                if(levelSimulatedReader.isStateAtPosition(foliagePos,blockState -> blockState.is(ModBlocks.COCONUT_LEAF))){
                    return false;
                }
            }

            BlockState blockState = isEnd ? ModBlocks.COCONUT_LEAF_END.defaultBlockState() : ModBlocks.COCONUT_LEAF.defaultBlockState();
            if (blockState.hasProperty(BlockStateProperties.WATERLOGGED)) {
                blockState = blockState.setValue(
                        BlockStateProperties.WATERLOGGED, levelSimulatedReader.isFluidAtPosition(foliagePos, fluidState -> fluidState.isSourceOfType(Fluids.WATER))
                );
            }

            if(blockState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)){
                BlockPos blockPos = new BlockPos(centerPos.getX() - foliagePos.getX(),centerPos.getY() - foliagePos.getY(),centerPos.getZ() - foliagePos.getZ());
                Direction direction = Direction.getNearest(blockPos.getX(),blockPos.getY(),blockPos.getZ());
                blockState = blockState.setValue(BlockStateProperties.HORIZONTAL_FACING,direction.getOpposite());
            }


            foliageSetter.set(foliagePos, blockState);
            return true;
        }
    }
}
