package net.firemuffin303.thaidelight.fabric;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.entity.Dragonfly;
import net.firemuffin303.thaidelight.common.entity.FlowerCrabEntity;
import net.firemuffin303.thaidelight.common.event.ModVillagerTrades;
import net.firemuffin303.thaidelight.common.registry.ModConfiguredFeatures;
import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.firemuffin303.thaidelight.fabric.common.registry.ModBlocksFabric;
import net.firemuffin303.thaidelight.fabric.common.registry.ModItemsFabric;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class ThaiDelightModFabric implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Muffin's Thai Delight");
    @Override
    public void onInitialize() {

        ThaiDelight.init();
        ThaiDelight.postInit();

        ModBlocksFabric.init();
        ModItemsFabric.init();

        ServerLifecycleEvents.SERVER_STARTING.register((server) ->{
            LOGGER.info("Registering thaidelight structure in villages");
            ThaiDelight.registerStructure(server);
        });

        FabricDefaultAttributeRegistry.register(ModEntityTypes.FLOWER_CRAB, FlowerCrabEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.DRAGONFLY, Dragonfly.createAttributes());

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.MANGROVE_SWAMP,Biomes.SWAMP), MobCategory.CREATURE,ModEntityTypes.FLOWER_CRAB,10,3,5);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.MANGROVE_SWAMP,Biomes.SWAMP,Biomes.RIVER), MobCategory.CREATURE,ModEntityTypes.DRAGONFLY,2,1,3);

        BiomeModifications.addFeature((context) ->{
            return BiomeSelectors.includeByKey(Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.FLOWER_FOREST).test(context);
        }, GenerationStep.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.PATCH_LIME_BUSH);

        BiomeModifications.addFeature((context) ->{
            return BiomeSelectors.includeByKey(Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.FLOWER_FOREST,Biomes.SAVANNA,Biomes.SAVANNA_PLATEAU).test(context);
        }, GenerationStep.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.PATCH_WILD_PEPPER);

        BiomeModifications.addFeature((context) ->{
            return BiomeSelectors.includeByKey(Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.FLOWER_FOREST,Biomes.SAVANNA,Biomes.SAVANNA_PLATEAU).test(context);
        }, GenerationStep.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.PAPAYA_TREE_CHECKED);

        this.addVillagersTrades();

        Set<ResourceLocation> chestsId = Set.of(
                BuiltInLootTables.VILLAGE_PLAINS_HOUSE,
                BuiltInLootTables.VILLAGE_SAVANNA_HOUSE,
                BuiltInLootTables.VILLAGE_SNOWY_HOUSE,
                BuiltInLootTables.VILLAGE_TAIGA_HOUSE,
                BuiltInLootTables.VILLAGE_DESERT_HOUSE,
                BuiltInLootTables.ABANDONED_MINESHAFT,
                BuiltInLootTables.PILLAGER_OUTPOST);

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            ResourceLocation injectId = new ResourceLocation(ThaiDelight.MOD_ID, "inject/" + id.getPath());


            if (chestsId.contains(id)) {
                tableBuilder.pool(LootPool.lootPool().add(LootTableReference.lootTableReference(injectId).setWeight(1).setQuality(0)).build());
            }

        });

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
}
