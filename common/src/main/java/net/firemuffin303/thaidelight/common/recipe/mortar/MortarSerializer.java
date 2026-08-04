package net.firemuffin303.thaidelight.common.recipe.mortar;

import com.google.gson.*;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class MortarSerializer implements RecipeSerializer<RegularMortarRecipe> {
    private static final MapCodec<RegularMortarRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
       return instance.group(
               Codec.STRING.fieldOf("group").forGetter(recipe -> recipe.group),
               Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").flatXmap(list -> {
                   Ingredient[] ingredients = list.stream().filter(ingredient -> !ingredient.isEmpty()).toArray(Ingredient[]::new);
                   if(ingredients.length == 0){
                       return DataResult.error(() -> "No ingredients for Mortar recipe!");
                   } else if (ingredients.length > 4) {
                       return DataResult.error(() -> "Too many ingredients for Mortar recipe!");
                   }

                   return DataResult.success(NonNullList.of(Ingredient.EMPTY,ingredients));
               },DataResult::success).forGetter(recipe -> recipe.ingredients),
               ItemStack.STRICT_CODEC.optionalFieldOf("container",ItemStack.EMPTY).forGetter(recipe -> recipe.container),
               ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
               MortarRecipeBookTab.CODEC.fieldOf("category").orElse(MortarRecipeBookTab.MISC).forGetter(recipe -> recipe.mortarRecipeBookTab)
       ).apply(instance,RegularMortarRecipe::new);
    });

    public static final StreamCodec<RegistryFriendlyByteBuf, RegularMortarRecipe> STREAM_CODEC = StreamCodec.of(MortarSerializer::toNetwork, MortarSerializer::fromNetwork);

    @Override
    public MapCodec<RegularMortarRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, RegularMortarRecipe> streamCodec() {
        return STREAM_CODEC;
    }

    private static RegularMortarRecipe fromNetwork(RegistryFriendlyByteBuf registryFriendlyByteBuf) {
        String group = registryFriendlyByteBuf.readUtf();
        MortarRecipeBookTab mortarRecipeBookTab = registryFriendlyByteBuf.readEnum(MortarRecipeBookTab.class);
        int i = registryFriendlyByteBuf.readVarInt();
        NonNullList<Ingredient> nonNullList = NonNullList.withSize(i, Ingredient.EMPTY);
        nonNullList.replaceAll((ingredient) -> {
            return (Ingredient)Ingredient.CONTENTS_STREAM_CODEC.decode(registryFriendlyByteBuf);
        });
        ItemStack container = ItemStack.OPTIONAL_STREAM_CODEC.decode(registryFriendlyByteBuf);
        ItemStack result = ItemStack.STREAM_CODEC.decode(registryFriendlyByteBuf);
        return new RegularMortarRecipe(group,nonNullList, container,result,mortarRecipeBookTab);
    }

    private static void toNetwork(RegistryFriendlyByteBuf registryFriendlyByteBuf, RegularMortarRecipe mortarRecipe) {
        registryFriendlyByteBuf.writeUtf(mortarRecipe.group);
        registryFriendlyByteBuf.writeEnum(mortarRecipe.mortarRecipeBookTab);
        registryFriendlyByteBuf.writeVarInt(mortarRecipe.ingredients.size());

        for (Ingredient ingredient : mortarRecipe.ingredients) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(registryFriendlyByteBuf, ingredient);
        }

        ItemStack.OPTIONAL_STREAM_CODEC.encode(registryFriendlyByteBuf, mortarRecipe.container);
        ItemStack.STREAM_CODEC.encode(registryFriendlyByteBuf, mortarRecipe.result);
    }
}
