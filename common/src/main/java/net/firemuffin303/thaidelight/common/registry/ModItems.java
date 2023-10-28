package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.item.FishSauceBottleItem;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;

import java.util.ArrayList;
import java.util.Map;

public class ModItems {
    public static final ArrayList<Item> ITEMS = new ArrayList<>();

    //Crab
    public static final Item CRAB_SPAWN_EGG = ModPlatform.registerSpawnEgg(ModEntityTypes.FLOWER_CRAB,0x5c5dbc,0xf0784f,new Item.Properties());

    public static final Item CRAB_BUCKET = ModPlatform.registerMobBucket(ModEntityTypes.FLOWER_CRAB,()-> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH,new Item.Properties().stacksTo(1));
    public static final Item CRAB_MEAT = new Item(new Item.Properties().food(ModFood.CRAB));
    public static final Item COOKED_CRAB_MEAT =  new Item(new Item.Properties().food(ModFood.COOKED_CRAB));
    public static final Item CRAB_MEAT_WITH_SEAFOOD =  new Item(new Item.Properties().food(ModFood.CRAB_WITH_SEAFOOD));

    //Dragonfly
    public static final Item DRAGONFLY_SPAWN_EGG = ModPlatform.registerSpawnEgg(ModEntityTypes.DRAGONFLY,0x5c5dbc,0xf0784f,new Item.Properties());
    public static final Item DRAGONFLY = new Item(new Item.Properties());
    public static final Item DRAGONFLY_BOTTLE = new Item(new Item.Properties());
    public static final Item COOKED_DRAGONFLY = new Item(new Item.Properties().food(ModFood.COOKED_DRAGONFLY));


    //Bucket
    public static final Item SEAFOOD_BUCKET = new BucketItem(ModFluid.SEAFOOD,new Item.Properties().stacksTo(1));
    public static final Item FISH_SAUCE_BUCKET =  new BucketItem(ModFluid.SEAFOOD,new Item.Properties().stacksTo(1));
    public static final Item FISH_SAUCE_BOTTLE =  new FishSauceBottleItem(new Item.Properties().food(ModFood.FISH_SAUCE).craftRemainder(Items.GLASS_BOTTLE));

    //Crops
    public static final Item LIME = new Item(new Item.Properties());
    public static final Item LIME_SEED = new ItemNameBlockItem(ModBlocks.LIME_BUSH,new Item.Properties()){
        @Override
        public void registerBlocks(Map<Block, Item> map, Item item) {
            super.registerBlocks(map, item);
            map.put(ModBlocks.LIME_BUSH,item);
        }
    };
    public static final Item PEPPER_SEED = new ItemNameBlockItem(ModBlocks.PEPPER_CROP,new Item.Properties()){
        @Override
        public void registerBlocks(Map<Block, Item> map, Item item) {
            super.registerBlocks(map, item);
            map.put(ModBlocks.PEPPER_CROP,item);
        }
    };
    public static final Item PEPPER = new Item(new Item.Properties());
    public static final Item PAPAYA = new Item(new Item.Properties());
    public static final Item PAPAYA_SEED = new ItemNameBlockItem(ModBlocks.PAPAYA_CROP,new Item.Properties()){
        @Override
        public void registerBlocks(Map<Block, Item> map, Item item) {
            super.registerBlocks(map, item);
            map.put(ModBlocks.PAPAYA_CROP,item);
        }
    };

    public static void init(){
        register("flower_crab_spawn_egg",CRAB_SPAWN_EGG);
        register("flower_crab_bucket",CRAB_BUCKET);
        register("flower_crab_meat",CRAB_MEAT);
        register("cooked_flower_crab_meat",COOKED_CRAB_MEAT);
        register("cooked_seafood_flower_crab_meat",CRAB_MEAT_WITH_SEAFOOD);

        register("dragonfly_spawn_egg",DRAGONFLY_SPAWN_EGG);
        register("dragonfly",DRAGONFLY);
        register("dragonfly_bottle",DRAGONFLY_BOTTLE);
        register("cooked_dragonfly",COOKED_DRAGONFLY);


        register("seafood_bucket",SEAFOOD_BUCKET);
        register("fish_sauce_bucket",FISH_SAUCE_BUCKET);
        register("fish_sauce_bottle",FISH_SAUCE_BOTTLE);

        register("lime_seeds",LIME_SEED);
        register("lime",LIME);
        register("pepper",PEPPER);
        register("pepper_seeds",PEPPER_SEED);
        register("papaya",PAPAYA);
        register("papaya_seeds",PAPAYA_SEED);
    }



    public static final CreativeModeTab CTAB = ModPlatform.createCreativeModeTab(new ResourceLocation(ThaiDelight.MOD_ID,"main"),() -> new ItemStack(ModBlocks.MORTAR) ,ITEMS);

    public static void register(String id,Item item){
        ModPlatform.registryItem(id,() -> item);
        ITEMS.add(item);
    }



    static class ModFood{
        public static final FoodProperties CRAB = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).meat().build();
        public static final FoodProperties COOKED_CRAB = new FoodProperties.Builder().nutrition(5).saturationMod(0.3F).meat().build();
        public static final FoodProperties CRAB_WITH_SEAFOOD = new FoodProperties.Builder().nutrition(5).saturationMod(0.3F).alwaysEat().effect(new MobEffectInstance(MobEffects.WATER_BREATHING,200,0),1.0f).meat().build();

        public static final FoodProperties COOKED_DRAGONFLY = new FoodProperties.Builder().nutrition(3).saturationMod(0.2f).alwaysEat().build();
        public static final FoodProperties FISH_SAUCE = new FoodProperties.Builder().nutrition(4).saturationMod(0.1f).alwaysEat().effect(new MobEffectInstance(MobEffects.HUNGER,100,0),1.0f).build();
    }


}
