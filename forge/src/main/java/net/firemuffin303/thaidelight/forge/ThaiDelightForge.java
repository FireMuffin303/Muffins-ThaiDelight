package net.firemuffin303.thaidelight.forge;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.entity.ai.NearestMobStinkyTargetGoal;
import net.firemuffin303.thaidelight.common.registry.*;
import net.firemuffin303.thaidelight.common.registry.forge.*;
import net.firemuffin303.thaidelight.config.ModConfig;
import net.firemuffin303.thaidelight.forge.common.attachment.DurianHeatAttachment;
import net.firemuffin303.thaidelight.forge.common.attachment.ModAttachments;
import net.firemuffin303.thaidelight.forge.common.capabilities.DurianHeatProvider;
import net.firemuffin303.thaidelight.forge.common.capabilities.IDurianHeat;
import net.firemuffin303.thaidelight.forge.common.capabilities.ISpicy;
import net.firemuffin303.thaidelight.forge.common.capabilities.SpicyProvider;
import net.firemuffin303.thaidelight.forge.mixin.accessor.PotionBrewingAccessor;
import net.firemuffin303.thaidelight.forge.network.DurianHeatPacket;
import net.firemuffin303.thaidelight.forge.network.SpicyPacket;
import net.firemuffin303.thaidelight.forge.network.ThaiDelightPacketHandler;
import net.firemuffin303.thaidelight.mixin.accessor.MobAccessor;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

import java.util.*;
import java.util.stream.Collectors;

@Mod(ThaiDelightCommon.MOD_ID)
public class ThaiDelightForge {
    private static final DeferredRegister<?>[] REGISTERS = {
            ModBoatVariantsImpl.BOAT_VARIANT,
            ModEntityTypesImpl.ENTITY_TYPES,
            ModMenuTypeImpl.MENU,
            ModBlockEntityTypesImpl.BLOCK_ENTITY,
            ModBlocksImpl.BLOCK,
            ModItemsImpl.CREATIVE_TAB,
            ModItemsImpl.ITEMS,
            ModRecipesImpl.RECIPE_TYPE,
            ModRecipesImpl.RECIPE_SERIALIZER,
            ModAttachments.ATTACHMENT_TYPES
    };



