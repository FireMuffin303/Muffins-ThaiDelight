package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.common.ModMobEffect;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.ArrayList;

public class ModMobEffects {
    public static final ArrayList<MobEffect> MOB_EFFECTS = new ArrayList<>();

    private static void register(String id,MobEffect mobEffect){
        ModPlatform.registerMobEffect(id,mobEffect);
        MOB_EFFECTS.add(mobEffect);
    }
}
