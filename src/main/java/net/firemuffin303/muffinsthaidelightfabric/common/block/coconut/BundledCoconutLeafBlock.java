package net.firemuffin303.muffinsthaidelightfabric.common.block.coconut;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.RotatedPillarBlock;

public class BundledCoconutLeafBlock extends RotatedPillarBlock {

    public BundledCoconutLeafBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.Y));
    }
}
