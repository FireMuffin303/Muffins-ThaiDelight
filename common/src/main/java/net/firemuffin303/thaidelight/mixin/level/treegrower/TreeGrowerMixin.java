package net.firemuffin303.thaidelight.mixin.level.treegrower;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModFeatures;
import net.firemuffin303.thaidelight.common.world.trees.DurianTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TreeGrower.class)
public abstract class TreeGrowerMixin {
    @Shadow @Final private String name;

    @Shadow @Nullable protected abstract ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource randomSource);

    @Shadow protected abstract boolean hasFlowers(LevelAccessor levelAccessor, BlockPos blockPos);

    @Inject(method = "growTree",at = @At("HEAD"), cancellable = true)
    public void muffins$onGrowTree(ServerLevel serverLevel, ChunkGenerator chunkGenerator, BlockPos blockPos, BlockState blockState, RandomSource randomSource, CallbackInfoReturnable<Boolean> cir){
        if(this.name.equals("durian")){
            ResourceKey<ConfiguredFeature<?,?>> resourceKey = this.getConfiguredMegaFeature(randomSource);
            if(resourceKey != null){
                if(this.hasFlowers(serverLevel,blockPos)){
                    resourceKey = ModFeatures.FEATURE_TALL_DURIAN_TREE_BEE;
                }

                Holder<ConfiguredFeature<?, ?>> holder = serverLevel.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(resourceKey).orElse((Holder.Reference<ConfiguredFeature<?, ?>>) null);
                if(holder != null){
                    BlockPattern.BlockPatternMatch blockPatternMatch = DurianTreeGrower.getOrCreatePattern().find(serverLevel,blockPos);

                    if (blockPatternMatch != null) {
                        BlockPos centerPos = blockPatternMatch.getBlock(1,0,1).getPos();
                        ConfiguredFeature<?, ?> configuredFeature = holder.value();
                        BlockState blockState2 = Blocks.AIR.defaultBlockState();
                        serverLevel.setBlock(centerPos, blockState2, 4);
                        serverLevel.setBlock(centerPos.offset( 1, 0,0), blockState2, 4);
                        serverLevel.setBlock(centerPos.offset(-1, 0, 0), blockState2, 4);
                        serverLevel.setBlock(centerPos.offset(0, 0, 1), blockState2, 4);
                        serverLevel.setBlock(centerPos.offset(0, 0, -1), blockState2, 4);
                        if (configuredFeature.place(serverLevel, chunkGenerator, randomSource, centerPos)) {
                            cir.setReturnValue(true);
                        } else {
                            serverLevel.setBlock(centerPos, blockState, 4);
                            serverLevel.setBlock(centerPos.offset( 1, 0,0), blockState, 4);
                            serverLevel.setBlock(centerPos.offset( -1, 0,0), blockState, 4);
                            serverLevel.setBlock(centerPos.offset(0, 0, 1), blockState, 4);
                            serverLevel.setBlock(centerPos.offset(0, 0, -1), blockState, 4);
                            cir.setReturnValue(false);
                        }
                    }
                }

            }
        }
    }



}
