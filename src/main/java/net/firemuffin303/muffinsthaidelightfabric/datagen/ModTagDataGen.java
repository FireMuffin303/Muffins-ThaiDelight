package net.firemuffin303.muffinsthaidelightfabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModEntityTypes;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModTagDataGen {
    public static class ModItemTagDataGen extends FabricTagProvider.ItemTagProvider {
        TagKey<Item> INSECT_ITEMS = TagKey.create(Registries.ITEM, new ResourceLocation("alexsmobs", "insect_items"));
        TagKey<Item> FORGE_RAW_FISHES = TagKey.create(Registries.ITEM, new ResourceLocation("forge", "raw_fishes"));
        TagKey<Item> C_RAW_FISHES = TagKey.create(Registries.ITEM, new ResourceLocation("c", "raw_fishes"));

        public ModItemTagDataGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
            super(output, completableFuture, null);
        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {
            // getOrCreateTagBuilder(ItemTags.PIGLIN_LOVED).add(ModItems.GOLDEN_PASTLE);
            getOrCreateTagBuilder(TagKey.create(Registries.ITEM, new ResourceLocation("origins", "meat")))
                    .add(ModItems.CRAB_MEAT)
                    .add(ModItems.COOKED_CRAB_MEAT)
                    .add(ModItems.DRAGONFLY)
                    .add(ModItems.COOKED_DRAGONFLY)
                    .add(ModItems.LARB);

            getOrCreateTagBuilder(TagKey.create(Registries.ITEM, new ResourceLocation("create", "upright_on_belt")))
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
                    .add(ModItems.DRAGONFLY)
                    .add(ModItems.COOKED_DRAGONFLY);

            getOrCreateTagBuilder(TagKey.create(Registries.ITEM, new ResourceLocation("crabbersdelight", "cooked_seafood")))
                    .add(ModItems.COOKED_CRAB_MEAT);

            getOrCreateTagBuilder(TagKey.create(Registries.ITEM, new ResourceLocation("crabbersdelight", "raw_seafood")))
                    .add(ModItems.CRAB_MEAT);

            getOrCreateTagBuilder(TagKey.create(Registries.ITEM, new ResourceLocation("forge", "seeds")))
                    .add(ModItems.PEPPER_SEED)
                    .add(ModItems.PAPAYA_SEEDS);

            getOrCreateTagBuilder(ModTags.SPICY_LEVEL_5)
                    .add(ModItems.PEPPER);

            getOrCreateTagBuilder(ModTags.SPICY_LEVEL_50)
                    .add(ModItems.LARB)
                    .add(ModItems.SOMTAM);

            getOrCreateTagBuilder(TagKey.create(Registries.ITEM, new ResourceLocation("c","foods/cooked_meats")))
                    .add(ModItems.COOKED_CRAB_MEAT)
                    .add(ModItems.COOKED_DRAGONFLY);

        }
    }

    public static class ModBlockTagDataGen extends FabricTagProvider.BlockTagProvider{

        public ModBlockTagDataGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
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

    public static class ModEntityTypesTagDataGen extends FabricTagProvider.EntityTypeTagProvider{

        public ModEntityTypesTagDataGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            getOrCreateTagBuilder(EntityTypeTags.FROG_FOOD)
                    .add(ModEntityTypes.DRAGONFLY);
        }
    }
}
