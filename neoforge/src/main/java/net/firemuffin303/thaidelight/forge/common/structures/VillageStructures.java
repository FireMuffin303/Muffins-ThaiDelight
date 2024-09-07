package net.firemuffin303.thaidelight.forge.common.structures;

import net.firemuffin303.thaidelight.ThaiDelight;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraftforge.event.server.ServerAboutToStartEvent;

import static vectorwing.farmersdelight.common.world.VillageStructures.addBuildingToPool;

public class VillageStructures {

    public static void addNewVillageBuilding(ServerAboutToStartEvent event) {
        ThaiDelight.registerStructure(event.getServer());
    }
}
