package net.firemuffin303.muffinsthaidelightfabric.client;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import io.github.fabricators_of_create.porting_lib.recipe_book_categories.RecipeBookRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.client.sceens.MortarScreen;
import net.firemuffin303.muffinsthaidelightfabric.common.block.FermentedFishCauldronBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.DragonflyEntity;
import net.firemuffin303.muffinsthaidelightfabric.common.item.DragonflyBottleItem;
import net.firemuffin303.muffinsthaidelightfabric.common.item.tooltipComponent.FlavorTooltipClient;
import net.firemuffin303.muffinsthaidelightfabric.common.recipe.mortar.MortarRecipe;
import net.firemuffin303.muffinsthaidelightfabric.common.recipe.mortar.MortarRecipeBookTab;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModMenuType;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModRecipes;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ThaiDelightClient implements ClientModInitializer {
    private static final Block[] CUTOUT = {
            ModBlocks.SOMTAM_FEAST,
            ModBlocks.WILD_PEPPER_CROP,
            ModBlocks.PEPPER_CROP,
            ModBlocks.PAPAYA,
            ModBlocks.PAPAYA_SAPLING,
            ModBlocks.CRAB_EGG,
            ModBlocks.PAPAYA_CROP,
            ModBlocks.LIME_SAPLING,
            ModBlocks.DURIAN_BLOCK,
            ModBlocks.DURIAN_FLOWER,
            ModBlocks.DURIAN_LEAVES,
            ModBlocks.DURIAN_SAPLING,
            ModBlocks.MANGO_BLOCK,
            ModBlocks.LIME_PLANT,
            ModBlocks.POTTED_LIME_SAPLING,
            ModBlocks.POTTED_COCONUT_SAPLING,
            ModBlocks.POTTED_DURIAN_SAPLING,
            ModBlocks.POTTED_MANGO_SAPLING,
            ModBlocks.HOLY_BASIL,
            ModBlocks.BASIL,
            ModBlocks.COCONUT_LEAF,
            ModBlocks.COCONUT_LEAF_END,
            ModBlocks.BUTTERFLY_PEA_WALL,
            ModBlocks.BUTTERFLY_PEA_BLOCK,
            ModBlocks.BUDDING_BUTTERFLY_PEA_BLOCK,
            ModBlocks.SMALL_DURIAN_BLOCK
    };

    public static final RecipeBookType MORTAR_RECIPE_BOOK_TYPE = RecipeBookType.valueOf("MORTAR_RECIPE_BOOK_TYPE");
    public static final RecipeBookCategories MORTAR_SEARCH = RecipeBookCategories.valueOf("MORTAR_SEARCH");
    public static final RecipeBookCategories MORTAR_MEALS = RecipeBookCategories.valueOf("MORTAR_MEALS");
    public static final RecipeBookCategories MORTAR_MISC = RecipeBookCategories.valueOf("MORTAR_MISC");

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

        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> {
            if(blockAndTintGetter != null && blockPos != null){
                return BiomeColors.getAverageFoliageColor(blockAndTintGetter,blockPos);
            }
            return FoliageColor.getDefaultColor();
        },ModBlocks.DURIAN_LEAVES,ModBlocks.MANGO_LEAVES);

        ColorProviderRegistry.ITEM.register((itemStack, i) -> FoliageColor.getDefaultColor(), ModItems.DURIAN_LEAVES,ModItems.MANGO_LEAVES);

        FabricModelPredicateProviderRegistry.register(ModItems.DRAGONFLY_BOTTLE, ThaiDelight.modid("variant"), new ClampedItemPropertyFunction() {
            @Override
            public float unclampedCall(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int i) {
                return ((float)DragonflyBottleItem.getVariant(itemStack)) / ((float)DragonflyEntity.DragonflyVariant.values().length);
            }
        });

        HudRenderCallback.EVENT.register(ModHudRenderer::init);

        TooltipComponentCallback.EVENT.register(tooltipComponent -> {
            if(tooltipComponent instanceof FlavorTooltipClient.FlavorTooltipComponent flavorTooltipComponent){
                return new FlavorTooltipClient(flavorTooltipComponent);
            }
            return null;
        });

        RecipeBookRegistry.registerBookCategories(ThaiDelightClient.MORTAR_RECIPE_BOOK_TYPE,
                List.of(ThaiDelightClient.MORTAR_SEARCH,ThaiDelightClient.MORTAR_MEALS,ThaiDelightClient.MORTAR_MISC));
        RecipeBookRegistry.registerAggregateCategory(ThaiDelightClient.MORTAR_SEARCH,List.of(ThaiDelightClient.MORTAR_MEALS,ThaiDelightClient.MORTAR_MISC));
        RecipeBookRegistry.registerRecipeCategoryFinder(ModRecipes.MORTAR, recipe -> {
            if(recipe instanceof MortarRecipe mortarRecipe){
                MortarRecipeBookTab mortarRecipeBookTab = mortarRecipe.getRecipeBookTab();
                if(mortarRecipeBookTab != null){
                    return switch (mortarRecipeBookTab){
                        case MEALS -> MORTAR_MEALS;
                        case MISC -> MORTAR_MISC;
                    };
                }
            }

            return ThaiDelightClient.MORTAR_MISC;
        });

    }
}
