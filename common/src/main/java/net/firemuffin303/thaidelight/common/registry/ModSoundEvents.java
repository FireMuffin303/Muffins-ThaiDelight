package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSoundEvents {
    public static SoundEvent MORTAR_CRAFT;
    public static SoundEvent DRAGONFLY_LOOP;
    public static SoundEvent DRAGONFLY_HURT;

    public static void init(){
        MORTAR_CRAFT = ModPlatform.registerSoundEvent("ui.mortar.take_result",SoundEvent.createVariableRangeEvent(new ResourceLocation(ThaiDelight.MOD_ID,"ui.mortar.take_result")));
        DRAGONFLY_LOOP = ModPlatform.registerSoundEvent("entity.dragonfly.loop",SoundEvent.createVariableRangeEvent(new ResourceLocation(ThaiDelight.MOD_ID,"entity.dragonfly.loop")));
        DRAGONFLY_HURT = ModPlatform.registerSoundEvent("entity.dragonfly.hurt",SoundEvent.createVariableRangeEvent(new ResourceLocation(ThaiDelight.MOD_ID,"entity.dragonfly.hurt")));
    }
}
