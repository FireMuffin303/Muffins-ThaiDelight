package net.firemuffin303.thaidelight.common.block.cauldron;

import net.firemuffin303.thaidelight.common.registry.ModCauldronInteraction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.LayeredCauldronBlock;

public class CoconutMilkCauldron extends LayeredCauldronBlock {

    public CoconutMilkCauldron(Properties properties) {
        super(Biome.Precipitation.NONE,ModCauldronInteraction.COCONUT_MILK,properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 1));
    }

    @Override
    public Item asItem() {
        return Items.CAULDRON;
    }
}
