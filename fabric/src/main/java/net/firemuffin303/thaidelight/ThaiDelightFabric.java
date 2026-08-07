package net.firemuffin303.thaidelight;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.logging.LogUtils;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.LandPathNodeTypesRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.firemuffin303.thaidelight.common.TDFabricEvents;
import net.firemuffin303.thaidelight.common.cardinalcomponents.DurianHeatComponent;
import net.firemuffin303.thaidelight.common.cardinalcomponents.SpicyComponent;
import net.firemuffin303.thaidelight.common.entity.DragonflyEntity;
import net.firemuffin303.thaidelight.common.entity.FlowerCrabEntity;
import net.firemuffin303.thaidelight.common.registry.*;
import net.firemuffin303.thaidelight.config.ModConfig;
import net.firemuffin303.thaidelight.integration.toughasnail.ToughAsNailIntegration;
import net.firemuffin303.thaidelight.network.ModLevelEventPacket;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public class ThaiDelightFabric implements ModInitializer {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static boolean IS_FOT_INSTALLED = false;
    public static boolean IS_TOUGH_AS_NAIL_INSTALLED = false;


    @Override
    public void onInitialize() {
        IS_TOUGH_AS_NAIL_INSTALLED = FabricLoader.getInstance().isModLoaded("toughasnails");
        //MidnightConfig.init(ThaiDelightCommon.MOD_ID, ThaiDelightConfig.class);
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        ThaiDelightCommon.init();

        ThaiDelightCommon.postInit();

        ModEntityTypes.registerAttribute(FabricDefaultAttributeRegistry::register);

        SpawnPlacements.register((EntityType<FlowerCrabEntity>) ModEntityTypes.FLOWER_CRAB.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FlowerCrabEntity::checkSpawnRules);
        SpawnPlacements.register((EntityType<DragonflyEntity>)ModEntityTypes.DRAGONFLY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DragonflyEntity::checkSpawnRules);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.BEACH), MobCategory.CREATURE,ModEntityTypes.FLOWER_CRAB.get(),10,3,5);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.MANGROVE_SWAMP, Biomes.SWAMP), MobCategory.CREATURE,ModEntityTypes.DRAGONFLY.get(),2,1,3);

        if(IS_TOUGH_AS_NAIL_INSTALLED){
            ToughAsNailIntegration.toughAsNailIntegration();
        }

        FabricBrewingRecipeRegistryBuilder.BUILD.register(new FabricBrewingRecipeRegistryBuilder.BuildCallback() {
            @Override
            public void build(PotionBrewing.Builder builder) {
                builder.addMix(Potions.AWKWARD,ModItems.FERMENTED_FISH.get(), ModMobEffects.STENCH_POTION);
                builder.addMix(ModMobEffects.STENCH_POTION, Items.REDSTONE,ModMobEffects.LONG_STENCH_POTION);
                builder.addMix(ModMobEffects.STENCH_POTION, Items.GLOWSTONE_DUST,ModMobEffects.STRONG_STENCH_POTION);
            }
        });

        TillableBlockRegistry.register(Blocks.BAMBOO_SAPLING,useOnContext -> true,Blocks.AIR.defaultBlockState(),ModItems.BAMBOO_SHOOT.get());

        TDFabricEvents.worldGeneration();
        TDFabricEvents.registerFuel();
        TDFabricEvents.modifyLootTable();
        TDFabricEvents.initializeStinkyEffect();
        TDFabricEvents.addVillagersTrades();

        CommandRegistrationCallback.EVENT.register(ThaiDelightFabric::modCommand);
        ThaiDelightCommon.BURN_MAP.forEach((block, burnEntry) -> FlammableBlockRegistry.getDefaultInstance().add(block,burnEntry.burn(), burnEntry.spread()));
        LandPathNodeTypesRegistry.register(ModBlocks.LIME_PLANT.get(), PathType.DAMAGE_OTHER,PathType.DANGER_OTHER);


        AttackEntityCallback.EVENT.register(new AttackEntityCallback() {
            @Override
            public InteractionResult interact(Player player, Level world, InteractionHand hand, Entity entity, @Nullable EntityHitResult hitResult) {
                if(player.isSpectator()){
                    return InteractionResult.PASS;
                }

                if(entity instanceof LivingEntity livingEntity){
                    if(livingEntity.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.DURIAN_HELMET.get()) && world.random.nextFloat() < 0.45){
                        float f = Mth.randomBetween(entity.getRandom(), 1, 3);
                        player.hurt(world.damageSources().thorns(entity),f);
                    }
                }

                return InteractionResult.PASS;
            }
        });

        PayloadTypeRegistry.playS2C().register(ModLevelEventPacket.TYPE,ModLevelEventPacket.STREAM_CODEC);

    }

    public static void onEntityHurt(LivingEntity livingEntity,DamageSource damageSource,boolean blocked){
        Entity entity = damageSource.getEntity();
        if(!blocked && livingEntity.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.DURIAN_HELMET.get()) && entity != null && livingEntity.getRandom().nextFloat() < 0.45){
            float f = Mth.randomBetween(entity.getRandom(), 1, 3);
            entity.hurt(livingEntity.level().damageSources().thorns(entity),f);
        }
    }

    public static void modCommand(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection commandSelection){
        commandDispatcher.register(
                Commands.literal("durianHeat")
                        .requires(source -> source.hasPermission(4))
                        .then(Commands.argument("player", EntityArgument.player())
                                .then(Commands.literal("set")
                                        .then(Commands.argument("amount", IntegerArgumentType.integer(1))
                                                .executes(commandContext -> {
                                                    ServerPlayer serverPlayer = EntityArgument.getPlayer(commandContext,"player");
                                                    DurianHeatComponent durianHeatComponent = ModCardinalComponents.DURIAN_HEAT.get(serverPlayer);
                                                    durianHeatComponent.setTimer(IntegerArgumentType.getInteger(commandContext,"amount"));
                                                    commandContext.getSource().sendSuccess(() -> Component.literal("Apply Durian Heat to Player for amount."),false);
                                                    return 1;
                                                })
                                        )
                                )

                                .then(Commands.literal("clear")
                                        .executes(commandContext -> {
                                            ServerPlayer serverPlayer = EntityArgument.getPlayer(commandContext,"player");
                                            DurianHeatComponent durianHeatComponent = ModCardinalComponents.DURIAN_HEAT.get(serverPlayer);
                                            durianHeatComponent.setTimer(0);
                                            commandContext.getSource().sendSuccess(() -> Component.literal("Apply Durian Heat to Player for amount."),false);
                                            return 1;
                                        })
                                )

                                .then(Commands.literal("heat")
                                        .then(Commands.argument("isHeatedUp", BoolArgumentType.bool())
                                                .executes(commandContext -> {
                                                    ServerPlayer serverPlayer = EntityArgument.getPlayer(commandContext,"player");
                                                    DurianHeatComponent durianHeatComponent = ModCardinalComponents.DURIAN_HEAT.get(serverPlayer);
                                                    durianHeatComponent.setHeatedUp(BoolArgumentType.getBool(commandContext,"isHeatedUp"));
                                                    commandContext.getSource().sendSuccess(() -> Component.literal("Apply Durian Heat to Player for amount."),false);
                                                    return 1;
                                                })
                                        )
                                )


                        )
        );

        commandDispatcher.register(
                Commands.literal("spicy")
                        .requires(source -> source.hasPermission(4))
                        .then(Commands.argument("player",EntityArgument.player())
                                .then(Commands.literal("add")
                                        .then(Commands.argument("amount",IntegerArgumentType.integer(0))
                                                .executes(commandContext -> {
                                                    ServerPlayer serverPlayer = EntityArgument.getPlayer(commandContext,"player");
                                                    SpicyComponent spicyComponent = ModCardinalComponents.SPICY_HEAT.get(serverPlayer);
                                                    spicyComponent.addTime(IntegerArgumentType.getInteger(commandContext,"amount"));
                                                    commandContext.getSource().sendSuccess(() -> Component.literal("Apply Spicy to Player for amount."),false);
                                                    return 1;
                                                })
                                        )
                                )

                                .then(Commands.literal("set")
                                        .then(Commands.argument("amount",IntegerArgumentType.integer(0))
                                                .executes(commandContext -> {
                                                    ServerPlayer serverPlayer = EntityArgument.getPlayer(commandContext,"player");
                                                    SpicyComponent spicyComponent = ModCardinalComponents.SPICY_HEAT.get(serverPlayer);
                                                    spicyComponent.setTime(IntegerArgumentType.getInteger(commandContext,"amount"));
                                                    commandContext.getSource().sendSuccess(() -> Component.literal("Apply Spicy to Player for amount."),false);
                                                    return 1;
                                                })
                                        )
                                )



                                .then(Commands.literal("clear")
                                        .executes(commandContext -> {
                                            ServerPlayer serverPlayer = EntityArgument.getPlayer(commandContext,"player");
                                            SpicyComponent spicyComponent = ModCardinalComponents.SPICY_HEAT.get(serverPlayer);
                                            spicyComponent.setTime(0);
                                            commandContext.getSource().sendSuccess(() -> Component.literal("Cleared Spicy from Player."),false);
                                            return 1;
                                        })
                                )
                        )

        );
    }




}
