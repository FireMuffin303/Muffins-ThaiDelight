package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

public class ModLootTables {
    public static final ResourceKey<LootTable> LIME_HARVEST = create(ThaiDelightCommon.modid("harvest/lime_harvest"));
    public static final ResourceKey<LootTable> LIME_SHEARS = create(ThaiDelightCommon.modid("harvest/lime_shears"));
    public static final ResourceKey<LootTable> BASIL_HARVEST = create(ThaiDelightCommon.modid("harvest/basil_harvest"));
    public static final ResourceKey<LootTable> BASIL_SHEARS = create(ThaiDelightCommon.modid("harvest/basil_shears"));
    public static final ResourceKey<LootTable> HOLY_BASIL_HARVEST = create(ThaiDelightCommon.modid("harvest/holy_basil_harvest"));
    public static final ResourceKey<LootTable> HOLY_BASIL_SHEARS = create(ThaiDelightCommon.modid("harvest/holy_basil_shears"));
    public static final ResourceKey<LootTable> BUTTERFLY_PEA_HARVEST = create(ThaiDelightCommon.modid("harvest/butterfly_pea_harvest"));
    public static final ResourceKey<LootTable> BUTTERFLY_PEA_SHEARS = create(ThaiDelightCommon.modid("harvest/butterfly_pea_shears"));
    public static final ResourceKey<LootTable> PEPPER_HARVEST = create(ThaiDelightCommon.modid("harvest/pepper_harvest"));
    //ENTITY
    public static final ResourceKey<LootTable> DRAGONFLY = create(ThaiDelightCommon.modid("entities/dragonfly"));
    public static final ResourceKey<LootTable> FLOWER_CRAB = create(ThaiDelightCommon.modid("entities/flower_crab"));
    //CHEST
    public static final ResourceKey<LootTable> VILLAGE_THAI_HOUSE = create(ThaiDelightCommon.modid("chests/village/village_thai_house"));
    //INJECT
    public static final ResourceKey<LootTable> INJECT_VILLAGE_PLAINS_HOUSE = create(ThaiDelightCommon.modid("inject/chests/village/village_plains_house"));
    public static final ResourceKey<LootTable> INJECT_VILLAGE_DESERT_HOUSE = create(ThaiDelightCommon.modid("inject/chests/village/village_desert_house"));
    public static final ResourceKey<LootTable> INJECT_VILLAGE_TAIGA_HOUSE = create(ThaiDelightCommon.modid("inject/chests/village/village_taiga_house"));
    public static final ResourceKey<LootTable> INJECT_VILLAGE_SNOWY_HOUSE = create(ThaiDelightCommon.modid("inject/chests/village/village_snowy_house"));
    public static final ResourceKey<LootTable> INJECT_VILLAGE_SAVANNA_HOUSE = create(ThaiDelightCommon.modid("inject/chests/village/village_snowy_house"));
    public static final ResourceKey<LootTable> INJECT_ABANDONED_MINESHAFT = create(ThaiDelightCommon.modid("inject/chests/abandoned_mineshaft"));
    public static final ResourceKey<LootTable> INJECT_PILLAGER_OUTPOST = create(ThaiDelightCommon.modid("inject/chests/pillager_outpost"));


    private static ResourceKey<LootTable> create(ResourceLocation resourceLocation){
        return ResourceKey.create(Registries.LOOT_TABLE,resourceLocation);
    }

}
