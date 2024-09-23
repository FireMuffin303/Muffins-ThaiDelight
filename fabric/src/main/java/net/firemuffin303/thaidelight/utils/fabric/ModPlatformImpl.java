package net.firemuffin303.thaidelight.utils.fabric;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.fabric.common.item.LimeJuiceItem;
import net.firemuffin303.thaidelight.fabric.common.item.PapayaJuiceItem;
import net.firemuffin303.thaidelight.fabric.common.item.PastleItem;
import net.firemuffin303.thaidelight.fabric.common.item.SomtamItem;
import net.firemuffin303.thaidelight.fabric.common.registry.ModBlocksFabric;
import net.firemuffin303.thaidelight.fabric.common.registry.ModItemsFabric;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.material.Fluid;
import vectorwing.farmersdelight.common.block.WildCropBlock;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ModPlatformImpl {

    public static <T extends Block> Supplier<T> registryBlock(ResourceLocation resourceLocation, Supplier<T> block) {
        T returnBlock = Registry.register(BuiltInRegistries.BLOCK,resourceLocation,block.get());
        return () -> returnBlock;
    }

    public static <T extends Item> Supplier<Item> registryItem(ResourceLocation resourceLocation, Supplier<T> item) {
        T returnItem = Registry.register(BuiltInRegistries.ITEM,resourceLocation,item.get());
        return () -> returnItem;
    }

    public static <T extends BlockEntity> void registerBlockEntity(ResourceLocation resourceLocation,BlockEntityType<T> blockEntityType) {
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,resourceLocation,blockEntityType);
    }

    public static  <T extends Recipe<?>> Supplier<RecipeType<T>> registerRecipeType(ResourceLocation resourceLocation,Supplier<RecipeType<T>> recipeType) {
        RecipeType<T> rtRecipe = Registry.register(BuiltInRegistries.RECIPE_TYPE, resourceLocation, recipeType.get());
        return () -> rtRecipe;
    }

    public static <T extends Entity> Supplier<EntityType<T>> registerEntityType(ResourceLocation resourceLocation, Supplier<EntityType<T>>entityType) {
        EntityType<T> rtEntityType = Registry.register(BuiltInRegistries.ENTITY_TYPE,resourceLocation ,entityType.get());
        return () -> rtEntityType;
    }

    public static Supplier<SoundEvent> registerSoundEvent(ResourceLocation resourceLocation,Supplier<SoundEvent> event) {
        SoundEvent soundEvent = Registry.register(BuiltInRegistries.SOUND_EVENT,resourceLocation,event.get());
        return () -> soundEvent;
    }

    public static void registerFluid(String id, Fluid fluid) {
        Registry.register(BuiltInRegistries.FLUID,ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,id),fluid);
    }

    public static void registerMobEffect(String id, MobEffect mobEffect) {
        Registry.register(BuiltInRegistries.MOB_EFFECT,ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,id),mobEffect);
    }

    public static void registerPotion(String id, Potion potion) {
        Registry.register(BuiltInRegistries.POTION,ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,id),potion);
    }

    public static <T extends Recipe<?>> void registerRecipeSerializer(String id, RecipeSerializer<T> recipeSerializer) {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,id),recipeSerializer);
    }

    public static <M extends AbstractContainerMenu,U extends Screen & MenuAccess<M>> void registerScreen(MenuType<M> menuType, ModPlatform.ScreenConstructor<M, U> screen) {
        MenuScreens.register(menuType,screen::create);
    }

    public static <T extends TreeDecorator> TreeDecoratorType<T> registerTreeDecorator(String id, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE,ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,id),new TreeDecoratorType<T>(codec));
    }

    public static CreativeModeTab createCreativeModeTab(ResourceLocation resourceLocation, Supplier<ItemStack> icon, ArrayList<ItemLike> itemList) {
        CreativeModeTab creativeModeTab = FabricItemGroup.builder()
                .title(Component.translatable("itemGroup."+resourceLocation.getNamespace()+"."+resourceLocation.getPath()))
                .icon(icon)
                .displayItems((itemDisplayParameters, output) -> itemList.forEach(output::accept)).build();
        CreativeModeTab creativeModeTab1 = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,resourceLocation,creativeModeTab);
        return creativeModeTab1;
    }

    public static CreativeModeTab.Builder createCreativeModeTab(CreativeModeTab.Builder builder) {
        return builder;
    }

    //---------------------------------------------------------------------

    public static <T extends Entity> void registerEntityRenderer(EntityType<T> entityTypeSupplier, EntityRendererProvider<T> entityRendererProvider) {
        EntityRendererRegistry.register(entityTypeSupplier,entityRendererProvider);

    }


    public static  <T extends Mob> Item registerSpawnEgg(EntityType<T> entityType, int primaryColor, int secondaryColor, Item.Properties properties) {
        return new SpawnEggItem(entityType,primaryColor,secondaryColor,properties);
    }

    public static <T extends Mob> Item registerMobBucket(EntityType<T> entityType, Supplier<? extends Fluid> fluid, Supplier<? extends SoundEvent> soundEvent, Item.Properties properties) {
        return new MobBucketItem(entityType,fluid.get(),soundEvent.get(),properties);
    }

    public static <T extends BlockEntity> BlockEntityType.Builder<T> buildBlockEntity(ModBlocks.ModBlockEntityTypes.BlockEntitySupplier<T> blockEntityTypeSupplier, Block block) {
        return BlockEntityType.Builder.of(blockEntityTypeSupplier::create,block);
    }

    public static <T extends BlockEntity> void registerBlockEntityRenderer(BlockEntityType<T> blockEntityTypeSupplier, BlockEntityRendererProvider<T> blockEntityRendererProvider) {
        BlockEntityRendererRegistry.register(blockEntityTypeSupplier,blockEntityRendererProvider::create);
    }

    public static Block getSomtamBlock() {
        return ModBlocksFabric.SOMTAM_FEAST;
    }

    public static Block getSpicyMincedPorkBlock() {
        return ModBlocksFabric.SPICY_MINCED_PORK_SALAD_FEAST;
    }

    public static Block getCrabFriedRice() {
        return ModBlocksFabric.CRAB_FRIED_RICE;
    }

    public static Item getConsumableItem(FoodProperties foodProperties, boolean effectTooltips) {
        return new ConsumableItem(ModItemsFabric.foodBowl(foodProperties),effectTooltips);
    }

    public static Holder<MobEffect> getNourishment() {
        return ModEffects.NOURISHMENT;
    }

    public static Holder<MobEffect> getComfort() {
        return ModEffects.COMFORT;
    }

    public static Item createPastleItem(Tier tier, int attackDamage, float attackSpeed, Item.Properties properties) {
        return new PastleItem(tier,attackDamage,attackSpeed,properties);
    }

    public static Class<? extends Item> getPastleClass() {
        return PastleItem.class;
    }

    public static Block getWildCropBlock(Holder<MobEffect> mobEffect, int duration, BlockBehaviour.Properties properties) {
        return new WildCropBlock(mobEffect,duration,properties);
    }

    public static Item getDrinkable(Item.Properties properties, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        return new DrinkableItem(properties, hasFoodEffectTooltip,hasCustomTooltip);
    }

    public static Item getPapayaJuice(Item.Properties properties) {
        return new PapayaJuiceItem(properties);
    }

    public static Item getLimeJuice(Item.Properties properties) {
        return new LimeJuiceItem(properties);
    }

    public static <T extends AbstractContainerMenu> MenuType<T> registryMenu(String id, ModPlatform.MenuSupplier<T> menu) {
        return Registry.register(BuiltInRegistries.MENU,ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,id),new MenuType<T>(menu::create, FeatureFlags.VANILLA_SET));
    }

    public static void registerStrippables(Map<Block, Block> blockBlockMap) {
        blockBlockMap.forEach(StrippableBlockRegistry::register);
    }
}
