package net.firemuffin303.thaidelight.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.ModMobEffect;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class ModMobEffects {
    public static final ResourcefulRegistry<MobEffect> MOB_EFFECT = ResourcefulRegistries.create(BuiltInRegistries.MOB_EFFECT, ThaiDelight.MOD_ID);

    public static final RegistryEntry<MobEffect> CRAB_REACH = MOB_EFFECT.register("crab_reach", () -> new ModMobEffect(MobEffectCategory.BENEFICIAL,0xe25f3e).addAttributeModifier(ModPlatform.getReachAttribute(),"6ebf72e4-95f2-4170-b727-f357d7ee1ed9",1.0d, AttributeModifier.Operation.ADDITION));
}
