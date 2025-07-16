package net.firemuffin303.muffinsthaidelightfabric.client;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.client.sceens.MortarScreen;
import net.firemuffin303.muffinsthaidelightfabric.common.block.FermentedFishCauldronBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.item.tooltipComponent.FlavorTooltipClient;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModMenuType;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.Block;

public class ThaiDelightClient implements ClientModInitializer {
    private static final Block[] CUTOUT = {ModBlocks.SOMTAM_FEAST, ModBlocks.LIME_BUSH, ModBlocks.WILD_PEPPER_CROP, ModBlocks.PEPPER_CROP, ModBlocks.PAPAYA, ModBlocks.PAPAYA_SAPLING, ModBlocks.CRAB_EGG, ModBlocks.PAPAYA_CROP, ModBlocks.LIME_SAPLING,ModBlocks.DURIAN_BLOCK,ModBlocks.DURIAN_FLOWER};

    @Override
    public void onInitializeClient() {
        ClientModelRegistry.entityInit();

        TerraformBoatClientHelper.registerModelLayers(ThaiDelight.modid("durian_boat"),false);
        TerraformBoatClientHelper.registerModelLayers(ThaiDelight.modid("coconut_boat"),false);
        TerraformBoatClientHelper.registerModelLayers(ThaiDelight.modid("mango_boat"),false);


        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(Sheets.SIGN_SHEET, ThaiDelight.modid("entity/signs/durian")));
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(Sheets.SIGN_SHEET, ThaiDelight.modid("entity/signs/coconut")));
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(Sheets.SIGN_SHEET, ThaiDelight.modid("entity/signs/mango")));
        MenuScreens.register(ModMenuType.MORTAR, MortarScreen::new);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),CUTOUT);

        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> {
            if(blockAndTintGetter != null && blockPos != null && blockState.getValue(FermentedFishCauldronBlock.FERMENT) == 0){
                return BiomeColors.getAverageWaterColor(blockAndTintGetter,blockPos);
            }
            return 0xFFFFFF;
        },ModBlocks.FERMENTED_FISH_CAULDRON);

        HudRenderCallback.EVENT.register(ModHudRenderer::init);

        TooltipComponentCallback.EVENT.register(tooltipComponent -> {
            if(tooltipComponent instanceof FlavorTooltipClient.FlavorTooltipComponent flavorTooltipComponent){
                return new FlavorTooltipClient(flavorTooltipComponent);
            }
            return null;
        });
    }
}
