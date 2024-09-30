package net.firemuffin303.thaidelight.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.firemuffin303.thaidelight.fabric.common.registry.ModItemsFabric;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ItemTagDataGen extends FabricTagProvider.ItemTagProvider {

    TagKey<Item> INSECT_ITEMS = TagKey.create(Registries.ITEM,ResourceLocation.fromNamespaceAndPath("alexsmobs","insect_items"));
    TagKey<Item> FORGE_RAW_FISHES = TagKey.create(Registries.ITEM,ResourceLocation.fromNamespaceAndPath("forge","raw_fishes"));
    TagKey<Item> C_RAW_FISHES = TagKey.create(Registries.ITEM,ResourceLocation.fromNamespaceAndPath("c","raw_fishes"));

    public ItemTagDataGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture, null);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
       // getOrCreateTagBuilder(ItemTags.PIGLIN_LOVED).add(ModItems.GOLDEN_PASTLE);
        getOrCreateTagBuilder(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("origins","meat")))
                .add(ModItems.CRAB_MEAT.get())
                .add(ModItems.COOKED_CRAB_MEAT.get())
                .add(ModItems.DRAGONFLY.get())
                .add(ModItems.COOKED_DRAGONFLY.get())
                .add(ModItemsFabric.SPICY_MINCED_MEAT_SALAD.asItem());

        getOrCreateTagBuilder(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("create","upright_on_belt")))
                .add(ModItems.FISH_SAUCE_BOTTLE.get())
                .add(ModItems.LIME_JUICE.get())
                .add(ModItems.PAPAYA_JUICE.get());

        getOrCreateTagBuilder(ModTags.LIME)
                .add(ModItems.LIME.get())
                .add(ModItems.SLICED_LIME.get());

        getOrCreateTagBuilder(ModTags.RAW_PAPAYA)
                .add(ModItems.RAW_PAPAYA.get())
                .add(ModItems.RAW_PAPAYA_SLICE.get());

        getOrCreateTagBuilder(ModTags.RIPE_PAPAYA)
                .add(ModItems.PAPAYA.get())
                .add(ModItems.SLICED_PAPAYA.get());

        getOrCreateTagBuilder(ModTags.PAPAYA)
                .addTag(ModTags.RIPE_PAPAYA)
                .addTag(ModTags.RAW_PAPAYA);

        getOrCreateTagBuilder(ModTags.FLOWER_CRAB_MEAT)
                .add(ModItems.CRAB_MEAT.get())
                .add(ModItems.COOKED_CRAB_MEAT.get())
        ;

        getOrCreateTagBuilder(ModTags.FLOWER_CRAB_FOOD)
                .add(Items.COD)
                .add(Items.SALMON)
                .add(Items.TROPICAL_FISH)
                .add(vectorwing.farmersdelight.common.registry.ModItems.COD_SLICE.get())
                .add(vectorwing.farmersdelight.common.registry.ModItems.SALMON_SLICE.get())
                .forceAddTag(FORGE_RAW_FISHES)
                .forceAddTag(C_RAW_FISHES)
        ;

        getOrCreateTagBuilder(C_RAW_FISHES);
        getOrCreateTagBuilder(FORGE_RAW_FISHES);


        getOrCreateTagBuilder(ModTags.DRAGONFLY_FOOD)
                .add(Items.SPIDER_EYE)
                .forceAddTag(INSECT_ITEMS);

        getOrCreateTagBuilder(INSECT_ITEMS)
                .add(ModItems.DRAGONFLY.get())
                .add(ModItems.COOKED_DRAGONFLY.get());

        getOrCreateTagBuilder(TagKey.create(Registries.ITEM,ResourceLocation.fromNamespaceAndPath("crabbersdelight","cooked_seafood")))
                .add(ModItems.COOKED_CRAB_MEAT.get());

        getOrCreateTagBuilder(TagKey.create(Registries.ITEM,ResourceLocation.fromNamespaceAndPath("crabbersdelight","raw_seafood")))
                .add(ModItems.CRAB_MEAT.get());

        getOrCreateTagBuilder(TagKey.create(Registries.ITEM,ResourceLocation.fromNamespaceAndPath("forge","seeds")))
                .add(ModItems.PEPPER_SEED.get())
                .add(ModItems.PAPAYA_SEEDS.get());


    }
}
