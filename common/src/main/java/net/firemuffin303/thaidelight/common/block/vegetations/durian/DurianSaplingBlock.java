package net.firemuffin303.thaidelight.common.block.vegetations.durian;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class DurianSaplingBlock extends SaplingBlock {
    public DurianSaplingBlock(TreeGrower treeGrower, Properties properties) {
        super(treeGrower, properties);
    }

    //TODO : porting old growing algorithm
    @Override
    public void advanceTree(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState, RandomSource randomSource) {

        super.advanceTree(serverLevel, blockPos, blockState, randomSource);
    }
}
