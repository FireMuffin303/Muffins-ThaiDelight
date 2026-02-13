package net.firemuffin303.muffinsthaidelightfabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
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
            getOrCreateTagBuilder(SPICY_FOODS).add(
                    ModItems.PEPPER,
                    ModItems.SOMTAM,
                    ModItems.LARB,
                    ModItems.PHAT_KAPHRAO,
                    ModItems.BASIL_OMELETTE,
                    ModItems.DURIAN_CURRY,
                    ModItems.BAMBOO_SHOOT_SOUP,
                    ModItems.STEAMED_BAMBOO_SHOOT
            );

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

            getOrCreateTagBuilder(PEPPER).add(
                    ModItems.PEPPER
            );

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
                    .add(ModItems.DURIAN,ModItems.SMALL_DURIAN,ModItems.DURIAN_PULP);

            getOrCreateTagBuilder(DURIAN_FOOD)
                    .add(ModItems.DURIAN_PULP,ModItems.FRIED_DURIAN,ModItems.DURIAN_CURRY,ModItems.DURIAN_CAKE_SLICE);


            getOrCreateTagBuilder(FERMENTED_DRINKS)
                    .addOptional(new ResourceLocation("brewinandchewin","beer"))
                    .addOptional(new ResourceLocation("brewinandchewin","vodka"))
                    .addOptional(new ResourceLocation("brewinandchewin","mead"))
                    .addOptional(new ResourceLocation("brewinandchewin","rice_wine"))
                    .addOptional(new ResourceLocation("brewinandchewin","egg_grog"))
                    .addOptional(new ResourceLocation("brewinandchewin","strongroot_ale"))
                    .addOptional(new ResourceLocation("brewinandchewin","saccharine_rum"))
                    .addOptional(new ResourceLocation("brewinandchewin","pale_jane"))
                    .addOptional(new ResourceLocation("brewinandchewin","dread_nog"))
                    .addOptional(new ResourceLocation("brewinandchewin","salty_folly"))
                    .addOptional(new ResourceLocation("brewinandchewin","steel_toe_stout"))
                    .addOptional(new ResourceLocation("brewinandchewin","glittering_grenadine"))
                    .addOptional(new ResourceLocation("brewinandchewin","bloody_mary"))
                    .addOptional(new ResourceLocation("brewinandchewin","red_rum"))
                    .addOptional(new ResourceLocation("brewinandchewin","withering_dross"))
                    .addOptional(new ResourceLocation("brewinandchewin","kombucha"));



            getOrCreateTagBuilder(MANGO)
                    .add(ModItems.MANGO,ModItems.MANGO_SLICE)
                    .addOptional(new ResourceLocation("fishofthieves","mango"));

            getOrCreateTagBuilder(COCONUT)
                    .add(ModItems.COCONUT,ModItems.STRIPPED_COCONUT,ModItems.COCONUT_SLICE)
                    .addOptional(new ResourceLocation("fishofthieves","coconut"));


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

            getOrCreateTagBuilder(CROPS).add(
                    ModItems.PEPPER,
                    ModItems.LIME,
                    ModItems.HOLY_BASIL,
                    ModItems.BASIL
            );

            getOrCreateTagBuilder(VEGETABLES).add(
                    ModItems.PEPPER,
                    ModItems.HOLY_BASIL,
                    ModItems.BASIL,
                    ModItems.BAMBOO_SHOOT
            );


            getOrCreateTagBuilder(PINEAPPLE).addOptional(
                    new ResourceLocation("fishofthieves","crownless_pineapple")
            ).addOptional(
                    new ResourceLocation("fishofthieves","pineapple")
            );

            getOrCreateTagBuilder(BANANA).addOptional(
                    new ResourceLocation("fishofthieves","banana")
            );

            getOrCreateTagBuilder(SPRING_CROPS_ITEM).add(
                    ModItems.LIME_SAPLING,
                    ModItems.PEPPER_SEED,
                    ModItems.DURIAN_SAPLING,
                    ModItems.PAPAYA_SAPLING,
                    ModItems.HOLY_BASIL,
                    ModItems.BASIL,
                    ModItems.BUTTERFLY_PEA_SEEDS
            );

            getOrCreateTagBuilder(SUMMER_CROPS_ITEM).add(
                    ModItems.LIME_SAPLING,
                    ModItems.PEPPER_SEED,
                    ModItems.DURIAN_SAPLING,
                    ModItems.MANGO_SAPLING,
                    ModItems.COCONUT,
                    ModItems.COCONUT_SAPLING,
                    ModItems.PAPAYA_SAPLING,
                    ModItems.HOLY_BASIL,
                    ModItems.BASIL,
                    ModItems.BUTTERFLY_PEA_SEEDS
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
