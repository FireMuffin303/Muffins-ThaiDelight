package net.firemuffin303.thaidelight.common.block.entity;

import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MortarBlockEntity extends BlockEntity  {
    public static Container container;

    public MortarBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.ModBlockEntityTypes.MORTAR_BLOCK_ENTITY, blockPos, blockState);
    }

}
