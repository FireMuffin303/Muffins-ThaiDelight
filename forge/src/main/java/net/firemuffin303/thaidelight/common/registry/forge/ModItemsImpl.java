package net.firemuffin303.thaidelight.common.registry.forge;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Supplier;

public class ModItemsImpl {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,ThaiDelightCommon.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, ThaiDelightCommon.MOD_ID);


    public static Item createPapayaJuiceItem() {
    }

    public static Item createLimeJuiceItem() {
    }

    public static Item createHoneyLimeJuiceItem() {
    }

    public static Item createCoconutWaterJuiceItem() {
    }

    public static Item createCoconutWaterJuiceItem() {
    }

    public static Item createDrinkableItem(Item.Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
        return new DrinkableItem(properties, hasPotionEffectTooltip, hasCustomTooltip);
    }

    public static Item.Properties getDrinkItem() {
        return ModItems.drinkItem();
    }

    public static Item createButterflyPeaTeaItem() {
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
}
