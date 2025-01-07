package net.firemuffin303.muffinsthaidelightfabric.common.menu;

import net.firemuffin303.muffinsthaidelightfabric.common.recipe.MortarRecipe;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModMenuType;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModRecipes;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModSoundEvents;
import net.minecraft.core.NonNullList;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class MortarMenu extends RecipeBookMenu<Container> {
    private final ResultContainer resultContainer = new ResultContainer();
    private final CraftingContainer craftSlots = new TransientCraftingContainer(this, 2, 2);
    private final Player player;
    private final ContainerLevelAccess access;
    long lastSoundTime;

    public MortarMenu(int i, Inventory inventory){
        this(i,inventory,ContainerLevelAccess.NULL);
    }

    public MortarMenu(int i, Inventory inventory, ContainerLevelAccess containerLevelAccess) {
        super(ModMenuType.MORTAR, i);
        this.player = inventory.player;
        this.access = containerLevelAccess;

        this.addSlot(new MortarResultSlot(this.player, this.craftSlots, this.resultContainer, 0, 124, 35));


        int j;
        int k;

        for(j = 0; j < 2; ++j) {
            for(k = 0; k < 2; ++k) {
                this.addSlot(new Slot(this.craftSlots, k + j * 2, 39 + k * 18, 26 + j * 18));
            }
        }

        for(j = 0; j < 3; ++j) {
            for(k = 0; k < 9; ++k) {
                this.addSlot(new Slot(inventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
            }
        }

        for(j = 0; j < 9; ++j) {
            this.addSlot(new Slot(inventory, j, 8 + j * 18, 142));
        }
    }

    public void removed(Player arg) {
        super.removed(arg);
        this.access.execute((arg2, arg3) -> {
            this.clearContainer(arg, this.craftSlots);
        });
    }

    public void slotsChanged(Container arg) {
        this.access.execute((argx, arg2) -> {
            slotChangedCraftingGrid(this, argx, this.player, this.craftSlots, this.resultContainer);
        });
    }

    protected static void slotChangedCraftingGrid(AbstractContainerMenu arg, Level arg2, Player arg3, CraftingContainer arg4, ResultContainer arg5) {
        if (!arg2.isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer)arg3;
            ItemStack itemStack = ItemStack.EMPTY;
            Optional<MortarRecipe> optional = arg2.getServer().getRecipeManager().getRecipeFor(ModRecipes.MORTAR, arg4, arg2);
            if (optional.isPresent()) {
                MortarRecipe mortarRecipe = optional.get();
                if (arg5.setRecipeUsed(arg2, serverPlayer, mortarRecipe)) {
                    ItemStack itemStack2 = mortarRecipe.assemble(arg4, arg2.registryAccess());
                    if (itemStack2.isItemEnabled(arg2.enabledFeatures())) {
                        itemStack = itemStack2;
                    }
                }
            }

            arg5.setItem(0, itemStack);
            arg.setRemoteSlot(0, itemStack);
            serverPlayer.connection.send(new ClientboundContainerSetSlotPacket(arg.containerId, arg.incrementStateId(), 0, itemStack));
        }
    }

    @Override
    public void fillCraftSlotsStackedContents(StackedContents stackedContents) {
        this.craftSlots.fillStackedContents(stackedContents);
    }

    @Override
    public void clearCraftingContent() {
        this.craftSlots.clearContent();
        this.resultContainer.clearContent();
    }

    @Override
    public boolean recipeMatches(Recipe<? super Container> recipe) {
        return recipe.matches(this.craftSlots,this.player.level());
    }

    @Override
    public int getResultSlotIndex() {
        return 0;
    }

    @Override
    public int getGridWidth() {
        return this.craftSlots.getWidth();
    }

    @Override
    public int getGridHeight() {
        return this.craftSlots.getHeight();
    }

    @Override
    public int getSize() {
        return 5;
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return RecipeBookType.CRAFTING;
    }

    @Override
    public boolean shouldMoveToInventory(int i) {
        return i != this.getResultSlotIndex();
    }

    @Override
    public ItemStack quickMoveStack(Player arg, int i) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(i);
        if (slot != null && slot.hasItem()) {
            ItemStack itemStack2 = slot.getItem();
            itemStack = itemStack2.copy();
            if (i == 0) {
                this.access.execute((arg3, arg4) -> {
                    itemStack2.getItem().onCraftedBy(itemStack2, arg3, arg);
                });
                if (!this.moveItemStackTo(itemStack2, 5, 41, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemStack2, itemStack);
            } else if (i >= 5 && i < 41) {
                if (!this.moveItemStackTo(itemStack2, 1, 5, false)) {
                    if (i < 37) {
                        if (!this.moveItemStackTo(itemStack2, 30, 41, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(itemStack2, 5, 30, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemStack2, 5, 41, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(arg, itemStack2);
            if (i == 0) {
                arg.drop(itemStack2, false);
            }
        }

        return itemStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access,this.player, ModBlocks.MORTAR);
    }

    class MortarResultSlot extends ResultSlot{
        protected CraftingContainer craftSlots;
        protected Player player;
        public MortarResultSlot(Player player, CraftingContainer craftingContainer, Container container, int i, int j, int k) {
            super(player, craftingContainer, container, i, j, k);
            this.craftSlots = craftingContainer;
            this.player = player;
        }

        @Override
        public void onTake(Player player, ItemStack itemStack) {

            this.checkTakeAchievements(itemStack);
            NonNullList<ItemStack> nonNullList = player.level().getRecipeManager().getRemainingItemsFor(ModRecipes.MORTAR, this.craftSlots, player.level());

            for(int i = 0; i < nonNullList.size(); ++i) {
                ItemStack itemStack2 = this.craftSlots.getItem(i);
                ItemStack itemStack3 = (ItemStack)nonNullList.get(i);
                if (!itemStack2.isEmpty()) {
                    this.craftSlots.removeItem(i, 1);
                    itemStack2 = this.craftSlots.getItem(i);
                }

                if (!itemStack3.isEmpty()) {
                    if (itemStack2.isEmpty()) {
                        this.craftSlots.setItem(i, itemStack3);
                    } else if (ItemStack.isSameItemSameTags(itemStack2, itemStack3)) {
                        itemStack3.grow(itemStack2.getCount());
                        this.craftSlots.setItem(i, itemStack3);
                    } else if (!this.player.getInventory().add(itemStack3)) {
                        this.player.drop(itemStack3, false);
                    }
                }
            }

            MortarMenu.this.access.execute((level, blockPos) -> {
                long l = level.getGameTime();
                if (MortarMenu.this.lastSoundTime != l) {
                    level.playSound((Player)null, blockPos, ModSoundEvents.MORTAR_CRAFT, SoundSource.BLOCKS, 1.0F, 1.0F);
                    MortarMenu.this.lastSoundTime = l;
                }

            });
        }
    }
}
