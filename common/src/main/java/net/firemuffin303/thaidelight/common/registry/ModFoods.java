package net.firemuffin303.thaidelight.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

import java.util.List;
import java.util.function.Supplier;

public class ModFoods{
    public static final FoodProperties LIME = new FoodProperties.Builder().nutrition(4).saturationModifier(0.2F).build();
    public static final FoodProperties LIME_SLICE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).fast().build();

    public static final FoodProperties PEPPER = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).alwaysEdible().fast().build();

    public static final FoodProperties DURIAN_PULP = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).build();

    public static final FoodProperties COCONUT_MEAT = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).build();

    public static final FoodProperties MANGO = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).build();
    public static final FoodProperties MANGO_SLICE = new FoodProperties.Builder().nutrition(3).saturationModifier(0.2f).build();

    public static final FoodProperties PAPAYA = new FoodProperties.Builder().nutrition(5).saturationModifier(0.4F).build();
    public static final FoodProperties SLICED_PAPAYA = new FoodProperties.Builder().nutrition(3).saturationModifier(0.2F).build();

    public static final FoodProperties RAW_PAPAYA = new FoodProperties.Builder().nutrition(4).saturationModifier(0.2F).build();
    public static final FoodProperties SLICED_UNRIPE_PAPAYA = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).alwaysEdible().fast().build();

    public static final FoodProperties CRAB = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).build();
    public static final FoodProperties COOKED_CRAB = new FoodProperties.Builder().nutrition(8).saturationModifier(0.5F).build();

    public static final FoodProperties DRAGONFLY = addEffects(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).alwaysEdible(),
            List.of(
                    new FoodEffectSupplier(() ->new MobEffectInstance(MobEffects.HUNGER,10*20,0),0.8f),
                    new FoodEffectSupplier(() -> new MobEffectInstance(MobEffects.CONFUSION,10*20,0),0.8f)
            )).build();
    public static final FoodProperties COOKED_DRAGONFLY = new FoodProperties.Builder().nutrition(3).saturationModifier(0.2f).alwaysEdible().build();

    public static final FoodProperties FISH_SAUCE = new FoodProperties.Builder().alwaysEdible()
            .effect(new MobEffectInstance(MobEffects.HUNGER,200,0),1.0f).build();

    /*
    public static final FoodProperties FERMENTED_FISH = addEffects(new FoodProperties.Builder().alwaysEdible()(),
            List.of(
                new FoodEffectSupplier(() -> new MobEffectInstance(ModMobEffects.STINKY.get(),10*20),1f),
                new FoodEffectSupplier(() -> new MobEffectInstance(ModMobEffects.APPETITE_LOSS.get(),10*20),1f)
            )).build();

     */

    public static final FoodProperties PAPAYA_JUICE = new FoodProperties.Builder().alwaysEdible().build();
    public static final FoodProperties LIME_JUICE = new FoodProperties.Builder().alwaysEdible().build();
    public static final FoodProperties HONEY_LIME_JUICE = new FoodProperties.Builder().alwaysEdible().build();
    public static final FoodProperties COCONUT_WATER = new FoodProperties.Builder().alwaysEdible().build();

    public static final FoodProperties PESTO_SAUCE = (new FoodProperties.Builder())
            .nutrition(4).saturationModifier(0.4f).build();

    public static final FoodProperties FRIED_DURIAN = new FoodProperties.Builder().alwaysEdible().fast().nutrition(3).saturationModifier(0.4f).build();

    public static final FoodProperties SOMTAM = addEffects(new FoodProperties.Builder().nutrition(14).saturationModifier(0.75F),
            List.of(
                    new FoodEffectSupplier(() -> new MobEffectInstance(PlatformUtil.getNourishmentEffect(),6000,0),1.0f)
            )).build();

    public static final FoodProperties LARB = addEffects(new FoodProperties.Builder().nutrition(14).saturationModifier(0.75F),
            List.of(
                    new FoodEffectSupplier(() -> new MobEffectInstance(PlatformUtil.getNourishmentEffect(),6000,0),1.0f)
            )).build();

    public static final FoodProperties CRAB_FRIED_RICE = addEffects(new FoodProperties.Builder().nutrition(16).saturationModifier(0.80F),
            List.of(
                    new FoodEffectSupplier(() -> new MobEffectInstance(PlatformUtil.getNourishmentEffect(),2400,0),1.0f),
                    new FoodEffectSupplier(() -> new MobEffectInstance(MobEffects.WATER_BREATHING,1200,0),1.0f),
                    new FoodEffectSupplier(() -> new MobEffectInstance(PlatformUtil.getComfort(),9600,0),1.0f)
    )).build();

    public static final FoodProperties PHAT_KAPHRAO = addEffects(new FoodProperties.Builder()
            .nutrition(12)
            .saturationModifier(0.75f),
            List.of(
                    new FoodEffectSupplier(() -> new MobEffectInstance(PlatformUtil.getComfort(),6000,0),1.0f)
            ))
            .build();

    public static final FoodProperties MANGO_STICKY_RICE = addEffects(new FoodProperties.Builder()
            .nutrition(12)
            .saturationModifier(0.55F),
            List.of(
                    new FoodEffectSupplier(() -> new MobEffectInstance(PlatformUtil.getComfort(),9600,0),1.0f),
                    new FoodEffectSupplier(() ->new MobEffectInstance(MobEffects.DIG_SPEED,600),1.0f)
            ))
            .build();

    public static final FoodProperties PINEAPPLE_FRIED_RICE = addEffects(new FoodProperties.Builder()
            .nutrition(16)
            .saturationModifier(0.80F),
            List.of(
                    new FoodEffectSupplier(() ->new MobEffectInstance(PlatformUtil.getNourishmentEffect(),2400,0),1.0f),
                    new FoodEffectSupplier(() -> new MobEffectInstance(MobEffects.REGENERATION,600,0),1.0f),
                    new FoodEffectSupplier(() -> new MobEffectInstance(PlatformUtil.getComfort(),9600,0),1.0f)
            )).build();

    public static final FoodProperties STIR_FRIED_NOODLE = addEffects(new FoodProperties.Builder()
            .nutrition(12)
            .saturationModifier(0.55F),
            List.of(
                    new FoodEffectSupplier(() -> new MobEffectInstance(PlatformUtil.getComfort(),5000,0),1.0f)
            )).build();

    public static final FoodProperties DURIAN_CURRY = addEffects(new FoodProperties.Builder()
            .nutrition(12)
            .saturationModifier(0.8F),
            List.of(
                    new FoodEffectSupplier(() -> new MobEffectInstance(PlatformUtil.getNourishmentEffect(),3600,0),1.0f)
            )).build();

    public static final FoodProperties DURIAN_CAKE = addEffects(new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.1F)
            .fast(),
            List.of(
                    new FoodEffectSupplier(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 0, false, false),1.0f)
            )).build();

    public static final FoodProperties MANGO_PIE = addEffects(new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.3F)
            .fast(),
            List.of(
                    new FoodEffectSupplier(() ->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0, false, false), 1.0F)
            )).build();

    public static final FoodProperties COCONUT_PIE_SLICE = addEffects(new FoodProperties.Builder()
            .nutrition(3).saturationModifier(0.3f).fast(),
            List.of(
                    new FoodEffectSupplier(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED,600,0,false,false),1.0f)
            )).build();

    public static final FoodProperties COCONUT_JELLO = addEffects(new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(1F).fast(),
            List.of(
                    new FoodEffectSupplier(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED,200,0),1.0f)
            ))
            .fast().build();

    public static final FoodProperties KHANOM_BABIN = addEffects(new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(1F).fast(),List.of(
                    new FoodEffectSupplier(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED,200,0),1.0f)
    )).build();

    public static final FoodProperties OMELETTE = addEffects(new FoodProperties.Builder()
            .nutrition(12).saturationModifier(0.75f),List.of(
                    new FoodEffectSupplier(() -> new MobEffectInstance(PlatformUtil.getComfort(),6000,0),1.0f)
            )).build();

    public static final FoodProperties BASIL_OMELETTE = addEffects(new FoodProperties.Builder()
            .nutrition(12).saturationModifier(0.75f),
            List.of(
                    new FoodEffectSupplier(() -> new MobEffectInstance(PlatformUtil.getComfort(),9600,0),1.0f))
            ).build();

    public static final FoodProperties BAMBOO_SOUP = addEffects(new FoodProperties.Builder()
            .nutrition(10).saturationModifier(0.80f),
            List.of(
                    new FoodEffectSupplier(() -> new MobEffectInstance(PlatformUtil.getComfort(),4800,0),1.0f)
            )).build();

    public static final FoodProperties STEAMED_BAMBOO_SHOOT = addEffects(new FoodProperties.Builder()
            .nutrition(10).saturationModifier(0.80f),
            List.of(
                    new FoodEffectSupplier(() -> new MobEffectInstance(PlatformUtil.getComfort(),6000,0),1.0f)
            )).build();

    public static final FoodProperties BANANA_IN_COCONUT_MILK = addEffects(new FoodProperties.Builder()
            .nutrition(10).saturationModifier(0.90f),
            List.of(
                    new FoodEffectSupplier(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED,600,0),1.0f),
                    new FoodEffectSupplier(() -> new MobEffectInstance(PlatformUtil.getComfort(),6000,0),1.0f)
            )).build();

    public static final FoodProperties KHANOM_CHAN = addEffects(new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(1F)
            .fast(),List.of(new FoodEffectSupplier(() ->new MobEffectInstance(MobEffects.MOVEMENT_SPEED,200,0),1.0f)))
            .build();

    public static final FoodProperties COCONUT_MILK_ICE_CREAM = addEffects(new FoodProperties.Builder()
            .nutrition(3).saturationModifier(0.5F).fast(),
            List.of(
                    new FoodEffectSupplier(() ->new MobEffectInstance(MobEffects.MOVEMENT_SPEED,200,0),1.0f),
            new FoodEffectSupplier(() -> new MobEffectInstance(PlatformUtil.getComfort(),3000,0),1.0f)
                    ))
            .build();



    public static final FoodProperties SEAFOOD_SAUCE = addEffects(new FoodProperties.Builder().alwaysEdible(),
            List.of(new FoodEffectSupplier(() ->new MobEffectInstance(MobEffects.WATER_BREATHING,200,0),1.0f))).build();

    @ExpectPlatform
    public static FoodProperties.Builder addEffects(FoodProperties.Builder builder, List<FoodEffectSupplier> list){
        throw new AssertionError();
    }


    public record FoodEffectSupplier(Supplier<MobEffectInstance> supplier,float chance){}
}
