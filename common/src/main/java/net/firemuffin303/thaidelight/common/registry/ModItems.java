package net.firemuffin303.thaidelight.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.item.equipments.DurianHelmetItem;
import net.firemuffin303.thaidelight.common.item.vegetations.CoconutItem;
import net.firemuffin303.thaidelight.common.item.DragonflyBottleItem;
import net.firemuffin303.thaidelight.common.item.DyeableItem;
import net.firemuffin303.thaidelight.common.item.SackItem;
import net.firemuffin303.thaidelight.common.item.vegetations.papaya.PapayaFlowerItem;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Supplier;

import static net.firemuffin303.thaidelight.util.PlatformUtil.bowlFoodItem;

public class ModItems {

    public static final Supplier<CreativeModeTab> MOD_TAB = registerCreativeTab("main",() -> CreativeModeTab.builder(null,-1)
            .title(Component.translatable("itemGroup."+ThaiDelightCommon.MOD_ID+".main"))
            .icon(() -> new ItemStack(ModBlocks.MORTAR.get()))
            .displayItems(RegistryUtils::itemsGenerator)
            .build());


    public static final ArrayList<Supplier<Item>> FLAT_ITEMS = new ArrayList<>();


    public static final Supplier<Item> MORTAR = register("mortar",() -> new BlockItem(ModBlocks.MORTAR.get(),new Item.Properties()));
    public static final Supplier<Item> SACK = register("sack",() -> new SackItem(new Item.Properties().stacksTo(1)));

    //Blocks
    public static final Supplier<Item> LIME_CRATE = register("lime_crate",() -> new BlockItem(ModBlocks.LIME_CRATE.get(),new Item.Properties()));
    public static final Supplier<Item> PEPPER_CRATE = register("pepper_crate",() -> new BlockItem(ModBlocks.PEPPER_CRATE.get(),new Item.Properties()));
    public static final Supplier<Item> RAW_PAPAYA_CRATE = register("raw_papaya_crate",() -> new BlockItem(ModBlocks.RAW_PAPAYA_CRATE.get(),new Item.Properties()));
    public static final Supplier<Item> PAPAYA_CRATE = register("papaya_crate",() -> new BlockItem(ModBlocks.PAPAYA_CRATE.get(),new Item.Properties()));
    public static final Supplier<Item> MANGO_CRATE = register("mango_crate",() -> new BlockItem(ModBlocks.MANGO_CRATE.get(),new Item.Properties()));
    public static final Supplier<Item> HOLY_BASIL_CRATE = register("holy_basil_crate",() -> new BlockItem(ModBlocks.HOLY_BASIL_CRATE.get(),new Item.Properties()));
    public static final Supplier<Item> BASIL_CRATE = register("basil_crate",() -> new BlockItem(ModBlocks.BASIL_CRATE.get(),new Item.Properties()));
    public static final Supplier<Item> BAMBOO_SHOOT_CRATE = register("bamboo_shoot_crate",() -> new BlockItem(ModBlocks.BAMBOO_SHOOT_CRATE.get(),new Item.Properties()));
    public static final Supplier<Item> BUTTERFLY_PEA_CRATE = register("butterfly_pea_crate",() -> new BlockItem(ModBlocks.BUTTERFLY_PEA_CRATE.get(),new Item.Properties()));

    //Crab
    public static final Supplier<Item> CRAB_SPAWN_EGG = register("flower_crab_spawn_egg",() -> new SpawnEggItem(ModEntityTypes.FLOWER_CRAB.get(),0x93a064,0xac3247,new Item.Properties()));
    public static final Supplier<Item> CRAB_EGG = register("flower_crab_egg",() -> new BlockItem(ModBlocks.CRAB_EGG.get(),new Item.Properties()));
    public static final Supplier<Item> CRAB_BUCKET = registerFlatItem("flower_crab_bucket",() -> new MobBucketItem(ModEntityTypes.FLOWER_CRAB.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH,new Item.Properties().stacksTo(1)));
    public static final Supplier<Item> CRAB_MEAT = registerFlatItem("flower_crab",() -> new Item(new Item.Properties().food(ModFoods.CRAB)));
    public static final Supplier<Item> COOKED_CRAB_MEAT = registerFlatItem("cooked_flower_crab",() -> new Item(new Item.Properties().food(ModFoods.COOKED_CRAB)));

