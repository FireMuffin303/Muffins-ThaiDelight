package net.firemuffin303.thaidelight.utils.forge;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.forge.MobVote2023ModForge;
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
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ModPlatformImpl {

    public static <T extends Block> void registryBlock(String id, Supplier<T> block) {
        MobVote2023ModForge.BLOCK.register(id,block);
    }

    public static <T extends Item> void registryItem(String id, Supplier<T> item) {
        MobVote2023ModForge.ITEMS.register(id,item);
    }

    public static <T extends Entity> void registerEntityType(String id, EntityType<T> entityType) {
        MobVote2023ModForge.ENTITY_TYPES.register(id,()->entityType);
    }

    public static <T extends BlockEntity> void registerBlockEntity(String id,BlockEntityType<T> blockEntityType) {
        MobVote2023ModForge.BLOCK_ENTITY_TYPES.register(id,()->blockEntityType);
    }

    public static void registerFluid(String id, Fluid fluid) {
        MobVote2023ModForge.FLUIDS.register(id,() -> fluid);
    }

    public static void registerMobEffect(String id, MobEffect mobEffect) {
        MobVote2023ModForge.MOB_EFFECT.register(id,() -> mobEffect);
    }

    public static void registerPotion(String id, Potion potion) {
        MobVote2023ModForge.POTION.register(id,() -> potion);
    }


    public static CreativeModeTab createCreativeModeTab(ResourceLocation resourceLocation, Supplier<ItemStack> icon, ArrayList<Item> itemList) {
        CreativeModeTab creativeModeTab = CreativeModeTab.builder()
                .title(Component.translatable("itemGroup."+resourceLocation.getNamespace()+"."+resourceLocation.getPath()))
                .icon(icon)
                .displayItems((itemDisplayParameters, output) -> itemList.forEach(output::accept)).build();
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,resourceLocation,creativeModeTab);
    }

    //-----------------------------------------------------------------------


    public static void holdingCrabClaw(Player player) {
        if(!player.getAttribute(ForgeMod.BLOCK_REACH.get()).hasModifier(ThaiDelight.CRAB_REACH)){
            player.getAttribute(ForgeMod.BLOCK_REACH.get()).addTransientModifier(ThaiDelight.CRAB_REACH);

        }
    }


    public static void stopHoldingCrabClaw(Player player) {
        if(player.getAttribute(ForgeMod.BLOCK_REACH.get()).hasModifier(ThaiDelight.CRAB_REACH)){
            player.getAttribute(ForgeMod.BLOCK_REACH.get()).removeModifier(ThaiDelight.CRAB_REACH);

        }
    }

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

    public static Attribute getReachAttribute() {
        return ForgeMod.BLOCK_REACH.get();
    }

    public static <T extends BlockEntity> BlockEntityType.Builder<T> buildBlockEntity(ModBlocks.ModBlockEntityTypes.BlockEntitySupplier<T> blockEntityTypeSupplier, Block block) {
        return BlockEntityType.Builder.of(blockEntityTypeSupplier::create,block);
    }


    public static <T extends BlockEntity> void registerBlockEntityRenderer(BlockEntityType<T> blockEntityTypeSupplier, BlockEntityRendererProvider<T> blockEntityRendererProvider) {

    }




}