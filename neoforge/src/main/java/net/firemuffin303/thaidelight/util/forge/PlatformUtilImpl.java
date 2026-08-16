package net.firemuffin303.thaidelight.util.forge;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.neoforge.common.attachment.DurianHeatAttachment;
import net.firemuffin303.thaidelight.neoforge.common.attachment.ModAttachments;
import net.firemuffin303.thaidelight.neoforge.mixin.accessor.AxeItemAccessor;
import net.firemuffin303.thaidelight.network.ModLevelEventPacket;
import net.firemuffin303.thaidelight.util.ModUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.network.PacketDistributor;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModSounds;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PlatformUtilImpl {

    public static TagKey<Item> shearTag() {
        return Tags.Items.TOOLS_SHEAR;
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
        return ModSounds.BLOCK_TOMATOES_PICK_TOMATOES.get();
    }

    public static RecipeBookType getMortarBookType() {
        return RecipeBookType.valueOf("MUFFINS_THAIDELIGHT_MORTAR_RECIPE_BOOK_TYPE");
    }

    public static Holder<MobEffect> getComfort() {
        return BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.fromNamespaceAndPath("farmersdelight", "comfort")).orElseThrow();
    }

    public static Holder<MobEffect> getNourishmentEffect() {
        return BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.fromNamespaceAndPath("farmersdelight","nourishment")).orElseThrow();
    }

    public static Supplier<Item> getTreeBarkItem() {
        return () -> BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("farmersdelight","tree_bark"));
    }

    public static UseAnim getDurianCatcherUseAnim() {
        return UseAnim.NONE;
    }

    public static HumanoidModel.ArmPose getSackShoulderPose() {
        return HumanoidModel.ArmPose.valueOf("MUFFINS_THAIDELIGHT_SACK_SHOULDER_HOLD");
    }

    public static HumanoidModel.ArmPose getDurianCatcherHoldArmPose() {
        return HumanoidModel.ArmPose.valueOf("MUFFINS_THAIDELIGHT_CATCHING_BAG_HOLD");
    }

    public static HumanoidModel.ArmPose getDurianCatcherSwingArmPose() {
        return HumanoidModel.ArmPose.valueOf("MUFFINS_THAIDELIGHT_CATCHING_BAG_SWING");
    }

    public static void setSpicyTime(int value, LivingEntity livingEntity) {
        livingEntity.getData(ModAttachments.SPICY).setTime(value);

    }

    public static void addSpicyTime(int value, LivingEntity livingEntity) {
        livingEntity.getData(ModAttachments.SPICY).addTime(value);
    }

    public static int getSpicyTime(LivingEntity livingEntity) {
        if(livingEntity instanceof Player){
            return livingEntity.getData(ModAttachments.SPICY).getTimer();
        }
        return 0;
    }

    public static ModUtils.DurianComponentSupplier getDurianHeatComponent(LivingEntity livingEntity) {
        DurianHeatAttachment durianHeatAttachment = livingEntity.getData(ModAttachments.DURIAN_HEAT);
        return new ModUtils.DurianComponentSupplier(durianHeatAttachment.getTimer(),durianHeatAttachment.isHeatUp());
    }

    public static void setDurianHeat(boolean value, LivingEntity livingEntity) {
        livingEntity.getData(ModAttachments.DURIAN_HEAT).setHeat(value);
    }

    public static void addDurianHeatTime(int i, LivingEntity livingEntity) {
       DurianHeatAttachment durianHeatAttachment = livingEntity.getData(ModAttachments.DURIAN_HEAT);
       durianHeatAttachment.setTimer(durianHeatAttachment.getTimer() + i);
    }

    public static void registerStrippable(Map<Block, Block> map) {
        Map<Block,Block> map1 = new HashMap<>();
        map1.putAll(AxeItemAccessor.getStrippables());
        map1.putAll(map);
        AxeItemAccessor.setStrippables(map1);
    }

    public static void playDurianCatchSound(ServerLevel serverLevel, Vec3 vec3, BlockPos blockPos) {
        PacketDistributor.sendToPlayersNear(serverLevel,null, vec3.x(), vec3.y(), vec3.z(), 32,new ModLevelEventPacket((byte) 1,blockPos));
    }

    public static ModelResourceLocation createModelResourceLocation(ResourceLocation resourceLocation) {
        return ModelResourceLocation.standalone(resourceLocation);
    }

}
