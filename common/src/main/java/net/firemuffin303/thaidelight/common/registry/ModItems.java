package net.firemuffin303.thaidelight.common.registry;

import com.google.common.collect.Sets;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.entity.Dragonfly;
import net.firemuffin303.thaidelight.common.item.ModMusicDisc;
import net.firemuffin303.thaidelight.common.item.bottle.DragonflyBottleItem;
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
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class ModItems {
    public static final Set<Enchantment> DISALLOW_PESTLE_ENCHANTMENT = Sets.newHashSet(Enchantments.SHARPNESS,Enchantments.SMITE,Enchantments.BANE_OF_ARTHROPODS,Enchantments.SWEEPING_EDGE);
    public static final ArrayList<ItemLike> ITEMS = new ArrayList<>();

    //Crab
    public static final Item CRAB_SPAWN_EGG = ModPlatform.registerSpawnEgg(ModEntityTypes.FLOWER_CRAB,0x93a064,0xac3247,new Item.Properties());

    public static final Item CRAB_BUCKET = ModPlatform.registerMobBucket(ModEntityTypes.FLOWER_CRAB,()-> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH,new Item.Properties().stacksTo(1));
    public static final Item CRAB_MEAT = new Item(new Item.Properties().food(ModFood.CRAB));
    public static final Item COOKED_CRAB_MEAT =  new Item(new Item.Properties().food(ModFood.COOKED_CRAB));

    //Dragonfly
    public static final Item DRAGONFLY_SPAWN_EGG = ModPlatform.registerSpawnEgg(ModEntityTypes.DRAGONFLY,0x181d13,0x246011,new Item.Properties());
    public static final Item DRAGONFLY = new Item(new Item.Properties().food(ModFood.DRAGONFLY));
    public static final Item DRAGONFLY_BOTTLE = new DragonflyBottleItem(new Item.Properties().stacksTo(1));
    public static final Item COOKED_DRAGONFLY = new Item(new Item.Properties().food(ModFood.COOKED_DRAGONFLY));


    //Bucket
    public static final Item FISH_SAUCE_BOTTLE =  ModPlatform.getDrinkable(drinkItem().food(ModFood.FISH_SAUCE),true,false);
    public static final Item FERMENTED_FISH_BOTTLE = ModPlatform.getDrinkable(drinkItem(),true,false);
    public static final Item PAPAYA_JUICE = ModPlatform.getPapayaJuice(drinkItem().food(ModFood.PAPAYA_JUICE));
    public static final Item LIME_JUICE = ModPlatform.getLimeJuice(drinkItem().food(ModFood.LIME_JUICE));

    //Crops
    public static final Item LIME = new Item(new Item.Properties().food(ModFood.LIME));
    public static final Item SLICED_LIME = new Item(new Item.Properties().food(ModFood.LIME_SLICE));


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

    public static final Item PAPAYA = new Item(new Item.Properties().food(ModFood.PAPAYA));
    public static final Item SLICED_PAPAYA = new Item(new Item.Properties().food(ModFood.SLICED_PAPAYA));
    public static final Item RAW_PAPAYA = new Item(new Item.Properties().food(ModFood.RAW_PAPAYA));
    public static final Item RAW_PAPAYA_SLICE = new Item(new Item.Properties().food(ModFood.SLICED_UNRIPE_PAPAYA));
    public static final Item PAPAYA_SEEDS = new ItemNameBlockItem(ModBlocks.PAPAYA_CROPS,new Item.Properties()){
        @Override
        public void registerBlocks(Map<Block, Item> map, Item item) {
            super.registerBlocks(map, item);
            map.put(ModBlocks.PAPAYA_CROPS,item);
        }
    };


    //Equipment
    //public static final Item LOINCLOTH = new LoinclothItem(new Item.Properties());

    //Disc
    //I can't write lugtoong music
    //public static final Item ESAN_MUSIC_DISC = new ModMusicDisc(3,SoundEvents.MUSIC_DISC_RELIC,new Item.Properties().stacksTo(1).rarity(Rarity.RARE),218);

    public static void init(){
        register("flower_crab_spawn_egg",CRAB_SPAWN_EGG);
        register("flower_crab_bucket",CRAB_BUCKET);
        register("flower_crab",CRAB_MEAT);
        register("cooked_flower_crab",COOKED_CRAB_MEAT);

        register("dragonfly_spawn_egg",DRAGONFLY_SPAWN_EGG);
        register("dragonfly",DRAGONFLY);
        register("dragonfly_bottle",DRAGONFLY_BOTTLE);
        register("cooked_dragonfly",COOKED_DRAGONFLY);

        register("fish_sauce_bottle",FISH_SAUCE_BOTTLE);
        register("fermented_fish_bottle",FERMENTED_FISH_BOTTLE);
        register("papaya_juice",PAPAYA_JUICE);
        register("lime_juice",LIME_JUICE);

        register("lime",LIME);
        register("lime_slice",SLICED_LIME);

        register("pepper",PEPPER);
        register("pepper_seeds",PEPPER_SEED);

        register("papaya",PAPAYA);
        register("papaya_slice",SLICED_PAPAYA);
        register("raw_papaya", RAW_PAPAYA);
        register("raw_papaya_slice", RAW_PAPAYA_SLICE);
        register("papaya_seeds", PAPAYA_SEEDS);
    }


    public static void register(String id,Item item){
        ModPlatform.registryItem(id,() -> item);
        ITEMS.add(item);
    }

    public static Item.Properties drinkItem() {
        return (new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16);
    }



    public static class ModFood{
        public static final FoodProperties CRAB = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).meat().build();
        public static final FoodProperties COOKED_CRAB = new FoodProperties.Builder().nutrition(8).saturationMod(0.5F).meat().build();

        public static final FoodProperties COOKED_DRAGONFLY = new FoodProperties.Builder().nutrition(3).saturationMod(0.2f).alwaysEat().build();
        public static final FoodProperties DRAGONFLY = new FoodProperties.Builder().nutrition(2).saturationMod(0.1f).alwaysEat().effect(new MobEffectInstance(MobEffects.HUNGER,10*20,0),0.8f).effect(new MobEffectInstance(MobEffects.CONFUSION,10*20,0),0.8f).build();

        public static final FoodProperties LIME = new FoodProperties.Builder().nutrition(4).saturationMod(0.2F).build();
        public static final FoodProperties LIME_SLICE = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build();

        public static final FoodProperties PEPPER = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).alwaysEat().fast().build();

        public static final FoodProperties SLICED_UNRIPE_PAPAYA = new FoodProperties.Builder().nutrition(1).saturationMod(0.1F).alwaysEat().fast().build();
        public static final FoodProperties SLICED_PAPAYA = new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).build();
        public static final FoodProperties PAPAYA = new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).build();
        public static final FoodProperties RAW_PAPAYA = new FoodProperties.Builder().nutrition(4).saturationMod(0.2F).build();

        public static final FoodProperties FISH_SAUCE = new FoodProperties.Builder().alwaysEat().effect(new MobEffectInstance(MobEffects.HUNGER,200,0),1.0f).build();
        public static final FoodProperties SEAFOOD_SAUCE = new FoodProperties.Builder().alwaysEat().effect(new MobEffectInstance(MobEffects.WATER_BREATHING,200,0),1.0f).build();
        public static final FoodProperties PAPAYA_JUICE = new FoodProperties.Builder().alwaysEat().build();
        public static final FoodProperties LIME_JUICE = new FoodProperties.Builder().alwaysEat().build();
    }

}
