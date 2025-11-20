package net.firemuffin303.muffinsthaidelightfabric;

import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.DragonflyEntity;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.FlowerCrabEntity;
import net.firemuffin303.muffinsthaidelightfabric.common.event.ModVillagerTrades;
import net.firemuffin303.muffinsthaidelightfabric.common.item.DragonflyBottleItem;
import net.firemuffin303.muffinsthaidelightfabric.config.ThaiDelightConfig;
import net.firemuffin303.muffinsthaidelightfabric.mixin.*;
import net.firemuffin303.muffinsthaidelightfabric.util.BlockEntityTypeAdder;
import net.firemuffin303.muffinsthaidelightfabric.mixin.food.ChickenFoodAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.food.FrogFoodAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.food.ParrotTameFoodAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.food.PigFoodAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.loot.LootPoolBuilderAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.loot.LootTableAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.villager.VillagerAccessor;
import net.firemuffin303.muffinsthaidelightfabric.registry.*;
import net.firemuffin303.muffinsthaidelightfabric.util.CommonEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import org.slf4j.Logger;

import java.util.*;
import java.util.stream.Stream;

public class ThaiDelight implements ModInitializer {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "muffins_thaidelight";
    public static final String TASTY_NBT = "Tasty";

    public static final ResourceLocation SPICY_PAYLOAD_ID = new ResourceLocation(MOD_ID,"spicypayload");

    public static final TerraformBoatType DURIAN = new TerraformBoatType.Builder()
            .item(ModItems.DURIAN_BOAT)
            .chestItem(ModItems.DURIAN_CHEST_BOAT)
            .planks(ModItems.DURIAN_PLANKS)
            .build();

    private static final TerraformBoatType COCONUT = new TerraformBoatType.Builder()
            .item(ModItems.COCONUT_BOAT)
            .chestItem(ModItems.COCONUT_CHEST_BOAT)
            .planks(ModItems.COCONUT_PLANKS)
            .build();

    private static final TerraformBoatType MANGO = new TerraformBoatType.Builder()
            .item(ModItems.MANGO_BOAT)
            .chestItem(ModItems.MANGO_CHEST_BOAT)
            .planks(ModItems.MANGO_PLANKS)
            .build();