    //Dragonfly
    public static final Supplier<Item> DRAGONFLY_SPAWN_EGG = register("dragonfly_spawn_egg",() -> new SpawnEggItem(ModEntityTypes.DRAGONFLY.get(),0x181d13,0x246011,new Item.Properties()));
    public static final Supplier<Item> DRAGONFLY = registerFlatItem("dragonfly",() -> new Item(new Item.Properties().food(ModFoods.DRAGONFLY)));
    public static final Supplier<Item> DRAGONFLY_BOTTLE = register("dragonfly_bottle",() -> new DragonflyBottleItem(new Item.Properties().stacksTo(1)));
    public static final Supplier<Item> COOKED_DRAGONFLY = registerFlatItem("cooked_dragonfly",() -> new Item(new Item.Properties().food(ModFoods.COOKED_DRAGONFLY)));

    //Bucket
    public static final Supplier<Item> FISH_SAUCE_BOTTLE = registerFlatItem("fish_sauce_bottle",() -> createDrinkableItem(getDrinkItem().food(ModFoods.FISH_SAUCE),true,false)) ;
    public static final Supplier<Item> FERMENTED_FISH = registerFlatItem("fermented_fish",() -> createConsumeableItem(bowlFoodItem(ModFoods.FERMENTED_FISH),true,false));
    public static final Supplier<Item> PAPAYA_JUICE = registerFlatItem("papaya_juice",() -> createPapayaJuiceItem());
    public static final Supplier<Item> LIME_JUICE = registerFlatItem("lime_juice",() -> createLimeJuiceItem());
    public static final Supplier<Item> HONEY_LIME_JUICE = registerFlatItem("honey_lime_juice",() -> createHoneyLimeJuiceItem());
    public static final Supplier<Item> COCONUT_WATER = registerFlatItem("coconut_water",() -> createCoconutWaterJuiceItem());

    //Crops
    //------------- Lime ---------------
    public static final Supplier<Item> LIME_SAPLING = register("lime_sapling",() -> new BlockItem(ModBlocks.LIME_SAPLING.get(),new Item.Properties()));
    public static final Supplier<Item> LIME = registerFlatItem("lime",() -> new ItemNameBlockItem(ModBlocks.LIME_BLOCK.get(),new Item.Properties().food(ModFoods.LIME)));
    public static final Supplier<Item> SLICED_LIME = registerFlatItem("lime_slice",() -> new Item(new Item.Properties().food(ModFoods.LIME_SLICE)));

    public static final Supplier<Item> WILD_PEPPER_CROP = register("wild_pepper",() -> new BlockItem(ModBlocks.WILD_PEPPER_CROP.get(),new Item.Properties()));
    public static final Supplier<Item> PEPPER = registerFlatItem("pepper",() -> new Item(new Item.Properties().food(ModFoods.PEPPER)));
    public static final Supplier<Item> PEPPER_SEED = registerFlatItem("pepper_seeds",() -> new ItemNameBlockItem(ModBlocks.BUDDING_PEPPER_CROP.get(),new Item.Properties()){
        @Override
        public void registerBlocks(Map<Block, Item> map, Item item) {
            super.registerBlocks(map, item);
            map.put(ModBlocks.PEPPER_CROP.get(),item);
        }
    });

