package net.firemuffin303.thaidelight.util.forge;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModSounds;

import java.util.function.Supplier;

public class PlatformUtilImpl {

    public static TagKey<Item> shearTag() {
        return Tags.Items.SHEARS;
    }

    public static Block richSoilBlock() {
        return ModBlocks.RICH_SOIL.get();
    }

    public static Block richSoilFarmBlock() {
        return ModBlocks.RICH_SOIL_FARMLAND.get();
    }

    public static Item.Properties bowlFoodItem(FoodProperties foodProperties) {
        return ModItems.bowlItem(foodProperties);
    }

    public static Supplier<Block> cabinetBlock(BlockBehaviour.Properties properties) {
        return () -> new CabinetBlock(properties);
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
        return RecipeBookType.CRAFTING;
    }

    public static Supplier<MobEffect> getComfort() {
        return RegistryObject.create(new ResourceLocation("farmersdelight", "comfort"),ForgeRegistries.MOB_EFFECTS);
    }

    public static Supplier<MobEffect> getNourishmentEffect() {
        return RegistryObject.create(new ResourceLocation("farmersdelight","nourishment"),ForgeRegistries.MOB_EFFECTS);
    }
}
