package net.firemuffin303.thaidelight.common.item.papaya;

import net.firemuffin303.thaidelight.common.block.vegetations.papaya.PapayaLogBlock;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class PapayaFlowerItem extends BlockItem {
    public PapayaFlowerItem(Properties properties) {
        super(ModBlocks.PAPAYA_FLOWER.get(), properties);
    }

    @Override
    protected @Nullable BlockState getPlacementState(BlockPlaceContext blockPlaceContext) {
        LevelReader levelReader = blockPlaceContext.getLevel();
        BlockPos blockPos = blockPlaceContext.getClickedPos();

        BlockState clickedBlock = levelReader.getBlockState(blockPos.relative(blockPlaceContext.getClickedFace().getOpposite()));
        BlockState finalBlockState = this.getBlock().getStateForPlacement(blockPlaceContext);
        if(clickedBlock.is(ModBlocks.PAPAYA_LOG.get()) && clickedBlock.getValue(PapayaLogBlock.AXIS).isVertical() && blockPlaceContext.getClickedFace().getAxis().isHorizontal()) {
            finalBlockState = ModBlocks.BUDDING_PAPAYA_FLOWER.get().getStateForPlacement(blockPlaceContext);
        } else if((blockPlaceContext.getClickedFace().getAxis().isHorizontal() && !levelReader.getBlockState(blockPos).is(ModBlocks.PAPAYA_FLOWER.get())) || levelReader.getBlockState(blockPos).is(ModBlocks.WALL_PAPAYA_FLOWER.get())){
            finalBlockState = ModBlocks.WALL_PAPAYA_FLOWER.get().getStateForPlacement(blockPlaceContext);
        }

        if(finalBlockState != null && finalBlockState.canSurvive(levelReader,blockPos) && levelReader.isUnobstructed(finalBlockState,blockPos,CollisionContext.empty())){
            return finalBlockState;
        }
        return null;
    }

    @Override
    public void registerBlocks(Map<Block, Item> map, Item item) {
        super.registerBlocks(map, item);
        map.put(ModBlocks.WALL_PAPAYA_FLOWER.get(),item);
        map.put(ModBlocks.BUDDING_PAPAYA_FLOWER.get(),item);
    }
}
