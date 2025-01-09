package net.firemuffin303.muffinsthaidelightfabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.PlayerSpicyRenderer;
import net.firemuffin303.muffinsthaidelightfabric.client.sceens.MortarScreen;
import net.firemuffin303.muffinsthaidelightfabric.common.block.FermentedFishCauldronBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.data.SpicyData;
import net.firemuffin303.muffinsthaidelightfabric.common.item.tooltipComponent.FlavorTooltipClient;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModMenuType;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Block;

public class ThaiDelightClient implements ClientModInitializer {
    private static final Block[] CUTOUT = {ModBlocks.SOMTAM_FEAST, ModBlocks.LIME_BUSH, ModBlocks.WILD_PEPPER_CROP, ModBlocks.PEPPER_CROP, ModBlocks.PAPAYA, ModBlocks.PAPAYA_SAPLING, ModBlocks.CRAB_EGG, ModBlocks.PAPAYA_CROP, ModBlocks.LIME_SAPLING};

    @Override
    public void onInitializeClient() {
        ClientModelRegistry.entityInit();

        MenuScreens.register(ModMenuType.MORTAR, MortarScreen::new);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),CUTOUT);

        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> {
            if(blockAndTintGetter != null && blockPos != null && blockState.getValue(FermentedFishCauldronBlock.FERMENT) == 0){
                return BiomeColors.getAverageWaterColor(blockAndTintGetter,blockPos);
            }
            return 0xFFFFFF;
        },ModBlocks.FERMENTED_FISH_CAULDRON);

        HudRenderCallback.EVENT.register(ModHudRenderer::init);

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, livingEntityRenderer, registrationHelper, context) -> {
            Model model = livingEntityRenderer.getModel();
            if(model instanceof HeadedModel) {
                RenderLayerParent<LivingEntity, EntityModel<LivingEntity>> renderLayerParent = (RenderLayerParent<LivingEntity, EntityModel<LivingEntity>>) livingEntityRenderer;
                registrationHelper.register(new PlayerSpicyRenderer(renderLayerParent, (LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>>) livingEntityRenderer));
            }
        });

        ClientPlayNetworking.registerGlobalReceiver(ThaiDelight.SPICY_PAYLOAD_ID,(minecraft, clientPacketListener, friendlyByteBuf, packetSender) -> {
            int spicyLevel = friendlyByteBuf.readInt();
            minecraft.execute(() ->{
                if(minecraft.player != null){
                    ((SpicyData.SpicyAccessor)minecraft.player).muffinsThaiDelight$access().spicyLevel = spicyLevel;
                }
            });
        });

        TooltipComponentCallback.EVENT.register(tooltipComponent -> {
            if(tooltipComponent instanceof FlavorTooltipClient.FlavorTooltipComponent flavorTooltipComponent){
                return new FlavorTooltipClient(flavorTooltipComponent);
            }
            return null;
        });
    }
}
