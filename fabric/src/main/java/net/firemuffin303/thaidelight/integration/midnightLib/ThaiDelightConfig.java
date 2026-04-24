package net.firemuffin303.thaidelight.integration.midnightLib;

import eu.midnightdust.lib.config.MidnightConfig;

public class ThaiDelightConfig extends MidnightConfig {

    private static final String GAMEPLAY = "gameplay";
    //private static final String FISH_OF_THIEVES = "fishofthieves";

    @Comment(category = GAMEPLAY,centered = true) public static Comment gameplay;
    @Entry(category = GAMEPLAY)
    public static Boolean wanderingTraderShouldTradeTDItem = true;

    @Entry(category = GAMEPLAY)
    public static Boolean villagerShouldTradeTDItem = true;

    @Entry(category = GAMEPLAY)
    public static Boolean stinkyShouldTriggerNeutral = true;


    public static enum TreeType{
        BOTH,
        THAI_DELIGHT,
        FISH_OF_THIEVES
    }
}
