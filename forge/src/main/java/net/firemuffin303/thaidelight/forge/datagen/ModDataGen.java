package net.firemuffin303.thaidelight.forge.datagen;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ThaiDelight.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        generator.addProvider(event.includeServer(), new ModRecipes(output));
    }
}