    //------------------ Durian ------------------
    public static final Supplier<Item> DURIAN_SAPLING = register("durian_sapling",() ->new BlockItem(ModBlocks.DURIAN_SAPLING.get(),new Item.Properties()));
    public static final Supplier<Item> DURIAN_LEAVES = register("durian_leaves",() ->new BlockItem(ModBlocks.DURIAN_LEAVES.get(),new Item.Properties()));
    public static final Supplier<Item> DURIAN_FLOWER = register("durian_flower",() ->new BlockItem(ModBlocks.DURIAN_FLOWER.get(),new Item.Properties()));
    public static final Supplier<Item> SMALL_DURIAN = register("small_durian",() ->new BlockItem(ModBlocks.SMALL_DURIAN_BLOCK.get(),new Item.Properties()));
    public static final Supplier<Item> DURIAN = register("durian",() ->new BlockItem(ModBlocks.DURIAN_BLOCK.get(),new Item.Properties()));
    public static final Supplier<Item> DURIAN_PULP = registerFlatItem("durian_pulp",() ->new Item(new Item.Properties().food(ModFoods.DURIAN_PULP)));
    //Durian Woodset item
    public static final Supplier<Item> DURIAN_LOG = register("durian_log",() ->new BlockItem(ModBlocks.DURIAN_LOG.get(),new Item.Properties()));
    public static final Supplier<Item> DURIAN_WOOD = register("durian_wood",() ->new BlockItem(ModBlocks.DURIAN_WOOD.get(),new Item.Properties()));
    public static final Supplier<Item> STRIPPED_DURIAN_LOG = register("stripped_durian_log",() ->new BlockItem(ModBlocks.STRIPPED_DURIAN_LOG.get(),new Item.Properties()));
    public static final Supplier<Item> STRIPPED_DURIAN_WOOD = register("stripped_durian_wood",() ->new BlockItem(ModBlocks.STRIPPED_DURIAN_WOOD.get(),new Item.Properties()));
    public static final Supplier<Item> DURIAN_PLANKS = register("durian_planks",() ->new BlockItem(ModBlocks.DURIAN_PLANKS.get(),new Item.Properties()));
    public static final Supplier<Item> DURIAN_STAIRS = register("durian_stairs",() ->new BlockItem(ModBlocks.DURIAN_STAIRS.get(),new Item.Properties()));
    public static final Supplier<Item> DURIAN_SLAB = register("durian_slab",() ->new BlockItem(ModBlocks.DURIAN_SLAB.get(),new Item.Properties()));
    public static final Supplier<Item> DURIAN_FENCE = register("durian_fence",() ->new BlockItem(ModBlocks.DURIAN_FENCE.get(),new Item.Properties()));
    public static final Supplier<Item> DURIAN_FENCE_GATE = register("durian_fence_gate",() ->new BlockItem(ModBlocks.DURIAN_FENCE_GATE.get(),new Item.Properties()));
    public static final Supplier<Item> DURIAN_DOOR = register("durian_door",() ->new BlockItem(ModBlocks.DURIAN_DOOR.get(),new Item.Properties()));
    public static final Supplier<Item> DURIAN_TRAPDOOR = register("durian_trapdoor",() ->new BlockItem(ModBlocks.DURIAN_TRAPDOOR.get(),new Item.Properties()));
    public static final Supplier<Item> DURIAN_PRESSURE_PLATE = register("durian_pressure_plate",() ->new BlockItem(ModBlocks.DURIAN_PRESSURE_PLATE.get(),new Item.Properties()));
    public static final Supplier<Item> DURIAN_BUTTON = register("durian_button",() ->new BlockItem(ModBlocks.DURIAN_BUTTON.get(),new Item.Properties()));
    public static final Supplier<Item> DURIAN_SIGN = register("durian_sign",() ->new SignItem(new Item.Properties().stacksTo(16),ModBlocks.DURIAN_SIGN.get(),ModBlocks.DURIAN_WALL_SIGN.get()));
    public static final Supplier<Item> DURIAN_HANGING_SIGN = register("durian_hanging_sign",() ->new HangingSignItem(ModBlocks.DURIAN_HANGING_SIGN.get(),ModBlocks.DURIAN_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));
    public static final Supplier<Item> DURIAN_CABINET = register("durian_cabinet",() ->new BlockItem(ModBlocks.DURIAN_CABINET.get(),new Item.Properties()));
    public static final Supplier<Item> DURIAN_BOAT = createBoat(ThaiDelightCommon.modid("durian_boat"),false,"durian");
    public static final Supplier<Item> DURIAN_CHEST_BOAT = createBoat(ThaiDelightCommon.modid("durian_chest_boat"),true,"durian");

