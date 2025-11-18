package net.firemuffin303.muffinsthaidelightfabric.common.block.blockEntity;

import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsthaidelightfabric.common.menu.SackMenu;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SackBlockEntity extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> items;
    public SackBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntityTypes.SACK_BLOCK_ENTITY, blockPos, blockState);
        this.items = NonNullList.withSize(5,ItemStack.EMPTY);
    }



    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        if (!this.trySaveLootTable(compoundTag)) {
            ContainerHelper.saveAllItems(compoundTag, this.items);
        }
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(compoundTag)) {
            ContainerHelper.loadAllItems(compoundTag, this.items);
        }
    }

    @Override
    public ItemStack removeItem(int i, int j) {
        ItemStack itemStack = super.removeItem(i,j);
        this.markUpdated();
        return itemStack;
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        super.setItem(i, itemStack);
        this.markUpdated();
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        this.items = nonNullList;
    }

    public ItemStack getFirstStack(){
        return this.items.get(0);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("muffins_thaidelight.container.sack");
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new SackMenu(i,inventory,this);
    }

    @Override
    public int getContainerSize() {
        return 5;
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }


    private void markUpdated() {
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }
}
