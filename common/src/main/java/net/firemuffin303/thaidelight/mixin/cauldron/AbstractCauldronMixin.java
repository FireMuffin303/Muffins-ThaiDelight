package net.firemuffin303.thaidelight.mixin.cauldron;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.firemuffin303.thaidelight.common.registry.ModCauldronInteraction;
import net.firemuffin303.thaidelight.common.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;

@Mixin(AbstractCauldronBlock.class)
public abstract class AbstractCauldronMixin {

    @Shadow @Final
    protected CauldronInteraction.InteractionMap interactions;

    @ModifyReturnValue(method = "useItemOn",at = @At("RETURN"))
    public ItemInteractionResult muffins$use(ItemInteractionResult original,
                                             @Local(argsOnly = true) ItemStack itemStack,
                                             @Local(argsOnly = true) BlockState blockState,
                                             @Local(argsOnly = true) Level level,
                                             @Local(argsOnly = true) BlockPos blockPos,
                                             @Local(argsOnly = true) Player player,
                                             @Local(argsOnly = true) InteractionHand interactionHand
                                             ){
        if(this.interactions == CauldronInteraction.EMPTY){
            if(itemStack.is(ModTags.COCONUT) && !CauldronInteraction.EMPTY.map().containsKey(itemStack.getItem())){
                return ModCauldronInteraction.SET_COCONUT_CAULDRON.interact(blockState, level, blockPos, player, interactionHand, itemStack);
            }
        } else if((AbstractCauldronBlock)(Object)this instanceof LayeredCauldronBlock && this.interactions == CauldronInteraction.WATER){
            if(itemStack.is(ModTags.COMMON_RAW_FISHES) && !CauldronInteraction.WATER.map().containsKey(itemStack.getItem())){
                return ModCauldronInteraction.MAKE_FERMENTED_FISH.interact(blockState, level, blockPos, player, interactionHand, itemStack);
            }
        }

        return original;
    }
}