    public static final Supplier<Item> DURIAN_PEEL = registerFlatItem("durian_peel",() ->new Item(new Item.Properties()));
    public static final Supplier<Item> DURIAN_PEEL_BLOCK = register("durian_peel_block",() ->new BlockItem(ModBlocks.DURIAN_PEEL_BLOCK.get(),new Item.Properties()));
    public static final Supplier<Item> DURIAN_HELMET = registerFlatItem("durian_helmet", DurianHelmetItem::new);
    //------------------ 🥥 COCONUT 🥥 ------------------
    public static final Supplier<Item> COCONUT_SAPLING = register("coconut_sapling",() ->new BlockItem(ModBlocks.COCONUT_SAPLING.get(),new Item.Properties()));
    public static final Supplier<Item> COCONUT_LEAF = register("coconut_leaf",() ->new BlockItem(ModBlocks.COCONUT_LEAF.get(),new Item.Properties()));
    public static final Supplier<Item> BUDDING_COCONUT_LEAF = register("budding_coconut_leaf",() ->new BlockItem(ModBlocks.BUDDING_COCONUT_LEAF.get(),new Item.Properties()));
    public static final Supplier<Item> COCONUT_LEAF_BLOCK = register("coconut_leaf_block",() ->new BlockItem(ModBlocks.COCONUT_LEAF_BLOCK.get(),new Item.Properties()));
    //COCONUT Woodset item
    public static final Supplier<Item> COCONUT_LOG = register("coconut_log",() ->new BlockItem(ModBlocks.COCONUT_LOG.get(),new Item.Properties()));
    public static final Supplier<Item> COCONUT_WOOD = register("coconut_wood",() ->new BlockItem(ModBlocks.COCONUT_WOOD.get(),new Item.Properties()));
    public static final Supplier<Item> STRIPPED_COCONUT_LOG = register("stripped_coconut_log",() ->new BlockItem(ModBlocks.STRIPPED_COCONUT_LOG.get(),new Item.Properties()));
    public static final Supplier<Item> STRIPPED_COCONUT_WOOD = register("stripped_coconut_wood",() ->new BlockItem(ModBlocks.STRIPPED_COCONUT_WOOD.get(),new Item.Properties()));
    public static final Supplier<Item> COCONUT_PLANKS = register("coconut_planks",() ->new BlockItem(ModBlocks.COCONUT_PLANKS.get(),new Item.Properties()));
    public static final Supplier<Item> COCONUT_STAIRS = register("coconut_stairs",() ->new BlockItem(ModBlocks.COCONUT_STAIRS.get(),new Item.Properties()));
    public static final Supplier<Item> COCONUT_SLAB = register("coconut_slab",() ->new BlockItem(ModBlocks.COCONUT_SLAB.get(),new Item.Properties()));
    public static final Supplier<Item> COCONUT_FENCE = register("coconut_fence",() ->new BlockItem(ModBlocks.COCONUT_FENCE.get(),new Item.Properties()));
    public static final Supplier<Item> COCONUT_FENCE_GATE = register("coconut_fence_gate",() ->new BlockItem(ModBlocks.COCONUT_FENCE_GATE.get(),new Item.Properties()));
    public static final Supplier<Item> COCONUT_DOOR = register("coconut_door",() ->new BlockItem(ModBlocks.COCONUT_DOOR.get(),new Item.Properties()));
    public static final Supplier<Item> COCONUT_TRAPDOOR = register("coconut_trapdoor",() ->new BlockItem(ModBlocks.COCONUT_TRAPDOOR.get(),new Item.Properties()));
    public static final Supplier<Item> COCONUT_PRESSURE_PLATE = register("coconut_pressure_plate",() ->new BlockItem(ModBlocks.COCONUT_PRESSURE_PLATE.get(),new Item.Properties()));
    public static final Supplier<Item> COCONUT_BUTTON = register("coconut_button",() ->new BlockItem(ModBlocks.COCONUT_BUTTON.get(),new Item.Properties()));
    public static final Supplier<Item> COCONUT_SIGN = register("coconut_sign",() ->new SignItem(new Item.Properties().stacksTo(16),ModBlocks.COCONUT_SIGN.get(),ModBlocks.COCONUT_WALL_SIGN.get()));
    public static final Supplier<Item> COCONUT_HANGING_SIGN = register("coconut_hanging_sign",() ->new HangingSignItem(ModBlocks.COCONUT_HANGING_SIGN.get(),ModBlocks.COCONUT_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));
    public static final Supplier<Item> COCONUT_CABINET = register("coconut_cabinet",() ->new BlockItem(ModBlocks.COCONUT_CABINET.get(),new Item.Properties()));
    public static final Supplier<Item> COCONUT_BOAT = createBoat(ThaiDelightCommon.modid("coconut_boat"),false,"coconut");
    public static final Supplier<Item> COCONUT_CHEST_BOAT = createBoat(ThaiDelightCommon.modid("coconut_chest_boat"),true,"coconut");

    public static final Supplier<Item> COCONUT = register("coconut",() ->new CoconutItem(new Item.Properties()));
    public static final Supplier<Item> STRIPPED_COCONUT = register("stripped_coconut",() ->new BlockItem(ModBlocks.STRIPPED_COCONUT.get(),new Item.Properties()));
    public static final Supplier<Item> COCONUT_LEAF_MAT = register("coconut_leaf_mat",() ->new BlockItem(ModBlocks.COCONUT_LEAF_CARPET.get(),new Item.Properties()));


    //------------------ 🥭 MANGO 🥭 --------------------
    public static final Supplier<Item> MANGO_SAPLING = register("mango_sapling",() ->new BlockItem(ModBlocks.MANGO_SAPLING.get(),new Item.Properties()));
    public static final Supplier<Item> MANGO_LEAVES = register("mango_leaves",() ->new BlockItem(ModBlocks.MANGO_LEAVES.get(),new Item.Properties()));

