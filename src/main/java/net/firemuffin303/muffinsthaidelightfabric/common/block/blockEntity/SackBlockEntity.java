package net.firemuffin303.muffinsthaidelightfabric.common.block.blockEntity;

import com.mojang.logging.LogUtils;
import net.firemuffin303.muffinsthaidelightfabric.common.block.SackBlock;
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
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SackBlockEntity extends BlockEntity implements Container, Nameable {
    private NonNullList<ItemStack> items = NonNullList.withSize(5,ItemStack.EMPTY);
    private ItemStack currentItem = ItemStack.EMPTY;
    @Nullable
    private Component name;

    public SackBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntityTypes.SACK_BLOCK_ENTITY, blockPos, blockState);
    }


    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        ContainerHelper.saveAllItems(compoundTag, this.items);
        if (this.name != null) {
            compoundTag.putString("CustomName", Component.Serializer.toJson(this.name));
        }
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        this.items.clear();
        ContainerHelper.loadAllItems(compoundTag, this.items);

        if (compoundTag.contains("CustomName", 8)) {
            this.name = Component.Serializer.fromJson(compoundTag.getString("CustomName"));
        }

        for(ItemStack itemStack:this.items){
            if(itemStack.isEmpty()) continue;
            this.currentItem = itemStack;
            break;
        }
    }

    @Override
    public @Nullable Component getCustomName() {
        return this.name;
    }

    @Override
    public ItemStack removeItem(int i, int j) {
        ItemStack itemStack = ContainerHelper.removeItem(this.items,i,j);
        if(!itemStack.isEmpty()){
            this.markUpdated();
            this.setChanged();
        }
        return itemStack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        return this.removeItem(i,64);
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        this.items.set(i,itemStack);
        this.markUpdated();
        this.setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public int getContainerSize() {
        return 5;
    }

    @Override
    public boolean isEmpty() {
        return this.items.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int i) {
        return this.items.get(i);
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public boolean canPlaceItem(int i, ItemStack itemStack) {
        ItemStack sackItemSlot = this.items.get(i);
        return this.items.stream().anyMatch(sackItem -> sackItem.is(itemStack.getItem())) && sackItemSlot.getCount() < sackItemSlot.getMaxStackSize();
    }

    @Override
    public boolean canTakeItem(Container container, int i, ItemStack itemStack) {
        return container.hasAnyMatching(itemStack2 -> {
            if (itemStack2.isEmpty()) {
                return true;
            }
            return ItemStack.isSameItemSameTags(itemStack, itemStack2) && itemStack2.getCount() + itemStack.getCount() <= Math.min(itemStack2.getMaxStackSize(), container.getMaxStackSize());
        });
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    public ItemStack getCurrentItem(){
        return this.currentItem;
    }

    public int getSackItemAmount(){
        int amount = 0;

        for(ItemStack itemStack:this.items){
            amount += itemStack.getCount();
        }

        return amount;
    }

    public boolean canInsertItem(ItemStack itemStack){
        if(this.isEmpty() && itemStack.getItem().canFitInsideContainerItems()){
            return true;
        }

        if(this.items.stream().allMatch(sackItem -> sackItem.getCount() >= sackItem.getMaxStackSize())){
            return false;
        }


        return this.items.stream().anyMatch(sackItem -> sackItem.is(itemStack.getItem()) && ItemStack.isSameItemSameTags(sackItem,itemStack)) && itemStack.getItem().canFitInsideContainerItems();
    }

    public ItemStack addItem(ItemStack itemStack){
        for(int i = 0; i < this.items.size(); ++i){
            ItemStack sackItem = this.items.get(i);
            if(canMergeItems(sackItem,itemStack)){
                int j = itemStack.getMaxStackSize() - sackItem.getCount();
                int k = Math.min(itemStack.getCount(), j);
                itemStack.shrink(k);
                sackItem.grow(k);
                this.markUpdated();
                this.setChanged();
                return itemStack.copy();
            }else if(sackItem.isEmpty()){
                this.setItem(i,itemStack.copy());
                return ItemStack.EMPTY;
            }
        }
        return ItemStack.EMPTY;
    }

    public ItemStack insertItem(ItemStack itemStack){
        if(this.items.stream().allMatch(ItemStack::isEmpty)){
            this.setItem(0,itemStack);
            return ItemStack.EMPTY;
        } else if (this.items.stream().allMatch(itemStack1 -> itemStack1.getCount() >= itemStack1.getMaxStackSize()) || !this.getItem(0).is(itemStack.getItem())) {
            return itemStack;
        }

        boolean shouldUpdate = false;


        for(int i = 0; i < this.items.size();i++){
            ItemStack sackItem = this.getItem(i);
            if(canMergeItems(sackItem,itemStack)){
                int j = itemStack.getMaxStackSize() - sackItem.getCount();
                int k = Math.min(itemStack.getCount(), j);
                itemStack.shrink(k);
                sackItem.grow(k);

                shouldUpdate = true;

            } else if (sackItem.isEmpty() && itemStack.getCount() > 0) {
                this.setItem(i, itemStack);
                shouldUpdate = true;
                itemStack = new ItemStack(itemStack.getItem(),0);
                break;
            }

        }


        if(shouldUpdate){
            this.markUpdated();
        }

        return itemStack;
    }

    public ItemStack popItem(){
        for(int i = this.items.size()-1; i >= 0; i--){
            ItemStack itemStack = this.items.get(i).copy();
            if(itemStack.isEmpty()) continue;
            this.removeItem(i,64);
            this.markUpdated();
            return itemStack;
        }

        return ItemStack.EMPTY;
    }

    private static boolean canMergeItems(ItemStack itemStack, ItemStack itemStack2) {
        return itemStack.getCount() < itemStack.getMaxStackSize() && ItemStack.isSameItemSameTags(itemStack, itemStack2);
    }


    public void markUpdated() {
        BlockState blockState = this.getBlockState();

        if (this.items.stream().allMatch(itemStack -> itemStack.getCount() >= itemStack.getMaxStackSize())) {
            blockState = blockState.setValue(SackBlock.FILLED, true);
        }else {
            blockState = blockState.setValue(SackBlock.FILLED, false);
        }

        if(blockState != this.getBlockState()){
            Objects.requireNonNull(this.level).setBlock(this.getBlockPos(),blockState,3);
        }
        Objects.requireNonNull(this.level).sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }


    @Override
    public Component getName() {
        return this.name;
    }
}
