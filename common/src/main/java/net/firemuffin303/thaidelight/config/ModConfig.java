package net.firemuffin303.thaidelight.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.firemuffin303.thaidelight.ThaiDelightCommon;

@Config(name = ThaiDelightCommon.MOD_ID)
public class ModConfig implements ConfigData {
    public static Boolean wanderingTraderShouldTradeTDItem = true;
    public static Boolean villagerShouldTradeTDItem = true;
    public static Boolean stinkyShouldTriggerNeutral = true;

}
