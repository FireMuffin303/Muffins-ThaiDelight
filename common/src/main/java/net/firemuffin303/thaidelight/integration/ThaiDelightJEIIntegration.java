package net.firemuffin303.thaidelight.integration;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.client.screens.MortarScreen;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.apache.http.util.TextUtils;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class ThaiDelightJEIIntegration implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(ThaiDelight.MOD_ID,"jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper iGuiHelper = registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(new MortarJEI(iGuiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ClientLevel level = Objects.requireNonNull(Minecraft.getInstance().level);
        registration.addRecipes(MortarJEI.MORTAR,level.getRecipeManager().getAllRecipesFor(ModRecipes.MORTAR));
        registration.addIngredientInfo(new ItemStack(ModBlocks.PAPAYA_LOG), VanillaTypes.ITEM_STACK, Component.translatable("jei.info.papaya_log"));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        //registration.addRecipeClickArea(MortarScreen.class,MortarScreen.CLICK_AREA.getX(),MortarScreen.CLICK_AREA.getY(),MortarScreen.CLICK_AREA.getWidth(),MortarScreen.CLICK_AREA.getHeight(),MortarJEI.MORTAR);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.MORTAR),MortarJEI.MORTAR);
    }
}
