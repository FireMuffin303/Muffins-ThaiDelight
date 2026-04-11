package net.firemuffin303.thaidelight.mixin.recipeBookType;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = RecipeBookCategories.class,priority = 2910)
public class RecipeBookCategoriesMixin {
    @Final
    @Mutable
    @Shadow
    private static RecipeBookCategories[] $VALUES;

    @Invoker("<init>")
    private static RecipeBookCategories createRecipeBookCategory(String name, int ordinal, ItemStack... itemStacks) {
        throw new RuntimeException();
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addCookingRecipeCategory(CallbackInfo ci) {
        List<RecipeBookCategories> recipeBookTypeList = new ArrayList<>(List.of($VALUES));
        recipeBookTypeList.add(createRecipeBookCategory("MORTAR_SEARCH", $VALUES.length, new ItemStack(Items.COMPASS)));
        recipeBookTypeList.add(createRecipeBookCategory("MORTAR_MEALS", $VALUES.length + 1, new ItemStack(ModItems.SOMTAM_FEAST.get())));
        recipeBookTypeList.add(createRecipeBookCategory("MORTAR_MISC", $VALUES.length + 2, new ItemStack(ModItems.PEPPER.get()),new ItemStack(Items.BONE_MEAL)));
        $VALUES = recipeBookTypeList.toArray(RecipeBookCategories[]::new);
    }
}
