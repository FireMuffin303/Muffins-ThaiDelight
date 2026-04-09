package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Block> PAPAYA_LOGS = TagKey.create(Registries.BLOCK, ThaiDelightCommon.modid("papaya_logs"));

    public static final TagKey<Item> SPICY_FOODS = TagKey.create(Registries.ITEM,ThaiDelightCommon.modid("spicy_foods"));

    public static final TagKey<Item> LIME = TagKey.create(Registries.ITEM, new ResourceLocation("c","foods/lime"));
    public static final TagKey<Item> PAPAYA = TagKey.create(Registries.ITEM, new ResourceLocation("c","foods/papaya"));
    public static final TagKey<Item> RIPE_PAPAYA = TagKey.create(Registries.ITEM, new ResourceLocation("c","foods/ripe_papaya"));
    public static final TagKey<Item> RAW_PAPAYA = TagKey.create(Registries.ITEM, new ResourceLocation("c","foods/raw_papaya"));
    public static final TagKey<Item> DURIAN = TagKey.create(Registries.ITEM,new ResourceLocation("c","foods/durian"));
    public static final TagKey<Item> DURIAN_FOOD = TagKey.create(Registries.ITEM,ThaiDelightCommon.modid("durian_foods"));
    public static final TagKey<Item> MANGO = TagKey.create(Registries.ITEM,new ResourceLocation("c","foods/mango"));
    public static final TagKey<Item> COCONUT = TagKey.create(Registries.ITEM,new ResourceLocation("c","foods/coconut"));
    public static final TagKey<Item> PINEAPPLE = TagKey.create(Registries.ITEM,new ResourceLocation("c","foods/pineapple"));
    public static final TagKey<Item> BANANA = TagKey.create(Registries.ITEM,new ResourceLocation("c","foods/banana"));

    public static final TagKey<Item> FERMENTED_DRINKS = TagKey.create(Registries.ITEM,new ResourceLocation("c","foods/fermented_drinks"));

    public static final TagKey<Item> FLOWER_CRAB_MEAT = TagKey.create(Registries.ITEM, ThaiDelightCommon.modid("flower_crab_meat"));

    public static final TagKey<Item> FLOWER_CRAB_FOOD = TagKey.create(Registries.ITEM, ThaiDelightCommon.modid("flower_crab_food"));
    public static final TagKey<Item> DRAGONFLY_FOOD = TagKey.create(Registries.ITEM, ThaiDelightCommon.modid("dragonfly_food"));
    public static final TagKey<Item> WATER_BUFFALO_FOOD = TagKey.create(Registries.ITEM, ThaiDelightCommon.modid("water_buffalo_food"));

    public static final TagKey<Item> DURIAN_LOGS_ITEM = TagKey.create(Registries.ITEM,ThaiDelightCommon.modid("durian_logs"));
    public static final TagKey<Item> MANGO_LOGS_ITEM = TagKey.create(Registries.ITEM,ThaiDelightCommon.modid("mango_logs"));
    public static final TagKey<Item> COCONUT_LOGS_ITEM = TagKey.create(Registries.ITEM,ThaiDelightCommon.modid("coconut_logs"));
    public static final TagKey<Item> PAPAYA_LOGS_ITEM = TagKey.create(Registries.ITEM,ThaiDelightCommon.modid("papaya_logs"));

    public static final TagKey<Block> FLOWER_CRAB_SPAWNABLE_ON = TagKey.create(Registries.BLOCK,ThaiDelightCommon.modid("flower_crab_spawnable_on"));

    public static final TagKey<Block> DURIAN_LOGS_BLOCK = TagKey.create(Registries.BLOCK,ThaiDelightCommon.modid("durian_logs"));
    public static final TagKey<Block> MANGO_LOGS_BLOCK = TagKey.create(Registries.BLOCK,ThaiDelightCommon.modid("mango_logs"));
    public static final TagKey<Block> COCONUT_LOGS_BLOCK = TagKey.create(Registries.BLOCK,ThaiDelightCommon.modid("coconut_logs"));

    public static final TagKey<Block> SACK_CATCHABLE = TagKey.create(Registries.BLOCK,ThaiDelightCommon.modid("sack_catchable"));
    public static final TagKey<Block> SACK_HEAVY_CATCHABLE = TagKey.create(Registries.BLOCK,ThaiDelightCommon.modid("sack_heavy_catchable"));

    public static final TagKey<Block> DURIAN_RICH_SOIL = TagKey.create(Registries.BLOCK,ThaiDelightCommon.modid("durian_rich_soil"));
    public static final TagKey<Block> MANGO_RICH_SOIL = TagKey.create(Registries.BLOCK,ThaiDelightCommon.modid("mango_rich_soil"));
    public static final TagKey<Block> COCONUT_RICH_SOIL = TagKey.create(Registries.BLOCK,ThaiDelightCommon.modid("coconut_rich_soil"));
    public static final TagKey<Block> COMMON_RICH_SOIL = TagKey.create(Registries.BLOCK,ThaiDelightCommon.modid("common_rich_soil"));

    public static final TagKey<Biome> LIME_TREE_BIOMES = TagKey.create(Registries.BIOME, ThaiDelightCommon.modid("lime_tree_biomes"));
    public static final TagKey<Biome> PAPAYA_TREE_BIOMES = TagKey.create(Registries.BIOME, ThaiDelightCommon.modid("papaya_tree_biomes"));
    public static final TagKey<Biome> PEPPER_TREE_BIOMES = TagKey.create(Registries.BIOME, ThaiDelightCommon.modid("pepper_tree_biomes"));
    public static final TagKey<Biome> DURIAN_TREE_BIOMES = TagKey.create(Registries.BIOME, ThaiDelightCommon.modid("durian_tree_biomes"));
    public static final TagKey<Biome> MANGO_TREE_BIOMES = TagKey.create(Registries.BIOME, ThaiDelightCommon.modid("mango_tree_biomes"));
    public static final TagKey<Biome> COCONUT_TREE_BIOMES = TagKey.create(Registries.BIOME, ThaiDelightCommon.modid("coconut_tree_biomes"));

    public static final TagKey<Biome> WILD_HOLY_BASIL_BIOMES = TagKey.create(Registries.BIOME,ThaiDelightCommon.modid("wild_holy_basil_biomes"));
    public static final TagKey<Biome> WILD_BASIL_BIOMES = TagKey.create(Registries.BIOME,ThaiDelightCommon.modid("wild_basil_biomes"));
    public static final TagKey<Biome> WILD_ALL_BASIL_BIOMES = TagKey.create(Registries.BIOME,ThaiDelightCommon.modid("wild_all_basil_biomes"));
    public static final TagKey<Biome> BUTTERFLY_PEA_BIOMES = TagKey.create(Registries.BIOME,ThaiDelightCommon.modid("butterfly_peas_biomes"));

    //----- Farmers Delight --------
    public static final TagKey<Block> FARMER_DELIGHT_ROPE = TagKey.create(Registries.BLOCK,new ResourceLocation("farmersdelight","ropes"));

    //----- Covenience Tag ----
    public static final TagKey<Item> COMMON_MILKS = TagKey.create(Registries.ITEM,new ResourceLocation("c","milks"));
    public static final TagKey<Item> COMMON_COOKED_MEATS =  TagKey.create(Registries.ITEM,new ResourceLocation("c","foods/cooked_meats"));
    public static final TagKey<Item> KNIVES = TagKey.create(Registries.ITEM,new ResourceLocation("c","tools/knives"));
    public static final TagKey<Item> COMMON_EGGS = TagKey.create(Registries.ITEM,new ResourceLocation("c","eggs"));
    public static final TagKey<Item> COMMON_RAW_FISHES = TagKey.create(Registries.ITEM,new ResourceLocation("c","foods/raw_fishes"));

    public static final TagKey<Item> CROPS = TagKey.create(Registries.ITEM,new ResourceLocation("c","crops"));
    public static final TagKey<Item> VEGETABLES = TagKey.create(Registries.ITEM,new ResourceLocation("c","foods/vegetables"));
    public static final TagKey<Item> PEPPER = TagKey.create(Registries.ITEM,new ResourceLocation("c","foods/vegetables/chili_pepper"));

    //----- SereneSeason -----
    public static final TagKey<Block> AUTUMN_CROPS = TagKey.create(Registries.BLOCK,new ResourceLocation("sereneseasons","autumn_crops"));
    public static final TagKey<Block> SPRING_CROPS = TagKey.create(Registries.BLOCK,new ResourceLocation("sereneseasons","spring_crops"));
    public static final TagKey<Block> SUMMER_CROPS = TagKey.create(Registries.BLOCK,new ResourceLocation("sereneseasons","summer_crops"));
    public static final TagKey<Block> WINTER_CROPS = TagKey.create(Registries.BLOCK,new ResourceLocation("sereneseasons","winter_crops"));

    public static final TagKey<Item> AUTUMN_CROPS_ITEM = TagKey.create(Registries.ITEM,new ResourceLocation("sereneseasons","autumn_crops"));
    public static final TagKey<Item> SPRING_CROPS_ITEM = TagKey.create(Registries.ITEM,new ResourceLocation("sereneseasons","spring_crops"));
    public static final TagKey<Item> SUMMER_CROPS_ITEM = TagKey.create(Registries.ITEM,new ResourceLocation("sereneseasons","summer_crops"));
    public static final TagKey<Item> WINTER_CROPS_ITEM = TagKey.create(Registries.ITEM,new ResourceLocation("sereneseasons","winter_crops"));


    //Damage Types
    public static final TagKey<DamageType> FALLING_DURIAN = TagKey.create(Registries.DAMAGE_TYPE,ThaiDelightCommon.modid("falling_durian"));
    public static final TagKey<DamageType> SPICY_RESISTANT_TO = TagKey.create(Registries.DAMAGE_TYPE,ThaiDelightCommon.modid("spicy_resistant_to"));

}

