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
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.entity.ai.NearestMobStinkyTargetGoal;
import net.firemuffin303.thaidelight.common.registry.*;
import net.firemuffin303.thaidelight.config.ModConfig;
import net.firemuffin303.thaidelight.mixin.accessor.MobAccessor;
import net.firemuffin303.thaidelight.mixin.accessor.StructurePoolAccessor;
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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.biome.Biomes;
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

        BiomeModifications.addFeature(context -> BiomeSelectors.tag(ModTags.WILD_BASIL_BIOMES).test(context),
                GenerationStep.Decoration.VEGETAL_DECORATION,ModFeatures.PATCH_WILD_BASIL);

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
                GoalSelector goalSelector = ((MobAccessor)mob).getTargetSelector();
                if(!goalSelector.getAvailableGoals().isEmpty()){
                    if(entity instanceof NeutralMob && !(mob instanceof AbstractGolem)){
                        goalSelector.addGoal(map.getOrDefault(entity.getType(), 3), new NearestMobStinkyTargetGoal<>(mob, LivingEntity.class, true));
                    } else if(mob instanceof Panda || mob instanceof Llama || mob instanceof Dolphin){
                        goalSelector.addGoal(2, new NearestMobStinkyTargetGoal<>(mob, LivingEntity.class, true));
                    }else if(mob instanceof Monster && !(mob instanceof Creeper)){
                        goalSelector.addGoal(1, new NearestMobStinkyTargetGoal<>(mob, LivingEntity.class, true));

                    }

                }
            }
        });
    }

    public static void addVillagersTrades(){
        if(ModConfig.villagerShouldTradeTDItem){
            ModVillagerTrades.trades().forEach(modVillagerTrade -> {
                TradeOfferHelper.registerVillagerOffers(modVillagerTrade.villagerProfession(), modVillagerTrade.level(), (factories) ->{
                    factories.add((entity, randomSource) -> modVillagerTrade.merchantOffer());
                });
            });
        }


        if(ModConfig.wanderingTraderShouldTradeTDItem){
            TradeOfferHelper.registerWanderingTraderOffers(1, (factories) -> {
                ModVillagerTrades.wanderTrade().forEach(integerMerchantOfferPair -> {
                    factories.add((entity, randomSource) -> integerMerchantOfferPair);
                });
            });
        }

    }
}
