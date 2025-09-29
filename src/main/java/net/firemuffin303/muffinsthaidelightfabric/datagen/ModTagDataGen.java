package net.firemuffin303.muffinsthaidelightfabric.datagen;

import com.terraformersmc.modmenu.util.mod.Mod;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModEntityTypes;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ModTagDataGen {
    public static class ModItemTagDataGen extends FabricTagProvider.ItemTagProvider {
        TagKey<Item> INSECT_ITEMS = TagKey.create(Registries.ITEM, new ResourceLocation("alexsmobs", "insect_items"));
        TagKey<Item> FORGE_RAW_FISHES = TagKey.create(Registries.ITEM, new ResourceLocation("forge", "raw_fishes"));
        TagKey<Item> C_RAW_FISHES = TagKey.create(Registries.ITEM, new ResourceLocation("c", "raw_fishes"));

        public static final TagKey<Item> DURIAN_LOGS = TagKey.create(Registries.ITEM,ThaiDelight.modid("durian_logs"));
        public static final TagKey<Item> MANGO_LOGS = TagKey.create(Registries.ITEM,ThaiDelight.modid("mango_logs"));
        public static final TagKey<Item> COCONUT_LOGS = TagKey.create(Registries.ITEM,ThaiDelight.modid("coconut_logs"));

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
                    .add(
                            ModItems.FISH_SAUCE_BOTTLE,
                            ModItems.LIME_JUICE,
                            ModItems.PAPAYA_JUICE,
                            ModItems.COCONUT_WATER
                    );

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

            getOrCreateTagBuilder(ItemTags.PLANKS).add(ModItems.DURIAN_PLANKS,ModItems.MANGO_PLANKS,ModItems.COCONUT_PLANKS);

            getOrCreateTagBuilder(DURIAN_LOGS).add(ModItems.DURIAN_LOG,ModItems.DURIAN_WOOD,ModItems.STRIPPED_DURIAN_LOG,ModItems.STRIPPED_DURIAN_WOOD);
            getOrCreateTagBuilder(MANGO_LOGS).add(ModItems.MANGO_LOG,ModItems.MANGO_WOOD,ModItems.STRIPPED_MANGO_LOG,ModItems.STRIPPED_MANGO_WOOD);
            getOrCreateTagBuilder(COCONUT_LOGS).add(ModItems.COCONUT_LOG,ModItems.COCONUT_WOOD,ModItems.STRIPPED_COCONUT_LOG,ModItems.STRIPPED_COCONUT_WOOD);

            getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS).add(ModItems.DURIAN_FLOWER,ModItems.BUTTERFLY_PEA);

            getOrCreateTagBuilder(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(
                    ModItems.HOLY_BASIL_SAPLING,
                    ModItems.BASIL_SAPLING,
                    ModItems.PEPPER_SEED
            );
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
                    .add(ModBlocks.LIME_CRATE)
                    .add(ModBlocks.DURIAN_BLOCK);

            getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                    .add(ModBlocks.MORTAR);

            getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_HOE)
                    .add(ModBlocks.DURIAN_BLOCK);

            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                    .add(ModBlocks.PAPAYA_LOG)
                    .add(ModBlocks.DURIAN_LOG);

            getOrCreateTagBuilder(BlockTags.LEAVES)
                    .add(
                            ModBlocks.PAPAYA_LEAVES,
                            ModBlocks.DURIAN_LEAVES,
                            ModBlocks.COCONUT_LEAF,
                            ModBlocks.COCONUT_LEAF_END
                    );

            getOrCreateTagBuilder(ModTags.PAPAYA_LOGS)
                    .add(ModBlocks.PAPAYA_LOG)
                    .add(ModBlocks.PAPAYA_WOOD)
                    .add(ModBlocks.STRIPPED_PAPAYA_LOG)
                    .add(ModBlocks.STRIPPED_PAPAYA_WOOD);


            getOrCreateTagBuilder(BlockTags.PLANKS).add(ModBlocks.DURIAN_PLANKS,ModBlocks.MANGO_PLANKS,ModBlocks.COCONUT_PLANKS);
            getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(ModBlocks.DURIAN_STAIRS,ModBlocks.MANGO_STAIRS,ModBlocks.COCONUT_STAIRS);
            getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(ModBlocks.DURIAN_SLAB,ModBlocks.MANGO_SLAB,ModBlocks.COCONUT_SLAB);
            getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(ModBlocks.DURIAN_FENCE,ModBlocks.MANGO_FENCE,ModBlocks.COCONUT_FENCE);
            getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(ModBlocks.DURIAN_FENCE_GATE,ModBlocks.MANGO_FENCE_GATE,ModBlocks.COCONUT_FENCE_GATE);
            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(ModBlocks.DURIAN_DOOR,ModBlocks.MANGO_DOOR,ModBlocks.COCONUT_DOOR);
            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(ModBlocks.DURIAN_TRAPDOOR,ModBlocks.MANGO_TRAPDOOR,ModBlocks.COCONUT_FENCE_GATE);
            getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.DURIAN_PRESSURE_PLATE,ModBlocks.MANGO_PRESSURE_PLATE,ModBlocks.COCONUT_PRESSURE_PLATE);
            getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(ModBlocks.DURIAN_BUTTON,ModBlocks.MANGO_BUTTON,ModBlocks.COCONUT_BUTTON);

            getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(ModBlocks.DURIAN_FLOWER,ModBlocks.BUTTERFLY_PEA_WALL);
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

    public static class ModBiomeTagDataGen extends FabricTagProvider<Biome> {
        public static final TagKey<Biome> MANGO_TREE_BIOMES = TagKey.create(Registries.BIOME, ThaiDelight.modid("mango_tree_biomes"));
        public ModBiomeTagDataGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, Registries.BIOME, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            getOrCreateTagBuilder(MANGO_TREE_BIOMES).add(Biomes.BIRCH_FOREST,Biomes.OLD_GROWTH_BIRCH_FOREST,Biomes.FLOWER_FOREST,Biomes.FOREST);
        }
    }
}
