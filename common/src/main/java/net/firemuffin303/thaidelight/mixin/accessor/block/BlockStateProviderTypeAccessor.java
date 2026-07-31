package net.firemuffin303.thaidelight.mixin.accessor.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BlockStateProviderType.class)
public interface BlockStateProviderTypeAccessor {

    @Invoker("<init>")
    static <P extends BlockStateProvider> BlockStateProviderType<P> init(MapCodec<P> codec){
        throw new AssertionError();
    }
}
