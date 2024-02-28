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
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class ThaiDelightModFabricClient implements ClientModInitializer {

    public static final ModelResourceLocation STONE_PASTLE_MODEL = new ModelResourceLocation(ThaiDelight.MOD_ID,"stone_pastle_3d","inventory");
    public static final ModelResourceLocation IRON_PASTLE_MODEL = new ModelResourceLocation(ThaiDelight.MOD_ID,"iron_pastle_3d","inventory");
    public static final ModelResourceLocation GOLDEN_PASTLE_MODEL = new ModelResourceLocation(ThaiDelight.MOD_ID,"golden_pastle_3d","inventory");
    public static final ModelResourceLocation DIAMOND_PASTLE_MODEL = new ModelResourceLocation(ThaiDelight.MOD_ID,"diamond_pastle_3d","inventory");
    public static final ModelResourceLocation NETHERITE_PASTLE_MODEL = new ModelResourceLocation(ThaiDelight.MOD_ID,"netherite_pastle_3d","inventory");

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
                ModBlocks.SOMTAM_FEAST,
                ModBlocks.LIME_SAPLING,
                ModBlocks.LIME_CROP,
                ModBlocks.WILD_PEPPER_CROP,
                ModBlocks.PEPPER_CROP,
                ModBlocks.PAPAYA,
                ModBlocks.PAPAYA_SAPLING,
                ModBlocks.CRAB_EGG,
                ModBlocks.PAPAYA_CROPS);

        ItemProperties.register(ModItems.DRAGONFLY_BOTTLE,new ResourceLocation(ThaiDelight.MOD_ID,"variant"),(itemStack, clientLevel, livingEntity, i) -> {
            return ((itemStack.getOrCreateTag().getInt("Variant")) * 4.0f) / 16f;
        });
    }
}
