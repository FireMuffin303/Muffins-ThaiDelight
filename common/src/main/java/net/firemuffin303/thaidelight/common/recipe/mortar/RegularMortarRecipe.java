package net.firemuffin303.thaidelight.common.recipe.mortar;

import net.firemuffin303.thaidelight.common.menu.MortarInput;
import net.firemuffin303.thaidelight.common.registry.ModRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class RegularMortarRecipe implements MortarRecipe{
    protected final String group;
    protected final NonNullList<Ingredient> ingredients;
    protected final ItemStack container;
    protected final ItemStack result;
    protected MortarRecipeBookTab mortarRecipeBookTab;

    public RegularMortarRecipe(String group, NonNullList<Ingredient> ingredients,ItemStack container, ItemStack result,MortarRecipeBookTab mortarRecipeBookTab) {
        this.group = group;
        this.ingredients = ingredients;

        if(container.isEmpty()){
            this.container = ItemStack.EMPTY;
        }else{
            this.container = container;
        }
        this.result = result;
        this.mortarRecipeBookTab = mortarRecipeBookTab;
    }


    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.result;
    }

    public ItemStack getResult(){
        return this.result;
    }

    public ItemStack getContainer() {
        return this.container;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean matches(MortarInput recipeInput, Level level) {
        if(recipeInput.itemStacks().size() != this.ingredients.size()){
            return false;
        }

        StackedContents stackedContents = new StackedContents();
        int i =0;
        for(int j = 0; j < 4; j++){
            ItemStack itemStack = recipeInput.getItem(j);
            if(!itemStack.isEmpty()){
                stackedContents.accountStack(itemStack,1);
                ++i;
            }
        }

        return i == this.ingredients.size() && stackedContents.canCraft(this,null) && this.container.is(recipeInput.getItem(4).getItem());
    }

    @Override
    public ItemStack assemble(MortarInput recipeInput, HolderLookup.Provider provider) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return i * j >= this.ingredients.size();
    }


    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.MORTAR_SERIALIZER.get();
    }

    @Override
    public MortarRecipeBookTab getRecipeBookTab() {
        return this.mortarRecipeBookTab;
    }
}
