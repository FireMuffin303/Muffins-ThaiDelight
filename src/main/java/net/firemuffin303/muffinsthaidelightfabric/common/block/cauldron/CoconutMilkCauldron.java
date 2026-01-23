package net.firemuffin303.muffinsthaidelightfabric.common.block.cauldron;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModCauldronInteraction;
import net.minecraft.world.level.block.LayeredCauldronBlock;

public class CoconutMilkCauldron extends LayeredCauldronBlock {

    public CoconutMilkCauldron(Properties properties) {
        super(properties,precipitation -> false, ModCauldronInteraction.COCONUT_MILK);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 1));
    }
}
