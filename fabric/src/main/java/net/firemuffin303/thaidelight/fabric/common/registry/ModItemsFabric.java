package net.firemuffin303.thaidelight.fabric.common.registry;

import com.nhoryzon.mc.farmersdelight.item.ConsumableItem;
import com.nhoryzon.mc.farmersdelight.registry.EffectsRegistry;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.fabric.common.item.SomtamItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ModItemsFabric {
    public static final Item SOMTAM = new SomtamItem(foodBowl(ModFoodFabric.SOMTAM));
    public static final Item SPICY_MINCED_MEAT_SALAD = new ConsumableItem(foodBowl(ModFoodFabric.SPICY_MINCED_PORK_SALAD));
    public static final Item CRAB_FRIED_RICE = new ConsumableItem(foodBowl(ModFoodFabric.CRAB_FRIED_RICE));
    public static final Item STIR_FRIED_NOODLE = new ConsumableItem(foodBowl(ModFoodFabric.STIR_FRIED_NOODLE));

    public static void init(){
        register("somtam",SOMTAM);
        register("spicy_minced_meat_salad", SPICY_MINCED_MEAT_SALAD);
        register("crab_fried_rice",CRAB_FRIED_RICE);
        register("stir_fried_noodle",STIR_FRIED_NOODLE);

    }

    private static void register(String id, Item item){
        Registry.register(BuiltInRegistries.ITEM,new ResourceLocation(ThaiDelight.MOD_ID,id),item);
        ModItems.ITEMS.add(item);

    }

    public static Item.Properties foodBowl(FoodProperties foodProperties){
        return new Item.Properties().food(foodProperties).craftRemainder(Items.BOWL).stacksTo(16);
    }

    public static class ModFoodFabric{
        public static final FoodProperties SOMTAM = new FoodProperties.Builder().nutrition(16).saturationMod(0.6F).effect(new MobEffectInstance(EffectsRegistry.NOURISHMENT.get(),6000,0),1.0f).build();
        public static final FoodProperties CRAB_FRIED_RICE = new FoodProperties.Builder().nutrition(16).saturationMod(0.8F).effect(new MobEffectInstance(EffectsRegistry.COMFORT.get(),6000,0),1.0f).build();
        public static final FoodProperties SPICY_MINCED_PORK_SALAD = new FoodProperties.Builder()
                .nutrition(14)
                .saturationMod(0.75F)
                .effect(new MobEffectInstance(EffectsRegistry.NOURISHMENT.get(),5000,0),1.0f).build();

        public static final FoodProperties STIR_FRIED_NOODLE = new FoodProperties.Builder()
                .nutrition(12)
                .saturationMod(0.65F)
                .effect(new MobEffectInstance(EffectsRegistry.COMFORT.get(),5000,0),1.0f).build();
    }
}
