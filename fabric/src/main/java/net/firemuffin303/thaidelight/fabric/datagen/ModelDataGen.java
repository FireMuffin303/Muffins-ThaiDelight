package net.firemuffin303.thaidelight.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.firemuffin303.thaidelight.ThaiDelight;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
import net.firemuffin303.thaidelight.fabric.common.registry.ModItemsFabric;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Optional;

import static net.minecraft.data.models.model.TextureMapping.getBlockTexture;

public class ModelDataGen extends FabricModelProvider {
    private static final ModelTemplate PASTLE_3D = createModItem("pastle_3d_template", TextureSlot.LAYER0);
    private static final ModelTemplate SPAWN_EGG = createMincraftItem("template_spawn_egg");
    public ModelDataGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        createCrateBlock(ModBlocks.LIME_CRATE,blockStateModelGenerator);
        createCrateBlock(ModBlocks.PEPPER_CRATE,blockStateModelGenerator);
        createCrateBlock(ModBlocks.UNRIPE_PAPAYA_CRATE,blockStateModelGenerator);
        createCrateBlock(ModBlocks.PAPAYA_CRATE,blockStateModelGenerator);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.CRAB_SPAWN_EGG,SPAWN_EGG);
        itemModelGenerator.generateFlatItem(ModItems.CRAB_BUCKET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CRAB_MEAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COOKED_CRAB_MEAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CRAB_MEAT_WITH_SEAFOOD, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.DRAGONFLY_SPAWN_EGG,SPAWN_EGG);
        itemModelGenerator.generateFlatItem(ModItems.DRAGONFLY, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DRAGONFLY_BOTTLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COOKED_DRAGONFLY, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.SEAFOOD_BOTTLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.FISH_SAUCE_BOTTLE, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.LIME, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SLICED_LIME, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.LIME_SEED, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PEPPER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PEPPER_SEED, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.UNRIPE_PAPAYA,ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PAPAYA, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PAPAYA_SEED, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.LOINCLOTH, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItemsFabric.SOMTAM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItemsFabric.CRAB_FRIED_RICE, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.STONE_PASTLE,"_3d",PASTLE_3D);
        itemModelGenerator.generateFlatItem(ModItems.IRON_PASTLE,"_3d",PASTLE_3D);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_PASTLE,"_3d",PASTLE_3D);
        itemModelGenerator.generateFlatItem(ModItems.DIAMOND_PASTLE,"_3d",PASTLE_3D);
        itemModelGenerator.generateFlatItem(ModItems.NETHERITE_PASTLE,"_3d",PASTLE_3D);

        itemModelGenerator.generateFlatItem(ModItems.STONE_PASTLE,"_2d",ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.IRON_PASTLE,"_2d",ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_PASTLE,"_2d",ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DIAMOND_PASTLE,"_2d",ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NETHERITE_PASTLE,"_2d",ModelTemplates.FLAT_ITEM);
    }

    private static void createBlock(Block block,ModelTemplate modelTemplate,TextureMapping textureMapping, BlockModelGenerators blockModelGenerator){
        blockModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block,modelTemplate.create(block,textureMapping, blockModelGenerator.modelOutput)));

    }

    private static void createCubeAll(Block block, BlockModelGenerators blockModelGenerator){
        TextureMapping textureMapping = TextureMapping.cube(block);
        createBlock(block,ModelTemplates.CUBE_ALL,textureMapping,blockModelGenerator);
    }

    private static void createCrateBlock(Block block, BlockModelGenerators blockModelGenerator){
        TextureMapping textureMapping = new TextureMapping()
                .put(TextureSlot.SIDE, getBlockTexture(block, "_side"))
                .put(TextureSlot.TOP, getBlockTexture(block, "_top"))
                .put(TextureSlot.BOTTOM, new ResourceLocation("farmersdelight:block/crate_bottom"));
        createBlock(block,ModelTemplates.CUBE_BOTTOM_TOP,textureMapping,blockModelGenerator);
    }

    private static ModelTemplate createModItem(String string, TextureSlot... textureSlots) {
        return new ModelTemplate(Optional.of(new ResourceLocation(ThaiDelight.MOD_ID, "item/" + string)),Optional.empty(), textureSlots);
    }

    private static ModelTemplate createMincraftItem(String string, TextureSlot... textureSlots) {
        return new ModelTemplate(Optional.of(new ResourceLocation("item/" + string)),Optional.empty(), textureSlots);
    }

}
