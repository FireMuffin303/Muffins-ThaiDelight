package net.firemuffin303.thaidelight.common.registry;

import com.google.common.collect.ImmutableList;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.block.vegetations.durian.DurianFlowerBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.mango.HangingMangoBlock;
import net.firemuffin303.thaidelight.common.world.feature.*;
import net.firemuffin303.thaidelight.common.world.feature.stateproviders.RandomHorizontalFacingStateProvider;
import net.firemuffin303.thaidelight.mixin.accessor.feature.FoliagePlacerTypeAccessor;
import net.firemuffin303.thaidelight.mixin.accessor.feature.TrunkPlacerTypeAccessor;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ClampedInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Supplier;

public class ModFeatures {
    public static final ResourceRegistry<TrunkPlacerType<?>> TRUNK_PLACER_REGISTRY = ResourceRegistry.create(Registries.TRUNK_PLACER_TYPE,ThaiDelightCommon.MOD_ID);
    public static final ResourceRegistry<FoliagePlacerType<?>> FOLIAGE_PLACER_REGISTRY = ResourceRegistry.create(Registries.FOLIAGE_PLACER_TYPE,ThaiDelightCommon.MOD_ID);

    public static final Supplier<TrunkPlacerType<?>> LIME_TRUNK_PLACER = TRUNK_PLACER_REGISTRY.register("lime_trunk_placer",() ->TrunkPlacerTypeAccessor.init(LimeTreeTrunkPlacer.CODEC));
    public static final Supplier<TrunkPlacerType<?>> DURIAN_TRUNK_PLACER = TRUNK_PLACER_REGISTRY.register("durian_trunk_placer",() -> TrunkPlacerTypeAccessor.init(DurianTreeTrunkPlacer.CODEC));
    public static final Supplier<TrunkPlacerType<?>> COCONUT_TRUNK_PLACER = TRUNK_PLACER_REGISTRY.register("coconut_trunk_placer",() -> TrunkPlacerTypeAccessor.init(CoconutTreeTrunkPlacer.CODEC));
    public static final Supplier<TrunkPlacerType<?>> MEGA_DURIAN_TRUNK_PLACER = TRUNK_PLACER_REGISTRY.register("mega_durian_trunk_placer",() -> TrunkPlacerTypeAccessor.init(MegaDurianTrunkPlacer.CODEC));

    public static final Supplier<FoliagePlacerType<?>> DURIAN_FOLIAGE_PLACER = FOLIAGE_PLACER_REGISTRY.register("durian_foliage_placer",() ->FoliagePlacerTypeAccessor.init(DurianTreeFoliagePlacer.CODEC));
    public static final Supplier<FoliagePlacerType<?>> HANGING_BLOB_FOLIAGE_PLACER = FOLIAGE_PLACER_REGISTRY.register("hanging_blob_foliage_placer", () -> FoliagePlacerTypeAccessor.init(HangingBlobFoliagePlacer.CODEC));
    public static final Supplier<FoliagePlacerType<?>> COCONUT_FOLIAGE_PLACER = FOLIAGE_PLACER_REGISTRY.register("coconut_foliage_placer",() -> FoliagePlacerTypeAccessor.init(CoconutLeavesFoliagePlacer.CODEC));
    public static final Supplier<FoliagePlacerType<?>> PAPAYA_FOLIAGE_PLACER = FOLIAGE_PLACER_REGISTRY.register("papaya_foliage_placer",() -> FoliagePlacerTypeAccessor.init(PapayaLeavesFoliagePlacer.CODEC));

    public static final ResourceKey<ConfiguredFeature<?,?>> FEATURE_PATCH_LIME_BUSH;
    public static final ResourceKey<ConfiguredFeature<?,?>> FEATURE_PATCH_WILD_PEPPER;
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_PATCH_WILD_HOLY_BASIL = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelightCommon.modid("patch_wild_holy_basil"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_PATCH_WILD_BASIL = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelightCommon.modid("patch_wild_basil"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_PATCH_WILD_ALL_BASIL = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelightCommon.modid("patch_wild_all_basil"));

    //Papaya
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_PAPAYA_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelightCommon.modid("papaya_tree"));

