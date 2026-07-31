package net.firemuffin303.thaidelight.common.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.firemuffin303.thaidelight.common.block.vegetations.coconut.CoconutLeafBlock;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
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
import net.minecraft.world.level.material.Fluids;

public class CoconutLeavesFoliagePlacer extends FoliagePlacer {
    protected final int height;
    protected final int rand_radius_a;
    protected final int rand_radius_b;

    public static final MapCodec<CoconutLeavesFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
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
        return ModFeatures.COCONUT_FOLIAGE_PLACER.get();
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
        for(int layer = 0;layer < 2;layer++) {
            int newOffset = offset - layer;
            this.placeLeaves(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, foliageAttachment.pos(), foliageRadius, newOffset, foliageAttachment.doubleTrunk(),layer);
        }

        foliageSetter.set(foliageAttachment.pos().above(), ModBlocks.COCONUT_LEAF_CARPET.get().defaultBlockState());
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
            FoliageSetter foliageSetter,
            RandomSource randomSource,
            TreeConfiguration treeConfiguration,
            BlockPos centerPos,
            int radius,
            int yPos,
            boolean bl,
            int layer
    ) {
        int k = bl ? 1 : 0;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        for(int i = 0; i < 2; i++){
            int newRadius = radius + randomSource.nextInt(rand_radius_a,rand_radius_b) - layer;
            for(int l = -newRadius; l <= newRadius;l++){
                int xPos = i == 0 ? l : 0;
                int zPos = i == 1 ? l : 0;
                if (!this.shouldSkipLocationSigned(randomSource, xPos, yPos, zPos, newRadius, bl)){
                    mutableBlockPos.setWithOffset(centerPos,xPos,yPos,zPos);
                    LeafState leafState = LeafState.MIDDLE;
                    if(l == -newRadius || l == newRadius){
                        leafState = LeafState.END;
                    }else if(l == -1 || l == 1){
                        leafState = LeafState.BASE;
                    }
                    tryPlaceCoconutLeaf(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, mutableBlockPos,centerPos,leafState);
                }
            }
        }

    }

    protected static boolean tryPlaceCoconutLeaf(
            LevelSimulatedReader levelSimulatedReader,
            FoliageSetter foliageSetter,
            RandomSource randomSource,
            TreeConfiguration treeConfiguration,
            BlockPos foliagePos,
            BlockPos centerPos,
            LeafState leafState
    ) {
        if (!TreeFeature.validTreePos(levelSimulatedReader, foliagePos)) {
            return false;
        } else {
            if(leafState == LeafState.END){
                if(levelSimulatedReader.isStateAtPosition(foliagePos,blockState -> blockState.is(ModBlocks.COCONUT_LEAF.get()))){
                    return false;
                }
            }

            BlockState blockState = ModBlocks.COCONUT_LEAF.get().defaultBlockState().setValue(CoconutLeafBlock.END,leafState == LeafState.END);

            if(leafState == LeafState.BASE){
                blockState = ModBlocks.BUDDING_COCONUT_LEAF.get().defaultBlockState();
            }

            if (blockState.hasProperty(BlockStateProperties.WATERLOGGED)) {
                blockState = blockState.setValue(
                        BlockStateProperties.WATERLOGGED, levelSimulatedReader.isFluidAtPosition(foliagePos, fluidState -> fluidState.isSourceOfType(Fluids.WATER))
                );
            }

            if(blockState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)){
                BlockPos blockPos = new BlockPos(centerPos.getX() - foliagePos.getX(),0,centerPos.getZ() - foliagePos.getZ());
                Direction direction = Direction.getNearest(blockPos.getX(),blockPos.getY(),blockPos.getZ());
                blockState = blockState.setValue(BlockStateProperties.HORIZONTAL_FACING,direction.getOpposite());
            }


            foliageSetter.set(foliagePos, blockState);
            return true;
        }
    }

    public enum LeafState{
        BASE,
        MIDDLE,
        END
    }
}
