package net.firemuffin303.thaidelight.mixin.accessor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Blocks.class)
public interface BlocksAccessor {

    @Invoker("flowerPot")
    static Block flowerPot(Block block){
        throw new AssertionError();
    }

    @Invoker("log")
    static Block log(MapColor mapColor, MapColor mapColor2){
        throw new AssertionError();
    }

    @Invoker("woodenButton")
    static Block woodenButton(BlockSetType blockSetType){
        throw new AssertionError();
    }

    @Invoker("never")
    static Boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType){
        throw new AssertionError();
    }

    @Invoker("ocelotOrParrot")
    static Boolean ocelotOrParrot(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType){
        throw new AssertionError();
    }

}
