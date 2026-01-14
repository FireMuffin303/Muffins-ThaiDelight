package net.firemuffin303.muffinsthaidelightfabric.registry;

import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Block> PAPAYA_LOGS = TagKey.create(Registries.BLOCK, new ResourceLocation(ThaiDelight.MOD_ID,"papaya_logs"));

    public static final TagKey<Item> LIME = TagKey.create(Registries.ITEM, new ResourceLocation(ThaiDelight.MOD_ID,"lime"));
    public static final TagKey<Item> PAPAYA = TagKey.create(Registries.ITEM, new ResourceLocation(ThaiDelight.MOD_ID,"papaya"));
    public static final TagKey<Item> RIPE_PAPAYA = TagKey.create(Registries.ITEM, new ResourceLocation(ThaiDelight.MOD_ID,"ripe_papaya"));
    public static final TagKey<Item> RAW_PAPAYA = TagKey.create(Registries.ITEM, new ResourceLocation(ThaiDelight.MOD_ID,"raw_papaya"));
    public static final TagKey<Item> DURIAN = TagKey.create(Registries.ITEM,ThaiDelight.modid("durian"));
    public static final TagKey<Item> MANGO = TagKey.create(Registries.ITEM,ThaiDelight.modid("mango"));
    public static final TagKey<Item> COCONUT = TagKey.create(Registries.ITEM,ThaiDelight.modid("coconut"));
    public static final TagKey<Item> PINEAPPLE = TagKey.create(Registries.ITEM,ThaiDelight.modid("pineapple"));
    public static final TagKey<Item> BANANA = TagKey.create(Registries.ITEM,ThaiDelight.modid("banana"));

    public static final TagKey<Item> FLOWER_CRAB_MEAT = TagKey.create(Registries.ITEM, new ResourceLocation(ThaiDelight.MOD_ID,"flower_crab_meat"));

    public static final TagKey<Item> FLOWER_CRAB_FOOD = TagKey.create(Registries.ITEM, new ResourceLocation(ThaiDelight.MOD_ID,"flower_crab_food"));
    public static final TagKey<Item> DRAGONFLY_FOOD = TagKey.create(Registries.ITEM, new ResourceLocation(ThaiDelight.MOD_ID,"dragonfly_food"));
    public static final TagKey<Item> WATER_BUFFALO_FOOD = TagKey.create(Registries.ITEM, new ResourceLocation(ThaiDelight.MOD_ID,"water_buffalo_food"));

    public static final TagKey<Item> DURIAN_LOGS_ITEM = TagKey.create(Registries.ITEM,ThaiDelight.modid("durian_logs"));
    public static final TagKey<Item> MANGO_LOGS_ITEM = TagKey.create(Registries.ITEM,ThaiDelight.modid("mango_logs"));
    public static final TagKey<Item> COCONUT_LOGS_ITEM = TagKey.create(Registries.ITEM,ThaiDelight.modid("coconut_logs"));


    public static final TagKey<Block> FLOWER_CRAB_SPAWNABLE_ON = TagKey.create(Registries.BLOCK,ThaiDelight.modid("flower_crab_spawnable_on"));

    public static final TagKey<Block> DURIAN_LOGS_BLOCK = TagKey.create(Registries.BLOCK,ThaiDelight.modid("durian_logs"));
    public static final TagKey<Block> MANGO_LOGS_BLOCK = TagKey.create(Registries.BLOCK,ThaiDelight.modid("mango_logs"));
    public static final TagKey<Block> COCONUT_LOGS_BLOCK = TagKey.create(Registries.BLOCK,ThaiDelight.modid("coconut_logs"));

    public static final TagKey<Block> SACK_CATCHABLE = TagKey.create(Registries.BLOCK,ThaiDelight.modid("sack_catchable"));
    public static final TagKey<Block> SACK_HEAVY_CATCHABLE = TagKey.create(Registries.BLOCK,ThaiDelight.modid("sack_heavy_catchable"));

    public static final TagKey<Block> DURIAN_RICH_SOIL = TagKey.create(Registries.BLOCK,ThaiDelight.modid("durian_rich_soil"));
    public static final TagKey<Block> MANGO_RICH_SOIL = TagKey.create(Registries.BLOCK,ThaiDelight.modid("mango_rich_soil"));
    public static final TagKey<Block> COCONUT_RICH_SOIL = TagKey.create(Registries.BLOCK,ThaiDelight.modid("coconut_rich_soil"));
    public static final TagKey<Block> COMMON_RICH_SOIL = TagKey.create(Registries.BLOCK,ThaiDelight.modid("common_rich_soil"));

    public static final TagKey<Biome> LIME_TREE_BIOMES = TagKey.create(Registries.BIOME, ThaiDelight.modid("lime_tree_biomes"));
    public static final TagKey<Biome> PAPAYA_TREE_BIOMES = TagKey.create(Registries.BIOME, ThaiDelight.modid("papaya_tree_biomes"));
    public static final TagKey<Biome> PEPPER_TREE_BIOMES = TagKey.create(Registries.BIOME, ThaiDelight.modid("pepper_tree_biomes"));
    public static final TagKey<Biome> DURIAN_TREE_BIOMES = TagKey.create(Registries.BIOME, ThaiDelight.modid("durian_tree_biomes"));
    public static final TagKey<Biome> MANGO_TREE_BIOMES = TagKey.create(Registries.BIOME, ThaiDelight.modid("mango_tree_biomes"));
    public static final TagKey<Biome> COCONUT_TREE_BIOMES = TagKey.create(Registries.BIOME, ThaiDelight.modid("coconut_tree_biomes"));

    //----- Covenience Tag ----
    public static final TagKey<Item> COMMON_MILKS = TagKey.create(Registries.ITEM,new ResourceLocation("c","milks"));
    public static final TagKey<Item> COMMON_COOKED_MEATS =  TagKey.create(Registries.ITEM,new ResourceLocation("c","foods/cooked_meats"));
    public static final TagKey<Item> KNIVES = TagKey.create(Registries.ITEM,new ResourceLocation("c","tools/knives"));
    public static final TagKey<Item> COMMON_EGGS = TagKey.create(Registries.ITEM,new ResourceLocation("c","eggs"));
    public static final TagKey<Item> COMMON_FISHES = TagKey.create(Registries.ITEM,new ResourceLocation("c","raw_fishes"));
}
