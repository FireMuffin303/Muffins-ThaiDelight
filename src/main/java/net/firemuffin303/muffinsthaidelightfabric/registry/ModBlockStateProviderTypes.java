package net.firemuffin303.muffinsthaidelightfabric.registry;

import com.mojang.serialization.Codec;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.world.feature.stateproviders.RandomHorizontalFacingStateProvider;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;

public class ModBlockStateProviderTypes {
    public static final BlockStateProviderType<RandomHorizontalFacingStateProvider> RANDOM_HORIZONTAL_FACING =
            register("random_horizontal_facing",RandomHorizontalFacingStateProvider.CODEC);

    private static <P extends BlockStateProvider> BlockStateProviderType<P> register(String id, Codec<P> codec){
        return Registry.register(BuiltInRegistries.BLOCKSTATE_PROVIDER_TYPE, ThaiDelight.modid(id),new BlockStateProviderType<>(codec));
    }

    public static void init(){}
}
