package net.firemuffin303.muffinsthaidelightfabric.integration.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiStack;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.recipe.mortar.MortarRecipe;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModRecipes;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModTags;
import net.minecraft.world.item.Items;

public class ThaiDelightEMI implements EmiPlugin {
    public static final EmiRecipeCategory FERMENTED_FISH = new EmiRecipeCategory(
            ThaiDelight.modid("cauldron_crafting"),
            EmiStack.of(Items.CAULDRON),
            new EmiTexture(ThaiDelight.modid("textures/gui/emi/cauldron.png"),0,0,16,16,16,16,16,16)
    );

    public static final EmiRecipeCategory MORTAR = new EmiRecipeCategory(
            ThaiDelight.modid("mortar"),
            EmiStack.of(ModItems.MORTAR),
            new EmiTexture(ThaiDelight.modid("textures/gui/emi/mortar.png"), 0, 0, 16, 16,16,16,16,16)
    );


    @Override
    public void register(EmiRegistry emiRegistry) {
        emiRegistry.addCategory(MORTAR);
        emiRegistry.addCategory(FERMENTED_FISH);

        for(MortarRecipe recipe : emiRegistry.getRecipeManager().getAllRecipesFor(ModRecipes.MORTAR)){
            emiRegistry.addRecipe(new EMIMortarRecipe(recipe));
        }

        emiRegistry.addRecipe(new EMIFermentedFishRecipe(ModTags.COMMON_RAW_FISHES,1,Items.BOWL,ModItems.FERMENTED_FISH, EMIFermentedFishRecipe.TransformMode.WAIT));
        emiRegistry.addRecipe(new EMIFermentedFishRecipe(ModTags.COCONUT,3,Items.GLASS_BOTTLE,ModItems.COCONUT_MILK_BOTTLE, EMIFermentedFishRecipe.TransformMode.WATER));
    }
}
