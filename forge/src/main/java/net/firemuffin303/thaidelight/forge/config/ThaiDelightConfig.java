package net.firemuffin303.thaidelight.forge.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModList;

public class ThaiDelightConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // Gameplay
    public static final ForgeConfigSpec.BooleanValue WANDERING_TRADER_SHOULD_TRADE_TD_ITEM;
    public static final ForgeConfigSpec.BooleanValue VILLAGER_SHOULD_TRADE_TD_ITEM;
    public static final ForgeConfigSpec.BooleanValue STINKY_SHOULD_TRIGGER_NEUTRAL;

    static {
        BUILDER.comment("Gameplay Settings").push("gameplay");

        WANDERING_TRADER_SHOULD_TRADE_TD_ITEM = BUILDER
                .comment("Should wandering traders sell Thai Delight items?")
                .define("wanderingTraderShouldTradeTDItem", true);

        VILLAGER_SHOULD_TRADE_TD_ITEM = BUILDER
                .comment("Should villagers sell Thai Delight items?")
                .define("villagerShouldTradeTDItem", true);

        STINKY_SHOULD_TRIGGER_NEUTRAL = BUILDER
                .comment("Should the Stinky effect trigger neutral mobs?")
                .define("stinkyShouldTriggerNeutral", true);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    // Helper — guard Fish of Thieves config values behind a mod presence check
    public static boolean isFishOfThievesLoaded() {
        return ModList.get().isLoaded("fishofthieves");
    }

    public static enum TreeType{
        BOTH,
        THAI_DELIGHT,
        FISH_OF_THIEVES
    }
}
