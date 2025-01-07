package net.firemuffin303.muffinsthaidelightfabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.client.model.BuffoloModel;
import net.firemuffin303.muffinsthaidelightfabric.client.model.DragonflyModel;
import net.firemuffin303.muffinsthaidelightfabric.client.model.FlowerCrabModel;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.BuffaloRenderer;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.CrabRenderer;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.DragonflyRenderer;
import net.firemuffin303.muffinsthaidelightfabric.client.sceens.MortarScreen;
import net.firemuffin303.muffinsthaidelightfabric.common.block.FermentedFishCauldronBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.entitydata.SpicyData;
import net.firemuffin303.muffinsthaidelightfabric.common.item.tooltipComponent.FlavorTooltipClient;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModEntityTypes;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModMenuType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;

public class ThaiDelightClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntityTypes.FLOWER_CRAB, CrabRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.DRAGONFLY, DragonflyRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.BUFFALO, BuffaloRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(FlowerCrabModel.LAYER,FlowerCrabModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(DragonflyModel.LAYER,DragonflyModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(BuffoloModel.LAYER_LOCATION,BuffoloModel::createBodyLayer);

        MenuScreens.register(ModMenuType.MORTAR, MortarScreen::new);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                ModBlocks.SOMTAM_FEAST,
                ModBlocks.LIME_BUSH,
                ModBlocks.WILD_PEPPER_CROP,
                ModBlocks.PEPPER_CROP,
                ModBlocks.PAPAYA,
                ModBlocks.PAPAYA_SAPLING,
                ModBlocks.CRAB_EGG,
                ModBlocks.PAPAYA_CROP,
                ModBlocks.LIME_SAPLING
        );

        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> {
            if(blockAndTintGetter != null && blockPos != null && blockState.getValue(FermentedFishCauldronBlock.FERMENT) == 0){
                return BiomeColors.getAverageWaterColor(blockAndTintGetter,blockPos);
            }
            return 0xFFFFFF;
        },ModBlocks.FERMENTED_FISH_CAULDRON);

        HudRenderCallback.EVENT.register(ModHudRenderer::init);

        /*
        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {
            String newShader = null;
            GameRenderer renderer = Minecraft.getInstance().gameRenderer;

            if(minecraft.player != null && ((SpicyData.SpicyAccessor)minecraft.player).muffinsThaiDelight$access().getSpicyLevel() > 0 ){
                newShader = "shaders/post/creeper.json";
            }

            if(newShader != null){
                renderer.loadEffect(new ResourceLocation(newShader));
            }else{
                renderer.shutdownEffect();
            }


        });

         */




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
