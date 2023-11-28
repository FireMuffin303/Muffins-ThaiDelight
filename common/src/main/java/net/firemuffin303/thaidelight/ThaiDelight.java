package net.firemuffin303.thaidelight;

import net.firemuffin303.thaidelight.common.block.saucebowl.SauceBowlInteraction;
import net.firemuffin303.thaidelight.common.registry.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.level.block.ComposterBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class ThaiDelight {
    public static final Logger LOGGER = LoggerFactory.getLogger(ThaiDelight.MOD_ID);
    public static final String MOD_ID = "muffins_thaidelight";

    public static final AttributeModifier CRAB_REACH = new AttributeModifier(UUID.fromString("9b61c8ad-e259-4a36-b6f6-382ab3c509ea"),"Range Modifier",1.0d,AttributeModifier.Operation.ADDITION);

    public static void init() {
        ModEntityTypes.init();
        ModBlocks.init();
        ModBlocks.ModBlockEntityTypes.init();
        ModFluid.init();
        ModItems.init();
        ModMobEffects.init();
        ModPotions.init();
        ModRecipes.init();
        ModRecipes.ModRecipeSerializer.init();
    }

    public static void postInit(){
        ModEntityTypes.postInit();
        ModPotions.postInit();
        SauceBowlInteraction.init();
        registerComposterBlock();
    }

    public static void registerComposterBlock(){
        ComposterBlock.COMPOSTABLES.put(ModItems.PEPPER_SEED,0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.LIME_SEED,0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.PAPAYA_SEED,0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.PEPPER,0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.LIME,0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.UNRIPE_PAPAYA,0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.PAPAYA,0.65f);

        ComposterBlock.COMPOSTABLES.put(ModItems.SLICED_LIME,0.4f);
        ComposterBlock.COMPOSTABLES.put(ModItems.SLICED_UNRIPE_PAPAYA,0.4f);
        ComposterBlock.COMPOSTABLES.put(ModItems.SLICED_PAPAYA,0.4f);
    }
}
