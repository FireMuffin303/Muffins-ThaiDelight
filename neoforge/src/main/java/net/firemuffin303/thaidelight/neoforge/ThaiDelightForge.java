package net.firemuffin303.thaidelight.neoforge;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.logging.LogUtils;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.firemuffin303.muffinsmcapi.MuffinsMcAPI;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.entity.ai.NearestMobStinkyTargetGoal;
import net.firemuffin303.thaidelight.common.registry.*;
import net.firemuffin303.thaidelight.common.registry.forge.*;
import net.firemuffin303.thaidelight.config.ModConfig;
import net.firemuffin303.thaidelight.neoforge.common.attachment.DurianHeatAttachment;
import net.firemuffin303.thaidelight.neoforge.common.attachment.ModAttachments;
import net.firemuffin303.thaidelight.neoforge.network.DurianHeatPacket;
import net.firemuffin303.thaidelight.neoforge.network.SpicyPacket;
import net.firemuffin303.thaidelight.mixin.accessor.MobAccessor;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.common.EffectCures;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

import java.util.*;
import java.util.stream.Collectors;

@Mod(ThaiDelightCommon.MOD_ID)
public class ThaiDelightForge {
    private static final DeferredRegister<?>[] REGISTERS = {
            ModBoatVariantsImpl.BOAT_VARIANT,
            ModEntityTypesImpl.ENTITY_TYPES,
            ModBlockEntityTypesImpl.BLOCK_ENTITY,
            ModBlocksImpl.BLOCK,
            ModItemsImpl.CREATIVE_TAB,
            ModItemsImpl.ITEMS,
            ModAttachments.ATTACHMENT_TYPES
    };



    public ThaiDelightForge(IEventBus eventBus, ModContainer modContainer){
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        ThaiDelightCommon.init();
        Arrays.stream(REGISTERS).forEach(deferredRegister -> deferredRegister.register(eventBus));

        NeoForge.EVENT_BUS.addListener(this::onPlayerTick);
        NeoForge.EVENT_BUS.addListener(this::onPlayerJoin);
        NeoForge.EVENT_BUS.addListener(this::registerCommand);
        NeoForge.EVENT_BUS.addListener(this::registerWanderingTraderOffers);
        NeoForge.EVENT_BUS.addListener(this::registerVillagerTrade);
        NeoForge.EVENT_BUS.addListener(this::registerGoalSelector);
        NeoForge.EVENT_BUS.addListener(this::registerBrewingRecipeEvent);
        NeoForge.EVENT_BUS.addListener(this::registerEffectRemoved);
        NeoForge.EVENT_BUS.addListener(this::registerRightClickBlockEvent);

        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::registerEntityAttribute);
        eventBus.addListener(this::addBlockEntityType);

        LogUtils.getLogger().info("does we have oven_boat_type yet?{}", BuiltInRegistries.REGISTRY.containsKey(MuffinsMcAPI.modid("oven_boat_type")));

    }

    public void commonSetup(FMLCommonSetupEvent event){
        event.enqueueWork(ThaiDelightCommon::postInit);
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
        //player.getCapability(SpicyProvider.SPICY_CAPABILITY).ifPresent(spicy -> spicy.tick(player));
        player.getData(ModAttachments.DURIAN_HEAT).tick(player);
    }

    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event){
        if(event.getEntity() instanceof  ServerPlayer serverPlayer){
            DurianHeatAttachment durianHeatAttachment = serverPlayer.getData(ModAttachments.DURIAN_HEAT);
            PacketDistributor.sendToPlayer(serverPlayer,new DurianHeatPacket(durianHeatAttachment.getTimer(),durianHeatAttachment.isHeatUp()));
            PacketDistributor.sendToPlayer(serverPlayer,new SpicyPacket(serverPlayer.getData(ModAttachments.SPICY)));
        }
    }

    public void registerRightClickBlockEvent(PlayerInteractEvent.RightClickBlock event){
        ItemStack toolStack = event.getEntity().getItemInHand(event.getHand());
        if(toolStack.is(ItemTags.HOES)){
            Level level = event.getLevel();
            BlockPos pos = event.getPos();
            BlockState state = event.getLevel().getBlockState(pos);

            if( level.getBlockState(pos.above()).isAir() && state.is(Blocks.BAMBOO_SAPLING)){
                Block.popResource(level,pos,new ItemStack(ModItems.BAMBOO_SHOOT.get()));
                level.setBlock(pos,Blocks.AIR.defaultBlockState(),3);
            }
        }
    }

    public void registerEffectRemoved(MobEffectEvent.Remove event){
        if(event.getCure() == EffectCures.MILK){
            if(event.getEntity() instanceof  Player player){
                PlatformUtil.setSpicyTime(0,player);
            }
        }
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
