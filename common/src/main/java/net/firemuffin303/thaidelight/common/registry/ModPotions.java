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


    public static void init(){
    }

    private static void register(String id,Potion potion){
        ModPlatform.registerPotion(id, potion);
        POTIONS.add(potion);
    }

    public static void potionBrewing(){
    }

    public static void postInit(){
        potionBrewing();
    }
}
