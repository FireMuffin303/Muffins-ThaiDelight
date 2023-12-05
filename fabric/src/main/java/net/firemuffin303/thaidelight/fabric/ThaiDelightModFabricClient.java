package net.firemuffin303.thaidelight.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.client.MobVote2023Client;
import net.firemuffin303.thaidelight.client.registry.ModEntityClient;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;

import java.util.function.Supplier;

public class ThaiDelightModFabricClient implements ClientModInitializer {

    public static final ModelResourceLocation STONE_PASTLE_MODEL = new ModelResourceLocation(ThaiDelight.MOD_ID,"stone_pastle_3d","inventory");
    public static final ModelResourceLocation IRON_PASTLE_MODEL = new ModelResourceLocation(ThaiDelight.MOD_ID,"iron_pastle_3d","inventory");
    public static final ModelResourceLocation GOLDEN_PASTLE_MODEL = new ModelResourceLocation(ThaiDelight.MOD_ID,"golden_pastle_3d","inventory");
    public static final ModelResourceLocation DIAMOND_PASTLE_MODEL = new ModelResourceLocation(ThaiDelight.MOD_ID,"diamond_pastle_3d","inventory");
    public static final ModelResourceLocation NETHERITE_PASTLE_MODEL = new ModelResourceLocation(ThaiDelight.MOD_ID,"netherite_pastle_3d","inventory");

    @Override
    public void onInitializeClient() {
        MobVote2023Client.init();

        ModEntityClient.layerRegistry(new ModEntityClient.LayerDefinitionRegistry() {
            @Override
            public void register(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
                EntityModelLayerRegistry.registerModelLayer(location, definition::get);
            }
        });

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SOMTAM_FEAST, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIME_SAPLING, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIME_CROP, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WILD_PEPPER_CROP, RenderType.cutout());

    }
}