    public ThaiDelightForge(IEventBus eventBus){
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        ThaiDelightCommon.init();
        Arrays.stream(REGISTERS).forEach(deferredRegister -> deferredRegister.register(eventBus));

        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.addListener(this::onPlayerTick);
        NeoForge.EVENT_BUS.addListener(this::onPlayerJoin);
        NeoForge.EVENT_BUS.addListener(this::registerCommand);
        NeoForge.EVENT_BUS.addListener(this::registerWanderingTraderOffers);
        NeoForge.EVENT_BUS.addListener(this::registerVillagerTrade);
        NeoForge.EVENT_BUS.addListener(this::registerGoalSelector);
        NeoForge.EVENT_BUS.addListener(this::registerBrewingRecipeEvent);

        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::registerEntityAttribute);
        eventBus.addListener(this::registerPayloadEvent);
        eventBus.addListener(this::addBlockEntityType);

    }

    public void commonSetup(FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
            ThaiDelightCommon.postInit();
            ThaiDelightPacketHandler.registerSpicyPacket();
        });
    }

    public void registerBrewingRecipeEvent(RegisterBrewingRecipesEvent event){
        event.getBuilder().addMix(Potions.AWKWARD, ModItems.FERMENTED_FISH.get(), ModMobEffects.STENCH_POTION);
        event.getBuilder().addMix(ModMobEffects.STENCH_POTION, Items.REDSTONE,ModMobEffects.LONG_STENCH_POTION);
        event.getBuilder().addMix(ModMobEffects.STENCH_POTION, Items.GLOWSTONE_DUST,ModMobEffects.STRONG_STENCH_POTION);
    }


    public void addBlockEntityType(BlockEntityTypeAddBlocksEvent event){
        ModBlocks.CABINET.forEach(blockSupplier -> {
            event.modify(ModBlockEntityTypes.CABINET.get(),blockSupplier.get());
        });
    }

    public void registerEntityAttribute(EntityAttributeCreationEvent event){
        ModEntityTypes.registerAttribute((entity,attribute) -> {
            event.put(entity,attribute.build());
        });
    }

    public void registerGoalSelector(EntityJoinLevelEvent event){
        Map<EntityType<?>,Integer> map = Map.of(
                EntityType.BEE,2,
                EntityType.ENDERMAN,3,
                EntityType.POLAR_BEAR,3,
                EntityType.WOLF,4,
                EntityType.ZOMBIFIED_PIGLIN,2
        );

        Entity entity = event.getEntity();
        if(entity instanceof Mob mob){
            GoalSelector goalSelector = ((MobAccessor)mob).getTargetSelector();
            if(!goalSelector.getAvailableGoals().isEmpty()){
                if(entity instanceof NeutralMob && !(mob instanceof AbstractGolem)){
                    goalSelector.addGoal(map.getOrDefault(entity.getType(), 3), new NearestMobStinkyTargetGoal<>(mob, LivingEntity.class, true));
                } else if(mob instanceof Panda || mob instanceof Llama || mob instanceof Dolphin){
                    goalSelector.addGoal(2, new NearestMobStinkyTargetGoal<>(mob, LivingEntity.class, true));
                }else if(mob instanceof Monster  && !(mob instanceof Creeper)){
                    goalSelector.addGoal(1, new NearestMobStinkyTargetGoal<>(mob, LivingEntity.class, true));

                }

            }
        }

    }

    public void registerVillagerTrade(VillagerTradesEvent event){
        if(ModConfig.villagerShouldTradeTDItem){
            ModVillagerTrades.trades().stream().forEach(modVillagerTrade -> {
                if(event.getType() == modVillagerTrade.villagerProfession()){
                    List<VillagerTrades.ItemListing> list = event.getTrades().get(modVillagerTrade.level());
                    MerchantOffer merchantOffer = modVillagerTrade.merchantOffer();
                    list.add(new BasicItemListing(merchantOffer.getCostA(),merchantOffer.getCostB(),merchantOffer.getResult(),merchantOffer.getMaxUses(),merchantOffer.getXp(),merchantOffer.getPriceMultiplier()));
                }
            });
        }

    }

    public void registerWanderingTraderOffers(WandererTradesEvent event){
        Set<VillagerTrades.ItemListing> set = ModVillagerTrades.wanderTrade().stream().map(merchantOffer -> new BasicItemListing(merchantOffer.getCostA(), merchantOffer.getCostB(),
                merchantOffer.getResult(),
                merchantOffer.getMaxUses(),
                merchantOffer.getXp(),
                merchantOffer.getPriceMultiplier()
                )).collect(Collectors.toSet());

        if(ModConfig.wanderingTraderShouldTradeTDItem){
            event.getGenericTrades().addAll(set);
        }
    }

    public void onPlayerTick(PlayerTickEvent.Post event){
        Player player = event.getEntity();
        player.getCapability(SpicyProvider.SPICY_CAPABILITY).ifPresent(spicy -> spicy.tick(player));
        player.getData(ModAttachments.DURIAN_HEAT).tick(player);
    }

    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event){
        if(event.getEntity() instanceof  ServerPlayer serverPlayer){
            DurianHeatAttachment durianHeatAttachment = serverPlayer.getData(ModAttachments.DURIAN_HEAT);
            PacketDistributor.sendToPlayer(serverPlayer,new DurianHeatPacket(durianHeatAttachment.getTimer(),durianHeatAttachment.isHeatUp()));
            PacketDistributor.sendToPlayer(serverPlayer,new SpicyPacket(serverPlayer.getData(ModAttachments.SPICY)));
        }
    }

    public void registerPayloadEvent(RegisterPayloadHandlersEvent event){
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(DurianHeatPacket.TYPE, DurianHeatPacket.STREAM_CODEC, new IPayloadHandler<DurianHeatPacket>() {
            @Override
            public void handle(DurianHeatPacket arg, IPayloadContext iPayloadContext) {

            }
        });

        registrar.playToClient(SpicyPacket.TYPE, SpicyPacket.STREAM_CODEC, new IPayloadHandler<SpicyPacket>() {
            @Override
            public void handle(SpicyPacket arg, IPayloadContext iPayloadContext) {

            }
        });
    }

    public void registerCommand(RegisterCommandsEvent event){
        modCommand(event.getDispatcher(),event.getBuildContext(),event.getCommandSelection());
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
                                                    DurianHeatAttachment durianHeatAttachment = serverPlayer.getData(ModAttachments.DURIAN_HEAT);
                                                    durianHeatAttachment.setTimer(IntegerArgumentType.getInteger(commandContext,"amount"));
                                                    PacketDistributor.sendToPlayer(serverPlayer,new DurianHeatPacket(durianHeatAttachment.getTimer(),durianHeatAttachment.isHeatUp()));
                                                    commandContext.getSource().sendSuccess(() -> Component.literal("Apply Durian Heat to Player for amount."),false);
                                                    return 1;
                                                })
                                        )
                                )

                                .then(Commands.literal("clear")
                                        .executes(commandContext -> {
                                            ServerPlayer serverPlayer = EntityArgument.getPlayer(commandContext,"player");
                                            DurianHeatAttachment durianHeatAttachment = serverPlayer.getData(ModAttachments.DURIAN_HEAT);
                                            durianHeatAttachment.setTimer(0);
                                            PacketDistributor.sendToPlayer(serverPlayer,new DurianHeatPacket(durianHeatAttachment.getTimer(),durianHeatAttachment.isHeatUp()));
                                            commandContext.getSource().sendSuccess(() -> Component.literal("Apply Durian Heat to Player for amount."),false);
                                            return 1;
                                        })
                                )

                                .then(Commands.literal("heat")
                                        .then(Commands.argument("isHeatedUp", BoolArgumentType.bool())
                                                .executes(commandContext -> {
                                                    ServerPlayer serverPlayer = EntityArgument.getPlayer(commandContext,"player");
                                                    DurianHeatAttachment durianHeatAttachment = serverPlayer.getData(ModAttachments.DURIAN_HEAT);
                                                    durianHeatAttachment.setHeat(BoolArgumentType.getBool(commandContext,"isHeatedUp"));
                                                    PacketDistributor.sendToPlayer(serverPlayer,new DurianHeatPacket(durianHeatAttachment.getTimer(),durianHeatAttachment.isHeatUp()));
                                                    commandContext.getSource().sendSuccess(() -> Component.literal("Apply Durian Heat to Player for amount."),false);
                                                    return 1;
                                                })
                                        )
                                )


                        )
        ).createBuilder();

        commandDispatcher.register(
                Commands.literal("spicy")
                        .requires(source -> source.hasPermission(4))
                        .then(Commands.argument("player",EntityArgument.player())
                                .then(Commands.literal("add")
                                        .then(Commands.argument("amount",IntegerArgumentType.integer(0))
                                                .executes(commandContext -> {
                                                    ServerPlayer serverPlayer = EntityArgument.getPlayer(commandContext,"player");
                                                    serverPlayer.setData(ModAttachments.SPICY,serverPlayer.getData(ModAttachments.SPICY) + IntegerArgumentType.getInteger(commandContext,"amount"));
                                                    commandContext.getSource().sendSuccess(() -> Component.literal("Apply Spicy to Player for amount."),false);
                                                    return 1;
                                                })
                                        )
                                )

                                .then(Commands.literal("set")
                                        .then(Commands.argument("amount",IntegerArgumentType.integer(0))
                                                .executes(commandContext -> {
                                                    ServerPlayer serverPlayer = EntityArgument.getPlayer(commandContext,"player");
                                                    serverPlayer.setData(ModAttachments.SPICY,IntegerArgumentType.getInteger(commandContext,"amount"));
                                                    commandContext.getSource().sendSuccess(() -> Component.literal("Apply Spicy to Player for amount."),false);
                                                    return 1;
                                                })
                                        )
                                )



                                .then(Commands.literal("clear")
                                        .executes(commandContext -> {
                                            ServerPlayer serverPlayer = EntityArgument.getPlayer(commandContext,"player");
                                            serverPlayer.setData(ModAttachments.SPICY,0);
                                            commandContext.getSource().sendSuccess(() -> Component.literal("Cleared Spicy from Player."),false);
                                            return 1;
                                        })
                                )
                        )

        ).createBuilder();
    }

}
