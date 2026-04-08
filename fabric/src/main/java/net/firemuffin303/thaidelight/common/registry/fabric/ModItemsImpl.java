package net.firemuffin303.thaidelight.common.registry.fabric;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import com.terraformersmc.terraform.boat.impl.item.TerraformBoatItem;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.item.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Supplier;

public class ModItemsImpl {
    public static Item createPapayaJuiceItem() {
        return new PapayaJuiceItem();
    }

    public static Item createLimeJuiceItem() {
        return new LimeJuiceItem();
    }

    public static Item createHoneyLimeJuiceItem() {
        return new HoneyLimeJuiceItem();
    }

    public static Item createCoconutWaterJuiceItem() {
        return new CoconutWaterJuiceItem();
    }

    public static Item createButterflyPeaTeaItem() {
        return new ButterflyPeaJuiceItem();
    }

    public static Item createDrinkableItem(Item.Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
        return new DrinkableItem(properties, hasPotionEffectTooltip, hasCustomTooltip);
    }

    public static Item.Properties getDrinkItem() {
        return ModItems.drinkItem();
    }

    public static Item createConsumeableItem(Item.Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        return new ConsumableItem(properties,hasFoodEffectTooltip,hasCustomTooltip);
    }

    public static Item.Properties bowlItem(FoodProperties foodProperties) {
        return ModItems.bowlFoodItem(foodProperties);
    }


    public static Supplier<Item> register(String id, Supplier<Item> item) {
        Item registeredItem = Registry.register(BuiltInRegistries.ITEM, ThaiDelightCommon.modid(id),item.get());
        return () -> registeredItem;
    }

    public static Supplier<CreativeModeTab> registerCreativeTab(String id, Supplier<CreativeModeTab> supplier) {
    }


}
