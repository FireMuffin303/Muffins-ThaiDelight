package net.firemuffin303.thaidelight.common.item;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

public class SneakPlaceItemNameBlock extends ItemNameBlockItem {
    public SneakPlaceItemNameBlock(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult place(BlockPlaceContext blockPlaceContext) {
        Player player = blockPlaceContext.getPlayer();
        if (player != null && player.isShiftKeyDown()) {
            return super.place(blockPlaceContext);
        }
        return InteractionResult.PASS;
    }
}
