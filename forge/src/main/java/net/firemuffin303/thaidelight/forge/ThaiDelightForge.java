package net.firemuffin303.thaidelight.forge;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.entity.ai.NearestMobStinkyTargetGoal;
import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModMobEffects;
import net.firemuffin303.thaidelight.common.registry.ModVillagerTrades;
import net.firemuffin303.thaidelight.common.registry.forge.*;
import net.firemuffin303.thaidelight.config.ModConfig;
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
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.DeferredRegister;

import java.util.*;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = ThaiDelightCommon.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
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
            ModRecipesImpl.RECIPE_SERIALIZER
    };



    public ThaiDelightForge(){
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ThaiDelightCommon.init();
        Arrays.stream(REGISTERS).forEach(deferredRegister -> deferredRegister.register(eventBus));

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class,this::attachCapability);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTick);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerJoin);
        MinecraftForge.EVENT_BUS.addListener(this::registerCommand);
        MinecraftForge.EVENT_BUS.addListener(this::registerWanderingTraderOffers);
        MinecraftForge.EVENT_BUS.addListener(this::registerVillagerTrade);
        MinecraftForge.EVENT_BUS.addListener(this::registerGoalSelector);
        MinecraftForge.EVENT_BUS.addListener(this::onEffectAdded);
        eventBus.register(this);

    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
            ThaiDelightCommon.postInit();
            ThaiDelightPacketHandler.registerSpicyPacket();

            PotionBrewingAccessor.addMix(Potions.AWKWARD, ModItems.FERMENTED_FISH.get(), ModMobEffects.STENCH_POTION.get());
            PotionBrewingAccessor.addMix(ModMobEffects.STENCH_POTION.get(), Items.REDSTONE,ModMobEffects.LONG_STENCH_POTION.get());
            PotionBrewingAccessor.addMix(ModMobEffects.STENCH_POTION.get(), Items.GLOWSTONE_DUST,ModMobEffects.STRONG_STENCH_POTION.get());
        });
    }



    @SubscribeEvent
    public static void registerEntityAttribute(EntityAttributeCreationEvent event){
        ModEntityTypes.registerAttribute((entity,attribute) -> {
            event.put(entity,attribute.build());
        });
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event){
        event.register(ISpicy.class);
        event.register(IDurianHeat.class);
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

    public void attachCapability(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof LivingEntity){
            event.addCapability(ThaiDelightCommon.modid("spicy"),new SpicyProvider());
            event.addCapability(ThaiDelightCommon.modid("durian_heat"),new DurianHeatProvider());
        }
    }

    public void onPlayerTick(TickEvent.PlayerTickEvent event){
        Player player = event.player;
        if(event.phase == TickEvent.Phase.END){
            player.getCapability(SpicyProvider.SPICY_CAPABILITY).ifPresent(spicy -> spicy.tick(player));
            player.getCapability(DurianHeatProvider.DURIAN_CAPABILITY).ifPresent(durian -> durian.tick(player));

        }
    }

    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event){
        if(event.getEntity() instanceof  ServerPlayer serverPlayer){
            serverPlayer.getCapability(DurianHeatProvider.DURIAN_CAPABILITY).ifPresent(durian -> {
                ThaiDelightPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer),new DurianHeatPacket(durian.getTimer(),durian.isHeatUp()) );
            });

            serverPlayer.getCapability(SpicyProvider.SPICY_CAPABILITY).ifPresent(spicy -> {
                ThaiDelightPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer),new SpicyPacket(spicy.getTimer()));
            });
        }
    }

    public void registerCommand(RegisterCommandsEvent event){
        modCommand(event.getDispatcher(),event.getBuildContext(),event.getCommandSelection());
    }

    public void onEffectAdded(MobEffectEvent.Added event){
        MobEffectInstance mobEffectInstance = event.getEffectInstance();
        LivingEntity livingEntity = event.getEntity();
        if(mobEffectInstance.getEffect() == MobEffects.FIRE_RESISTANCE && livingEntity.getCapability(SpicyProvider.SPICY_CAPABILITY).orElse(new SpicyProvider()).getTimer() > 200){
            livingEntity.getCapability(SpicyProvider.SPICY_CAPABILITY).ifPresent(spicy -> {
                spicy.setTimer(200,livingEntity);
                if(livingEntity instanceof ServerPlayer serverPlayer){
                    ThaiDelightPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer),new SpicyPacket(spicy.getTimer()) );
                }
            });
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
                                                    int timer = IntegerArgumentType.getInteger(commandContext,"amount");
                                                    serverPlayer.getCapability(DurianHeatProvider.DURIAN_CAPABILITY).ifPresent(durian -> {
                                                        durian.setTimer(timer);
                                                        ThaiDelightPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer),new DurianHeatPacket(durian.getTimer(),durian.isHeatUp()));

                                                    });
                                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.muffins_thaidelight.durian_heat.set_timer", serverPlayer.getDisplayName(),timer/20),false);
                                                    return 1;
                                                })
                                        )
                                )

                                .then(Commands.literal("clear")
                                        .executes(commandContext -> {
                                            ServerPlayer serverPlayer = EntityArgument.getPlayer(commandContext,"player");
                                            serverPlayer.getCapability(DurianHeatProvider.DURIAN_CAPABILITY).ifPresent(durian -> {
                                                durian.setTimer(0);
                                                ThaiDelightPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer),new DurianHeatPacket(durian.getTimer(),durian.isHeatUp()));

                                            });
                                            commandContext.getSource().sendSuccess(() -> Component.translatable("commands.muffins_thaidelight.durian_heat.clear",serverPlayer.getDisplayName()),false);
                                            return 1;
                                        })
                                )

                                .then(Commands.literal("heat")
                                        .then(Commands.argument("isHeatedUp", BoolArgumentType.bool())
                                                .executes(commandContext -> {
                                                    ServerPlayer serverPlayer = EntityArgument.getPlayer(commandContext,"player");
                                                    serverPlayer.getCapability(DurianHeatProvider.DURIAN_CAPABILITY).ifPresent(durian -> {
                                                        durian.setHeat(BoolArgumentType.getBool(commandContext,"isHeatedUp"));
                                                        ThaiDelightPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer),new DurianHeatPacket(durian.getTimer(),durian.isHeatUp()));

                                                    });
                                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.muffins_thaidelight.durian_heat.set_heat",serverPlayer.getDisplayName()),false);
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
                                                    int timer = IntegerArgumentType.getInteger(commandContext,"amount");
                                                    serverPlayer.getCapability(SpicyProvider.SPICY_CAPABILITY).ifPresent(spicy -> {
                                                        spicy.addTimer(timer,serverPlayer);
                                                    });

                                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.muffins_thaidelight.spicy.set_timer",serverPlayer.getDisplayName(),timer/20),false);
                                                    return 1;
                                                })
                                        )
                                )

                                .then(Commands.literal("set")
                                        .then(Commands.argument("amount",IntegerArgumentType.integer(0))
                                                .executes(commandContext -> {
                                                    ServerPlayer serverPlayer = EntityArgument.getPlayer(commandContext,"player");
                                                    int timer = IntegerArgumentType.getInteger(commandContext,"amount");
                                                    serverPlayer.getCapability(SpicyProvider.SPICY_CAPABILITY).ifPresent(spicy -> {
                                                        spicy.setTimer(timer,serverPlayer);
                                                    });

                                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.muffins_thaidelight.spicy.set_timer",serverPlayer.getDisplayName(),timer/20),false);
                                                    return 1;
                                                })
                                        )
                                )



                                .then(Commands.literal("clear")
                                        .executes(commandContext -> {
                                            ServerPlayer serverPlayer = EntityArgument.getPlayer(commandContext,"player");
                                            serverPlayer.getCapability(SpicyProvider.SPICY_CAPABILITY).ifPresent(spicy -> {
                                                spicy.setTimer(0,serverPlayer);
                                            });
                                            commandContext.getSource().sendSuccess(() -> Component.translatable("commands.muffins_thaidelight.spicy.clear",serverPlayer.getDisplayName()),false);
                                            return 1;
                                        })
                                )
                        )

        ).createBuilder();
    }

}
