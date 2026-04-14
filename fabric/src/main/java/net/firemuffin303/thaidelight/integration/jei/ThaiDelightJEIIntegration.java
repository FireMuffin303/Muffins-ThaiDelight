package net.firemuffin303.thaidelight.integration.jei;

import com.google.common.collect.ImmutableList;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.client.sceens.MortarScreen;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModRecipes;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.Objects;

import static net.firemuffin303.thaidelight.integration.jei.FermentedFishRecipeCategory.translateKey;

public class ThaiDelightJEIIntegration implements IModPlugin {
    public static final RecipeType<FermentedFishRecipeCategory.FermentedFishDummy> FERMENTED_FISH = new RecipeType<>(ThaiDelightCommon.modid("fermented_fish"), FermentedFishRecipeCategory.FermentedFishDummy.class);

    @Override
    public ResourceLocation getPluginUid() {
        return ThaiDelightCommon.modid("jei");
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
        registration.addRecipes(MortarJEI.MORTAR,level.getRecipeManager().getAllRecipesFor(ModRecipes.MORTAR.get()));
        registration.addRecipes(FERMENTED_FISH, List.of(
                new FermentedFishRecipeCategory.FermentedFishDummy(ModTags.COMMON_RAW_FISHES,Items.BOWL, ModItems.FERMENTED_FISH.get(),new FermentedFishRecipeCategory.FermentedFishCauldronDrawable()){
                    @Override
                    public void renderExtra(GuiGraphics guiGraphics, IDrawable timeIcon, IDrawable slot) {
                        timeIcon.draw(guiGraphics,68,2);
                    }

                    @Override
                    public List<Component> tooltip() {
                        return ImmutableList.of(translateKey("fermented_fish1"),translateKey("fermented_fish2"),translateKey("fermented_fish3"));
                    }
                },
                new FermentedFishRecipeCategory.FermentedFishDummy(ModTags.COCONUT,Items.GLASS_BOTTLE,ModItems.COCONUT_MILK_BOTTLE.get(),new FermentedFishRecipeCategory.CoconutCauldronDrawable()){
                    @Override
                    public void renderExtra(GuiGraphics guiGraphics, IDrawable timeIcon, IDrawable slot) {
                        slot.draw(guiGraphics,67,3);
                    }

                    @Override
                    public ItemStack extraIngredient() {
                        return new ItemStack(Items.WATER_BUCKET);
                    }

                    @Override
                    public List<Component> tooltip() {
                        return ImmutableList.of(translateKey("coconut_milk_bottle"));
                    }
                }
        ));
        //registration.addIngredientInfo(new ItemStack(ModBlocks.PAPAYA_LOG), VanillaTypes.ITEM_STACK, Component.translatable("jei.info.papaya_log"));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(MortarScreen.class,MortarScreen.CLICK_AREA.getX(), MortarScreen.CLICK_AREA.getY(),MortarScreen.CLICK_AREA.getWidth(),MortarScreen.CLICK_AREA.getHeight(),MortarJEI.MORTAR);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.MORTAR.get()),MortarJEI.MORTAR);
        registration.addRecipeCatalyst(new ItemStack(Items.CAULDRON),FERMENTED_FISH);
    }
}
