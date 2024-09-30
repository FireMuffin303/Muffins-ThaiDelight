package net.firemuffin303.thaidelight.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.fabric.mixin.object.builder.client.ModelPredicateProviderRegistryAccessor;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.client.ThaiDelightClient;
import net.firemuffin303.thaidelight.client.registry.ModEntityClient;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class ThaiDelightModFabricClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        ThaiDelightClient.init();

        ModEntityClient.layerRegistry(new ModEntityClient.LayerDefinitionRegistry() {
            @Override
            public void register(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
                EntityModelLayerRegistry.registerModelLayer(location, definition::get);
            }
        });

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                ModBlocks.SOMTAM_FEAST.get(),
                ModBlocks.LIME_SAPLING.get(),
                ModBlocks.LIME_CROP.get(),
                ModBlocks.WILD_PEPPER_CROP.get(),
                ModBlocks.PEPPER_CROP.get(),
                ModBlocks.PAPAYA.get(),
                ModBlocks.PAPAYA_SAPLING.get(),
                ModBlocks.CRAB_EGG.get(),
                ModBlocks.PAPAYA_CROPS.get());

        ItemProperties.register(ModItems.DRAGONFLY_BOTTLE.get(),ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,"variant"),(itemStack, clientLevel, livingEntity, i) -> {
            return ((itemStack.get(DataComponents.BUCKET_ENTITY_DATA).copyTag().getInt("Variant")) * 4.0f) / 16f;
        });
    }
}
