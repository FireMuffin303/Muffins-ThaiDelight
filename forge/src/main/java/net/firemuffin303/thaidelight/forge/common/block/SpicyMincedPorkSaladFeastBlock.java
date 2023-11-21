package net.firemuffin303.thaidelight.forge.common.block;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.forge.common.registry.ModItemsForge;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vectorwing.farmersdelight.common.block.FeastBlock;

public class SpicyMincedPorkSaladFeastBlock extends FeastBlock {
    public SpicyMincedPorkSaladFeastBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.CAKE), ModItemsForge.SPICY_MINCED_PORK_SALAD, true);
    }
}
