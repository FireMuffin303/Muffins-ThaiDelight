package net.firemuffin303.thaidelight.datagen.provider;

import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import vectorwing.farmersdelight.common.block.CabinetBlock;

import java.util.function.Supplier;

public class ThaiDelightBlockModelProvider extends BlockStateProvider {

    protected void registerStatesAndModels() {
        for(Supplier<Block> blockSupplier : ModBlocks.CRATES){
            this.crateBlock(blockSupplier.get());
        }
    }
    private String blockName(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    public ResourceLocation resourceBlock(String path) {
        return ThaiDelightCommon.modid("block/" + path);
    }


    public void cabinetBlock(Block block, String woodType) {
        /*
        this.horizontalBlock(block, (state) -> {
            String suffix = (Boolean)state.getValue(CabinetBlock.OPEN) ? "_open" : "";
            return this.models().orientable(this.blockName(block) + suffix, this.resourceBlock(woodType + "_cabinet_side"), this.resourceBlock(woodType + "_cabinet_front" + suffix), this.resourceBlock(woodType + "_cabinet_top"));
        });

         */
    }

    public void crateBlock(Block block) {
        String id = this.blockName(block);
        //this.simpleBlock(block, this.models().cubeBottomTop(id, this.resourceBlock(id+"_side"), this.resourceBlock("crate_bottom"), this.resourceBlock(id+"_top")));
    }

    @Override
    protected BlockStateProviderType<?> type() {
        return null;
    }

    @Override
    public BlockState getState(RandomSource arg, BlockPos arg2) {
        return null;
    }
}
