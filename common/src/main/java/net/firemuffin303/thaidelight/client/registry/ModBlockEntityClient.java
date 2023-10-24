package net.firemuffin303.thaidelight.client.registry;

import net.firemuffin303.thaidelight.client.renderer.MortarBlockRenderer;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ModBlockEntityClient {
    public static void init(){
        ModPlatform.registerBlockEntityRenderer(ModBlocks.ModBlockEntityTypes.MORTAR_BLOCK_ENTITY.get(), MortarBlockRenderer::new);

    }


    @FunctionalInterface
    public interface BlockEntityRendererSupplier<T extends GeoBlockRenderer<?>> {
        T create(BlockEntityRendererProvider.Context context);
    }
}
