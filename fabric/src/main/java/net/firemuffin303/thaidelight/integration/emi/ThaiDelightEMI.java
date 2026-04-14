package net.firemuffin303.thaidelight.integration.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiStack;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipe;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.common.registry.ModRecipes;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.minecraft.world.item.Items;

public class ThaiDelightEMI implements EmiPlugin {
    public static final EmiRecipeCategory FERMENTED_FISH = new EmiRecipeCategory(
            ThaiDelightCommon.modid("cauldron_crafting"),
            EmiStack.of(Items.CAULDRON),
            new EmiTexture(ThaiDelightCommon.modid("textures/gui/emi/cauldron.png"),0,0,16,16,16,16,16,16)
    );

    public static final EmiRecipeCategory MORTAR = new EmiRecipeCategory(
            ThaiDelightCommon.modid("mortar"),
            EmiStack.of(ModItems.MORTAR.get()),
            new EmiTexture(ThaiDelightCommon.modid("textures/gui/emi/mortar.png"), 0, 0, 16, 16,16,16,16,16)
    );


    @Override
    public void register(EmiRegistry emiRegistry) {
        emiRegistry.addCategory(MORTAR);
        emiRegistry.addCategory(FERMENTED_FISH);

        for(MortarRecipe recipe : emiRegistry.getRecipeManager().getAllRecipesFor(ModRecipes.MORTAR.get())){
            emiRegistry.addRecipe(new EMIMortarRecipe(recipe));
        }

        emiRegistry.addRecipe(new EMIFermentedFishRecipe(ModTags.COMMON_RAW_FISHES,1,Items.BOWL,ModItems.FERMENTED_FISH.get(), EMIFermentedFishRecipe.TransformMode.WAIT));
        emiRegistry.addRecipe(new EMIFermentedFishRecipe(ModTags.COCONUT,3,Items.GLASS_BOTTLE,ModItems.COCONUT_MILK_BOTTLE.get(), EMIFermentedFishRecipe.TransformMode.WATER));
    }
}
