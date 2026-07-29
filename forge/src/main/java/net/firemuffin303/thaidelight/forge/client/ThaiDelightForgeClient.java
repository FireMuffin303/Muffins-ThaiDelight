package net.firemuffin303.thaidelight.forge.client;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.client.ThaiDelightCommonClient;
import net.firemuffin303.thaidelight.client.renderer.DurianHeatRendererLayer;
import net.firemuffin303.thaidelight.client.renderer.component.SackTooltipComponent;
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
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.FoliageColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.jarjar.nio.util.Lazy;
import net.neoforged.neoforge.client.IArmPoseTransformer;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.common.NeoForge;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Mod(value = ThaiDelightCommon.MOD_ID,dist = Dist.CLIENT)
public class ThaiDelightForgeClient {
    public static final EnumProxy<HumanoidModel.ArmPose> PROXY_SACK_HOLD = new EnumProxy<>(HumanoidModel.ArmPose.class, true, (IArmPoseTransformer) (arg, arg2, arg3) -> {});
    public static final EnumProxy<HumanoidModel.ArmPose> PROXY_SACK_SHOULDER_HOLD = new EnumProxy<>(HumanoidModel.ArmPose.class, false, (IArmPoseTransformer) (arg, arg2, arg3) -> {});
    public static final EnumProxy<HumanoidModel.ArmPose> PROXY_SACK_SWING = new EnumProxy<>(HumanoidModel.ArmPose.class, true, (IArmPoseTransformer) (arg, arg2, arg3) -> {});
    public static final EnumProxy<RecipeBookCategories> PROXY_MORTAR_SEARCH = new EnumProxy<>(RecipeBookCategories.class, Lazy.of(List.of(new ItemStack(Items.COMPASS))));
    public static final EnumProxy<RecipeBookCategories> PROXY_MORTAR_MEALS = new EnumProxy<>(RecipeBookCategories.class, Lazy.of(List.of(new ItemStack(ModItems.SOMTAM_FEAST.get()))));
    public static final EnumProxy<RecipeBookCategories> PROXY_MORTAR_MISC = new EnumProxy<>(RecipeBookCategories.class, Lazy.of(List.of(new ItemStack(ModItems.PEPPER.get()),new ItemStack(Items.BONE_MEAL))));

    public ThaiDelightForgeClient(IEventBus iEventBus){
        NeoForge.EVENT_BUS.addListener(this::clientSetup);

        iEventBus.addListener(this::registerMenuScreen);
        iEventBus.addListener(this::registerEntityModelLayer);
        iEventBus.addListener(this::registerEntityRenderer);
        iEventBus.addListener(this::registerColorBlock);
        iEventBus.addListener(this::registerColorItem);
        iEventBus.addListener(this::registerRecipeBook);
        iEventBus.addListener(this::registerTooltipComponent);
        iEventBus.addListener(this::registerModel);
        iEventBus.addListener(this::registerAddLayer);

    }

    public void clientSetup(FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            ThaiDelightCommonClient.registerCustomEffectRenderer();
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

            //ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class,(screen) -> AutoConfig.getConfigScreen(ModConfig.class,screen).get());


        });



    }


    public void registerMenuScreen(RegisterMenuScreensEvent event){
        event.register(ModMenuType.MORTAR.get(), MortarScreen::new);
    }

    public void registerEntityModelLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        ThaiDelightCommonClient.entityModelRegister(event::registerLayerDefinition);
    }

    public  void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event){
        ThaiDelightCommonClient.entityRendererRegister(event::registerEntityRenderer);
        ThaiDelightCommonClient.blockEntityRenderRegister(event::registerBlockEntityRenderer);
    }

    public void registerColorBlock(RegisterColorHandlersEvent.Block event){
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

    public void registerColorItem(RegisterColorHandlersEvent.Item event){
        event.register((itemStack, i) -> i > 0 ? -1 : ModUtils.getColor(itemStack), ModItems.COCONUT_MILK_ICE_CREAM.get());
        event.register((itemStack, i) -> ModUtils.getColor(itemStack), ModItems.KHANOM_CHAN.get());
        event.register((itemStack, i) -> FoliageColor.getDefaultColor(), ModItems.DURIAN_LEAVES.get(),ModItems.MANGO_LEAVES.get());
    }

    public void registerRecipeBook(RegisterRecipeBookCategoriesEvent event){

        final RecipeBookType MORTAR_BOOK_TYPE = RecipeBookType.valueOf("muffins_thaidelight_MORTAR_RECIPE_BOOK_TYPE");

        event.registerBookCategories(MORTAR_BOOK_TYPE, List.of(PROXY_MORTAR_SEARCH.getValue(),PROXY_MORTAR_MEALS.getValue(),PROXY_MORTAR_MISC.getValue()));
        event.registerAggregateCategory(PROXY_MORTAR_SEARCH.getValue(),List.of(PROXY_MORTAR_MEALS.getValue(),PROXY_MORTAR_MISC.getValue()));
        event.registerRecipeCategoryFinder(ModRecipes.MORTAR.get(),recipe -> {
            if(recipe.value() instanceof MortarRecipe mortarRecipe){
                MortarRecipeBookTab mortarRecipeBookTab = mortarRecipe.getRecipeBookTab();
                if(mortarRecipeBookTab != null){
                    return switch (mortarRecipeBookTab){
                        case MEALS -> PROXY_MORTAR_MEALS.getValue();
                        case MISC -> PROXY_MORTAR_MISC.getValue();
                    };
                }
            }

            return PROXY_MORTAR_MISC.getValue();
        });
    }

    public void registerTooltipComponent(RegisterClientTooltipComponentFactoriesEvent event){
        event.register(SackTooltipComponent.SackToolTip.class, SackTooltipComponent::new);
    }

    public void registerModel(ModelEvent.RegisterAdditional event){
        event.register(ThaiDelightCommonClient.SACK_MODEL);
        event.register(ThaiDelightCommonClient.FILLED_SACK_MODEL);
        event.register(ThaiDelightCommonClient.SACK_MODEL_IN_HAND);
        event.register(ThaiDelightCommonClient.FULL_SACK_MODEL_IN_HAND);
    }

    public void registerAddLayer(EntityRenderersEvent.AddLayers event){
        event.getSkins().forEach(skinType -> {
            PlayerRenderer playerRenderer = event.getSkin(skinType);
            if(playerRenderer != null){
                playerRenderer.addLayer(new DurianHeatRendererLayer<>(playerRenderer));
            }
        });
    }
}
