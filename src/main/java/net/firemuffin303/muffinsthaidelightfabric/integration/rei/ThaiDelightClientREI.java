package net.firemuffin303.muffinsthaidelightfabric.integration.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.recipe.mortar.MortarRecipe;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModRecipes;

public class ThaiDelightClientREI implements REIClientPlugin {
    public static final CategoryIdentifier<MortarREIDisplay> MORTAR_ID = CategoryIdentifier.of(ThaiDelight.modid("plugin/mortar"));

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(MortarRecipe.class, ModRecipes.MORTAR,MortarREIDisplay::new);
    }

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new MortarREICategory());
        registry.addWorkstations(MORTAR_ID, EntryIngredient.of(EntryStacks.of(ModItems.MORTAR)));
    }
}
