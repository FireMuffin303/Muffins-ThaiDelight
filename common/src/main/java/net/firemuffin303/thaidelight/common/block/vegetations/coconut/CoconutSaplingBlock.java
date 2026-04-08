package net.firemuffin303.thaidelight.common.block.vegetations.coconut;

import net.firemuffin303.thaidelight.common.world.trees.CoconutTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CoconutSaplingBlock extends SaplingBlock {
    public CoconutSaplingBlock(Properties properties) {
        super(new CoconutTreeGrower(), properties);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return Block.box(3.0, -1.0, 3.0, 13.0, 4.0, 13.0);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return Block.box(3.0, -1.0, 3.0, 13.0, 16.0, 13.0);
    }
}
