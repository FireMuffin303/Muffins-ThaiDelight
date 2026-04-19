package net.firemuffin303.thaidelight.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class ModSoundEvents {
    public static ResourceRegistry<SoundEvent> SOUND = ResourceRegistry.create(Registries.SOUND_EVENT,ThaiDelightCommon.MOD_ID);

    public static Supplier<SoundEvent> MORTAR_CRAFT = SOUND.register("ui.mortar.take_result",() -> SoundEvent.createVariableRangeEvent(ThaiDelightCommon.modid("ui.mortar.take_result")));
    public static Supplier<SoundEvent> SACK_CATCHING_DURIAN = SOUND.register("item.sack.catching_durian",() -> SoundEvent.createVariableRangeEvent(ThaiDelightCommon.modid("item.sack.catching_durian")));

    public static void init(){
        SOUND.init();
    }

}
