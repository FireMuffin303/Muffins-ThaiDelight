package net.firemuffin303.thaidelight.forge.common;

import net.minecraft.world.level.GameRules;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Configuration {
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.BooleanValue GENERATE_WILD_PEPPER;
    public static ForgeConfigSpec.BooleanValue GENERATE_WILD_PAPAYA;
    public static ForgeConfigSpec.BooleanValue GENERATE_WILD_LIME;


    public Configuration(){

    }

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        COMMON_BUILDER.comment("World Generation").push("world");
        GENERATE_WILD_LIME = COMMON_BUILDER.comment("Should generate wild lime.").define("genWildLime",true);
        GENERATE_WILD_PEPPER = COMMON_BUILDER.comment("Should generate wild pepper.").define("genWildPepper",true);
        GENERATE_WILD_PAPAYA = COMMON_BUILDER.comment("Should generate wild papaya.").define("genWildPapaya",true);

        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
