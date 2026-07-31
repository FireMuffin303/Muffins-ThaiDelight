package net.firemuffin303.thaidelight.common.registry.forge;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModBlockEntityTypes;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class ModBlockEntityTypesImpl {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ThaiDelightCommon.MOD_ID);

    public static <T extends BlockEntity> Supplier<BlockEntityType<T>> register(String id, ModBlockEntityTypes.BlockEntitySupplier<T> blockEntitySupplier, List<Supplier<Block>> blocks) {
        return BLOCK_ENTITY.register(id,() -> BlockEntityType.Builder.of(blockEntitySupplier::create,blocks.stream().map(Supplier::get).toArray(Block[]::new)).build(Util.fetchChoiceType(References.BLOCK_ENTITY, id)));
    }
}
