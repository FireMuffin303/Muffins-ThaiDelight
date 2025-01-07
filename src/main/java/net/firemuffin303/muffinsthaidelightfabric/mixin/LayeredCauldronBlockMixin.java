package net.firemuffin303.muffinsthaidelightfabric.mixin;

import net.firemuffin303.muffinsthaidelightfabric.common.block.FermentedFishCauldronBlock;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LayeredCauldronBlock.class)
public abstract class LayeredCauldronBlockMixin {

    @Shadow @Final public static IntegerProperty LEVEL;

    @Inject(method = "entityInside", at = @At("TAIL"))
    public void muffinsThaiDelight$entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity, CallbackInfo ci){
        if(entity instanceof ItemEntity item){
            if(item.getItem().is(ItemTags.FISHES)){
                level.setBlockAndUpdate(blockPos, ModBlocks.FERMENTED_FISH_CAULDRON.defaultBlockState()
                        .setValue(FermentedFishCauldronBlock.FERMENT,0)
                        .setValue(FermentedFishCauldronBlock.LEVEL,blockState.getValue(LEVEL)));
                level.playSound(null,blockPos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS,1.0f,1.0f);
                level.gameEvent(null, GameEvent.FLUID_PLACE,blockPos);
                item.discard();
            }


        }
    }
}
