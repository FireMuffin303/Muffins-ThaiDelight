package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

public class ModLootTable {
    public static final ResourceKey<LootTable> DRAGONFLY_DROP = register("entities/dragonfly");
    public static final ResourceKey<LootTable> FLOWER_CRAB = register("entities/flower_crab");

    public static final ResourceKey<LootTable> INJECT_VILLAGE_DESERT_HOUSE = register("inject/chests/village/village_desert_house");
    public static final ResourceKey<LootTable> INJECT_VILLAGE_PLAINS_HOUSE = register("inject/chests/village/village_plains_house");
    public static final ResourceKey<LootTable> INJECT_VILLAGE_TAIGA_HOUSE = register("inject/chests/village/village_taiga_house");
    public static final ResourceKey<LootTable> INJECT_VILLAGE_SNOWY_HOUSE = register("inject/chests/village/village_snowy_house");
    public static final ResourceKey<LootTable> INJECT_VILLAGE_SAVANNA_HOUSE = register("inject/chests/village/village_savanna_house");
    public static final ResourceKey<LootTable> INJECT_ABANDONED_MINESHAFT = register("inject/chests/abandoned_mineshaft");
    public static final ResourceKey<LootTable> INJECT_PILLAGER_OUTPOST = register("inject/chests/pillager_outpost");

    public static final ResourceKey<LootTable> VILLAGE_THAI_HOUSE = register("chests/village/village_thai_house");

    public static ResourceKey<LootTable> register(String id){
        return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,id));
    }

    public static void init(){}

}
