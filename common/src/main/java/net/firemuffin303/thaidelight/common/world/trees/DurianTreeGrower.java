package net.firemuffin303.thaidelight.common.world.trees;

import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;

public class DurianTreeGrower {
    public static BlockPattern getOrCreatePattern(){
        return BlockPatternBuilder.start()
                .aisle("#S#")
                .aisle("SSS")
                .aisle("#S#")
                .where('S', BlockInWorld.hasState(BlockStatePredicate.forBlock(ModBlocks.DURIAN_SAPLING.get())))
                .where('#',BlockInWorld.hasState(blockState -> !blockState.is(ModBlocks.DURIAN_SAPLING.get())))
                .build();
    }
}
