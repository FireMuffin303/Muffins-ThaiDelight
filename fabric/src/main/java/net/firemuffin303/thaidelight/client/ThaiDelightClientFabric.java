package net.firemuffin303.thaidelight.client;

import com.mojang.datafixers.util.Pair;
import io.github.fabricators_of_create.porting_lib.recipe_book_categories.RecipeBookRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.asm.ModASMEarlyRiser;
import net.firemuffin303.thaidelight.client.renderer.DurianHeatRendererLayer;
import net.firemuffin303.thaidelight.client.renderer.armor.DurianHelmetRenderer;
import net.firemuffin303.thaidelight.client.renderer.component.SackTooltipComponent;
import net.firemuffin303.thaidelight.client.renderer.item.SackItemRenderer;
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
import net.firemuffin303.thaidelight.mixin.fabric.recipeBookType.RecipeBookSettingsAccessor;
import net.firemuffin303.thaidelight.network.ModLevelEventPacket;
import net.firemuffin303.thaidelight.util.ModUtils;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.stats.RecipeBookSettings;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ThaiDelightClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ThaiDelightCommonClient.entityRendererRegister(EntityRendererRegistry::register);
        ThaiDelightCommonClient.entityModelRegister(((modelLayer, provider) -> EntityModelLayerRegistry.registerModelLayer(modelLayer, provider::get)));
        ThaiDelightCommonClient.blockEntityRenderRegister(BlockEntityRenderers::register);
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.SACK.get(),new SackItemRenderer());

        ArmorRenderer.register(new DurianHelmetRenderer(), ModItems.DURIAN_HELMET.get());

        ThaiDelightCommonClient.registerCustomEffectRenderer();

        Supplier<MenuType<MortarMenu>> menuTypeSupplier = (Supplier<MenuType<MortarMenu>>) (Supplier<?>) ModMenuType.MORTAR;
        MenuScreens.register(menuTypeSupplier.get(), MortarScreen::new);

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

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),ThaiDelightCommonClient.CUTOUT.stream().map(Supplier::get).toArray(Block[]::new));

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

        ClientPlayNetworking.registerGlobalReceiver(ModLevelEventPacket.TYPE, ModLevelEventPacket::receive);
    }


    public static void registerRecipe(){
        RecipeBookType recipeBookType = ModASMEarlyRiser.getMortarBookType();
        RecipeBookCategories mortarSearch = ModASMEarlyRiser.getMortarSearchCategory();
        RecipeBookCategories mortarMeals = ModASMEarlyRiser.getMortarMealsCategory();
        RecipeBookCategories mortarMisc = ModASMEarlyRiser.getMortarMiscCategory();

        RecipeBookRegistry.registerBookCategories(recipeBookType, List.of(mortarSearch,mortarMeals,mortarMisc));
        RecipeBookRegistry.registerAggregateCategory(mortarSearch,List.of(mortarMeals,mortarMisc));
        RecipeBookRegistry.registerRecipeCategoryFinder(ModRecipes.MORTAR.get(), recipe -> {
            if(recipe instanceof MortarRecipe mortarRecipe){
                MortarRecipeBookTab mortarRecipeBookTab = mortarRecipe.getRecipeBookTab();
                if(mortarRecipeBookTab != null){
                    return switch (mortarRecipeBookTab){
                        case MEALS -> mortarMeals;
                        case MISC -> mortarMisc;
                    };
                }
            }

            return mortarMisc;
        });

        Map<RecipeBookType, Pair<String, String>> newMap = new HashMap<>(RecipeBookSettingsAccessor.getTagField());
        newMap.put(recipeBookType, Pair.of("isMortarBookGuiOpen", "isMortarBookFilteringCraftable"));
        RecipeBookSettingsAccessor.setTagFields(newMap);

    }
}
