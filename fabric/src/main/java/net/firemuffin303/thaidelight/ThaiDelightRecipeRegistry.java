package net.firemuffin303.thaidelight;

import net.firemuffin303.muffinsmcapi.fabric.api.IRecipeBookInitializer;
import net.firemuffin303.muffinsmcapi.impl.recipebooks.OvenRecipeBookRegistry;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public class ThaiDelightRecipeRegistry implements IRecipeBookInitializer {
    public static final String MORTAR_TYPE = "MUFFINS_THAIDELIGHT_MORTAR_RECIPE_BOOK_TYPE";
    public static final String MORTAR_SEARCH = "MUFFINS_THAIDELIGHT_MORTAR_SEARCH";
    public static final String MORTAR_MEAL = "MUFFINS_THAIDELIGHT_MORTAR_MEALS";
    public static final String MORTAR_MISC = "MUFFINS_THAIDELIGHT_MORTAR_MISC";

    @Override
    public void register(OvenRecipeBookRegistry ovenRecipeBookRegistry) {
        ovenRecipeBookRegistry.registerRecipeCategories(MORTAR_SEARCH, () -> new ItemStack[]{new ItemStack(Items.COMPASS)});
        ovenRecipeBookRegistry.registerRecipeCategories(MORTAR_MEAL, () -> new ItemStack[]{new ItemStack(ModItems.SOMTAM_FEAST.get())});
        ovenRecipeBookRegistry.registerRecipeCategories(MORTAR_MISC, () -> new ItemStack[]{new ItemStack(ModItems.PEPPER.get()),new ItemStack(Items.BONE_MEAL)});

        ovenRecipeBookRegistry.registerRecipeBook(MORTAR_TYPE,List.of(MORTAR_SEARCH,MORTAR_MEAL,MORTAR_MISC));
    }
}
