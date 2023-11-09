package net.firemuffin303.thaidelight.forge.common.block;

import net.firemuffin303.thaidelight.forge.common.registry.ModItemsForge;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.function.Supplier;

public class CrabFriedRiceFeastBlock extends FeastBlock {
    public CrabFriedRiceFeastBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.CAKE), ModItemsForge.CRAB_FRIED_RICE, true);
    }
}
