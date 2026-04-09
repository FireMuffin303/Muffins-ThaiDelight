package net.firemuffin303.thaidelight.client;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.client.sceens.MortarScreen;
import net.firemuffin303.thaidelight.common.registry.ModMenuType;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterRecipeBookCategoriesEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ThaiDelightCommon.MOD_ID,value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ThaiDelightForgeClient {

    public ThaiDelightForgeClient(){
        MenuScreens.register(ModMenuType.MORTAR.get(), MortarScreen::new);
    }

    @SubscribeEvent
    public static void registerEntityModelLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        ThaiDelightCommonClient.entityModelRegister(event::registerLayerDefinition);
    }

    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event){
        ThaiDelightCommonClient.entityRendererRegister(event::registerEntityRenderer);
        ThaiDelightCommonClient.blockEntityRenderRegister(event::registerBlockEntityRenderer);
    }

    @SubscribeEvent
    public static void registerEntityAddLayer(EntityRenderersEvent.AddLayers event){

    }



    @SubscribeEvent
    public static void registerRecipeBook(RegisterRecipeBookCategoriesEvent event){

    }
}
