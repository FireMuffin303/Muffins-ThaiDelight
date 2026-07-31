package net.firemuffin303.thaidelight.datagen.provider;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class ThaiDelightGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ThaiDelightGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output,registries, ThaiDelightCommon.MOD_ID);
    }

    @Override
    protected void start() {
        /*
        this.add("add_loot_village_plains_house",new AddLootTableModifierExtension(new LootItemCondition[]{},ThaiDelightCommon.modid("inject/chests/village/village_plains_house")));
        this.add("add_loot_village_desert_house",new AddLootTableModifierExtension(new LootItemCondition[]{},ThaiDelightCommon.modid("inject/chests/village/village_desert_house")));
        this.add("add_loot_village_savanna_house",new AddLootTableModifierExtension(new LootItemCondition[]{},ThaiDelightCommon.modid("inject/chests/village/village_savanna_house")));
        this.add("add_loot_village_taiga_house",new AddLootTableModifierExtension(new LootItemCondition[]{},ThaiDelightCommon.modid("inject/chests/village/village_taiga_house")));
        this.add("add_loot_village_snowy_house",new AddLootTableModifierExtension(new LootItemCondition[]{},ThaiDelightCommon.modid("inject/chests/village/village_snowy_house")));
        this.add("add_abandoned_mineshaft",new AddLootTableModifierExtension(new LootItemCondition[]{},ThaiDelightCommon.modid("inject/chests/abandoned_mineshaft")));

        this.add("add_pillager_outpost",new AddLootTableModifierExtension(new LootItemCondition[]{},ThaiDelightCommon.modid("inject/chests/pillager_outpost")));
        */
    }
}
