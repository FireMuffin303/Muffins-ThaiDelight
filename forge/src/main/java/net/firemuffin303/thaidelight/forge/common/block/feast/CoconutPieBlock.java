package net.firemuffin303.thaidelight.forge.common.block.feast;

import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import vectorwing.farmersdelight.common.block.PieBlock;

public class CoconutPieBlock extends PieBlock {
    public CoconutPieBlock() {
        super(Properties.copy(Blocks.CAKE),ModItems.COCONUT_PIE_SLICE);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(hand);
        if(level.isClientSide){
            if(itemStack.is(Items.HONEY_BOTTLE)){
                return InteractionResult.SUCCESS;
            }
        }

        if(itemStack.is(Items.HONEY_BOTTLE)){
            level.setBlock(pos, ModBlocks.HONEY_COCONUT_PIE.get().defaultBlockState().setValue(BITES,state.getValue(BITES)).setValue(FACING,state.getValue(FACING)), 3);
            level.playSound(null, pos, SoundEvents.HONEY_BLOCK_PLACE, SoundSource.PLAYERS, 0.8F, 0.8F);
            player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
            player.setItemInHand(hand, ItemUtils.createFilledResult(itemStack,player,new ItemStack(Items.GLASS_BOTTLE)));

            return InteractionResult.SUCCESS;
        }

        return super.use(state, level, pos, player, hand, hit);
    }
}
