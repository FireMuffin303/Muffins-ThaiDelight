package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.common.ModMobEffect;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.ArrayList;

public class ModMobEffects {
    public static final ArrayList<MobEffect> MOB_EFFECTS = new ArrayList<>();

    public static final MobEffect CRAB_REACH = new ModMobEffect(MobEffectCategory.BENEFICIAL,0xe25f3e).addAttributeModifier(ModPlatform.getReachAttribute(),"6ebf72e4-95f2-4170-b727-f357d7ee1ed9",1.0d, AttributeModifier.Operation.ADDITION);

    public static void init(){
        register("crab_reach",CRAB_REACH);
    }

    private static void register(String id,MobEffect mobEffect){
        ModPlatform.registerMobEffect(id,mobEffect);
        MOB_EFFECTS.add(mobEffect);
    }
}
