package net.firemuffin303.thaidelight.forge;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.block.saucebowl.SauceBowlInteraction;
import net.firemuffin303.thaidelight.common.entity.FlowerCrabEntity;
import net.firemuffin303.thaidelight.common.registry.*;
import net.firemuffin303.thaidelight.forge.common.registry.ModBlocksForge;
import net.firemuffin303.thaidelight.forge.common.registry.ModItemsForge;
import net.firemuffin303.thaidelight.forge.common.structures.VillageStructures;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CompoundIngredient;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.Arrays;
import java.util.Collections;

@Mod(ThaiDelight.MOD_ID)
public class ThaiDelightForge {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES,ThaiDelight.MOD_ID);
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS,ThaiDelight.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,ThaiDelight.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,ThaiDelight.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS,ThaiDelight.MOD_ID);
    public static final DeferredRegister<MobEffect> MOB_EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,ThaiDelight.MOD_ID);
    public static final DeferredRegister<Potion> POTION = DeferredRegister.create(ForgeRegistries.POTIONS,ThaiDelight.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPE = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES,ThaiDelight.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS,ThaiDelight.MOD_ID);

    public ThaiDelightForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        //ThaiDelight.init();
        modEventBus.register(this);
        ENTITY_TYPES.register(modEventBus);
        BLOCK.register(modEventBus);
        BLOCK_ENTITY_TYPES.register(modEventBus);
        FLUIDS.register(modEventBus);
        MOB_EFFECT.register(modEventBus);
        ITEMS.register(modEventBus);
        POTION.register(modEventBus);
        RECIPE_TYPE.register(modEventBus);
        RECIPE_SERIALIZER.register(modEventBus);


        modEventBus.addListener(EventPriority.HIGH,this::registerEvent);
        modEventBus.addListener(EventPriority.LOW,this::registerAttribute);
        modEventBus.addListener(EventPriority.LOW,this::registerEntitySpawn);
        modEventBus.addListener(EventPriority.LOW,this::registerCommonSetup);

        MinecraftForge.EVENT_BUS.addListener(VillageStructures::addNewVillageBuilding);
        MinecraftForge.EVENT_BUS.register(this);

    }

    public void registerAttribute(EntityAttributeCreationEvent event){
        ModEntityTypes.registerAttributes(((supplier, builderSupplier) -> event.put(supplier.get(),builderSupplier.get().build())));
    }

    public void registerEntitySpawn(SpawnPlacementRegisterEvent spawnPlacements){
        spawnPlacements.register(ModEntityTypes.FLOWER_CRAB,SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FlowerCrabEntity::checkSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
    }

    public void registerEvent(RegisterEvent registerEvent){
        registerEvent.register(ForgeRegistries.Keys.ENTITY_TYPES,helper -> ModEntityTypes.init());
        registerEvent.register(ForgeRegistries.Keys.BLOCKS,helper -> {
            ModBlocks.init();
            ModBlocksForge.init();
        });
        registerEvent.register(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES,helper -> ModBlocks.ModBlockEntityTypes.init());
        registerEvent.register(ForgeRegistries.Keys.FLUIDS,helper -> ModFluid.init());
        registerEvent.register(ForgeRegistries.Keys.MOB_EFFECTS,helper -> ModMobEffects.init());
        registerEvent.register(ForgeRegistries.Keys.ITEMS,helper -> {
            ModItems.init();
            ModItemsForge.init();
        });
        registerEvent.register(ForgeRegistries.Keys.POTIONS,helper -> ModPotions.init());
        registerEvent.register(ForgeRegistries.Keys.RECIPE_TYPES,helper -> ModRecipes.init());
        registerEvent.register(ForgeRegistries.Keys.RECIPE_SERIALIZERS,helper -> ModRecipes.ModRecipeSerializer.init());
    }

    public void registerCommonSetup(FMLCommonSetupEvent event){
        event.enqueueWork(SauceBowlInteraction::init);
        event.enqueueWork(ThaiDelight::registerComposterBlock);
    }


}
