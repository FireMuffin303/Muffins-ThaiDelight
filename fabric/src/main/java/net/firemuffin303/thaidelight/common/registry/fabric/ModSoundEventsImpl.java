package net.firemuffin303.thaidelight.common.registry.fabric;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class ModSoundEventsImpl {
    public static Supplier<SoundEvent> register(String id) {
        ResourceLocation resourceID = ThaiDelightCommon.modid(id);
        SoundEvent soundEvent = Registry.register(BuiltInRegistries.SOUND_EVENT,resourceID,SoundEvent.createVariableRangeEvent(resourceID));
        return () -> soundEvent;
    }
}
