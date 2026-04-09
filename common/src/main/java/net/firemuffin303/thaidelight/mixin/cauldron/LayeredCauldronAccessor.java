package net.firemuffin303.thaidelight.mixin.cauldron;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Predicate;

@Mixin(LayeredCauldronBlock.class)
public interface LayeredCauldronAccessor {

    @Accessor("fillPredicate")
    Predicate<Biome.Precipitation> getFillPredicate();
}
