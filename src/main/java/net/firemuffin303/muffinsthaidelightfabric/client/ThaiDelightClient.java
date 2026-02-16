package net.firemuffin303.muffinsthaidelightfabric.client;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.mojang.logging.LogUtils;
import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import io.github.fabricators_of_create.porting_lib.recipe_book_categories.RecipeBookRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.fabricmc.loader.api.FabricLoader;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.DurianHeatRendererLayer;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.StatusEffectRenderer;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.armor.DurianHelmetRenderer;
import net.firemuffin303.muffinsthaidelightfabric.common.attachments.DurianHeatAttachment;
import net.firemuffin303.muffinsthaidelightfabric.network.packet.DurianHeatPacket;
import net.firemuffin303.muffinsthaidelightfabric.network.packet.ModLevelEventPacket;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.blocks.SackBlockEntityRenderer;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.items.SackItemRenderer;
import net.firemuffin303.muffinsthaidelightfabric.client.renderer.component.SackTooltipComponent;
import net.firemuffin303.muffinsthaidelightfabric.client.sceens.MortarScreen;
import net.firemuffin303.muffinsthaidelightfabric.common.block.FermentedFishCauldronBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.entity.DragonflyEntity;
import net.firemuffin303.muffinsthaidelightfabric.common.item.DragonflyBottleItem;
import net.firemuffin303.muffinsthaidelightfabric.common.item.SackItem;
import net.firemuffin303.muffinsthaidelightfabric.common.item.tooltipComponent.FlavorTooltipClient;
import net.firemuffin303.muffinsthaidelightfabric.common.recipe.mortar.MortarRecipe;
import net.firemuffin303.muffinsthaidelightfabric.common.recipe.mortar.MortarRecipeBookTab;
import net.firemuffin303.muffinsthaidelightfabric.network.packet.SpicyPacket;
import net.firemuffin303.muffinsthaidelightfabric.network.packet.ThaiDelightConfigPacket;
import net.firemuffin303.muffinsthaidelightfabric.util.BlockEntityTypeAdder;
import net.firemuffin303.muffinsthaidelightfabric.registry.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
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
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.Nullable;

import java.util.*;

@Environment(EnvType.CLIENT)
public class ThaiDelightClient implements ClientModInitializer {
    private static final Block[] CUTOUT;

    public static final ModelResourceLocation SACK_MODEL_IN_HAND = new ModelResourceLocation(ThaiDelight.MOD_ID,"sack_in_hand","inventory");
    public static final ModelResourceLocation FULL_SACK_MODEL_IN_HAND = new ModelResourceLocation(ThaiDelight.MOD_ID,"full_sack_in_hand","inventory");
    public static final ModelResourceLocation SACK_MODEL = new ModelResourceLocation(ThaiDelight.MOD_ID,"sack","inventory");
    public static final ModelResourceLocation FILLED_SACK_MODEL = new ModelResourceLocation(ThaiDelight.MOD_ID,"filled_sack","inventory");

    public static final SplashRenderer FISH_OF_THIEVES = new SplashRenderer("Also try Fish of Thieves mod!");

    public static final RecipeBookType MORTAR_RECIPE_BOOK_TYPE = RecipeBookType.valueOf("MORTAR_RECIPE_BOOK_TYPE");
    public static final RecipeBookCategories MORTAR_SEARCH = RecipeBookCategories.valueOf("MORTAR_SEARCH");
    public static final RecipeBookCategories MORTAR_MEALS = RecipeBookCategories.valueOf("MORTAR_MEALS");
    public static final RecipeBookCategories MORTAR_MISC = RecipeBookCategories.valueOf("MORTAR_MISC");

    public static boolean isJEIInstalled = false;
    public static boolean isEMIInstalled = false;


    @Override
    public void onInitializeClient() {
        isJEIInstalled = FabricLoader.getInstance().isModLoaded("jei");
        isEMIInstalled = FabricLoader.getInstance().isModLoaded("emi");

        ClientModelRegistry.entityInit();

        TerraformBoatClientHelper.registerModelLayers(ThaiDelight.modid("durian_boat"),false);
        TerraformBoatClientHelper.registerModelLayers(ThaiDelight.modid("coconut_boat"),false);
        TerraformBoatClientHelper.registerModelLayers(ThaiDelight.modid("mango_boat"),false);

        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.SACK,new SackItemRenderer());
        BlockEntityRenderers.register(ModBlockEntityTypes.SACK_BLOCK_ENTITY, SackBlockEntityRenderer::new);

