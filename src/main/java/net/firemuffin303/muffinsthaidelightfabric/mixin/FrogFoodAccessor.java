package net.firemuffin303.muffinsthaidelightfabric.mixin;

import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Frog.class)
public interface FrogFoodAccessor {
    @Accessor(value = "TEMPTATION_ITEM")
    static Ingredient getFoodItems(){
        throw new AssertionError();
    }

    @Accessor(value = "TEMPTATION_ITEM")
    @Mutable
    static void setFoodItems(Ingredient ingredient){
        throw new AssertionError();
    }
}
