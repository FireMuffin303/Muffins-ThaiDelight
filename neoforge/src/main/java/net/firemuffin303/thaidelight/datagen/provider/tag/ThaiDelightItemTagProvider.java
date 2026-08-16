package net.firemuffin303.thaidelight.datagen.provider.tag;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

import static net.firemuffin303.thaidelight.common.registry.ModTags.*;

public class ThaiDelightItemTagProvider extends ItemTagsProvider {
    TagKey<Item> INSECT_ITEMS = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("alexsmobs", "insect_items"));
    TagKey<Item> FORGE_RAW_FISHES = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("forge", "raw_fishes"));
    TagKey<Item> C_RAW_FISHES = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "raw_fishes"));

    public ThaiDelightItemTagProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, CompletableFuture<TagLookup<Block>> completableFuture2) {
        super(arg, completableFuture, completableFuture2);
    }


    @Override
    protected void addTags(HolderLookup.Provider arg) {
        this.tag(SPICY_FOODS).add(
                ModItems.PEPPER.get(),
                ModItems.SOMTAM.get(),
                ModItems.LARB.get(),
                ModItems.PHAT_KAPHRAO.get(),
                ModItems.BASIL_OMELETTE.get(),
                ModItems.DURIAN_CURRY.get(),
                ModItems.BAMBOO_SHOOT_SOUP.get(),
                ModItems.STEAMED_BAMBOO_SHOOT.get()
        );

        this.tag(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("origins", "meat")))
                .add(ModItems.CRAB_MEAT.get())
                .add(ModItems.COOKED_CRAB_MEAT.get())
                .add(ModItems.DRAGONFLY.get())
                .add(ModItems.COOKED_DRAGONFLY.get())
                .add(ModItems.LARB.get());

        this.tag(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("create", "upright_on_belt")))
                .add(
                        ModItems.FISH_SAUCE_BOTTLE.get(),
                        ModItems.LIME_JUICE.get(),
                        ModItems.HONEY_LIME_JUICE.get(),
                        ModItems.PAPAYA_JUICE.get(),
                        ModItems.COCONUT_WATER.get(),
                        ModItems.BUTTERFLY_PEA_TEA.get(),
                        ModItems.COCONUT_MILK_BOTTLE.get()
                );

        this.tag(ModTags.LIME)
                .add(ModItems.LIME.get())
                .add(ModItems.SLICED_LIME.get());

        this.tag(PEPPER).add(
                ModItems.PEPPER.get()
        );

        this.tag(ModTags.RAW_PAPAYA)
                .add(ModItems.RAW_PAPAYA.get())
                .add(ModItems.RAW_PAPAYA_SLICE.get());

        this.tag(ModTags.RIPE_PAPAYA)
                .add(ModItems.PAPAYA.get())
                .add(ModItems.SLICED_PAPAYA.get());

        this.tag(ModTags.PAPAYA)
                .addTag(ModTags.RIPE_PAPAYA)
                .addTag(ModTags.RAW_PAPAYA);

        this.tag(DURIAN)
                .add(ModItems.DURIAN.get(),ModItems.SMALL_DURIAN.get(),ModItems.DURIAN_PULP.get());

        this.tag(DURIAN_FOOD)
                .add(ModItems.DURIAN_PULP.get(),ModItems.FRIED_DURIAN.get(),ModItems.DURIAN_CURRY.get(),ModItems.DURIAN_CAKE_SLICE.get());


        this.tag(FERMENTED_DRINKS)
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



        this.tag(MANGO)
                .add(ModItems.MANGO.get(),ModItems.MANGO_SLICE.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath("fishofthieves","mango"));

        this.tag(COCONUT)
                .add(ModItems.COCONUT.get(),ModItems.STRIPPED_COCONUT.get(),ModItems.COCONUT_SLICE.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath("fishofthieves","coconut"));


        this.tag(ModTags.FLOWER_CRAB_MEAT)
                .add(ModItems.CRAB_MEAT.get())
                .add(ModItems.COOKED_CRAB_MEAT.get())
        ;

        this.tag(ModTags.FLOWER_CRAB_FOOD)
                .add(Items.COD)
                .add(Items.SALMON)
                .add(Items.TROPICAL_FISH)
                .add(vectorwing.farmersdelight.common.registry.ModItems.COD_SLICE.get())
                .add(vectorwing.farmersdelight.common.registry.ModItems.SALMON_SLICE.get())
                .addOptional(FORGE_RAW_FISHES.location())
                .addOptionalTag(C_RAW_FISHES.location())
        ;

        this.tag(C_RAW_FISHES);
        this.tag(FORGE_RAW_FISHES);


        this.tag(ModTags.DRAGONFLY_FOOD)
                .add(Items.SPIDER_EYE)
                .addOptional(INSECT_ITEMS.location());

        this.tag(INSECT_ITEMS)
                .add(ModItems.DRAGONFLY.get())
                .add(ModItems.COOKED_DRAGONFLY.get());

        this.tag(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("crabbersdelight", "cooked_seafood")))
                .add(ModItems.COOKED_CRAB_MEAT.get());

        this.tag(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("crabbersdelight", "raw_seafood")))
                .add(ModItems.CRAB_MEAT.get());

        this.tag(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("forge", "seeds")))
                .add(ModItems.PEPPER_SEED.get())
                .add(ModItems.PAPAYA_SEEDS.get());

        this.tag(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c","foods/cooked_meats")))
                .add(ModItems.COOKED_CRAB_MEAT.get())
                .add(ModItems.COOKED_DRAGONFLY.get());

        this.tag(ItemTags.PLANKS).add(
                ModItems.DURIAN_PLANKS.get(),
                ModItems.MANGO_PLANKS.get(),
                ModItems.COCONUT_PLANKS.get()
        );

        this.tag(DURIAN_LOGS_ITEM).add(
                ModItems.DURIAN_LOG.get(),
                ModItems.DURIAN_WOOD.get(),
                ModItems.STRIPPED_DURIAN_LOG.get(),
                ModItems.STRIPPED_DURIAN_WOOD.get()
        );
        this.tag(MANGO_LOGS_ITEM).add(
                ModItems.MANGO_LOG.get(),
                ModItems.MANGO_WOOD.get(),
                ModItems.STRIPPED_MANGO_LOG.get(),
                ModItems.STRIPPED_MANGO_WOOD.get()
        );
        this.tag(COCONUT_LOGS_ITEM).add(
                ModItems.COCONUT_LOG.get(),
                ModItems.COCONUT_WOOD.get(),
                ModItems.STRIPPED_COCONUT_LOG.get(),
                ModItems.STRIPPED_COCONUT_WOOD.get()
        );

        this.tag(ItemTags.SMALL_FLOWERS).add(
                ModItems.DURIAN_FLOWER.get(),
                ModItems.BUTTERFLY_PEA.get(),
                ModItems.PAPAYA_FLOWER.get()
        );

        this.tag(ItemTags.VILLAGER_PLANTABLE_SEEDS).add(
                ModItems.BASIL.get(),
                ModItems.PEPPER_SEED.get()
        );

        this.tag(PAPAYA_LOGS_ITEM).add(
                ModItems.PAPAYA_LOG.get(),
                ModItems.PAPAYA_WOOD.get(),
                ModItems.STRIPPED_PAPAYA_LOG.get(),
                ModItems.STRIPPED_PAPAYA_WOOD.get()
        );


        this.tag(ItemTags.SAPLINGS).add(
                ModItems.LIME_SAPLING.get(),
                ModItems.PAPAYA_SAPLING.get(),
                ModItems.DURIAN_SAPLING.get(),
                ModItems.MANGO_SAPLING.get(),
                ModItems.COCONUT_SAPLING.get()
        );

        this.tag(ItemTags.LOGS_THAT_BURN)
                .addTag(PAPAYA_LOGS_ITEM)
                .addTag(COCONUT_LOGS_ITEM)
                .addTag(MANGO_LOGS_ITEM)
                .addTag(DURIAN_LOGS_ITEM);

        this.tag(ItemTags.WOODEN_DOORS)
                .add(ModItems.DURIAN_DOOR.get(),ModItems.MANGO_DOOR.get(),ModItems.COCONUT_DOOR.get());

        this.tag(ItemTags.WOODEN_TRAPDOORS)
                .add(ModItems.DURIAN_TRAPDOOR.get(),ModItems.MANGO_TRAPDOOR.get(),ModItems.COCONUT_TRAPDOOR.get());

        this.tag(ItemTags.WOODEN_BUTTONS)
                .add(ModItems.DURIAN_BUTTON.get(),ModItems.MANGO_BUTTON.get(),ModItems.COCONUT_BUTTON.get());

        this.tag(ItemTags.WOODEN_FENCES)
                .add(ModItems.DURIAN_FENCE.get(),ModItems.MANGO_FENCE.get(),ModItems.COCONUT_FENCE.get());

        this.tag(ItemTags.WOODEN_SLABS)
                .add(ModItems.DURIAN_SLAB.get(),ModItems.MANGO_SLAB.get(),ModItems.COCONUT_SLAB.get());

        this.tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModItems.DURIAN_PRESSURE_PLATE.get(),ModItems.MANGO_PRESSURE_PLATE.get(),ModItems.COCONUT_PRESSURE_PLATE.get());

        this.tag(ItemTags.WOODEN_STAIRS)
                .add(ModItems.DURIAN_STAIRS.get(), ModItems.MANGO_STAIRS.get(),ModItems.COCONUT_STAIRS.get());

        this.tag(ItemTags.BOATS)
                .add(ModItems.DURIAN_BOAT.get(),ModItems.MANGO_BOAT.get(),ModItems.COCONUT_BOAT.get());

        this.tag(ItemTags.CHEST_BOATS)
                .add(ModItems.DURIAN_CHEST_BOAT.get(),ModItems.MANGO_CHEST_BOAT.get(),ModItems.COCONUT_CHEST_BOAT.get());

        this.tag(ItemTags.SIGNS)
                .add(ModItems.DURIAN_SIGN.get(),ModItems.MANGO_SIGN.get(),ModItems.COCONUT_SIGN.get());

        this.tag(ItemTags.HANGING_SIGNS)
                .add(ModItems.DURIAN_HANGING_SIGN.get(),ModItems.MANGO_HANGING_SIGN.get(),ModItems.COCONUT_HANGING_SIGN.get());

        /*
        this.tag(vectorwing.farmersdelight.common.tag.ModTags.WOODEN_CABINETS)
                .add(ModItems.DURIAN_CABINET.get(),ModItems.MANGO_CABINET.get(),ModItems.COCONUT_CABINET.get());

        this.tag(vectorwing.farmersdelight.common.tag.ModTags.WILD_CROPS_ITEM)
                .add(ModItems.WILD_PEPPER_CROP.get(),ModItems.WILD_BASIL.get());

         */

        this.tag(CROPS).add(
                ModItems.PEPPER.get(),
                ModItems.LIME.get(),
                ModItems.BASIL.get()
        );

        this.tag(VEGETABLES).add(
                ModItems.PEPPER.get(),
                ModItems.BASIL.get(),
                ModItems.BAMBOO_SHOOT.get()
        );


        this.tag(PINEAPPLE).addOptional(
                ResourceLocation.fromNamespaceAndPath("fishofthieves","crownless_pineapple")
        ).addOptional(
                ResourceLocation.fromNamespaceAndPath("fishofthieves","pineapple")
        );

        this.tag(BANANA).addOptional(
                ResourceLocation.fromNamespaceAndPath("fishofthieves","banana")
        );

        this.tag(SPRING_CROPS_ITEM).add(
                ModItems.LIME_SAPLING.get(),
                ModItems.PEPPER_SEED.get(),
                ModItems.DURIAN_SAPLING.get(),
                ModItems.PAPAYA_SAPLING.get(),
                ModItems.BASIL.get(),
                ModItems.BUTTERFLY_PEA_SEEDS.get()
        );

        this.tag(SUMMER_CROPS_ITEM).add(
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

    }
}
