package net.firemuffin303.thaidelight.fabric.datagen;

import com.nhoryzon.mc.farmersdelight.registry.ItemsRegistry;
import com.nhoryzon.mc.farmersdelight.registry.TagsRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.firemuffin303.thaidelight.fabric.common.registry.ModItemsFabric;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ItemTagDataGen extends FabricTagProvider.ItemTagProvider {

    TagKey<Item> INSECT_ITEMS = TagKey.create(Registries.ITEM,new ResourceLocation("alexsmobs","insect_items"));
    TagKey<Item> FORGE_RAW_FISHES = TagKey.create(Registries.ITEM,new ResourceLocation("forge","raw_fishes"));
    TagKey<Item> C_RAW_FISHES = TagKey.create(Registries.ITEM,new ResourceLocation("c","raw_fishes"));

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
                .add(ModItems.FISH_SAUCE_BOTTLE)
                .add(ModItems.LIME_JUICE)
                .add(ModItems.PAPAYA_JUICE);

        getOrCreateTagBuilder(ModTags.LIME)
                .add(ModItems.LIME)
                .add(ModItems.SLICED_LIME);

        getOrCreateTagBuilder(ModTags.RAW_PAPAYA)
                .add(ModItems.RAW_PAPAYA)
                .add(ModItems.RAW_PAPAYA_SLICE);

        getOrCreateTagBuilder(ModTags.RIPE_PAPAYA)
                .add(ModItems.PAPAYA)
                .add(ModItems.SLICED_PAPAYA);

        getOrCreateTagBuilder(ModTags.PAPAYA)
                .addTag(ModTags.RIPE_PAPAYA)
                .addTag(ModTags.RAW_PAPAYA);

        getOrCreateTagBuilder(ModTags.FLOWER_CRAB_MEAT)
                .add(ModItems.CRAB_MEAT)
                .add(ModItems.COOKED_CRAB_MEAT)
        ;

        getOrCreateTagBuilder(ModTags.FLOWER_CRAB_FOOD)
                .add(Items.COD)
                .add(Items.SALMON)
                .add(Items.TROPICAL_FISH)
                .add(ItemsRegistry.COD_SLICE.get())
                .add(ItemsRegistry.SALMON_SLICE.get())
                .forceAddTag(FORGE_RAW_FISHES)
                .forceAddTag(C_RAW_FISHES)
        ;

        getOrCreateTagBuilder(C_RAW_FISHES);
        getOrCreateTagBuilder(FORGE_RAW_FISHES);


        getOrCreateTagBuilder(ModTags.DRAGONFLY_FOOD)
                .add(Items.SPIDER_EYE)
                .forceAddTag(INSECT_ITEMS);

        getOrCreateTagBuilder(INSECT_ITEMS)
                .add(ModItems.DRAGONFLY)
                .add(ModItems.COOKED_DRAGONFLY);

        getOrCreateTagBuilder(TagKey.create(Registries.ITEM,new ResourceLocation("crabbersdelight","cooked_seafood")))
                .add(ModItems.COOKED_CRAB_MEAT);

        getOrCreateTagBuilder(TagKey.create(Registries.ITEM,new ResourceLocation("crabbersdelight","raw_seafood")))
                .add(ModItems.CRAB_MEAT);

        getOrCreateTagBuilder(TagKey.create(Registries.ITEM,new ResourceLocation("forge","seeds")))
                .add(ModItems.PEPPER_SEED)
                .add(ModItems.PAPAYA_SEEDS);


    }
}
