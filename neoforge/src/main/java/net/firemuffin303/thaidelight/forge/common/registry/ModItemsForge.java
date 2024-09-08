package net.firemuffin303.thaidelight.forge.common.registry;

import net.firemuffin303.thaidelight.forge.ThaiDelightForge;
import net.firemuffin303.thaidelight.forge.common.item.SomtamItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.function.Supplier;

public class ModItemsForge {
    public static final Supplier<Item> SOMTAM = ThaiDelightForge.ITEMS.register("somtam",() -> new SomtamItem(vectorwing.farmersdelight.common.registry.ModItems.bowlFoodItem(ModFoodForge.SOMTAM)));
    public static final Supplier<Item> SPICY_MINCED_MEAT_SALAD = ThaiDelightForge.ITEMS.register("spicy_minced_meat_salad",() -> new ConsumableItem(vectorwing.farmersdelight.common.registry.ModItems.bowlFoodItem(ModFoodForge.SPICY_MINCED_PORK_SALAD),true));
    public static final Supplier<Item> CRAB_FRIED_RICE = ThaiDelightForge.ITEMS.register("crab_fried_rice",() -> new ConsumableItem(vectorwing.farmersdelight.common.registry.ModItems.bowlFoodItem(ModFoodForge.CRAB_FRIED_RICE),true));
    public static final Supplier<Item> STIR_FRIED_NOODLE = ThaiDelightForge.ITEMS.register("stir_fried_noodle",() -> new ConsumableItem(vectorwing.farmersdelight.common.registry.ModItems.bowlFoodItem(ModFoodForge.STIR_FRIED_NOODLE),true));


    public static void init(){
    }

    public static class ModFoodForge{
        public static final FoodProperties SOMTAM = new FoodProperties.Builder()
                .nutrition(16)
                .saturationModifier(0.6F)
                .effect(new MobEffectInstance(ModEffects.NOURISHMENT,6000,0),1.0f).build();


        public static final FoodProperties CRAB_FRIED_RICE = (new FoodProperties.Builder()).nutrition(18)
                .saturationModifier(0.8F)
                .effect(new MobEffectInstance(ModEffects.COMFORT, 6000, 0), 1.0F).build();

        public static final FoodProperties SPICY_MINCED_PORK_SALAD = new FoodProperties.Builder()
                .nutrition(14)
                .saturationModifier(0.75F)
                .effect(new MobEffectInstance(ModEffects.NOURISHMENT,5000,0),1.0f).build();

        public static final FoodProperties STIR_FRIED_NOODLE = new FoodProperties.Builder()
                .nutrition(12)
                .saturationModifier(0.65F)
                .effect(new MobEffectInstance(ModEffects.COMFORT,5000,0),1.0f).build();
    }
}
