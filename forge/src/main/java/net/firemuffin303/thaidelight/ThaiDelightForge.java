package net.firemuffin303.thaidelight;

import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.firemuffin303.thaidelight.common.registry.forge.*;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

import java.util.Arrays;

@Mod(ThaiDelightCommon.MOD_ID)
public class ThaiDelightForge {
    private static final DeferredRegister<?>[] REGISTERS = {
            ModSoundEventsImpl.SOUND_EVENT,
            ModBlockStateProviderTypesImpl.BLOCK_STATE_PROVIDER_TYPE,
            ModTreeDecoratorTypesImpl.TREE_DECORATOR_TYPE,
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
    public static void registerEntityAttribute(EntityAttributeCreationEvent event){
        ModEntityTypes.registerAttribute((entity,attribute) -> event.put(entity,attribute.build()));
    }
    

}
