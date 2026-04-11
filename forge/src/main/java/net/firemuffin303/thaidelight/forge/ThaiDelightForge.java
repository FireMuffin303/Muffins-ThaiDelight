package net.firemuffin303.thaidelight.forge;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModTreeDecoratorTypes;
import net.firemuffin303.thaidelight.common.registry.forge.*;
import net.firemuffin303.thaidelight.forge.common.capabilities.ISpicy;
import net.firemuffin303.thaidelight.forge.common.capabilities.SpicyProvider;
import net.firemuffin303.thaidelight.forge.network.SpicyPacket;
import net.firemuffin303.thaidelight.forge.network.ThaiDelightPacketHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.DeferredRegister;

import java.util.Arrays;

@Mod(ThaiDelightCommon.MOD_ID)
public class ThaiDelightForge {
    private static final DeferredRegister<?>[] REGISTERS = {
            ModSoundEventsImpl.SOUND_EVENT,
            ModBlockStateProviderTypesImpl.BLOCK_STATE_PROVIDER_TYPE,
            ModTreeDecoratorTypesImpl.TREE_DECORATOR_TYPE,
            ModFeaturesImpl.FOLIAGE_PLACER,
            ModFeaturesImpl.TRUNK_PLACER,
            ModEntityTypesImpl.ENTITY_TYPES,
            ModMobEffectsImpl.MOB_EFFECT,
            ModMobEffectsImpl.POTION,
            ModMenuTypeImpl.MENU,
            ModBlockEntityTypesImpl.BLOCK_ENTITY,
            ModBlocksImpl.BLOCK,
            ModItemsImpl.CREATIVE_TAB,
            ModItemsImpl.ITEMS,
            ModRecipesImpl.RECIPE_TYPE,
            ModRecipesImpl.RECIPE_SERIALIZER
    };



    public ThaiDelightForge(){
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ThaiDelightCommon.init();
        Arrays.stream(REGISTERS).forEach(deferredRegister -> deferredRegister.register(eventBus));

        eventBus.register(this);
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event){
        event.enqueueWork(ThaiDelightCommon::postInit);

    }


    @SubscribeEvent
    public static void registerEntityAttribute(EntityAttributeCreationEvent event){
        ModEntityTypes.registerAttribute((entity,attribute) -> event.put(entity,attribute.build()));
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event){
        event.register(ISpicy.class);
    }

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof LivingEntity){
            event.addCapability(ThaiDelightCommon.modid("spicy"),new SpicyProvider());
        }
    }

}
