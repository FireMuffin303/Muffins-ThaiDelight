package net.firemuffin303.thaidelight.datagen.provider.tag;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.firemuffin303.thaidelight.common.registry.ModTags.*;

public class ThaiDelightBiomeTagProvider extends BiomeTagsProvider {
    public ThaiDelightBiomeTagProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, completableFuture, ThaiDelightCommon.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        this.tag(LIME_TREE_BIOMES).add(Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.FLOWER_FOREST);
        this.tag(PAPAYA_TREE_BIOMES).add(Biomes.SAVANNA,Biomes.SAVANNA_PLATEAU,Biomes.WINDSWEPT_SAVANNA);
        this.tag(PEPPER_TREE_BIOMES).add(Biomes.SAVANNA,Biomes.SAVANNA_PLATEAU,Biomes.WINDSWEPT_SAVANNA);
        this.tag(DURIAN_TREE_BIOMES).add(Biomes.JUNGLE);
        this.tag(MANGO_TREE_BIOMES).add(Biomes.FLOWER_FOREST,Biomes.FOREST);
        this.tag(COCONUT_TREE_BIOMES).add(Biomes.BEACH);

        this.tag(WILD_BASIL_BIOMES).addTag(BiomeTags.IS_FOREST).addTag(BiomeTags.IS_JUNGLE);
        this.tag(BUTTERFLY_PEA_BIOMES).addTag(BiomeTags.IS_FOREST);
    }
}
