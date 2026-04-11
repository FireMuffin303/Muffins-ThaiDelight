package net.firemuffin303.thaidelight.common.registry.fabric;

import net.firemuffin303.thaidelight.common.registry.ModFoods;
import net.minecraft.world.food.FoodProperties;

import java.util.List;

public class ModFoodsImpl {
    public static FoodProperties.Builder addEffects(FoodProperties.Builder builder, List<ModFoods.FoodEffectSupplier> list) {
        list.forEach(foodEffectSupplier -> builder.effect(foodEffectSupplier.supplier().get(),foodEffectSupplier.chance()));
        return builder;
    }
}
