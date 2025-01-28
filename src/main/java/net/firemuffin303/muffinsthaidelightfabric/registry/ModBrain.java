package net.firemuffin303.muffinsthaidelightfabric.registry;

import com.mojang.serialization.Codec;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

import java.util.Optional;

public class ModBrain {
    public static final MemoryModuleType<GlobalPos> BUFFALO_MUD_POSITION = Registry.register(BuiltInRegistries.MEMORY_MODULE_TYPE,new ResourceLocation(ThaiDelight.MOD_ID,"buffalo_mud_position"),new MemoryModuleType<>(Optional.of(GlobalPos.CODEC)));
    public static final MemoryModuleType<Boolean> BUFFALO_PLAYING = Registry.register(BuiltInRegistries.MEMORY_MODULE_TYPE,new ResourceLocation(ThaiDelight.MOD_ID,"buffalo_playing"),new MemoryModuleType<>(Optional.empty()));
}
