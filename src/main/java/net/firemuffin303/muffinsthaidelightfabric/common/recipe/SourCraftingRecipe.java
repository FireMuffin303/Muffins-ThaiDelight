package net.firemuffin303.muffinsthaidelightfabric.common.recipe;

import net.firemuffin303.muffinsthaidelightfabric.common.item.tooltipComponent.FlavorTooltipClient;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class SourCraftingRecipe extends CustomRecipe {

    public SourCraftingRecipe(ResourceLocation resourceLocation, CraftingBookCategory craftingBookCategory) {
        super(resourceLocation, craftingBookCategory);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        boolean foodChecked = false;
        boolean fishBottleCheck = false;


        for (int i = 0; i < container.getContainerSize(); ++i){
            ItemStack itemStack = container.getItem(i);
            boolean containsTag = (itemStack.hasTag() && itemStack.getTag().contains(FlavorTooltipClient.FlavorTooltipComponent.FLAVOR_NBT));
            if(!itemStack.isEmpty() && itemStack.isEdible() && !itemStack.is(ModItems.SLICED_LIME) && !containsTag){
                if(foodChecked){
                    return false;
                }
                foodChecked = true;
            } else if (Ingredient.of(ModItems.SLICED_LIME).test(itemStack)) {
                if(fishBottleCheck){
                    return false;
                }
                fishBottleCheck = true;
            }
        }

        return fishBottleCheck && foodChecked;
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        ItemStack itemStack = ItemStack.EMPTY;

        for(int i = 0; i < container.getContainerSize();++i){
            ItemStack foodStack = container.getItem(i);
            if(foodStack.isEdible() && !foodStack.is(ModItems.SLICED_LIME)){
                itemStack = new ItemStack(foodStack.getItem(),1);
            }
        }

        if(!itemStack.isEmpty()){

            CompoundTag compoundTag = itemStack.getOrCreateTag();
            CompoundTag flavorTag = new CompoundTag();
            flavorTag.putBoolean(FlavorTooltipClient.FlavorTooltipComponent.SOUR_NBT,true);
            compoundTag.put(FlavorTooltipClient.FlavorTooltipComponent.FLAVOR_NBT,flavorTag);
        }

        return itemStack;
    }

    @Override
    public @NotNull NonNullList<ItemStack> getRemainingItems(CraftingContainer container) {
        return NonNullList.withSize(container.getContainerSize(), ItemStack.EMPTY);
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return i * j >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.ModSerializer.TASTY_CRAFTING_RECIPE;
    }

}
