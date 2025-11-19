package net.firemuffin303.muffinsthaidelightfabric.client;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.client.model.armor.DurianHelmetModel;
import net.firemuffin303.muffinsthaidelightfabric.client.model.entity.BabyBuffoloModel;
import net.firemuffin303.muffinsthaidelightfabric.client.model.entity.BuffoloModel;
import net.firemuffin303.muffinsthaidelightfabric.client.model.entity.DragonflyModel;
import net.firemuffin303.muffinsthaidelightfabric.client.model.entity.FlowerCrabModel;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.CrabRenderer;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.DragonflyRenderer;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModEntityTypes;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public class ClientModelRegistry {
    public static ModelLayerLocation DURIAN_HELMET = new ModelLayerLocation(ThaiDelight.modid("durian_armor"),"main");

    public static void entityInit(){
        EntityRendererRegistry.register(ModEntityTypes.FLOWER_CRAB, CrabRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.DRAGONFLY, DragonflyRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(FlowerCrabModel.LAYER,FlowerCrabModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(DragonflyModel.LAYER,DragonflyModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(BuffoloModel.LAYER_LOCATION,BuffoloModel::createAdultBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(BabyBuffoloModel.BABY_LAYER_LOCATION,BabyBuffoloModel::createBabyBodyLayer);

        EntityModelLayerRegistry.registerModelLayer(DURIAN_HELMET, DurianHelmetModel::createLayer);
    }
}
