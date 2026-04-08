package net.firemuffin303.thaidelight.common.registry.forge;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMobEffectsImpl {
    public static final DeferredRegister<MobEffect> MOB_EFFECT = DeferredRegister.create(Registries.MOB_EFFECT, ThaiDelightCommon.MOD_ID);
    public static final DeferredRegister<Potion> POTION = DeferredRegister.create(Registries.POTION,ThaiDelightCommon.MOD_ID);

    public static Supplier<MobEffect> registerMobEffect(String id, Supplier<MobEffect> mobEffectSupplier) {
        return MOB_EFFECT.register(id,mobEffectSupplier);
    }

    public static Supplier<Potion> registerPotion(String id, Supplier<Potion> potionSupplier) {
        return POTION.register(id,potionSupplier);
    }
}
