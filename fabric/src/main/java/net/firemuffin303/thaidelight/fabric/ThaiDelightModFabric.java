package net.firemuffin303.thaidelight.fabric;

import com.mojang.datafixers.util.Pair;
import net.firemuffin303.thaidelight.fabric.mixin.StructurePoolAccessorMixin;
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
import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.fabric.common.registry.ModBlocksFabric;
import net.firemuffin303.thaidelight.fabric.common.registry.ModItemsFabric;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.biome.Biomes;
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
        ServerLifecycleEvents.SERVER_STARTING.register((server) ->{
            LOGGER.info("Registering structure in village type of plains");
            this.addToStructurePool(server,
                    new ResourceLocation("minecraft","village/plains/houses"),
                    new ResourceLocation(ThaiDelight.MOD_ID, "village/plains/houses/thai_house_1"),4);
        });
        ThaiDelight.init();
        ThaiDelight.postInit();

        ModBlocksFabric.init();
        ModItemsFabric.init();

        FabricDefaultAttributeRegistry.register(ModEntityTypes.FLOWER_CRAB, FlowerCrabEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.DRAGONFLY, Dragonfly.createAttributes());


        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.MANGROVE_SWAMP,Biomes.SWAMP), MobCategory.CREATURE,ModEntityTypes.FLOWER_CRAB,10,3,5);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.MANGROVE_SWAMP,Biomes.SWAMP,Biomes.RIVER), MobCategory.AMBIENT,ModEntityTypes.DRAGONFLY,5,1,3);

       TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, (factories) -> {
           factories.add(new VillagerTrades.EmeraldForItems(ModItems.PEPPER,26,5,2));
           factories.add(new VillagerTrades.EmeraldForItems(ModItems.LIME,26,5,2));
           factories.add(new VillagerTrades.EmeraldForItems(ModItems.UNRIPE_PAPAYA,26,5,2));


       });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2, (factories) -> {
            factories.add(new VillagerTrades.EmeraldForItems(ModItems.PAPAYA,26,5,2));
        });


        TradeOfferHelper.registerWanderingTraderOffers(1, (factories) -> {
            factories.add(new VillagerTrades.ItemsForEmeralds(ModItems.PEPPER_SEED,1,2,1));
            factories.add(new VillagerTrades.ItemsForEmeralds(ModItems.LIME_SEED,1,2,1));
            factories.add(new VillagerTrades.ItemsForEmeralds(ModItems.PAPAYA_SEED,1,2,1));
        });




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

    protected void addToStructurePool(MinecraftServer server, ResourceLocation poolIdentifier, ResourceLocation nbtIdentifier, int weight) {
        Holder.Reference<StructureProcessorList> emptyProcessorList = server.registryAccess().registryOrThrow(Registries.PROCESSOR_LIST)
                .getHolderOrThrow(ResourceKey.create(Registries.PROCESSOR_LIST, new ResourceLocation("minecraft", "empty")));

        server.registryAccess().registryOrThrow(Registries.TEMPLATE_POOL).getOptional(poolIdentifier).ifPresentOrElse((structurePool) -> {
            SinglePoolElement compostPilePool = (SinglePoolElement) StructurePoolElement.single(nbtIdentifier.toString(), emptyProcessorList).apply(StructureTemplatePool.Projection.RIGID);
            List<Pair<StructurePoolElement, Integer>> elementCounts = new ArrayList(((StructurePoolAccessorMixin)structurePool).getRawTemplates());
            elementCounts.add(Pair.of(compostPilePool, weight));
            ((StructurePoolAccessorMixin)structurePool).setRawTemplates(elementCounts);
            IntStream.range(0, weight).forEach((value) -> {
                ((StructurePoolAccessorMixin)structurePool).getTemplates().add(compostPilePool);
            });
        }, () -> {
            LOGGER.warn("No structure pool found for {}, no compost heaps will be added on it.", poolIdentifier);
        });
    }

}
