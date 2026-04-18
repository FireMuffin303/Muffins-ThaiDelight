package net.firemuffin303.thaidelight.common;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.entity.ai.NearestMobStinkyTargetGoal;
import net.firemuffin303.thaidelight.common.registry.*;
import net.firemuffin303.thaidelight.mixin.accessor.MobAccessor;
import net.firemuffin303.thaidelight.mixin.accessor.StructurePoolAccessor;
import net.firemuffin303.thaidelight.mixin.accessor.VillagerAccessor;
import net.firemuffin303.thaidelight.mixin.food.ChickenFoodAccessor;
import net.firemuffin303.thaidelight.mixin.food.FrogFoodAccessor;
import net.firemuffin303.thaidelight.mixin.food.ParrotTameFoodAccessor;
import net.firemuffin303.thaidelight.mixin.food.PigFoodAccessor;
import net.firemuffin303.thaidelight.mixin.fabric.loot.LootPoolBuilderAccessor;
import net.firemuffin303.thaidelight.mixin.fabric.loot.LootTableAccessor;
import net.firemuffin303.thaidelight.util.BlockEntityTypeAdder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;

import java.util.*;
import java.util.stream.Stream;

public class TDFabricEvents {
    public static void worldGeneration(){
        boolean shouldCoconutSpawn = true;
        boolean shouldMangoSpawn = true;
        /*
        if(ThaiDelightCommonClient.IS_FOT_INSTALLED){
            shouldCoconutSpawn = ThaiDelightConfig.coconutTreeType != ThaiDelightConfig.TreeType.FISH_OF_THIEVES;
            shouldMangoSpawn = ThaiDelightConfig.shouldMangoTreeSpawn;
        }

         */

        BiomeModifications.addFeature((context) ->{
            return BiomeSelectors.tag(ModTags.LIME_TREE_BIOMES).test(context);
        }, GenerationStep.Decoration.VEGETAL_DECORATION, ModFeatures.PATCH_LIME_BUSH);

        BiomeModifications.addFeature((context) ->{
            return BiomeSelectors.tag(ModTags.PEPPER_TREE_BIOMES).test(context);
        }, GenerationStep.Decoration.VEGETAL_DECORATION, ModFeatures.PATCH_WILD_PEPPER);

        BiomeModifications.addFeature((context) ->{
            return BiomeSelectors.tag(ModTags.PAPAYA_TREE_BIOMES).test(context);
        }, GenerationStep.Decoration.VEGETAL_DECORATION, ModFeatures.TREES_PAPAYA);

        BiomeModifications.addFeature(context -> BiomeSelectors.tag(ModTags.DURIAN_TREE_BIOMES).test(context),
                GenerationStep.Decoration.VEGETAL_DECORATION,ModFeatures.TREES_DURIAN);

        BiomeModifications.addFeature(context -> BiomeSelectors.includeByKey(Biomes.SPARSE_JUNGLE).test(context),
                GenerationStep.Decoration.VEGETAL_DECORATION,ModFeatures.TREES_DURIAN_SPARSE_JUNGLE);


        if(shouldMangoSpawn){
            BiomeModifications.addFeature(context -> BiomeSelectors.tag(ModTags.MANGO_TREE_BIOMES).test(context),
                    GenerationStep.Decoration.VEGETAL_DECORATION,ModFeatures.TREES_MANGO);
        }

        if(shouldCoconutSpawn){
            BiomeModifications.addFeature(context -> BiomeSelectors.tag(ModTags.COCONUT_TREE_BIOMES).test(context),
                    GenerationStep.Decoration.VEGETAL_DECORATION,ModFeatures.TREES_COCONUT);
        }


        BiomeModifications.addFeature(context -> BiomeSelectors.tag(ModTags.WILD_HOLY_BASIL_BIOMES).test(context),
                GenerationStep.Decoration.VEGETAL_DECORATION,ModFeatures.PATCH_WILD_HOLY_BASIL);

        BiomeModifications.addFeature(context -> BiomeSelectors.tag(ModTags.WILD_BASIL_BIOMES).test(context),
                GenerationStep.Decoration.VEGETAL_DECORATION,ModFeatures.PATCH_WILD_BASIL);

        BiomeModifications.addFeature(context -> BiomeSelectors.tag(ModTags.WILD_ALL_BASIL_BIOMES).test(context),
                GenerationStep.Decoration.VEGETAL_DECORATION,ModFeatures.PATCH_WILD_ALL_BASIL);


        BiomeModifications.addFeature(context -> BiomeSelectors.tag(ModTags.BUTTERFLY_PEA_BIOMES).test(context),
                GenerationStep.Decoration.VEGETAL_DECORATION,ModFeatures.PATCH_BUTTERFLY_PEA
        );

        ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> {
            addToStructurePool(minecraftServer,
                    new ResourceLocation("minecraft","village/plains/houses"),
                    ThaiDelightCommon.modid("village/plains/houses/small_thai_house_1"),2);

            addToStructurePool(minecraftServer,
                    new ResourceLocation("minecraft","village/savanna/houses"),
                    ThaiDelightCommon.modid("village/savanna/houses/savanna_small_thai_house_1"),2);



            if(minecraftServer.isDedicatedServer()){
                Optional<BlockEntityType<?>> blockEntityTypeOptional = BuiltInRegistries.BLOCK_ENTITY_TYPE.getOptional(new ResourceLocation("farmersdelight","cabinet"));
                if(blockEntityTypeOptional.isPresent()){
                    BlockEntityTypeAdder cabinetAccessor = (BlockEntityTypeAdder) blockEntityTypeOptional.get();
                    ModBlocks.CABINET.forEach(blockSupplier -> cabinetAccessor.addSupportBlock(blockSupplier.get()));
                }
            }
        });
    }

    public static void registerFuel(){
        ThaiDelightCommon.FUEL_MAP.forEach(FuelRegistry.INSTANCE::add);
    }

    public static void modifyLootTable(){
        Set<ResourceLocation> chestsId = Set.of(
                BuiltInLootTables.VILLAGE_PLAINS_HOUSE,
                BuiltInLootTables.VILLAGE_SAVANNA_HOUSE,
                BuiltInLootTables.VILLAGE_SNOWY_HOUSE,
                BuiltInLootTables.VILLAGE_TAIGA_HOUSE,
                BuiltInLootTables.VILLAGE_DESERT_HOUSE,
                BuiltInLootTables.ABANDONED_MINESHAFT,
                BuiltInLootTables.PILLAGER_OUTPOST);

        LootTableEvents.MODIFY.register(new LootTableEvents.Modify() {
            @Override
            public void modifyLootTable(ResourceManager resourceManager, LootDataManager lootDataManager,
                                        ResourceLocation resourceLocation, LootTable.Builder builder, LootTableSource lootTableSource) {

                if (chestsId.contains(resourceLocation)) {
                    ResourceLocation injectId = ThaiDelightCommon.modid("inject/" + resourceLocation.getPath());
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

    public static void initializeStinkyEffect(){
        Map<EntityType<?>,Integer> map = Map.of(
                EntityType.BEE,2,
                EntityType.ENDERMAN,3,
                EntityType.POLAR_BEAR,3,
                EntityType.WOLF,4,
                EntityType.ZOMBIFIED_PIGLIN,2
        );

        ServerEntityEvents.ENTITY_LOAD.register((entity, serverLevel) -> {
            if(entity instanceof Mob mob){
                GoalSelector goalSelector = ((MobAccessor)mob).getGoalSelector();
                if(!goalSelector.getAvailableGoals().isEmpty()){
                    if(mob instanceof NeutralMob && !(mob instanceof AbstractGolem)){
                        goalSelector.addGoal(map.getOrDefault(entity.getType(), 3), new NearestMobStinkyTargetGoal<>(mob, Player.class, true));
                    } else if(mob instanceof Panda || mob instanceof Llama || mob instanceof Dolphin){
                        goalSelector.addGoal(2, new NearestMobStinkyTargetGoal<>(mob, Player.class, true));
                    }

                }
            }
        });
    }

    public static void setVillagerItem(){
        Map<Item,Integer> villagerFoodPoint = new HashMap<>(VillagerAccessor.getFoodPoints());
        villagerFoodPoint.put(ModItems.LIME.get(),1);
        villagerFoodPoint.put(ModItems.PEPPER.get(),1);
        villagerFoodPoint.put(ModItems.PAPAYA.get(),1);
        villagerFoodPoint.put(ModItems.RAW_PAPAYA.get(),1);
        villagerFoodPoint.put(ModItems.DURIAN_PULP.get(),1);
        villagerFoodPoint.put(ModItems.BASIL.get(),1);
        villagerFoodPoint.put(ModItems.HOLY_BASIL.get(),1);
        villagerFoodPoint.put(ModItems.MANGO.get(),1);
        villagerFoodPoint.put(ModItems.COCONUT_SLICE.get(),1);

        VillagerAccessor.setFoodPoints(villagerFoodPoint);

        Set<Item> villagerWantedItems = new HashSet<>(VillagerAccessor.getWantedItems());
        villagerWantedItems.add(ModItems.LIME.get());
        villagerWantedItems.add(ModItems.PEPPER.get());
        villagerWantedItems.add(ModItems.PAPAYA.get());
        villagerWantedItems.add(ModItems.RAW_PAPAYA.get());
        villagerWantedItems.add(ModItems.DURIAN_PULP.get());
        villagerWantedItems.add(ModItems.BASIL.get());
        villagerWantedItems.add(ModItems.HOLY_BASIL.get());
        villagerWantedItems.add(ModItems.MANGO.get());
        villagerWantedItems.add(ModItems.COCONUT_SLICE.get());
        VillagerAccessor.setWantedItems(villagerWantedItems);
    }

    public static void registerAnimalFood(){
        ParrotTameFoodAccessor.getTameFood().add(Item.byBlock(ModBlocks.PAPAYA_SAPLING.get()));
        ParrotTameFoodAccessor.getTameFood().add(ModItems.PEPPER_SEED.get());
        ParrotTameFoodAccessor.getTameFood().add(ModItems.BUTTERFLY_PEA_SEEDS.get());

        Ingredient newPigFoods = Ingredient.of(
                ModItems.RAW_PAPAYA.get(),
                ModItems.PAPAYA.get(),
                ModItems.SLICED_PAPAYA.get(),
                ModItems.RAW_PAPAYA_SLICE.get(),
                ModItems.LIME.get(),
                ModItems.SLICED_LIME.get(),
                ModItems.BAMBOO_SHOOT.get()
        );
        Ingredient newChickenFoods = Ingredient.of(ModItems.PAPAYA_SEEDS.get(),ModItems.PEPPER_SEED.get(),ModItems.BUTTERFLY_PEA_SEEDS.get());

        Ingredient newFrogFoods = Ingredient.of(ModItems.DRAGONFLY.get(),ModItems.COOKED_DRAGONFLY.get());

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

    public static void registerComposter(){
        ComposterBlock.COMPOSTABLES.put(ModItems.PEPPER_SEED.get(),0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.PAPAYA_LEAVES.get(),0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.LIME_SAPLING.get(),0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.PAPAYA_SAPLING.get(),0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.PEPPER.get(),0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.LIME.get(),0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.RAW_PAPAYA.get(),0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.PAPAYA.get(),0.65f);

        ComposterBlock.COMPOSTABLES.put(ModItems.SLICED_LIME.get(),0.4f);
        ComposterBlock.COMPOSTABLES.put(ModItems.RAW_PAPAYA_SLICE.get(),0.4f);
        ComposterBlock.COMPOSTABLES.put(ModItems.SLICED_PAPAYA.get(),0.4f);

        ComposterBlock.COMPOSTABLES.put(Item.byBlock(ModBlocks.PAPAYA_LOG.get()),0.8f);
        ComposterBlock.COMPOSTABLES.put(Item.byBlock(ModBlocks.STRIPPED_PAPAYA_LOG.get()),0.8f);
        ComposterBlock.COMPOSTABLES.put(Item.byBlock(ModBlocks.PAPAYA_WOOD.get()),0.8f);
        ComposterBlock.COMPOSTABLES.put(Item.byBlock(ModBlocks.STRIPPED_PAPAYA_WOOD.get()),0.8f);
        ComposterBlock.COMPOSTABLES.put(ModItems.DURIAN_PEEL.get(),0.8f);
    }

    public static void registerStrippable(){
        StrippableBlockRegistry.register(ModBlocks.PAPAYA_LOG.get(),ModBlocks.STRIPPED_PAPAYA_LOG.get());
        StrippableBlockRegistry.register(ModBlocks.PAPAYA_WOOD.get(),ModBlocks.STRIPPED_PAPAYA_WOOD.get());
        StrippableBlockRegistry.register(ModBlocks.DURIAN_LOG.get(),ModBlocks.STRIPPED_DURIAN_LOG.get());
        StrippableBlockRegistry.register(ModBlocks.DURIAN_WOOD.get(),ModBlocks.STRIPPED_DURIAN_WOOD.get());
        StrippableBlockRegistry.register(ModBlocks.MANGO_LOG.get(),ModBlocks.STRIPPED_MANGO_LOG.get());
        StrippableBlockRegistry.register(ModBlocks.MANGO_WOOD.get(),ModBlocks.STRIPPED_MANGO_WOOD.get());
        StrippableBlockRegistry.register(ModBlocks.COCONUT_LOG.get(),ModBlocks.STRIPPED_COCONUT_LOG.get());
        StrippableBlockRegistry.register(ModBlocks.COCONUT_WOOD.get(),ModBlocks.STRIPPED_COCONUT_WOOD.get());
    }

    public static void addVillagersTrades(){
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
}
