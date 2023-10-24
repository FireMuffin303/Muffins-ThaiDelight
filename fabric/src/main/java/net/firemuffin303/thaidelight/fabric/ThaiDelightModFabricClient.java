package net.firemuffin303.thaidelight.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.firemuffin303.thaidelight.client.MobVote2023Client;
import net.firemuffin303.thaidelight.client.registry.ModEntityClient;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

import java.util.function.Supplier;

public class ThaiDelightModFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MobVote2023Client.init();

        ModEntityClient.layerRegistry(new ModEntityClient.LayerDefinitionRegistry() {
            @Override
            public void register(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
                EntityModelLayerRegistry.registerModelLayer(location, definition::get);
            }
        });
    }
}
