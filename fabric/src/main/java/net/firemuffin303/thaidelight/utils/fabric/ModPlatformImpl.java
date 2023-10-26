package net.firemuffin303.thaidelight.utils.fabric;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
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
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Fluid;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ModPlatformImpl {

    public static <T extends Block> void registryBlock(String id, Supplier<T> block) {
        Registry.register(BuiltInRegistries.BLOCK,new ResourceLocation(ThaiDelight.MOD_ID,id),block.get());
    }

    public static <T extends Item> void registryItem(String id, Supplier<T> item) {
        Registry.register(BuiltInRegistries.ITEM,new ResourceLocation(ThaiDelight.MOD_ID,id),item.get());
    }

    public static <T extends BlockEntity> void registerBlockEntity(String id,BlockEntityType<T> blockEntityType) {
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,id,blockEntityType);
    }

    public static <T extends Entity> void registerEntityType(String id, EntityType<T> entityType) {
        Registry.register(BuiltInRegistries.ENTITY_TYPE,id,entityType);
    }

    public static void registerFluid(String id, Fluid fluid) {
        Registry.register(BuiltInRegistries.FLUID,id,fluid);
    }

    public static void registerMobEffect(String id, MobEffect mobEffect) {
        Registry.register(BuiltInRegistries.MOB_EFFECT,id,mobEffect);
    }

    public static void registerPotion(String id, Potion potion) {
        Registry.register(BuiltInRegistries.POTION,id,potion);
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



}
