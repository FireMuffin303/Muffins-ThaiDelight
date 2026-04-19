package net.firemuffin303.thaidelight.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.block.blockentity.SackBlockEntity;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.Supplier;

public class ModBlockEntityTypes {
    public static final ResourceRegistry<BlockEntityType<?>> BLOCK_ENTITY = ResourceRegistry.create(Registries.BLOCK_ENTITY_TYPE, ThaiDelightCommon.MOD_ID);
    public static Supplier<BlockEntityType<?>> SACK_BLOCK_ENTITY = BLOCK_ENTITY.register("sack",() -> BlockEntityType.Builder.of(SackBlockEntity::new,ModBlocks.SACK.get()).build(Util.fetchChoiceType(References.BLOCK_ENTITY, "sack")));

    public static void init(){
        BLOCK_ENTITY.init();
    }

    @ExpectPlatform
    public static <T extends BlockEntity> Supplier<BlockEntityType<T>> register(String id, BlockEntitySupplier<T> blockEntitySupplier, List<Supplier<Block>> blocks){
        throw new AssertionError();
    }

    public interface  BlockEntitySupplier<T extends BlockEntity>{
        T create(BlockPos blockPos, BlockState blockState);
    }
}
