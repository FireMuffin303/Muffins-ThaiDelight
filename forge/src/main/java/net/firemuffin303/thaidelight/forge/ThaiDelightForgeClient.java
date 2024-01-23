package net.firemuffin303.thaidelight.forge;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.client.registry.ModEntityClient;
import net.firemuffin303.thaidelight.client.registry.ModScreensClient;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = ThaiDelight.MOD_ID,value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ThaiDelightForgeClient {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            ModEntityClient.init();
            ModScreensClient.init();
        });
    }

    @SubscribeEvent
    public static void registryLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        ModEntityClient.layerRegistry(new ModEntityClient.LayerDefinitionRegistry() {
            @Override
            public void register(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
                event.registerLayerDefinition(location,definition);
            }
        });
    }



}
