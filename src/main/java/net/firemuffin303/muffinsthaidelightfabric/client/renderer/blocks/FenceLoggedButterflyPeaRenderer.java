package net.firemuffin303.muffinsthaidelightfabric.client.renderer.blocks;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsthaidelightfabric.common.block.blockEntity.FenceLoggedButterflyPeaBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class FenceLoggedButterflyPeaRenderer implements BlockEntityRenderer<FenceLoggedButterflyPeaBlockEntity> {
    private final BlockRenderDispatcher blockRenderDispatcher;

    public FenceLoggedButterflyPeaRenderer(BlockEntityRendererProvider.Context context){
        this.blockRenderDispatcher = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(FenceLoggedButterflyPeaBlockEntity blockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, int overlay) {
        poseStack.pushPose();
        BlockState blockState = blockEntity.fenceState;
        BlockState sourceState = blockEntity.getBlockState();

        blockState = blockState.setValue(BlockStateProperties.NORTH,sourceState.getValue(BlockStateProperties.NORTH));
        blockState = blockState.setValue(BlockStateProperties.EAST,sourceState.getValue(BlockStateProperties.EAST));
        blockState = blockState.setValue(BlockStateProperties.SOUTH,sourceState.getValue(BlockStateProperties.SOUTH));
        blockState = blockState.setValue(BlockStateProperties.WEST,sourceState.getValue(BlockStateProperties.WEST));
        blockState = blockState.setValue(BlockStateProperties.WATERLOGGED,sourceState.getValue(BlockStateProperties.WATERLOGGED));

        blockRenderDispatcher.renderSingleBlock(blockState,poseStack,multiBufferSource,light,overlay);
        poseStack.popPose();
    }
}
