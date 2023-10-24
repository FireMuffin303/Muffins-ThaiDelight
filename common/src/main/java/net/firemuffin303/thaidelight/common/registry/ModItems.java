package net.firemuffin303.thaidelight.common.registry;

import com.teamresourceful.resourcefullib.common.item.tabs.ResourcefulCreativeTab;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.item.FishSauceBottleItem;
import net.firemuffin303.thaidelight.common.item.ModSmithingTemplateItem;
import net.firemuffin303.thaidelight.common.item.WolfArmorItem;
import net.firemuffin303.thaidelight.utils.ModEntry;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.firemuffin303.thaidelight.utils.ModRegistryEntry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import software.bernie.shadowed.eliotlash.mclib.math.functions.classic.Mod;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ModItems {
    public static final ModRegistryEntry<Item> TITEMS = new ModRegistryEntry<>();
    public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(BuiltInRegistries.ITEM, ThaiDelight.MOD_ID);
    //Crab
    public static final Supplier<Item> CRAB_SPAWN_EGG = TITEMS.register("flower_crab_spawn_egg",
            ModPlatform.registryItem("flower_crab_spawn_egg",
                    ModPlatform.registerSpawnEgg(ModEntityTypes.FLOWER_CRAB,0x5c5dbc,0xf0784f,new Item.Properties())));

    public static final RegistryEntry<Item> CRAB_BUCKET = ITEMS.register("flower_crab_bucket",ModPlatform.registerMobBucket(ModEntityTypes.FLOWER_CRAB, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH,new Item.Properties().stacksTo(1)));
    public static final RegistryEntry<Item> CRAB_EGG = ITEMS.register("flower_crab_egg",() -> new BlockItem(ModBlocks.CRAB_EGG.get(), new Item.Properties()));
    public static final RegistryEntry<Item> CRAB_MEAT = ITEMS.register("flower_crab_meat",() -> new Item(new Item.Properties().food(ModFood.CRAB)));
    public static final RegistryEntry<Item> COOKED_CRAB_MEAT = ITEMS.register("cooked_flower_crab_meat",() -> new Item(new Item.Properties().food(ModFood.COOKED_CRAB)));
    public static final RegistryEntry<Item> CRAB_MEAT_WITH_SEAFOOD = ITEMS.register("cooked_seafood_flower_crab_meat",() -> new Item(new Item.Properties().food(ModFood.CRAB_WITH_SEAFOOD)));

    //Dragonfly
    public static final RegistryEntry<Item> DRAGONFLY_SPAWN_EGG = ITEMS.register("dragonfly_spawn_egg", ModPlatform.registerSpawnEgg(ModEntityTypes.DRAGONFLY,0x5c5dbc,0xf0784f,new Item.Properties()));
    public static final RegistryEntry<Item> DRAGONFLY = ITEMS.register("dragonfly",() ->new Item(new Item.Properties()));
    public static final RegistryEntry<Item> DRAGONFLY_BOTTLE = ITEMS.register("dragonfly_bottle",() ->new Item(new Item.Properties()));
    public static final RegistryEntry<Item> COOKED_DRAGONFLY = ITEMS.register("cooked_dragonfly",() ->new Item(new Item.Properties().food(ModFood.COOKED_DRAGONFLY)));

    //Block
    public static final Supplier<Item> MORTAR = TITEMS.register("mortar", ModPlatform.registryItem("mortar",() -> new BlockItem(ModBlocks.MORTAR.get(), new Item.Properties()))) ;

    //Bucket
    public static final RegistryEntry<Item> SEAFOOD_BUCKET = ITEMS.register("seafood_bucket",() -> new BucketItem(ModFluid.SEAFOOD.get(),new Item.Properties().stacksTo(1)));
    public static final RegistryEntry<Item> FISH_SAUCE_BUCKET = ITEMS.register("fish_sauce_bucket",() -> new BucketItem(ModFluid.SEAFOOD.get(),new Item.Properties().stacksTo(1)));
    public static final RegistryEntry<Item> FISH_SAUCE_BOTTLE = ITEMS.register("fish_sauce_bottle",() -> new FishSauceBottleItem(new Item.Properties().food(ModFood.FISH_SAUCE).craftRemainder(Items.GLASS_BOTTLE)));

    //Crops
    public static final RegistryEntry<Item> LIME = ITEMS.register("lime",() -> new Item(new Item.Properties()));


    public static final Supplier<CreativeModeTab> TAB = new ResourcefulCreativeTab(new ResourceLocation(ThaiDelight.MOD_ID,"main"))
            .setItemIcon(ModItems.MORTAR).addRegistry(ITEMS).build();

    public static final CreativeModeTab CTAB = ModPlatform.createCreativeModeTab(new ResourceLocation(ThaiDelight.MOD_ID,"main2"),() -> new ItemStack(ModItems.MORTAR.get()) ,TITEMS);

    static class ModFood{
        public static final FoodProperties CRAB = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).meat().build();
        public static final FoodProperties COOKED_CRAB = new FoodProperties.Builder().nutrition(5).saturationMod(0.3F).meat().build();
        public static final FoodProperties CRAB_WITH_SEAFOOD = new FoodProperties.Builder().nutrition(5).saturationMod(0.3F).alwaysEat().effect(new MobEffectInstance(MobEffects.WATER_BREATHING,200,0),1.0f).meat().build();

        public static final FoodProperties COOKED_DRAGONFLY = new FoodProperties.Builder().nutrition(3).saturationMod(0.2f).alwaysEat().build();
        public static final FoodProperties FISH_SAUCE = new FoodProperties.Builder().nutrition(4).saturationMod(0.1f).alwaysEat().effect(new MobEffectInstance(MobEffects.HUNGER,100,0),1.0f).build();
    }


}
