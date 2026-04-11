package net.firemuffin303.thaidelight.common.registry.fabric;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModBlockEntityTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.List;
import java.util.function.Supplier;

public class ModBlockEntityTypesImpl {
    public static <T extends BlockEntity> Supplier<BlockEntityType<T>> register(String id, ModBlockEntityTypes.BlockEntitySupplier<T> blockEntitySupplier, List<Supplier<Block>> blocks) {
        BlockEntityType<T> registeredBlockEntity = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ThaiDelightCommon.modid(id),BlockEntityType.Builder.of(blockEntitySupplier::create,blocks.stream().map(Supplier::get).toArray(Block[]::new)).build(null));
        return () -> registeredBlockEntity;
    }
}
