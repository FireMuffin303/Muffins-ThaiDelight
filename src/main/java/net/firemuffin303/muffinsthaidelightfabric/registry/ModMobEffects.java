package net.firemuffin303.muffinsthaidelightfabric.registry;

import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.mobeffect.ModMobEffect;
import net.firemuffin303.muffinsthaidelightfabric.common.mobeffect.StinkyMobEffect;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class ModMobEffects {
    public static MobEffect STINKY = Registry.register(BuiltInRegistries.MOB_EFFECT,ThaiDelight.modid("stinky"),new StinkyMobEffect(MobEffectCategory.HARMFUL,0xa5997c));
    public static MobEffect APPETITE_LOSS = Registry.register(BuiltInRegistries.MOB_EFFECT,ThaiDelight.modid("appetite_loss"),new ModMobEffect(MobEffectCategory.HARMFUL,0x271e46));


    public static Potion STENCH_POTION = Registry.register(BuiltInRegistries.POTION,
            ThaiDelight.modid("stench"),
            new Potion(
                    new MobEffectInstance(ModMobEffects.STINKY,120*20),
                    new MobEffectInstance(ModMobEffects.APPETITE_LOSS,120*20)
            ));

    public static Potion LONG_STENCH_POTION = Registry.register(BuiltInRegistries.POTION,
            ThaiDelight.modid("long_stench"),
            new Potion(
                    new MobEffectInstance(ModMobEffects.STINKY,300*20),
                    new MobEffectInstance(ModMobEffects.APPETITE_LOSS,300*20)
            ));

    public static Potion STRONG_STENCH_POTION = Registry.register(BuiltInRegistries.POTION,
            ThaiDelight.modid("strong_stench"),
            new Potion(
                    new MobEffectInstance(ModMobEffects.STINKY,50*20,1),
                    new MobEffectInstance(ModMobEffects.APPETITE_LOSS,50*20,1)
            ));

    public static void init() {

    }
}
