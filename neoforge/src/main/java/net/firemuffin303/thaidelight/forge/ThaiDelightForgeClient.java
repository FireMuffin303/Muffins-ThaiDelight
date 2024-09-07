package net.firemuffin303.thaidelight.forge;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.client.ThaiDelightClient;
import net.firemuffin303.thaidelight.client.registry.ModEntityClient;
import net.firemuffin303.thaidelight.client.registry.ModScreensClient;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.model.ForgeItemModelShaper;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = ThaiDelight.MOD_ID,value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ThaiDelightForgeClient {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            ThaiDelightClient.init();

            ItemProperties.register(ModItems.DRAGONFLY_BOTTLE,new ResourceLocation(ThaiDelight.MOD_ID,"variant"),(itemStack, clientLevel, livingEntity, i) -> {
                return ((itemStack.getOrCreateTag().getInt("Variant")) * 4.0f) / 16f;
            });
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
