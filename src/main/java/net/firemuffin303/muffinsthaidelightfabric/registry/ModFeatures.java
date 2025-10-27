package net.firemuffin303.muffinsthaidelightfabric.registry;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.block.durian.HangingDurianBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.mango.MangoBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.world.feature.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ClampedInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.placement.*;
import vectorwing.farmersdelight.common.registry.ModBiomeFeatures;
import vectorwing.farmersdelight.common.world.configuration.WildCropConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ModFeatures {
    public static final TrunkPlacerType<LimeTreeTrunkPlacer> LIME_TRUNK_PLACER = Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, ThaiDelight.modid("lime_trunk_placer"),new TrunkPlacerType<>(LimeTreeTrunkPlacer.CODEC));
    public static final TrunkPlacerType<DurianTreeTrunkPlacer> DURIAN_TRUNK_PLACER = Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE,ThaiDelight.modid("durian_trunk_placer"),new TrunkPlacerType<>(DurianTreeTrunkPlacer.CODEC));
    public static final TrunkPlacerType<CoconutTreeTrunkPlacer> COCONUT_TRUNK_PLACER = Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE,ThaiDelight.modid("coconut_trunk_placer"),new TrunkPlacerType<>(CoconutTreeTrunkPlacer.CODEC));

    public static final FoliagePlacerType<DurianTreeFoliagePlacer> DURIAN_FOLIAGE_PLACER = Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE,ThaiDelight.modid("durian_foliage_placer"),new FoliagePlacerType<>(DurianTreeFoliagePlacer.CODEC));
    public static final FoliagePlacerType<HangingBlobFoliagePlacer> HANGING_BLOB_FOLIAGE_PLACER = Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE,ThaiDelight.modid("hanging_blob_foliage_placer"),new FoliagePlacerType<>(HangingBlobFoliagePlacer.CODEC));
    public static final FoliagePlacerType<CoconutLeavesFoliagePlacer> COCONUT_FOLIAGE_PLACER = Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE,ThaiDelight.modid("coconut_foliage_placer"),new FoliagePlacerType<>(CoconutLeavesFoliagePlacer.CODEC));
    public static final FoliagePlacerType<PapayaLeavesFoliagePlacer> PAPAYA_FOLIAGE_PLACER = Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE,ThaiDelight.modid("papaya_foliage_placer"),new FoliagePlacerType<>(PapayaLeavesFoliagePlacer.CODEC));

    public static final ResourceKey<ConfiguredFeature<?,?>> FEATURE_PATCH_LIME_BUSH;
    public static final ResourceKey<ConfiguredFeature<?,?>> FEATURE_PATCH_WILD_PEPPER;

    //Papaya
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_PAPAYA_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(ThaiDelight.MOD_ID,"papaya_tree"));

    //Durian
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_DURIAN_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelight.modid("durian_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_DURAIN_TREE_BEE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelight.modid("durian_tree_bee"));
    //Mango
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_MANGO_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelight.modid("mango_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_MANGO_TREE_BEE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelight.modid("mango_tree_bee"));
    //Coconut
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_COCONUT_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelight.modid("coconut_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_COCONUT_TREE_BEE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelight.modid("coconut_tree_bee"));

    //Lime
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_LIME_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelight.modid("lime_tree"));

    public static final ResourceKey<PlacedFeature> PATCH_LIME_BUSH;
    public static final ResourceKey<PlacedFeature> PATCH_WILD_PEPPER;
    public static final ResourceKey<PlacedFeature> TREES_PAPAYA = ResourceKey.create(Registries.PLACED_FEATURE,new ResourceLocation(ThaiDelight.MOD_ID,"trees_papaya"));
    public static final ResourceKey<PlacedFeature> TREES_DURIAN = ResourceKey.create(Registries.PLACED_FEATURE,ThaiDelight.modid("trees_durian"));
    public static final ResourceKey<PlacedFeature> TREES_DURIAN_SPARSE_JUNGLE = ResourceKey.create(Registries.PLACED_FEATURE,ThaiDelight.modid("trees_durian_sparse"));
    public static final ResourceKey<PlacedFeature> TREES_MANGO = ResourceKey.create(Registries.PLACED_FEATURE,ThaiDelight.modid("trees_mango"));
    public static final ResourceKey<PlacedFeature> TREES_COCONUT = ResourceKey.create(Registries.PLACED_FEATURE,ThaiDelight.modid("trees_coconut"));

    public static void init(){}

    public static void bootstrapConfiguredFeature(BootstapContext<ConfiguredFeature<?, ?>> bootstapContext){

        bootstapContext.register(ModFeatures.FEATURE_PATCH_LIME_BUSH,
                new ConfiguredFeature<>(Feature.RANDOM_PATCH,
                        new RandomPatchConfiguration(32,6,3,
                                PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.LIME_PLANT)),
                                        BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), List.of(Blocks.GRASS_BLOCK)))
                                )
                        )
                ));

        bootstapContext.register(ModFeatures.FEATURE_PATCH_WILD_PEPPER,new ConfiguredFeature<>(ModBiomeFeatures.WILD_CROP.get(),
                new WildCropConfiguration(32,6,3,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_PEPPER_CROP.defaultBlockState())),
                        BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,BlockPredicate.matchesTag(Direction.DOWN.getNormal(), BlockTags.DIRT))),
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.GRASS.defaultBlockState())),
                                BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,BlockPredicate.matchesTag(Direction.DOWN.getNormal(), BlockTags.DIRT))),
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.COARSE_DIRT.defaultBlockState())),
                                BlockPredicate.allOf(BlockPredicate.replaceable(Direction.UP.getNormal()),BlockPredicate.matchesTag(BlockTags.DIRT)))

                        )));


        //Durian
        bootstapContext.register(ModFeatures.FEATURE_DURIAN_TREE,new ConfiguredFeature<>(Feature.TREE,createDurianTree(List.of()).build()));
        bootstapContext.register(ModFeatures.FEATURE_DURAIN_TREE_BEE,new ConfiguredFeature<>(Feature.TREE,createDurianTree(
                List.of(new BeehiveDecorator(0.05f))).build()));


        bootstapContext.register(ModFeatures.FEATURE_LIME_TREE,new ConfiguredFeature<>(Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new StraightTrunkPlacer(4,2,0),
                        BlockStateProvider.simple(ModBlocks.DURIAN_BLOCK),
                        new BlobFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0),3),
                        new TwoLayersFeatureSize(1,0,1)
                ).ignoreVines().build()
                ));

        //Mango
        bootstapContext.register(ModFeatures.FEATURE_MANGO_TREE,new ConfiguredFeature<>(Feature.TREE, createMangoTree(List.of()).build()));
        bootstapContext.register(ModFeatures.FEATURE_MANGO_TREE_BEE,new ConfiguredFeature<>(Feature.TREE,createMangoTree(List.of(
                new BeehiveDecorator(0.05f)
        )).build()));


        bootstapContext.register(ModFeatures.FEATURE_COCONUT_TREE,new ConfiguredFeature<>(Feature.TREE,createCoconutTree(List.of()).build()));
        bootstapContext.register(ModFeatures.FEATURE_COCONUT_TREE_BEE,new ConfiguredFeature<>(Feature.TREE,createCoconutTree(List.of(
                new BeehiveDecorator(0.05f)
        )).build()));

        bootstapContext.register(ModFeatures.FEATURE_PAPAYA_TREE,new ConfiguredFeature<>(Feature.TREE, createPapayaTree(List.of()).build() ));
    }

    public static void bootstrapPlacedFeature(BootstapContext<PlacedFeature> bootstapContext){
        var config_lime_bush = bootstapContext.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModFeatures.FEATURE_PATCH_LIME_BUSH);
        var config_wild_pepper = bootstapContext.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModFeatures.FEATURE_PATCH_WILD_PEPPER);

        Holder.Reference<ConfiguredFeature<?,?>> durian_tree_checked = bootstapContext.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModFeatures.FEATURE_DURIAN_TREE);
        Holder.Reference<ConfiguredFeature<?,?>> mango_tree_checked = bootstapContext.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModFeatures.FEATURE_MANGO_TREE);
        Holder.Reference<ConfiguredFeature<?,?>> coconut_tree_checked = bootstapContext.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModFeatures.FEATURE_COCONUT_TREE);
        Holder.Reference<ConfiguredFeature<?,?>> papaya_tree_checked = bootstapContext.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModFeatures.FEATURE_PAPAYA_TREE);


        bootstapContext.register(ModFeatures.PATCH_LIME_BUSH,new PlacedFeature(config_lime_bush,
                List.of(
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                        RarityFilter.onAverageOnceEvery(48),
                        InSquarePlacement.spread(),
                        BiomeFilter.biome()
                )
        ));

        bootstapContext.register(ModFeatures.PATCH_WILD_PEPPER,new PlacedFeature(config_wild_pepper,
                List.of(
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        BiomeFilter.biome()
                )
        ));

        bootstapContext.register(ModFeatures.TREES_DURIAN, new PlacedFeature(durian_tree_checked,
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1,0.02f,1),ModBlocks.DURIAN_SAPLING)));
        bootstapContext.register(ModFeatures.TREES_DURIAN_SPARSE_JUNGLE, new PlacedFeature(durian_tree_checked,
                VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(50),ModBlocks.DURIAN_SAPLING)));

        bootstapContext.register(ModFeatures.TREES_MANGO,new PlacedFeature(mango_tree_checked, ImmutableList.<PlacementModifier>builder()
                .add(CountPlacement.of(ClampedInt.of(UniformInt.of(-3,1),0,1)))
                .add(RarityFilter.onAverageOnceEvery(5))
                .add(InSquarePlacement.spread())
                .add(SurfaceWaterDepthFilter.forMaxDepth(0))
                .add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
                .add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.MANGO_SAPLING.defaultBlockState(), BlockPos.ZERO)))
                .add(BiomeFilter.biome()).build())
        );

        bootstapContext.register(ModFeatures.TREES_COCONUT,new PlacedFeature(coconut_tree_checked,ImmutableList.<PlacementModifier>builder()
                .add(CountPlacement.of(ClampedInt.of(UniformInt.of(-3,1),0,1)))
                .add(PlacementUtils.countExtra(1,0.02f,1))
                .add(InSquarePlacement.spread())
                .add(SurfaceWaterDepthFilter.forMaxDepth(0))
                .add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
                .add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.COCONUT_SAPLING.defaultBlockState(), BlockPos.ZERO)))
                .add(BiomeFilter.biome()).build()));

        bootstapContext.register(ModFeatures.TREES_PAPAYA,new PlacedFeature(papaya_tree_checked,ImmutableList.<PlacementModifier>builder()
                .add(RarityFilter.onAverageOnceEvery(20))
                .add(InSquarePlacement.spread())
                .add(SurfaceWaterDepthFilter.forMaxDepth(0))
                .add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
                .add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.PAPAYA_SAPLING.defaultBlockState(), BlockPos.ZERO)))
                .add(BiomeFilter.biome())
                .build()));
    }

    public static void dataGen(HolderLookup.Provider provider, FabricDynamicRegistryProvider.Entries entries){
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_PATCH_LIME_BUSH);
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_PATCH_WILD_PEPPER);

        //Durian
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_DURIAN_TREE);
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_DURAIN_TREE_BEE);

        //Mango
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_MANGO_TREE);
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_MANGO_TREE_BEE);

        //Coconut
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_COCONUT_TREE);
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_COCONUT_TREE_BEE);

        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_PAPAYA_TREE);

        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_LIME_TREE);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE),ModFeatures.PATCH_LIME_BUSH);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE),ModFeatures.PATCH_WILD_PEPPER);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE),ModFeatures.TREES_DURIAN);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE),ModFeatures.TREES_DURIAN_SPARSE_JUNGLE);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE),ModFeatures.TREES_MANGO);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE),ModFeatures.TREES_COCONUT);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE),ModFeatures.TREES_PAPAYA);

    }

    private static TreeConfiguration.TreeConfigurationBuilder createDurianTree(List<TreeDecorator> treeDecorators) {
        List<TreeDecorator> decorators = new ArrayList<>();
        decorators.add(new AttachedToLeavesDecorator(0.15f,1,0,
                BlockStateProvider.simple(ModBlocks.DURIAN_FLOWER.defaultBlockState().setValue(HangingDurianBlock.HANGING,true)),
                2,List.of(Direction.DOWN)));

        decorators.addAll(treeDecorators);


        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.DURIAN_LOG),
                new DurianTreeTrunkPlacer(6,2,0, UniformInt.of(-4,-2), UniformInt.of(2,4),UniformInt.of(2,4)),
                BlockStateProvider.simple(ModBlocks.DURIAN_LEAVES),
                new DurianTreeFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0),0.4f,0.12f),
                new TwoLayersFeatureSize(1,0,1)
        ).ignoreVines().decorators(decorators);
    }

    private static TreeConfiguration.TreeConfigurationBuilder createMangoTree(List<TreeDecorator> treeDecorators){
        List<TreeDecorator> decorators = new ArrayList<>();
        decorators.add(new AttachedToLeavesDecorator(0.24f,1,0,new RandomizedIntStateProvider(
                BlockStateProvider.simple(ModBlocks.MANGO_BLOCK.defaultBlockState().setValue(MangoBlock.HANGING,true)),
                MangoBlock.AGE,UniformInt.of(0,1)
        ),2,List.of(Direction.DOWN)));
        decorators.addAll(treeDecorators);

        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(6,1,0),
                BlockStateProvider.simple(ModBlocks.MANGO_LEAVES),
                new HangingBlobFoliagePlacer(UniformInt.of(2,3),ConstantInt.of(0),3,0.35f,0.1f),
                new TwoLayersFeatureSize(1,0,1)
        ).ignoreVines()
                .decorators(decorators);
    }

    private static TreeConfiguration.TreeConfigurationBuilder createCoconutTree(List<TreeDecorator> treeDecorators){
        List<TreeDecorator> decorators = new ArrayList<>(treeDecorators);

        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new CoconutTreeTrunkPlacer(6,1,2),
                BlockStateProvider.simple(ModBlocks.COCONUT_LEAF_END),
                new CoconutLeavesFoliagePlacer(UniformInt.of(2,3),ConstantInt.of(0),2,1,2),
                new TwoLayersFeatureSize(1,0,1)
        ).ignoreVines()
                .decorators(decorators);
    }

    private static TreeConfiguration.TreeConfigurationBuilder createPapayaTree(List<TreeDecorator> treeDecorators){
        List<TreeDecorator> decorators = new ArrayList<>(treeDecorators);

        decorators.add(new PapayaDecorator(0.8f));

        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.PAPAYA_LOG),
                new StraightTrunkPlacer(5,1,2),
                BlockStateProvider.simple(ModBlocks.PAPAYA_LEAVES),
                new PapayaLeavesFoliagePlacer(UniformInt.of(2,3),ConstantInt.of(0),2,BlockStateProvider.simple(ModBlocks.PAPAYA_LEAVES_STEM)),
                new TwoLayersFeatureSize(1,0,1)
        ).ignoreVines().decorators(decorators);
    }

    static {
        FEATURE_PATCH_LIME_BUSH = ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(ThaiDelight.MOD_ID,"patch_lime_bush"));
        FEATURE_PATCH_WILD_PEPPER = ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(ThaiDelight.MOD_ID,"patch_wild_pepper"));

        PATCH_LIME_BUSH = ResourceKey.create(Registries.PLACED_FEATURE,new ResourceLocation(ThaiDelight.MOD_ID,"patch_lime_bush"));
        PATCH_WILD_PEPPER = ResourceKey.create(Registries.PLACED_FEATURE,new ResourceLocation(ThaiDelight.MOD_ID,"patch_wild_pepper"));


    }
}
