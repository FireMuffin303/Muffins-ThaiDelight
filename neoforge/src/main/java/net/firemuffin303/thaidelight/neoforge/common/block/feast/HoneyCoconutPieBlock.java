package net.firemuffin303.thaidelight.neoforge.common.block.feast;

import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.block.PieBlock;

public class HoneyCoconutPieBlock extends PieBlock {
    public HoneyCoconutPieBlock(Properties properties) {
        super(properties, ModItems.HONEY_COCONUT_PIE_SLICE);
    }


    @Override
    protected InteractionResult consumeBite(Level level, BlockPos pos, BlockState state, Player player) {
        InteractionResult interactionResult = super.consumeBite(level, pos, state, player);
        if(interactionResult == InteractionResult.SUCCESS){
            player.removeEffect(MobEffects.POISON);
        }
        return interactionResult;
    }
}
