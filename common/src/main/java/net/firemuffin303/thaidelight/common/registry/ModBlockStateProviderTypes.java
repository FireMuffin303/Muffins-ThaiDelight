package net.firemuffin303.thaidelight.common.registry;

import com.mojang.serialization.Codec;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.world.feature.stateproviders.RandomHorizontalFacingStateProvider;
import net.firemuffin303.thaidelight.mixin.accessor.block.BlockStateProviderTypeAccessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;

import java.util.function.Supplier;

public class ModBlockStateProviderTypes {
    public static final ResourceRegistry<BlockStateProviderType<?>> BLOCK_STATE_PROVIDER = ResourceRegistry.create(Registries.BLOCK_STATE_PROVIDER_TYPE, ThaiDelightCommon.MOD_ID);


    public static final Supplier<BlockStateProviderType<?>> RANDOM_HORIZONTAL_FACING = BLOCK_STATE_PROVIDER.register("random_horizontal_facing",() -> BlockStateProviderTypeAccessor.init(RandomHorizontalFacingStateProvider.CODEC));

    public static void init(){
        BLOCK_STATE_PROVIDER.init();
    }
}
