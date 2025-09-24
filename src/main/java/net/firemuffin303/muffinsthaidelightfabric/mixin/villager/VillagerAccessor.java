package net.firemuffin303.muffinsthaidelightfabric.mixin.villager;

import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.Set;

@Mixin(Villager.class)
public interface VillagerAccessor {

    @Mutable
    @Accessor("FOOD_POINTS")
    static Map<Item, Integer> getFoodPoints() {
        throw new AssertionError();
    }

    @Mutable
    @Accessor("FOOD_POINTS")
    static void setFoodPoints(Map<Item, Integer> map) {
        throw new AssertionError();
    }

    @Mutable
    @Accessor("WANTED_ITEMS")
    static Set<Item> getWantedItems(){
        throw new AssertionError();
    }

    @Mutable
    @Accessor("WANTED_ITEMS")
    static void setWantedItems(Set<Item> items){
        throw new AssertionError();
    }
}
