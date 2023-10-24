package net.firemuffin303.thaidelight.client.renderer;

import net.firemuffin303.thaidelight.client.model.blockentity.MortarBlockModel;
import net.firemuffin303.thaidelight.common.block.entity.MortarBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class MortarBlockRenderer extends GeoBlockRenderer<MortarBlockEntity> {
    public MortarBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new MortarBlockModel());
    }
}
