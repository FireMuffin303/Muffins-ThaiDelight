package net.firemuffin303.thaidelight.forge.common.structures;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraftforge.event.server.ServerAboutToStartEvent;

import static vectorwing.farmersdelight.common.world.VillageStructures.addBuildingToPool;

public class VillageStructures {

    public static void addNewVillageBuilding(ServerAboutToStartEvent event) {
        Registry<StructureTemplatePool> templatePools = event.getServer().registryAccess().registry(Registries.TEMPLATE_POOL).get();
        Registry<StructureProcessorList> processorLists = event.getServer().registryAccess().registry(Registries.PROCESSOR_LIST).get();
        addBuildingToPool(templatePools, processorLists, new ResourceLocation("minecraft:village/plains/houses"), "muffins_thaidelight:village/houses/thai_house_village", 4);

    }
}
