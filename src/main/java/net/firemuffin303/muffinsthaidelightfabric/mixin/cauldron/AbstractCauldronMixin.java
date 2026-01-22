package net.firemuffin303.muffinsthaidelightfabric.mixin.cauldron;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModCauldronInteraction;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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

    @Shadow @Final private Map<Item, CauldronInteraction> interactions;

    @ModifyReturnValue(method = "use",at = @At("RETURN"))
    public InteractionResult muffins$use(InteractionResult original, @Local ItemStack itemStack,
                                         @Local(argsOnly = true)BlockState blockState,
                                         @Local(argsOnly = true)Level level,
                                         @Local(argsOnly = true)BlockPos blockPos,
                                         @Local(argsOnly = true)Player player,
                                         @Local(argsOnly = true) InteractionHand interactionHand
                                         ){
        if((AbstractCauldronBlock)(Object)this instanceof LayeredCauldronBlock && this.interactions == CauldronInteraction.WATER){
            if(itemStack.is(ModTags.COMMON_RAW_FISHES) && !CauldronInteraction.WATER.containsKey(itemStack.getItem())){
                return ModCauldronInteraction.MAKE_FERMENTED_FISH.interact(blockState, level, blockPos, player, interactionHand, itemStack);
            }
        }

        return original;
    }
}
