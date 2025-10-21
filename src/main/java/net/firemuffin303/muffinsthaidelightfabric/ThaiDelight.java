package net.firemuffin303.muffinsthaidelightfabric;

import com.mojang.datafixers.util.Pair;
import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.DragonflyEntity;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.FlowerCrabEntity;
import net.firemuffin303.muffinsthaidelightfabric.common.event.ModVillagerTrades;
import net.firemuffin303.muffinsthaidelightfabric.common.item.DragonflyBottleItem;
import net.firemuffin303.muffinsthaidelightfabric.config.ThaiDelightConfig;
import net.firemuffin303.muffinsthaidelightfabric.datagen.ModTagDataGen;
import net.firemuffin303.muffinsthaidelightfabric.mixin.*;
import net.firemuffin303.muffinsthaidelightfabric.mixin.food.ChickenFoodAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.food.FrogFoodAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.food.ParrotTameFoodAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.food.PigFoodAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.loot.LootPoolBuilderAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.loot.LootTableAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.villager.VillagerAccessor;
import net.firemuffin303.muffinsthaidelightfabric.registry.*;
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
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Stream;

public class ThaiDelight implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(ThaiDelight.MOD_ID);
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
        registerComposter();
        registerStrippable();
        registerAnimalFood();
        addVillagersTrades();

        Map<Item,Integer> villagerFoodPoint = new HashMap<>(VillagerAccessor.getFoodPoints());
        villagerFoodPoint.put(ModItems.LIME,1);
        villagerFoodPoint.put(ModItems.PEPPER,1);
        villagerFoodPoint.put(ModItems.PAPAYA,1);
        villagerFoodPoint.put(ModItems.RAW_PAPAYA,1);
        villagerFoodPoint.put(ModItems.DURIAN_PULP,1);
        villagerFoodPoint.put(ModItems.BASIL,1);
        villagerFoodPoint.put(ModItems.HOLY_BASIL,1);
        villagerFoodPoint.put(ModItems.MANGO,1);
        villagerFoodPoint.put(ModItems.COCONUT_SLICE,1);

        VillagerAccessor.setFoodPoints(villagerFoodPoint);

        Set<Item> villagerWantedItems = new HashSet<>(VillagerAccessor.getWantedItems());
        villagerWantedItems.add(ModItems.LIME);
        villagerWantedItems.add(ModItems.PEPPER);
        villagerWantedItems.add(ModItems.PAPAYA);
        villagerWantedItems.add(ModItems.RAW_PAPAYA);
        villagerWantedItems.add(ModItems.DURIAN_PULP);
        villagerWantedItems.add(ModItems.BASIL);
        villagerWantedItems.add(ModItems.HOLY_BASIL);
        villagerWantedItems.add(ModItems.MANGO);
        villagerWantedItems.add(ModItems.COCONUT_SLICE);
        villagerWantedItems.add(ModItems.BASIL_SAPLING);
        villagerWantedItems.add(ModItems.HOLY_BASIL_SAPLING);
        VillagerAccessor.setWantedItems(villagerWantedItems);

        FabricDefaultAttributeRegistry.register(ModEntityTypes.FLOWER_CRAB, FlowerCrabEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.DRAGONFLY, DragonflyEntity.createAttributes());


        SpawnPlacements.register(ModEntityTypes.FLOWER_CRAB,SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FlowerCrabEntity::checkSpawnRules);
        SpawnPlacements.register(ModEntityTypes.DRAGONFLY,SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DragonflyEntity::checkSpawnRules);

        PotionBrewing.addMix(Potions.AWKWARD,ModItems.FERMENTED_FISH,ModMobEffects.STINKY_POTION);
        PotionBrewing.addMix(ModMobEffects.STINKY_POTION, Items.REDSTONE,ModMobEffects.LONG_STINKY_POTION);
        PotionBrewing.addMix(ModMobEffects.STINKY_POTION, Items.GLOWSTONE_DUST,ModMobEffects.STRONG_STINKY_POTION);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.BEACH), MobCategory.CREATURE,ModEntityTypes.FLOWER_CRAB,10,3,5);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.MANGROVE_SWAMP,Biomes.SWAMP), MobCategory.CREATURE,ModEntityTypes.DRAGONFLY,2,1,3);

        BiomeModifications.addFeature((context) ->{
            return BiomeSelectors.includeByKey(Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.FLOWER_FOREST).test(context);
        }, GenerationStep.Decoration.VEGETAL_DECORATION, ModFeatures.PATCH_LIME_BUSH);

        BiomeModifications.addFeature((context) ->{
            return BiomeSelectors.includeByKey(Biomes.SAVANNA,Biomes.SAVANNA_PLATEAU,Biomes.WINDSWEPT_SAVANNA).test(context);
        }, GenerationStep.Decoration.VEGETAL_DECORATION, ModFeatures.PATCH_WILD_PEPPER);

        BiomeModifications.addFeature((context) ->{
            return BiomeSelectors.includeByKey(Biomes.SAVANNA,Biomes.SAVANNA_PLATEAU,Biomes.WINDSWEPT_SAVANNA).test(context);
        }, GenerationStep.Decoration.VEGETAL_DECORATION, ModFeatures.PAPAYA_TREE_CHECKED);

        BiomeModifications.addFeature(context -> BiomeSelectors.includeByKey(Biomes.JUNGLE).test(context),
                GenerationStep.Decoration.VEGETAL_DECORATION,ModFeatures.TREES_DURIAN);

        BiomeModifications.addFeature(context -> BiomeSelectors.includeByKey(Biomes.SPARSE_JUNGLE).test(context), GenerationStep.Decoration.VEGETAL_DECORATION,ModFeatures.TREES_DURIAN_SPARSE_JUNGLE);

        BiomeModifications.addFeature(context -> BiomeSelectors.tag(ModTagDataGen.ModBiomeTagDataGen.MANGO_TREE_BIOMES).test(context),
                GenerationStep.Decoration.VEGETAL_DECORATION,ModFeatures.TREES_MANGO);

        BiomeModifications.addFeature(context -> BiomeSelectors.includeByKey(Biomes.BEACH).test(context), GenerationStep.Decoration.VEGETAL_DECORATION,ModFeatures.TREES_COCONUT);

        ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> {
            addToStructurePool(minecraftServer,
                    new ResourceLocation("minecraft","village/plains/houses"),
                    ThaiDelight.modid("village/plains/houses/small_thai_house_1"),2);

            addToStructurePool(minecraftServer,
                    new ResourceLocation("minecraft","village/savanna/houses"),
                    ThaiDelight.modid("village/savanna/houses/savanna_small_thai_house_1"),2);
        });
    }

    private void registerComposter(){
        ComposterBlock.COMPOSTABLES.put(ModItems.PEPPER_SEED,0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.PAPAYA_LEAVES,0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.LIME_SAPLING,0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.PAPAYA_SAPLING,0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.PEPPER,0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.LIME,0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.RAW_PAPAYA,0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.PAPAYA,0.65f);

        ComposterBlock.COMPOSTABLES.put(ModItems.SLICED_LIME,0.4f);
        ComposterBlock.COMPOSTABLES.put(ModItems.RAW_PAPAYA_SLICE,0.4f);
        ComposterBlock.COMPOSTABLES.put(ModItems.SLICED_PAPAYA,0.4f);

        ComposterBlock.COMPOSTABLES.put(Item.byBlock(ModBlocks.PAPAYA_LOG),0.8f);
        ComposterBlock.COMPOSTABLES.put(Item.byBlock(ModBlocks.STRIPPED_PAPAYA_LOG),0.8f);
        ComposterBlock.COMPOSTABLES.put(Item.byBlock(ModBlocks.PAPAYA_WOOD),0.8f);
        ComposterBlock.COMPOSTABLES.put(Item.byBlock(ModBlocks.STRIPPED_PAPAYA_WOOD),0.8f);
    }

    private void registerStrippable(){
        StrippableBlockRegistry.register(ModBlocks.PAPAYA_LOG,ModBlocks.STRIPPED_PAPAYA_LOG);
        StrippableBlockRegistry.register(ModBlocks.PAPAYA_WOOD,ModBlocks.STRIPPED_PAPAYA_WOOD);
        StrippableBlockRegistry.register(ModBlocks.DURIAN_LOG,ModBlocks.STRIPPED_DURIAN_LOG);
        StrippableBlockRegistry.register(ModBlocks.DURIAN_WOOD,ModBlocks.STRIPPED_DURIAN_WOOD);
        StrippableBlockRegistry.register(ModBlocks.MANGO_LOG,ModBlocks.STRIPPED_MANGO_LOG);
        StrippableBlockRegistry.register(ModBlocks.MANGO_WOOD,ModBlocks.STRIPPED_MANGO_WOOD);
        StrippableBlockRegistry.register(ModBlocks.COCONUT_LOG,ModBlocks.STRIPPED_COCONUT_LOG);
        StrippableBlockRegistry.register(ModBlocks.COCONUT_WOOD,ModBlocks.STRIPPED_COCONUT_WOOD);
    }

    private void registerAnimalFood(){
        ParrotTameFoodAccessor.getTameFood().add(Item.byBlock(ModBlocks.PAPAYA_SAPLING));
        ParrotTameFoodAccessor.getTameFood().add(ModItems.PEPPER_SEED);
        ParrotTameFoodAccessor.getTameFood().add(ModItems.BUTTERFLY_PEA_SEEDS);

        Ingredient newPigFoods = Ingredient.of(
                ModItems.RAW_PAPAYA,
                ModItems.PAPAYA,
                ModItems.SLICED_PAPAYA,
                ModItems.RAW_PAPAYA_SLICE,
                ModItems.LIME,
                ModItems.SLICED_LIME,
                ModItems.BAMBOO_SHOOT
        );
        Ingredient newChickenFoods = Ingredient.of(ModItems.PAPAYA_SEEDS,ModItems.PEPPER_SEED,ModItems.BUTTERFLY_PEA_SEEDS);

        Ingredient newFrogFoods = Ingredient.of(ModItems.DRAGONFLY,ModItems.COOKED_DRAGONFLY);

        PigFoodAccessor.setFoodItems(Ingredient.of(
                Stream.concat(Arrays.stream(PigFoodAccessor.getFoodItems().getItems()),Arrays.stream(newPigFoods.getItems()))
        ));

        ChickenFoodAccessor.setFoodItems(Ingredient.of(
                Stream.concat(Arrays.stream(ChickenFoodAccessor.getFoodItems().getItems()),Arrays.stream(newChickenFoods.getItems()))
        ));

        FrogFoodAccessor.setFoodItems(Ingredient.of(
                Stream.concat(Arrays.stream(FrogFoodAccessor.getFoodItems().getItems()),Arrays.stream(newFrogFoods.getItems()))
        ));

    }

    private void addVillagersTrades(){
        ModVillagerTrades.trades().forEach(modVillagerTrade -> {
            TradeOfferHelper.registerVillagerOffers(modVillagerTrade.villagerProfession(), modVillagerTrade.level(), (factories) ->{
                factories.add((entity, randomSource) -> modVillagerTrade.merchantOffer());
            });
        });


        TradeOfferHelper.registerWanderingTraderOffers(1, (factories) -> {
            ModVillagerTrades.wanderTrade().forEach(integerMerchantOfferPair -> {
                factories.add((entity, randomSource) -> integerMerchantOfferPair);
            });
        });
    }

    public static void addToStructurePool(MinecraftServer server, ResourceLocation poolIdentifier, ResourceLocation nbtIdentifier, int weight) {
        Holder<StructureProcessorList> emptyProcessList = server.registryAccess().registryOrThrow(Registries.PROCESSOR_LIST)
                .getHolderOrThrow(ResourceKey.create(Registries.PROCESSOR_LIST, new ResourceLocation("minecraft", "empty")));
        Registry<StructureTemplatePool> structureTemplatePools = server.registryAccess().registry(Registries.TEMPLATE_POOL).orElseThrow();

        StructureTemplatePool structure = structureTemplatePools.get(poolIdentifier);

        if(structure == null){
            return;
        }

        SinglePoolElement singlePoolElement = StructurePoolElement.legacy(nbtIdentifier.toString(),emptyProcessList)
                .apply(StructureTemplatePool.Projection.RIGID);

        List<Pair<StructurePoolElement,Integer>> elements = new ArrayList<>(((StructurePoolAccessor)structure).getRawTemplates());
        elements.add(Pair.of(singlePoolElement,weight));
        ((StructurePoolAccessor)structure).setRawTemplates(elements);

        for(int i = 0; i < weight; i++){
            ((StructurePoolAccessor)structure).getTemplates().add(singlePoolElement);
        }
    }

    private static void itemsGenerator(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output){
        output.accept(ModItems.MORTAR);
        output.accept(ModItems.LIME_CRATE);
        output.accept(ModItems.PEPPER_CRATE);
        output.accept(ModItems.RAW_PAPAYA_CRATE);
        output.accept(ModItems.PAPAYA_CRATE);
        output.accept(ModItems.DURIAN_CRATE);
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
        output.accept(ModItems.MEDIUM_DURIAN);
        output.accept(ModItems.DURIAN);
        output.accept(ModItems.DURIAN_PULP);
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
        output.accept(ModItems.NEW_PAPAYA_LEAVES);
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
