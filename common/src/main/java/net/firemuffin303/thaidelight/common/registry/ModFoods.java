package net.firemuffin303.thaidelight.common.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods{
    public static final FoodProperties LIME = new FoodProperties.Builder().nutrition(4).saturationMod(0.2F).build();
    public static final FoodProperties LIME_SLICE = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).fast().build();

    public static final FoodProperties PEPPER = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).alwaysEat().fast().build();

    public static final FoodProperties DURIAN_PULP = new FoodProperties.Builder().nutrition(8).saturationMod(0.4f).build();

    public static final FoodProperties COCONUT_MEAT = new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build();

    public static final FoodProperties MANGO = new FoodProperties.Builder().nutrition(6).saturationMod(0.4f).build();
    public static final FoodProperties MANGO_SLICE = new FoodProperties.Builder().nutrition(3).saturationMod(0.2f).build();

    public static final FoodProperties PAPAYA = new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).build();
    public static final FoodProperties SLICED_PAPAYA = new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).build();

    public static final FoodProperties RAW_PAPAYA = new FoodProperties.Builder().nutrition(4).saturationMod(0.2F).build();
    public static final FoodProperties SLICED_UNRIPE_PAPAYA = new FoodProperties.Builder().nutrition(1).saturationMod(0.1F).alwaysEat().fast().build();

    public static final FoodProperties CRAB = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).meat().build();
    public static final FoodProperties COOKED_CRAB = new FoodProperties.Builder().nutrition(8).saturationMod(0.5F).meat().build();

    public static final FoodProperties DRAGONFLY = new FoodProperties.Builder().nutrition(2).saturationMod(0.1f).alwaysEat()
            .effect(new MobEffectInstance(MobEffects.HUNGER,10*20,0),0.8f).effect(new MobEffectInstance(MobEffects.CONFUSION,10*20,0),0.8f).build();
    public static final FoodProperties COOKED_DRAGONFLY = new FoodProperties.Builder().nutrition(3).saturationMod(0.2f).alwaysEat().build();

    public static final FoodProperties FISH_SAUCE = new FoodProperties.Builder().alwaysEat().effect(new MobEffectInstance(MobEffects.HUNGER,200,0),1.0f).build();
    public static final FoodProperties FERMENTED_FISH = new FoodProperties.Builder().alwaysEat()
            .effect(new MobEffectInstance(ModMobEffects.STINKY.get(),10*20),1f)
            .effect(new MobEffectInstance(ModMobEffects.APPETITE_LOSS.get(),10*20),1f)

            .build();

    public static final FoodProperties PAPAYA_JUICE = new FoodProperties.Builder().alwaysEat().build();
    public static final FoodProperties LIME_JUICE = new FoodProperties.Builder().alwaysEat().build();
    public static final FoodProperties HONEY_LIME_JUICE = new FoodProperties.Builder().alwaysEat().build();
    public static final FoodProperties COCONUT_WATER = new FoodProperties.Builder().alwaysEat().build();

    public static final FoodProperties PESTO_SAUCE = (new FoodProperties.Builder())
            .nutrition(4).saturationMod(0.4f).build();

    public static final FoodProperties FRIED_DURIAN = new FoodProperties.Builder().alwaysEat().fast().nutrition(3).saturationMod(0.4f).build();

    public static final FoodProperties SOMTAM = new FoodProperties.Builder()
            .nutrition(14)
            .saturationMod(0.75F)
            .effect(new MobEffectInstance(ModEffects.NOURISHMENT.get(),6000,0),1.0f).build();

    public static final FoodProperties LARB = new FoodProperties.Builder()
            .nutrition(14)
            .saturationMod(0.75F)
            .effect(new MobEffectInstance(ModEffects.NOURISHMENT.get(),6000,0),1.0f).build();

    public static final FoodProperties CRAB_FRIED_RICE = new FoodProperties.Builder()
            .nutrition(16)
            .saturationMod(0.80F)
            .effect(new MobEffectInstance(ModEffects.NOURISHMENT.get(),2400,0),1.0f)
            .effect(new MobEffectInstance(MobEffects.WATER_BREATHING,1200,0),1.0f)
            .effect(new MobEffectInstance(ModEffects.COMFORT.get(),9600,0),1.0f).build();

    public static final FoodProperties PHAT_KAPHRAO = new FoodProperties.Builder()
            .nutrition(12)
            .saturationMod(0.75f)
            .effect(new MobEffectInstance(ModEffects.COMFORT.get(),6000,0),1.0f)
            .build();

    public static final FoodProperties MANGO_STICKY_RICE = new FoodProperties.Builder()
            .nutrition(12)
            .saturationMod(0.55F)
            .effect(new MobEffectInstance(ModEffects.COMFORT.get(),9600,0),1.0f)
            .effect(new MobEffectInstance(MobEffects.DIG_SPEED,600),1.0f)
            .build();

    public static final FoodProperties PINEAPPLE_FRIED_RICE = new FoodProperties.Builder()
            .nutrition(16)
            .saturationMod(0.80F)
            .effect(new MobEffectInstance(ModEffects.NOURISHMENT.get(),2400,0),1.0f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION,600,0),1.0f)
            .effect(new MobEffectInstance(ModEffects.COMFORT.get(),9600,0),1.0f).build();

    public static final FoodProperties STIR_FRIED_NOODLE = new FoodProperties.Builder()
            .nutrition(12)
            .saturationMod(0.55F)
            .effect(new MobEffectInstance(ModEffects.COMFORT.get(),5000,0),1.0f).build();

    public static final FoodProperties DURIAN_CURRY = new FoodProperties.Builder()
            .nutrition(12)
            .saturationMod(0.8F)
            .effect(new MobEffectInstance(ModEffects.NOURISHMENT.get(),3600,0),1.0f).build();

    public static final FoodProperties DURIAN_CAKE = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(0.1F)
            .fast()
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 0, false, false), 1.0F).build();

    public static final FoodProperties MANGO_PIE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(0.3F)
            .fast()
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0, false, false), 1.0F).build();

    public static final FoodProperties COCONUT_PIE_SLICE = new FoodProperties.Builder()
            .nutrition(3).saturationMod(0.3f).fast()
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,600,0,false,false),1.0f).build();

    public static final FoodProperties COCONUT_JELLO = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(1F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,200,0),1.0f)
            .fast().build();

    public static final FoodProperties KHANOM_BABIN = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(1F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,200,0),1.0f)
            .fast().build();

    public static final FoodProperties OMELETTE = new FoodProperties.Builder()
            .nutrition(12).saturationMod(0.75f)
            .effect(new MobEffectInstance(ModEffects.COMFORT.get(),6000,0),1.0f).build();

    public static final FoodProperties BASIL_OMELETTE = new FoodProperties.Builder()
            .nutrition(12).saturationMod(0.75f)
            .effect(new MobEffectInstance(ModEffects.COMFORT.get(),9600,0),1.0f).build();

    public static final FoodProperties BAMBOO_SOUP = new FoodProperties.Builder()
            .nutrition(10).saturationMod(0.80f)
            .effect(new MobEffectInstance(ModEffects.COMFORT.get(),4800,0),1.0f).build();

    public static final FoodProperties STEAMED_BAMBOO_SHOOT = new FoodProperties.Builder()
            .nutrition(10).saturationMod(0.80f)
            .effect(new MobEffectInstance(ModEffects.COMFORT.get(),6000,0),1.0f).build();

    public static final FoodProperties BANANA_IN_COCONUT_MILK = new FoodProperties.Builder()
            .nutrition(10).saturationMod(0.90f)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,600,0),1.0f)
            .effect(new MobEffectInstance(ModEffects.COMFORT.get(),6000,0),1.0f).build();

    public static final FoodProperties KHANOM_CHAN = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(1F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,200,0),1.0f)
            .fast().build();

    public static final FoodProperties COCONUT_MILK_ICE_CREAM = new FoodProperties.Builder()
            .nutrition(3)
            .saturationMod(0.5F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,200,0),1.0f)
            .effect(new MobEffectInstance(ModEffects.COMFORT.get(),3000,0),1.0f)
            .fast()
            .build();



    public static final FoodProperties SEAFOOD_SAUCE = new FoodProperties.Builder().alwaysEat().effect(new MobEffectInstance(MobEffects.WATER_BREATHING,200,0),1.0f).build();
}
