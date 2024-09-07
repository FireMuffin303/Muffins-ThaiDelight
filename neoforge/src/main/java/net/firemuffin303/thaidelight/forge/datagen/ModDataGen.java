package net.firemuffin303.thaidelight.forge.datagen;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.client.model.ForgeItemModelShaper;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = ThaiDelight.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        generator.addProvider(event.includeServer(), new ModRecipes(output));
    }

   /* static class ModBiomeModifiers extends DatapackBuiltinEntriesProvider {

        private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
                .add(ForgeRegistries.Keys.BIOME_MODIFIERS, context ->{
                    context.register(key("flower_crab"),
                            ForgeBiomeModifiers.AddSpawnsBiomeModifier.singleSpawn(, new MobSpawnSettings.SpawnerData(ModEntityTypes.FLOWER_CRAB,10,3,5));
                } );

        public ModBiomeModifiers(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries, BUILDER ,  Set.of(ThaiDelight.MOD_ID));
        }

        private static ResourceKey<net.minecraftforge.common.world.BiomeModifier> key(String id){
            return  ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(ThaiDelight.MOD_ID,id));
        }

    }*/

}