    public static final Supplier<Item> MANGO_LOG = register("mango_log",() ->new BlockItem(ModBlocks.MANGO_LOG.get(),new Item.Properties()));
    public static final Supplier<Item> MANGO_WOOD = register("mango_wood",() ->new BlockItem(ModBlocks.MANGO_WOOD.get(),new Item.Properties()));
    public static final Supplier<Item> STRIPPED_MANGO_LOG = register("stripped_mango_log",() ->new BlockItem(ModBlocks.STRIPPED_MANGO_LOG.get(),new Item.Properties()));
    public static final Supplier<Item> STRIPPED_MANGO_WOOD = register("stripped_mango_wood",() ->new BlockItem(ModBlocks.STRIPPED_MANGO_WOOD.get(),new Item.Properties()));
    public static final Supplier<Item> MANGO_PLANKS = register("mango_planks",() ->new BlockItem(ModBlocks.MANGO_PLANKS.get(),new Item.Properties()));
    public static final Supplier<Item> MANGO_STAIRS = register("mango_stairs",() ->new BlockItem(ModBlocks.MANGO_STAIRS.get(),new Item.Properties()));
    public static final Supplier<Item> MANGO_SLAB = register("mango_slab",() ->new BlockItem(ModBlocks.MANGO_SLAB.get(),new Item.Properties()));
    public static final Supplier<Item> MANGO_FENCE = register("mango_fence",() ->new BlockItem(ModBlocks.MANGO_FENCE.get(),new Item.Properties()));
    public static final Supplier<Item> MANGO_FENCE_GATE = register("mango_fence_gate",() ->new BlockItem(ModBlocks.MANGO_FENCE_GATE.get(),new Item.Properties()));
    public static final Supplier<Item> MANGO_DOOR = register("mango_door",() ->new BlockItem(ModBlocks.MANGO_DOOR.get(),new Item.Properties()));
    public static final Supplier<Item> MANGO_TRAPDOOR = register("mango_trapdoor",() ->new BlockItem(ModBlocks.MANGO_TRAPDOOR.get(),new Item.Properties()));
    public static final Supplier<Item> MANGO_PRESSURE_PLATE = register("mango_pressure_plate",() ->new BlockItem(ModBlocks.MANGO_PRESSURE_PLATE.get(),new Item.Properties()));
    public static final Supplier<Item> MANGO_BUTTON = register("mango_button",() ->new BlockItem(ModBlocks.MANGO_BUTTON.get(),new Item.Properties()));
    public static final Supplier<Item> MANGO_SIGN = register("mango_sign",() ->new SignItem(new Item.Properties().stacksTo(16),ModBlocks.MANGO_SIGN.get(),ModBlocks.MANGO_WALL_SIGN.get()));
    public static final Supplier<Item> MANGO_HANGING_SIGN = register("mango_hanging_sign",() ->new HangingSignItem(ModBlocks.MANGO_HANGING_SIGN.get(),ModBlocks.MANGO_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));
    public static final Supplier<Item> MANGO_CABINET = register("mango_cabinet",() ->new BlockItem(ModBlocks.MANGO_CABINET.get(),new Item.Properties()));
    public static final Supplier<Item> MANGO_BOAT = createBoat(ThaiDelightCommon.modid("mango_boat"),false,"mango");
    public static final Supplier<Item> MANGO_CHEST_BOAT = createBoat(ThaiDelightCommon.modid("mango_chest_boat"),true,"mango");

    public static final Supplier<Item> MANGO = registerFlatItem("mango",() ->new ItemNameBlockItem(ModBlocks.STACKABLE_MANGO_BLOCK.get(),new Item.Properties().food(ModFoods.MANGO)));
    public static final Supplier<Item> MANGO_SLICE = registerFlatItem("mango_slice",() ->new Item(new Item.Properties().food(ModFoods.MANGO_SLICE)));

