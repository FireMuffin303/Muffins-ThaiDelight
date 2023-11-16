package net.firemuffin303.thaidelight.common.block.entity;

import net.firemuffin303.thaidelight.common.recipe.MortarRecipe;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class MortarBlockEntity extends BlockEntity implements WorldlyContainer {
    public NonNullList<ItemStack> inventory;
    private final RecipeManager.CachedCheck<Container, ? extends MortarRecipe> quickCheck;

    private ResourceLocation lastRecipeID;

    public MortarBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.ModBlockEntityTypes.MORTAR_BLOCK_ENTITY, blockPos, blockState);
        this.inventory = NonNullList.withSize(4,ItemStack.EMPTY);
        this.quickCheck = RecipeManager.createCheck(ModRecipes.MORTAR);
    }

    public boolean process(MortarBlockEntity mortarBlockEntity,Player player,ItemStack tool){
        if(this.level == null){
            return false;
        }

        MortarRecipe mortarRecipe = mortarBlockEntity.quickCheck.getRecipeFor(mortarBlockEntity,this.level).orElse(null);
        if(mortarRecipe == null){
            player.displayClientMessage(Component.literal("It doesn't seem right."),true);
            return false;
        }

        ItemStack itemStack = mortarRecipe.getResultItem(this.level.registryAccess()).copy();

        if(!this.level.isClientSide()){
            ServerLevel serverLevel = (ServerLevel) this.level;

            ItemEntity itemEntity = new ItemEntity(serverLevel, mortarBlockEntity.getBlockPos().getX(), mortarBlockEntity.getBlockPos().getY(), mortarBlockEntity.getBlockPos().getZ(), itemStack);
            itemEntity.setDefaultPickUpDelay();

            serverLevel.addFreshEntity(itemEntity);

            tool.hurtAndBreak(1,player,(user) -> {
                user.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }

        this.lastRecipeID = mortarRecipe.getId();
        return true;
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        this.inventory = NonNullList.withSize(this.getContainerSize(),ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compoundTag,this.inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        ContainerHelper.saveAllItems(compoundTag,this.inventory);
    }

    public int getContainerSize() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getItem(int i) {
        return null;
    }

    @Override
    public ItemStack removeItem(int i, int j) {
        return null;
    }

    @Override
    public ItemStack removeItemNoUpdate(int i) {
        return null;
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {

    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        return new int[0];
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, ItemStack itemStack, @Nullable Direction direction) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int i, ItemStack itemStack, Direction direction) {
        return false;
    }

    @Override
    public void clearContent() {

    }
}
