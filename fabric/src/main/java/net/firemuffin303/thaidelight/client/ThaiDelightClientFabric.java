package net.firemuffin303.thaidelight.client;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import io.github.fabricators_of_create.porting_lib.recipe_book_categories.RecipeBookRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.client.renderer.DurianHeatRendererLayer;
import net.firemuffin303.thaidelight.client.renderer.armor.DurianHelmetRenderer;
import net.firemuffin303.thaidelight.client.renderer.component.SackTooltipComponent;
import net.firemuffin303.thaidelight.client.renderer.item.SackItemRenderer;
import net.firemuffin303.thaidelight.client.sceens.MortarScreen;
import net.firemuffin303.thaidelight.common.block.cauldron.FermentedFishCauldronBlock;
import net.firemuffin303.thaidelight.common.entity.DragonflyEntity;
import net.firemuffin303.thaidelight.common.item.DragonflyBottleItem;
import net.firemuffin303.thaidelight.common.item.SackItem;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipe;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipeBookTab;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModMenuType;
import net.firemuffin303.thaidelight.common.registry.ModRecipes;
import net.firemuffin303.thaidelight.util.ModUtils;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class ThaiDelightClientFabric implements ClientModInitializer {
    private static final List<Supplier<Block>> CUTOUT;

    public static final RecipeBookType MORTAR_RECIPE_BOOK_TYPE = RecipeBookType.valueOf("MORTAR_RECIPE_BOOK_TYPE");
    public static final RecipeBookCategories MORTAR_SEARCH = RecipeBookCategories.valueOf("MORTAR_SEARCH");
    public static final RecipeBookCategories MORTAR_MEALS = RecipeBookCategories.valueOf("MORTAR_MEALS");
    public static final RecipeBookCategories MORTAR_MISC = RecipeBookCategories.valueOf("MORTAR_MISC");


    @Override
    public void onInitializeClient() {
        ThaiDelightCommonClient.entityRendererRegister(EntityRendererRegistry::register);
        ThaiDelightCommonClient.entityModelRegister(((modelLayer, provider) -> EntityModelLayerRegistry.registerModelLayer(modelLayer, provider::get)));
        ThaiDelightCommonClient.blockEntityRenderRegister(BlockEntityRenderers::register);
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.SACK.get(),new SackItemRenderer());

        TerraformBoatClientHelper.registerModelLayers(ThaiDelightCommon.modid("durian_boat"),false);
        TerraformBoatClientHelper.registerModelLayers(ThaiDelightCommon.modid("coconut_boat"),false);
        TerraformBoatClientHelper.registerModelLayers(ThaiDelightCommon.modid("mango_boat"),false);

        ArmorRenderer.register(new DurianHelmetRenderer(), ModItems.DURIAN_HELMET.get());

        ThaiDelightCommonClient.registerCustomEffectRenderer();

        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(Sheets.SIGN_SHEET, ThaiDelightCommon.modid("entity/signs/durian")));
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(Sheets.SIGN_SHEET, ThaiDelightCommon.modid("entity/signs/coconut")));
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(Sheets.SIGN_SHEET, ThaiDelightCommon.modid("entity/signs/mango")));

        MenuScreens.register(ModMenuType.MORTAR.get(), MortarScreen::new);

        registerRecipe();

        ItemProperties.register(ModItems.DRAGONFLY_BOTTLE.get(), ThaiDelightCommon.modid("variant"), new ClampedItemPropertyFunction() {
            @Override
            public float unclampedCall(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int i) {
                return ((float) DragonflyBottleItem.getVariant(itemStack)) / ((float) DragonflyEntity.DragonflyVariant.values().length);
            }
        });

        ItemProperties.register(ModItems.SACK.get(), ThaiDelightCommon.modid("fullness"), new ClampedItemPropertyFunction() {
            @Override
            public float unclampedCall(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int i) {
                return SackItem.isFull(itemStack) ? 1f : 0f;
            }
        });

        ModelLoadingPlugin.register(new ModelLoadingPlugin() {
            @Override
            public void onInitializeModelLoader(Context context) {
                context.addModels(ThaiDelightCommonClient.SACK_MODEL,
                        ThaiDelightCommonClient.FILLED_SACK_MODEL,
                        ThaiDelightCommonClient.SACK_MODEL_IN_HAND,
                        ThaiDelightCommonClient.FULL_SACK_MODEL_IN_HAND
                );

            }
        });

        TooltipComponentCallback.EVENT.register(new TooltipComponentCallback() {
            @Override
            public @Nullable ClientTooltipComponent getComponent(TooltipComponent data) {
                if(data instanceof SackTooltipComponent.SackToolTip sackTooltipComponent){
                    return new SackTooltipComponent(sackTooltipComponent);
                }
                return null;
            }
        });

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),CUTOUT.stream().map(Supplier::get).toArray(Block[]::new));

        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> {
            if(blockAndTintGetter != null && blockPos != null && blockState.getValue(FermentedFishCauldronBlock.FERMENT) == 0){
                return BiomeColors.getAverageWaterColor(blockAndTintGetter,blockPos);
            }
            return 0xFFFFFF;
        },ModBlocks.FERMENTED_FISH_CAULDRON.get());

        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> {
            if(blockAndTintGetter != null && blockPos != null){
                return BiomeColors.getAverageFoliageColor(blockAndTintGetter,blockPos);
            }
            return FoliageColor.getDefaultColor();
        },ModBlocks.DURIAN_LEAVES.get(),ModBlocks.MANGO_LEAVES.get());

        ColorProviderRegistry.ITEM.register((itemStack, i) -> FoliageColor.getDefaultColor(), ModItems.DURIAN_LEAVES.get(),ModItems.MANGO_LEAVES.get());
        ColorProviderRegistry.ITEM.register((itemStack, i) -> i > 0 ? -1 : ModUtils.getColor(itemStack), ModItems.COCONUT_MILK_ICE_CREAM.get());
        ColorProviderRegistry.ITEM.register((itemStack, i) -> ModUtils.getColor(itemStack), ModItems.KHANOM_CHAN.get());

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register(new LivingEntityFeatureRendererRegistrationCallback() {
            @Override
            public void registerRenderers(EntityType<? extends LivingEntity> entityType, LivingEntityRenderer<?, ?> livingEntityRenderer, RegistrationHelper registrationHelper, EntityRendererProvider.Context context) {
                if(livingEntityRenderer instanceof PlayerRenderer playerRenderer){
                    registrationHelper.register(new DurianHeatRendererLayer<>(playerRenderer));
                }
            }
        });

        ItemProperties.register(ModItems.DRAGONFLY_BOTTLE.get(), ThaiDelightCommon.modid("variant"), new ClampedItemPropertyFunction() {
            @Override
            public float unclampedCall(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int i) {
                return ((float)DragonflyBottleItem.getVariant(itemStack)) / ((float)DragonflyEntity.DragonflyVariant.values().length);
            }
        });

        ItemProperties.register(ModItems.SACK.get(), ThaiDelightCommon.modid("fullness"), new ClampedItemPropertyFunction() {
            @Override
            public float unclampedCall(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int i) {
                return SackItem.isFull(itemStack) ? 1f : 0f;
            }
        });
    }


    public static void registerRecipe(){
        RecipeBookRegistry.registerBookCategories(MORTAR_RECIPE_BOOK_TYPE, List.of(MORTAR_SEARCH,MORTAR_MEALS,MORTAR_MISC));
        RecipeBookRegistry.registerAggregateCategory(MORTAR_SEARCH,List.of(MORTAR_MEALS,MORTAR_MISC));
        RecipeBookRegistry.registerRecipeCategoryFinder(ModRecipes.MORTAR.get(), recipe -> {
            if(recipe instanceof MortarRecipe mortarRecipe){
                MortarRecipeBookTab mortarRecipeBookTab = mortarRecipe.getRecipeBookTab();
                if(mortarRecipeBookTab != null){
                    return switch (mortarRecipeBookTab){
                        case MEALS -> MORTAR_MEALS;
                        case MISC -> MORTAR_MISC;
                    };
                }
            }

            return MORTAR_MISC;
        });


    }




    static {
        CUTOUT = List.of(ModBlocks.SOMTAM_FEAST,
                ModBlocks.WILD_PEPPER_CROP,
                ModBlocks.PEPPER_CROP,
                ModBlocks.PAPAYA,
                ModBlocks.PAPAYA_SAPLING,
                ModBlocks.CRAB_EGG,
                ModBlocks.PAPAYA_CROP,
                ModBlocks.LIME_SAPLING,
                ModBlocks.HANGING_DURIAN,
                ModBlocks.SMALL_DURIAN_BLOCK,
                ModBlocks.DURIAN_BLOCK,
                ModBlocks.DURIAN_FLOWER,
                ModBlocks.DURIAN_LEAVES,
                ModBlocks.DURIAN_SAPLING,
                ModBlocks.HANGING_MANGO_BLOCK,
                ModBlocks.LIME_PLANT,
                ModBlocks.MANGO_SAPLING,
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
                ModBlocks.STACKABLE_MANGO_BLOCK,
                ModBlocks.COCONUT,
                ModBlocks.BUDDING_PEPPER_CROP,
                ModBlocks.BUDDING_PAPAYA_FLOWER,
                ModBlocks.PAPAYA_FLOWER,
                ModBlocks.WALL_PAPAYA_FLOWER,
                ModBlocks.WALL_PAPAYA_LEAVES,
                ModBlocks.PAPAYA_LEAVES,
                ModBlocks.PAPAYA_LEAVES_STEM,
                ModBlocks.DURIAN_DOOR,
                ModBlocks.DURIAN_TRAPDOOR,
                ModBlocks.MANGO_DOOR,
                ModBlocks.MANGO_TRAPDOOR,
                ModBlocks.COCONUT_DOOR,
                ModBlocks.COCONUT_TRAPDOOR,
                ModBlocks.BUDDING_COCONUT_LEAF,
                ModBlocks.PINEAPPLE_FRIED_RICE_FEAST,
                ModBlocks.STRIPPED_COCONUT,
                ModBlocks.COCONUT_SAPLING,
                ModBlocks.WILD_BASIL,
                ModBlocks.WILD_HOLY_BASIL,
                ModBlocks.POTTED_HOLY_BASIL,
                ModBlocks.POTTED_BASIL,
                ModBlocks.LARB_FEAST);
    }
}
