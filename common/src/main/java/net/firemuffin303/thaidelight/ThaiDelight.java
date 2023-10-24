package net.firemuffin303.thaidelight;

import net.firemuffin303.thaidelight.common.block.cauldron.ModCauldronInteraction;
import net.firemuffin303.thaidelight.common.registry.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class ThaiDelight {
    public static final Logger LOGGER = LoggerFactory.getLogger(ThaiDelight.MOD_ID);
    public static final String MOD_ID = "muffins_thaidelight";

    public static final AttributeModifier CRAB_REACH = new AttributeModifier(UUID.fromString("9b61c8ad-e259-4a36-b6f6-382ab3c509ea"),"Range Modifier",1.0d,AttributeModifier.Operation.ADDITION);

    public static void init() {
        ModBlocks.ModBlockEntityTypes.BLOCK_ENTITY_TYPE.init();
        ModBlocks.BLOCK.init();
        ModEntityTypes.ENTITY_TYPE.init();
        ModFluid.FLUID.init();
        ModItems.ITEMS.init();
        ModMobEffects.MOB_EFFECT.init();
        ModPotions.POTION.init();
    }

    public static void postInit(){
        ModEntityTypes.postInit();
        ModPotions.postInit();
        ModCauldronInteraction.init();
    }
}
