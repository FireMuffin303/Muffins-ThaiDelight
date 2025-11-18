package net.firemuffin303.muffinsthaidelightfabric.client.renderer.blocks;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.firemuffin303.muffinsthaidelightfabric.common.block.blockEntity.SackBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4f;

public class SackBlockEntityRenderer implements BlockEntityRenderer<SackBlockEntity> {
    private final ItemRenderer itemRenderer;

    public SackBlockEntityRenderer(BlockEntityRendererProvider.Context context){
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(SackBlockEntity blockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {
        if(blockEntity.getFirstStack().isEmpty()){
            return;
        }

        ItemStack itemStack = blockEntity.getFirstStack();
        BakedModel bakedModel = itemRenderer.getModel(itemStack,blockEntity.getLevel(),null,0);
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.getInstance().renderBuffers().bufferSource();
        poseStack.pushPose();
        RenderSystem.applyModelViewMatrix();
        poseStack.translate(0.5f, 0.4f, 0.814f);
        poseStack.scale(0.5f,0.5f,0.5f);
        poseStack.mulPoseMatrix(new Matrix4f().scale(1, 1, 0.01f));

        this.itemRenderer.render(itemStack,ItemDisplayContext.GUI,false,poseStack,multiBufferSource,0xF000F0,OverlayTexture.NO_OVERLAY,bakedModel);
        RenderSystem.disableDepthTest();
        multibuffersource$buffersource.endBatch();
        RenderSystem.enableDepthTest();


        poseStack.popPose();
    }
}