    public static final CreativeModeTab MOD_TAB = FabricItemGroup.builder()
            .title(Component.translatable("itemGroup."+ThaiDelight.MOD_ID+".main"))
            .icon(() -> new ItemStack(ModBlocks.MORTAR))
            .displayItems(ThaiDelight::itemsGenerator)
            .build();
    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,new ResourceLocation(MOD_ID,"main"),MOD_TAB);

        init();
        postInit();

        MidnightConfig.init(MOD_ID, ThaiDelightConfig.class);

        //ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new FlavorManager());


        ServerEntityEvents.ENTITY_LOAD.register((entity, serverLevel) -> {
            if(entity instanceof PathfinderMob mob){
                GoalSelector goalSelector = ((MobAccessor)mob).getGoalSelector();
                if(!goalSelector.getAvailableGoals().isEmpty()){
                    goalSelector.addGoal(3,new AvoidEntityGoal<>(mob, LivingEntity.class,6.0f,1.0,1.2,livingEntity -> livingEntity.hasEffect(ModMobEffects.STINKY)));
                }
            }
        });

        Set<ResourceLocation> chestsId = Set.of(
                BuiltInLootTables.VILLAGE_PLAINS_HOUSE,
                BuiltInLootTables.VILLAGE_SAVANNA_HOUSE,
                BuiltInLootTables.VILLAGE_SNOWY_HOUSE,
                BuiltInLootTables.VILLAGE_TAIGA_HOUSE,
                BuiltInLootTables.VILLAGE_DESERT_HOUSE,
                BuiltInLootTables.ABANDONED_MINESHAFT,
                BuiltInLootTables.PILLAGER_OUTPOST);

        TillableBlockRegistry.register(Blocks.BAMBOO_SAPLING,useOnContext -> true,Blocks.AIR.defaultBlockState(),ModItems.BAMBOO_SHOOT);



        LootTableEvents.MODIFY.register(new LootTableEvents.Modify() {
            @Override
            public void modifyLootTable(ResourceManager resourceManager, LootDataManager lootDataManager,
                                        ResourceLocation resourceLocation, LootTable.Builder builder, LootTableSource lootTableSource) {

                if (chestsId.contains(resourceLocation)) {
                    ResourceLocation injectId = new ResourceLocation(ThaiDelight.MOD_ID, "inject/" + resourceLocation.getPath());
                    LootTable injectingLootTable = lootDataManager.getLootTable(injectId);
                    LootTableAccessor accessor = (LootTableAccessor) injectingLootTable;

                    LootPool injectingPool = List.of(accessor.getPools()).get(0);

                    builder.modifyPools(builder1 -> {
                        for(LootPoolEntryContainer lootPoolEntryContainer : injectingPool.entries){
                            ((LootPoolBuilderAccessor) builder1).getEntries().add(lootPoolEntryContainer);
                        }

                    });


                }
            }
        });
    }

    private void init(){
        ModEntityTypes.init();
        ModBlockEntityTypes.init();
        ModBlocks.init();
        ModItems.init();
        ModSoundEvents.init();
        ModRecipes.init();
        ModRecipes.ModSerializer.init();
        ModFeatures.init();
        ModMenuType.init();
        ModTreeDecoratorTypes.init();
        ModMobEffects.init();

        Registry.register(TerraformBoatTypeRegistry.INSTANCE,ModItems.DURIAN_BOAT_KEY,DURIAN);
        Registry.register(TerraformBoatTypeRegistry.INSTANCE,ModItems.COCONUT_BOAT_KEY,COCONUT);
        Registry.register(TerraformBoatTypeRegistry.INSTANCE,ModItems.MANGO_BOAT_KEY,MANGO);

    }

    private void postInit(){
        ModCauldronInteraction.init();
        CommonEvents.registerComposter();
        CommonEvents.registerAnimalFood();
        CommonEvents.registerStrippable();
        CommonEvents.addVillagersTrades();
        CommonEvents.registerFuel();
        CommonEvents.worldGeneration();
        CommonEvents.entityInit();
        CommonEvents.setVillagerItem();

        PotionBrewing.addMix(Potions.AWKWARD,ModItems.FERMENTED_FISH,ModMobEffects.STINKY_POTION);
        PotionBrewing.addMix(ModMobEffects.STINKY_POTION, Items.REDSTONE,ModMobEffects.LONG_STINKY_POTION);
        PotionBrewing.addMix(ModMobEffects.STINKY_POTION, Items.GLOWSTONE_DUST,ModMobEffects.STRONG_STINKY_POTION);
    }

    private static void itemsGenerator(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output){
        output.accept(ModItems.MORTAR);
        output.accept(ModItems.SACK);
        output.accept(ModItems.LIME_CRATE);
        output.accept(ModItems.PEPPER_CRATE);
        output.accept(ModItems.RAW_PAPAYA_CRATE);
        output.accept(ModItems.PAPAYA_CRATE);
        output.accept(ModItems.MANGO_CRATE);
        output.accept(ModItems.COCONUT_CRATE);
        output.accept(ModItems.HOLY_BASIL_CRATE);
        output.accept(ModItems.BASIL_CRATE);
        output.accept(ModItems.BAMBOO_SHOOT_CRATE);
        output.accept(ModItems.BUTTERFLY_PEA_CRATE);

        output.accept(ModItems.CRAB_SPAWN_EGG);
        output.accept(ModItems.CRAB_EGG);
        output.accept(ModItems.CRAB_BUCKET);
        output.accept(ModItems.CRAB_MEAT);
        output.accept(ModItems.COOKED_CRAB_MEAT);

        output.accept(ModItems.DRAGONFLY_SPAWN_EGG);
        Arrays.stream(DragonflyEntity.DragonflyVariant.values()).forEach(dragonflyVariant -> {
            ItemStack itemStack = new ItemStack(ModItems.DRAGONFLY_BOTTLE);
            DragonflyBottleItem.setVariant(itemStack,dragonflyVariant);
            output.accept(itemStack);
        });
        output.accept(ModItems.DRAGONFLY);
        output.accept(ModItems.COOKED_DRAGONFLY);

        output.accept(ModItems.FISH_SAUCE_BOTTLE);
        output.accept(ModItems.FERMENTED_FISH);
        output.accept(ModItems.PAPAYA_JUICE);
        output.accept(ModItems.LIME_JUICE);
        output.accept(ModItems.COCONUT_WATER);
        output.accept(ModItems.BUTTERFLY_PEA_TEA);

        output.accept(ModItems.LIME_SAPLING);
        output.accept(ModItems.LIME);
        output.accept(ModItems.SLICED_LIME);

        output.accept(ModItems.WILD_PEPPER_CROP);
        output.accept(ModItems.PEPPER);
        output.accept(ModItems.PEPPER_SEED);

        output.accept(ModItems.DURIAN_SAPLING);
        output.accept(ModItems.DURIAN_LEAVES);
        output.accept(ModItems.DURIAN_FLOWER);
        output.accept(ModItems.SMALL_DURIAN);
        output.accept(ModItems.DURIAN);
        output.accept(ModItems.DURIAN_PULP);
        output.accept(ModItems.DURIAN_PEEL);
        output.accept(ModItems.DURIAN_PEEL_BLOCK);
        output.accept(ModItems.DURIAN_HELMET);
        output.accept(ModItems.DURIAN_LOG);
        output.accept(ModItems.DURIAN_WOOD);
        output.accept(ModItems.STRIPPED_DURIAN_LOG);
        output.accept(ModItems.STRIPPED_DURIAN_WOOD);
        output.accept(ModItems.DURIAN_PLANKS);
        output.accept(ModItems.DURIAN_STAIRS);
        output.accept(ModItems.DURIAN_SLAB);
        output.accept(ModItems.DURIAN_FENCE);
        output.accept(ModItems.DURIAN_FENCE_GATE);
        output.accept(ModItems.DURIAN_DOOR);
        output.accept(ModItems.DURIAN_TRAPDOOR);
        output.accept(ModItems.DURIAN_PRESSURE_PLATE);
        output.accept(ModItems.DURIAN_BUTTON);
        output.accept(ModItems.DURIAN_SIGN);
        output.accept(ModItems.DURIAN_HANGING_SIGN);
        output.accept(ModItems.DURIAN_CABINET);
        output.accept(ModItems.DURIAN_BOAT);
        output.accept(ModItems.DURIAN_CHEST_BOAT);

        output.accept(ModItems.COCONUT_SAPLING);
        output.accept(ModItems.COCONUT_LEAF);
        output.accept(ModItems.COCONUT_LEAF_BLOCK);

        output.accept(ModItems.COCONUT_LOG);
        output.accept(ModItems.COCONUT_WOOD);
        output.accept(ModItems.STRIPPED_COCONUT_LOG);
        output.accept(ModItems.STRIPPED_COCONUT_WOOD);
        output.accept(ModItems.COCONUT_PLANKS);
        output.accept(ModItems.COCONUT_STAIRS);
        output.accept(ModItems.COCONUT_SLAB);
        output.accept(ModItems.COCONUT_FENCE);
        output.accept(ModItems.COCONUT_FENCE_GATE);
        output.accept(ModItems.COCONUT_DOOR);
        output.accept(ModItems.COCONUT_TRAPDOOR);
        output.accept(ModItems.COCONUT_PRESSURE_PLATE);
        output.accept(ModItems.COCONUT_BUTTON);
        output.accept(ModItems.COCONUT_SIGN);
        output.accept(ModItems.COCONUT_HANGING_SIGN);
        output.accept(ModItems.COCONUT_BOAT);
        output.accept(ModItems.COCONUT_CHEST_BOAT);
        output.accept(ModItems.COCONUT_CABINET);

        output.accept(ModItems.COCONUT);
        output.accept(ModItems.COCONUT_SLICE);

        output.accept(ModItems.MANGO_SAPLING);
        output.accept(ModItems.MANGO_LEAVES);

        output.accept(ModItems.MANGO_LOG);
        output.accept(ModItems.MANGO_WOOD);
        output.accept(ModItems.STRIPPED_MANGO_LOG);
        output.accept(ModItems.STRIPPED_MANGO_WOOD);
        output.accept(ModItems.MANGO_PLANKS);
        output.accept(ModItems.MANGO_STAIRS);
        output.accept(ModItems.MANGO_SLAB);
        output.accept(ModItems.MANGO_FENCE);
        output.accept(ModItems.MANGO_FENCE_GATE);
        output.accept(ModItems.MANGO_DOOR);
        output.accept(ModItems.MANGO_TRAPDOOR);
        output.accept(ModItems.MANGO_PRESSURE_PLATE);
        output.accept(ModItems.MANGO_BUTTON);
        output.accept(ModItems.MANGO_SIGN);
        output.accept(ModItems.MANGO_HANGING_SIGN);
        output.accept(ModItems.MANGO_BOAT);
        output.accept(ModItems.MANGO_CHEST_BOAT);
        output.accept(ModItems.MANGO_CABINET);

        output.accept(ModItems.MANGO);
        output.accept(ModItems.MANGO_SLICE);

        output.accept(ModItems.PAPAYA);
        output.accept(ModItems.PAPAYA_FLOWER);
        output.accept(ModItems.SLICED_PAPAYA);
        output.accept(ModItems.RAW_PAPAYA);
        output.accept(ModItems.RAW_PAPAYA_SLICE);
        output.accept(ModItems.PAPAYA_LOG);
        output.accept(ModItems.STRIPPED_PAPAYA_LOG);
        output.accept(ModItems.PAPAYA_WOOD);
        output.accept(ModItems.STRIPPED_PAPAYA_WOOD);
        output.accept(ModItems.PAPAYA_LEAVES);
        output.accept(ModItems.PAPAYA_SAPLING);
        output.accept(ModItems.PAPAYA_SEEDS);

        output.accept(ModItems.HOLY_BASIL);
        output.accept(ModItems.HOLY_BASIL_SAPLING);
        output.accept(ModItems.BASIL);
        output.accept(ModItems.BASIL_SAPLING);

        output.accept(ModItems.BUTTERFLY_PEA);
        output.accept(ModItems.BUTTERFLY_PEA_SEEDS);

        output.accept(ModItems.BAMBOO_SHOOT);

        output.accept(ModItems.PESTO_SAUCE);
        output.accept(ModItems.FRIED_DURIAN);
        output.accept(ModItems.SOMTAM_FEAST);
        output.accept(ModItems.SOMTAM);
        output.accept(ModItems.LARB_FEAST);
        output.accept(ModItems.LARB);
        output.accept(ModItems.CRAB_FRIED_RICE_FEAST);
        output.accept(ModItems.CRAB_FRIED_RICE);
        output.accept(ModItems.PHAT_KAPHRAO_FEAST);
        output.accept(ModItems.PHAT_KAPHRAO);
        output.accept(ModItems.MANGO_STICKY_RICE_FEAST);
        output.accept(ModItems.MANGO_STICKY_RICE);
        output.accept(ModItems.PINEAPPLE_FRIED_RICE_FEAST);
        output.accept(ModItems.PINEAPPLE_FRIED_RICE);
        output.accept(ModItems.STIR_FRIED_NOODLE);
        output.accept(ModItems.COCONUT_MILK_BOTTLE);
        output.accept(ModItems.DURIAN_CURRY);
        output.accept(ModItems.DURIAN_CAKE);
        output.accept(ModItems.DURIAN_CAKE_SLICE);
        output.accept(ModItems.MANGO_PUDDING);
        output.accept(ModItems.MANGO_PUDDING_SLICE);

        output.accept(ModItems.COCONUT_JELLY);
        output.accept(ModItems.KHANOM_BABIN);
        output.accept(ModItems.COCONUT_PIE);
        output.accept(ModItems.COCONUT_PIE_SLICE);

        output.accept(ModItems.BASIL_OMELETTE_FEAST);
        output.accept(ModItems.BASIL_OMELETTE);

        output.accept(ModItems.BAMBOO_SHOOT_SOUP);
        output.accept(ModItems.STEAMED_BAMBOO_SHOOT);

        output.accept(ModItems.BANANA_IN_COCONUT_MILK);

        for(Item item : ModItems.KHANOM_CHAN_ARRAY){
            output.accept(item);
        }

        for(Item item : ModItems.COCONUT_MILK_ICE_CREAM_ARRAY){
            output.accept(item);
        }
    }

    public static ResourceLocation modid(String id){
        return new ResourceLocation(MOD_ID,id);
    }
}
