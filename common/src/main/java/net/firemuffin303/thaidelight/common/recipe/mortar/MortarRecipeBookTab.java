package net.firemuffin303.thaidelight.common.recipe.mortar;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.crafting.CraftingBookCategory;

public enum MortarRecipeBookTab implements StringRepresentable {
    MEALS("meals"),
    MISC("misc");

    public final String name;
    public static final Codec<MortarRecipeBookTab> CODEC = StringRepresentable.fromEnum(MortarRecipeBookTab::values);

    MortarRecipeBookTab(String name){this.name = name;}

    public static MortarRecipeBookTab findByName(String name) {
        for (MortarRecipeBookTab value : values()) {
            if (value.name.equals(name)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
