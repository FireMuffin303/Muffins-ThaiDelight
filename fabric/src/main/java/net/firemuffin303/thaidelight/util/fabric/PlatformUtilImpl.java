package net.firemuffin303.thaidelight.util.fabric;

import io.github.fabricators_of_create.porting_lib.tags.Tags;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.firemuffin303.thaidelight.client.ThaiDelightClientFabric;
import net.firemuffin303.thaidelight.common.block.vegetation.pepper.FabricBuddingPepperBlock;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModEffects;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModSounds;

import java.util.function.Supplier;

public class PlatformUtilImpl {

    public static Item.Properties bowlFoodItem(FoodProperties foodProperties) {
        return ModItems.bowlFoodItem(foodProperties);
    }

    public static TagKey<Item> shearTag() {
        return Tags.Items.SHEARS;
    }

    public static Block richSoilBlock() {
        return ModBlocks.RICH_SOIL.get();
    }

    public static Block richSoilFarmBlock() {
        return ModBlocks.RICH_SOIL_FARMLAND.get();
    }

    public static Block farmerDelightRope() {
        return ModBlocks.ROPE.get();
    }

    public static boolean tomatoVineConfig() {
        return Configuration.ENABLE_TOMATO_VINE_CLIMBING_TAGGED_ROPES.get();
    }

    public static String defaultTomatoVineConfig() {
        return Configuration.DEFAULT_TOMATO_VINE_ROPE.get();
    }

    public static SoundEvent tomatoPickSound() {
        return ModSounds.ITEM_TOMATO_PICK_FROM_BUSH.get();
    }

    public static RecipeBookType getMortarBookType() {
        return ThaiDelightClientFabric.MORTAR_RECIPE_BOOK_TYPE;
    }

    public static Supplier<MobEffect> getComfort() {
        return ModEffects.COMFORT;
    }

    public static Supplier<MobEffect> getNourishmentEffect() {
        return ModEffects.NOURISHMENT;
    }

}

