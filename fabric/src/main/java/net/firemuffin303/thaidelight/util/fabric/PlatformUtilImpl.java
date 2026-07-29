package net.firemuffin303.thaidelight.util.fabric;

import com.mojang.logging.LogUtils;
import io.github.fabricators_of_create.porting_lib.tags.Tags;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.firemuffin303.thaidelight.asm.ModASMEarlyRiser;
import net.firemuffin303.thaidelight.client.ThaiDelightClientFabric;
import net.firemuffin303.thaidelight.common.cardinalcomponents.DurianHeatComponent;
import net.firemuffin303.thaidelight.common.registry.ModCardinalComponents;
import net.firemuffin303.thaidelight.integration.midnightLib.ThaiDelightConfig;
import net.firemuffin303.thaidelight.network.ModLevelEventPacket;
import net.firemuffin303.thaidelight.util.ModUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModEffects;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModSounds;

import java.util.Map;
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

    public static Holder<MobEffect> getComfort() {
        return ModEffects.COMFORT;
    }

    public static Holder<MobEffect> getNourishmentEffect() {
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

    public static void registerStrippable(Map<Block, Block> map) {
        map.forEach(StrippableBlockRegistry::register);
    }

    public static void playDurianCatchSound(ServerLevel serverLevel, Vec3 vec3, BlockPos blockPos) {
        for(ServerPlayer serverPlayer : PlayerLookup.around(serverLevel,vec3,32)){
            ServerPlayNetworking.send(serverPlayer,new ModLevelEventPacket((byte) 1,blockPos));
        }
    }


    public static void registerBlockBurn(Supplier<Block> blockSupplier, int burn, int spread) {
        FlammableBlockRegistry.getDefaultInstance().add(blockSupplier.get(),burn,spread);
    }


}

