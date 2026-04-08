package net.firemuffin303.thaidelight.common.registry.fabric;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.alchemy.Potion;

import java.util.function.Supplier;

public class ModMobEffectsImpl {
    public static Supplier<MobEffect> registerMobEffect(String id, Supplier<MobEffect> mobEffectSupplier) {
        MobEffect mobEffect = Registry.register(BuiltInRegistries.MOB_EFFECT, ThaiDelightCommon.modid(id),mobEffectSupplier.get());
        return () -> mobEffect;
    }

    public static Supplier<Potion> registerPotion(String id, Supplier<Potion> potionSupplier) {
        Potion potion = Registry.register(BuiltInRegistries.POTION,ThaiDelightCommon.modid(id),potionSupplier.get());
        return () -> potion;
    }
}
