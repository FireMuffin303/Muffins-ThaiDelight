package net.firemuffin303.muffinsthaidelightfabric.client.renderer.blocks;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.firemuffin303.muffinsthaidelightfabric.common.block.SackBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.blockEntity.SackBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.state.BlockState;
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

        BlockState blockState = blockEntity.getBlockState();

        ItemStack itemStack = blockEntity.getFirstStack();
        BakedModel bakedModel = itemRenderer.getModel(itemStack,blockEntity.getLevel(),null,0);
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.getInstance().renderBuffers().bufferSource();
        poseStack.pushPose();

        Direction direction = blockState.getValue(SackBlock.HORIZONTAL_FACING);
        float g = direction.getClockWise().toYRot();
        poseStack.mulPose(Axis.YP.rotationDegrees(-g + 90));

        //RenderSystem.applyModelViewMatrix();
        poseStack.translate(0f,0.5f,0f);

        switch (direction){
            case SOUTH -> poseStack.translate(0.5f,0f,0.94375f);
            case EAST -> poseStack.translate(-0.5f,0f,0.94375f);
            case WEST -> poseStack.translate(0.5f,0f,-0.05625);
            default -> poseStack.translate(-0.5f,0f,-0.05625);
        }

        poseStack.scale(0.6f,0.6f,0.6f);
        poseStack.mulPoseMatrix(new Matrix4f().scale(1, 1, 0.01f));

        this.itemRenderer.render(itemStack,ItemDisplayContext.GUI,false,poseStack,multiBufferSource,0xF000F0,OverlayTexture.NO_OVERLAY,bakedModel);
        RenderSystem.disableDepthTest();
        multibuffersource$buffersource.endBatch();
        RenderSystem.enableDepthTest();


        poseStack.popPose();
    }
}
