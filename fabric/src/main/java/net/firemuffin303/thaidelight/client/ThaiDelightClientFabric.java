package net.firemuffin303.thaidelight.client;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import io.github.fabricators_of_create.porting_lib.recipe_book_categories.RecipeBookRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.client.renderer.armor.DurianHelmetRenderer;
import net.firemuffin303.thaidelight.client.renderer.component.SackTooltipComponent;
import net.firemuffin303.thaidelight.client.sceens.MortarScreen;
import net.firemuffin303.thaidelight.common.entity.DragonflyEntity;
import net.firemuffin303.thaidelight.common.item.DragonflyBottleItem;
import net.firemuffin303.thaidelight.common.item.SackItem;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipe;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipeBookTab;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModMenuType;
import net.firemuffin303.thaidelight.common.registry.ModRecipes;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.model.Material;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ThaiDelightClientFabric implements ClientModInitializer {

    public static final RecipeBookType MORTAR_RECIPE_BOOK_TYPE = RecipeBookType.valueOf("MORTAR_RECIPE_BOOK_TYPE");
    public static final RecipeBookCategories MORTAR_SEARCH = RecipeBookCategories.valueOf("MORTAR_SEARCH");
    public static final RecipeBookCategories MORTAR_MEALS = RecipeBookCategories.valueOf("MORTAR_MEALS");
    public static final RecipeBookCategories MORTAR_MISC = RecipeBookCategories.valueOf("MORTAR_MISC");


    @Override
    public void onInitializeClient() {
        ThaiDelightCommonClient.entityRendererRegister(EntityRendererRegistry::register);
        ThaiDelightCommonClient.entityModelRegister(((modelLayer, provider) -> EntityModelLayerRegistry.registerModelLayer(modelLayer, provider::get)));
        ThaiDelightCommonClient.blockEntityRenderRegister(BlockEntityRenderers::register);

        TerraformBoatClientHelper.registerModelLayers(ThaiDelightCommon.modid("durian_boat"),false);
        TerraformBoatClientHelper.registerModelLayers(ThaiDelightCommon.modid("coconut_boat"),false);
        TerraformBoatClientHelper.registerModelLayers(ThaiDelightCommon.modid("mango_boat"),false);

        ArmorRenderer.register(new DurianHelmetRenderer(), ModItems.DURIAN_HELMET.get());

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

        TooltipComponentCallback.EVENT.register(new TooltipComponentCallback() {
            @Override
            public @Nullable ClientTooltipComponent getComponent(TooltipComponent data) {
                if(data instanceof SackTooltipComponent.SackToolTip sackTooltipComponent){
                    return new SackTooltipComponent(sackTooltipComponent);
                }
                return null;
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
}
