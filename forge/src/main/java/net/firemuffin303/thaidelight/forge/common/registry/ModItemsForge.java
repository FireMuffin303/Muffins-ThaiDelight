package net.firemuffin303.thaidelight.forge.common.registry;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.forge.ThaiDelightForge;
import net.firemuffin303.thaidelight.forge.common.block.SomtamFeastBlock;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class ModItemsForge {
    public static final RegistryObject<Item> SOMTAM_BLOCK = ThaiDelightForge.ITEMS.register("somtam_block",() -> new BlockItem(ModBlocksForge.SOMTAM_BLOCK.get(),new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SOMTAM = ThaiDelightForge.ITEMS.register("somtam",() -> new ConsumableItem(vectorwing.farmersdelight.common.registry.ModItems.bowlFoodItem(ModItems.ModFood.SOMTAM),true));

    public static final RegistryObject<Item> CRAB_FRIED_RICE_BLOCK = ThaiDelightForge.ITEMS.register("crab_fried_rice_block",() -> new BlockItem(ModBlocksForge.CRAB_FRIED_RICE_BLOCK.get(),new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CRAB_FRIED_RICE = ThaiDelightForge.ITEMS.register("crab_fried_rice",() -> new ConsumableItem(vectorwing.farmersdelight.common.registry.ModItems.bowlFoodItem(ModFoodForge.CRAB_FRIED_RICE),true));


    public static void init(){
    }

    static class ModFoodForge{
        public static final FoodProperties CRAB_FRIED_RICE = (new FoodProperties.Builder()).nutrition(18).saturationMod(0.8F).effect(() -> {
            return new MobEffectInstance((MobEffect)ModEffects.COMFORT.get(), 6000, 0);
        }, 1.0F).build();
    }
}
