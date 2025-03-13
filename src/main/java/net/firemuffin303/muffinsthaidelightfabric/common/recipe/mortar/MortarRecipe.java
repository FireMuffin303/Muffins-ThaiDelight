package net.firemuffin303.muffinsthaidelightfabric.common.recipe.mortar;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

public interface MortarRecipe extends Recipe<Container> {
    @Override
    default RecipeType<?> getType() {
        return ModRecipes.MORTAR;
    }

}
