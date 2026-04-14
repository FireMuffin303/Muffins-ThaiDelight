package net.firemuffin303.thaidelight.integration.midnightLib;

import eu.midnightdust.lib.config.MidnightConfig;

public class ThaiDelightConfig extends MidnightConfig {

    private static final String GAMEPLAY = "gameplay";
    private static final String FISH_OF_THIEVES = "fishofthieves";

    @Comment(category = GAMEPLAY,centered = true) public static Comment gameplay;
    @Entry(category = GAMEPLAY)
    public static Boolean wanderingTraderShouldTradeTDItem = true;

    @Entry(category = GAMEPLAY)
    public static Boolean villagerShouldTradeTDItem = true;

    @Entry(category = GAMEPLAY)
    public static Boolean stinkyShouldTriggerNeutral = true;


    @Comment(category = GAMEPLAY) public static Comment spacer1;
    @Comment(category = GAMEPLAY,centered = true) public static Comment worldGen;

    @Entry(category = GAMEPLAY)
    public static Boolean shouldThaiHouseSpawn = true;


    @Comment(category = GAMEPLAY) public static Comment spacer2;
    @Condition(requiredModId = FISH_OF_THIEVES)
    @Comment(category = GAMEPLAY,centered = true) public static Comment fishofthieves;
    @Condition(requiredModId = FISH_OF_THIEVES)
    @Entry(category = GAMEPLAY)
    public static TreeType coconutTreeType = TreeType.BOTH;

    @Condition(requiredModId = FISH_OF_THIEVES)
    @Entry(category = GAMEPLAY)
    public static boolean shouldMangoTreeSpawn = true;

    public static int encode(){
        //We doing Enchancement mod config sync way here, cause I have no brain to make it sync.
        //Yeah this is literally a ducttape the problem for now.
        String encoding =
                wanderingTraderShouldTradeTDItem.toString() +
                        villagerShouldTradeTDItem.toString() +
                        stinkyShouldTriggerNeutral.toString() +
                        shouldThaiHouseSpawn.toString() +
                        coconutTreeType.toString() +
                        shouldThaiHouseSpawn.toString();

        return encoding.hashCode();
    }


    public static enum TreeType{
        BOTH,
        THAI_DELIGHT,
        FISH_OF_THIEVES
    }
}
