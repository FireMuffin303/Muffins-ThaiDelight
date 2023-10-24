package net.firemuffin303.thaidelight.fabric;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.fabricmc.api.ModInitializer;
import net.firemuffin303.thaidelight.common.entity.Dragonfly;
import net.firemuffin303.thaidelight.common.entity.FlowerCrabEntity;
import net.firemuffin303.thaidelight.common.registry.ModEntityTypes;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.biome.Biomes;

public class ThaiDelightModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ThaiDelight.init();
        ThaiDelight.postInit();

        FabricDefaultAttributeRegistry.register(ModEntityTypes.FLOWER_CRAB.get(), FlowerCrabEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntityTypes.DRAGONFLY.get(), Dragonfly.createAttributes());


        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.MANGROVE_SWAMP,Biomes.SWAMP), MobCategory.CREATURE,ModEntityTypes.FLOWER_CRAB.get(),10,3,5);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.MANGROVE_SWAMP,Biomes.SWAMP,Biomes.RIVER), MobCategory.AMBIENT,ModEntityTypes.DRAGONFLY.get(),10,1,3);


    }

}
