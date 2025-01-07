package net.firemuffin303.muffinsthaidelightfabric.registry;


import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.item.DragonflyBottleItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
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
    public static final ArrayList<Item> ITEMS = new ArrayList<>();
    public static final Item MORTAR = register("mortar",new BlockItem(ModBlocks.MORTAR,new Item.Properties()));

    //Blocks
    public static final Item LIME_CRATE = register("lime_crate",new BlockItem(ModBlocks.LIME_CRATE,new Item.Properties()));
    public static final Item PEPPER_CRATE = register("pepper_crate",new BlockItem(ModBlocks.PEPPER_CRATE,new Item.Properties()));
    public static final Item RAW_PAPAYA_CRATE = register("raw_papaya_crate",new BlockItem(ModBlocks.RAW_PAPAYA_CRATE,new Item.Properties()));
    public static final Item PAPAYA_CRATE = register("papaya_crate",new BlockItem(ModBlocks.PAPAYA_CRATE,new Item.Properties()));

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
    public static final Item BUFFALO_SPAWN_EGG = register("buffalo_spawn_egg",new SpawnEggItem(ModEntityTypes.BUFFALO,0x343639,0x444444,new Item.Properties()));

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

    //---Food---
    public static final Item SOMTAM_FEAST = register("somtam_feast",new BlockItem(ModBlocks.SOMTAM_FEAST,new Item.Properties()));
    public static final Item SOMTAM = register("somtam",new ConsumableItem(bowlFoodItem(ModFood.SOMTAM)){
        @Override
        public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
            super.affectConsumer(stack, level, consumer);
            consumer.setTicksFrozen(0);
        }
    });

    public static final Item LARB_FEAST = register("larb_feast",new BlockItem(ModBlocks.LARB_FEAST,new Item.Properties()));
    public static final Item LARB = register("larb",new ConsumableItem(bowlFoodItem(ModFood.LARB)));
    public static final Item CRAB_FRIED_RICE_FEAST = register("crab_fried_rice_feast",new BlockItem(ModBlocks.CRAB_FRIED_RICE_FEAST,new Item.Properties()));
    public static final Item CRAB_FRIED_RICE = register("crab_fried_rice",new ConsumableItem(bowlFoodItem(ModFood.CRAB_FRIED_RICE)));
    public static final Item STIR_FRIED_NOODLE = register("stir_fried_noodle",new ConsumableItem(bowlFoodItem(ModFood.STIR_FRIED_NOODLE)));

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

        public static final FoodProperties LIME = new FoodProperties.Builder().nutrition(4).saturationMod(0.2F)
                .effect(new MobEffectInstance(ModMobEffects.GLUTTONY,10*20),1.0f)
                .build();
        public static final FoodProperties LIME_SLICE = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F)
                .effect(new MobEffectInstance(ModMobEffects.GLUTTONY,5*20),1.0f)
                .build();

        public static final FoodProperties PEPPER = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).alwaysEat().fast().build();

        public static final FoodProperties SLICED_UNRIPE_PAPAYA = new FoodProperties.Builder().nutrition(1).saturationMod(0.1F).alwaysEat().fast().build();
        public static final FoodProperties SLICED_PAPAYA = new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).build();
        public static final FoodProperties PAPAYA = new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).build();
        public static final FoodProperties RAW_PAPAYA = new FoodProperties.Builder().nutrition(4).saturationMod(0.2F).build();

        public static final FoodProperties FISH_SAUCE = new FoodProperties.Builder().alwaysEat().effect(new MobEffectInstance(MobEffects.HUNGER,200,0),1.0f).build();
        public static final FoodProperties SEAFOOD_SAUCE = new FoodProperties.Builder().alwaysEat().effect(new MobEffectInstance(MobEffects.WATER_BREATHING,200,0),1.0f).build();
        public static final FoodProperties PAPAYA_JUICE = new FoodProperties.Builder().alwaysEat().build();
        public static final FoodProperties LIME_JUICE = new FoodProperties.Builder().alwaysEat().build();

        public static final FoodProperties SOMTAM = new FoodProperties.Builder().nutrition(16).saturationMod(0.6F).effect(new MobEffectInstance(ModEffects.NOURISHMENT.get(),6000,0),1.0f).build();
        public static final FoodProperties CRAB_FRIED_RICE = new FoodProperties.Builder().nutrition(16).saturationMod(0.8F).effect(new MobEffectInstance(ModEffects.COMFORT.get(),6000,0),1.0f).build();
        public static final FoodProperties LARB = new FoodProperties.Builder()
                .nutrition(14)
                .saturationMod(0.75F)
                .effect(new MobEffectInstance(ModEffects.NOURISHMENT.get(),5000,0),1.0f).build();

        public static final FoodProperties STIR_FRIED_NOODLE = new FoodProperties.Builder()
                .nutrition(12)
                .saturationMod(0.65F)
                .effect(new MobEffectInstance(ModEffects.COMFORT.get(),5000,0),1.0f).build();
        public static final FoodProperties FERMENTED_FISH = new FoodProperties.Builder()
                .nutrition(7).saturationMod(0.35f).effect(new MobEffectInstance(ModMobEffects.STINKY,10*20),0.5f).build();
    }

}
