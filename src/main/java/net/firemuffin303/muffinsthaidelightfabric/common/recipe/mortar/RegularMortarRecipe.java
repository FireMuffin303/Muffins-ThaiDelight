package net.firemuffin303.muffinsthaidelightfabric.common.recipe.mortar;

import com.mojang.logging.LogUtils;
import net.fabricmc.fabric.impl.renderer.SpriteFinderImpl;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class RegularMortarRecipe implements MortarRecipe{
    private final ResourceLocation id;
    private final String group;
    private final NonNullList<Ingredient> ingredients;
    private final ItemStack container;
    private final ItemStack result;
    private MortarRecipeBookTab mortarRecipeBookTab;

    public RegularMortarRecipe(ResourceLocation id, String group, NonNullList<Ingredient> ingredients,ItemStack container, ItemStack result,MortarRecipeBookTab mortarRecipeBookTab) {
        this.id = id;
        this.group = group;
        this.ingredients = ingredients;
        this.container = container;
        this.result = result;
        this.mortarRecipeBookTab = mortarRecipeBookTab;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.result;
    }

    public ItemStack getResult(){
        return this.result;
    }

    public ItemStack getContainer() {
        return container;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean matches(Container container, Level level) {
        StackedContents stackedContents = new StackedContents();
        int i =0;
        for(int j = 1; j < 5; j++){
            ItemStack itemStack = container.getItem(j);
            if(!itemStack.isEmpty()){
                stackedContents.accountStack(itemStack,1);
                ++i;
            }
        }

        return i == this.ingredients.size() && stackedContents.canCraft(this,null) && this.container.is(container.getItem(5).getItem());
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        return this.getResultItem(registryAccess).copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return i * j >= this.ingredients.size();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.ModSerializer.MORTAR_SERIALIZER;
    }

    @Override
    public MortarRecipeBookTab getRecipeBookTab() {
        return this.mortarRecipeBookTab;
    }
}
