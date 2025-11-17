package net.firemuffin303.muffinsthaidelightfabric.client.renderer;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class CatcherBagItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer, SimpleSynchronousResourceReloadListener {
    public static final ModelResourceLocation CATCHER_IN_HAND_MODEL = new ModelResourceLocation(ThaiDelight.MOD_ID,"catcher_bag_in_hand","inventory");
    public static final ModelResourceLocation CATCHER_MODEL = new ModelResourceLocation(ThaiDelight.MOD_ID,"catcher_bag_inventory","inventory");
    private ModelManager modelManager;

    @Override
    public void render(ItemStack stack, ItemDisplayContext mode, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        boolean inHand = mode.firstPerson() || mode == ItemDisplayContext.THIRD_PERSON_LEFT_HAND || mode == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND || mode == ItemDisplayContext.HEAD;
        boolean inInventory = mode == ItemDisplayContext.GUI || mode == ItemDisplayContext.GROUND || mode == ItemDisplayContext.FIXED;



        BakedModel bakedModel = inHand ? this.modelManager.getModel(CATCHER_IN_HAND_MODEL) : this.modelManager.getModel(CATCHER_MODEL);

        matrices.pushPose();
        bakedModel.getTransforms().getTransform(mode).apply(true,matrices);
        if(inInventory){
            matrices.translate(0.5f,0.5F, 0.5F);
            Lighting.setupForFlatItems();
        }

        if(inHand){
            matrices.translate(0.1f,0.8F, 0.5F);
        }

        Minecraft.getInstance().getItemRenderer().render(stack,mode,false,matrices,vertexConsumers,light,overlay, bakedModel);

        if(vertexConsumers instanceof MultiBufferSource.BufferSource bufferSource){
            bufferSource.endBatch();
        }

        if(inInventory){
            Lighting.setupFor3DItems();
        }

        matrices.popPose();
    }

    @Override
    public ResourceLocation getFabricId() {
        return ThaiDelight.modid("catcher_bag");
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {

        this.modelManager = Minecraft.getInstance().getModelManager();
    }
}
