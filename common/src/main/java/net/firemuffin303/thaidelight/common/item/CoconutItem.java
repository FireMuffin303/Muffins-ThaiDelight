package net.firemuffin303.thaidelight.common.item;

import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class CoconutItem extends BlockItem {
    protected final Block crop = ModBlocks.COCONUT_SAPLING_CROP.get();
    public CoconutItem(Properties properties) {
        super(ModBlocks.COCONUT.get(), properties);
    }


    @Override
    protected @Nullable BlockState getPlacementState(BlockPlaceContext blockPlaceContext) {
        BlockState blockState = this.crop.getStateForPlacement(blockPlaceContext);
        if(blockState != null && this.canPlace(blockPlaceContext,blockState)){
            return blockState;
        }

        return super.getPlacementState(blockPlaceContext);
    }

    @Override
    public void registerBlocks(Map<Block, Item> map, Item item) {
        super.registerBlocks(map, item);
        map.put(ModBlocks.COCONUT_SAPLING_CROP.get(),item);
    }
}
