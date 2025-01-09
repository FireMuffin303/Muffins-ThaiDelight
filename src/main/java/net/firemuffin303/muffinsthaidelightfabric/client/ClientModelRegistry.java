package net.firemuffin303.muffinsthaidelightfabric.client;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.firemuffin303.muffinsthaidelightfabric.client.model.BabyBuffoloModel;
import net.firemuffin303.muffinsthaidelightfabric.client.model.BuffoloModel;
import net.firemuffin303.muffinsthaidelightfabric.client.model.DragonflyModel;
import net.firemuffin303.muffinsthaidelightfabric.client.model.FlowerCrabModel;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.BuffaloRenderer;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.CrabRenderer;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.DragonflyRenderer;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModEntityTypes;

public class ClientModelRegistry {
    public static void entityInit(){
        EntityRendererRegistry.register(ModEntityTypes.FLOWER_CRAB, CrabRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.DRAGONFLY, DragonflyRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.BUFFALO, BuffaloRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(FlowerCrabModel.LAYER,FlowerCrabModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(DragonflyModel.LAYER,DragonflyModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(BuffoloModel.LAYER_LOCATION,BuffoloModel::createAdultBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(BabyBuffoloModel.BABY_LAYER_LOCATION,BabyBuffoloModel::createBabyBodyLayer);
    }
}
