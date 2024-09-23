package net.firemuffin303.thaidelight.common.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.ints.IntList;
import net.firemuffin303.thaidelight.common.registry.ModRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.Iterator;

public class MortarRecipe implements Recipe<CraftingInput> {
    final String group;
    final NonNullList<Ingredient> ingredients;
    final ItemStack result;

    public MortarRecipe(String group,NonNullList<Ingredient> ingredients,ItemStack result){
        this.group = group;
        this.ingredients = ingredients;
        this.result = result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.ModRecipeSerializer.MORTAR_SERIALIZER;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
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
        return ModRecipes.MORTAR.get();
    }


    @Override
    public boolean matches(CraftingInput craftingInput, Level level) {
        if(craftingInput.ingredientCount() != this.ingredients.size()){
            return false;
        }

        return craftingInput.size() == 1 && this.ingredients.size() == 1 ?
                ((Ingredient)this.ingredients.getFirst()).test(craftingInput.getItem(0)) :
                craftingInput.stackedContents().canCraft(this, (IntList)null);
    }


    @Override
    public ItemStack assemble(CraftingInput recipeInput, HolderLookup.Provider provider) {
        return this.getResultItem(provider).copy();
    }



    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return i * j >= this.ingredients.size();
    }



    public static class Serializer implements RecipeSerializer<MortarRecipe>{

        public static final MapCodec<MortarRecipe> CODEC = RecordCodecBuilder.mapCodec((mortarRecipeInstance) ->{
           return mortarRecipeInstance.group(
                   Codec.STRING.optionalFieldOf("group","").forGetter(mortarRecipe -> {
                       return mortarRecipe.group;
                   }),
                   Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").flatXmap((list) ->{
                       Ingredient[] ingredients = (Ingredient[])list.stream().filter((ingredient) -> {
                           return !ingredient.isEmpty();
                       }).toArray((i) -> {
                           return new Ingredient[i];
                       });
                       if (ingredients.length == 0) {
                           return DataResult.error(() -> {
                               return "No ingredients for shapeless recipe";
                           });
                       } else {
                           return ingredients.length > 4 ? DataResult.error(() -> {
                               return "Too many ingredients for shapeless recipe";
                           }) : DataResult.success(NonNullList.of(Ingredient.EMPTY, ingredients));
                       }
                   }, DataResult::success).forGetter(mortarRecipe -> mortarRecipe.ingredients),
                   ItemStack.STRICT_CODEC.fieldOf("result").forGetter(mortarRecipe -> mortarRecipe.result)
           ).apply(mortarRecipeInstance,MortarRecipe::new);
        });

        public static final StreamCodec<RegistryFriendlyByteBuf,MortarRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork,Serializer::fromNetwork);

        public static MortarRecipe fromNetwork(RegistryFriendlyByteBuf registryFriendlyByteBuf) {
            String group = registryFriendlyByteBuf.readUtf();
            int i = registryFriendlyByteBuf.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(i,Ingredient.EMPTY);
            ingredients.replaceAll((ingredient) -> Ingredient.CONTENTS_STREAM_CODEC.decode(registryFriendlyByteBuf));

            ItemStack itemStack = ItemStack.STREAM_CODEC.decode(registryFriendlyByteBuf);
            return new MortarRecipe(group,ingredients,itemStack);
        }


        public static void toNetwork(RegistryFriendlyByteBuf registryFriendlyByteBuf, MortarRecipe recipe) {
            registryFriendlyByteBuf.writeUtf(recipe.group);
            registryFriendlyByteBuf.writeVarInt(recipe.ingredients.size());
            for (Ingredient ingredient : recipe.ingredients) {
                ingredient.CONTENTS_STREAM_CODEC.encode(registryFriendlyByteBuf,ingredient);
            }

            ItemStack.STREAM_CODEC.encode(registryFriendlyByteBuf,recipe.result);

        }

        @Override
        public MapCodec<MortarRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, MortarRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
