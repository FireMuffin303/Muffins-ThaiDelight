package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class ModSoundEvents {
    public static Supplier<SoundEvent> MORTAR_CRAFT = register("ui.mortar.take_result");
    public static Supplier<SoundEvent> DRAGONFLY_LOOP = register("entity.dragonfly.loop");
    public static Supplier<SoundEvent> DRAGONFLY_HURT = register("entity.dragonfly.hurt");

    public static void init(){
    }

    private static Supplier<SoundEvent> register(String id){
        return ModPlatform.registerSoundEvent(ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,id),() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(ThaiDelight.MOD_ID,id)));
    }
}
