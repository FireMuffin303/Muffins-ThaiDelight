package net.firemuffin303.muffinsthaidelightfabric.common.recipe.mortar;

import net.firemuffin303.muffinsthaidelightfabric.common.component.FlavorItemComponent;
import net.firemuffin303.muffinsthaidelightfabric.common.manager.FlavorManager;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModComponents;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModRecipes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class SauceBowlRecipe implements MortarRecipe{
    private final ResourceLocation id;

    public SauceBowlRecipe(ResourceLocation resourceLocation){
        this.id = resourceLocation;
    }

    @Override
    public boolean matches(Container container, Level level) {
        boolean foodChecked = false;
        boolean flavorChecked = false;
        int flavorAmount = 0;

        for(int i = 1;i < 5;i++){
            ItemStack itemStack = container.getItem(i);
            if(!itemStack.isEmpty()){
                if(FlavorManager.FLAVORS.get(itemStack.getItem()) != null){
                    FlavorManager.FlavorEntry flavorEntry = FlavorManager.FLAVORS.get(itemStack.getItem());
                    flavorAmount += flavorEntry.salty();
                    flavorAmount += flavorEntry.sour();
                    flavorAmount += flavorEntry.spicy();
                    flavorAmount += flavorEntry.sweet();
                    flavorChecked = flavorAmount > 0;
                }else {
                    foodChecked = true;
                }

            }

        }

        return flavorAmount <= 4 && container.getItem(5).is(Items.BOWL) && !foodChecked && flavorChecked;
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        ItemStack bowl = new ItemStack(ModItems.SAUCE_BOWL);
        int sour = 0,spicy = 0,salty = 0,sweet = 0;
        FlavorItemComponent flavorItemComponent = ModComponents.FLAVOR.get(bowl);

        for(int i = 0;i < container.getContainerSize();i++){
            ItemStack itemStack = container.getItem(i);
            if(!itemStack.isEmpty() && FlavorManager.FLAVORS.get(itemStack.getItem()) != null){
                FlavorManager.FlavorEntry flavorEntry = FlavorManager.FLAVORS.get(itemStack.getItem());
                salty += flavorEntry.salty();
                sour += flavorEntry.sour();
                spicy += flavorEntry.spicy();
                sweet += flavorEntry.sweet();
            }
        }

        flavorItemComponent.setSourLevel(sour);
        flavorItemComponent.setSpicyLevel(spicy);
        flavorItemComponent.setSaltyLevel(salty);
        flavorItemComponent.setSweetLevel(sweet);
        return bowl;
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return i * j >= 2;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.ModSerializer.SAUCE_BOWL_SERIALIZER;
    }
}
