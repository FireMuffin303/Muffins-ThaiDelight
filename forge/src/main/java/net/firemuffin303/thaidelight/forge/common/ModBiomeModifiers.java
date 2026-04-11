package net.firemuffin303.thaidelight.forge.common;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModFeatures;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> LIME_BUSH_BIOMES = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ThaiDelightCommon.modid("lime_bush_biome_modifier"));
    public static final ResourceKey<BiomeModifier> PEPPER_BIOME_MODIFIER = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS,ThaiDelightCommon.modid("pepper_biome_modifier"));
    public static final ResourceKey<BiomeModifier> PAPAYA_TREE_MODIFIER = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS,ThaiDelightCommon.modid("papaya_biome_modifier"));
    public static final ResourceKey<BiomeModifier> DURIAN_TREE_MODIFIER = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS,ThaiDelightCommon.modid("durian_biome_modifier"));
    public static final ResourceKey<BiomeModifier> MANGO_TREE_MODIFIER = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS,ThaiDelightCommon.modid("mango_biome_modifier"));
    public static final ResourceKey<BiomeModifier> COCONUT_TREE_MODIFIER = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS,ThaiDelightCommon.modid("coconut_biome_modifier"));

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        Holder.Reference<PlacedFeature> limeBush = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModFeatures.PATCH_LIME_BUSH);
        HolderSet<Biome> limeBiomeTag = context.lookup(Registries.BIOME).getOrThrow(ModTags.LIME_TREE_BIOMES);

        Holder.Reference<PlacedFeature> pepperPlaceFeature = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModFeatures.PATCH_WILD_PEPPER);
        HolderSet<Biome> pepperBiomeTag = context.lookup(Registries.BIOME).getOrThrow(ModTags.PEPPER_TREE_BIOMES);

        Holder.Reference<PlacedFeature> papayaPlaceFeature = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModFeatures.TREES_PAPAYA);
        HolderSet<Biome> papayaBiomeTag = context.lookup(Registries.BIOME).getOrThrow(ModTags.PAPAYA_TREE_BIOMES);

        Holder.Reference<PlacedFeature> durianPlaceFeature = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModFeatures.TREES_DURIAN);
        HolderSet<Biome> durianBiomeTag = context.lookup(Registries.BIOME).getOrThrow(ModTags.DURIAN_TREE_BIOMES);

        Holder.Reference<PlacedFeature> mangoPlaceFeature = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModFeatures.TREES_MANGO);
        HolderSet<Biome> mangoBiomeTag = context.lookup(Registries.BIOME).getOrThrow(ModTags.MANGO_TREE_BIOMES);

        Holder.Reference<PlacedFeature> coconutPlaceFeature = context.lookup(Registries.PLACED_FEATURE).getOrThrow(ModFeatures.TREES_COCONUT);
        HolderSet<Biome> coconutBiomeTag = context.lookup(Registries.BIOME).getOrThrow(ModTags.COCONUT_TREE_BIOMES);


        context.register(LIME_BUSH_BIOMES,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(limeBiomeTag,HolderSet.direct(limeBush), GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(PEPPER_BIOME_MODIFIER,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(pepperBiomeTag,HolderSet.direct(pepperPlaceFeature), GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(PAPAYA_TREE_MODIFIER,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(papayaBiomeTag,HolderSet.direct(papayaPlaceFeature), GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(DURIAN_TREE_MODIFIER,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(durianBiomeTag,HolderSet.direct(durianPlaceFeature), GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(MANGO_TREE_MODIFIER,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(mangoBiomeTag,HolderSet.direct(mangoPlaceFeature), GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(COCONUT_TREE_MODIFIER,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(coconutBiomeTag,HolderSet.direct(coconutPlaceFeature), GenerationStep.Decoration.VEGETAL_DECORATION));

    }
}
