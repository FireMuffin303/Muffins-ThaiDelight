package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;

import java.util.ArrayList;

public class ModPotions {
    private static final ArrayList<Potion> POTIONS = new ArrayList<>();

    public static final Potion CRAB_REACH = new Potion("crab_reach",new MobEffectInstance(ModMobEffects.CRAB_REACH,3600));
    public static final Potion LONG_CRAB_REACH = new Potion("crab_reach",new MobEffectInstance(ModMobEffects.CRAB_REACH,9600));
    public static final Potion STRONG_CRAB_REACH = new Potion("crab_reach",new MobEffectInstance(ModMobEffects.CRAB_REACH,1800,1));
    public static final Potion FISH_SAUCE = new Potion(new MobEffectInstance[0]);


    public static void init(){
        register("crab_reach",CRAB_REACH);
        register("long_crab_reach",LONG_CRAB_REACH);
        register("strong_crab_reach",STRONG_CRAB_REACH);
        register("fish_sauce",FISH_SAUCE);
    }

    private static void register(String id,Potion potion){
        ModPlatform.registerPotion(id, potion);
        POTIONS.add(potion);
    }

    public static void potionBrewing(){
        ModPlatform.registerPotionBrewing(() -> Potions.AWKWARD,()-> ModItems.CRAB_MEAT,()->ModPotions.CRAB_REACH);
        ModPlatform.registerPotionBrewing(() -> ModPotions.CRAB_REACH, () -> Items.REDSTONE,()->ModPotions.LONG_CRAB_REACH);
        ModPlatform.registerPotionBrewing(() -> ModPotions.STRONG_CRAB_REACH, () -> Items.GLOWSTONE,()->ModPotions.STRONG_CRAB_REACH);
        //ModPlatform.registerPotionBrewing(() -> Potions.WATER,()-> Items.COD,()->ModPotions.FISH_SAUCE);
    }

    public static void postInit(){
        potionBrewing();
    }
}
