package net.firemuffin303.muffinsthaidelightfabric.mixin;

import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Chicken.class)
public interface ChickenFoodAccessor {
    @Accessor(value = "FOOD_ITEMS")
    static Ingredient getFoodItems(){
        throw new AssertionError();
    }

    @Accessor(value = "FOOD_ITEMS")
    @Mutable
    static void setFoodItems(Ingredient ingredient){
        throw new AssertionError();
    }
}
