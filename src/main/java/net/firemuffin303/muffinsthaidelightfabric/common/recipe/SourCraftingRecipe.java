package net.firemuffin303.muffinsthaidelightfabric.common.recipe;

import net.firemuffin303.muffinsthaidelightfabric.common.component.FlavorItemComponent;
import net.firemuffin303.muffinsthaidelightfabric.common.manager.FlavorManager;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModComponents;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
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
        int flavor = 0;


        for (int i = 0; i < container.getContainerSize(); ++i){
            ItemStack itemStack = container.getItem(i);
            if(!itemStack.isEmpty() && itemStack.isEdible() && !FlavorManager.FLAVORS.containsKey(itemStack.getItem()) && !ModComponents.FLAVOR.get(itemStack).isFlavored()){
                if(foodChecked){
                    return false;
                }
                foodChecked = true;
            } else if (FlavorManager.FLAVORS.containsKey(itemStack.getItem())) {
                if(flavor > 3){
                    return false;
                }
                flavor++;
            }
        }

        return flavor <= 3 && flavor > 0 && foodChecked;
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        ItemStack itemStack = ItemStack.EMPTY;
        int sour = 0,spicy = 0,salty = 0,sweet = 0;


        for(int i = 0; i < container.getContainerSize();++i){
            ItemStack foodStack = container.getItem(i);
            if(foodStack.isEdible() && !FlavorManager.FLAVORS.containsKey(foodStack.getItem())){
                itemStack = new ItemStack(foodStack.getItem(),1);
            }else if(FlavorManager.FLAVORS.containsKey(foodStack.getItem())){
                FlavorManager.FlavorEntry flavorEntry = FlavorManager.FLAVORS.get(foodStack.getItem());
                 sour += flavorEntry.sour();
                 spicy += flavorEntry.spicy();
                 salty += flavorEntry.salty();
                 sweet += flavorEntry.sweet();
            }
        }

        if(!itemStack.isEmpty()){
            FlavorItemComponent flavorItemComponent = ModComponents.FLAVOR.get(itemStack);
            flavorItemComponent.setSourLevel(sour);
            flavorItemComponent.setSpicyLevel(spicy);
            flavorItemComponent.setSaltyLevel(salty);
            flavorItemComponent.setSweetLevel(sweet);
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
