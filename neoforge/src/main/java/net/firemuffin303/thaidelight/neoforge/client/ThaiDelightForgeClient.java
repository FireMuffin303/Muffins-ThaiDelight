package net.firemuffin303.thaidelight.neoforge.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.client.ThaiDelightCommonClient;
import net.firemuffin303.thaidelight.client.model.armor.DurianHelmetModel;
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
import net.firemuffin303.thaidelight.neoforge.client.renderer.SackItemRenderer;
import net.firemuffin303.thaidelight.neoforge.network.DurianHeatPacket;
import net.firemuffin303.thaidelight.neoforge.network.SpicyPacket;
import net.firemuffin303.thaidelight.util.ModAnimationUtils;
import net.firemuffin303.thaidelight.util.ModUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.FoliageColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.MainThreadPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Mod(value = ThaiDelightCommon.MOD_ID,dist = Dist.CLIENT)
public class ThaiDelightForgeClient {


    public ThaiDelightForgeClient(IEventBus iEventBus, ModContainer modContainer){
        iEventBus.addListener(this::clientSetup);
        iEventBus.addListener(this::registerMenuScreen);
        iEventBus.addListener(this::registerEntityModelLayer);
        iEventBus.addListener(this::registerEntityRenderer);
        iEventBus.addListener(this::registerColorBlock);
        iEventBus.addListener(this::registerColorItem);
        iEventBus.addListener(this::registerRecipeBook);
        iEventBus.addListener(this::registerTooltipComponent);
        iEventBus.addListener(this::registerModel);
        iEventBus.addListener(this::registerAddLayer);
        iEventBus.addListener(this::registerPacket);
        iEventBus.addListener(this::registerClientExtensions);

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

        final RecipeBookCategories MORTAR_SEARCH = RecipeBookCategories.valueOf("MUFFINS_THAIDELIGHT_MORTAR_SEARCH");
        final RecipeBookCategories MORTAR_MEALS = RecipeBookCategories.valueOf("MUFFINS_THAIDELIGHT_MORTAR_MEALS");
        final RecipeBookCategories MORTAR_MISC = RecipeBookCategories.valueOf("MUFFINS_THAIDELIGHT_MORTAR_MISC");


        final RecipeBookType MORTAR_BOOK_TYPE = RecipeBookType.valueOf("MUFFINS_THAIDELIGHT_MORTAR_RECIPE_BOOK_TYPE");

        event.registerBookCategories(MORTAR_BOOK_TYPE, List.of(MORTAR_SEARCH,MORTAR_MEALS,MORTAR_MISC));
        event.registerAggregateCategory(MORTAR_SEARCH,List.of(MORTAR_MEALS,MORTAR_MISC));
        event.registerRecipeCategoryFinder(ModRecipes.MORTAR.get(),recipe -> {
            if(recipe.value() instanceof MortarRecipe mortarRecipe){
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

    public void registerTooltipComponent(RegisterClientTooltipComponentFactoriesEvent event){
        event.register(SackTooltipComponent.SackToolTip.class, SackTooltipComponent::new);
    }

    public void registerModel(ModelEvent.RegisterAdditional event){
        /*
        event.register(ThaiDelightCommonClient.SACK_MODEL);
        event.register(ThaiDelightCommonClient.FILLED_SACK_MODEL);
        event.register(ThaiDelightCommonClient.SACK_MODEL_IN_HAND);
        event.register(ThaiDelightCommonClient.FULL_SACK_MODEL_IN_HAND);

         */
    }

    public void registerAddLayer(EntityRenderersEvent.AddLayers event){
        event.getSkins().forEach(skinType -> {
            PlayerRenderer playerRenderer = event.getSkin(skinType);
            if(playerRenderer != null){
                playerRenderer.addLayer(new DurianHeatRendererLayer<>(playerRenderer));
            }
        });
    }

    private void registerPacket(RegisterPayloadHandlersEvent event){
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(DurianHeatPacket.TYPE,DurianHeatPacket.STREAM_CODEC,new MainThreadPayloadHandler<>((arg, iPayloadContext) -> DurianHeatPacket.handle(arg)));
        registrar.playToClient(SpicyPacket.TYPE,SpicyPacket.STREAM_CODEC,new MainThreadPayloadHandler<>((arg, iPayloadContext) -> SpicyPacket.handle(arg)));
    }

    private void registerClientExtensions(RegisterClientExtensionsEvent event){
        event.registerItem(new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if(itemStack.is(ModItems.DURIAN_HELMET.get()) && equipmentSlot == EquipmentSlot.HEAD){
                    ModelPart modelPart = Minecraft.getInstance().getEntityModels().bakeLayer(DurianHelmetModel.DURIAN_HELMET);
                    DurianHelmetModel<LivingEntity> model  = new DurianHelmetModel<>(modelPart);
                    model.setAllVisible(false);
                    model.head.visible = true;
                    return model;
                }

                return IClientItemExtensions.super.getHumanoidArmorModel(livingEntity, itemStack, equipmentSlot, original);
            }
        },ModItems.DURIAN_HELMET.get());

        event.registerItem(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new SackItemRenderer();
            }

            @Override
            public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
                if(player.isUsingItem() && itemInHand.is(ModItems.SACK.get())){
                    ModAnimationUtils.handleUsingCatchingBag(poseStack,arm,equipProcess);
                    return true;
                }

                return IClientItemExtensions.super.applyForgeHandTransform(poseStack, player, arm, itemInHand, partialTick, equipProcess, swingProcess);
            }

            @Override
            public HumanoidModel.@Nullable ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
                if(SackItem.isFull(itemStack)){
                    return HumanoidModel.ArmPose.valueOf("MUFFINS_THAIDELIGHT_SACK_SHOULDER_HOLD");
                }

                return HumanoidModel.ArmPose.valueOf("MUFFINS_THAIDELIGHT_CATCHING_BAG_HOLD");
            }
        },ModItems.SACK.get());
    }

}
