package net.firemuffin303.thaidelight.common.registry;

import com.google.common.collect.Sets;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.item.bottle.FishSauceBottleItem;
import net.firemuffin303.thaidelight.common.item.LoinclothItem;
import net.firemuffin303.thaidelight.common.item.bottle.SeafoodBottleItem;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class ModItems {
    public static final Set<Enchantment> DISALLOW_PESTLE_ENCHANTMENT = Sets.newHashSet(Enchantments.SHARPNESS,Enchantments.SMITE,Enchantments.BANE_OF_ARTHROPODS,Enchantments.SWEEPING_EDGE);
    public static final ArrayList<Item> ITEMS = new ArrayList<>();

    //Pastle
    public static final Item STONE_PASTLE = ModPlatform.createPastleItem(Tiers.STONE,3,-3.0f,new Item.Properties().stacksTo(1));
    public static final Item IRON_PASTLE = ModPlatform.createPastleItem(Tiers.IRON,3,-3.0f,new Item.Properties().stacksTo(1));
    public static final Item GOLDEN_PASTLE = ModPlatform.createPastleItem(Tiers.GOLD,3,-3.0f,new Item.Properties().stacksTo(1));
    public static final Item DIAMOND_PASTLE = ModPlatform.createPastleItem(Tiers.DIAMOND,3,-3.0f,new Item.Properties().stacksTo(1));
    public static final Item NETHERITE_PASTLE = ModPlatform.createPastleItem(Tiers.NETHERITE,3,-3.0f,new Item.Properties().stacksTo(1).fireResistant());

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
    public static final Item SEAFOOD_BOTTLE = new SeafoodBottleItem(new Item.Properties().stacksTo(16).craftRemainder(Items.GLASS_BOTTLE));
    public static final Item FISH_SAUCE_BOTTLE =  ModPlatform.getDrinkable(drinkItem().food(ModFood.FISH_SAUCE),true,false);
    public static final Item PAPAYA_JUICE = ModPlatform.getPapayaJuice(drinkItem().food(ModFood.PAPAYA_JUICE));
    public static final Item LIME_JUICE = ModPlatform.getLimeJuice(drinkItem().food(ModFood.LIME_JUICE));

    //Crops
    public static final Item LIME = new Item(new Item.Properties());
    public static final Item SLICED_LIME = new Item(new Item.Properties());
    public static final Item LIME_SEED = new ItemNameBlockItem(ModBlocks.LIME_CROP,new Item.Properties()){
        @Override
        public void registerBlocks(Map<Block, Item> map, Item item) {
            super.registerBlocks(map, item);
            map.put(ModBlocks.LIME_CROP,item);
        }
    };

    public static final Item PEPPER = new Item(new Item.Properties().food(ModFood.PEPPER)){
        @Override
        public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
            livingEntity.setTicksFrozen(0);
            return super.finishUsingItem(itemStack, level, livingEntity);
        }
    };
    public static final Item PEPPER_SEED = new ItemNameBlockItem(ModBlocks.PEPPER_CROP,new Item.Properties()){
        @Override
        public void registerBlocks(Map<Block, Item> map, Item item) {
            super.registerBlocks(map, item);
            map.put(ModBlocks.PEPPER_CROP,item);
        }
    };

    public static final Item PAPAYA = new Item(new Item.Properties());
    public static final Item SLICED_PAPAYA = new Item(new Item.Properties().food(ModFood.SLICED_PAPAYA));
    public static final Item UNRIPE_PAPAYA = new Item(new Item.Properties());
    public static final Item SLICED_UNRIPE_PAPAYA = new Item(new Item.Properties().food(ModFood.SLICED_UNRIPE_PAPAYA));
    public static final Item PAPAYA_SEED = new ItemNameBlockItem(ModBlocks.PAPAYA_CROP,new Item.Properties()){
        @Override
        public void registerBlocks(Map<Block, Item> map, Item item) {
            super.registerBlocks(map, item);
            map.put(ModBlocks.PAPAYA_CROP,item);
        }
    };

    //Equipment
    public static final Item LOINCLOTH = new LoinclothItem(new Item.Properties());

    public static void init(){
        register("stone_pastle",STONE_PASTLE);
        register("iron_pastle",IRON_PASTLE);
        register("golden_pastle",GOLDEN_PASTLE);
        register("diamond_pastle",DIAMOND_PASTLE);
        register("netherite_pastle",NETHERITE_PASTLE);

        register("flower_crab_spawn_egg",CRAB_SPAWN_EGG);
        register("flower_crab_bucket",CRAB_BUCKET);
        register("flower_crab",CRAB_MEAT);
        register("cooked_flower_crab",COOKED_CRAB_MEAT);
        register("cooked_seafood_flower_crab",CRAB_MEAT_WITH_SEAFOOD);

        register("dragonfly_spawn_egg",DRAGONFLY_SPAWN_EGG);
        register("dragonfly",DRAGONFLY);
        register("dragonfly_bottle",DRAGONFLY_BOTTLE);
        register("cooked_dragonfly",COOKED_DRAGONFLY);


        register("seafood_bottle", SEAFOOD_BOTTLE);
        register("fish_sauce_bottle",FISH_SAUCE_BOTTLE);
        register("papaya_juice",PAPAYA_JUICE);
        register("lime_juice",LIME_JUICE);

        register("lime",LIME);
        register("sliced_lime",SLICED_LIME);
        register("lime_seeds",LIME_SEED);

        register("pepper",PEPPER);
        register("pepper_seeds",PEPPER_SEED);

        register("papaya",PAPAYA);
        register("sliced_papaya",SLICED_PAPAYA);
        register("unripe_papaya",UNRIPE_PAPAYA);
        register("sliced_unripe_papaya",SLICED_UNRIPE_PAPAYA);
        register("papaya_seeds",PAPAYA_SEED);



        //register("somtam",SOMTAM);
        //register("spicy_minced_pork_salad",SPICY_MINCED_PORK_SALAD);
        //register("crab_fried_rice",CRAB_FRIED_RICE);

        register("loincloth",LOINCLOTH);


    }



    public static final CreativeModeTab CTAB = ModPlatform.createCreativeModeTab(new ResourceLocation(ThaiDelight.MOD_ID,"main"),() -> new ItemStack(ModBlocks.MORTAR) ,ITEMS);

    public static void register(String id,Item item){
        ModPlatform.registryItem(id,() -> item);
        ITEMS.add(item);
    }

    public static Item.Properties drinkItem() {
        return (new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16);
    }



    public static class ModFood{
        public static final FoodProperties CRAB = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).meat().build();
        public static final FoodProperties COOKED_CRAB = new FoodProperties.Builder().nutrition(5).saturationMod(0.3F).meat().build();
        public static final FoodProperties CRAB_WITH_SEAFOOD = new FoodProperties.Builder().nutrition(5).saturationMod(0.3F).alwaysEat().effect(new MobEffectInstance(MobEffects.WATER_BREATHING,200,0),1.0f).meat().build();

        public static final FoodProperties COOKED_DRAGONFLY = new FoodProperties.Builder().nutrition(3).saturationMod(0.2f).alwaysEat().build();

        public static final FoodProperties PEPPER = new FoodProperties.Builder().nutrition(1).saturationMod(0.1F).alwaysEat().fast().build();

        public static final FoodProperties SLICED_UNRIPE_PAPAYA = new FoodProperties.Builder().nutrition(1).saturationMod(0.1F).alwaysEat().fast().build();
        public static final FoodProperties SLICED_PAPAYA = new FoodProperties.Builder().nutrition(5).saturationMod(0.2F).alwaysEat().fast().build();

        public static final FoodProperties FISH_SAUCE = new FoodProperties.Builder().alwaysEat().effect(new MobEffectInstance(MobEffects.HUNGER,200,0),1.0f).build();
        public static final FoodProperties SEAFOOD_SAUCE = new FoodProperties.Builder().alwaysEat().effect(new MobEffectInstance(MobEffects.WATER_BREATHING,200,0),1.0f).build();
        public static final FoodProperties PAPAYA_JUICE = new FoodProperties.Builder().alwaysEat().build();
        public static final FoodProperties LIME_JUICE = new FoodProperties.Builder().alwaysEat().build();
    }


}
