package net.firemuffin303.thaidelight.utils.fabric;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import com.nhoryzon.mc.farmersdelight.block.FeastBlock;
import com.nhoryzon.mc.farmersdelight.block.WildCropBlock;
import com.nhoryzon.mc.farmersdelight.item.ConsumableItem;
import com.nhoryzon.mc.farmersdelight.item.DrinkableItem;
import com.nhoryzon.mc.farmersdelight.registry.EffectsRegistry;
import com.nhoryzon.mc.farmersdelight.util.RecipeMatcher;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Fluid;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ModPlatformImpl {

    public static <T extends Block> void registryBlock(String id, Supplier<T> block) {
        Registry.register(BuiltInRegistries.BLOCK,new ResourceLocation(ThaiDelight.MOD_ID,id),block.get());
    }

    public static <T extends Item> void registryItem(String id, Supplier<T> item) {
        Registry.register(BuiltInRegistries.ITEM,new ResourceLocation(ThaiDelight.MOD_ID,id),item.get());
    }

    public static <T extends BlockEntity> void registerBlockEntity(String id,BlockEntityType<T> blockEntityType) {
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,new ResourceLocation(ThaiDelight.MOD_ID,id),blockEntityType);
    }

    public static  <T extends Recipe<?>> void registerRecipeType(String id,RecipeType<T> recipeType) {
        Registry.register(BuiltInRegistries.RECIPE_TYPE, new ResourceLocation(ThaiDelight.MOD_ID, id), recipeType);
    }

    public static <T extends Entity> void registerEntityType(String id, EntityType<T> entityType) {
        Registry.register(BuiltInRegistries.ENTITY_TYPE,new ResourceLocation(ThaiDelight.MOD_ID,id) ,entityType);
    }

    public static void registerFluid(String id, Fluid fluid) {
        Registry.register(BuiltInRegistries.FLUID,new ResourceLocation(ThaiDelight.MOD_ID,id),fluid);
    }

    public static void registerMobEffect(String id, MobEffect mobEffect) {
        Registry.register(BuiltInRegistries.MOB_EFFECT,new ResourceLocation(ThaiDelight.MOD_ID,id),mobEffect);
    }

    public static void registerPotion(String id, Potion potion) {
        Registry.register(BuiltInRegistries.POTION,new ResourceLocation(ThaiDelight.MOD_ID,id),potion);
    }

    public static <T extends Recipe<?>> void registerRecipeSerializer(String id, RecipeSerializer<T> recipeSerializer) {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,new ResourceLocation(ThaiDelight.MOD_ID,id),recipeSerializer);
    }

    public static <M extends AbstractContainerMenu,U extends Screen & MenuAccess<M>> void registerScreen(MenuType<M> menuType, ModPlatform.ScreenConstructor<M, U> screen) {
        MenuScreens.register(menuType,screen::create);
    }

    public static CreativeModeTab createCreativeModeTab(ResourceLocation resourceLocation, Supplier<ItemStack> icon, ArrayList<Item> itemList) {
        CreativeModeTab creativeModeTab = FabricItemGroup.builder()
                .title(Component.translatable("itemGroup."+resourceLocation.getNamespace()+"."+resourceLocation.getPath()))
                .icon(icon)
                .displayItems((itemDisplayParameters, output) -> itemList.forEach(output::accept)).build();
        CreativeModeTab creativeModeTab1 = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,resourceLocation,creativeModeTab);
        return creativeModeTab1;
    }

    //---------------------------------------------------------------------

    public static void holdingCrabClaw(Player player) {
        if(!player.getAttribute(ReachEntityAttributes.REACH).hasModifier(ThaiDelight.CRAB_REACH)){
            player.getAttribute(ReachEntityAttributes.REACH).addTransientModifier(ThaiDelight.CRAB_REACH);
        }
    }

    public static void stopHoldingCrabClaw(Player player) {
        if(player.getAttribute(ReachEntityAttributes.REACH).hasModifier(ThaiDelight.CRAB_REACH)){
            player.getAttribute(ReachEntityAttributes.REACH).removeModifier(ThaiDelight.CRAB_REACH);
        }
    }

    public static <T extends Entity> void registerEntityRenderer(EntityType<T> entityTypeSupplier, EntityRendererProvider<T> entityRendererProvider) {
        EntityRendererRegistry.register(entityTypeSupplier,entityRendererProvider);

    }

    public static <T extends Mob> void registerEntitySpawn(EntityType<T> entityType, SpawnPlacements.Type type, Heightmap.Types heightMapTypes, SpawnPlacements.SpawnPredicate<T> predicate) {
        //SpawnPlacementMixin.invokeRegister(entityType,type,heightMapTypes,predicate);
    }

    public static  <T extends Mob> Item registerSpawnEgg(EntityType<T> entityType, int primaryColor, int secondaryColor, Item.Properties properties) {
        return new SpawnEggItem(entityType,primaryColor,secondaryColor,properties);
    }

    public static <T extends Mob> Item registerMobBucket(EntityType<T> entityType, Supplier<? extends Fluid> fluid, Supplier<? extends SoundEvent> soundEvent, Item.Properties properties) {
        return new MobBucketItem(entityType,fluid.get(),soundEvent.get(),properties);
    }

    public static void registerPotionBrewing(Supplier<Potion> input, Supplier<Item> ingredient, Supplier<Potion> output) {
        FabricBrewingRecipeRegistry.registerPotionRecipe(input.get(), Ingredient.of(ingredient.get()),output.get());
    }

    public static Attribute getReachAttribute() {
        return ReachEntityAttributes.REACH;
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

    public static MobEffect getNourishment() {
        return EffectsRegistry.NOURISHMENT.get();
    }

    public static MobEffect getComfort() {
        return EffectsRegistry.COMFORT.get();
    }

    public static Item getSomtamItem() {
        return new SomtamItem(ModItemsFabric.foodBowl(ModItemsFabric.ModFoodFabric.SOMTAM));
    }

    public static FoodProperties getSomtamFood() {
        return ModItemsFabric.ModFoodFabric.SOMTAM;
    }

    public static Item createPastleItem(Tier tier, int attackDamage, float attackSpeed, Item.Properties properties) {
        return new PastleItem(tier,attackDamage,attackSpeed,properties);
    }

    public static Class<? extends Item> getPastleClass() {
        return PastleItem.class;
    }

    public static Block getWildCropBlock(MobEffect mobEffect, int duration, BlockBehaviour.Properties properties) {
        return new WildCropBlock();
    }

    public static <T> int[] getRecipeMatcher(List<T> inputs, List<? extends Predicate<T>> tests) {
        return RecipeMatcher.findMatches(inputs,tests);
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
        return Registry.register(BuiltInRegistries.MENU,new ResourceLocation(ThaiDelight.MOD_ID,id),new MenuType(menu::create, FeatureFlags.VANILLA_SET));
    }




}
