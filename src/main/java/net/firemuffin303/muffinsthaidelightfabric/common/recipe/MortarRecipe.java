package net.firemuffin303.muffinsthaidelightfabric.common.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class MortarRecipe implements Recipe<Container> {
    private final ResourceLocation id;
    private final String group;
    private final NonNullList<Ingredient> ingredients;
    private final ItemStack result;

    public MortarRecipe(ResourceLocation id,String group,NonNullList<Ingredient> ingredients,ItemStack result){
        this.id = id;
        this.group = group;
        this.ingredients = ingredients;
        this.result = result;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.ModSerializer.MORTAR_SERIALIZER;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.result;
    }

    public Ingredient getResult(){
        return Ingredient.of(this.result);
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.MORTAR;
    }


    @Override
    public boolean matches(Container container, Level level) {
        StackedContents stackedContents = new StackedContents();
        int i =0;
        for(int j = 0; j < container.getContainerSize(); j++){
            ItemStack itemStack = container.getItem(j);
            if(!itemStack.isEmpty()){
                stackedContents.accountStack(itemStack,1);
                ++i;
            }
        }
        return i == this.ingredients.size() && stackedContents.canCraft(this,null);
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        return this.getResultItem(registryAccess).copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return i * j >= this.ingredients.size();
    }



    public static class Serializer implements RecipeSerializer<MortarRecipe>{

        @Override
        public MortarRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
            String groupIn = GsonHelper.getAsString(jsonObject, "group", "");
            NonNullList<Ingredient> inputItemsIn = readIngredients(GsonHelper.getAsJsonArray(jsonObject, "ingredients"));

            if (inputItemsIn.isEmpty()) {
                throw new JsonParseException("No ingredients for mortar recipe");
            }else if(inputItemsIn.size() > 4){
                throw new JsonParseException("Too many ingredients for mortar recipe. Maximum at 4");
            }else{
                ItemStack results = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
                return new MortarRecipe(resourceLocation,groupIn,inputItemsIn,results);
            }
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
        public MortarRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
            String group = friendlyByteBuf.readUtf();
            int i = friendlyByteBuf.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(i,Ingredient.EMPTY);

            for(int j = 0; j < ingredients.size(); ++j){
                ingredients.set(j,Ingredient.fromNetwork(friendlyByteBuf));
            }

            ItemStack itemStack = friendlyByteBuf.readItem();
            return new MortarRecipe(resourceLocation,group,ingredients,itemStack);
        }


        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, MortarRecipe recipe) {
            friendlyByteBuf.writeUtf(recipe.group);
            friendlyByteBuf.writeVarInt(recipe.ingredients.size());

            for (Ingredient ingredient : recipe.ingredients) {
                ingredient.toNetwork(friendlyByteBuf);
            }

            friendlyByteBuf.writeItem(recipe.result);

        }
    }
}
