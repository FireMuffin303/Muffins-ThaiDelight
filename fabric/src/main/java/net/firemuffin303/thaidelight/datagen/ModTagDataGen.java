package net.firemuffin303.thaidelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModTags;
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

import static net.firemuffin303.thaidelight.common.registry.ModTags.*;

public class ModTagDataGen {
    public static class ModItemTagDataGen extends FabricTagProvider.ItemTagProvider {
        TagKey<Item> INSECT_ITEMS = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("alexsmobs", "insect_items"));
        TagKey<Item> FORGE_RAW_FISHES = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("forge", "raw_fishes"));
        TagKey<Item> C_RAW_FISHES = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_fishes"));

        public ModItemTagDataGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
            super(output, completableFuture, null);
        }

        @Override
        protected void addTags(HolderLookup.Provider arg) {
            // getOrCreateTagBuilder(ItemTags.PIGLIN_LOVED).add(ModItems.GOLDEN_PASTLE);
            getOrCreateTagBuilder(SPICY_FOODS).add(
                    ModItems.PEPPER.get(),
                    ModItems.SOMTAM.get(),
                    ModItems.LARB.get(),
                    ModItems.PHAT_KAPHRAO.get(),
                    ModItems.BASIL_OMELETTE.get(),
                    ModItems.DURIAN_CURRY.get(),
                    ModItems.BAMBOO_SHOOT_SOUP.get(),
                    ModItems.STEAMED_BAMBOO_SHOOT.get()
            );

            getOrCreateTagBuilder(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("origins", "meat")))
                    .add(ModItems.CRAB_MEAT.get())
                    .add(ModItems.COOKED_CRAB_MEAT.get())
                    .add(ModItems.DRAGONFLY.get())
                    .add(ModItems.COOKED_DRAGONFLY.get())
                    .add(ModItems.LARB.get());

            getOrCreateTagBuilder(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("create", "upright_on_belt")))
                    .add(
                            ModItems.FISH_SAUCE_BOTTLE.get(),
                            ModItems.LIME_JUICE.get(),
                            ModItems.HONEY_LIME_JUICE.get(),
                            ModItems.PAPAYA_JUICE.get(),
                            ModItems.COCONUT_WATER.get(),
                            ModItems.BUTTERFLY_PEA_TEA.get(),
                            ModItems.COCONUT_MILK_BOTTLE.get()
                    );

            getOrCreateTagBuilder(ModTags.LIME)
                    .add(ModItems.LIME.get())
                    .add(ModItems.SLICED_LIME.get());

            getOrCreateTagBuilder(PEPPER).add(
                    ModItems.PEPPER.get()
            );

            getOrCreateTagBuilder(ModTags.RAW_PAPAYA)
                    .add(ModItems.RAW_PAPAYA.get())
                    .add(ModItems.RAW_PAPAYA_SLICE.get());

            getOrCreateTagBuilder(ModTags.RIPE_PAPAYA)
                    .add(ModItems.PAPAYA.get())
                    .add(ModItems.SLICED_PAPAYA.get());

            getOrCreateTagBuilder(ModTags.PAPAYA)
                    .addTag(ModTags.RIPE_PAPAYA)
                    .addTag(ModTags.RAW_PAPAYA);

            getOrCreateTagBuilder(DURIAN)
                    .add(ModItems.DURIAN.get(),ModItems.SMALL_DURIAN.get(),ModItems.DURIAN_PULP.get());

            getOrCreateTagBuilder(DURIAN_FOOD)
                    .add(ModItems.DURIAN_PULP.get(),ModItems.FRIED_DURIAN.get(),ModItems.DURIAN_CURRY.get(),ModItems.DURIAN_CAKE_SLICE.get());


            getOrCreateTagBuilder(FERMENTED_DRINKS)
                    .addOptional(ResourceLocation.fromNamespaceAndPath("brewinandchewin","beer"))
                    .addOptional(ResourceLocation.fromNamespaceAndPath("brewinandchewin","vodka"))
                    .addOptional(ResourceLocation.fromNamespaceAndPath("brewinandchewin","mead"))
                    .addOptional(ResourceLocation.fromNamespaceAndPath("brewinandchewin","rice_wine"))
                    .addOptional(ResourceLocation.fromNamespaceAndPath("brewinandchewin","egg_grog"))
                    .addOptional(ResourceLocation.fromNamespaceAndPath("brewinandchewin","strongroot_ale"))
                    .addOptional(ResourceLocation.fromNamespaceAndPath("brewinandchewin","saccharine_rum"))
                    .addOptional(ResourceLocation.fromNamespaceAndPath("brewinandchewin","pale_jane"))
                    .addOptional(ResourceLocation.fromNamespaceAndPath("brewinandchewin","dread_nog"))
                    .addOptional(ResourceLocation.fromNamespaceAndPath("brewinandchewin","salty_folly"))
                    .addOptional(ResourceLocation.fromNamespaceAndPath("brewinandchewin","steel_toe_stout"))
                    .addOptional(ResourceLocation.fromNamespaceAndPath("brewinandchewin","glittering_grenadine"))
                    .addOptional(ResourceLocation.fromNamespaceAndPath("brewinandchewin","bloody_mary"))
                    .addOptional(ResourceLocation.fromNamespaceAndPath("brewinandchewin","red_rum"))
                    .addOptional(ResourceLocation.fromNamespaceAndPath("brewinandchewin","withering_dross"))
                    .addOptional(ResourceLocation.fromNamespaceAndPath("brewinandchewin","kombucha"))
                    .addOptionalTag(ResourceLocation.fromNamespaceAndPath("vinery","red_wine"))
                    .addOptionalTag(ResourceLocation.fromNamespaceAndPath("vinery","white_wine"));



            getOrCreateTagBuilder(MANGO)
                    .add(ModItems.MANGO.get(),ModItems.MANGO_SLICE.get())
                    .addOptional(ResourceLocation.fromNamespaceAndPath("fishofthieves","mango"));

            getOrCreateTagBuilder(COCONUT)
                    .add(ModItems.COCONUT.get(),ModItems.STRIPPED_COCONUT.get(),ModItems.COCONUT_SLICE.get())
                    .addOptional(ResourceLocation.fromNamespaceAndPath("fishofthieves","coconut"));


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

            getOrCreateTagBuilder(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("crabbersdelight", "cooked_seafood")))
                    .add(ModItems.COOKED_CRAB_MEAT.get());

            getOrCreateTagBuilder(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("crabbersdelight", "raw_seafood")))
                    .add(ModItems.CRAB_MEAT.get());

            getOrCreateTagBuilder(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("forge", "seeds")))
                    .add(ModItems.PEPPER_SEED.get())
                    .add(ModItems.PAPAYA_SEEDS.get());

            getOrCreateTagBuilder(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c","foods/cooked_meats")))
                    .add(ModItems.COOKED_CRAB_MEAT.get())
                    .add(ModItems.COOKED_DRAGONFLY.get());

            getOrCreateTagBuilder(ItemTags.PLANKS).add(ModItems.DURIAN_PLANKS.get(),ModItems.MANGO_PLANKS.get(),ModItems.COCONUT_PLANKS.get());

            getOrCreateTagBuilder(DURIAN_LOGS_ITEM).add(ModItems.DURIAN_LOG.get(),ModItems.DURIAN_WOOD.get(),ModItems.STRIPPED_DURIAN_LOG.get(),ModItems.STRIPPED_DURIAN_WOOD.get());
            getOrCreateTagBuilder(MANGO_LOGS_ITEM).add(ModItems.MANGO_LOG.get(),ModItems.MANGO_WOOD.get(),ModItems.STRIPPED_MANGO_LOG.get(),ModItems.STRIPPED_MANGO_WOOD.get());
            getOrCreateTagBuilder(COCONUT_LOGS_ITEM).add(ModItems.COCONUT_LOG.get(),ModItems.COCONUT_WOOD.get(),ModItems.STRIPPED_COCONUT_LOG.get(),ModItems.STRIPPED_COCONUT_WOOD.get());

            getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS).add(
                    ModItems.DURIAN_FLOWER.get(),
                    ModItems.BUTTERFLY_PEA.get(),
                    ModItems.PAPAYA_FLOWER.get()
            );

            getOrCreateTagBuilder(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(
                    ModItems.BASIL.get(),
                    ModItems.PEPPER_SEED.get()
            );

            getOrCreateTagBuilder(PAPAYA_LOGS_ITEM).add(
                    ModItems.PAPAYA_LOG.get(),
                    ModItems.PAPAYA_WOOD.get(),
                    ModItems.STRIPPED_PAPAYA_LOG.get(),
                    ModItems.STRIPPED_PAPAYA_WOOD.get()
            );


            getOrCreateTagBuilder(ItemTags.SAPLINGS).add(
                    ModItems.LIME_SAPLING.get(),
                    ModItems.PAPAYA_SAPLING.get(),
                    ModItems.DURIAN_SAPLING.get(),
                    ModItems.MANGO_SAPLING.get(),
                    ModItems.COCONUT_SAPLING.get()
            );

            getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                    .addTag(PAPAYA_LOGS_ITEM)
                    .addTag(COCONUT_LOGS_ITEM)
                    .addTag(MANGO_LOGS_ITEM)
                    .addTag(DURIAN_LOGS_ITEM);

            getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                    .add(ModItems.DURIAN_DOOR.get(),ModItems.MANGO_DOOR.get(),ModItems.COCONUT_DOOR.get());

            getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                    .add(ModItems.DURIAN_TRAPDOOR.get(),ModItems.MANGO_TRAPDOOR.get(),ModItems.COCONUT_TRAPDOOR.get());

            getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
                    .add(ModItems.DURIAN_BUTTON.get(),ModItems.MANGO_BUTTON.get(),ModItems.COCONUT_BUTTON.get());

            getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                    .add(ModItems.DURIAN_FENCE.get(),ModItems.MANGO_FENCE.get(),ModItems.COCONUT_FENCE.get());

            getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
                    .add(ModItems.DURIAN_SLAB.get(),ModItems.MANGO_SLAB.get(),ModItems.COCONUT_SLAB.get());

            getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                    .add(ModItems.DURIAN_PRESSURE_PLATE.get(),ModItems.MANGO_PRESSURE_PLATE.get(),ModItems.COCONUT_PRESSURE_PLATE.get());

            getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
                    .add(ModItems.DURIAN_STAIRS.get(), ModItems.MANGO_STAIRS.get(),ModItems.COCONUT_STAIRS.get());

            getOrCreateTagBuilder(ItemTags.BOATS)
                    .add(ModItems.DURIAN_BOAT.get(),ModItems.MANGO_BOAT.get(),ModItems.COCONUT_BOAT.get());

            getOrCreateTagBuilder(ItemTags.CHEST_BOATS)
                    .add(ModItems.DURIAN_CHEST_BOAT.get(),ModItems.MANGO_CHEST_BOAT.get(),ModItems.COCONUT_CHEST_BOAT.get());

            getOrCreateTagBuilder(ItemTags.SIGNS)
                    .add(ModItems.DURIAN_SIGN.get(),ModItems.MANGO_SIGN.get(),ModItems.COCONUT_SIGN.get());

            getOrCreateTagBuilder(ItemTags.HANGING_SIGNS)
                    .add(ModItems.DURIAN_HANGING_SIGN.get(),ModItems.MANGO_HANGING_SIGN.get(),ModItems.COCONUT_HANGING_SIGN.get());

            getOrCreateTagBuilder(vectorwing.farmersdelight.common.tag.ModTags.WOODEN_CABINETS)
                    .add(ModItems.DURIAN_CABINET.get(),ModItems.MANGO_CABINET.get(),ModItems.COCONUT_CABINET.get());

            getOrCreateTagBuilder(vectorwing.farmersdelight.common.tag.ModTags.WILD_CROPS_ITEM)
                    .add(ModItems.WILD_PEPPER_CROP.get(),ModItems.WILD_BASIL.get());

            getOrCreateTagBuilder(CROPS).add(
                    ModItems.PEPPER.get(),
                    ModItems.LIME.get(),
                    ModItems.BASIL.get()
            );

            getOrCreateTagBuilder(VEGETABLES).add(
                    ModItems.PEPPER.get(),
                    ModItems.BASIL.get(),
                    ModItems.BAMBOO_SHOOT.get()
            );


            getOrCreateTagBuilder(PINEAPPLE).addOptional(
                    ResourceLocation.fromNamespaceAndPath("fishofthieves","crownless_pineapple")
            ).addOptional(
                    ResourceLocation.fromNamespaceAndPath("fishofthieves","pineapple")
            );

            getOrCreateTagBuilder(BANANA).addOptional(
                    ResourceLocation.fromNamespaceAndPath("fishofthieves","banana")
            );

            getOrCreateTagBuilder(SPRING_CROPS_ITEM).add(
                    ModItems.LIME_SAPLING.get(),
                    ModItems.PEPPER_SEED.get(),
                    ModItems.DURIAN_SAPLING.get(),
                    ModItems.PAPAYA_SAPLING.get(),
                    ModItems.BASIL.get(),
                    ModItems.BUTTERFLY_PEA_SEEDS.get()
            );

            getOrCreateTagBuilder(SUMMER_CROPS_ITEM).add(
                    ModItems.LIME_SAPLING.get(),
                    ModItems.PEPPER_SEED.get(),
                    ModItems.DURIAN_SAPLING.get(),
                    ModItems.MANGO_SAPLING.get(),
                    ModItems.COCONUT.get(),
                    ModItems.COCONUT_SAPLING.get(),
                    ModItems.PAPAYA_SAPLING.get(),
                    ModItems.BASIL.get(),
                    ModItems.BUTTERFLY_PEA_SEEDS.get()
            );

            getOrCreateTagBuilder(COMMON_RAW_FISHES).addOptionalTag(
                    TagKey.create(Registries.ITEM,ResourceLocation.fromNamespaceAndPath("forge","raw_fishes"))
            );

            getOrCreateTagBuilder(ItemTags.PIG_FOOD).add(
                    ModItems.RAW_PAPAYA.get(),
                    ModItems.PAPAYA.get(),
                    ModItems.SLICED_PAPAYA.get(),
                    ModItems.RAW_PAPAYA_SLICE.get(),
                    ModItems.LIME.get(),
                    ModItems.SLICED_LIME.get(),
                    ModItems.BAMBOO_SHOOT.get()
            );

            getOrCreateTagBuilder(ItemTags.PARROT_FOOD).add(
                    ModItems.PAPAYA_SEEDS.get(),
                    ModItems.PEPPER_SEED.get(),
                    ModItems.BUTTERFLY_PEA_SEEDS.get()
            );

            getOrCreateTagBuilder(ItemTags.CHICKEN_FOOD).add(
                    ModItems.PAPAYA_SEEDS.get(),
                    ModItems.PEPPER_SEED.get(),
                    ModItems.BUTTERFLY_PEA_SEEDS.get()
            );

            getOrCreateTagBuilder(ItemTags.FROG_FOOD).add(
                    ModItems.DRAGONFLY.get(),
                    ModItems.COOKED_DRAGONFLY.get()
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
                    .add(ModEntityTypes.DRAGONFLY.get());

            getOrCreateTagBuilder(EntityTypeTags.CAN_BREATHE_UNDER_WATER)
                    .add(ModEntityTypes.FLOWER_CRAB.get());
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

            getOrCreateTagBuilder(WILD_BASIL_BIOMES).forceAddTag(BiomeTags.IS_FOREST).forceAddTag(BiomeTags.IS_JUNGLE);
            getOrCreateTagBuilder(BUTTERFLY_PEA_BIOMES).forceAddTag(BiomeTags.IS_FOREST);

        }
    }
}
