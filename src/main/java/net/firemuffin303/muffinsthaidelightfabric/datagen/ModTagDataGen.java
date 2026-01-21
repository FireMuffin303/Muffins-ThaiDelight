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
import net.minecraft.tags.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

import static net.firemuffin303.muffinsthaidelightfabric.registry.ModTags.*;

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
                    .add(
                            ModItems.FISH_SAUCE_BOTTLE,
                            ModItems.LIME_JUICE,
                            ModItems.HONEY_LIME_JUICE,
                            ModItems.PAPAYA_JUICE,
                            ModItems.COCONUT_WATER,
                            ModItems.BUTTERFLY_PEA_TEA,
                            ModItems.COCONUT_MILK_BOTTLE
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

            getOrCreateTagBuilder(DURIAN)
                    .add(ModItems.DURIAN,ModItems.SMALL_DURIAN);

            getOrCreateTagBuilder(MANGO)
                    .add(ModItems.MANGO,ModItems.MANGO_SLICE);

            getOrCreateTagBuilder(COCONUT)
                    .add(ModItems.COCONUT,ModItems.STRIPPED_COCONUT,ModItems.COCONUT_SLICE);


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

            getOrCreateTagBuilder(TagKey.create(Registries.ITEM, new ResourceLocation("c","foods/cooked_meats")))
                    .add(ModItems.COOKED_CRAB_MEAT)
                    .add(ModItems.COOKED_DRAGONFLY);

            getOrCreateTagBuilder(ItemTags.PLANKS).add(ModItems.DURIAN_PLANKS,ModItems.MANGO_PLANKS,ModItems.COCONUT_PLANKS);

            getOrCreateTagBuilder(DURIAN_LOGS_ITEM).add(ModItems.DURIAN_LOG,ModItems.DURIAN_WOOD,ModItems.STRIPPED_DURIAN_LOG,ModItems.STRIPPED_DURIAN_WOOD);
            getOrCreateTagBuilder(MANGO_LOGS_ITEM).add(ModItems.MANGO_LOG,ModItems.MANGO_WOOD,ModItems.STRIPPED_MANGO_LOG,ModItems.STRIPPED_MANGO_WOOD);
            getOrCreateTagBuilder(COCONUT_LOGS_ITEM).add(ModItems.COCONUT_LOG,ModItems.COCONUT_WOOD,ModItems.STRIPPED_COCONUT_LOG,ModItems.STRIPPED_COCONUT_WOOD);

            getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS).add(
                    ModItems.DURIAN_FLOWER,
                    ModItems.BUTTERFLY_PEA,
                    ModItems.PAPAYA_FLOWER
            );

            getOrCreateTagBuilder(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(
                    ModItems.HOLY_BASIL,
                    ModItems.BASIL,
                    ModItems.PEPPER_SEED
            );

            getOrCreateTagBuilder(PAPAYA_LOGS_ITEM).add(
                    ModItems.PAPAYA_LOG,
                    ModItems.PAPAYA_WOOD,
                    ModItems.STRIPPED_PAPAYA_LOG,
                    ModItems.STRIPPED_PAPAYA_WOOD
            );


            getOrCreateTagBuilder(ItemTags.SAPLINGS).add(
                    ModItems.LIME_SAPLING,
                    ModItems.PAPAYA_SAPLING,
                    ModItems.DURIAN_SAPLING,
                    ModItems.MANGO_SAPLING,
                    ModItems.COCONUT_SAPLING
            );

            getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                    .addTag(PAPAYA_LOGS_ITEM)
                    .addTag(COCONUT_LOGS_ITEM)
                    .addTag(MANGO_LOGS_ITEM)
                    .addTag(DURIAN_LOGS_ITEM);

            getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                    .add(ModItems.DURIAN_DOOR,ModItems.MANGO_DOOR,ModItems.COCONUT_DOOR);

            getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                    .add(ModItems.DURIAN_TRAPDOOR,ModItems.MANGO_TRAPDOOR,ModItems.COCONUT_TRAPDOOR);

            getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
                    .add(ModItems.DURIAN_BUTTON,ModItems.MANGO_BUTTON,ModItems.COCONUT_BUTTON);

            getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                    .add(ModItems.DURIAN_FENCE,ModItems.MANGO_FENCE,ModItems.COCONUT_FENCE);

            getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
                    .add(ModItems.DURIAN_SLAB,ModItems.MANGO_SLAB,ModItems.COCONUT_SLAB);

            getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                    .add(ModItems.DURIAN_PRESSURE_PLATE,ModItems.MANGO_PRESSURE_PLATE,ModItems.COCONUT_PRESSURE_PLATE);

            getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
                    .add(ModItems.DURIAN_STAIRS, ModItems.MANGO_STAIRS,ModItems.COCONUT_STAIRS);

            getOrCreateTagBuilder(ItemTags.BOATS)
                    .add(ModItems.DURIAN_BOAT,ModItems.MANGO_BOAT,ModItems.COCONUT_BOAT);

            getOrCreateTagBuilder(ItemTags.CHEST_BOATS)
                    .add(ModItems.DURIAN_CHEST_BOAT,ModItems.MANGO_CHEST_BOAT,ModItems.COCONUT_CHEST_BOAT);

            getOrCreateTagBuilder(ItemTags.SIGNS)
                    .add(ModItems.DURIAN_SIGN,ModItems.MANGO_SIGN,ModItems.COCONUT_SIGN);

            getOrCreateTagBuilder(ItemTags.HANGING_SIGNS)
                    .add(ModItems.DURIAN_HANGING_SIGN,ModItems.MANGO_HANGING_SIGN,ModItems.COCONUT_HANGING_SIGN);

            getOrCreateTagBuilder(vectorwing.farmersdelight.common.tag.ModTags.WOODEN_CABINETS)
                    .add(ModItems.DURIAN_CABINET,ModItems.MANGO_CABINET,ModItems.COCONUT_CABINET);

            getOrCreateTagBuilder(vectorwing.farmersdelight.common.tag.ModTags.WILD_CROPS_ITEM)
                    .add(ModItems.WILD_PEPPER_CROP,ModItems.WILD_BASIL,ModItems.WILD_HOLY_BASIL);
        }
    }

    public static class ModBlockTagDataGen extends FabricTagProvider.BlockTagProvider{



        public ModBlockTagDataGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            getOrCreateTagBuilder(BlockTags.PLANKS).add(ModBlocks.DURIAN_PLANKS,ModBlocks.MANGO_PLANKS,ModBlocks.COCONUT_PLANKS);
            getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(ModBlocks.DURIAN_STAIRS,ModBlocks.MANGO_STAIRS,ModBlocks.COCONUT_STAIRS);
            getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(ModBlocks.DURIAN_SLAB,ModBlocks.MANGO_SLAB,ModBlocks.COCONUT_SLAB);
            getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(ModBlocks.DURIAN_FENCE,ModBlocks.MANGO_FENCE,ModBlocks.COCONUT_FENCE);
            getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(ModBlocks.DURIAN_FENCE_GATE,ModBlocks.MANGO_FENCE_GATE,ModBlocks.COCONUT_FENCE_GATE);
            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(ModBlocks.DURIAN_DOOR,ModBlocks.MANGO_DOOR,ModBlocks.COCONUT_DOOR);
            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(ModBlocks.DURIAN_TRAPDOOR,ModBlocks.MANGO_TRAPDOOR,ModBlocks.COCONUT_TRAPDOOR);
            getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.DURIAN_PRESSURE_PLATE,ModBlocks.MANGO_PRESSURE_PLATE,ModBlocks.COCONUT_PRESSURE_PLATE);
            getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(ModBlocks.DURIAN_BUTTON,ModBlocks.MANGO_BUTTON,ModBlocks.COCONUT_BUTTON);

            getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(ModBlocks.DURIAN_SIGN,ModBlocks.MANGO_SIGN,ModBlocks.COCONUT_SIGN);
            getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(ModBlocks.DURIAN_WALL_SIGN,ModBlocks.MANGO_WALL_SIGN,ModBlocks.COCONUT_WALL_SIGN);
            getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(ModBlocks.DURIAN_HANGING_SIGN,ModBlocks.MANGO_HANGING_SIGN,ModBlocks.COCONUT_HANGING_SIGN);
            getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(ModBlocks.DURIAN_WALL_HANGING_SIGN,ModBlocks.MANGO_WALL_HANGING_SIGN,ModBlocks.COCONUT_WALL_HANGING_SIGN);

            getOrCreateTagBuilder(BlockTags.CROPS).add(
                    ModBlocks.BUDDING_PEPPER_CROP,
                    ModBlocks.PEPPER_CROP,
                    ModBlocks.LIME_PLANT,
                    ModBlocks.BUDDING_PAPAYA_FLOWER,
                    ModBlocks.PAPAYA,
                    ModBlocks.HANGING_DURIAN,
                    ModBlocks.HANGING_MANGO_BLOCK,
                    ModBlocks.BUDDING_COCONUT_LEAF,
                    ModBlocks.HOLY_BASIL,
                    ModBlocks.BASIL,
                    ModBlocks.BUDDING_BUTTERFLY_PEA_BLOCK,
                    ModBlocks.BUTTERFLY_PEA_BLOCK
            );

            getOrCreateTagBuilder(BlockTags.CAULDRONS)
                    .add(ModBlocks.FERMENTED_FISH_CAULDRON);

            getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(
                    ModBlocks.PEPPER_CRATE,
                    ModBlocks.PAPAYA_CRATE,
                    ModBlocks.LIME_CRATE,
                    ModBlocks.RAW_PAPAYA_CRATE,
                    ModBlocks.MANGO_CRATE,
                    ModBlocks.HOLY_BASIL_CRATE,
                    ModBlocks.BASIL_CRATE,
                    ModBlocks.BAMBOO_SHOOT_CRATE,
                    ModBlocks.BUTTERFLY_PEA_CRATE,
                    ModBlocks.SMALL_DURIAN_BLOCK,
                    ModBlocks.DURIAN_BLOCK,
                    ModBlocks.DURIAN_PEEL_BLOCK,
                    ModBlocks.COCONUT,
                    ModBlocks.STRIPPED_COCONUT
            );

            getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                    .add(ModBlocks.MORTAR);

            getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_HOE).add(
                    ModBlocks.DURIAN_BLOCK,
                    ModBlocks.COCONUT_LEAF_BLOCK
            );

            getOrCreateTagBuilder(DURIAN_LOGS_BLOCK).add(
                    ModBlocks.DURIAN_LOG,
                    ModBlocks.DURIAN_WOOD,
                    ModBlocks.STRIPPED_DURIAN_LOG,
                    ModBlocks.STRIPPED_DURIAN_WOOD
            );
            getOrCreateTagBuilder(MANGO_LOGS_BLOCK).add(
                    ModBlocks.MANGO_LOG,
                    ModBlocks.MANGO_WOOD,
                    ModBlocks.STRIPPED_MANGO_LOG,
                    ModBlocks.STRIPPED_MANGO_WOOD
            );
            getOrCreateTagBuilder(COCONUT_LOGS_BLOCK).add(
                    ModBlocks.COCONUT_LOG,
                    ModBlocks.COCONUT_WOOD,
                    ModBlocks.STRIPPED_COCONUT_LOG,
                    ModBlocks.STRIPPED_COCONUT_WOOD
            );
            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                    .addTag(PAPAYA_LOGS)
                    .addTag(DURIAN_LOGS_BLOCK)
                    .addTag(MANGO_LOGS_BLOCK)
                    .addTag(COCONUT_LOGS_BLOCK);


            getOrCreateTagBuilder(BlockTags.LEAVES)
                    .add(
                            ModBlocks.PAPAYA_LEAVES,
                            ModBlocks.DURIAN_LEAVES,
                            ModBlocks.MANGO_LEAVES,
                            ModBlocks.COCONUT_LEAF,
                            ModBlocks.COCONUT_LEAF_END
                    );

            getOrCreateTagBuilder(ModTags.PAPAYA_LOGS)
                    .add(ModBlocks.PAPAYA_LOG)
                    .add(ModBlocks.PAPAYA_WOOD)
                    .add(ModBlocks.STRIPPED_PAPAYA_LOG)
                    .add(ModBlocks.STRIPPED_PAPAYA_WOOD);

            getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(
                    ModBlocks.DURIAN_FLOWER,
                    ModBlocks.BUTTERFLY_PEA_WALL,
                    ModBlocks.BUDDING_PAPAYA_FLOWER,
                    ModBlocks.PAPAYA_FLOWER,
                    ModBlocks.WALL_PAPAYA_FLOWER
            );

            getOrCreateTagBuilder(FLOWER_CRAB_SPAWNABLE_ON).add(
                    Blocks.GRASS_BLOCK,
                    Blocks.MUD,
                    Blocks.MANGROVE_ROOTS,
                    Blocks.MUDDY_MANGROVE_ROOTS,
                    Blocks.CLAY,
                    Blocks.SAND,
                    Blocks.SUSPICIOUS_SAND
            );

            getOrCreateTagBuilder(SACK_CATCHABLE).add(
                    ModBlocks.SMALL_DURIAN_BLOCK,
                    ModBlocks.HANGING_MANGO_BLOCK).addTag(SACK_HEAVY_CATCHABLE);

            getOrCreateTagBuilder(SACK_HEAVY_CATCHABLE).add(
                    ModBlocks.DURIAN_BLOCK,
                    ModBlocks.HANGING_DURIAN,
                    ModBlocks.COCONUT
            );

            getOrCreateTagBuilder(BlockTags.CANDLE_CAKES).add(
                    ModBlocks.CANDLE_DURIAN_CAKE,
                    ModBlocks.WHITE_CANDLE_DURIAN_CAKE,
                    ModBlocks.LIGHT_GRAY_CANDLE_DURIAN_CAKE,
                    ModBlocks.GRAY_CANDLE_DURIAN_CAKE,
                    ModBlocks.BLACK_CANDLE_DURIAN_CAKE,
                    ModBlocks.BROWN_CANDLE_DURIAN_CAKE,
                    ModBlocks.RED_CANDLE_DURIAN_CAKE,
                    ModBlocks.ORANGE_CANDLE_DURIAN_CAKE,
                    ModBlocks.YELLOW_CANDLE_DURIAN_CAKE,
                    ModBlocks.LIME_CANDLE_DURIAN_CAKE,
                    ModBlocks.GREEN_CANDLE_DURIAN_CAKE,
                    ModBlocks.CYAN_CANDLE_DURIAN_CAKE,
                    ModBlocks.LIGHT_BLUE_CANDLE_DURIAN_CAKE,
                    ModBlocks.BLUE_CANDLE_DURIAN_CAKE,
                    ModBlocks.MAGENTA_CANDLE_DURIAN_CAKE,
                    ModBlocks.PURPLE_CANDLE_DURIAN_CAKE,
                    ModBlocks.PINK_CANDLE_DURIAN_CAKE
            );

            getOrCreateTagBuilder(vectorwing.farmersdelight.common.tag.ModTags.WILD_CROPS).add(
                    ModBlocks.WILD_BASIL,
                    ModBlocks.WILD_HOLY_BASIL,
                    ModBlocks.WILD_PEPPER_CROP
            );

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

        public ModBiomeTagDataGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, Registries.BIOME, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            getOrCreateTagBuilder(LIME_TREE_BIOMES).add(Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.FLOWER_FOREST);
            getOrCreateTagBuilder(PAPAYA_TREE_BIOMES).add(Biomes.SAVANNA,Biomes.SAVANNA_PLATEAU,Biomes.WINDSWEPT_SAVANNA);
            getOrCreateTagBuilder(PEPPER_TREE_BIOMES).add(Biomes.SAVANNA,Biomes.SAVANNA_PLATEAU,Biomes.WINDSWEPT_SAVANNA);
            getOrCreateTagBuilder(DURIAN_TREE_BIOMES).add(Biomes.JUNGLE);
            getOrCreateTagBuilder(MANGO_TREE_BIOMES).add(Biomes.FLOWER_FOREST,Biomes.FOREST);
            getOrCreateTagBuilder(COCONUT_TREE_BIOMES).add(Biomes.BEACH);

            getOrCreateTagBuilder(WILD_HOLY_BASIL_BIOMES).forceAddTag(BiomeTags.IS_FOREST);
            getOrCreateTagBuilder(WILD_BASIL_BIOMES).forceAddTag(BiomeTags.IS_FOREST);
            getOrCreateTagBuilder(WILD_ALL_BASIL_BIOMES).forceAddTag(BiomeTags.IS_JUNGLE);
            getOrCreateTagBuilder(BUTTERFLY_PEA_BIOMES).forceAddTag(BiomeTags.IS_FOREST);

        }
    }
}
