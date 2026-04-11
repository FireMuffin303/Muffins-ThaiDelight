package net.firemuffin303.thaidelight.common.registry.forge;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.forge.common.item.drinks.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Supplier;

public class ModItemsImpl {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,ThaiDelightCommon.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, ThaiDelightCommon.MOD_ID);


    public static Supplier<Item> createPapayaJuiceItem() {
        return PapayaJuiceItem::new;
    }

    public static Supplier<Item> createLimeJuiceItem() {
        return LimeJuiceItem::new;
    }

    public static Supplier<Item> createHoneyLimeJuiceItem() {
        return HoneyLimeJuiceItem::new;
    }

    public static Supplier<Item> createButterflyPeaTeaItem() {
        return ButterflyPeaJuiceItem::new;
    }

    public static Supplier<Item> createCoconutWaterJuiceItem() {
        return CoconutWaterJuiceItem::new;
    }


    public static Supplier<Item> createDrinkableItem(Item.Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
        return () -> new DrinkableItem(properties, hasPotionEffectTooltip, hasCustomTooltip);
    }

    public static Item.Properties getDrinkItem() {
        return ModItems.drinkItem();
    }



    public static Supplier<Item> createBoat(ResourceLocation id, boolean chest, String boatType) {
        return () -> new BoatItem(chest, Boat.Type.BAMBOO,new Item.Properties().stacksTo(1));
    }

    public static Supplier<Item> register(String id, Supplier<Item> item) {
        return ITEMS.register(id,item);
    }

    public static Supplier<CreativeModeTab> registerCreativeTab(String id, Supplier<CreativeModeTab> supplier) {
        return CREATIVE_TAB.register(id,supplier);
    }

    public static Supplier<Item> createConsumeableItem(Item.Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        return () -> new ConsumableItem(properties, hasFoodEffectTooltip, hasCustomTooltip);
    }

    public static Item.Properties bowlItem(FoodProperties foodProperties) {
        return ModItems.bowlFoodItem(foodProperties);
    }

    public static <T extends Mob> Supplier<Item> createSpawnEgg(Supplier<EntityType<T>> entityTypeSupplier, int primaryColor, int secondaryColor, Item.Properties properties) {
        return () -> new ForgeSpawnEggItem(entityTypeSupplier,primaryColor,secondaryColor,properties);
    }

    public static <T extends Mob> Supplier<Item> createMobBucket(Supplier<EntityType<T>> entitySupplier, Supplier<? extends Fluid> fluidSupplier, Supplier<? extends SoundEvent> soundSupplier, Item.Properties properties){
        return () -> new MobBucketItem(entitySupplier,fluidSupplier,soundSupplier,properties);
    }
}