    //----- 🍐 Papaya 🍐 -----------------------------------
    public static final Supplier<Item> PAPAYA = registerFlatItem("papaya",() ->new ItemNameBlockItem(ModBlocks.STACKABLE_PAPAYA.get(),new Item.Properties().food(ModFoods.PAPAYA)));
    public static final Supplier<Item> PAPAYA_FLOWER = register("papaya_flower",() ->new PapayaFlowerItem(new Item.Properties()));
    public static final Supplier<Item> SLICED_PAPAYA = registerFlatItem("papaya_slice",() ->new Item(new Item.Properties().food(ModFoods.SLICED_PAPAYA)));
    public static final Supplier<Item> RAW_PAPAYA = registerFlatItem("raw_papaya",() ->new ItemNameBlockItem(ModBlocks.STACKABLE_RAW_PAPAYA.get(),new Item.Properties().food(ModFoods.RAW_PAPAYA)));
    public static final Supplier<Item> RAW_PAPAYA_SLICE = registerFlatItem("raw_papaya_slice",() ->new Item(new Item.Properties().food(ModFoods.SLICED_UNRIPE_PAPAYA)));
    public static final Supplier<Item> PAPAYA_LOG = register("papaya_log",() ->new BlockItem(ModBlocks.PAPAYA_LOG.get(),new Item.Properties()));
    public static final Supplier<Item> STRIPPED_PAPAYA_LOG = register("stripped_papaya_log",() ->new BlockItem(ModBlocks.STRIPPED_PAPAYA_LOG.get(),new Item.Properties()));
    public static final Supplier<Item> PAPAYA_WOOD = register("papaya_wood",() ->new BlockItem(ModBlocks.PAPAYA_WOOD.get(),new Item.Properties()));
    public static final Supplier<Item> STRIPPED_PAPAYA_WOOD = register("stripped_papaya_wood",() ->new BlockItem(ModBlocks.STRIPPED_PAPAYA_WOOD.get(),new Item.Properties()));
    public static final Supplier<Item> PAPAYA_LEAVES = register("papaya_leaves",() ->new BlockItem(ModBlocks.PAPAYA_LEAVES.get(),new Item.Properties()));

    public static final Supplier<Item> PAPAYA_SAPLING = register("papaya_sapling",() ->new BlockItem(ModBlocks.PAPAYA_SAPLING.get(),new Item.Properties()));
    public static final Supplier<Item> PAPAYA_SEEDS = registerFlatItem("papaya_seeds",() ->new ItemNameBlockItem(ModBlocks.PAPAYA_CROP.get(),new Item.Properties()){
        @Override
        public void registerBlocks(Map<Block, Item> map, Item item) {
            super.registerBlocks(map, item);
            map.put(ModBlocks.PAPAYA_CROP.get(),item);
        }
    });

    public static final Supplier<Item> HOLY_BASIL = registerFlatItem("holy_basil",() ->new BlockItem(ModBlocks.HOLY_BASIL.get() ,new Item.Properties()));
    public static final Supplier<Item> WILD_HOLY_BASIL = register("wild_holy_basil",() ->new BlockItem(ModBlocks.WILD_HOLY_BASIL.get(),new Item.Properties()));

    public static final Supplier<Item> BASIL = registerFlatItem("basil",() ->new BlockItem(ModBlocks.BASIL.get(),new Item.Properties()));
    public static final Supplier<Item> WILD_BASIL = register("wild_basil",() ->new BlockItem(ModBlocks.WILD_BASIL.get(),new Item.Properties()));

    public static final Supplier<Item> BAMBOO_SHOOT = registerFlatItem("bamboo_shoot",() ->new ItemNameBlockItem(Blocks.BAMBOO_SAPLING,new Item.Properties()));

    //--- 🍔 Food 🍔---
    public static final Supplier<Item> SOMTAM_FEAST = registerFlatItem("somtam_feast",() ->new BlockItem(ModBlocks.SOMTAM_FEAST.get(),new Item.Properties()));
    public static final Supplier<Item> SOMTAM = registerFlatItem("somtam",() ->createConsumeableItem(bowlFoodItem(ModFoods.SOMTAM)));

    public static final Supplier<Item> LARB_FEAST = registerFlatItem("larb_feast",() ->new BlockItem(ModBlocks.LARB_FEAST.get(),new Item.Properties()));
    public static final Supplier<Item> LARB = registerFlatItem("larb",() ->createConsumeableItem(bowlFoodItem(ModFoods.LARB)));

    public static final Supplier<Item> CRAB_FRIED_RICE_FEAST = registerFlatItem("crab_fried_rice_feast",() ->new BlockItem(ModBlocks.CRAB_FRIED_RICE_FEAST.get(),new Item.Properties()));
    public static final Supplier<Item> CRAB_FRIED_RICE = registerFlatItem("crab_fried_rice",() ->createConsumeableItem(bowlFoodItem(ModFoods.CRAB_FRIED_RICE)));

    public static final Supplier<Item> STIR_FRIED_NOODLE = registerFlatItem("stir_fried_noodle",() ->createConsumeableItem(bowlFoodItem(ModFoods.STIR_FRIED_NOODLE)));

    public static final Supplier<Item> FRIED_DURIAN = registerFlatItem("fried_durian",() ->new Item(new Item.Properties().food(ModFoods.FRIED_DURIAN)));

    public static final Supplier<Item> COCONUT_MILK_BOTTLE = registerFlatItem("coconut_milk_bottle",() ->new Item(getDrinkItem()));
    public static final Supplier<Item> COCONUT_SLICE = registerFlatItem("coconut_slice",() ->createConsumeableItem(new Item.Properties().food(ModFoods.COCONUT_MEAT).craftRemainder(Items.BOWL)));

