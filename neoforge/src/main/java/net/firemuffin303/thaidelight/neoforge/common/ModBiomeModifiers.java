package net.firemuffin303.thaidelight.neoforge.common;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModFeatures;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> LIME_BUSH_BIOMES = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ThaiDelightCommon.modid("lime_bush_biome_modifier"));
    public static final ResourceKey<BiomeModifier> PEPPER_BIOME_MODIFIER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,ThaiDelightCommon.modid("pepper_biome_modifier"));
    public static final ResourceKey<BiomeModifier> PAPAYA_TREE_MODIFIER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,ThaiDelightCommon.modid("papaya_biome_modifier"));
    public static final ResourceKey<BiomeModifier> DURIAN_TREE_MODIFIER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,ThaiDelightCommon.modid("durian_biome_modifier"));
    public static final ResourceKey<BiomeModifier> SPARSE_DURIAN_TREE_MODIFIER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,ThaiDelightCommon.modid("sparse_durian_biome_modifier"));
    public static final ResourceKey<BiomeModifier> MANGO_TREE_MODIFIER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,ThaiDelightCommon.modid("mango_biome_modifier"));
    public static final ResourceKey<BiomeModifier> COCONUT_TREE_MODIFIER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,ThaiDelightCommon.modid("coconut_biome_modifier"));
    public static final ResourceKey<BiomeModifier> BUTTERFLY_PEA_MODIFIER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,ThaiDelightCommon.modid("butterfly_pea_biome_modifier"));
    public static final ResourceKey<BiomeModifier> BASIL_MODIFIER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,ThaiDelightCommon.modid("basil_biome_modifier"));
    public static final ResourceKey<BiomeModifier> HOLY_BASIL_MODIFIER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,ThaiDelightCommon.modid("holy_basil_biome_modifier"));
    public static final ResourceKey<BiomeModifier> ALL_BASIL_MODIFIER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,ThaiDelightCommon.modid("all_basil_biome_modifier"));

    public static final ResourceKey<BiomeModifier> FLOWER_CRAB_BIOME_MODIFIER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,ThaiDelightCommon.modid("flower_crab_biome_modifier"));
    public static final ResourceKey<BiomeModifier> DRAGONFLY_BIOME_MODIFIER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,ThaiDelightCommon.modid("dragonfly_biome_modifier"));

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        Holder.Reference<PlacedFeature> limeBush = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModFeatures.PATCH_LIME_BUSH);
        HolderSet<Biome> limeBiomeTag = context.lookup(Registries.BIOME).getOrThrow(ModTags.LIME_TREE_BIOMES);

        Holder.Reference<PlacedFeature> pepperPlaceFeature = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModFeatures.PATCH_WILD_PEPPER);
        HolderSet<Biome> pepperBiomeTag = context.lookup(Registries.BIOME).getOrThrow(ModTags.PEPPER_TREE_BIOMES);

        Holder.Reference<PlacedFeature> papayaPlaceFeature = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModFeatures.TREES_PAPAYA);
        HolderSet<Biome> papayaBiomeTag = context.lookup(Registries.BIOME).getOrThrow(ModTags.PAPAYA_TREE_BIOMES);

        Holder.Reference<PlacedFeature> durianPlaceFeature = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModFeatures.TREES_DURIAN);
        Holder.Reference<PlacedFeature> sparseDurianPlaceFeature = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModFeatures.TREES_DURIAN_SPARSE_JUNGLE);
        HolderSet<Biome> durianBiomeTag = context.lookup(Registries.BIOME).getOrThrow(ModTags.DURIAN_TREE_BIOMES);
        HolderSet<Biome> sparseDurianBiome = HolderSet.direct(context.lookup(Registries.BIOME).getOrThrow(Biomes.SPARSE_JUNGLE));

        Holder.Reference<PlacedFeature> mangoPlaceFeature = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModFeatures.TREES_MANGO);
        HolderSet<Biome> mangoBiomeTag = context.lookup(Registries.BIOME).getOrThrow(ModTags.MANGO_TREE_BIOMES);

        Holder.Reference<PlacedFeature> coconutPlaceFeature = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModFeatures.TREES_COCONUT);
        HolderSet<Biome> coconutBiomeTag = context.lookup(Registries.BIOME).getOrThrow(ModTags.COCONUT_TREE_BIOMES);

        Holder.Reference<PlacedFeature> butterflyPeaPlaceFeature = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModFeatures.PATCH_BUTTERFLY_PEA);
        HolderSet<Biome> butterflyPeaBiomeTag = context.lookup(Registries.BIOME).getOrThrow(ModTags.BUTTERFLY_PEA_BIOMES);

        Holder.Reference<PlacedFeature> basilPlaceFeature = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModFeatures.PATCH_WILD_BASIL);
        HolderSet<Biome> basilPeaBiomeTag = context.lookup(Registries.BIOME).getOrThrow(ModTags.WILD_BASIL_BIOMES);

        Holder.Reference<Biome> flowerCrabBiomeTag = context.lookup(Registries.BIOME).getOrThrow(Biomes.BEACH);
        HolderSet<Biome> dragonflyBiomeTag = HolderSet.direct(context.lookup(Registries.BIOME).getOrThrow(Biomes.MANGROVE_SWAMP),context.lookup(Registries.BIOME).getOrThrow(Biomes.SWAMP));


        /*
        context.register(LIME_BUSH_BIOMES,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(limeBiomeTag,HolderSet.direct(limeBush), GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(PEPPER_BIOME_MODIFIER,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(pepperBiomeTag,HolderSet.direct(pepperPlaceFeature), GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(PAPAYA_TREE_MODIFIER,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(papayaBiomeTag,HolderSet.direct(papayaPlaceFeature), GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(DURIAN_TREE_MODIFIER,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(durianBiomeTag,HolderSet.direct(durianPlaceFeature), GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(SPARSE_DURIAN_TREE_MODIFIER,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(sparseDurianBiome,HolderSet.direct(sparseDurianPlaceFeature), GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(MANGO_TREE_MODIFIER,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(mangoBiomeTag,HolderSet.direct(mangoPlaceFeature), GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(COCONUT_TREE_MODIFIER,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(coconutBiomeTag,HolderSet.direct(coconutPlaceFeature), GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(BUTTERFLY_PEA_MODIFIER,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(butterflyPeaBiomeTag,HolderSet.direct(butterflyPeaPlaceFeature), GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(BASIL_MODIFIER,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(basilPeaBiomeTag,HolderSet.direct(basilPlaceFeature), GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(FLOWER_CRAB_BIOME_MODIFIER,new ForgeBiomeModifiers.AddSpawnsBiomeModifier(HolderSet.direct(flowerCrabBiomeTag), List.of(
                new MobSpawnSettings.SpawnerData(ModEntityTypes.FLOWER_CRAB.get(),10,3,5)
        )));

        context.register(DRAGONFLY_BIOME_MODIFIER,new ForgeBiomeModifiers.AddSpawnsBiomeModifier(dragonflyBiomeTag, List.of(
                new MobSpawnSettings.SpawnerData(ModEntityTypes.DRAGONFLY.get(),2,1,3)
        )));


         */
    }
}
