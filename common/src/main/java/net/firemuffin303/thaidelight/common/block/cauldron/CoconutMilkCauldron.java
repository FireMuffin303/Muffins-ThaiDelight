package net.firemuffin303.thaidelight.common.block.cauldron;

import net.firemuffin303.thaidelight.common.registry.ModCauldronInteraction;
import net.minecraft.world.level.block.LayeredCauldronBlock;

public class CoconutMilkCauldron extends LayeredCauldronBlock {

    public CoconutMilkCauldron(Properties properties) {
        super(properties,precipitation -> false, ModCauldronInteraction.COCONUT_MILK);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 1));
    }
}