    public static final Supplier<Item> PHAT_KAPHRAO_FEAST = registerFlatItem("phat_kaphrao_feast",() ->new BlockItem(ModBlocks.PHAT_KAPHRAO_FEAST.get(),new Item.Properties()));
    public static final Supplier<Item> PHAT_KAPHRAO = registerFlatItem("phat_kaphrao", () -> createConsumeableItem(bowlFoodItem(ModFoods.PHAT_KAPHRAO)));

    public static final Supplier<Item> PINEAPPLE_FRIED_RICE_FEAST = registerFlatItem("pineapple_fried_rice_feast",() -> new BlockItem(ModBlocks.PINEAPPLE_FRIED_RICE_FEAST.get(),new Item.Properties()));
    public static final Supplier<Item> PINEAPPLE_FRIED_RICE = registerFlatItem("pineapple_fried_rice",() -> new Item(bowlFoodItem(ModFoods.PINEAPPLE_FRIED_RICE)));

    public static final Supplier<Item> DURIAN_CURRY = registerFlatItem("durian_curry",() -> createConsumeableItem(bowlFoodItem(ModFoods.DURIAN_CURRY)));
    public static final Supplier<Item> DURIAN_CAKE = registerFlatItem("durian_cake",() -> new BlockItem(ModBlocks.DURIAN_CAKE.get(),new Item.Properties().stacksTo(1)));
    public static final Supplier<Item> DURIAN_CAKE_SLICE = registerFlatItem("durian_cake_slice",() -> new Item(new Item.Properties().food(ModFoods.DURIAN_CAKE)));

    public static final Supplier<Item> MANGO_STICKY_RICE_FEAST = registerFlatItem("mango_sticky_rice_feast",() -> new BlockItem(ModBlocks.MANGO_STICKY_RICE_FEAST.get(),new Item.Properties()));
    public static final Supplier<Item> MANGO_STICKY_RICE = registerFlatItem("mango_sticky_rice",() -> createConsumeableItem(bowlFoodItem(ModFoods.MANGO_STICKY_RICE)));
    public static final Supplier<Item> MANGO_CHEESECAKE = registerFlatItem("mango_cheesecake",() -> new BlockItem(ModBlocks.MANGO_CHEESECAKE.get(),new Item.Properties()));
    public static final Supplier<Item> MANGO_CHEESECAKE_SLICE = registerFlatItem("mango_cheesecake_slice",() -> new Item(new Item.Properties().food(ModFoods.MANGO_PIE)));

