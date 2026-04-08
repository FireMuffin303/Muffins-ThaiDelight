package net.firemuffin303.thaidelight.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.util.PlatformUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class ModSoundEvents {
    public static Supplier<SoundEvent> MORTAR_CRAFT = register("ui.mortar.take_result");
    public static Supplier<SoundEvent> SACK_CATCHING_DURIAN = register("item.sack.catching_durian");

    public static void init(){}

    @ExpectPlatform
    public static Supplier<SoundEvent> register(String id){
        throw new AssertionError();
    }
}
