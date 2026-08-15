package net.firemuffin303.thaidelight.mixin.fabric.recipebook;

import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(RecipeBookType.class)
public enum RecipeBookTypeMixin {
    MUFFINS_THAIDELIGHT_MORTAR_RECIPE_BOOK_TYPE;

}
