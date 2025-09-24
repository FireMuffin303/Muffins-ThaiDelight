package net.firemuffin303.muffinsthaidelightfabric.registry;


import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.item.DragonflyBottleItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.ArrayList;
import java.util.Map;

import static vectorwing.farmersdelight.common.registry.ModItems.bowlFoodItem;
import static vectorwing.farmersdelight.common.registry.ModItems.drinkItem;

public class ModItems {
    public static final ResourceKey<TerraformBoatType> DURIAN_BOAT_KEY = TerraformBoatTypeRegistry.createKey(ThaiDelight.modid("durian_boat"));
    public static final ResourceKey<TerraformBoatType> COCONUT_BOAT_KEY = TerraformBoatTypeRegistry.createKey(ThaiDelight.modid("coconut_boat"));
    public static final ResourceKey<TerraformBoatType> MANGO_BOAT_KEY = TerraformBoatTypeRegistry.createKey(ThaiDelight.modid("mango_boat"));

    public static final ArrayList<Item> ITEMS = new ArrayList<>();

    public static final Item MORTAR = register("mortar",new BlockItem(ModBlocks.MORTAR,new Item.Properties()));

    //Blocks
    public static final Item LIME_CRATE = register("lime_crate",new BlockItem(ModBlocks.LIME_CRATE,new Item.Properties()));
    public static final Item PEPPER_CRATE = register("pepper_crate",new BlockItem(ModBlocks.PEPPER_CRATE,new Item.Properties()));
    public static final Item RAW_PAPAYA_CRATE = register("raw_papaya_crate",new BlockItem(ModBlocks.RAW_PAPAYA_CRATE,new Item.Properties()));
    public static final Item PAPAYA_CRATE = register("papaya_crate",new BlockItem(ModBlocks.PAPAYA_CRATE,new Item.Properties()));
    public static final Item DURIAN_CRATE = register("durian_crate",new BlockItem(ModBlocks.DURIAN_CRATE,new Item.Properties()));
    public static final Item MANGO_CRATE = register("mango_crate",new BlockItem(ModBlocks.MANGO_CRATE,new Item.Properties()));
    public static final Item COCONUT_CRATE = register("coconut_crate",new BlockItem(ModBlocks.COCONUT_CRATE,new Item.Properties()));
    public static final Item HOLY_BASIL_CRATE = register("holy_basil_crate",new BlockItem(ModBlocks.HOLY_BASIL_CRATE,new Item.Properties()));
    public static final Item BASIL_CRATE = register("basil_crate",new BlockItem(ModBlocks.BASIL_CRATE,new Item.Properties()));

    //Crab
    public static final Item CRAB_SPAWN_EGG = register("flower_crab_spawn_egg",new SpawnEggItem(ModEntityTypes.FLOWER_CRAB,0x93a064,0xac3247,new Item.Properties()));
    public static final Item CRAB_EGG = register("flower_crab_egg", new BlockItem(ModBlocks.CRAB_EGG,new Item.Properties()));
    public static final Item CRAB_BUCKET = register("flower_crab_bucket",new MobBucketItem(ModEntityTypes.FLOWER_CRAB,Fluids.WATER,SoundEvents.BUCKET_EMPTY_FISH,new Item.Properties().stacksTo(1)));
    public static final Item CRAB_MEAT = register("flower_crab",new Item(new Item.Properties().food(ModFood.CRAB)));
    public static final Item COOKED_CRAB_MEAT = register("cooked_flower_crab",new Item(new Item.Properties().food(ModFood.COOKED_CRAB)));

    //Dragonfly
    public static final Item DRAGONFLY_SPAWN_EGG = register("dragonfly_spawn_egg",new SpawnEggItem(ModEntityTypes.DRAGONFLY,0x181d13,0x246011,new Item.Properties()));
    public static final Item DRAGONFLY = register("dragonfly",new Item(new Item.Properties().food(ModFood.DRAGONFLY)));
    public static final Item DRAGONFLY_BOTTLE = register("dragonfly_bottle",new DragonflyBottleItem(new Item.Properties().stacksTo(1)));
    public static final Item COOKED_DRAGONFLY = register("cooked_dragonfly",new Item(new Item.Properties().food(ModFood.COOKED_DRAGONFLY)));

    //Buffalo
    //public static final Item BUFFALO_SPAWN_EGG = register("buffalo_spawn_egg",new SpawnEggItem(ModEntityTypes.BUFFALO,0x343639,0x444444,new Item.Properties()));

