package net.firemuffin303.thaidelight.common.registry.forge;

import net.firemuffin303.thaidelight.common.registry.ModFoods;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

import java.util.List;
import java.util.function.Supplier;

public class ModFoodsImpl {
    public static FoodProperties.Builder addEffects(FoodProperties.Builder builder, List<ModFoods.FoodEffectSupplier> list) {
        list.forEach(mobEffectInstanceSupplier -> builder.effect(mobEffectInstanceSupplier.supplier(), mobEffectInstanceSupplier.chance()));
        return builder;
    }
}
