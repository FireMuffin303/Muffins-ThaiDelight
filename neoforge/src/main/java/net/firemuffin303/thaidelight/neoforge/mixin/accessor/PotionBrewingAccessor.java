package net.firemuffin303.thaidelight.neoforge.mixin.accessor;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PotionBrewing.class)
public interface PotionBrewingAccessor {

    @Invoker("addMix")
    static void addMix(Potion arg, Item arg2, Potion arg3){
        throw new AssertionError();
    }
}
