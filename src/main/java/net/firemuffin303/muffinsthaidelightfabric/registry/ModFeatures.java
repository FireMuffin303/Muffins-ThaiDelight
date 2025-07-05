package net.firemuffin303.muffinsthaidelightfabric.registry;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.block.durian.DurianBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.world.feature.DurianTreeFoliagePlacer;
import net.firemuffin303.muffinsthaidelightfabric.common.world.feature.DurianTreeTrunkPlacer;
import net.firemuffin303.muffinsthaidelightfabric.common.world.feature.LimeFeature;
import net.firemuffin303.muffinsthaidelightfabric.common.world.feature.LimeTreeTrunkPlacer;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.placement.*;
import vectorwing.farmersdelight.common.registry.ModBiomeFeatures;
import vectorwing.farmersdelight.common.world.configuration.WildCropConfiguration;

import java.util.List;

public class ModFeatures {
    public static final TrunkPlacerType<LimeTreeTrunkPlacer> LIME_TRUNK_PLACER = Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, ThaiDelight.modid("lime_trunk_placer"),new TrunkPlacerType<>(LimeTreeTrunkPlacer.CODEC));
    public static final TrunkPlacerType<DurianTreeTrunkPlacer> DURIAN_TRUNK_PLACER = Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE,ThaiDelight.modid("durian_trunk_placer"),new TrunkPlacerType<>(DurianTreeTrunkPlacer.CODEC));
    public static final FoliagePlacerType<DurianTreeFoliagePlacer> DURIAN_FOLIAGE_PLACER = Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE,ThaiDelight.modid("durian_foliage_placer"),new FoliagePlacerType<>(DurianTreeFoliagePlacer.CODEC));


    public static final Feature<NoneFeatureConfiguration> LIME_FEATURE = Registry.register(BuiltInRegistries.FEATURE,new ResourceLocation(ThaiDelight.MOD_ID,"lime_feature"),new LimeFeature(NoneFeatureConfiguration.CODEC));

    public static final ResourceKey<ConfiguredFeature<?,?>> FEATURE_PATCH_LIME_BUSH;
    public static final ResourceKey<ConfiguredFeature<?,?>> FEATURE_PATCH_WILD_PEPPER;
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_PAPAYA_TREE;
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_DURIAN_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelight.modid("durian_tree"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATURE_LIME_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,ThaiDelight.modid("lime_tree"));
    public static final ResourceKey<PlacedFeature> PATCH_LIME_BUSH;
    public static final ResourceKey<PlacedFeature> PATCH_WILD_PEPPER;
    public static final ResourceKey<PlacedFeature> PAPAYA_TREE_CHECKED;

    public static void init(){}

    public static void bootstrapConfiguredFeature(BootstapContext<ConfiguredFeature<?, ?>> bootstapContext){
        bootstapContext.register(ModFeatures.FEATURE_PATCH_LIME_BUSH,
                new ConfiguredFeature<>(Feature.RANDOM_PATCH,
                        new RandomPatchConfiguration(12,5,3,
                                PlacementUtils.filtered(ModFeatures.LIME_FEATURE,
                                        new NoneFeatureConfiguration(),
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


        bootstapContext.register(ModFeatures.FEATURE_DURIAN_TREE,new ConfiguredFeature<>(Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.DURIAN_LOG),
                        new DurianTreeTrunkPlacer(5,2,0, UniformInt.of(-2,-1), UniformInt.of(2,3),UniformInt.of(1,3)),
                        BlockStateProvider.simple(Blocks.AZALEA_LEAVES),
                        new DurianTreeFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0),0.4f,0.12f),
                        new TwoLayersFeatureSize(1,0,1)
                ).ignoreVines()
                        .decorators(
                                List.of(
                                        new AttachedToLeavesDecorator(0.24f,1,0,new RandomizedIntStateProvider(
                                                BlockStateProvider.simple(ModBlocks.DURIAN_BLOCK.defaultBlockState().setValue(DurianBlock.HANGING,true)),
                                                DurianBlock.AGE,UniformInt.of(0,3)
                                        ),2,List.of(Direction.DOWN))
                                )
                        ).build()
        ));

        bootstapContext.register(ModFeatures.FEATURE_LIME_TREE,new ConfiguredFeature<>(Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new StraightTrunkPlacer(4,2,0),
                        BlockStateProvider.simple(ModBlocks.LIME_LEAVES),
                        new BlobFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0),3),
                        new TwoLayersFeatureSize(1,0,1)
                ).ignoreVines().build()
                ));

    }

    public static void bootstrapPlacedFeature(BootstapContext<PlacedFeature> bootstapContext){
        var config_lime_bush = bootstapContext.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModFeatures.FEATURE_PATCH_LIME_BUSH);
        var config_wild_pepper = bootstapContext.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModFeatures.FEATURE_PATCH_WILD_PEPPER);
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


    }

    public static void dataGen(HolderLookup.Provider provider, FabricDynamicRegistryProvider.Entries entries){
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_PATCH_LIME_BUSH);
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_PATCH_WILD_PEPPER);
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_DURIAN_TREE);
        entries.add(provider.lookupOrThrow(Registries.CONFIGURED_FEATURE),ModFeatures.FEATURE_LIME_TREE);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE),ModFeatures.PATCH_LIME_BUSH);
        entries.add(provider.lookupOrThrow(Registries.PLACED_FEATURE),ModFeatures.PATCH_WILD_PEPPER);

    }

    static {
        FEATURE_PATCH_LIME_BUSH = ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(ThaiDelight.MOD_ID,"patch_lime_bush"));
        FEATURE_PATCH_WILD_PEPPER = ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(ThaiDelight.MOD_ID,"patch_wild_pepper"));
        FEATURE_PAPAYA_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(ThaiDelight.MOD_ID,"papaya_tree"));

        PATCH_LIME_BUSH = ResourceKey.create(Registries.PLACED_FEATURE,new ResourceLocation(ThaiDelight.MOD_ID,"patch_lime_bush"));
        PATCH_WILD_PEPPER = ResourceKey.create(Registries.PLACED_FEATURE,new ResourceLocation(ThaiDelight.MOD_ID,"patch_wild_pepper"));
        PAPAYA_TREE_CHECKED = ResourceKey.create(Registries.PLACED_FEATURE,new ResourceLocation(ThaiDelight.MOD_ID,"papaya_tree_checked"));


    }
}