    public static final Supplier<Item> COCONUT_JELLY = registerFlatItem("coconut_jelly",() -> new Item(new Item.Properties().food(ModFoods.COCONUT_JELLO)));
    public static final Supplier<Item> KHANOM_BABIN = registerFlatItem("khanom_babin",() -> new Item(new Item.Properties().food(ModFoods.KHANOM_BABIN)));
    public static final Supplier<Item> COCONUT_PIE = registerFlatItem("coconut_pie",() -> new BlockItem(ModBlocks.COCONUT_PIE.get(),new Item.Properties()));
    public static final Supplier<Item> COCONUT_PIE_SLICE = registerFlatItem("coconut_pie_slice",() -> new Item(new Item.Properties().food(ModFoods.COCONUT_PIE_SLICE)));
    public static final Supplier<Item> HONEY_COCONUT_PIE = registerFlatItem("honey_coconut_pie",() -> new BlockItem(ModBlocks.HONEY_COCONUT_PIE.get(),new Item.Properties()));
    public static final Supplier<Item> HONEY_COCONUT_PIE_SLICE = registerFlatItem("honey_coconut_pie_slice",() -> new Item(new Item.Properties().food(ModFoods.COCONUT_PIE_SLICE)){
        @Override
        public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
            if (!level.isClientSide) {
                livingEntity.removeEffect(MobEffects.POISON);
            }
            return super.finishUsingItem(itemStack, level, livingEntity);
        }
    });

    public static final Supplier<Item> OMELETTE_FEAST = registerFlatItem("omelette_feast",() -> new BlockItem(ModBlocks.OMELETTE_FEAST.get(),new Item.Properties()));
    public static final Supplier<Item> OMELETTE = registerFlatItem("omelette",() -> new Item(bowlFoodItem(ModFoods.OMELETTE)));
    public static final Supplier<Item> BASIL_OMELETTE_FEAST = registerFlatItem("basil_omelette_feast",() -> new BlockItem(ModBlocks.BASIL_OMELETTE_FEAST.get(),new Item.Properties()));
    public static final Supplier<Item> BASIL_OMELETTE = registerFlatItem("basil_omelette",() -> new Item(bowlFoodItem(ModFoods.BASIL_OMELETTE)));

    public static final Supplier<Item> BAMBOO_SHOOT_SOUP = registerFlatItem("bamboo_shoot_soup",() -> new Item(bowlFoodItem(ModFoods.BAMBOO_SOUP)));
    public static final Supplier<Item> STEAMED_BAMBOO_SHOOT = registerFlatItem("steamed_bamboo_shoot", () ->new Item(bowlFoodItem(ModFoods.STEAMED_BAMBOO_SHOOT)));

    public static final Supplier<Item> BUTTERFLY_PEA = registerFlatItem("butterfly_pea",() -> new ItemNameBlockItem(ModBlocks.BUTTERFLY_PEA_WALL.get(),new Item.Properties()));
    //I KNOW IT'S NOT THAI. BUT THERE IS MORTAR AND BASIL IN THE SAME MOD. HOW COULD I MISS THIS OPPORTUNITY.
    public static final Supplier<Item> PESTO_SAUCE = registerFlatItem("pesto_sauce",() -> createConsumeableItem(new Item.Properties().food(ModFoods.PESTO_SAUCE).craftRemainder(Items.BOWL)));


    public static final Supplier<Item> BUTTERFLY_PEA_SEEDS = registerFlatItem("butterfly_pea_seeds", () ->new ItemNameBlockItem(ModBlocks.BUDDING_BUTTERFLY_PEA_BLOCK.get(),new Item.Properties()));
    public static final Supplier<Item> BUTTERFLY_PEA_TEA = registerFlatItem("butterfly_pea_tea",() -> createButterflyPeaTeaItem());

    public static final Supplier<Item> KHANOM_CHAN = registerFlatItem("khanom_chan",() -> new DyeableItem(new Item.Properties().food(ModFoods.KHANOM_CHAN)));
    public static final Supplier<Item> COCONUT_MILK_ICE_CREAM = register("coconut_milk_ice_cream",() -> new DyeableItem(bowlFoodItem(ModFoods.COCONUT_MILK_ICE_CREAM)));

    public static final Supplier<Item> BANANA_IN_COCONUT_MILK = registerFlatItem("banana_in_coconut_milk",() -> new Item(bowlFoodItem(ModFoods.BANANA_IN_COCONUT_MILK)));


    public static Supplier<Item> registerFlatItem(String id,Supplier<Item> item){
        Supplier<Item> registeredItem = register(id,item);
        FLAT_ITEMS.add(registeredItem);
        return registeredItem;
    }


    @ExpectPlatform
    public static Supplier<Item> register(String id, Supplier<Item> item){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<CreativeModeTab> registerCreativeTab(String id, Supplier<CreativeModeTab> supplier){
        throw new AssertionError();
    }

    public static void init() {
        Item.BY_BLOCK.put(ModBlocks.COCONUT_LEAF.get(),ModItems.COCONUT_LEAF.get());
        Item.BY_BLOCK.put(ModBlocks.HANGING_DURIAN.get(),ModItems.DURIAN.get());
        Item.BY_BLOCK.put(ModBlocks.HANGING_MANGO_BLOCK.get(),ModItems.MANGO.get());
        Item.BY_BLOCK.put(ModBlocks.PAPAYA.get(),ModItems.PAPAYA.get());
        Item.BY_BLOCK.put(ModBlocks.FERMENTED_FISH_CAULDRON.get(), Items.CAULDRON);
        Item.BY_BLOCK.put(ModBlocks.COCONUT_CAULDRON.get(),Items.CAULDRON);
        Item.BY_BLOCK.put(ModBlocks.COCONUT_MILK_CAULDRON.get(),Items.CAULDRON);
    }

    public static Item createConsumeableItem(Item.Properties properties){
        return createConsumeableItem(properties,false,false);
    }

    @ExpectPlatform
    public static Item createConsumeableItem(Item.Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Item.Properties bowlItem(FoodProperties foodProperties){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Item createDrinkableItem(Item.Properties properties,boolean hasPotionEffectTooltip,boolean hasCustomTooltip){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Item.Properties getDrinkItem(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Item createPapayaJuiceItem(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Item createLimeJuiceItem(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Item createHoneyLimeJuiceItem(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Item createButterflyPeaTeaItem(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Item createCoconutWaterJuiceItem(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Item> createBoat(ResourceLocation id, boolean chest, String boatType){
        throw new AssertionError();
    }
}