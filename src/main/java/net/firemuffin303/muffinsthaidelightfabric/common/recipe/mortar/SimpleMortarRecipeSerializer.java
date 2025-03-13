package net.firemuffin303.muffinsthaidelightfabric.common.recipe.mortar;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class SimpleMortarRecipeSerializer<T extends MortarRecipe> implements RecipeSerializer<T> {
    private final SimpleMortarRecipeSerializer.Factory<T> constructor;

    public SimpleMortarRecipeSerializer(Factory<T> constructor) {
        this.constructor = constructor;
    }

    @FunctionalInterface
    public interface Factory<T extends MortarRecipe> {
        T create(ResourceLocation resourceLocation);
    }

    @Override
    public T fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
        return this.constructor.create(resourceLocation);
    }

    @Override
    public T fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
        return this.constructor.create(resourceLocation);
    }

    @Override
    public void toNetwork(FriendlyByteBuf friendlyByteBuf, T recipe) {
    }
}
