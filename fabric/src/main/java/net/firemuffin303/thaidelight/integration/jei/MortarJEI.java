package net.firemuffin303.thaidelight.integration.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.client.sceens.MortarScreen;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipe;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class MortarJEI implements IRecipeCategory<MortarRecipe> {
    private static final ResourceLocation ID = ThaiDelightCommon.modid("mortar");
    public static final RecipeType<MortarRecipe> MORTAR = new RecipeType<>(ID,MortarRecipe.class);

    private final IDrawable icon;
    private final IDrawable background;

    public MortarJEI(IGuiHelper helper){
        this.icon = helper.createDrawableItemStack(new ItemStack(ModBlocks.MORTAR.get()));
        this.background = helper.createDrawable(MortarScreen.CRAFTING_TABLE_LOCATION,4,4,169,70);
    }

    @Override
    public RecipeType<MortarRecipe> getRecipeType() {
        return MORTAR;
    }

    @Override
    public Component getTitle() {
        return ModBlocks.MORTAR.get().getName();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MortarRecipe recipe, IFocusGroup focuses) {
        builder.addInvisibleIngredients(RecipeIngredientRole.CATALYST).addIngredients(Ingredient.of(ModBlocks.MORTAR.get()));
        builder.addSlot(RecipeIngredientRole.OUTPUT,120,31).addIngredients(Ingredient.of(recipe.getResult()));
        for(int i = 0;i < recipe.getIngredients().size();i++){

            builder.addSlot(RecipeIngredientRole.INPUT,i > 1 ? 35 + Mth.clamp(i-2,0,1) *18 : 35+ i *18,i > 1 ? 22+18: 22).addIngredients(recipe.getIngredients().get(i));
        }

        builder.addSlot(RecipeIngredientRole.INPUT,80,50).addIngredients(Ingredient.of(recipe.getContainer()));
    }

    @Override
    public void draw(MortarRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        //guiGraphics.blit(MortarScreen.CRAFTING_TABLE_LOCATION,4,4,4,4,169,70);
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
    }
}
