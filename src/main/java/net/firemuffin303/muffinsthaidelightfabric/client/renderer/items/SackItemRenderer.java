package net.firemuffin303.muffinsthaidelightfabric.client.renderer.items;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.client.ThaiDelightClient;
import net.firemuffin303.muffinsthaidelightfabric.common.block.SackBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.blockEntity.SackBlockEntity;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class SackItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    public static final ModelResourceLocation CATCHER_IN_HAND_MODEL = new ModelResourceLocation(ThaiDelight.MOD_ID,"catcher_bag_in_hand","inventory");
    public static final ModelResourceLocation CATCHER_MODEL = new ModelResourceLocation(ThaiDelight.MOD_ID,"catcher_bag_inventory","inventory");
    private ModelManager modelManager;
    private final SackBlockEntity sackBlockEntity = new SackBlockEntity(BlockPos.ZERO, ModBlocks.SACK.defaultBlockState());

    @Override
    public void render(ItemStack stack, ItemDisplayContext mode, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        boolean thirdPerson = mode == ItemDisplayContext.THIRD_PERSON_LEFT_HAND || mode == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND || mode == ItemDisplayContext.HEAD;
        boolean isLeftHand = false;
        Item item = stack.getItem();
        SackBlock sackBlock = (SackBlock) ((BlockItem)item).getBlock();
        if(mode == ItemDisplayContext.THIRD_PERSON_LEFT_HAND){
            isLeftHand = true;
        }
        boolean bl = BlockItem.getBlockEntityData(stack) != null;
        this.sackBlockEntity.load(BlockItem.getBlockEntityData(stack));

        matrices.pushPose();

        matrices.translate(0.5f,0.5f,0.5f);
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        BakedModel bakedModel = itemRenderer.getItemModelShaper().getModelManager().getModel(ThaiDelightClient.FULL_SACK_MODEL_IN_HAND);
        itemRenderer.render(stack,mode,isLeftHand,matrices,vertexConsumers,light,overlay,bakedModel);

        matrices.pushPose();
        matrices.translate(-0.85f,-0.75f,-0.25f);
        Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(this.sackBlockEntity,matrices,vertexConsumers,light,overlay);
        matrices.popPose();

        matrices.popPose();


    }
}
