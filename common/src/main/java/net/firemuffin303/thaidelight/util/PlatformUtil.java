package net.firemuffin303.thaidelight.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.tags.TagKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class PlatformUtil {

    @ExpectPlatform
    public static Item.Properties bowlFoodItem(FoodProperties foodProperties){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static TagKey<Item> shearTag(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Block richSoilBlock(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Block richSoilFarmBlock(){
        throw new AssertionError();
    }
}
