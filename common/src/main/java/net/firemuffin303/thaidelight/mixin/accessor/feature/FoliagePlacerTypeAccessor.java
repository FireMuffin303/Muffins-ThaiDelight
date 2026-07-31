package net.firemuffin303.thaidelight.mixin.accessor.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(FoliagePlacerType.class)
public interface FoliagePlacerTypeAccessor {

    @Invoker("<init>")
    static <P extends FoliagePlacer> FoliagePlacerType<P> init(MapCodec<P> codec){
        throw new AssertionError();
    }
}
