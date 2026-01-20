package net.firemuffin303.muffinsthaidelightfabric.util;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.network.packet.ModLevelEventPacket;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.DragonflyEntity;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.FlowerCrabEntity;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.ai.NearestMobStinkyTargetGoal;
import net.firemuffin303.muffinsthaidelightfabric.common.event.ModVillagerTrades;
import net.firemuffin303.muffinsthaidelightfabric.mixin.MobAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.StructurePoolAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.food.ChickenFoodAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.food.FrogFoodAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.food.ParrotTameFoodAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.food.PigFoodAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.loot.LootPoolBuilderAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.loot.LootTableAccessor;
import net.firemuffin303.muffinsthaidelightfabric.mixin.villager.VillagerAccessor;
import net.firemuffin303.muffinsthaidelightfabric.registry.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ThornsEnchantment;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
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
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Stream;

public class CommonEvents {
    public static ResourceLocation HAS_PINEAPPLE = ThaiDelight.modid("has_pineapple");
    public static ResourceLocation HAS_BANANA = ThaiDelight.modid("has_banana");


    public static void setVillagerItem(){
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
        VillagerAccessor.setWantedItems(villagerWantedItems);
    }

    public static void entityInit(){
        FabricDefaultAttributeRegistry.register(ModEntityTypes.FLOWER_CRAB, FlowerCrabEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.DRAGONFLY, DragonflyEntity.createAttributes());

        SpawnPlacements.register(ModEntityTypes.FLOWER_CRAB,SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FlowerCrabEntity::checkSpawnRules);
        SpawnPlacements.register(ModEntityTypes.DRAGONFLY,SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DragonflyEntity::checkSpawnRules);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.BEACH), MobCategory.CREATURE,ModEntityTypes.FLOWER_CRAB,10,3,5);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.MANGROVE_SWAMP,Biomes.SWAMP), MobCategory.CREATURE,ModEntityTypes.DRAGONFLY,2,1,3);


    }

    public static void worldGeneration(){
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

        BiomeModifications.addFeature(context -> BiomeSelectors.tag(ModTags.MANGO_TREE_BIOMES).test(context),
                GenerationStep.Decoration.VEGETAL_DECORATION,ModFeatures.TREES_MANGO);

        BiomeModifications.addFeature(context -> BiomeSelectors.tag(ModTags.COCONUT_TREE_BIOMES).test(context),
                GenerationStep.Decoration.VEGETAL_DECORATION,ModFeatures.TREES_COCONUT);


        ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> {
            CommonEvents.addToStructurePool(minecraftServer,
                    new ResourceLocation("minecraft","village/plains/houses"),
                    ThaiDelight.modid("village/plains/houses/small_thai_house_1"),2);

            CommonEvents.addToStructurePool(minecraftServer,
                    new ResourceLocation("minecraft","village/savanna/houses"),
                    ThaiDelight.modid("village/savanna/houses/savanna_small_thai_house_1"),2);

            if(minecraftServer.isDedicatedServer()){
                Optional<BlockEntityType<?>> blockEntityTypeOptional = BuiltInRegistries.BLOCK_ENTITY_TYPE.getOptional(new ResourceLocation("farmersdelight","cabinet"));
                if(blockEntityTypeOptional.isPresent()){
                    BlockEntityTypeAdder cabinetAccessor = (BlockEntityTypeAdder) blockEntityTypeOptional.get();
                    ModBlocks.CABINET.forEach(cabinetAccessor::addSupportBlock);
                }
            }
        });
    }

    public static void registerAnimalFood(){
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

    public static void registerComposter(){
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
        ComposterBlock.COMPOSTABLES.put(ModItems.DURIAN_PEEL,0.8f);
    }

    public static void registerStrippable(){
        StrippableBlockRegistry.register(ModBlocks.PAPAYA_LOG,ModBlocks.STRIPPED_PAPAYA_LOG);
        StrippableBlockRegistry.register(ModBlocks.PAPAYA_WOOD,ModBlocks.STRIPPED_PAPAYA_WOOD);
        StrippableBlockRegistry.register(ModBlocks.DURIAN_LOG,ModBlocks.STRIPPED_DURIAN_LOG);
        StrippableBlockRegistry.register(ModBlocks.DURIAN_WOOD,ModBlocks.STRIPPED_DURIAN_WOOD);
        StrippableBlockRegistry.register(ModBlocks.MANGO_LOG,ModBlocks.STRIPPED_MANGO_LOG);
        StrippableBlockRegistry.register(ModBlocks.MANGO_WOOD,ModBlocks.STRIPPED_MANGO_WOOD);
        StrippableBlockRegistry.register(ModBlocks.COCONUT_LOG,ModBlocks.STRIPPED_COCONUT_LOG);
        StrippableBlockRegistry.register(ModBlocks.COCONUT_WOOD,ModBlocks.STRIPPED_COCONUT_WOOD);
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

    public static void registerFuel(){
        FuelRegistry.INSTANCE.add(ModItems.DURIAN_PEEL,200);
        FuelRegistry.INSTANCE.add(ModItems.DURIAN_PEEL_BLOCK,1800);
        FuelRegistry.INSTANCE.add(ModItems.COCONUT_LEAF_BLOCK,4001);
        FuelRegistry.INSTANCE.add(ModItems.PAPAYA_LEAVES,100);
        FuelRegistry.INSTANCE.add(ModItems.DURIAN_CABINET,300);
        FuelRegistry.INSTANCE.add(ModItems.MANGO_CABINET,300);
        FuelRegistry.INSTANCE.add(ModItems.COCONUT_CABINET,300);
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

    public static void durianHelmetThorns(LivingEntity victim, Entity attacker){
        ItemStack helmet = victim.getItemBySlot(EquipmentSlot.HEAD);
        if(helmet.is(ModItems.DURIAN_HELMET) && EnchantmentHelper.getEnchantmentLevel(Enchantments.THORNS,victim) <= 0){
            RandomSource randomSource = victim.getRandom();
            if(ThornsEnchantment.shouldHit(1,randomSource)){
                if(attacker != null){
                    attacker.hurt(victim.damageSources().thorns(victim),ThornsEnchantment.getDamage(1,randomSource));
                }

                if (attacker != null) {
                    helmet.hurtAndBreak(2, victim, livingEntity -> livingEntity.broadcastBreakEvent(EquipmentSlot.HEAD));
                }
            }
        }
    }

    public static ArmorMaterial getDurianMaterial(){
        return new ArmorMaterial() {
            @Override
            public int getDurabilityForType(ArmorItem.@NotNull Type type) {
                int i = 12;
                return switch (type){
                    case HELMET -> 11;
                    case CHESTPLATE -> 16;
                    case LEGGINGS -> 15;
                    case BOOTS -> 13;
                } * i;
            }

            @Override
            public int getDefenseForType(ArmorItem.@NotNull Type type) {
                return switch (type){
                    case HELMET -> 2;
                    case CHESTPLATE -> 6;
                    case LEGGINGS -> 5;
                    case BOOTS -> 2;
                };
            }

            @Override
            public int getEnchantmentValue() {
                return 9;
            }

            @Override
            public @NotNull SoundEvent getEquipSound() {
                return SoundEvents.ARMOR_EQUIP_TURTLE;
            }

            @Override
            public @NotNull Ingredient getRepairIngredient() {
                return Ingredient.of(ModItems.DURIAN_PEEL);
            }

            @Override
            public @NotNull String getName() {
                return "durian";
            }

            @Override
            public float getToughness() {
                return 0f;
            }

            @Override
            public float getKnockbackResistance() {
                return 0f;
            }
        };
    }

    public static Optional<BlockPos> getTopConnectedBlock(BlockGetter blockGetter, BlockPos blockPos, BlockState middleBlock, Direction direction, BlockState endBlock) {
        BlockState blockState;
        BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
        do {
            mutableBlockPos.move(direction);
            blockState = blockGetter.getBlockState(mutableBlockPos);
        } while (blockState == middleBlock);
        if (blockState == endBlock) {
            return Optional.of(mutableBlockPos);
        }
        return Optional.empty();
    }

    public static void setResourceConditions(){
        ResourceConditions.register(HAS_PINEAPPLE, jsonObject -> BuiltInRegistries.ITEM.stream().anyMatch(item -> item.builtInRegistryHolder().is(ModTags.PINEAPPLE)));
        ResourceConditions.register(HAS_BANANA,jsonObject -> BuiltInRegistries.ITEM.stream().anyMatch(item -> item.builtInRegistryHolder().is(ModTags.BANANA)));
    }

    public static void playDurianCatchingSound(ServerLevel serverLevel, Vec3 vec3,BlockPos blockPos){
        for(ServerPlayer player : serverLevel.getServer().getPlayerList().getPlayers()){
            if(player.level().dimension() != serverLevel.dimension() || player.position().distanceTo(vec3) > 64f) continue;
            ServerPlayNetworking.send(player,new ModLevelEventPacket((byte) 1,blockPos));
        }


    }

    public static int calculateEatingWithAnorexiaEffect(LivingEntity livingEntity, int original){
        float amp = ( Objects.requireNonNull(livingEntity.getEffect(ModMobEffects.APPETITE_LOSS)).getAmplifier() + 1);
        float rate = 1.2f;
        return (int) ((float)original * (rate + (rate * (0.6 * amp))  ) );
    }
}