    //Durian
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_DURIAN_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelightCommon.modid("durian_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_DURAIN_TREE_BEE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelightCommon.modid("durian_tree_bee"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_TALL_DURIAN_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelightCommon.modid("tall_durian_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_TALL_DURIAN_TREE_BEE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelightCommon.modid("tall_durian_tree_bee"));

    //Mango
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_MANGO_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelightCommon.modid("mango_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_MANGO_TREE_BEE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelightCommon.modid("mango_tree_bee"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_FANCY_MANGO_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelightCommon.modid("fancy_mango_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_FANCY_MANGO_TREE_BEE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelightCommon.modid("fancy_mango_tree_bee"));

    //Coconut
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_COCONUT_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelightCommon.modid("coconut_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_COCONUT_TREE_BEE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelightCommon.modid("coconut_tree_bee"));

    //Lime
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_LIME_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelightCommon.modid("lime_tree"));

    //Butterfly Pea
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_BUTTERFLY_PEA = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelightCommon.modid("butterfly_pea_vine"));

    public static final ResourceKey<PlacedFeature> PATCH_LIME_BUSH;
    public static final ResourceKey<PlacedFeature> PATCH_WILD_PEPPER;
    public static final ResourceKey<PlacedFeature> PATCH_WILD_HOLY_BASIL = ResourceKey.create(Registries.PLACED_FEATURE,ThaiDelightCommon.modid("patch_wild_holy_basil"));
    public static final ResourceKey<PlacedFeature> PATCH_WILD_BASIL = ResourceKey.create(Registries.PLACED_FEATURE,ThaiDelightCommon.modid("patch_wild_basil"));
    public static final ResourceKey<PlacedFeature> PATCH_WILD_ALL_BASIL = ResourceKey.create(Registries.PLACED_FEATURE,ThaiDelightCommon.modid("patch_wild_all_basil"));
    public static final ResourceKey<PlacedFeature> PATCH_BUTTERFLY_PEA = ResourceKey.create(Registries.PLACED_FEATURE,ThaiDelightCommon.modid("patch_butterfly_pea"));
    public static final ResourceKey<PlacedFeature> TREES_PAPAYA = ResourceKey.create(Registries.PLACED_FEATURE,ThaiDelightCommon.modid("trees_papaya"));
    public static final ResourceKey<PlacedFeature> TREES_DURIAN = ResourceKey.create(Registries.PLACED_FEATURE,ThaiDelightCommon.modid("trees_durian"));
    public static final ResourceKey<PlacedFeature> TREES_DURIAN_SPARSE_JUNGLE = ResourceKey.create(Registries.PLACED_FEATURE,ThaiDelightCommon.modid("trees_durian_sparse"));
    public static final ResourceKey<PlacedFeature> TREES_MANGO = ResourceKey.create(Registries.PLACED_FEATURE,ThaiDelightCommon.modid("trees_mango"));
    public static final ResourceKey<PlacedFeature> TREES_COCONUT = ResourceKey.create(Registries.PLACED_FEATURE,ThaiDelightCommon.modid("trees_coconut"));

    public static void init(){
        TRUNK_PLACER_REGISTRY.init();
        FOLIAGE_PLACER_REGISTRY.init();
    }


    public static void bootstrapPlacedFeature(BootstapContext<PlacedFeature> bootstapContext){
        Holder.Reference<ConfiguredFeature<?,?>> config_lime_bush = bootstapContext.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModFeatures.FEATURE_PATCH_LIME_BUSH);
        Holder.Reference<ConfiguredFeature<?,?>> config_wild_pepper = bootstapContext.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModFeatures.FEATURE_PATCH_WILD_PEPPER);
        Holder.Reference<ConfiguredFeature<?,?>> config_wild_holy_basil = bootstapContext.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModFeatures.FEATURE_PATCH_WILD_HOLY_BASIL);
        Holder.Reference<ConfiguredFeature<?,?>> config_wild_basil = bootstapContext.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModFeatures.FEATURE_PATCH_WILD_BASIL);
        Holder.Reference<ConfiguredFeature<?,?>> config_wild_all_basil = bootstapContext.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModFeatures.FEATURE_PATCH_WILD_ALL_BASIL);
        Holder.Reference<ConfiguredFeature<?,?>> config_butterfly_pea = bootstapContext.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModFeatures.FEATURE_BUTTERFLY_PEA);

        Holder.Reference<ConfiguredFeature<?,?>> durian_tree_checked = bootstapContext.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModFeatures.FEATURE_DURIAN_TREE);
        Holder.Reference<ConfiguredFeature<?,?>> tall_durian_tree_checked = bootstapContext.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModFeatures.FEATURE_TALL_DURIAN_TREE);
        Holder.Reference<ConfiguredFeature<?,?>> mango_tree_checked = bootstapContext.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModFeatures.FEATURE_FANCY_MANGO_TREE);
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

        bootstapContext.register(ModFeatures.PATCH_WILD_HOLY_BASIL,new PlacedFeature(config_wild_holy_basil,
                List.of(
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        BiomeFilter.biome()
                )
        ));

        bootstapContext.register(ModFeatures.PATCH_WILD_BASIL,new PlacedFeature(config_wild_basil,
                List.of(
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        BiomeFilter.biome()
                )
        ));

        bootstapContext.register(ModFeatures.PATCH_WILD_ALL_BASIL,new PlacedFeature(config_wild_all_basil,
                List.of(
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                        RarityFilter.onAverageOnceEvery(64),
                        InSquarePlacement.spread(),
                        BiomeFilter.biome()
                )
        ));

        bootstapContext.register(ModFeatures.PATCH_BUTTERFLY_PEA,new PlacedFeature(config_butterfly_pea,
                List.of(
                    BiomeFilter.biome(),
                    InSquarePlacement.spread(),
                    CountPlacement.of(127),
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(64),VerticalAnchor.absolute(100))
                )
        ));

        bootstapContext.register(ModFeatures.TREES_DURIAN, new PlacedFeature(tall_durian_tree_checked,
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1,0.02f,1),ModBlocks.DURIAN_SAPLING.get())));
        bootstapContext.register(ModFeatures.TREES_DURIAN_SPARSE_JUNGLE, new PlacedFeature(durian_tree_checked,
                VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(50),ModBlocks.DURIAN_SAPLING.get())));

        bootstapContext.register(ModFeatures.TREES_MANGO,new PlacedFeature(mango_tree_checked, ImmutableList.<PlacementModifier>builder()
                .add(CountPlacement.of(ClampedInt.of(UniformInt.of(-3,1),0,1)))
                .add(RarityFilter.onAverageOnceEvery(8))
                .add(InSquarePlacement.spread())
                .add(SurfaceWaterDepthFilter.forMaxDepth(0))
                .add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
                .add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.MANGO_SAPLING.get().defaultBlockState(), BlockPos.ZERO)))
                .add(BiomeFilter.biome()).build())
        );

        bootstapContext.register(ModFeatures.TREES_COCONUT,new PlacedFeature(coconut_tree_checked,ImmutableList.<PlacementModifier>builder()
                .add(CountPlacement.of(ClampedInt.of(UniformInt.of(-3,1),0,1)))
                .add(PlacementUtils.countExtra(1,0.02f,1))
                .add(InSquarePlacement.spread())
                .add(SurfaceWaterDepthFilter.forMaxDepth(0))
                .add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
                .add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.COCONUT_SAPLING.get().defaultBlockState(), BlockPos.ZERO)))
                .add(BiomeFilter.biome()).build()));

        bootstapContext.register(ModFeatures.TREES_PAPAYA,new PlacedFeature(papaya_tree_checked,ImmutableList.<PlacementModifier>builder()
                .add(RarityFilter.onAverageOnceEvery(20))
                .add(InSquarePlacement.spread())
                .add(SurfaceWaterDepthFilter.forMaxDepth(0))
                .add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
                .add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.PAPAYA_SAPLING.get().defaultBlockState(), BlockPos.ZERO)))
                .add(BiomeFilter.biome())
                .build()));

    }

    /*

    public static void dataGen(HolderLookup.Provider provider, FabricDynamicRegistryProvider.Entries entries){
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_PATCH_LIME_BUSH);
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_PATCH_WILD_PEPPER);
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_PATCH_WILD_HOLY_BASIL);
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_PATCH_WILD_BASIL);
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_PATCH_WILD_ALL_BASIL);
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_BUTTERFLY_PEA);

        //Durian
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_DURIAN_TREE);
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_DURAIN_TREE_BEE);
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_TALL_DURIAN_TREE);
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_TALL_DURIAN_TREE_BEE);

        //Mango
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_MANGO_TREE);
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_MANGO_TREE_BEE);
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_FANCY_MANGO_TREE);
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_FANCY_MANGO_TREE_BEE);

        //Coconut
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_COCONUT_TREE);
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_COCONUT_TREE_BEE);

        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_PAPAYA_TREE);

        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_LIME_TREE);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE),ModFeatures.PATCH_LIME_BUSH);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE),ModFeatures.PATCH_WILD_PEPPER);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE), ModFeatures.PATCH_WILD_HOLY_BASIL);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE), ModFeatures.PATCH_WILD_BASIL);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE), ModFeatures.PATCH_WILD_ALL_BASIL);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE),ModFeatures.PATCH_BUTTERFLY_PEA);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE),ModFeatures.TREES_DURIAN);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE),ModFeatures.TREES_DURIAN_SPARSE_JUNGLE);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE),ModFeatures.TREES_MANGO);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE),ModFeatures.TREES_COCONUT);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE),ModFeatures.TREES_PAPAYA);



    }

     */

    public static TreeConfiguration.TreeConfigurationBuilder createShortDurianTree(List<TreeDecorator> treeDecorators){
        return createDurianTree(8,2,treeDecorators);
    }


    public static TreeConfiguration.TreeConfigurationBuilder createTallDurianTree(List<TreeDecorator> treeDecorators){
        List<TreeDecorator> decorators = new ArrayList<>();
        decorators.add(new AttachedToLeavesDecorator(0.15f,1,0,
                BlockStateProvider.simple(ModBlocks.DURIAN_FLOWER.get().defaultBlockState().setValue(DurianFlowerBlock.HANGING,true)),
                2,List.of(Direction.DOWN)));

        decorators.addAll(treeDecorators);


        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.DURIAN_LOG.get()),
                new MegaDurianTrunkPlacer(15,1,0, 2,UniformInt.of(-5,-4), UniformInt.of(4,6),UniformInt.of(2,4)),
                BlockStateProvider.simple(ModBlocks.DURIAN_LEAVES.get()),
                new DurianTreeFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0),0.4f,0.12f),
                new TwoLayersFeatureSize(1,0,1)
        ).ignoreVines().decorators(decorators);
    }

    private static TreeConfiguration.TreeConfigurationBuilder createDurianTree(int baseHeight,int heightRandA,List<TreeDecorator> treeDecorators) {
        List<TreeDecorator> decorators = new ArrayList<>();
        decorators.add(new AttachedToLeavesDecorator(0.15f,1,0,
                BlockStateProvider.simple(ModBlocks.DURIAN_FLOWER.get().defaultBlockState().setValue(DurianFlowerBlock.HANGING,true)),
                2,List.of(Direction.DOWN)));

        decorators.addAll(treeDecorators);


        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.DURIAN_LOG.get()),
                new DurianTreeTrunkPlacer(baseHeight,heightRandA,0, UniformInt.of(-4,-2), UniformInt.of(2,3),UniformInt.of(2,4)),
                BlockStateProvider.simple(ModBlocks.DURIAN_LEAVES.get()),
                new DurianTreeFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0),0.4f,0.12f),
                new TwoLayersFeatureSize(1,0,1)
        ).ignoreVines().decorators(decorators);
    }

    public static TreeConfiguration.TreeConfigurationBuilder createMangoTree(List<TreeDecorator> treeDecorators){
        List<TreeDecorator> decorators = new ArrayList<>();
        decorators.add(new AttachedToLeavesDecorator(0.24f,1,0,new RandomizedIntStateProvider(
                BlockStateProvider.simple(ModBlocks.HANGING_MANGO_BLOCK.get().defaultBlockState()),
                HangingMangoBlock.AGE,UniformInt.of(0,1)
        ),2,List.of(Direction.DOWN)));
        decorators.addAll(treeDecorators);

        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.MANGO_LOG.get()),
                new StraightTrunkPlacer(9,3,0),
                BlockStateProvider.simple(ModBlocks.MANGO_LEAVES.get()),
                new HangingBlobFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0),3,0.35f,0.1f),
                new TwoLayersFeatureSize(1,0,1)
        ).ignoreVines()
                .decorators(decorators);
    }

    public static TreeConfiguration.TreeConfigurationBuilder createFancyMangoTree(List<TreeDecorator> treeDecorators){
        List<TreeDecorator> decorators = new ArrayList<>();
        decorators.add(new AttachedToLeavesDecorator(0.24f,1,0,new RandomizedIntStateProvider(
                new RandomHorizontalFacingStateProvider(ModBlocks.HANGING_MANGO_BLOCK.get().defaultBlockState()),
                HangingMangoBlock.AGE, UniformInt.of(0, 1)
        ),2,List.of(Direction.DOWN)));
        decorators.addAll(treeDecorators);

        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.MANGO_LOG.get()),
                new FancyTrunkPlacer(8, 10, 0),
                BlockStateProvider.simple(ModBlocks.MANGO_LEAVES.get()),
                new HangingBlobFoliagePlacer(ConstantInt.of(3),ConstantInt.of(2),3,0.15f,0.1f),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                .ignoreVines().decorators(decorators);
    }

    public static TreeConfiguration.TreeConfigurationBuilder createCoconutTree(List<TreeDecorator> treeDecorators){
        List<TreeDecorator> decorators = new ArrayList<>(treeDecorators);

        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.COCONUT_LOG.get()),
                new CoconutTreeTrunkPlacer(7,1,2,UniformInt.of(3,5),0.6f),
                BlockStateProvider.simple(ModBlocks.COCONUT_LEAF_END.get()),
                new CoconutLeavesFoliagePlacer(UniformInt.of(2,3),ConstantInt.of(0),2,1,2),
                new TwoLayersFeatureSize(1,0,1)
        ).ignoreVines()
                .decorators(decorators);
    }

    public static TreeConfiguration.TreeConfigurationBuilder createPapayaTree(List<TreeDecorator> treeDecorators){
        List<TreeDecorator> decorators = new ArrayList<>(treeDecorators);

        decorators.add(new PapayaDecorator(0.8f));

        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.PAPAYA_LOG.get()),
                new StraightTrunkPlacer(5,1,2),
                BlockStateProvider.simple(ModBlocks.PAPAYA_LEAVES.get()),
                new PapayaLeavesFoliagePlacer(UniformInt.of(2,3),ConstantInt.of(0),2,BlockStateProvider.simple(ModBlocks.PAPAYA_LEAVES_STEM.get())),
                new TwoLayersFeatureSize(1,0,1)
        ).ignoreVines().decorators(decorators);
    }

    @ExpectPlatform
    public static Supplier<Feature<?>> createPatchWildPepper(){
        throw new AssertionError();
    }


    static {
        FEATURE_PATCH_LIME_BUSH = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelightCommon.modid("patch_lime_bush"));
        FEATURE_PATCH_WILD_PEPPER = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelightCommon.modid("patch_wild_pepper"));

        PATCH_LIME_BUSH = ResourceKey.create(Registries.PLACED_FEATURE,ThaiDelightCommon.modid("patch_lime_bush"));
        PATCH_WILD_PEPPER = ResourceKey.create(Registries.PLACED_FEATURE,ThaiDelightCommon.modid("patch_wild_pepper"));


    }
}
