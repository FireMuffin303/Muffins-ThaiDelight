package net.firemuffin303.muffinsthaidelightfabric.registry;

import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.mobeffect.ModMobEffect;
import net.firemuffin303.muffinsthaidelightfabric.common.mobeffect.StinkyMobEffect;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class ModMobEffects {
    public static MobEffect STINKY = Registry.register(BuiltInRegistries.MOB_EFFECT,new ResourceLocation(ThaiDelight.MOD_ID,"stinky"),new StinkyMobEffect(MobEffectCategory.HARMFUL,0xa5997c));
    public static MobEffect GLUTTONY = Registry.register(BuiltInRegistries.MOB_EFFECT,new ResourceLocation(ThaiDelight.MOD_ID,"gluttony"),new ModMobEffect(MobEffectCategory.BENEFICIAL,0xfbcb92));

    public static Potion STINKY_POTION = Registry.register(BuiltInRegistries.POTION,new ResourceLocation(ThaiDelight.MOD_ID,"stinkiness"),new Potion(new MobEffectInstance(ModMobEffects.STINKY,80*20)));
    public static Potion LONG_STINKY_POTION = Registry.register(BuiltInRegistries.POTION,new ResourceLocation(ThaiDelight.MOD_ID,"long_stinkiness"),new Potion(new MobEffectInstance(ModMobEffects.STINKY,200*20)));
    public static Potion STRONG_STINKY_POTION = Registry.register(BuiltInRegistries.POTION,new ResourceLocation(ThaiDelight.MOD_ID,"strong_stinkiness"),new Potion(new MobEffectInstance(ModMobEffects.STINKY,50*20,1)));

    public static void init() {

    }
}
