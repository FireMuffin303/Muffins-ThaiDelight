package net.firemuffin303.thaidelight.forge;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.entity.Dragonfly;
import net.firemuffin303.thaidelight.common.entity.FlowerCrabEntity;
import net.firemuffin303.thaidelight.common.event.ModVillagerTrades;
import net.firemuffin303.thaidelight.common.item.bottle.DragonflyBottleItem;
import net.firemuffin303.thaidelight.common.registry.*;
import net.firemuffin303.thaidelight.forge.common.Configuration;
import net.firemuffin303.thaidelight.forge.common.registry.ModBlocksForge;
import net.firemuffin303.thaidelight.forge.common.registry.ModItemsForge;
import net.firemuffin303.thaidelight.forge.common.structures.VillageStructures;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.List;

@Mod(ThaiDelight.MOD_ID)
public class ThaiDelightForge {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES,ThaiDelight.MOD_ID);
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS,ThaiDelight.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,ThaiDelight.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,ThaiDelight.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS,ThaiDelight.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPE = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES,ThaiDelight.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS,ThaiDelight.MOD_ID);
    public static final DeferredRegister<MenuType<?>> MENU_TYPE = DeferredRegister.create(ForgeRegistries.MENU_TYPES,ThaiDelight.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENT = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,ThaiDelight.MOD_ID);
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES,ThaiDelight.MOD_ID);


    public ThaiDelightForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        //ThaiDelight.init();
        modEventBus.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Configuration.COMMON_CONFIG);
        ENTITY_TYPES.register(modEventBus);
        BLOCK.register(modEventBus);
        BLOCK_ENTITY_TYPES.register(modEventBus);
        FLUIDS.register(modEventBus);
        ITEMS.register(modEventBus);
        RECIPE_TYPE.register(modEventBus);
        RECIPE_SERIALIZER.register(modEventBus);
        MENU_TYPE.register(modEventBus);
        SOUND_EVENT.register(modEventBus);
        TREE_DECORATOR.register(modEventBus);


        modEventBus.addListener(EventPriority.HIGH,this::registerEvent);
        modEventBus.addListener(EventPriority.LOW,this::registerAttribute);
        modEventBus.addListener(EventPriority.LOW,this::registerEntitySpawn);
        modEventBus.addListener(EventPriority.LOW,this::registerCommonSetup);

        MinecraftForge.EVENT_BUS.addListener(VillageStructures::addNewVillageBuilding);
        MinecraftForge.EVENT_BUS.addListener(this::registerVillagerTrades);
        MinecraftForge.EVENT_BUS.addListener(this::registerWandererTrades);
        MinecraftForge.EVENT_BUS.register(this);

    }

    public void registerAttribute(EntityAttributeCreationEvent event){
        ModEntityTypes.registerAttributes(((supplier, builderSupplier) -> event.put(supplier.get(),builderSupplier.get().build())));
    }

    public void registerEntitySpawn(SpawnPlacementRegisterEvent spawnPlacements){
        spawnPlacements.register(ModEntityTypes.FLOWER_CRAB,SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FlowerCrabEntity::checkSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
        spawnPlacements.register(ModEntityTypes.DRAGONFLY,SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
    }

    public void registerEvent(RegisterEvent registerEvent){
        registerEvent.register(ForgeRegistries.Keys.ENTITY_TYPES,helper -> ModEntityTypes.init());
        registerEvent.register(ForgeRegistries.Keys.BLOCKS,helper -> {
            ModBlocks.init();
            ModBlocksForge.init();
        });
        registerEvent.register(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES,helper -> ModBlocks.ModBlockEntityTypes.init());
        registerEvent.register(ForgeRegistries.Keys.FLUIDS,helper -> ModFluid.init());
        registerEvent.register(ForgeRegistries.Keys.ITEMS,helper -> {
            ModItems.init();
            ModItemsForge.init();
        });
        registerEvent.register(ForgeRegistries.Keys.RECIPE_TYPES,helper -> ModRecipes.init());
        registerEvent.register(ForgeRegistries.Keys.RECIPE_SERIALIZERS,helper -> ModRecipes.ModRecipeSerializer.init());
        registerEvent.register(ForgeRegistries.Keys.MENU_TYPES,helper -> ModMenuType.init());
        registerEvent.register(ForgeRegistries.Keys.SOUND_EVENTS,helper -> ModSoundEvents.init());
        registerEvent.register(ForgeRegistries.Keys.TREE_DECORATOR_TYPES,helper -> ModTreeDecorator.init());

        registerEvent.register(Registries.CREATIVE_MODE_TAB, helper ->
                Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                        new ResourceLocation(ThaiDelight.MOD_ID,"main"),
                        CreativeModeTab.builder().title(Component.translatable("itemGroup."+ThaiDelight.MOD_ID+".main"))
                                .icon(() -> new ItemStack(ModBlocks.MORTAR))
                                .displayItems(this::displayItem).build()));
    }

    public void registerCommonSetup(FMLCommonSetupEvent event){
        event.enqueueWork(ThaiDelight::registerComposterBlock);
        event.enqueueWork(ThaiDelight::registerStrippables);
        event.enqueueWork(ThaiDelight::registerAnimalFoodItem);
    }

    public void registerVillagerTrades(VillagerTradesEvent event){
        ModVillagerTrades.trades().forEach(modVillagerTrade -> {
            if(event.getType() == modVillagerTrade.villagerProfession()){
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                trades.get(modVillagerTrade.level()).add(((arg, arg2) -> modVillagerTrade.merchantOffer()));
            }
        });
    }

    public void registerWandererTrades(WandererTradesEvent event){
        ModVillagerTrades.wanderTrade().forEach(integerMerchantOfferPair -> {
            event.getGenericTrades().add((arg, arg2) -> integerMerchantOfferPair);
        });
    }

    public void displayItem(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output){
        output.accept(ModBlocks.MORTAR);

        output.accept(ModBlocks.LIME_CRATE);
        output.accept(ModBlocks.PEPPER_CRATE);
        output.accept(ModBlocks.RAW_PAPAYA_CRATE);
        output.accept(ModBlocks.PAPAYA_CRATE);
        output.accept(ModBlocks.PAPAYA_LOG);
        output.accept(ModBlocks.PAPAYA_WOOD);
        output.accept(ModBlocks.STRIPPED_PAPAYA_LOG);
        output.accept(ModBlocks.STRIPPED_PAPAYA_WOOD);
        output.accept(ModBlocks.PAPAYA_LEAVES);

        output.accept(ModBlocks.SOMTAM_FEAST);
        output.accept(ModBlocks.SPICY_MINCED_MEAT_SALAD_FEAST);
        output.accept(ModBlocks.CRAB_FRIED_RICE_FEAST);

        output.accept(ModBlocks.WILD_PEPPER_CROP);
        output.accept(ModItems.PEPPER);
        output.accept(ModItems.PEPPER_SEED);

        output.accept(ModItems.LIME);
        output.accept(ModItems.SLICED_LIME);
        output.accept(ModBlocks.LIME_SAPLING);

        output.accept(ModItems.PAPAYA);
        output.accept(ModItems.SLICED_PAPAYA);
        output.accept(ModItems.RAW_PAPAYA);
        output.accept(ModItems.RAW_PAPAYA_SLICE);
        output.accept(ModBlocks.PAPAYA_SAPLING);
        output.accept(ModItems.PAPAYA_SEEDS);

        output.accept(ModItemsForge.SOMTAM.get());
        output.accept(ModItemsForge.SPICY_MINCED_MEAT_SALAD.get());
        output.accept(ModItemsForge.CRAB_FRIED_RICE.get());
        output.accept(ModItemsForge.STIR_FRIED_NOODLE.get());

        output.accept(ModItems.LIME_JUICE);
        output.accept(ModItems.PAPAYA_JUICE);

        output.accept(ModItems.CRAB_SPAWN_EGG);
        output.accept(ModBlocks.CRAB_EGG);
        output.accept(ModItems.CRAB_BUCKET);
        output.accept(ModItems.CRAB_MEAT);
        output.accept(ModItems.COOKED_CRAB_MEAT);

        output.accept(ModItems.DRAGONFLY_SPAWN_EGG);

        Dragonfly.DragonflyVariant[] dragonflyVariants = Dragonfly.DragonflyVariant.values();

        for(Dragonfly.DragonflyVariant variant : dragonflyVariants){
            ItemStack itemStack = new ItemStack(ModItems.DRAGONFLY_BOTTLE);
            DragonflyBottleItem.setVariant(itemStack,variant);
            output.accept(itemStack);


        }

        output.accept(ModItems.DRAGONFLY);
        output.accept(ModItems.COOKED_DRAGONFLY);
    }
}
