package net.firemuffin303.thaidelight.common.block.crops;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PapayaCrop extends CropBlock {
    public PapayaCrop(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.PAPAYA_SEED;
    }

}
