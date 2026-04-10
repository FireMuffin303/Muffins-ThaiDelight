package net.firemuffin303.thaidelight.util;

import net.minecraft.world.level.block.Block;

import java.util.Set;

public interface BlockEntityTypeAdder {

    default void addSupportBlock(Block block){
        throw new AssertionError("Implement in Mixin");
    }

}
