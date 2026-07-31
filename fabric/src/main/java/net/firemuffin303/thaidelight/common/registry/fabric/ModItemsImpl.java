package net.firemuffin303.thaidelight.common.registry.fabric;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.item.*;
import net.firemuffin303.thaidelight.common.registry.ModMobEffects;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.material.Fluid;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Supplier;

public class ModItemsImpl {
    public static Supplier<Item> createPapayaJuiceItem() {
        return PapayaJuiceItem::new;
    }

    public static Supplier<Item> createLimeJuiceItem() {
        return LimeJuiceItem::new;
    }

    public static Supplier<Item> createHoneyLimeJuiceItem() {
        return HoneyLimeJuiceItem::new;
    }

    public static Supplier<Item> createCoconutWaterJuiceItem() {
        return CoconutWaterJuiceItem::new;
    }

    public static Supplier<Item> createButterflyPeaTeaItem() {
        return ButterflyPeaJuiceItem::new;
    }

    public static Supplier<Item> createDrinkableItem(Item.Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
        return () -> new DrinkableItem(properties, hasPotionEffectTooltip, hasCustomTooltip);
    }

    public static Item.Properties getDrinkItem() {
        return ModItems.drinkItem();
    }

    public static Supplier<Item> createConsumeableItem(Item.Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        return () -> new ConsumableItem(properties,hasFoodEffectTooltip,hasCustomTooltip);
    }

    public static Item.Properties bowlItem(FoodProperties foodProperties) {
        return ModItems.bowlFoodItem(foodProperties);
    }


    public static <T extends Item> Supplier<T> register(String id, Supplier<T> item) {
        T registeredItem = Registry.register(BuiltInRegistries.ITEM, ThaiDelightCommon.modid(id),item.get());
        return () -> registeredItem;
    }

    public static Supplier<CreativeModeTab> registerCreativeTab(String id, Supplier<CreativeModeTab> supplier) {
        CreativeModeTab creativeModeTab = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,ThaiDelightCommon.modid(id),supplier.get());
        return () -> creativeModeTab;
    }


    public static Supplier<Item> createSpawnEgg(Supplier<EntityType<?>> entityTypeSupplier, int primaryColor, int secondaryColor, Item.Properties properties) {
        return () -> new SpawnEggItem((EntityType<? extends Mob>) entityTypeSupplier.get(),primaryColor,secondaryColor,properties);
    }

    public static Supplier<Item> createMobBucket(Supplier<EntityType<?>> entitySupplier, Supplier<? extends Fluid> fluidSupplier, Supplier<? extends SoundEvent> soundSupplier, Item.Properties properties) {
        return () -> new MobBucketItem(entitySupplier.get(),fluidSupplier.get(),soundSupplier.get(),properties);
    }

    public static Supplier<Item> createFermentedFish() {
        return () -> new ConsumableItem(bowlItem(new FoodProperties.Builder().alwaysEdible()
                .effect(new MobEffectInstance(ModMobEffects.STINKY,10*20),1f)
                .effect(new MobEffectInstance(ModMobEffects.APPETITE_LOSS,10*20),1f)
                .build()),false,false);
    }


}