    //Bucket
    public static final Item FISH_SAUCE_BOTTLE = register("fish_sauce_bottle",new DrinkableItem(drinkItem().food(ModFood.FISH_SAUCE),true,false)) ;
    public static final Item FERMENTED_FISH = register("fermented_fish",new ConsumableItem(bowlFoodItem(ModFood.FERMENTED_FISH),true,false));
    public static final Item PAPAYA_JUICE = register("papaya_juice",new DrinkableItem(drinkItem().food(ModFood.PAPAYA_JUICE),false,true){
        @Override
        public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
            super.affectConsumer(stack, level, consumer);
            consumer.removeEffect(MobEffects.HUNGER);
        }
    });
    public static final Item LIME_JUICE = register("lime_juice",new DrinkableItem(drinkItem().food(ModFood.LIME_JUICE),false,true){
        @Override
        public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
            super.affectConsumer(stack, level, consumer);
            consumer.removeEffect(MobEffects.BLINDNESS);
        }
    });

    //Crops
    //------------- Lime ---------------
    public static final Item LIME_SAPLING = register("lime_sapling",new BlockItem(ModBlocks.LIME_SAPLING,new Item.Properties()));
    public static final Item LIME = register("lime",new Item(new Item.Properties().food(ModFood.LIME)));
    public static final Item SLICED_LIME = register("lime_slice",new Item(new Item.Properties().food(ModFood.LIME_SLICE)));
    public static final Item LIME_LEAVES = register("lime_leaves",new BlockItem(ModBlocks.LIME_LEAVES,new Item.Properties()));

    public static final Item WILD_PEPPER_CROP = register("wild_pepper",new BlockItem(ModBlocks.WILD_PEPPER_CROP,new Item.Properties()));
    public static final Item PEPPER = register("pepper",new Item(new Item.Properties().food(ModFood.PEPPER)));
    public static final Item PEPPER_SEED = register("pepper_seeds",new ItemNameBlockItem(ModBlocks.PEPPER_CROP,new Item.Properties()){
        @Override
        public void registerBlocks(Map<Block, Item> map, Item item) {
            super.registerBlocks(map, item);
            map.put(ModBlocks.PEPPER_CROP,item);
        }
    });

    //------------------ Durian ------------------
    public static final Item DURIAN_SAPLING = register("durian_sapling",new BlockItem(ModBlocks.DURIAN_SAPLING,new Item.Properties()));
    public static final Item DURIAN_LEAVES = register("durian_leaves",new BlockItem(ModBlocks.DURIAN_LEAVES,new Item.Properties()));
    public static final Item DURIAN_FLOWER = register("durian_flower",new BlockItem(ModBlocks.DURIAN_FLOWER,new Item.Properties()));
    public static final Item DURIAN = register("durian_block",new BlockItem(ModBlocks.DURIAN_BLOCK,new Item.Properties()));
    public static final Item DURIAN_PULP = register("durian_pulp",new Item(new Item.Properties().food(ModFood.DURIAN_PULP)));
    //Durian Woodset item
    public static final Item DURIAN_LOG = register("durian_log",new BlockItem(ModBlocks.DURIAN_LOG,new Item.Properties()));
    public static final Item DURIAN_WOOD = register("durian_wood",new BlockItem(ModBlocks.DURIAN_WOOD,new Item.Properties()));
    public static final Item STRIPPED_DURIAN_LOG = register("stripped_durian_log",new BlockItem(ModBlocks.STRIPPED_DURIAN_LOG,new Item.Properties()));
    public static final Item STRIPPED_DURIAN_WOOD = register("stripped_durian_wood",new BlockItem(ModBlocks.STRIPPED_DURIAN_WOOD,new Item.Properties()));
    public static final Item DURIAN_PLANKS = register("durian_planks",new BlockItem(ModBlocks.DURIAN_PLANKS,new Item.Properties()));
    public static final Item DURIAN_STAIRS = register("durian_stairs",new BlockItem(ModBlocks.DURIAN_STAIRS,new Item.Properties()));
    public static final Item DURIAN_SLAB = register("durian_slab",new BlockItem(ModBlocks.DURIAN_SLAB,new Item.Properties()));
    public static final Item DURIAN_FENCE = register("durian_fence",new BlockItem(ModBlocks.DURIAN_FENCE,new Item.Properties()));
    public static final Item DURIAN_FENCE_GATE = register("durian_fence_gate",new BlockItem(ModBlocks.DURIAN_FENCE_GATE,new Item.Properties()));
    public static final Item DURIAN_DOOR = register("durian_door",new BlockItem(ModBlocks.DURIAN_DOOR,new Item.Properties()));
    public static final Item DURIAN_TRAPDOOR = register("durian_trapdoor",new BlockItem(ModBlocks.DURIAN_TRAPDOOR,new Item.Properties()));
    public static final Item DURIAN_PRESSURE_PLATE = register("durian_pressure_plate",new BlockItem(ModBlocks.DURIAN_PRESSURE_PLATE,new Item.Properties()));
    public static final Item DURIAN_BUTTON = register("durian_button",new BlockItem(ModBlocks.DURIAN_BUTTON,new Item.Properties()));
    public static final Item DURIAN_SIGN = register("durian_sign",new SignItem(new Item.Properties().stacksTo(16),ModBlocks.DURIAN_SIGN,ModBlocks.DURIAN_WALL_SIGN));
    public static final Item DURIAN_HANGING_SIGN = register("durian_hanging_sign",new HangingSignItem(ModBlocks.DURIAN_HANGING_SIGN,ModBlocks.DURIAN_WALL_HANGING_SIGN,new Item.Properties().stacksTo(16)));
    public static final Item DURIAN_CABINET = register("durian_cabinet",new BlockItem(ModBlocks.DURIAN_CABINET,new Item.Properties()));
    public static final Item DURIAN_BOAT = TerraformBoatItemHelper.registerBoatItem(ThaiDelight.modid("durian_boat"),DURIAN_BOAT_KEY,false);
    public static final Item DURIAN_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ThaiDelight.modid("durian_chest_boat"),DURIAN_BOAT_KEY,true);

    //------------------ 🥥 COCONUT 🥥 ------------------
    public static final Item COCONUT_SAPLING = register("coconut_sapling",new BlockItem(ModBlocks.COCONUT_SAPLING,new Item.Properties()));
    public static final Item COCONUT_LEAVES = register("coconut_leaves",new BlockItem(ModBlocks.COCONUT_LEAF,new Item.Properties()));
    //COCONUT Woodset item
    public static final Item COCONUT_LOG = register("coconut_log",new BlockItem(ModBlocks.COCONUT_LOG,new Item.Properties()));
    public static final Item COCONUT_WOOD = register("coconut_wood",new BlockItem(ModBlocks.COCONUT_WOOD,new Item.Properties()));
    public static final Item STRIPPED_COCONUT_LOG = register("stripped_coconut_log",new BlockItem(ModBlocks.STRIPPED_COCONUT_LOG,new Item.Properties()));
    public static final Item STRIPPED_COCONUT_WOOD = register("stripped_coconut_wood",new BlockItem(ModBlocks.STRIPPED_COCONUT_WOOD,new Item.Properties()));
    public static final Item COCONUT_PLANKS = register("coconut_planks",new BlockItem(ModBlocks.COCONUT_PLANKS,new Item.Properties()));
    public static final Item COCONUT_STAIRS = register("coconut_stairs",new BlockItem(ModBlocks.COCONUT_STAIRS,new Item.Properties()));
    public static final Item COCONUT_SLAB = register("coconut_slab",new BlockItem(ModBlocks.COCONUT_SLAB,new Item.Properties()));
    public static final Item COCONUT_FENCE = register("coconut_fence",new BlockItem(ModBlocks.COCONUT_FENCE,new Item.Properties()));
    public static final Item COCONUT_FENCE_GATE = register("coconut_fence_gate",new BlockItem(ModBlocks.COCONUT_FENCE_GATE,new Item.Properties()));
    public static final Item COCONUT_DOOR = register("coconut_door",new BlockItem(ModBlocks.COCONUT_DOOR,new Item.Properties()));
    public static final Item COCONUT_TRAPDOOR = register("coconut_trapdoor",new BlockItem(ModBlocks.COCONUT_TRAPDOOR,new Item.Properties()));
    public static final Item COCONUT_PRESSURE_PLATE = register("coconut_pressure_plate",new BlockItem(ModBlocks.COCONUT_PRESSURE_PLATE,new Item.Properties()));
    public static final Item COCONUT_BUTTON = register("coconut_button",new BlockItem(ModBlocks.COCONUT_BUTTON,new Item.Properties()));
    public static final Item COCONUT_SIGN = register("coconut_sign",new SignItem(new Item.Properties().stacksTo(16),ModBlocks.COCONUT_SIGN,ModBlocks.COCONUT_WALL_SIGN));
    public static final Item COCONUT_HANGING_SIGN = register("coconut_hanging_sign",new HangingSignItem(ModBlocks.COCONUT_HANGING_SIGN,ModBlocks.COCONUT_WALL_HANGING_SIGN,new Item.Properties().stacksTo(16)));
    public static final Item COCONUT_CABINET = register("coconut_cabinet",new BlockItem(ModBlocks.COCONUT_CABINET,new Item.Properties()));
    public static final Item COCONUT_BOAT = TerraformBoatItemHelper.registerBoatItem(ThaiDelight.modid("coconut_boat"),COCONUT_BOAT_KEY,false);
    public static final Item COCONUT_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ThaiDelight.modid("coconut_chest_boat"),COCONUT_BOAT_KEY,true);

    public static final Item COCONUT = register("coconut",new Item(new Item.Properties()));


    //------------------ 🥭 MANGO 🥭 --------------------
    public static final Item MANGO_SAPLING = register("mango_sapling",new BlockItem(ModBlocks.MANGO_SAPLING,new Item.Properties()));
    public static final Item MANGO_LEAVES = register("mango_leaves",new BlockItem(ModBlocks.MANGO_LEAVES,new Item.Properties()));

    public static final Item MANGO_LOG = register("mango_log",new BlockItem(ModBlocks.MANGO_LOG,new Item.Properties()));
    public static final Item MANGO_WOOD = register("mango_wood",new BlockItem(ModBlocks.MANGO_WOOD,new Item.Properties()));
    public static final Item STRIPPED_MANGO_LOG = register("stripped_mango_log",new BlockItem(ModBlocks.STRIPPED_MANGO_LOG,new Item.Properties()));
    public static final Item STRIPPED_MANGO_WOOD = register("stripped_mango_wood",new BlockItem(ModBlocks.STRIPPED_MANGO_WOOD,new Item.Properties()));
    public static final Item MANGO_PLANKS = register("mango_planks",new BlockItem(ModBlocks.MANGO_PLANKS,new Item.Properties()));
    public static final Item MANGO_STAIRS = register("mango_stairs",new BlockItem(ModBlocks.MANGO_STAIRS,new Item.Properties()));
    public static final Item MANGO_SLAB = register("mango_slab",new BlockItem(ModBlocks.MANGO_SLAB,new Item.Properties()));
    public static final Item MANGO_FENCE = register("mango_fence",new BlockItem(ModBlocks.MANGO_FENCE,new Item.Properties()));
    public static final Item MANGO_FENCE_GATE = register("mango_fence_gate",new BlockItem(ModBlocks.MANGO_FENCE_GATE,new Item.Properties()));
    public static final Item MANGO_DOOR = register("mango_door",new BlockItem(ModBlocks.MANGO_DOOR,new Item.Properties()));
    public static final Item MANGO_TRAPDOOR = register("mango_trapdoor",new BlockItem(ModBlocks.MANGO_TRAPDOOR,new Item.Properties()));
    public static final Item MANGO_PRESSURE_PLATE = register("mango_pressure_plate",new BlockItem(ModBlocks.MANGO_PRESSURE_PLATE,new Item.Properties()));
    public static final Item MANGO_BUTTON = register("mango_button",new BlockItem(ModBlocks.MANGO_BUTTON,new Item.Properties()));
    public static final Item MANGO_SIGN = register("mango_sign",new SignItem(new Item.Properties().stacksTo(16),ModBlocks.MANGO_SIGN,ModBlocks.MANGO_WALL_SIGN));
    public static final Item MANGO_HANGING_SIGN = register("mango_hanging_sign",new HangingSignItem(ModBlocks.MANGO_HANGING_SIGN,ModBlocks.MANGO_WALL_HANGING_SIGN,new Item.Properties().stacksTo(16)));
    public static final Item MANGO_CABINET = register("mango_cabinet",new BlockItem(ModBlocks.MANGO_CABINET,new Item.Properties()));
    public static final Item MANGO_BOAT = TerraformBoatItemHelper.registerBoatItem(ThaiDelight.modid("mango_boat"),MANGO_BOAT_KEY,false);
    public static final Item MANGO_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ThaiDelight.modid("mango_chest_boat"),MANGO_BOAT_KEY,true);

    public static final Item MANGO = register("mango",new Item(new Item.Properties()));
    public static final Item MANGO_SLICE = register("mango_slice",new Item(new Item.Properties()));

    //----- 🍐 Papaya 🍐 -----------------------------------
    public static final Item PAPAYA = register("papaya",new Item(new Item.Properties().food(ModFood.PAPAYA)));
    public static final Item SLICED_PAPAYA = register("papaya_slice",new Item(new Item.Properties().food(ModFood.SLICED_PAPAYA)));
    public static final Item RAW_PAPAYA = register("raw_papaya",new Item(new Item.Properties().food(ModFood.RAW_PAPAYA)));
    public static final Item RAW_PAPAYA_SLICE = register("raw_papaya_slice",new Item(new Item.Properties().food(ModFood.SLICED_UNRIPE_PAPAYA)));
    public static final Item PAPAYA_LOG = register("papaya_log",new BlockItem(ModBlocks.PAPAYA_LOG,new Item.Properties()));
    public static final Item STRIPPED_PAPAYA_LOG = register("stripped_papaya_log",new BlockItem(ModBlocks.STRIPPED_PAPAYA_LOG,new Item.Properties()));
    public static final Item PAPAYA_WOOD = register("papaya_wood",new BlockItem(ModBlocks.PAPAYA_WOOD,new Item.Properties()));
    public static final Item STRIPPED_PAPAYA_WOOD = register("stripped_papaya_wood",new BlockItem(ModBlocks.STRIPPED_PAPAYA_WOOD,new Item.Properties()));
    public static final Item PAPAYA_LEAVES = register("papaya_leaves",new BlockItem(ModBlocks.PAPAYA_LEAVES,new Item.Properties()));
    public static final Item PAPAYA_SAPLING = register("papaya_sapling",new BlockItem(ModBlocks.PAPAYA_SAPLING,new Item.Properties()));
    public static final Item PAPAYA_SEEDS = register("papaya_seeds",new ItemNameBlockItem(ModBlocks.PAPAYA_CROP,new Item.Properties()){
        @Override
        public void registerBlocks(Map<Block, Item> map, Item item) {
            super.registerBlocks(map, item);
            map.put(ModBlocks.PAPAYA_CROP,item);
        }
    });

    // 🌿 HOLY BASIL 🌿
    public static final Item HOLY_BASIL_SAPLING = register("holy_basil_sapling",new ItemNameBlockItem(ModBlocks.HOLY_BASIL,new Item.Properties()){
        @Override
        public void registerBlocks(Map<Block, Item> map, Item item) {
            super.registerBlocks(map, item);
            map.put(ModBlocks.HOLY_BASIL,item);
        }
    });
    public static final Item HOLY_BASIL = register("holy_basil",new Item(new Item.Properties()));

    // 🌿 BASIL 🌿
    public static final Item BASIL_SAPLING = register("basil_sapling",new ItemNameBlockItem(ModBlocks.BASIL,new Item.Properties()){
        @Override
        public void registerBlocks(Map<Block, Item> map, Item item) {
            super.registerBlocks(map, item);
            map.put(ModBlocks.BASIL,item);
        }
    });
    public static final Item BASIL = register("basil",new Item(new Item.Properties()));

    //--- 🍔 Food 🍔---
    public static final Item SOMTAM_FEAST = register("somtam_feast",new BlockItem(ModBlocks.SOMTAM_FEAST,new Item.Properties()));
    public static final Item SOMTAM = register("somtam",new ConsumableItem(bowlFoodItem(ModFood.SOMTAM)));

    public static final Item LARB_FEAST = register("larb_feast",new BlockItem(ModBlocks.LARB_FEAST,new Item.Properties()));
    public static final Item LARB = register("larb",new ConsumableItem(bowlFoodItem(ModFood.LARB)));
    public static final Item CRAB_FRIED_RICE_FEAST = register("crab_fried_rice_feast",new BlockItem(ModBlocks.CRAB_FRIED_RICE_FEAST,new Item.Properties()));
    public static final Item CRAB_FRIED_RICE = register("crab_fried_rice",new ConsumableItem(bowlFoodItem(ModFood.CRAB_FRIED_RICE)));
    public static final Item STIR_FRIED_NOODLE = register("stir_fried_noodle",new ConsumableItem(bowlFoodItem(ModFood.STIR_FRIED_NOODLE)));

    public static final Item FRIED_DURIAN = register("fried_durian",new Item(new Item.Properties().food(ModFood.FRIED_DURIAN)));
    public static final Item MANGO_STICKY_RICE = register("mango_sticky_rice",new Item(new Item.Properties().food(ModFood.FRIED_DURIAN)));
    public static final Item COCONUT_MILK_BOTTLE = register("coconut_milk_bottle",new Item(new Item.Properties()));
    public static final Item COCONUT_SLICE = register("coconut_slice",new ConsumableItem(new Item.Properties().craftRemainder(Items.BOWL).food(ModFood.COCONUT_MEAT)));
    public static final Item PHAT_KAPHRAO = register("phat_kaphrao", new ConsumableItem(bowlFoodItem(ModFood.PHAT_KAPHRAO)));

    public static Item register(String id,Item item){
        ITEMS.add(item);
        return Registry.register(BuiltInRegistries.ITEM,new ResourceLocation(ThaiDelight.MOD_ID,id),item);
    }

    public static void init() {
    }



    //----------------------------------------------------------------------------------------------
    public static class ModFood{
        public static final FoodProperties CRAB = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).meat().build();
        public static final FoodProperties COOKED_CRAB = new FoodProperties.Builder().nutrition(8).saturationMod(0.5F).meat().build();

        public static final FoodProperties COOKED_DRAGONFLY = new FoodProperties.Builder().nutrition(3).saturationMod(0.2f).alwaysEat().build();
        public static final FoodProperties DRAGONFLY = new FoodProperties.Builder().nutrition(2).saturationMod(0.1f).alwaysEat().effect(new MobEffectInstance(MobEffects.HUNGER,10*20,0),0.8f).effect(new MobEffectInstance(MobEffects.CONFUSION,10*20,0),0.8f).build();

        public static final FoodProperties LIME = new FoodProperties.Builder().nutrition(4).saturationMod(0.2F).build();
        public static final FoodProperties LIME_SLICE = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F)
                .build();

        public static final FoodProperties PEPPER = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).alwaysEat().fast().build();

        public static final FoodProperties SLICED_UNRIPE_PAPAYA = new FoodProperties.Builder().nutrition(1).saturationMod(0.1F).alwaysEat().fast().build();
        public static final FoodProperties SLICED_PAPAYA = new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).build();
        public static final FoodProperties PAPAYA = new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).build();
        public static final FoodProperties RAW_PAPAYA = new FoodProperties.Builder().nutrition(4).saturationMod(0.2F).build();

        public static final FoodProperties DURIAN_PULP = new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build();
        public static final FoodProperties FRIED_DURIAN = new FoodProperties.Builder().alwaysEat().fast().nutrition(3).saturationMod(0.4f).build();

        public static final FoodProperties COCONUT_MEAT = new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build();

        public static final FoodProperties FISH_SAUCE = new FoodProperties.Builder().alwaysEat().effect(new MobEffectInstance(MobEffects.HUNGER,200,0),1.0f).build();
        public static final FoodProperties SEAFOOD_SAUCE = new FoodProperties.Builder().alwaysEat().effect(new MobEffectInstance(MobEffects.WATER_BREATHING,200,0),1.0f).build();
        public static final FoodProperties PAPAYA_JUICE = new FoodProperties.Builder().alwaysEat().build();
        public static final FoodProperties LIME_JUICE = new FoodProperties.Builder().alwaysEat().build();

        public static final FoodProperties SOMTAM = new FoodProperties.Builder()
                .nutrition(14)
                .saturationMod(0.75F)
                .effect(new MobEffectInstance(ModEffects.NOURISHMENT.get(),6000,0),1.0f).build();
        public static final FoodProperties CRAB_FRIED_RICE = new FoodProperties.Builder()
                .nutrition(14)
                .saturationMod(0.6F)
                .effect(new MobEffectInstance(ModEffects.COMFORT.get(),6000,0),1.0f).build();

        public static final FoodProperties LARB = new FoodProperties.Builder()
                .nutrition(14)
                .saturationMod(0.75F)
                .effect(new MobEffectInstance(ModEffects.NOURISHMENT.get(),6000,0),1.0f).build();

        public static final FoodProperties STIR_FRIED_NOODLE = new FoodProperties.Builder()
                .nutrition(12)
                .saturationMod(0.55F)
                .effect(new MobEffectInstance(ModEffects.COMFORT.get(),5000,0),1.0f).build();

        public static final FoodProperties PHAT_KAPHRAO = new FoodProperties.Builder()
                .nutrition(12)
                .saturationMod(0.8F)
                .effect(new MobEffectInstance(ModEffects.NOURISHMENT.get(),3600,0),1.0f).build();

        public static final FoodProperties FERMENTED_FISH = new FoodProperties.Builder().alwaysEat().effect(new MobEffectInstance(ModMobEffects.STINKY,10*20),0.5f).build();
    }

}
