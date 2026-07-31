package net.firemuffin303.thaidelight.common.menu;


import net.firemuffin303.thaidelight.common.recipe.mortar.MortarRecipe;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModMenuType;
import net.firemuffin303.thaidelight.common.registry.ModRecipes;
import net.firemuffin303.thaidelight.common.registry.ModSoundEvents;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class MortarMenu extends RecipeBookMenu<MortarInput,MortarRecipe> {
    private final ResultContainer resultContainer = new ResultContainer();
    private final Container craftSlots;
    private final Player player;
    private final ContainerLevelAccess access;
    long lastSoundTime;

    public MortarMenu(int i, Inventory inventory){
        this(i,inventory,ContainerLevelAccess.NULL);
    }

    public MortarMenu(int i, Inventory inventory, ContainerLevelAccess containerLevelAccess) {
        super(ModMenuType.MORTAR.get(), i);
        this.player = inventory.player;
        this.access = containerLevelAccess;
        this.craftSlots = new SimpleContainer(5){
            @Override
            public void setChanged() {
                super.setChanged();
                MortarMenu.this.slotsChanged(this);
            }
        };


        this.addSlot(new Slot(this.craftSlots, 0, 39, 26));
        this.addSlot(new Slot(this.craftSlots, 1, 57, 26));
        this.addSlot(new Slot(this.craftSlots, 2, 39, 44));
        this.addSlot(new Slot(this.craftSlots, 3, 57, 44));

        this.addSlot(new Slot(this.craftSlots, 4, 84, 54));
        this.addSlot(new MortarResultSlot(this.player, this.craftSlots, this.resultContainer, 5, 124, 35));


        for(int j = 0; j < 3; ++j) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(inventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
            }
        }

        for(int j = 0; j < 9; ++j) {
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
        this.access.execute((level, blockPos) -> {
            MortarInput mortarInput = createRecipeInput(this.craftSlots);
            slotChangedCraftingGrid(this, level, this.player,mortarInput, this.resultContainer);
        });
    }

    protected static void slotChangedCraftingGrid(AbstractContainerMenu arg, Level level, Player player, MortarInput container, ResultContainer resultContainer) {
        if (!level.isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer)player;
            ItemStack itemStack = ItemStack.EMPTY;
            Optional<RecipeHolder<MortarRecipe>> optional = level.getServer().getRecipeManager().getRecipeFor(ModRecipes.MORTAR.get(), container, level);
            if (optional.isPresent()) {
                MortarRecipe mortarRecipe = optional.get().value();
                if (resultContainer.setRecipeUsed(level, serverPlayer, optional.get())) {
                    ItemStack itemStack2 = mortarRecipe.assemble(container, level.registryAccess());
                    if (itemStack2.isItemEnabled(level.enabledFeatures())) {
                        itemStack = itemStack2;
                    }
                }
            }

            resultContainer.setItem(0, itemStack);
            arg.setRemoteSlot(0, itemStack);
            serverPlayer.connection.send(new ClientboundContainerSetSlotPacket(arg.containerId, arg.incrementStateId(), 0, itemStack));
        }
    }

    private static MortarInput createRecipeInput(Container container){
        List<ItemStack> itemStacks = new ArrayList<>();
        for(int i = 0; i < 4; i ++){
            itemStacks.add(container.getItem(i));
        }
        return new MortarInput(itemStacks,container.getItem(4));
    }

    @Override
    public void fillCraftSlotsStackedContents(StackedContents stackedContents) {
        for(int i = 0; i < this.craftSlots.getContainerSize(); ++i) {
            stackedContents.accountSimpleStack(this.craftSlots.getItem(i));
        }
    }

    @Override
    public void clearCraftingContent() {
        this.craftSlots.clearContent();
        this.resultContainer.clearContent();
    }

    @Override
    public boolean recipeMatches(RecipeHolder recipeHolder) {
        return ((MortarRecipe)recipeHolder.value()).matches(createRecipeInput(this.craftSlots),this.player.level());
    }

    @Override
    public int getResultSlotIndex() {
        return 0;
    }

    @Override
    public int getGridWidth() {
        return 2;
    }

    @Override
    public int getGridHeight() {
        return 2;
    }

    @Override
    public int getSize() {
        return 6;
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return PlatformUtil.getMortarBookType();
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
                if (!this.moveItemStackTo(itemStack2, 6, 42, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemStack2, itemStack);
            } else if (i >= 6 && i < 42) {
                if (!this.moveItemStackTo(itemStack2, 1, 6, false)) {
                    if (i < 37) {
                        if (!this.moveItemStackTo(itemStack2, 31, 42, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(itemStack2, 6, 31, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemStack2, 6, 42, false)) {
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
        return stillValid(this.access,this.player, ModBlocks.MORTAR.get());
    }

    class MortarResultSlot extends Slot{
        protected Container craftSlots;
        protected Player player;
        public MortarResultSlot(Player player, Container craftingContainer, Container container, int i, int j, int k) {
            super(container,i,j,k);
            this.craftSlots = craftingContainer;
            this.player = player;
        }

        @Override
        public boolean mayPlace(ItemStack itemStack) {
            return false;
        }

        @Override
        public void onTake(Player player, ItemStack itemStack) {

            this.checkTakeAchievements(itemStack);
            Supplier<RecipeType<MortarRecipe>> m = ModRecipes.MORTAR;
            NonNullList<ItemStack> nonNullList = player.level().getRecipeManager().getRemainingItemsFor(m.get(), createRecipeInput(this.craftSlots), player.level());

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
                    } else if (ItemStack.isSameItemSameComponents(itemStack2, itemStack3)) {
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
                    level.playSound((Player)null, blockPos, ModSoundEvents.MORTAR_CRAFT.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                    MortarMenu.this.lastSoundTime = l;
                }

            });
        }
    }
}
