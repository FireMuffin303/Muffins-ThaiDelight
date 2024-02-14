package net.firemuffin303.thaidelight.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.fabric.common.registry.ModItemsFabric;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;

import java.util.concurrent.CompletableFuture;

public class ItemTagDataGen extends FabricTagProvider.ItemTagProvider {
    public ItemTagDataGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture, null);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
       // getOrCreateTagBuilder(ItemTags.PIGLIN_LOVED).add(ModItems.GOLDEN_PASTLE);
        getOrCreateTagBuilder(TagKey.create(Registries.ITEM, new ResourceLocation("origins","meat")))
                .add(ModItems.CRAB_MEAT)
                .add(ModItems.COOKED_CRAB_MEAT)
                .add(ModItems.DRAGONFLY)
                .add(ModItems.COOKED_DRAGONFLY)
                .add(ModItemsFabric.SPICY_MINCED_MEAT_SALAD);

        getOrCreateTagBuilder(TagKey.create(Registries.ITEM, new ResourceLocation("create","upright_on_belt")))
                .add(ModItems.SEAFOOD_BOTTLE)
                .add(ModItems.FISH_SAUCE_BOTTLE)
                .add(ModItems.LIME_JUICE)
                .add(ModItems.PAPAYA_JUICE);

       // getOrCreateTagBuilder(ItemTags.MUSIC_DISCS).add(ModItems.ESAN_MUSIC_DISC);


    }
}
