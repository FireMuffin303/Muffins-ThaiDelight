package net.firemuffin303.thaidelight.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

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

    @ExpectPlatform
    public static Block farmerDelightRope(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean tomatoVineConfig(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static String defaultTomatoVineConfig(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static SoundEvent tomatoPickSound(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static RecipeBookType getMortarBookType(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<MobEffect> getComfort(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<MobEffect> getNourishmentEffect(){
        throw new AssertionError();
    }
}
