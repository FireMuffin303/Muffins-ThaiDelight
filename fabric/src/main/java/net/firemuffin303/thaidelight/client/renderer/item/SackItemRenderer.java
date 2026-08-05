package net.firemuffin303.thaidelight.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.client.ThaiDelightClientFabric;
import net.firemuffin303.thaidelight.client.ThaiDelightCommonClient;
import net.firemuffin303.thaidelight.common.block.SackBlock;
import net.firemuffin303.thaidelight.common.block.blockentity.SackBlockEntity;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class SackItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    private final SackBlockEntity sackBlockEntity = new SackBlockEntity(BlockPos.ZERO, ModBlocks.SACK.get().defaultBlockState());


    @Override
    public void render(ItemStack stack, ItemDisplayContext mode, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        boolean thirdPerson = mode == ItemDisplayContext.THIRD_PERSON_LEFT_HAND ||
                mode == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND ||
                mode == ItemDisplayContext.HEAD;
        boolean gui = mode == ItemDisplayContext.GUI || mode == ItemDisplayContext.FIXED || mode == ItemDisplayContext.GROUND;
        boolean isLeftHand = false;
        Item item = stack.getItem();
        SackBlock sackBlock = (SackBlock) ((BlockItem)item).getBlock();
        if(mode == ItemDisplayContext.THIRD_PERSON_LEFT_HAND){
            isLeftHand = true;
        }

        List<ItemStack> stackList = stack.get(DataComponents.CONTAINER).nonEmptyStream().toList();
        NonNullList<ItemStack> itemStacks = NonNullList.withSize(5,ItemStack.EMPTY);
        for(int i = 0;i< stackList.size();i++){
            itemStacks.set(i,stackList.get(i));
        }

        this.sackBlockEntity.setItems(itemStacks);

        matrices.pushPose();

        matrices.translate(0.5f,0.5f,0.5f);
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        BakedModel bakedModel = itemRenderer.getItemModelShaper().getModelManager().getModel(ThaiDelightCommonClient.FULL_SACK_MODEL_IN_HAND);
        itemRenderer.render(stack,mode,isLeftHand,matrices,vertexConsumers,light,overlay,bakedModel);

        if(thirdPerson){
            matrices.pushPose();
            matrices.scale(0.8f,0.8f,1f);
            if (isLeftHand) {
                matrices.translate(-0.025f, -0.55f, -0.12f);
            } else {
                matrices.translate(-0.975f, -0.55f, -0.12f);
            }

            Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(this.sackBlockEntity,matrices,vertexConsumers,light,overlay);
            matrices.popPose();
        }

        matrices.popPose();


    }
}
