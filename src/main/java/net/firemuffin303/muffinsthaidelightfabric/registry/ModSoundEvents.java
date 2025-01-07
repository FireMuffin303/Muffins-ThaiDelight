package net.firemuffin303.muffinsthaidelightfabric.registry;

import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSoundEvents {
    public static SoundEvent MORTAR_CRAFT = register("ui.mortar.take_result");

    public static void init(){}

    public static SoundEvent register(String id){
        ResourceLocation ID = new ResourceLocation(ThaiDelight.MOD_ID,id);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, ID,SoundEvent.createVariableRangeEvent(ID));
    }
}
