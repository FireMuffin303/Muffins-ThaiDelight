package net.firemuffin303.thaidelight.mixin.blockEntityType;

import com.mojang.datafixers.types.Type;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.Set;

@Mixin(BlockEntityType.class)
public abstract class BlockEntityTypeMixin implements BlockEntityTypeAdder {
    @Mutable
    @Shadow @Final private Set<Block> validBlocks;

    @Inject(method = "<init>",at = @At("TAIL"))
    public void muffins$init(BlockEntityType.BlockEntitySupplier blockEntitySupplier, Set set, Type type, CallbackInfo ci){
        if(!(this.validBlocks instanceof HashSet<Block>)){
            this.validBlocks = new HashSet<>(this.validBlocks);
        }
    }

    @Override
    public void addSupportBlock(Block block) {
        this.validBlocks.add(block);
    }
}
