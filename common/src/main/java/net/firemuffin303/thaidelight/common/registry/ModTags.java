package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Block> PAPAYA_LOGS = TagKey.create(Registries.BLOCK, new ResourceLocation(ThaiDelight.MOD_ID,"papaya_logs"));

    public static final TagKey<Item> LIME = TagKey.create(Registries.ITEM, new ResourceLocation(ThaiDelight.MOD_ID,"lime"));
    public static final TagKey<Item> PAPAYA = TagKey.create(Registries.ITEM, new ResourceLocation(ThaiDelight.MOD_ID,"papaya"));
    public static final TagKey<Item> RIPE_PAPAYA = TagKey.create(Registries.ITEM, new ResourceLocation(ThaiDelight.MOD_ID,"ripe_papaya"));
    public static final TagKey<Item> RAW_PAPAYA = TagKey.create(Registries.ITEM, new ResourceLocation(ThaiDelight.MOD_ID,"raw_papaya"));
    public static final TagKey<Item> FLOWER_CRAB_MEAT = TagKey.create(Registries.ITEM, new ResourceLocation(ThaiDelight.MOD_ID,"flower_crab_meat"));

    public static final TagKey<Item> FLOWER_CRAB_FOOD = TagKey.create(Registries.ITEM, new ResourceLocation(ThaiDelight.MOD_ID,"flower_crab_food"));
    public static final TagKey<Item> DRAGONFLY_FOOD = TagKey.create(Registries.ITEM, new ResourceLocation(ThaiDelight.MOD_ID,"dragonfly_food"));

}
