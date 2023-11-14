package net.firemuffin303.thaidelight.client.registry;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.client.model.DragonflyModel;
import net.firemuffin303.thaidelight.client.model.FlowerCrabModel;
import net.firemuffin303.thaidelight.client.renderer.CrabRenderer;
import net.firemuffin303.thaidelight.client.renderer.DragonflyRenderer;
import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class ModEntityClient {
    public static void init(){
        ModPlatform.registerEntityRenderer(ModEntityTypes.FLOWER_CRAB, CrabRenderer::new);
        ModPlatform.registerEntityRenderer(ModEntityTypes.DRAGONFLY, DragonflyRenderer::new);

    }

    public static void layerRegistry(LayerDefinitionRegistry registry){
        registry.register(FlowerCrabModel.LAYER, FlowerCrabModel::createBodyLayer);
        registry.register(DragonflyModel.LAYER,DragonflyModel::createBodyLayer);
    }

    public static abstract class LayerDefinitionRegistry {
        public abstract void register(ModelLayerLocation location, Supplier<LayerDefinition> definition);
    }
}
