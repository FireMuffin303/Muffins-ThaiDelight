package net.firemuffin303.thaidelight.forge.client;

import com.mojang.logging.LogUtils;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.client.ThaiDelightCommonClient;
import net.firemuffin303.thaidelight.client.renderer.DurianHeatRendererLayer;
import net.firemuffin303.thaidelight.client.renderer.component.SackTooltipComponent;
import net.firemuffin303.thaidelight.client.sceens.MortarScreen;
import net.firemuffin303.thaidelight.common.block.cauldron.FermentedFishCauldronBlock;
import net.firemuffin303.thaidelight.common.entity.DragonflyEntity;
import net.firemuffin303.thaidelight.common.item.DragonflyBottleItem;
import net.firemuffin303.thaidelight.common.item.SackItem;
import net.firemuffin303.thaidelight.common.menu.MortarMenu;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipe;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipeBookTab;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModMenuType;
import net.firemuffin303.thaidelight.common.registry.ModRecipes;
import net.firemuffin303.thaidelight.util.ModUtils;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.FoliageColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = ThaiDelightCommon.MOD_ID,value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ThaiDelightForgeClient {
    public static final HumanoidModel.ArmPose SACK_HOLD = HumanoidModel.ArmPose.create("CATCHING_BAG_HOLD", true, (arg, arg2, arg3) -> {});
    public static final HumanoidModel.ArmPose SACK_SHOULDER_HOLD = HumanoidModel.ArmPose.create("SACK_SHOULDER_HOLD", false, (arg, arg2, arg3) -> {});
    public static final HumanoidModel.ArmPose SACK_SWING = HumanoidModel.ArmPose.create("CATCHING_BAG_SWING", true, (arg, arg2, arg3) -> {});

    public ThaiDelightForgeClient(){
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            ThaiDelightCommonClient.registerCustomEffectRenderer();
            Supplier<MenuType<MortarMenu>> menuTypeSupplier = (Supplier<MenuType<MortarMenu>>) (Supplier<?>) ModMenuType.MORTAR;
            MenuScreens.register(menuTypeSupplier.get(), MortarScreen::new);
            ItemProperties.register(ModItems.SACK.get(), ThaiDelightCommon.modid("fullness"), new ClampedItemPropertyFunction() {
                @Override
                public float unclampedCall(ItemStack arg, @Nullable ClientLevel arg2, @Nullable LivingEntity arg3, int i) {
                    return SackItem.isFull(arg) ? 1f : 0f;
                }
            });

            ItemProperties.register(ModItems.DRAGONFLY_BOTTLE.get(), ThaiDelightCommon.modid("variant"), new ClampedItemPropertyFunction() {
                @Override
                public float unclampedCall(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int i) {
                    return ((float) DragonflyBottleItem.getVariant(itemStack)) / ((float) DragonflyEntity.DragonflyVariant.values().length);
                }
            });

            ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,() -> new ConfigScreenHandler.ConfigScreenFactory((minecraft, screen) -> {
                return
            }));

        });

    }

    @SubscribeEvent
    public static void registerEntityModelLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        ThaiDelightCommonClient.entityModelRegister(event::registerLayerDefinition);
    }

    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event){
        ThaiDelightCommonClient.entityRendererRegister(event::registerEntityRenderer);
        ThaiDelightCommonClient.blockEntityRenderRegister(event::registerBlockEntityRenderer);
    }

    @SubscribeEvent
    public static void registerColorBlock(RegisterColorHandlersEvent.Block event){
        event.register((blockState, blockAndTintGetter, blockPos, i) -> {
            if(blockAndTintGetter != null && blockPos != null && blockState.getValue(FermentedFishCauldronBlock.FERMENT) == 0){
                return BiomeColors.getAverageWaterColor(blockAndTintGetter,blockPos);
            }
            return 0xFFFFFF;
        }, ModBlocks.FERMENTED_FISH_CAULDRON.get());

        event.register((blockState, blockAndTintGetter, blockPos, i) -> {
            if(blockAndTintGetter != null && blockPos != null){
                return BiomeColors.getAverageFoliageColor(blockAndTintGetter,blockPos);
            }
            return FoliageColor.getDefaultColor();
        },ModBlocks.DURIAN_LEAVES.get(),ModBlocks.MANGO_LEAVES.get());
    }

    @SubscribeEvent
    public static void registerColorItem(RegisterColorHandlersEvent.Item event){
        event.register((itemStack, i) -> i > 0 ? -1 : ModUtils.getColor(itemStack), ModItems.COCONUT_MILK_ICE_CREAM.get());
        event.register((itemStack, i) -> ModUtils.getColor(itemStack), ModItems.KHANOM_CHAN.get());
        event.register((itemStack, i) -> FoliageColor.getDefaultColor(), ModItems.DURIAN_LEAVES.get(),ModItems.MANGO_LEAVES.get());
    }

    @SubscribeEvent
    public static void registerRecipeBook(RegisterRecipeBookCategoriesEvent event){

        final RecipeBookType MORTAR_BOOK_TYPE = RecipeBookType.create("MORTAR_RECIPE_BOOK_TYPE");
        final RecipeBookCategories MORTAR_SEARCH = RecipeBookCategories.create("MORTAR_SEARCH",new ItemStack(Items.COMPASS));
        final RecipeBookCategories MORTAR_MEALS = RecipeBookCategories.create("MORTAR_MEALS",new ItemStack(ModItems.SOMTAM_FEAST.get()));
        final RecipeBookCategories MORTAR_MISC = RecipeBookCategories.create("MORTAR_MISC",new ItemStack(ModItems.PEPPER.get()),new ItemStack(Items.BONE_MEAL));


        event.registerBookCategories(MORTAR_BOOK_TYPE, List.of(MORTAR_SEARCH,MORTAR_MEALS,MORTAR_MISC));
        event.registerAggregateCategory(MORTAR_SEARCH,List.of(MORTAR_MEALS,MORTAR_MISC));
        event.registerRecipeCategoryFinder(ModRecipes.MORTAR.get(),recipe -> {
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

    @SubscribeEvent
    public static void registerTooltipComponent(RegisterClientTooltipComponentFactoriesEvent event){
        event.register(SackTooltipComponent.SackToolTip.class, SackTooltipComponent::new);
    }

    @SubscribeEvent
    public static void registerModel(ModelEvent.RegisterAdditional event){
        event.register(ThaiDelightCommonClient.SACK_MODEL);
        event.register(ThaiDelightCommonClient.FILLED_SACK_MODEL);
        event.register(ThaiDelightCommonClient.SACK_MODEL_IN_HAND);
        event.register(ThaiDelightCommonClient.FULL_SACK_MODEL_IN_HAND);
    }

    @SubscribeEvent
    public static void registerAddLayer(EntityRenderersEvent.AddLayers event){
        event.getSkins().forEach(skinType -> {
            var playerRenderer = event.getSkin(skinType);
            if(playerRenderer != null){
                playerRenderer.addLayer(new DurianHeatRendererLayer(playerRenderer));
            }
        });
    }
}
