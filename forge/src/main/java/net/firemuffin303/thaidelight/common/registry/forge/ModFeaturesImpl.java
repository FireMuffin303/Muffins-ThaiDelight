package net.firemuffin303.thaidelight.common.registry.forge;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.registry.ModBiomeFeatures;
import vectorwing.farmersdelight.common.world.configuration.WildCropConfiguration;

import java.util.List;
import java.util.function.Supplier;

import static net.firemuffin303.thaidelight.common.registry.ModFeatures.*;

public class ModFeaturesImpl {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, ThaiDelightCommon.MOD_ID);
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE,ThaiDelightCommon.MOD_ID);

    public static <P extends TrunkPlacer> Supplier<TrunkPlacerType<P>> registerTrunkPlacer(String id, Supplier<TrunkPlacerType<P>> supplier) {
        return TRUNK_PLACER.register(id,supplier);
    }

    public static <P extends FoliagePlacer> Supplier<FoliagePlacerType<P>> registerFoliagePlacer(String id, Supplier<FoliagePlacerType<P>> supplier) {
        return FOLIAGE_PLACER.register(id,supplier);
    }

    public static void bootstrapConfiguredFeature(BootstapContext<ConfiguredFeature<?, ?>> bootstapContext){

        bootstapContext.register(ModFeatures.FEATURE_PATCH_LIME_BUSH,
                new ConfiguredFeature<>(Feature.RANDOM_PATCH,
                        new RandomPatchConfiguration(32,6,3,
                                PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.LIME_PLANT.get())),
                                        BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), List.of(Blocks.GRASS_BLOCK)))
                                )
                        )
                ));

        bootstapContext.register(ModFeatures.FEATURE_PATCH_WILD_PEPPER,new ConfiguredFeature<>(ModBiomeFeatures.WILD_CROP.get(),
                new WildCropConfiguration(32,6,3,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_PEPPER_CROP.get().defaultBlockState())),
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,BlockPredicate.matchesTag(Direction.DOWN.getNormal(), BlockTags.DIRT))),
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.GRASS.defaultBlockState())),
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,BlockPredicate.matchesTag(Direction.DOWN.getNormal(), BlockTags.DIRT))),
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.COARSE_DIRT.defaultBlockState())),
                                BlockPredicate.allOf(BlockPredicate.replaceable(Direction.UP.getNormal()),BlockPredicate.matchesTag(BlockTags.DIRT)))

                )));

        bootstapContext.register(ModFeatures.FEATURE_PATCH_WILD_HOLY_BASIL,new ConfiguredFeature<>(ModBiomeFeatures.WILD_CROP.get(),
                new WildCropConfiguration(24,6,3,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_HOLY_BASIL.get().defaultBlockState())),
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,BlockPredicate.matchesTag(Direction.DOWN.getNormal(), BlockTags.DIRT))),
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.GRASS.defaultBlockState())),
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,BlockPredicate.matchesTag(Direction.DOWN.getNormal(), BlockTags.DIRT))),
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.COARSE_DIRT.defaultBlockState())),
                                BlockPredicate.allOf(BlockPredicate.replaceable(Direction.UP.getNormal()),BlockPredicate.matchesTag(BlockTags.DIRT)))

                )));

        bootstapContext.register(ModFeatures.FEATURE_PATCH_WILD_BASIL,new ConfiguredFeature<>(ModBiomeFeatures.WILD_CROP.get(),
                new WildCropConfiguration(24,6,3,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_BASIL.get().defaultBlockState())),
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,BlockPredicate.matchesTag(Direction.DOWN.getNormal(), BlockTags.DIRT))),
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.GRASS.defaultBlockState())),
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,BlockPredicate.matchesTag(Direction.DOWN.getNormal(), BlockTags.DIRT))),
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.COARSE_DIRT.defaultBlockState())),
                                BlockPredicate.allOf(BlockPredicate.replaceable(Direction.UP.getNormal()),BlockPredicate.matchesTag(BlockTags.DIRT)))

                )));

        bootstapContext.register(ModFeatures.FEATURE_PATCH_WILD_ALL_BASIL,new ConfiguredFeature<>(ModBiomeFeatures.WILD_CROP.get(),
                new WildCropConfiguration(48,8,3,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_BASIL.get().defaultBlockState())),
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,BlockPredicate.matchesTag(Direction.DOWN.getNormal(), BlockTags.DIRT))),
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_HOLY_BASIL.get().defaultBlockState())),
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,BlockPredicate.matchesTag(Direction.DOWN.getNormal(), BlockTags.DIRT))),
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.COARSE_DIRT.defaultBlockState())),
                                BlockPredicate.allOf(BlockPredicate.replaceable(Direction.UP.getNormal()),BlockPredicate.matchesTag(BlockTags.DIRT)))

                )));

        bootstapContext.register(ModFeatures.FEATURE_BUTTERFLY_PEA,new ConfiguredFeature<>(Feature.MULTIFACE_GROWTH,new MultifaceGrowthConfiguration(
                (MultifaceBlock) ModBlocks.BUTTERFLY_PEA_WALL.get(),20,true,true,true,0.5f,
                HolderSet.direct(Block::builtInRegistryHolder,
                        Blocks.OAK_LOG,Blocks.BIRCH_LOG,Blocks.SPRUCE_LOG,
                        Blocks.ACACIA_LOG,Blocks.DARK_OAK_LOG,Blocks.JUNGLE_LOG,
                        Blocks.CHERRY_LOG, Blocks.MANGROVE_LOG,ModBlocks.DURIAN_LOG.get(),
                        ModBlocks.MANGO_LOG.get(),ModBlocks.COCONUT_LOG.get()
                )
        )));

        //Durian
        bootstapContext.register(ModFeatures.FEATURE_DURIAN_TREE,new ConfiguredFeature<>(Feature.TREE,createShortDurianTree(List.of()).build()));
        bootstapContext.register(ModFeatures.FEATURE_DURAIN_TREE_BEE,new ConfiguredFeature<>(Feature.TREE,createShortDurianTree(
                List.of(new BeehiveDecorator(0.05f))).build()));
        bootstapContext.register(ModFeatures.FEATURE_TALL_DURIAN_TREE,new ConfiguredFeature<>(Feature.TREE,createTallDurianTree(List.of()).build()));
        bootstapContext.register(ModFeatures.FEATURE_TALL_DURIAN_TREE_BEE,new ConfiguredFeature<>(Feature.TREE,createTallDurianTree(
                List.of(new BeehiveDecorator(0.05f))).build()));


        bootstapContext.register(ModFeatures.FEATURE_LIME_TREE,new ConfiguredFeature<>(Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new StraightTrunkPlacer(4,2,0),
                        BlockStateProvider.simple(ModBlocks.DURIAN_BLOCK.get()),
                        new BlobFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0),3),
                        new TwoLayersFeatureSize(1,0,1)
                ).ignoreVines().build()
        ));

        //Mango
        bootstapContext.register(ModFeatures.FEATURE_MANGO_TREE,new ConfiguredFeature<>(Feature.TREE, createMangoTree(List.of()).build()));
        bootstapContext.register(ModFeatures.FEATURE_MANGO_TREE_BEE,new ConfiguredFeature<>(Feature.TREE,createMangoTree(List.of(
                new BeehiveDecorator(0.05f)
        )).build()));
        bootstapContext.register(ModFeatures.FEATURE_FANCY_MANGO_TREE,new ConfiguredFeature<>(Feature.TREE,createFancyMangoTree(List.of()).build()));
        bootstapContext.register(ModFeatures.FEATURE_FANCY_MANGO_TREE_BEE,new ConfiguredFeature<>(Feature.TREE,createFancyMangoTree(List.of(
                new BeehiveDecorator(0.05f)
        )).build()));


        bootstapContext.register(ModFeatures.FEATURE_COCONUT_TREE,new ConfiguredFeature<>(Feature.TREE,createCoconutTree(List.of()).build()));
        bootstapContext.register(ModFeatures.FEATURE_COCONUT_TREE_BEE,new ConfiguredFeature<>(Feature.TREE,createCoconutTree(List.of(
                new BeehiveDecorator(0.05f)
        )).build()));

        bootstapContext.register(ModFeatures.FEATURE_PAPAYA_TREE,new ConfiguredFeature<>(Feature.TREE, createPapayaTree(List.of()).build() ));
    }


    public static ConfiguredFeature createPatchWildPepper() {
        return new ConfiguredFeature(ModBiomeFeatures.WILD_CROP.get(),
                new WildCropConfiguration(32,6,3,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_PEPPER_CROP.get().defaultBlockState())),
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,BlockPredicate.matchesTag(Direction.DOWN.getNormal(), BlockTags.DIRT))),
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.GRASS.defaultBlockState())),
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,BlockPredicate.matchesTag(Direction.DOWN.getNormal(), BlockTags.DIRT))),
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.COARSE_DIRT.defaultBlockState())),
                                BlockPredicate.allOf(BlockPredicate.replaceable(Direction.UP.getNormal()),BlockPredicate.matchesTag(BlockTags.DIRT)))

                ));
    }
}
