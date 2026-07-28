package net.firemuffin303.thaidelight.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.mobeffect.ModMobEffect;
import net.firemuffin303.thaidelight.common.mobeffect.StinkyMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

import java.util.function.Supplier;

public class ModMobEffects {
    public static final ResourceRegistry<MobEffect> MOB_EFFECT = ResourceRegistry.create(Registries.MOB_EFFECT,ThaiDelightCommon.MOD_ID);
    public static final ResourceRegistry<Potion> POTION = ResourceRegistry.create(Registries.POTION,ThaiDelightCommon.MOD_ID);

    public static Holder<MobEffect> STINKY = MOB_EFFECT.registerHolder("stinky",() -> new StinkyMobEffect(MobEffectCategory.HARMFUL,0xa5997c));
    public static Holder<MobEffect> APPETITE_LOSS = MOB_EFFECT.registerHolder("appetite_loss",() -> new ModMobEffect(MobEffectCategory.HARMFUL,0x271e46));

    public static Holder<Potion> STENCH_POTION = POTION.registerHolder("stench",() -> new Potion(new MobEffectInstance(ModMobEffects.STINKY,120*20), new MobEffectInstance(ModMobEffects.APPETITE_LOSS,120*20)));
    public static Holder<Potion> LONG_STENCH_POTION = POTION.registerHolder("long_stench",() -> new Potion(new MobEffectInstance(ModMobEffects.STINKY,300*20), new MobEffectInstance(ModMobEffects.APPETITE_LOSS,300*20)));
    public static Holder<Potion> STRONG_STENCH_POTION = POTION.registerHolder("strong_stench",() -> new Potion(new MobEffectInstance(ModMobEffects.STINKY,50*20,1), new MobEffectInstance(ModMobEffects.APPETITE_LOSS,50*20,1)));


    public static void init() {
        MOB_EFFECT.init();
        POTION.init();
    }

}
