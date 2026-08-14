package net.firemuffin303.thaidelight.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;

import java.util.Map;
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
    public static Holder<MobEffect> getComfort(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Holder<MobEffect> getNourishmentEffect(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Item> getTreeBarkItem(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void setSpicyTime(int value, LivingEntity livingEntity){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void addSpicyTime(int value, LivingEntity livingEntity){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static int getSpicyTime(LivingEntity livingEntity){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerBlockBurn(Supplier<Block> blockSupplier,int burn,int spread){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static ModUtils.DurianComponentSupplier getDurianHeatComponent(LivingEntity livingEntity){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void setDurianHeat(boolean value,LivingEntity livingEntity){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void addDurianHeatTime(int i,LivingEntity livingEntity){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static UseAnim getDurianCatcherUseAnim(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static HumanoidModel.ArmPose getSackShoulderPose(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static HumanoidModel.ArmPose getDurianCatcherHoldArmPose(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static HumanoidModel.ArmPose getDurianCatcherSwingArmPose(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerStrippable(Map<Block, Block> map){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void playDurianCatchSound(ServerLevel serverLevel, Vec3 vec3, BlockPos blockPos){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static PathType debugPathType(BlockState state, BlockGetter world, BlockPos pos, boolean neighbor){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static ModelResourceLocation createModelResourceLocation(ResourceLocation resourceLocation){
        throw new AssertionError();
    }
}
