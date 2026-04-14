package net.firemuffin303.thaidelight;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.logging.LogUtils;
import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.firemuffin303.thaidelight.common.TDFabricEvents;
import net.firemuffin303.thaidelight.common.cardinalcomponents.DurianHeatComponent;
import net.firemuffin303.thaidelight.common.cardinalcomponents.SpicyComponent;
import net.firemuffin303.thaidelight.common.entity.DragonflyEntity;
import net.firemuffin303.thaidelight.common.entity.FlowerCrabEntity;
import net.firemuffin303.thaidelight.common.registry.*;
import net.firemuffin303.thaidelight.common.registry.fabric.ModItemsImpl;
import net.firemuffin303.thaidelight.integration.midnightLib.ThaiDelightConfig;
import net.firemuffin303.thaidelight.integration.modmenu.ThaiDelightModMenu;
import net.firemuffin303.thaidelight.integration.toughasnail.ToughAsNailIntegration;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import org.slf4j.Logger;

public class ThaiDelightFabric implements ModInitializer {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static boolean IS_FOT_INSTALLED = false;
    public static boolean IS_TOUGH_AS_NAIL_INSTALLED = false;



    public static final TerraformBoatType DURIAN = new TerraformBoatType.Builder()
            .item(ModItems.DURIAN_BOAT.get())
            .chestItem(ModItems.DURIAN_CHEST_BOAT.get())
            .planks(ModItems.DURIAN_PLANKS.get())
            .build();

    private static final TerraformBoatType COCONUT = new TerraformBoatType.Builder()
            .item(ModItems.COCONUT_BOAT.get())
            .chestItem(ModItems.COCONUT_CHEST_BOAT.get())
            .planks(ModItems.COCONUT_PLANKS.get())
            .build();

    private static final TerraformBoatType MANGO = new TerraformBoatType.Builder()
            .item(ModItems.MANGO_BOAT.get())
            .chestItem(ModItems.MANGO_CHEST_BOAT.get())
            .planks(ModItems.MANGO_PLANKS.get())
            .build();

    @Override
    public void onInitialize() {
        IS_TOUGH_AS_NAIL_INSTALLED = FabricLoader.getInstance().isModLoaded("toughasnails");
        MidnightConfig.init(ThaiDelightCommon.MOD_ID, ThaiDelightConfig.class);
        ThaiDelightCommon.init();
        ThaiDelightCommon.postInit();

        ModEntityTypes.registerAttribute(FabricDefaultAttributeRegistry::register);

        Registry.register(TerraformBoatTypeRegistry.INSTANCE, ModItemsImpl.DURIAN_BOAT_KEY,DURIAN);
        Registry.register(TerraformBoatTypeRegistry.INSTANCE,ModItemsImpl.COCONUT_BOAT_KEY,COCONUT);
        Registry.register(TerraformBoatTypeRegistry.INSTANCE,ModItemsImpl.MANGO_BOAT_KEY,MANGO);

        SpawnPlacements.register(ModEntityTypes.FLOWER_CRAB.get(),SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FlowerCrabEntity::checkSpawnRules);
        SpawnPlacements.register(ModEntityTypes.DRAGONFLY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DragonflyEntity::checkSpawnRules);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.BEACH), MobCategory.CREATURE,ModEntityTypes.FLOWER_CRAB.get(),10,3,5);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.MANGROVE_SWAMP, Biomes.SWAMP), MobCategory.CREATURE,ModEntityTypes.DRAGONFLY.get(),2,1,3);

        if(IS_TOUGH_AS_NAIL_INSTALLED){
            ToughAsNailIntegration.toughAsNailIntegration();
        }

        TerraformBoatItemHelper.registerBoatDispenserBehavior(ModItems.DURIAN_BOAT.get(),ModItemsImpl.DURIAN_BOAT_KEY,false);
        TerraformBoatItemHelper.registerBoatDispenserBehavior(ModItems.DURIAN_CHEST_BOAT.get(),ModItemsImpl.DURIAN_BOAT_KEY,true);
        TerraformBoatItemHelper.registerBoatDispenserBehavior(ModItems.MANGO_BOAT.get(),ModItemsImpl.MANGO_BOAT_KEY,false);
        TerraformBoatItemHelper.registerBoatDispenserBehavior(ModItems.MANGO_CHEST_BOAT.get(),ModItemsImpl.MANGO_BOAT_KEY,true);
        TerraformBoatItemHelper.registerBoatDispenserBehavior(ModItems.COCONUT_BOAT.get(),ModItemsImpl.COCONUT_BOAT_KEY,false);
        TerraformBoatItemHelper.registerBoatDispenserBehavior(ModItems.COCONUT_CHEST_BOAT.get(),ModItemsImpl.COCONUT_BOAT_KEY,true);

        PotionBrewing.addMix(Potions.AWKWARD,ModItems.FERMENTED_FISH.get(), ModMobEffects.STENCH_POTION.get());
        PotionBrewing.addMix(ModMobEffects.STENCH_POTION.get(), Items.REDSTONE,ModMobEffects.LONG_STENCH_POTION.get());
        PotionBrewing.addMix(ModMobEffects.STENCH_POTION.get(), Items.GLOWSTONE_DUST,ModMobEffects.STRONG_STENCH_POTION.get());

        TillableBlockRegistry.register(Blocks.BAMBOO_SAPLING,useOnContext -> true,Blocks.AIR.defaultBlockState(),ModItems.BAMBOO_SHOOT.get());

        TDFabricEvents.worldGeneration();
        TDFabricEvents.registerFuel();
        TDFabricEvents.modifyLootTable();
        TDFabricEvents.initializeStinkyEffect();
        TDFabricEvents.setVillagerItem();
        TDFabricEvents.addVillagersTrades();
        TDFabricEvents.registerStrippable();
        TDFabricEvents.registerComposter();
        TDFabricEvents.registerAnimalFood();


        CommandRegistrationCallback.EVENT.register(ThaiDelightFabric::modCommand);

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
