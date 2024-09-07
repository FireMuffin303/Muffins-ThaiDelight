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
import java.util.function.Supplier;

public class ModItems {
    //Blocks
    public static final Supplier<Item> MORTAR = register("mortar_and_pestle",() -> new BlockItem(ModBlocks.MORTAR.get(), new Item.Properties()));

    public static final Supplier<Item> LIME_CRATE = register("lime_crate",() -> new BlockItem(ModBlocks.LIME_CRATE.get(), new Item.Properties()));
    public static final Supplier<Item> PEPPER_CRATE = register("pepper_crate",() -> new BlockItem(ModBlocks.PEPPER_CRATE.get(), new Item.Properties()));
    public static final Supplier<Item> RAW_PEPPER_CRATE = register("raw_pepper_crate",() -> new BlockItem(ModBlocks.RAW_PAPAYA_CRATE.get(), new Item.Properties()));
    public static final Supplier<Item> PAPAYA_CRATE = register("papaya_crate",() -> new BlockItem(ModBlocks.PAPAYA_CRATE.get(), new Item.Properties()));

    public static final Supplier<Item> FLOWER_CRAB_EGG = register("flower_crab_egg",() -> new BlockItem(ModBlocks.CRAB_EGG.get(), new Item.Properties()));

    public static final Supplier<Item> WILD_PEPPER_CROP = register("wild_pepper",() -> new BlockItem(ModBlocks.WILD_PEPPER_CROP.get(), new Item.Properties()));
    public static final Supplier<Item> LIME_SAPLING = register("lime_sapling",() -> new BlockItem(ModBlocks.LIME_SAPLING.get(), new Item.Properties()));
    public static final Supplier<Item> PAPAYA_SAPLING = register("papaya_sapling",() -> new BlockItem(ModBlocks.PAPAYA_SAPLING.get(), new Item.Properties()));

    public static final Supplier<Item> SOMTAM_FEAST = register("somtam_feast",() -> new BlockItem(ModBlocks.SOMTAM_FEAST.get(), new Item.Properties()));
    public static final Supplier<Item> LARB_FEAST = register("larb_feast",() -> new BlockItem(ModBlocks.LARB_FEAST.get(), new Item.Properties()));
    public static final Supplier<Item> CRAB_FRIED_RICE_FEAST = register("crab_fried_rice_feast",() -> new BlockItem(ModBlocks.CRAB_FRIED_RICE_FEAST.get(), new Item.Properties()));

    public static final Supplier<Item> PAPAYA_LOG = register("papaya_log", () -> new BlockItem(ModBlocks.PAPAYA_LOG.get(), new Item.Properties()));
    public static final Supplier<Item> STRIPPED_PAPAYA_LOG = register("stripped_papaya_log",() -> new BlockItem(ModBlocks.STRIPPED_PAPAYA_LOG.get(), new Item.Properties()));
    public static final Supplier<Item> PAPAYA_WOOD = register("papaya_wood",() -> new BlockItem(ModBlocks.PAPAYA_WOOD.get(), new Item.Properties()));
    public static final Supplier<Item> STRIPPED_PAPAYA_WOOD = register("stripped_papaya_wood",() -> new BlockItem(ModBlocks.STRIPPED_PAPAYA_WOOD.get(), new Item.Properties()));
    public static final Supplier<Item> PAPAYA_LEAVES = register("papaya_leaves",() -> new BlockItem(ModBlocks.PAPAYA_LEAVES.get(), new Item.Properties()));



    //Crab
    public static final Supplier<Item>  CRAB_SPAWN_EGG = register("flower_crab_spawn_egg",() -> new SpawnEggItem(ModEntityTypes.FLOWER_CRAB,0x93a064,0xac3247,new Item.Properties()));

    public static final Supplier<Item> CRAB_BUCKET = register("flower_crab_bucket",() -> ModPlatform.registerMobBucket(ModEntityTypes.FLOWER_CRAB,()-> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH,new Item.Properties().stacksTo(1)));
    public static final Supplier<Item> CRAB_MEAT = register("flower_crab",() -> new Item(new Item.Properties().food(ModFood.CRAB)));
    public static final Supplier<Item> COOKED_CRAB_MEAT =  register("cooked_flower_crab", () -> new Item(new Item.Properties().food(ModFood.COOKED_CRAB)));

    //Dragonfly
    public static final Supplier<Item> DRAGONFLY_SPAWN_EGG = register("dragonfly_spawn_egg", () -> new SpawnEggItem(ModEntityTypes.DRAGONFLY,0x181d13,0x246011,new Item.Properties()));
    public static final Supplier<Item> DRAGONFLY = register("dragonfly",() -> new Item(new Item.Properties().food(ModFood.DRAGONFLY)));
    public static final Supplier<Item> DRAGONFLY_BOTTLE = register("dragon_bottle",() -> new DragonflyBottleItem(new Item.Properties().stacksTo(1)));
    public static final Supplier<Item> COOKED_DRAGONFLY = register("cooked_dragonfly",() -> new Item(new Item.Properties().food(ModFood.COOKED_DRAGONFLY)));


