package net.firemuffin303.muffinsthaidelightfabric.common.block.blockEntity;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class FenceLoggedButterflyPeaBlockEntity extends BlockEntity {
    public BlockState fenceState;

    public FenceLoggedButterflyPeaBlockEntity( BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntityTypes.FENCE_LOGGED_BUTTERFLY_PEA_BLOCK_ENTITY, blockPos, blockState);
        this.fenceState = Blocks.OAK_FENCE.defaultBlockState();
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        compoundTag.putString("block_id", BuiltInRegistries.BLOCK.getKey(this.fenceState.getBlock()).toString());
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        this.fenceState = BuiltInRegistries.BLOCK.get(new ResourceLocation(compoundTag.getString("block_id"))).defaultBlockState();
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }
}
