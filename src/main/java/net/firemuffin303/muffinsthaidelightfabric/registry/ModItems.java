package net.firemuffin303.muffinsthaidelightfabric.registry;


import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.item.CoconutItem;
import net.firemuffin303.muffinsthaidelightfabric.common.item.DragonflyBottleItem;
import net.firemuffin303.muffinsthaidelightfabric.common.item.DyeableItem;
import net.firemuffin303.muffinsthaidelightfabric.common.item.SackItem;
import net.firemuffin303.muffinsthaidelightfabric.common.item.papaya.PapayaFlowerItem;
import net.firemuffin303.muffinsthaidelightfabric.util.CommonEvents;
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
import net.minecraft.world.level.block.Blocks;
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

    public static final ArrayList<Item> FLAT_ITEMS = new ArrayList<>();


    public static final Item MORTAR = register("mortar",new BlockItem(ModBlocks.MORTAR,new Item.Properties()));
    public static final Item SACK = register("sack",new SackItem(new Item.Properties().stacksTo(1)));

    //Blocks
    public static final Item LIME_CRATE = register("lime_crate",new BlockItem(ModBlocks.LIME_CRATE,new Item.Properties()));
    public static final Item PEPPER_CRATE = register("pepper_crate",new BlockItem(ModBlocks.PEPPER_CRATE,new Item.Properties()));
    public static final Item RAW_PAPAYA_CRATE = register("raw_papaya_crate",new BlockItem(ModBlocks.RAW_PAPAYA_CRATE,new Item.Properties()));
    public static final Item PAPAYA_CRATE = register("papaya_crate",new BlockItem(ModBlocks.PAPAYA_CRATE,new Item.Properties()));
    public static final Item MANGO_CRATE = register("mango_crate",new BlockItem(ModBlocks.MANGO_CRATE,new Item.Properties()));
    //public static final Item COCONUT_CRATE = register("coconut_crate",new BlockItem(ModBlocks.COCONUT_CRATE,new Item.Properties()));
    public static final Item HOLY_BASIL_CRATE = register("holy_basil_crate",new BlockItem(ModBlocks.HOLY_BASIL_CRATE,new Item.Properties()));
    public static final Item BASIL_CRATE = register("basil_crate",new BlockItem(ModBlocks.BASIL_CRATE,new Item.Properties()));
    public static final Item BAMBOO_SHOOT_CRATE = register("bamboo_shoot_crate",new BlockItem(ModBlocks.BAMBOO_SHOOT_CRATE,new Item.Properties()));
    public static final Item BUTTERFLY_PEA_CRATE = register("butterfly_pea_crate",new BlockItem(ModBlocks.BUTTERFLY_PEA_CRATE,new Item.Properties()));

    //Crab
    public static final Item CRAB_SPAWN_EGG = register("flower_crab_spawn_egg",new SpawnEggItem(ModEntityTypes.FLOWER_CRAB,0x93a064,0xac3247,new Item.Properties()));
    public static final Item CRAB_EGG = register("flower_crab_egg", new BlockItem(ModBlocks.CRAB_EGG,new Item.Properties()));
    public static final Item CRAB_BUCKET = registerFlatItem("flower_crab_bucket",new MobBucketItem(ModEntityTypes.FLOWER_CRAB,Fluids.WATER,SoundEvents.BUCKET_EMPTY_FISH,new Item.Properties().stacksTo(1)));
    public static final Item CRAB_MEAT = registerFlatItem("flower_crab",new Item(new Item.Properties().food(ModFood.CRAB)));
    public static final Item COOKED_CRAB_MEAT = registerFlatItem("cooked_flower_crab",new Item(new Item.Properties().food(ModFood.COOKED_CRAB)));

    //Dragonfly
    public static final Item DRAGONFLY_SPAWN_EGG = register("dragonfly_spawn_egg",new SpawnEggItem(ModEntityTypes.DRAGONFLY,0x181d13,0x246011,new Item.Properties()));
    public static final Item DRAGONFLY = registerFlatItem("dragonfly",new Item(new Item.Properties().food(ModFood.DRAGONFLY)));
    public static final Item DRAGONFLY_BOTTLE = register("dragonfly_bottle",new DragonflyBottleItem(new Item.Properties().stacksTo(1)));
    public static final Item COOKED_DRAGONFLY = registerFlatItem("cooked_dragonfly",new Item(new Item.Properties().food(ModFood.COOKED_DRAGONFLY)));

    //Buffalo
    //public static final Item BUFFALO_SPAWN_EGG = register("buffalo_spawn_egg",new SpawnEggItem(ModEntityTypes.BUFFALO,0x343639,0x444444,new Item.Properties()));

    //Bucket
    public static final Item FISH_SAUCE_BOTTLE = registerFlatItem("fish_sauce_bottle",new DrinkableItem(drinkItem().food(ModFood.FISH_SAUCE),true,false)) ;
    public static final Item FERMENTED_FISH = registerFlatItem("fermented_fish",new ConsumableItem(bowlFoodItem(ModFood.FERMENTED_FISH),true,false));
    public static final Item PAPAYA_JUICE = registerFlatItem("papaya_juice",new DrinkableItem(drinkItem().food(ModFood.PAPAYA_JUICE),false,true){
        @Override
        public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
            super.affectConsumer(stack, level, consumer);
            consumer.removeEffect(MobEffects.HUNGER);
        }
    });
    public static final Item LIME_JUICE = registerFlatItem("lime_juice",new DrinkableItem(drinkItem().food(ModFood.LIME_JUICE),false,true){
        @Override
        public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
            super.affectConsumer(stack, level, consumer);
            consumer.removeEffect(MobEffects.BLINDNESS);
        }
    });

    public static final Item HONEY_LIME_JUICE = registerFlatItem("honey_lime_juice",new DrinkableItem(drinkItem().food(ModFood.HONEY_LIME_JUICE),false,true){
        @Override
        public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
            super.affectConsumer(stack, level, consumer);
            consumer.removeEffect(MobEffects.BLINDNESS);
            consumer.removeEffect(MobEffects.POISON);
        }
    });

    public static final Item COCONUT_WATER = registerFlatItem("coconut_water",new DrinkableItem(drinkItem().food(ModFood.COCONUT_WATER),false,true){
        @Override
        public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
            super.affectConsumer(stack, level, consumer);
            consumer.removeEffect(MobEffects.WEAKNESS);
        }
    });

    //Crops
    //------------- Lime ---------------
    public static final Item LIME_SAPLING = register("lime_sapling",new BlockItem(ModBlocks.LIME_SAPLING,new Item.Properties()));
    public static final Item LIME = registerFlatItem("lime",new ItemNameBlockItem(ModBlocks.LIME_BLOCK,new Item.Properties().food(ModFood.LIME)));
    public static final Item SLICED_LIME = registerFlatItem("lime_slice",new Item(new Item.Properties().food(ModFood.LIME_SLICE)));

    public static final Item WILD_PEPPER_CROP = register("wild_pepper",new BlockItem(ModBlocks.WILD_PEPPER_CROP,new Item.Properties()));
    public static final Item PEPPER = registerFlatItem("pepper",new Item(new Item.Properties().food(ModFood.PEPPER)));
    public static final Item PEPPER_SEED = registerFlatItem("pepper_seeds",new ItemNameBlockItem(ModBlocks.BUDDING_PEPPER_CROP,new Item.Properties()){
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
    public static final Item SMALL_DURIAN = register("small_durian",new BlockItem(ModBlocks.SMALL_DURIAN_BLOCK,new Item.Properties()));
    public static final Item DURIAN = register("durian",new BlockItem(ModBlocks.DURIAN_BLOCK,new Item.Properties()));
    public static final Item DURIAN_PULP = registerFlatItem("durian_pulp",new Item(new Item.Properties().food(ModFood.DURIAN_PULP)));
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

    public static final Item DURIAN_PEEL = registerFlatItem("durian_peel",new Item(new Item.Properties()));
    public static final Item DURIAN_PEEL_BLOCK = register("durian_peel_block",new BlockItem(ModBlocks.DURIAN_PEEL_BLOCK,new Item.Properties()));
    public static final Item DURIAN_HELMET = registerFlatItem("durian_helmet",new ArmorItem(CommonEvents.getDurianMaterial(), ArmorItem.Type.HELMET,new Item.Properties()));
    //------------------ 🥥 COCONUT 🥥 ------------------
    public static final Item COCONUT_SAPLING = register("coconut_sapling",new BlockItem(ModBlocks.COCONUT_SAPLING,new Item.Properties()));
    public static final Item COCONUT_LEAF = register("coconut_leaf",new BlockItem(ModBlocks.COCONUT_LEAF,new Item.Properties()));
    public static final Item BUDDING_COCONUT_LEAF = register("budding_coconut_leaf",new BlockItem(ModBlocks.BUDDING_COCONUT_LEAF,new Item.Properties()));
    public static final Item COCONUT_LEAF_BLOCK = register("coconut_leaf_block",new BlockItem(ModBlocks.COCONUT_LEAF_BLOCK,new Item.Properties()));
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

    public static final Item COCONUT = register("coconut",new CoconutItem(new Item.Properties()));
    public static final Item STRIPPED_COCONUT = register("stripped_coconut",new BlockItem(ModBlocks.STRIPPED_COCONUT,new Item.Properties()));
    public static final Item COCONUT_LEAF_MAT = register("coconut_leaf_mat",new BlockItem(ModBlocks.COCONUT_LEAF_CARPET,new Item.Properties()));


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

    public static final Item MANGO = registerFlatItem("mango",new ItemNameBlockItem(ModBlocks.STACKABLE_MANGO_BLOCK,new Item.Properties()));
    public static final Item MANGO_SLICE = registerFlatItem("mango_slice",new Item(new Item.Properties()));

    //----- 🍐 Papaya 🍐 -----------------------------------
    public static final Item PAPAYA = registerFlatItem("papaya",new ItemNameBlockItem(ModBlocks.STACKABLE_PAPAYA,new Item.Properties().food(ModFood.PAPAYA)));
    public static final Item PAPAYA_FLOWER = register("papaya_flower",new PapayaFlowerItem(new Item.Properties()));
    public static final Item SLICED_PAPAYA = registerFlatItem("papaya_slice",new Item(new Item.Properties().food(ModFood.SLICED_PAPAYA)));
    public static final Item RAW_PAPAYA = registerFlatItem("raw_papaya",new ItemNameBlockItem(ModBlocks.STACKABLE_RAW_PAPAYA,new Item.Properties().food(ModFood.RAW_PAPAYA)));
    public static final Item RAW_PAPAYA_SLICE = registerFlatItem("raw_papaya_slice",new Item(new Item.Properties().food(ModFood.SLICED_UNRIPE_PAPAYA)));
    public static final Item PAPAYA_LOG = register("papaya_log",new BlockItem(ModBlocks.PAPAYA_LOG,new Item.Properties()));
    public static final Item STRIPPED_PAPAYA_LOG = register("stripped_papaya_log",new BlockItem(ModBlocks.STRIPPED_PAPAYA_LOG,new Item.Properties()));
    public static final Item PAPAYA_WOOD = register("papaya_wood",new BlockItem(ModBlocks.PAPAYA_WOOD,new Item.Properties()));
    public static final Item STRIPPED_PAPAYA_WOOD = register("stripped_papaya_wood",new BlockItem(ModBlocks.STRIPPED_PAPAYA_WOOD,new Item.Properties()));
    public static final Item PAPAYA_LEAVES = register("papaya_leaves",new BlockItem(ModBlocks.PAPAYA_LEAVES,new Item.Properties()));

    public static final Item PAPAYA_SAPLING = register("papaya_sapling",new BlockItem(ModBlocks.PAPAYA_SAPLING,new Item.Properties()));
    public static final Item PAPAYA_SEEDS = registerFlatItem("papaya_seeds",new ItemNameBlockItem(ModBlocks.PAPAYA_CROP,new Item.Properties()){
        @Override
        public void registerBlocks(Map<Block, Item> map, Item item) {
            super.registerBlocks(map, item);
            map.put(ModBlocks.PAPAYA_CROP,item);
        }
    });

    public static final Item HOLY_BASIL = registerFlatItem("holy_basil",new BlockItem(ModBlocks.HOLY_BASIL ,new Item.Properties()));
    public static final Item WILD_HOLY_BASIL = register("wild_holy_basil",new BlockItem(ModBlocks.WILD_HOLY_BASIL,new Item.Properties()));

    public static final Item BASIL = registerFlatItem("basil",new BlockItem(ModBlocks.BASIL,new Item.Properties()));
    public static final Item WILD_BASIL = register("wild_basil",new BlockItem(ModBlocks.WILD_BASIL,new Item.Properties()));

    public static final Item BAMBOO_SHOOT = registerFlatItem("bamboo_shoot",new ItemNameBlockItem(Blocks.BAMBOO_SAPLING,new Item.Properties()));

    //--- 🍔 Food 🍔---
    public static final Item SOMTAM_FEAST = registerFlatItem("somtam_feast",new BlockItem(ModBlocks.SOMTAM_FEAST,new Item.Properties()));
    public static final Item SOMTAM = registerFlatItem("somtam",new ConsumableItem(bowlFoodItem(ModFood.SOMTAM)));

    public static final Item LARB_FEAST = registerFlatItem("larb_feast",new BlockItem(ModBlocks.LARB_FEAST,new Item.Properties()));
    public static final Item LARB = registerFlatItem("larb",new ConsumableItem(bowlFoodItem(ModFood.LARB)));

    public static final Item CRAB_FRIED_RICE_FEAST = registerFlatItem("crab_fried_rice_feast",new BlockItem(ModBlocks.CRAB_FRIED_RICE_FEAST,new Item.Properties()));
    public static final Item CRAB_FRIED_RICE = registerFlatItem("crab_fried_rice",new ConsumableItem(bowlFoodItem(ModFood.CRAB_FRIED_RICE)));

    public static final Item STIR_FRIED_NOODLE = registerFlatItem("stir_fried_noodle",new ConsumableItem(bowlFoodItem(ModFood.STIR_FRIED_NOODLE)));

    public static final Item FRIED_DURIAN = registerFlatItem("fried_durian",new Item(new Item.Properties().food(ModFood.FRIED_DURIAN)));
    public static final Item COCONUT_MILK_BOTTLE = registerFlatItem("coconut_milk_bottle",new Item(new Item.Properties()));
    public static final Item COCONUT_SLICE = registerFlatItem("coconut_slice",new ConsumableItem(new Item.Properties().food(ModFood.COCONUT_MEAT).craftRemainder(Items.BOWL)));

    public static final Item PHAT_KAPHRAO_FEAST = registerFlatItem("phat_kaphrao_feast",new BlockItem(ModBlocks.PHAT_KAPHRAO_FEAST,new Item.Properties()));
    public static final Item PHAT_KAPHRAO = registerFlatItem("phat_kaphrao", new ConsumableItem(bowlFoodItem(ModFood.PHAT_KAPHRAO)));

    public static final Item PINEAPPLE_FRIED_RICE_FEAST = registerFlatItem("pineapple_fried_rice_feast",new BlockItem(ModBlocks.PINEAPPLE_FRIED_RICE_FEAST,new Item.Properties()));
    public static final Item PINEAPPLE_FRIED_RICE = registerFlatItem("pineapple_fried_rice",new Item(bowlFoodItem(ModFood.CRAB_FRIED_RICE)));

    public static final Item DURIAN_CURRY = registerFlatItem("durian_curry",new ConsumableItem(bowlFoodItem(ModFood.DURIAN_CURRY)));
    public static final Item DURIAN_CAKE = registerFlatItem("durian_cake",new BlockItem(ModBlocks.DURIAN_CAKE,new Item.Properties().stacksTo(1)));
    public static final Item DURIAN_CAKE_SLICE = registerFlatItem("durian_cake_slice",new Item(new Item.Properties().food(ModFood.MANGO_PIE)));

    public static final Item MANGO_STICKY_RICE_FEAST = registerFlatItem("mango_sticky_rice_feast",new BlockItem(ModBlocks.MANGO_STICKY_RICE_FEAST,new Item.Properties()));
    public static final Item MANGO_STICKY_RICE = registerFlatItem("mango_sticky_rice",new ConsumableItem(bowlFoodItem(ModFood.MANGO_STICKY_RICE)));
    public static final Item MANGO_CHEESECAKE = registerFlatItem("mango_cheesecake",new BlockItem(ModBlocks.MANGO_CHEESECAKE,new Item.Properties()));
    public static final Item MANGO_CHEESECAKE_SLICE = registerFlatItem("mango_cheesecake_slice",new Item(new Item.Properties().food(ModFood.MANGO_PIE)));

    public static final Item COCONUT_JELLY = registerFlatItem("coconut_jelly",new Item(new Item.Properties().food(ModFood.MANGO_PIE)));
    public static final Item KHANOM_BABIN = registerFlatItem("khanom_babin",new Item(new Item.Properties().food(ModFood.MANGO_PIE)));
    public static final Item COCONUT_PIE = registerFlatItem("coconut_pie",new BlockItem(ModBlocks.COCONUT_PIE,new Item.Properties()));
    public static final Item COCONUT_PIE_SLICE = registerFlatItem("coconut_pie_slice",new Item(new Item.Properties().food(ModFood.COCONUT_PIE_SLICE)));
    public static final Item HONEY_COCONUT_PIE = registerFlatItem("honey_coconut_pie",new BlockItem(ModBlocks.HONEY_COCONUT_PIE,new Item.Properties()));
    public static final Item HONEY_COCONUT_PIE_SLICE = registerFlatItem("honey_coconut_pie_slice",new Item(new Item.Properties().food(ModFood.COCONUT_PIE_SLICE)){
        @Override
        public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
            if (!level.isClientSide) {
                livingEntity.removeEffect(MobEffects.POISON);
            }
            return super.finishUsingItem(itemStack, level, livingEntity);
        }
    });

    public static final Item OMELETTE_FEAST = registerFlatItem("omelette_feast",new BlockItem(ModBlocks.OMELETTE_FEAST,new Item.Properties()));
    public static final Item OMELETTE = registerFlatItem("omelette",new Item(new Item.Properties()));
    public static final Item BASIL_OMELETTE_FEAST = registerFlatItem("basil_omelette_feast",new BlockItem(ModBlocks.BASIL_OMELETTE_FEAST,new Item.Properties()));
    public static final Item BASIL_OMELETTE = registerFlatItem("basil_omelette",new Item(new Item.Properties()));

    public static final Item BAMBOO_SHOOT_SOUP = registerFlatItem("bamboo_shoot_soup",new Item(new Item.Properties()));
    public static final Item STEAMED_BAMBOO_SHOOT = registerFlatItem("steamed_bamboo_shoot",new Item(new Item.Properties()));

    public static final Item BUTTERFLY_PEA = registerFlatItem("butterfly_pea",new ItemNameBlockItem(ModBlocks.BUTTERFLY_PEA_WALL,new Item.Properties()));
    //I KNOW IT'S NOT THAI. BUT THERE IS MORTAR AND BASIL IN THE SAME MOD. HOW COULD I MISS THIS OPPORTUNITY.
    public static final Item PESTO_SAUCE = registerFlatItem("pesto_sauce",new ConsumableItem(new Item.Properties().food(ModFood.PESTO_SAUCE).craftRemainder(Items.BOWL)));


    public static final Item BUTTERFLY_PEA_SEEDS = registerFlatItem("butterfly_pea_seeds",new ItemNameBlockItem(ModBlocks.BUDDING_BUTTERFLY_PEA_BLOCK,new Item.Properties()));
    public static final Item BUTTERFLY_PEA_TEA = registerFlatItem("butterfly_pea_tea",new Item(drinkItem()));

    public static final Item KHANOM_CHAN = registerFlatItem("khanom_chan",new DyeableItem(new Item.Properties().food(ModFood.MANGO_PIE)));
    public static final Item COCONUT_MILK_ICE_CREAM = register("coconut_milk_ice_cream",new DyeableItem(bowlFoodItem(ModFood.COCONUT_MILK_ICE_CREAM)));

    public static final Item BANANA_IN_COCONUT_MILK = registerFlatItem("banana_in_coconut_milk",new Item(new Item.Properties()));


    public static Item registerKhanomChan(String id){
        return registerFlatItem(id,new Item(new Item.Properties().food(ModFood.MANGO_PIE)));
    }

    public static Item registerCoconutMilkIceCream(String id){
        return registerFlatItem(id,new Item(bowlFoodItem(ModFood.COCONUT_MILK_ICE_CREAM)));
    }

    public static Item registerFlatItem(String id,Item item){
        Item registeredItem = register(id,item);
        FLAT_ITEMS.add(registeredItem);
        return registeredItem;
    }

    public static Item register(String id,Item item){
        return Registry.register(BuiltInRegistries.ITEM,new ResourceLocation(ThaiDelight.MOD_ID,id),item);
    }

    public static void init() {
        Item.BY_BLOCK.put(ModBlocks.COCONUT_LEAF,ModItems.COCONUT_LEAF);
        Item.BY_BLOCK.put(ModBlocks.HANGING_DURIAN,ModItems.DURIAN);
        Item.BY_BLOCK.put(ModBlocks.HANGING_MANGO_BLOCK,ModItems.MANGO);
        Item.BY_BLOCK.put(ModBlocks.PAPAYA,ModItems.PAPAYA);
    }

    public static void addFuel(){
        FuelRegistry.INSTANCE.add(ModItems.LIME,200);
        FuelRegistry.INSTANCE.add(ModItems.SLICED_LIME,200);
        FuelRegistry.INSTANCE.add(ModItems.DURIAN_PEEL,200);
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

        public static final FoodProperties DURIAN_PULP = new FoodProperties.Builder().nutrition(4).saturationMod(0.2f).build();
        public static final FoodProperties FRIED_DURIAN = new FoodProperties.Builder().alwaysEat().fast().nutrition(3).saturationMod(0.4f).build();

        public static final FoodProperties COCONUT_MEAT = new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build();

        public static final FoodProperties COCONUT_PIE_SLICE = new FoodProperties.Builder()
                .nutrition(3).saturationMod(0.3f).fast()
                .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,600,0,false,false),1.0f).build();

        public static final FoodProperties FISH_SAUCE = new FoodProperties.Builder().alwaysEat().effect(new MobEffectInstance(MobEffects.HUNGER,200,0),1.0f).build();
        public static final FoodProperties SEAFOOD_SAUCE = new FoodProperties.Builder().alwaysEat().effect(new MobEffectInstance(MobEffects.WATER_BREATHING,200,0),1.0f).build();
        public static final FoodProperties PAPAYA_JUICE = new FoodProperties.Builder().alwaysEat().build();
        public static final FoodProperties LIME_JUICE = new FoodProperties.Builder().alwaysEat().build();
        public static final FoodProperties HONEY_LIME_JUICE = new FoodProperties.Builder().alwaysEat().build();
        public static final FoodProperties COCONUT_WATER = new FoodProperties.Builder().alwaysEat().build();

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

        public static final FoodProperties DURIAN_CURRY = new FoodProperties.Builder()
                .nutrition(12)
                .saturationMod(0.8F)
                .effect(new MobEffectInstance(ModEffects.NOURISHMENT.get(),3600,0),1.0f).build();

        public static final FoodProperties MANGO_STICKY_RICE = new FoodProperties.Builder()
                .nutrition(12)
                .saturationMod(0.55F)
                .effect(new MobEffectInstance(ModEffects.COMFORT.get(),9600,0),1.0f).build();

        public static final FoodProperties MANGO_PIE = new FoodProperties.Builder()
                .nutrition(3)
                .saturationMod(0.3F)
                .fast()
                .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0, false, false), 1.0F).build();


        public static final FoodProperties FERMENTED_FISH = new FoodProperties.Builder().alwaysEat()
                .effect(new MobEffectInstance(ModMobEffects.STINKY,10*20),1f)
                .effect(new MobEffectInstance(ModMobEffects.APPETITE_LOSS,10*20),1f)

                .build();

        public static final FoodProperties COCONUT_MILK_ICE_CREAM = new FoodProperties.Builder()
                .nutrition(3)
                .saturationMod(0.3F)
                .fast()
                .build();

        public static final FoodProperties PESTO_SAUCE = (new FoodProperties.Builder())
                .nutrition(4).saturationMod(0.4f).build();
    }

}
