package net.firemuffin303.thaidelight.common.recipe.mortar;

import com.google.gson.*;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class MortarSerializer implements RecipeSerializer<RegularMortarRecipe> {
    @Override
    public RegularMortarRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
        String groupIn = GsonHelper.getAsString(jsonObject, "group", "");
        NonNullList<Ingredient> inputItemsIn = readIngredients(GsonHelper.getAsJsonArray(jsonObject, "ingredients"));
        ItemStack container = GsonHelper.isValidNode(jsonObject,"container") ? ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject,"container")) : ItemStack.EMPTY;

        if (inputItemsIn.isEmpty()) {
            throw new JsonParseException("No ingredients for mortar recipe");
        }else if(inputItemsIn.size() > 4){
            throw new JsonParseException("Too many ingredients for mortar recipe. Maximum at 4");
        }

        String tab = GsonHelper.getAsString(jsonObject,"recipe_book_tab",null);
        MortarRecipeBookTab mortarRecipeBookTab = MortarRecipeBookTab.findByName(tab);

        ItemStack results = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
        return new RegularMortarRecipe(resourceLocation,groupIn,inputItemsIn,container,results,mortarRecipeBookTab);
    }

    private static NonNullList<Ingredient> readIngredients(JsonArray ingredientArray) {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();

        for(int i = 0; i < ingredientArray.size(); ++i) {
            Ingredient ingredient = Ingredient.fromJson(ingredientArray.get(i),false);
            if (!ingredient.isEmpty()) {
                nonnulllist.add(ingredient);
            }
        }

        return nonnulllist;
    }

    @Override
    public RegularMortarRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
        String group = friendlyByteBuf.readUtf();
        int i = friendlyByteBuf.readVarInt();
        NonNullList<Ingredient> ingredients = NonNullList.withSize(i,Ingredient.EMPTY);

        for(int j = 0; j < ingredients.size(); ++j){
            ingredients.set(j,Ingredient.fromNetwork(friendlyByteBuf));
        }

        ItemStack container = friendlyByteBuf.readItem();
        ItemStack resultItem = friendlyByteBuf.readItem();
        MortarRecipeBookTab mortarRecipeBookTab = MortarRecipeBookTab.findByName(friendlyByteBuf.readUtf());

        return new RegularMortarRecipe(resourceLocation,group,ingredients,container,resultItem,mortarRecipeBookTab);
    }


    @Override
    public void toNetwork(FriendlyByteBuf friendlyByteBuf, RegularMortarRecipe recipe) {
        friendlyByteBuf.writeUtf(recipe.getGroup());
        friendlyByteBuf.writeVarInt(recipe.getIngredients().size());

        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredient.toNetwork(friendlyByteBuf);
        }

        friendlyByteBuf.writeItem(recipe.getContainer());
        friendlyByteBuf.writeItem(recipe.getResult());
        friendlyByteBuf.writeUtf(recipe.getRecipeBookTab().name);

    }
}
