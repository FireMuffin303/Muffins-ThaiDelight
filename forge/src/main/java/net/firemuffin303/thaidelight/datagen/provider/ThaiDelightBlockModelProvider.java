package net.firemuffin303.thaidelight.datagen.provider;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.common.block.CabinetBlock;

import java.util.function.Supplier;

import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

public class ThaiDelightBlockModelProvider extends BlockStateProvider {
    public ThaiDelightBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ThaiDelightCommon.MOD_ID, existingFileHelper);
    }
    @Override
    protected void registerStatesAndModels() {
        for(Supplier<Block> blockSupplier : ModBlocks.CRATES){
            this.crateBlock(blockSupplier.get());
        }
    }
    private String blockName(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    public ResourceLocation resourceBlock(String path) {
        return ThaiDelightCommon.modid("block/" + path);
    }


    public void cabinetBlock(Block block, String woodType) {
        this.horizontalBlock(block, (state) -> {
            String suffix = (Boolean)state.getValue(CabinetBlock.OPEN) ? "_open" : "";
            return this.models().orientable(this.blockName(block) + suffix, this.resourceBlock(woodType + "_cabinet_side"), this.resourceBlock(woodType + "_cabinet_front" + suffix), this.resourceBlock(woodType + "_cabinet_top"));
        });
    }

    public void crateBlock(Block block) {
        String id = this.blockName(block);
        this.simpleBlock(block, this.models().cubeBottomTop(id, this.resourceBlock(id+"_side"), this.resourceBlock("crate_bottom"), this.resourceBlock(id+"_top")));
    }
}
