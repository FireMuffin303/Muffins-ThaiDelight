package net.firemuffin303.thaidelight.common.block.crops;

import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;

public class ModSaplingBlock extends SaplingBlock {
    //Just to fix the protected stuff.
    public ModSaplingBlock(AbstractTreeGrower abstractTreeGrower, Properties properties) {
        super(abstractTreeGrower, properties);
    }
}
