package net.firemuffin303.thaidelight.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.mobeffect.ModMobEffect;
import net.firemuffin303.thaidelight.common.mobeffect.StinkyMobEffect;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

import java.util.function.Supplier;

public class ModMobEffects {
    public static Supplier<MobEffect> STINKY = registerMobEffect("stinky",() -> new StinkyMobEffect(MobEffectCategory.HARMFUL,0xa5997c));
    public static Supplier<MobEffect> APPETITE_LOSS = registerMobEffect("appetite_loss",() -> new ModMobEffect(MobEffectCategory.HARMFUL,0x271e46));

    public static Supplier<Potion> STENCH_POTION = registerPotion("stench",() -> new Potion(new MobEffectInstance(ModMobEffects.STINKY.get(),120*20), new MobEffectInstance(ModMobEffects.APPETITE_LOSS.get(),120*20)));
    public static Supplier<Potion> LONG_STENCH_POTION = registerPotion("long_stench",() -> new Potion(new MobEffectInstance(ModMobEffects.STINKY.get(),300*20), new MobEffectInstance(ModMobEffects.APPETITE_LOSS.get(),300*20)));
    public static Supplier<Potion> STRONG_STENCH_POTION = registerPotion("strong_stench",() -> new Potion(new MobEffectInstance(ModMobEffects.STINKY.get(),50*20,1), new MobEffectInstance(ModMobEffects.APPETITE_LOSS.get(),50*20,1)));


    public static void init() {}


    @ExpectPlatform
    public static Supplier<MobEffect> registerMobEffect(String id,Supplier<MobEffect> mobEffectSupplier){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Potion> registerPotion(String id,Supplier<Potion> potionSupplier){
        throw new AssertionError();
    }
}
