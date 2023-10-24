package net.firemuffin303.thaidelight.utils;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Fluid;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ModPlatform {
    @ExpectPlatform
    public static <T extends Block> Supplier<T> registryBlock(String id, Supplier<T> block){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Item> Supplier<T> registryItem(String id,Supplier<T> item){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static CreativeModeTab createCreativeModeTab(ResourceLocation resourceLocation, Supplier<ItemStack> icon, ModRegistryEntry<Item> itemList){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T> ModRegistryEntry<T> create(Registry<T> registry, String id){
        throw new NotImplementedException();
    }

    @ExpectPlatform
    public static void holdingCrabClaw(Player player){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void stopHoldingCrabClaw(Player player){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Entity> void registerEntityRenderer(Supplier<EntityType<T>> entityTypeSupplier, EntityRendererProvider<T> entityRendererProvider){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Mob> Supplier<Item> registerSpawnEgg(Supplier<EntityType<T>> entityType, int primaryColor, int secondaryColor, Item.Properties properties){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Mob> Supplier<Item> registerMobBucket(Supplier<EntityType<T>> entityType, Supplier<? extends Fluid> fluid, Supplier<? extends SoundEvent> soundEvent, Item.Properties properties){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Mob> void registerEntitySpawn(EntityType<T> entityType, SpawnPlacements.Type type, Heightmap.Types heightMapTypes, SpawnPlacements.SpawnPredicate<T> predicate) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerPotionBrewing(Supplier<Potion> input,Supplier<Item> ingredient,Supplier<Potion> output) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends BlockEntity> BlockEntityType.Builder<T> registerBlockEntity(ModBlocks.ModBlockEntityTypes.BlockEntitySupplier<T> blockEntityTypeSupplier, Block block){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Attribute getReachAttribute() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends BlockEntity> void registerBlockEntityRenderer(BlockEntityType<T> blockEntityTypeSupplier, BlockEntityRendererProvider<T> blockEntityRendererProvider) {
        throw new AssertionError();
    }
}
