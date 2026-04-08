package net.firemuffin303.thaidelight.common.registry.forge;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModBlockEntityTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntityTypesImpl {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ThaiDelightCommon.MOD_ID);

    public static <T extends BlockEntity> Supplier<BlockEntityType<T>> register(String id, ModBlockEntityTypes.BlockEntitySupplier<T> blockEntitySupplier, Block... blocks) {
        return BLOCK_ENTITY.register(id,() -> BlockEntityType.Builder.of(blockEntitySupplier::create,blocks).build(null));
    }
}
