package net.firemuffin303.muffinsthaidelightfabric.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.block.blockEntity.FenceLoggedButterflyPeaBlockEntity;
import net.firemuffin303.muffinsthaidelightfabric.common.block.blockEntity.SackBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntityTypes {
    public static BlockEntityType<SackBlockEntity> SACK_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ThaiDelight.modid("sack"), FabricBlockEntityTypeBuilder.create(SackBlockEntity::new,ModBlocks.SACK).build());
    public static BlockEntityType<FenceLoggedButterflyPeaBlockEntity> FENCE_LOGGED_BUTTERFLY_PEA_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,ThaiDelight.modid("fence_logged_butterfly_pea"),FabricBlockEntityTypeBuilder.create(FenceLoggedButterflyPeaBlockEntity::new,ModBlocks.FENCE_LOGGED_BUTTERFLY_PEA).build());

    public static void init(){}

}
