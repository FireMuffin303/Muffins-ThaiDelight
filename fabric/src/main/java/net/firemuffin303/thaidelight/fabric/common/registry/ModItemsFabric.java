package net.firemuffin303.thaidelight.fabric.common.registry;

import com.nhoryzon.mc.farmersdelight.item.ConsumableItem;
import com.nhoryzon.mc.farmersdelight.item.ModItemSettings;
import com.nhoryzon.mc.farmersdelight.item.enumeration.Foods;
import com.nhoryzon.mc.farmersdelight.registry.EffectsRegistry;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.fabric.common.item.SomtamItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

public class ModItemsFabric {
    public static final Item SOMTAM = new SomtamItem(foodBowl(ModFoodFabric.SOMTAM,Items.BOWL,16));
    public static final Item SPICY_MINCED_PORK_SALAD = new ConsumableItem(foodBowl(ModFoodFabric.SPICY_MINCED_PORK_SALAD,Items.BOWL,16));
    public static final Item CRAB_FRIED_RICE = new ConsumableItem(foodBowl(ModFoodFabric.CRAB_FRIED_RICE,Items.BOWL,16));

    public static void init(){
        register("somtam",SOMTAM);
        register("spicy_minced_pork_salad",SPICY_MINCED_PORK_SALAD);
        register("crab_fried_rice",CRAB_FRIED_RICE);
    }

    private static void register(String id, Item item){
        Registry.register(BuiltInRegistries.ITEM,new ResourceLocation(ThaiDelight.MOD_ID,id),item);
        ModItems.ITEMS.add(item);

    }

    private static Item.Properties foodBowl(FoodProperties foodProperties,Item item,int stackTo){
        return new Item.Properties().food(foodProperties).craftRemainder(item).stacksTo(stackTo);
    }

    static class ModFoodFabric{
        public static final FoodProperties SOMTAM = new FoodProperties.Builder().nutrition(18).saturationMod(0.8F).effect(new MobEffectInstance(EffectsRegistry.NOURISHMENT.get(),6000,0),1.0f).build();
        public static final FoodProperties CRAB_FRIED_RICE = new FoodProperties.Builder().nutrition(16).saturationMod(0.8F).effect(new MobEffectInstance(EffectsRegistry.COMFORT.get(),6000,0),1.0f).build();
        public static final FoodProperties SPICY_MINCED_PORK_SALAD = new FoodProperties.Builder()
                .nutrition(14)
                .saturationMod(0.75F)
                .effect(new MobEffectInstance(EffectsRegistry.NOURISHMENT.get(),5000,0),1.0f).build();
    }
}
