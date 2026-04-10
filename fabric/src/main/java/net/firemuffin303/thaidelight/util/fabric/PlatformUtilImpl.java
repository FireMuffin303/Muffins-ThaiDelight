package net.firemuffin303.thaidelight.util.fabric;

import io.github.fabricators_of_create.porting_lib.tags.Tags;
import net.firemuffin303.thaidelight.asm.ModASMEarlyRiser;
import net.firemuffin303.thaidelight.client.ThaiDelightClientFabric;
import net.firemuffin303.thaidelight.common.cardinalcomponents.DurianHeatComponent;
import net.firemuffin303.thaidelight.common.registry.ModCardinalComponents;
import net.firemuffin303.thaidelight.util.ModUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.Configuration;
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

    public static Supplier<Item> getTreeBarkItem() {
        return ModItems.TREE_BARK;
    }

    public static void setSpicyTime(int value, LivingEntity livingEntity) {
        ModCardinalComponents.SPICY_HEAT.get(livingEntity).setTime(value);
    }

    public static int getSpicyTime(LivingEntity livingEntity) {
        return ModCardinalComponents.SPICY_HEAT.get(livingEntity).timer;
    }

    public static ModUtils.DurianComponentSupplier getDurianHeatComponent(LivingEntity livingEntity) {
        DurianHeatComponent durianHeatComponent = ModCardinalComponents.DURIAN_HEAT.get(livingEntity);
        return new ModUtils.DurianComponentSupplier(durianHeatComponent.timer,durianHeatComponent.isHeatedUp);
    }

    public static void setDurianHeat(boolean value,LivingEntity livingEntity) {
        ModCardinalComponents.DURIAN_HEAT.get(livingEntity).setHeatedUp(value);
    }


    public static void addSpicyTime(int value, LivingEntity livingEntity) {
        ModCardinalComponents.SPICY_HEAT.get(livingEntity).addTime(value);
    }

    public static void addDurianHeatTime(int i, LivingEntity livingEntity) {
        ModCardinalComponents.DURIAN_HEAT.get(livingEntity).addTimer(i);
    }

    public static UseAnim getDurianCatcherUseAnim() {
        return ModASMEarlyRiser.getDurianCatcherUseAnim();
    }

    public static HumanoidModel.ArmPose getSackShoulderPose() {
        return ModASMEarlyRiser.getSackShoulderArmPose();
    }

    public static HumanoidModel.ArmPose getDurianCatcherHoldArmPose() {
        return ModASMEarlyRiser.getDurianCatcherHoldArmPose();
    }

    public static HumanoidModel.ArmPose getDurianCatcherSwingArmPose() {
        return ModASMEarlyRiser.getDurianCatcherSwingArmPose();
    }


}

