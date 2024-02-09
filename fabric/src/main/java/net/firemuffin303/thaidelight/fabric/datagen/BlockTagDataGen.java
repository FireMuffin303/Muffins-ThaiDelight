package net.firemuffin303.thaidelight.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagDataGen extends FabricTagProvider.BlockTagProvider {
    public BlockTagDataGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.PEPPER_CRATE)
                .add(ModBlocks.PAPAYA_CRATE)
                .add(ModBlocks.LIME_CRATE);

        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.MORTAR);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.PAPAYA_LOG);

        getOrCreateTagBuilder(ModTags.PAPAYA_LOGS)
                .add(ModBlocks.PAPAYA_LOG)
                .add(ModBlocks.PAPAYA_WOOD)
                .add(ModBlocks.STRIPPED_PAPAYA_LOG)
                .add(ModBlocks.STRIPPED_PAPAYA_WOOD);
    }
}
