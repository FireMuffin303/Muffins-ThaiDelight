package net.firemuffin303.thaidelight.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.thaidelight.common.block.blockentity.SackBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class ModBlockEntityTypes {
    public static Supplier<BlockEntityType<SackBlockEntity>> SACK_BLOCK_ENTITY = register("sack",SackBlockEntity::new,ModBlocks.SACK.get());

    public static void init(){}

    @ExpectPlatform
    public static <T extends BlockEntity> Supplier<BlockEntityType<T>> register(String id, BlockEntitySupplier<T> blockEntitySupplier, Block... blocks){
        throw new AssertionError();
    }

    public interface  BlockEntitySupplier<T extends BlockEntity>{
        T create(BlockPos blockPos, BlockState blockState);
    }
}