    //Bucket
    public static final Supplier<Item> FISH_SAUCE_BOTTLE = register("fish_sauce_bottle",() -> ModPlatform.getDrinkable(drinkItem().food(ModFood.FISH_SAUCE),true,false)) ;
    public static final Supplier<Item> FERMENTED_FISH_BOTTLE = register("fermented_fish_bottle",() -> ModPlatform.getDrinkable(drinkItem(),true,false));
    public static final Supplier<Item> PAPAYA_JUICE = register("papaya_juice", () -> ModPlatform.getPapayaJuice(drinkItem().food(ModFood.PAPAYA_JUICE)));
    public static final Supplier<Item> LIME_JUICE = register("lime_juice",() -> ModPlatform.getLimeJuice(drinkItem().food(ModFood.LIME_JUICE)));

    //Crops
    public static final Supplier<Item> LIME = register("lime",() -> new Item(new Item.Properties().food(ModFood.LIME)));
    public static final Supplier<Item> SLICED_LIME = register("lime_sliced", () -> new Item(new Item.Properties().food(ModFood.LIME_SLICE)));


    public static final Supplier<Item> PEPPER = register("pepper",() -> new Item(new Item.Properties().food(ModFood.PEPPER)){
        @Override
        public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
            livingEntity.setTicksFrozen(0);
            return super.finishUsingItem(itemStack, level, livingEntity);
        }
    });
    public static final Supplier<Item> PEPPER_SEED = register("pepper_seeds", () -> new ItemNameBlockItem(ModBlocks.PEPPER_CROP.get(),new Item.Properties()){
        @Override
        public void registerBlocks(Map<Block, Item> map, Item item) {
            super.registerBlocks(map, item);
            map.put(ModBlocks.PEPPER_CROP.get(),item);
        }
    });

    public static final Supplier<Item> PAPAYA = register("papaya",() -> new Item(new Item.Properties().food(ModFood.PAPAYA)));
    public static final Supplier<Item> SLICED_PAPAYA = register("papaya_slice",() -> new Item(new Item.Properties().food(ModFood.SLICED_PAPAYA)));
    public static final Supplier<Item> RAW_PAPAYA = register("raw_papaya", () -> new Item(new Item.Properties().food(ModFood.RAW_PAPAYA)));
    public static final Supplier<Item> RAW_PAPAYA_SLICE = register("raw_papaya_slice",() -> new Item(new Item.Properties().food(ModFood.SLICED_UNRIPE_PAPAYA)));
    public static final Supplier<Item> PAPAYA_SEEDS = register("papaya_seeds",() -> new ItemNameBlockItem(ModBlocks.PAPAYA_CROPS.get(),new Item.Properties()){
        @Override
        public void registerBlocks(Map<Block, Item> map, Item item) {
            super.registerBlocks(map, item);
            map.put(ModBlocks.PAPAYA_CROPS.get(),item);
        }
    });


    //Equipment
    //public static final Item LOINCLOTH = new LoinclothItem(new Item.Properties());

    //Disc
    //I can't write lugtoong music
    //public static final Item ESAN_MUSIC_DISC = new ModMusicDisc(3,SoundEvents.MUSIC_DISC_RELIC,new Item.Properties().stacksTo(1).rarity(Rarity.RARE),218);

    public static void init(){
    }


    public static Supplier<Item> register(String id,Supplier<Item> item){
       return ModPlatform.registryItem(id,item);
    }

    public static Item.Properties drinkItem() {
        return (new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16);
    }



    public static class ModFood{
        public static final FoodProperties CRAB = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).build();
        public static final FoodProperties COOKED_CRAB = new FoodProperties.Builder().nutrition(8).saturationModifier(0.5F).build();

        public static final FoodProperties COOKED_DRAGONFLY = new FoodProperties.Builder().nutrition(3).saturationModifier(0.2f).alwaysEdible().build();
        public static final FoodProperties DRAGONFLY = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).alwaysEdible().effect(new MobEffectInstance(MobEffects.HUNGER,10*20,0),0.8f).effect(new MobEffectInstance(MobEffects.CONFUSION,10*20,0),0.8f).build();

        public static final FoodProperties LIME = new FoodProperties.Builder().nutrition(4).saturationModifier(0.2F).build();
        public static final FoodProperties LIME_SLICE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).build();

        public static final FoodProperties PEPPER = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).alwaysEdible().fast().build();

        public static final FoodProperties SLICED_UNRIPE_PAPAYA = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).alwaysEdible().fast().build();
        public static final FoodProperties SLICED_PAPAYA = new FoodProperties.Builder().nutrition(3).saturationModifier(0.2F).build();
        public static final FoodProperties PAPAYA = new FoodProperties.Builder().nutrition(5).saturationModifier(0.4F).build();
        public static final FoodProperties RAW_PAPAYA = new FoodProperties.Builder().nutrition(4).saturationModifier(0.2F).build();

        public static final FoodProperties FISH_SAUCE = new FoodProperties.Builder().alwaysEdible().effect(new MobEffectInstance(MobEffects.HUNGER,200,0),1.0f).build();
        public static final FoodProperties SEAFOOD_SAUCE = new FoodProperties.Builder().alwaysEdible().effect(new MobEffectInstance(MobEffects.WATER_BREATHING,200,0),1.0f).build();
        public static final FoodProperties PAPAYA_JUICE = new FoodProperties.Builder().alwaysEdible().build();
        public static final FoodProperties LIME_JUICE = new FoodProperties.Builder().alwaysEdible().build();
    }

}