        ModelLoadingPlugin.register(new ModelLoadingPlugin() {
            @Override
            public void onInitializeModelLoader(Context context) {
                context.addModels(ThaiDelightClient.SACK_MODEL,
                        ThaiDelightClient.FILLED_SACK_MODEL,
                        ThaiDelightClient.SACK_MODEL_IN_HAND,
                        ThaiDelightClient.FULL_SACK_MODEL_IN_HAND
                );

            }
        });

        StatusEffectRenderer.init();


        ArmorRenderer.register(new DurianHelmetRenderer(),ModItems.DURIAN_HELMET);

        if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT){
            ClientLifecycleEvents.CLIENT_STARTED.register(new ClientLifecycleEvents.ClientStarted() {
                @Override
                public void onClientStarted(Minecraft minecraft) {
                    Optional<BlockEntityType<?>> blockEntityTypeOptional = BuiltInRegistries.BLOCK_ENTITY_TYPE.getOptional(new ResourceLocation("farmersdelight","cabinet"));
                    if(blockEntityTypeOptional.isPresent()){
                        BlockEntityTypeAdder cabinetAccessor = (BlockEntityTypeAdder) blockEntityTypeOptional.get();
                        ModBlocks.CABINET.forEach(cabinetAccessor::addSupportBlock);
                    }
                }
            });
        }

        TooltipComponentCallback.EVENT.register(new TooltipComponentCallback() {
            @Override
            public @Nullable ClientTooltipComponent getComponent(TooltipComponent data) {
                if(data instanceof SackTooltipComponent.SackToolTip sackTooltipComponent){
                    return new SackTooltipComponent(sackTooltipComponent);
                }
                return null;
            }
        });

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
        ColorProviderRegistry.ITEM.register((itemStack, i) -> i > 0 ? -1 : ((DyeableLeatherItem) itemStack.getItem()).getColor(itemStack), ModItems.COCONUT_MILK_ICE_CREAM);
        ColorProviderRegistry.ITEM.register((itemStack, i) -> ((DyeableLeatherItem) itemStack.getItem()).getColor(itemStack), ModItems.KHANOM_CHAN);

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register(new LivingEntityFeatureRendererRegistrationCallback() {
            @Override
            public void registerRenderers(EntityType<? extends LivingEntity> entityType, LivingEntityRenderer<?, ?> livingEntityRenderer, RegistrationHelper registrationHelper, EntityRendererProvider.Context context) {
                if(livingEntityRenderer instanceof PlayerRenderer playerRenderer){
                    registrationHelper.register(new DurianHeatRendererLayer<>(playerRenderer));
                }
            }
        });

        ItemProperties.register(ModItems.DRAGONFLY_BOTTLE, ThaiDelight.modid("variant"), new ClampedItemPropertyFunction() {
            @Override
            public float unclampedCall(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int i) {
                return ((float)DragonflyBottleItem.getVariant(itemStack)) / ((float)DragonflyEntity.DragonflyVariant.values().length);
            }
        });

        ItemProperties.register(ModItems.SACK, ThaiDelight.modid("fullness"), new ClampedItemPropertyFunction() {
            @Override
            public float unclampedCall(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int i) {
                return SackItem.isFull(itemStack) ? 1f : 0f;
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

        ClientPlayNetworking.registerGlobalReceiver(ModLevelEventPacket.TYPE,ModLevelEventPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(ThaiDelightConfigPacket.TYPE,ThaiDelightConfigPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(DurianHeatPacket.TYPE,DurianHeatPacket::recieve);
        ClientPlayNetworking.registerGlobalReceiver(SpicyPacket.TYPE,SpicyPacket::recieve);
    }

    static {
        CUTOUT = new Block[]{
                ModBlocks.SOMTAM_FEAST,
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
                ModBlocks.LARB_FEAST
        };
    }
}
