package net.firemuffin303.thaidelight.common.registry.forge;

import com.mojang.serialization.Codec;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockStateProviderTypesImpl {
    public static final DeferredRegister<BlockStateProviderType<?>> BLOCK_STATE_PROVIDER_TYPE = DeferredRegister.create(Registries.BLOCK_STATE_PROVIDER_TYPE, ThaiDelightCommon.MOD_ID);

    public static <P extends BlockStateProvider> Supplier<BlockStateProviderType<P>> register(String id, Codec<P> codec) {
        return BLOCK_STATE_PROVIDER_TYPE.register(id,() -> new BlockStateProviderType<>(codec));
    }
}
