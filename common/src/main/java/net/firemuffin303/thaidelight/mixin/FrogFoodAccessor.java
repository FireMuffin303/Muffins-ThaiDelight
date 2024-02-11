package net.firemuffin303.thaidelight.mixin;

import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Frog.class)
public interface FrogFoodAccessor {

    @Accessor("TEMPTATION_ITEM")
    static Ingredient getFoodItems(){
        throw new AssertionError();
    }

    @Accessor("TEMPTATION_ITEM")
    @Mutable
    static void setFoodItems(Ingredient ingredient){
        throw new AssertionError();
    }
}
