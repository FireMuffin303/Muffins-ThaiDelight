package net.firemuffin303.thaidelight.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class ModUtil {
    public static ItemStack getCraftRemainder(ItemStack itemStack){
        return itemStack.getItem().hasCraftingRemainingItem() ? itemStack.getItem().getCraftingRemainingItem().getDefaultInstance() : ItemStack.EMPTY;
    }

    public static boolean isAreaLoaded(ServerLevel serverLevel, BlockPos center, int range) {
        return serverLevel.hasChunksAt(center.offset(-range, -range, -range), center.offset(range, range, range));
    }

    public static Optional<BlockPos> getTopConnectedBlock(BlockGetter blockGetter, BlockPos blockPos, BlockState middleBlock, Direction direction, BlockState endBlock) {
        BlockState blockState;
        BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
        do {
            mutableBlockPos.move(direction);
            blockState = blockGetter.getBlockState(mutableBlockPos);
        } while (blockState == middleBlock);
        if (blockState == endBlock) {
            return Optional.of(mutableBlockPos);
        }
        return Optional.empty();
    }
}
