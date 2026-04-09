package net.firemuffin303.thaidelight.common.registry.fabric;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.item.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Supplier;

public class ModItemsImpl {

    public static final ResourceKey<TerraformBoatType> DURIAN_BOAT_KEY = TerraformBoatTypeRegistry.createKey(ThaiDelightCommon.modid("durian_boat"));
    public static final ResourceKey<TerraformBoatType> COCONUT_BOAT_KEY = TerraformBoatTypeRegistry.createKey(ThaiDelightCommon.modid("coconut_boat"));
    public static final ResourceKey<TerraformBoatType> MANGO_BOAT_KEY = TerraformBoatTypeRegistry.createKey(ThaiDelightCommon.modid("mango_boat"));

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
        CreativeModeTab creativeModeTab = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,ThaiDelightCommon.modid(id),supplier.get());
        return () -> creativeModeTab;
    }

    public static Supplier<Item> createBoat(ResourceLocation id,boolean chest,String boatType) {
        return switch (boatType){
            case "durian" ->  () -> TerraformBoatItemHelper.registerBoatItem(id,DURIAN_BOAT_KEY,chest);
            case "mango" -> () -> TerraformBoatItemHelper.registerBoatItem(id,MANGO_BOAT_KEY,chest);
            case "coconut" -> () -> TerraformBoatItemHelper.registerBoatItem(id,COCONUT_BOAT_KEY,chest);
            default -> throw new IllegalStateException("Unexpected value: " + boatType);
        };
    }


}
