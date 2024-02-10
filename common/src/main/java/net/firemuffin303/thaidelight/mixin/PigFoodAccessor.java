package net.firemuffin303.thaidelight.mixin;

import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Pig.class)
public interface PigFoodAccessor {

    @Accessor("FOOD_ITEMS")
    static Ingredient getFoodItems(){
        throw new AssertionError();
    }

    @Accessor("FOOD_ITEMS")
    @Mutable
    static void setFoodItem(Ingredient ingredient){
        throw new AssertionError();
    }
}
