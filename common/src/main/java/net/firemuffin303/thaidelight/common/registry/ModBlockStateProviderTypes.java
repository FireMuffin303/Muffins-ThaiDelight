package net.firemuffin303.thaidelight.common.registry;

import com.mojang.serialization.Codec;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.thaidelight.common.world.feature.stateproviders.RandomHorizontalFacingStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;

import java.util.function.Supplier;

public class ModBlockStateProviderTypes {
    public static final Supplier<BlockStateProviderType<RandomHorizontalFacingStateProvider>> RANDOM_HORIZONTAL_FACING =
            register("random_horizontal_facing",RandomHorizontalFacingStateProvider.CODEC);

    @ExpectPlatform
    private static <P extends BlockStateProvider> Supplier<BlockStateProviderType<P>> register(String id, Codec<P> codec){
        throw new AssertionError();
    }

    public static void init(){}
}
