package net.firemuffin303.thaidelight.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.entity.Dragonfly;
import net.firemuffin303.thaidelight.common.entity.FlowerCrabEntity;
import net.firemuffin303.thaidelight.common.event.ModVillagerTrades;
import net.firemuffin303.thaidelight.common.item.bottle.DragonflyBottleItem;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModConfiguredFeatures;
import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.fabric.common.registry.ModBlocksFabric;
import net.firemuffin303.thaidelight.fabric.common.registry.ModItemsFabric;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

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

        FabricDefaultAttributeRegistry.register(ModEntityTypes.FLOWER_CRAB.get(), FlowerCrabEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.DRAGONFLY.get(), Dragonfly.createAttributes());

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.MANGROVE_SWAMP,Biomes.SWAMP), MobCategory.CREATURE,ModEntityTypes.FLOWER_CRAB.get(),10,3,5);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.MANGROVE_SWAMP,Biomes.SWAMP), MobCategory.CREATURE,ModEntityTypes.DRAGONFLY.get(),2,1,3);

        BiomeModifications.addFeature((context) ->{
            return BiomeSelectors.includeByKey(Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.FLOWER_FOREST).test(context);
        }, GenerationStep.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.PATCH_LIME_BUSH);

        BiomeModifications.addFeature((context) ->{
            return BiomeSelectors.includeByKey(Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.FLOWER_FOREST,Biomes.SAVANNA,Biomes.SAVANNA_PLATEAU).test(context);
        }, GenerationStep.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.PATCH_WILD_PEPPER);

        BiomeModifications.addFeature((context) ->{
            return BiomeSelectors.includeByKey(Biomes.FOREST, Biomes.FLOWER_FOREST,Biomes.SAVANNA,Biomes.SAVANNA_PLATEAU).test(context);
        }, GenerationStep.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.PAPAYA_TREE_CHECKED);

        this.addVillagersTrades();

        Set<ResourceKey<LootTable>> chestsId = Set.of(
                BuiltInLootTables.VILLAGE_PLAINS_HOUSE,
                BuiltInLootTables.VILLAGE_SAVANNA_HOUSE,
                BuiltInLootTables.VILLAGE_SNOWY_HOUSE,
                BuiltInLootTables.VILLAGE_TAIGA_HOUSE,
                BuiltInLootTables.VILLAGE_DESERT_HOUSE,
                BuiltInLootTables.ABANDONED_MINESHAFT,
                BuiltInLootTables.PILLAGER_OUTPOST);

        /*
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            ResourceLocation injectId = ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID, "inject/" + id.name());


            if (chestsId.contains(id)) {
                tableBuilder.pool(LootPool.lootPool().add(LootTableReference.lootTableReference(injectId).setWeight(1).setQuality(0)).build());
            }

        });
        */

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,"main"),
                FabricItemGroup.builder().title(Component.translatable("itemGroup."+ThaiDelight.MOD_ID+".main"))
                        .icon(() -> new ItemStack(ModBlocks.MORTAR.get()))
                        .displayItems(this::displayItem).build());

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

        output.accept(ModItemsFabric.SOMTAM);
        output.accept(ModItemsFabric.SPICY_MINCED_MEAT_SALAD);
        output.accept(ModItemsFabric.CRAB_FRIED_RICE);
        output.accept(ModItemsFabric.STIR_FRIED_NOODLE);

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
