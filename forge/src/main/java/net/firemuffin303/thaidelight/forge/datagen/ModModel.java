package net.firemuffin303.thaidelight.forge.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.util.datafix.fixes.BlockEntityKeepPacked;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModModel extends BlockModelProvider {
    public ModModel(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
    }
}
