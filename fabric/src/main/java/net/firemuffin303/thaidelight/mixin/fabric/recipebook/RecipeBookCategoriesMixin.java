package net.firemuffin303.thaidelight.mixin.fabric.recipebook;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RecipeBookCategories.class)
public enum RecipeBookCategoriesMixin {
    MUFFINS_THAIDELIGHT_MORTAR_SEARCH(new ItemStack[]{new ItemStack(Items.COMPASS)}),
    MUFFINS_THAIDELIGHT_MORTAR_MEALS(new ItemStack[]{new ItemStack(ModItems.SOMTAM_FEAST.get())}),
    MUFFINS_THAIDELIGHT_MORTAR_MISC(new ItemStack[]{new ItemStack(ModItems.PEPPER.get()),new ItemStack(Items.BONE_MEAL)});

    @Shadow
    RecipeBookCategoriesMixin(ItemStack... stack){

    }
}
