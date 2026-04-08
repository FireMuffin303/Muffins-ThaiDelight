package net.firemuffin303.thaidelight.common.registry.fabric;

import com.mojang.serialization.Codec;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;

import java.util.function.Supplier;

public class ModBlockStateProviderTypesImpl {
    public static <P extends BlockStateProvider> Supplier<BlockStateProviderType<P>> register(String id, Codec<P> codec) {
        BlockStateProviderType<P> blockStateProviderType = Registry.register(BuiltInRegistries.BLOCKSTATE_PROVIDER_TYPE, ThaiDelightCommon.modid(id),new BlockStateProviderType<>(codec));
        return () -> blockStateProviderType;
    }
}
