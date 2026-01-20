package net.firemuffin303.muffinsthaidelightfabric.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.client.sceens.MortarScreen;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.Objects;

public class ThaiDelightJEIIntegration implements IModPlugin {
    public static final RecipeType<FermentedFishRecipeCategory.FermentedFishDummy> FERMENTED_FISH = new RecipeType<>(ThaiDelight.modid("fermented_fish"), FermentedFishRecipeCategory.FermentedFishDummy.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(ThaiDelight.MOD_ID,"jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper iGuiHelper = registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(new MortarJEI(iGuiHelper));
        registration.addRecipeCategories(new FermentedFishRecipeCategory(iGuiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ClientLevel level = Objects.requireNonNull(Minecraft.getInstance().level);
        registration.addRecipes(MortarJEI.MORTAR,level.getRecipeManager().getAllRecipesFor(ModRecipes.MORTAR));
        registration.addRecipes(FERMENTED_FISH, List.of(new FermentedFishRecipeCategory.FermentedFishDummy()));
        //registration.addIngredientInfo(new ItemStack(ModBlocks.PAPAYA_LOG), VanillaTypes.ITEM_STACK, Component.translatable("jei.info.papaya_log"));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(MortarScreen.class,MortarScreen.CLICK_AREA.getX(),MortarScreen.CLICK_AREA.getY(),MortarScreen.CLICK_AREA.getWidth(),MortarScreen.CLICK_AREA.getHeight(),MortarJEI.MORTAR);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.MORTAR),MortarJEI.MORTAR);
        registration.addRecipeCatalyst(new ItemStack(Items.CAULDRON),FERMENTED_FISH);
    }
}
