package net.firemuffin303.thaidelight.common.registry.forge;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSoundEventsImpl {
    public static final DeferredRegister<SoundEvent> SOUND_EVENT = DeferredRegister.create(Registries.SOUND_EVENT, ThaiDelightCommon.MOD_ID);

    public static Supplier<SoundEvent> register(String id) {
        return SOUND_EVENT.register(id, () -> SoundEvent.createVariableRangeEvent(ThaiDelightCommon.modid(id)));
    }
}
