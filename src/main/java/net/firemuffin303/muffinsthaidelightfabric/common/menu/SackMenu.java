package net.firemuffin303.muffinsthaidelightfabric.common.menu;

import net.firemuffin303.muffinsthaidelightfabric.registry.ModMenuType;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SackMenu extends AbstractContainerMenu {
    private final Container container;

    public SackMenu(int i, Inventory inventory){
        this(i,inventory,new SimpleContainer(5));
    }

    public SackMenu(int i, Inventory inventory, Container container) {
        super(ModMenuType.SACK, i);

        ChestMenu.checkContainerSize(container, 5);
        this.container = container;
        container.startOpen(inventory.player);
        for(int m = 0; m < this.container.getContainerSize(); ++m) {
            this.addSlot(new Slot(container, m, 44 + m * 18, 18));
        }


        //Player Inventory
        for(int j = 0; j < 3; ++j) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(inventory, k + j * 9 + 9, 8 + k * 18, 50 + j * 18));
            }
        }

        for(int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(inventory, j, 8 + j * 18, 108));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(i);
        if (slot != null && slot.hasItem()) {
            ItemStack itemStack2 = slot.getItem();
            itemStack = itemStack2.copy();
            if (i < this.container.getContainerSize()) {
                if (!this.moveItemStackTo(itemStack2, this.container.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack2, 0, this.container.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemStack;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.container.stopOpen(player);
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    public ItemStack firstStack(){
        return this.container.getItem(0);
    }

    public int getSize(){
        return this.container.getContainerSize();
    }

    public Container getContainer() {
        return this.container;
    }

    private static class SackSlot extends Slot{
        private boolean isLocked;

        public SackSlot(Container container, int i, int j, int k) {
            super(container, i, j, k);
            this.isLocked = false;
        }


        @Override
        public boolean isActive() {
            return !this.isLocked;
        }
    }
}
