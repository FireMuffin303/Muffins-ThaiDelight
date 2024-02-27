package net.firemuffin303.thaidelight.utils.forge;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.forge.ThaiDelightForge;
import net.firemuffin303.thaidelight.forge.common.item.LimeJuiceItem;
import net.firemuffin303.thaidelight.forge.common.item.PapayaJuiceItem;
import net.firemuffin303.thaidelight.forge.common.item.PastleItem;
import net.firemuffin303.thaidelight.forge.common.item.SomtamItem;
import net.firemuffin303.thaidelight.forge.common.registry.ModBlocksForge;
import net.firemuffin303.thaidelight.forge.common.registry.ModItemsForge;
import net.firemuffin303.thaidelight.forge.mixin.AxeItemAccessor;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
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
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
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
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.util.RecipeMatcher;
import vectorwing.farmersdelight.common.block.WildCropBlock;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.registry.ModEffects;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ModPlatformImpl {

    public static <T extends Block> void registryBlock(String id, Supplier<T> block) {
        ThaiDelightForge.BLOCK.register(id,block);
    }


    public static <T extends Item> void registryItem(String id, Supplier<T> item) {
        ThaiDelightForge.ITEMS.register(id,item);
    }

    public static <T extends Entity> void registerEntityType(String id, EntityType<T> entityType) {
        ThaiDelightForge.ENTITY_TYPES.register(id,()->entityType);
    }

    public static SoundEvent registerSoundEvent(String id,SoundEvent event) {
         ThaiDelightForge.SOUND_EVENT.register(id,() -> event);
         return event;
    }

    public static <T extends BlockEntity> void registerBlockEntity(String id,BlockEntityType<T> blockEntityType) {
        ThaiDelightForge.BLOCK_ENTITY_TYPES.register(id,()->blockEntityType);
    }

    public static void registerFluid(String id, Fluid fluid) {
        ThaiDelightForge.FLUIDS.register(id,() -> fluid);
    }

    /*public static void registerMobEffect(String id, MobEffect mobEffect) {
        ThaiDelightForge.MOB_EFFECT.register(id,() -> mobEffect);
    }*/

    /*public static void registerPotion(String id, Potion potion) {
        ThaiDelightForge.POTION.register(id,() -> potion);
    }*/

    public static <T extends Recipe<?>> void registerRecipeType(String id,RecipeType<T> recipeType) {
        ThaiDelightForge.RECIPE_TYPE.register(id,() -> recipeType);
    }

    public static <T extends AbstractContainerMenu> MenuType<T> registryMenu(String id, ModPlatform.MenuSupplier<T> menu) {
        MenuType<T> menuType = new MenuType(menu::create, FeatureFlags.VANILLA_SET);
        ThaiDelightForge.MENU_TYPE.register(id,() -> menuType);
        return menuType;
    }

    public static <M extends AbstractContainerMenu,U extends Screen & MenuAccess<M>> void registerScreen(MenuType<M> menuType, ModPlatform.ScreenConstructor<M, U> screen) {
        MenuScreens.register(menuType,screen::create);
    }

    public static <T extends Recipe<?>> void registerRecipeSerializer(String id, RecipeSerializer<T> recipeSerializer) {
        ThaiDelightForge.RECIPE_SERIALIZER.register(id,() -> recipeSerializer);
    }

    public static <T extends TreeDecorator> TreeDecoratorType<T> registerTreeDecorator(String id, Codec<T> codec) {
        TreeDecoratorType<T> treeDecoratorType = new TreeDecoratorType(codec);
        ThaiDelightForge.TREE_DECORATOR.register(id,() -> treeDecoratorType);
        return treeDecoratorType;
    }

    public static CreativeModeTab createCreativeModeTab(ResourceLocation resourceLocation, Supplier<ItemStack> icon, ArrayList<ItemLike> itemList) {
        CreativeModeTab creativeModeTab = CreativeModeTab.builder()
                .title(Component.translatable("itemGroup."+resourceLocation.getNamespace()+"."+resourceLocation.getPath()))
                .icon(icon)
                .displayItems((itemDisplayParameters, output) -> ThaiDelightForge.ITEMS.getEntries().forEach(itemRegistryObject -> output.accept(itemRegistryObject.get()))).build();
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,resourceLocation,creativeModeTab);
    }

    //-----------------------------------------------------------------------


    public static <T extends Entity> void registerEntityRenderer(EntityType<T> entityTypeSupplier, EntityRendererProvider<T> entityRendererProvider) {
        EntityRenderers.register(entityTypeSupplier, entityRendererProvider);
    }

    public static <T extends Mob> void registerEntitySpawn(EntityType<T> entityType, SpawnPlacements.Type type, Heightmap.Types heightMapTypes, SpawnPlacements.SpawnPredicate<T> predicate) {
        SpawnPlacements.register(entityType,type,heightMapTypes,predicate);
    }

    public static <T extends Mob> Item registerSpawnEgg(EntityType<T> entityType, int primaryColor, int secondaryColor, Item.Properties properties) {
        return new ForgeSpawnEggItem(() -> entityType,primaryColor,secondaryColor,properties);
    }

    public static <T extends Mob> Item registerMobBucket(EntityType<T> entityType, Supplier<? extends Fluid> fluid, Supplier<? extends SoundEvent> soundEvent, Item.Properties properties) {
        return new MobBucketItem(()->entityType,fluid,soundEvent,properties);
    }

    public static void registerPotionBrewing(Supplier<Potion> input, Supplier<Item> ingredient, Supplier<Potion> output) {
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION),input.get())),Ingredient.of(ingredient.get()) ,PotionUtils.setPotion(new ItemStack(Items.POTION),output.get())));
    }

    public static <T extends BlockEntity> BlockEntityType.Builder<T> buildBlockEntity(ModBlocks.ModBlockEntityTypes.BlockEntitySupplier<T> blockEntityTypeSupplier, Block block) {
        return BlockEntityType.Builder.of(blockEntityTypeSupplier::create,block);
    }


    public static <T extends BlockEntity> void registerBlockEntityRenderer(BlockEntityType<T> blockEntityTypeSupplier, BlockEntityRendererProvider<T> blockEntityRendererProvider) {

    }

    public static Block getSomtamBlock() {
        return ModBlocksForge.SOMTAM_BLOCK;
    }

    public static Block getSpicyMincedPorkBlock() {
        return ModBlocksForge.SPICY_MINCED_PORK_SALAD_BLOCK;
    }

    public static Item getConsumableItem(FoodProperties foodProperties,boolean effectTooltips) {
        return new ConsumableItem(ModItems.bowlFoodItem(foodProperties),effectTooltips);
    }

    public static MobEffect getNourishment() {
        return ModEffects.NOURISHMENT.get();
    }

    public static MobEffect getComfort() {
        return ModEffects.COMFORT.get();
    }

    public static Item getSomtamItem() {
        return new SomtamItem(ModItems.bowlFoodItem(ModItemsForge.ModFoodForge.SOMTAM));
    }

    public static FoodProperties getSomtamFood() {
        return ModItemsForge.ModFoodForge.SOMTAM;
    }

    public static Item createPastleItem(Tier tier, int attackDamage, float attackSpeed, Item.Properties properties) {
        return new PastleItem(tier,attackDamage,attackSpeed,properties);
    }

    public static Class<? extends Item> getPastleClass() {
        return PastleItem.class;
    }

    public static Block getWildCropBlock(MobEffect mobEffect, int duration, BlockBehaviour.Properties properties) {
        return new WildCropBlock(mobEffect,duration,properties);
    }

    public static <T> int[] getRecipeMatcher(List<T> inputs, List<? extends Predicate<T>> tests) {
        return RecipeMatcher.findMatches(inputs,tests);
    }

    public static Block getCrabFriedRice() {
        return ModBlocksForge.CRAB_FRIED_RICE;
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

    public static void registerStrippables(Map<Block, Block> blockBlockMap) {
        Map<Block,Block> map = new ImmutableMap.Builder<Block,Block>().putAll(AxeItemAccessor.getStrippables()).putAll(blockBlockMap).build();
        AxeItemAccessor.setStrippables(map);
    }


}
