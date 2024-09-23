package net.firemuffin303.thaidelight.forge;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.entity.Dragonfly;
import net.firemuffin303.thaidelight.common.entity.FlowerCrabEntity;
import net.firemuffin303.thaidelight.common.event.ModVillagerTrades;
import net.firemuffin303.thaidelight.common.item.bottle.DragonflyBottleItem;
import net.firemuffin303.thaidelight.common.registry.*;
import net.firemuffin303.thaidelight.forge.common.registry.ModBlocksForge;
import net.firemuffin303.thaidelight.forge.common.registry.ModItemsForge;
import net.firemuffin303.thaidelight.forge.common.structures.VillageStructures;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.List;

@Mod(ThaiDelight.MOD_ID)
public class ThaiDelightForge {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE,ThaiDelight.MOD_ID);
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(BuiltInRegistries.BLOCK,ThaiDelight.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM,ThaiDelight.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE,ThaiDelight.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID,ThaiDelight.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPE = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE,ThaiDelight.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER,ThaiDelight.MOD_ID);
    public static final DeferredRegister<MenuType<?>> MENU_TYPE = DeferredRegister.create(BuiltInRegistries.MENU,ThaiDelight.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENT = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT,ThaiDelight.MOD_ID);
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR = DeferredRegister.create(BuiltInRegistries.TREE_DECORATOR_TYPE,ThaiDelight.MOD_ID);


    public ThaiDelightForge(IEventBus iEventBus) {
        //ThaiDelight.init();
        iEventBus.register(this);

        ENTITY_TYPES.register(iEventBus);
        BLOCK.register(iEventBus);
        BLOCK_ENTITY_TYPES.register(iEventBus);
        FLUIDS.register(iEventBus);
        ITEMS.register(iEventBus);
        RECIPE_TYPE.register(iEventBus);
        RECIPE_SERIALIZER.register(iEventBus);
        MENU_TYPE.register(iEventBus);
        SOUND_EVENT.register(iEventBus);
        TREE_DECORATOR.register(iEventBus);


        iEventBus.addListener(EventPriority.HIGH,this::registerEvent);
        iEventBus.addListener(EventPriority.LOW,this::registerAttribute);
        iEventBus.addListener(EventPriority.LOW,this::registerCommonSetup);

        NeoForge.EVENT_BUS.addListener(VillageStructures::addNewVillageBuilding);
        NeoForge.EVENT_BUS.addListener(this::registerVillagerTrades);
        NeoForge.EVENT_BUS.addListener(this::registerWandererTrades);
        NeoForge.EVENT_BUS.register(this);

    }

    public void registerAttribute(EntityAttributeCreationEvent event){
        ModEntityTypes.registerAttributes(((supplier, builderSupplier) -> event.put(supplier.get(),builderSupplier.get().build())));
    }

    public void registerEvent(RegisterEvent registerEvent){
        registerEvent.register(Registries.CREATIVE_MODE_TAB, helper ->
                Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                        ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,"main"),
                        CreativeModeTab.builder().title(Component.translatable("itemGroup."+ThaiDelight.MOD_ID+".main"))
                                .icon(() -> new ItemStack(ModBlocks.MORTAR.get()))
                                .displayItems(this::displayItem).build()));
    }

    public void registerCommonSetup(FMLCommonSetupEvent event){
        event.enqueueWork(ThaiDelight::registerComposterBlock);
        event.enqueueWork(ThaiDelight::registerStrippables);
        event.enqueueWork(ThaiDelight::registerAnimalFoodItem);
        event.enqueueWork(ModCauldronInteraction::init);
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
        output.accept(ModBlocks.MORTAR.get());

        output.accept(ModBlocks.LIME_CRATE.get());
        output.accept(ModBlocks.PEPPER_CRATE.get());
        output.accept(ModBlocks.RAW_PAPAYA_CRATE.get());
        output.accept(ModBlocks.PAPAYA_CRATE.get());
        output.accept(ModBlocks.PAPAYA_LOG.get());
        output.accept(ModBlocks.PAPAYA_WOOD.get());
        output.accept(ModBlocks.STRIPPED_PAPAYA_LOG.get());
        output.accept(ModBlocks.STRIPPED_PAPAYA_WOOD.get());
        output.accept(ModBlocks.PAPAYA_LEAVES.get());

        output.accept(ModBlocks.SOMTAM_FEAST.get());
        output.accept(ModBlocks.LARB_FEAST.get());
        output.accept(ModBlocks.CRAB_FRIED_RICE_FEAST.get());

        output.accept(ModBlocks.WILD_PEPPER_CROP.get());
        output.accept(ModItems.PEPPER.get());
        output.accept(ModItems.PEPPER_SEED.get());

        output.accept(ModItems.LIME.get());
        output.accept(ModItems.SLICED_LIME.get());
        output.accept(ModBlocks.LIME_SAPLING.get());

        output.accept(ModItems.PAPAYA.get());
        output.accept(ModItems.SLICED_PAPAYA.get());
        output.accept(ModItems.RAW_PAPAYA.get());
        output.accept(ModItems.RAW_PAPAYA_SLICE.get());
        output.accept(ModBlocks.PAPAYA_SAPLING.get());
        output.accept(ModItems.PAPAYA_SEEDS.get());

        output.accept(ModItemsForge.SOMTAM.get());
        output.accept(ModItemsForge.SPICY_MINCED_MEAT_SALAD.get());
        output.accept(ModItemsForge.CRAB_FRIED_RICE.get());
        output.accept(ModItemsForge.STIR_FRIED_NOODLE.get());

        output.accept(ModItems.LIME_JUICE.get());
        output.accept(ModItems.PAPAYA_JUICE.get());
        output.accept(ModItems.FISH_SAUCE_BOTTLE.get());

        output.accept(ModItems.CRAB_SPAWN_EGG.get());
        output.accept(ModBlocks.CRAB_EGG.get());
        output.accept(ModItems.CRAB_BUCKET.get());
        output.accept(ModItems.CRAB_MEAT.get());
        output.accept(ModItems.COOKED_CRAB_MEAT.get());

        output.accept(ModItems.DRAGONFLY_SPAWN_EGG.get());

        Dragonfly.DragonflyVariant[] dragonflyVariants = Dragonfly.DragonflyVariant.values();

        for(Dragonfly.DragonflyVariant variant : dragonflyVariants){
            ItemStack itemStack = new ItemStack(ModItems.DRAGONFLY_BOTTLE.get());
            DragonflyBottleItem.setVariant(itemStack,variant);
            output.accept(itemStack);


        }

        output.accept(ModItems.DRAGONFLY.get());
        output.accept(ModItems.COOKED_DRAGONFLY.get());
    }
}
