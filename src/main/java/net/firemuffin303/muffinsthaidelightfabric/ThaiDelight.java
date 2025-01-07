package net.firemuffin303.muffinsthaidelightfabric;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.fabricators_of_create.porting_lib.entity.events.EntityEvents;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.BuffaloEntity;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.DragonflyEntity;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.FlowerCrabEntity;
import net.firemuffin303.muffinsthaidelightfabric.common.entitydata.SpicyData;
import net.firemuffin303.muffinsthaidelightfabric.common.event.ModVillagerTrades;
import net.firemuffin303.muffinsthaidelightfabric.mixin.*;
import net.firemuffin303.muffinsthaidelightfabric.registry.*;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
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
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ThaiDelight implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(ThaiDelight.MOD_ID);
    public static final String MOD_ID = "muffins_thaidelight";
    public static final String TASTY_NBT = "Tasty";

    public static final ResourceLocation SPICY_PAYLOAD_ID = new ResourceLocation(MOD_ID,"spicypayload");

    public static final CreativeModeTab MOD_TAB = FabricItemGroup.builder()
            .title(Component.translatable("itemGroup."+ThaiDelight.MOD_ID+".main"))
            .icon(() -> new ItemStack(ModBlocks.MORTAR))
            .displayItems((itemDisplayParameters, output) -> {
                ModItems.ITEMS.forEach(output::accept);
            })
            .build();
    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,new ResourceLocation(MOD_ID,"main"),MOD_TAB);
        init();
        postInit();



        ServerPlayConnectionEvents.JOIN.register((serverGamePacketListener, packetSender, minecraftServer) -> minecraftServer.execute(() -> {
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
            buf.writeInt(((SpicyData.SpicyAccessor)serverGamePacketListener.player).muffinsThaiDelight$access().spicyLevel);
            ServerPlayNetworking.send(serverGamePacketListener.player,ThaiDelight.SPICY_PAYLOAD_ID,buf);
        }));


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

        LootTableEvents.MODIFY.register((resourceManager, lootDataManager, resourceLocation, builder, lootTableSource) -> {
            ResourceLocation injectId = new ResourceLocation(ThaiDelight.MOD_ID, "inject/" + resourceLocation.getPath());


            if (chestsId.contains(resourceLocation)) {
                builder.pool(LootPool.lootPool().add(LootTableReference.lootTableReference(injectId).setWeight(1).setQuality(0)).build());
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
    }

    private void postInit(){
        ModCauldronInteraction.init();
        registerComposter();
        registerStrippable();
        registerAnimalFood();
        addVillagersTrades();

        FabricDefaultAttributeRegistry.register(ModEntityTypes.FLOWER_CRAB, FlowerCrabEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.DRAGONFLY, DragonflyEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.BUFFALO, BuffaloEntity.createAttributes());

        SpawnPlacements.register(ModEntityTypes.FLOWER_CRAB,SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FlowerCrabEntity::checkSpawnRules);
        SpawnPlacements.register(ModEntityTypes.DRAGONFLY,SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DragonflyEntity::checkSpawnRules);

        /*
        List<Item> fishes = BuiltInRegistries.ITEM.stream().filter(item -> new ItemStack(item).is(ItemTags.FISHES)).toList();
        for(Item item : fishes ){
            PotionBrewing.add

            new PotionBrewing.Mix<Item>(item, Ingredient.of(PotionUtils.setPotion(Items.POTION.getDefaultInstance(),Potions.WATER)),ModItems.FISH_SAUCE_BOTTLE);

        }

         */

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

        ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> {
            addToStructurePool(minecraftServer,
                    new ResourceLocation("minecraft","village/plains/houses"),
                    new ResourceLocation(ThaiDelight.MOD_ID, "village/plains/houses/small_thai_house_1"),2);

            addToStructurePool(minecraftServer,
                    new ResourceLocation("minecraft","village/savanna/houses"),
                    new ResourceLocation(ThaiDelight.MOD_ID,"village/savanna/houses/savanna_small_thai_house_1"),2);
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
    }

    private void registerAnimalFood(){
        ParrotTameFoodAccessor.getTameFood().add(Item.byBlock(ModBlocks.PAPAYA_SAPLING));
        ParrotTameFoodAccessor.getTameFood().add(ModItems.PEPPER_SEED);

        Ingredient newPigFoods = Ingredient.of(ModItems.RAW_PAPAYA,ModItems.PAPAYA,ModItems.SLICED_PAPAYA,ModItems.RAW_PAPAYA_SLICE,ModItems.LIME,ModItems.SLICED_LIME);
        Ingredient newChickenFoods = Ingredient.of(ModItems.PAPAYA_SEEDS,ModItems.PEPPER_SEED);

        Ingredient newFrogFoods = Ingredient.of(ModItems.DRAGONFLY,ModItems.COOKED_DRAGONFLY);

        PigFoodAccessor.setFoodItems(Ingredient.of(new ImmutableList.Builder<ItemStack>().addAll(Arrays.stream(PigFoodAccessor.getFoodItems().getItems()).iterator())
                .addAll(Arrays.asList(newPigFoods.getItems())).build().stream()));

        ChickenFoodAccessor.setFoodItems(Ingredient.of(
                new ImmutableList.Builder<ItemStack>().addAll(Arrays.stream(ChickenFoodAccessor.getFoodItems().getItems()).iterator())
                        .addAll(Arrays.asList(newChickenFoods.getItems())).build().stream()));

        FrogFoodAccessor.setFoodItems(Ingredient.of(
                new ImmutableList.Builder<ItemStack>().addAll(Arrays.asList(FrogFoodAccessor.getFoodItems().getItems()).iterator())
                        .addAll(Arrays.asList(newFrogFoods.getItems())).build().stream()));
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
}
