package net.firemuffin303.muffinsthaidelightfabric.common.world.trees;

import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModFeatures;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class DurianTreeGrower extends AbstractTreeGrower {
    @Nullable
    protected BlockPattern blockPattern = null;

    @Override
    protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean bl) {
        return bl ? ModFeatures.FEATURE_DURAIN_TREE_BEE : ModFeatures.FEATURE_DURIAN_TREE;
    }

    public boolean growTree(ServerLevel serverLevel, ChunkGenerator chunkGenerator, BlockPos blockPos, BlockState blockState, RandomSource randomSource) {
        BlockPattern.BlockPatternMatch blockPatternMatch = this.getOrCreatePattern().find(serverLevel,blockPos);

        /*int saplingAmount = 0;
        for(Direction direction: Direction.Plane.HORIZONTAL){
            BlockState blockState1 = serverLevel.getBlockState(blockPos.relative(direction));
            Block saplingBlock = blockState.getBlock();

            if(!blockState1.is(saplingBlock)){
                saplingAmount = 0;

            }else if(blockState1.is(saplingBlock)){
                saplingAmount++;
            } else if(blockState1.is(blockState.getBlock()) && !shouldBigTree){
                for(Direction direction2: Direction.Plane.HORIZONTAL){
                    BlockPos blockPos1 =  blockPos.relative(direction2);
                    if(serverLevel.getBlockState(blockPos1.relative(direction)).is(blockState.getBlock())){
                        saplingAmount++;
                    }
                }

            }
        }

         */

        if (blockPatternMatch != null) {
            //return super.growTree(serverLevel, chunkGenerator, blockPos, blockState, randomSource);
            BlockPos blockPos1 = blockPatternMatch.getBlock(1,0,1).getPos();
            return this.placeMega(serverLevel, chunkGenerator, blockPos1, blockState, randomSource);
        }
        return super.growTree(serverLevel, chunkGenerator, blockPos, blockState, randomSource);
    }

    private BlockPattern getOrCreatePattern(){
        if(blockPattern == null){
            this.blockPattern = BlockPatternBuilder.start()
                    .aisle("#S#")
                    .aisle("SSS")
                    .aisle("#S#")
                    .where('S', BlockInWorld.hasState(BlockStatePredicate.forBlock(ModBlocks.DURIAN_SAPLING)))
                    .where('#',BlockInWorld.hasState(blockState -> !blockState.is(ModBlocks.DURIAN_SAPLING)))
                    .build();
        }

        return this.blockPattern;
    }

    public boolean placeMega(
            ServerLevel serverLevel, ChunkGenerator chunkGenerator, BlockPos centerPos, BlockState blockState, RandomSource randomSource
    ) {
        ResourceKey<ConfiguredFeature<?, ?>> resourceKey = this.getConfiguredMegaFeature(randomSource,this.hasFlowers(serverLevel, centerPos));
        if (resourceKey == null) {
            return false;
        } else {
            Holder<ConfiguredFeature<?, ?>> holder = serverLevel.registryAccess()
                    .registryOrThrow(Registries.CONFIGURED_FEATURE)
                    .getHolder(resourceKey)
                    .orElse(null);
            if (holder == null) {
                return false;
            } else {
                ConfiguredFeature<?, ?> configuredFeature = holder.value();
                BlockState blockState2 = Blocks.AIR.defaultBlockState();
                serverLevel.setBlock(centerPos, blockState2, 4);
                serverLevel.setBlock(centerPos.offset( 1, 0,0), blockState2, 4);
                serverLevel.setBlock(centerPos.offset(-1, 0, 0), blockState2, 4);
                serverLevel.setBlock(centerPos.offset(0, 0, 1), blockState2, 4);
                serverLevel.setBlock(centerPos.offset(0, 0, -1), blockState2, 4);
                if (configuredFeature.place(serverLevel, chunkGenerator, randomSource, centerPos)) {
                    return true;
                } else {
                    serverLevel.setBlock(centerPos, blockState, 4);
                    serverLevel.setBlock(centerPos.offset( 1, 0,0), blockState, 4);
                    serverLevel.setBlock(centerPos.offset( -1, 0,0), blockState, 4);
                    serverLevel.setBlock(centerPos.offset(0, 0, 1), blockState, 4);
                    serverLevel.setBlock(centerPos.offset(0, 0, -1), blockState, 4);
                    return false;
                }
            }
        }
    }

    protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource randomSource,boolean bl) {

         return bl ? ModFeatures.FEATURE_TALL_DURIAN_TREE_BEE : ModFeatures.FEATURE_TALL_DURIAN_TREE;
    }

    private boolean hasFlowers(LevelAccessor levelAccessor, BlockPos blockPos) {
        for (BlockPos blockPos2 : BlockPos.MutableBlockPos.betweenClosed(blockPos.below().north(2).west(2), blockPos.above().south(2).east(2))) {
            if (levelAccessor.getBlockState(blockPos2).is(BlockTags.FLOWERS)) {
                return true;
            }
        }

        return false;
    }
}
