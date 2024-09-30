package net.firemuffin303.thaidelight.forge;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.client.ThaiDelightClient;
import net.firemuffin303.thaidelight.client.registry.ModEntityClient;
import net.firemuffin303.thaidelight.client.screens.MortarScreen;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModMenuType;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

import java.util.function.Supplier;

@Mod(value = ThaiDelight.MOD_ID,dist = Dist.CLIENT)
public class ThaiDelightForgeClient {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            ThaiDelightClient.init();

            ItemProperties.register(ModItems.DRAGONFLY_BOTTLE.get(),ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,"variant"),(itemStack, clientLevel, livingEntity, i) -> {
                return ((itemStack.get(DataComponents.BUCKET_ENTITY_DATA).copyTag().getInt("Variant")) * 4.0f) / 16f;
            });
        });
    }

    @SubscribeEvent
    public static void registryLayer(net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions event){
        ModEntityClient.layerRegistry(new ModEntityClient.LayerDefinitionRegistry() {
            @Override
            public void register(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
                event.registerLayerDefinition(location,definition);
            }
        });
    }

    @SubscribeEvent
    public static void registerScreen(RegisterMenuScreensEvent menuScreensEvent){
        menuScreensEvent.register(ModMenuType.MORTAR, MortarScreen::new);
    }



}
