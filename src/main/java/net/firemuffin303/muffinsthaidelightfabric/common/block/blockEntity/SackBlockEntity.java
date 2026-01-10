package net.firemuffin303.muffinsthaidelightfabric.common.block.blockEntity;

import net.firemuffin303.muffinsthaidelightfabric.common.block.SackBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.menu.SackMenu;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SackBlockEntity extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> items;
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        @Override
        protected void onOpen(Level level, BlockPos blockPos, BlockState blockState) {
            SackBlockEntity.this.updateBlockState(blockState,true);
        }

        @Override
        protected void onClose(Level level, BlockPos blockPos, BlockState blockState) {
            SackBlockEntity.this.updateBlockState(blockState,false);
        }

        @Override
        protected void openerCountChanged(Level level, BlockPos blockPos, BlockState blockState, int i, int j) {

        }

        @Override
        protected boolean isOwnContainer(Player player) {
            if (player.containerMenu instanceof SackMenu) {
                Container container = ((SackMenu)player.containerMenu).getContainer();
                return container == SackBlockEntity.this;
            }
            return false;
        }
    };

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
    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        super.setItem(i, itemStack);
        this.markUpdated();
    }

    @Override
    public NonNullList<ItemStack> getItems() {
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


    public void markUpdated() {
        BlockState blockState = this.getBlockState();

        if (this.items.stream().allMatch(itemStack -> itemStack.getCount() == itemStack.getMaxStackSize())) {
            blockState = blockState.setValue(SackBlock.FILLED, true);
        }else {
            blockState = blockState.setValue(SackBlock.FILLED, false);
        }

        if(blockState != this.getBlockState()){
            this.level.setBlock(this.getBlockPos(),blockState,3);
        }
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    void updateBlockState(BlockState blockState, boolean bl) {
        this.level.setBlock(this.getBlockPos(), blockState.setValue(SackBlock.OPEN, bl), 3);
    }
}
