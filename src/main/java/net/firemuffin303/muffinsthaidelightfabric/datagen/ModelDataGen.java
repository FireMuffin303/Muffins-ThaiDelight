package net.firemuffin303.muffinsthaidelightfabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.block.FermentedFishCauldronBlock;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.Optional;

import static net.minecraft.data.models.BlockModelGenerators.createHorizontalFacingDispatchAlt;
import static net.minecraft.data.models.BlockModelGenerators.createSimpleBlock;
import static net.minecraft.data.models.model.TextureMapping.getBlockTexture;

public class ModelDataGen extends FabricModelProvider {
    private static final ModelTemplate PASTLE_3D = createModItem("pastle_3d_template", TextureSlot.LAYER0);
    private static final ModelTemplate SPAWN_EGG = createMincraftItem("template_spawn_egg");
    private static final ModelTemplate LIME_BUSH_STAGE2 = new ModelTemplate(Optional.of(new ResourceLocation(ThaiDelight.MOD_ID,"block/template_lime_bush_stage2")),Optional.empty(),TextureSlot.SIDE,TextureSlot.TOP);
    public ModelDataGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        createCrateBlock(ModBlocks.LIME_CRATE,blockStateModelGenerator);
        createCrateBlock(ModBlocks.PEPPER_CRATE,blockStateModelGenerator);
        createCrateBlock(ModBlocks.RAW_PAPAYA_CRATE,blockStateModelGenerator);
        createCrateBlock(ModBlocks.PAPAYA_CRATE,blockStateModelGenerator);

        blockStateModelGenerator.createSimpleFlatItemModel(ModBlocks.CRAB_EGG);
        blockStateModelGenerator.blockStateOutput.accept(createSimpleBlock(ModBlocks.CRAB_EGG,ModelLocationUtils.getModelLocation(ModBlocks.CRAB_EGG)));

        blockStateModelGenerator.woodProvider(ModBlocks.PAPAYA_LOG).logWithHorizontal(ModBlocks.PAPAYA_LOG).wood(ModBlocks.PAPAYA_WOOD);
        blockStateModelGenerator.woodProvider(ModBlocks.STRIPPED_PAPAYA_LOG).logWithHorizontal(ModBlocks.STRIPPED_PAPAYA_LOG).wood(ModBlocks.STRIPPED_PAPAYA_WOOD);

        createCubeAll(ModBlocks.PAPAYA_LEAVES,blockStateModelGenerator);
        createCubeAll(ModBlocks.LIME_LEAVES,blockStateModelGenerator);

        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.PAPAYA)
                        .with(PropertyDispatch.property(BlockStateProperties.AGE_2)
                                .select(0, net.minecraft.data.models.blockstates.Variant.variant()
                                        .with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(ModBlocks.PAPAYA, "_stage0")))
                                .select(1, net.minecraft.data.models.blockstates.Variant.variant()
                                        .with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(ModBlocks.PAPAYA, "_stage1")))
                                .select(2, net.minecraft.data.models.blockstates.Variant.variant()
                                        .with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(ModBlocks.PAPAYA, "_stage2"))))
                        .with(createHorizontalFacingDispatchAlt()));



        blockStateModelGenerator.createCrossBlockWithDefaultItem(ModBlocks.WILD_PEPPER_CROP, BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createCrossBlockWithDefaultItem(ModBlocks.PAPAYA_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);

        createPepperCrop(blockStateModelGenerator);
        createLimeCrop(blockStateModelGenerator);

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.PAPAYA_CROP).with(PropertyDispatch.property(BlockStateProperties.AGE_1).generate((integer) -> {
            return net.minecraft.data.models.blockstates.Variant.variant().with(VariantProperties.MODEL,
                    blockStateModelGenerator.createSuffixedVariant(ModBlocks.PAPAYA_CROP, "_stage" + integer, new ModelTemplate(Optional.of(new ResourceLocation(ThaiDelight.MOD_ID,"block/crop_cross")),Optional.empty(),TextureSlot.CROSS), TextureMapping::cross));
        })));

        ResourceLocation fermentedLevel0 = new ResourceLocation(ThaiDelight.MOD_ID,"block/fermented_fish_cauldron_fermented0");
        ResourceLocation fermentedLevel1 = new ResourceLocation(ThaiDelight.MOD_ID,"block/fermented_fish_cauldron_fermented1");
        ResourceLocation fermentedLevel2 = new ResourceLocation(ThaiDelight.MOD_ID,"block/fermented_fish_cauldron_fermented2");

        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.FERMENTED_FISH_CAULDRON)
                        .with(PropertyDispatch.properties(FermentedFishCauldronBlock.LEVEL,FermentedFishCauldronBlock.FERMENT)
                                .select(1,0, Variant.variant().with(VariantProperties.MODEL,
                                        ModelTemplates.CAULDRON_LEVEL1.createWithSuffix(ModBlocks.FERMENTED_FISH_CAULDRON,"_fermented0_level1",
                                                TextureMapping.cauldron(fermentedLevel0), blockStateModelGenerator.modelOutput)))
                                .select(1,1,Variant.variant().with(VariantProperties.MODEL,
                                        ModelTemplates.CAULDRON_LEVEL1.createWithSuffix(ModBlocks.FERMENTED_FISH_CAULDRON,"_fermented1_level1",
                                                TextureMapping.cauldron(fermentedLevel1), blockStateModelGenerator.modelOutput)))
                                .select(1,2,Variant.variant().with(VariantProperties.MODEL,
                                        ModelTemplates.CAULDRON_LEVEL1.createWithSuffix(ModBlocks.FERMENTED_FISH_CAULDRON,"_fermented2_level1",
                                                TextureMapping.cauldron(fermentedLevel2), blockStateModelGenerator.modelOutput)))

                                .select(2,0, Variant.variant().with(VariantProperties.MODEL,
                                        ModelTemplates.CAULDRON_LEVEL2.createWithSuffix(ModBlocks.FERMENTED_FISH_CAULDRON,"_fermented0_level2",
                                                TextureMapping.cauldron(fermentedLevel0), blockStateModelGenerator.modelOutput)))
                                .select(2,1,Variant.variant().with(VariantProperties.MODEL,
                                        ModelTemplates.CAULDRON_LEVEL2.createWithSuffix(ModBlocks.FERMENTED_FISH_CAULDRON,"_fermented1_level2",
                                                TextureMapping.cauldron(fermentedLevel1), blockStateModelGenerator.modelOutput)))
                                .select(2,2,Variant.variant().with(VariantProperties.MODEL,
                                        ModelTemplates.CAULDRON_LEVEL2.createWithSuffix(ModBlocks.FERMENTED_FISH_CAULDRON,"_fermented2_level2",
                                                TextureMapping.cauldron(fermentedLevel2), blockStateModelGenerator.modelOutput)))

                                .select(3,0, Variant.variant().with(VariantProperties.MODEL,
                                        ModelTemplates.CAULDRON_FULL.createWithSuffix(ModBlocks.FERMENTED_FISH_CAULDRON,"_fermented0_level3",
                                                TextureMapping.cauldron(fermentedLevel0), blockStateModelGenerator.modelOutput)))
                                .select(3,1,Variant.variant().with(VariantProperties.MODEL,
                                        ModelTemplates.CAULDRON_FULL.createWithSuffix(ModBlocks.FERMENTED_FISH_CAULDRON,"_fermented1_level3",
                                                TextureMapping.cauldron(fermentedLevel1), blockStateModelGenerator.modelOutput)))
                                .select(3,2,Variant.variant().with(VariantProperties.MODEL,
                                        ModelTemplates.CAULDRON_FULL.createWithSuffix(ModBlocks.FERMENTED_FISH_CAULDRON,"_fermented2_level3",
                                                TextureMapping.cauldron(fermentedLevel2), blockStateModelGenerator.modelOutput)))
                        )
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        //Crab
        itemModelGenerator.generateFlatItem(ModItems.CRAB_SPAWN_EGG,SPAWN_EGG);
        itemModelGenerator.generateFlatItem(ModItems.CRAB_BUCKET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CRAB_MEAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COOKED_CRAB_MEAT, ModelTemplates.FLAT_ITEM);

        //Dragonfly
        itemModelGenerator.generateFlatItem(ModItems.DRAGONFLY_SPAWN_EGG,SPAWN_EGG);
        itemModelGenerator.generateFlatItem(ModItems.DRAGONFLY, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COOKED_DRAGONFLY, ModelTemplates.FLAT_ITEM);

        //Buffolo
        itemModelGenerator.generateFlatItem(ModItems.BUFFALO_SPAWN_EGG,SPAWN_EGG);

        itemModelGenerator.generateFlatItem(ModItems.FISH_SAUCE_BOTTLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.FERMENTED_FISH,ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.LIME, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SLICED_LIME, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.PEPPER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PEPPER_SEED, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.PAPAYA, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SLICED_PAPAYA, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAW_PAPAYA, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAW_PAPAYA_SLICE,ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PAPAYA_SEEDS,ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.PAPAYA_JUICE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.LIME_JUICE, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.SOMTAM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.LARB, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CRAB_FRIED_RICE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STIR_FRIED_NOODLE, ModelTemplates.FLAT_ITEM);


        itemModelGenerator.generateFlatItem(Item.byBlock(ModBlocks.SOMTAM_FEAST), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Item.byBlock(ModBlocks.LARB_FEAST), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Item.byBlock(ModBlocks.CRAB_FRIED_RICE_FEAST), ModelTemplates.FLAT_ITEM);

    }

    private static void createBlock(Block block, ModelTemplate modelTemplate, TextureMapping textureMapping, BlockModelGenerators blockModelGenerator){
        blockModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block,modelTemplate.create(block,textureMapping, blockModelGenerator.modelOutput)));

    }

    private static void createPepperCrop(BlockModelGenerators blockModelGenerators){
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.PEPPER_CROP).with(PropertyDispatch.property(BlockStateProperties.AGE_7).generate((integer) -> {
            return net.minecraft.data.models.blockstates.Variant.variant().with(VariantProperties.MODEL,
                    blockModelGenerators.createSuffixedVariant(ModBlocks.PEPPER_CROP, "_stage" + integer, new ModelTemplate(Optional.of(new ResourceLocation(ThaiDelight.MOD_ID,"block/crop_cross")),Optional.empty(),TextureSlot.CROSS), TextureMapping::cross));
        })));
    }

    private static void createLimeCrop( BlockModelGenerators blockModelGenerator){
        blockModelGenerator.createSimpleFlatItemModel(ModBlocks.LIME_SAPLING);
        createBlock(ModBlocks.LIME_SAPLING,ModelTemplates.CROSS,TextureMapping.cross(ModBlocks.LIME_SAPLING),blockModelGenerator);
        blockModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.LIME_BUSH)
                        .with(PropertyDispatch.property(BlockStateProperties.AGE_2)
                                .select(0,Variant.variant().with(VariantProperties.MODEL,
                                        blockModelGenerator.createSuffixedVariant(ModBlocks.LIME_BUSH,"_stage0",ModelTemplates.AZALEA, resourceLocation -> limeTextureMapping(0))))
                                .select(1,Variant.variant().with(VariantProperties.MODEL,
                                        blockModelGenerator.createSuffixedVariant(ModBlocks.LIME_BUSH,"_stage1",ModelTemplates.AZALEA, resourceLocation -> limeTextureMapping(1))))
                                .select(2,Variant.variant().with(VariantProperties.MODEL,
                                        blockModelGenerator.createSuffixedVariant(ModBlocks.LIME_BUSH,"_stage2",LIME_BUSH_STAGE2,resourceLocation -> limeTextureMapping(0)))
                        )));


    }

    private static TextureMapping limeTextureMapping(int level){
        return new TextureMapping().put(TextureSlot.SIDE, getBlockTexture(ModBlocks.LIME_BUSH, "_side_stage" + level))
                .put(TextureSlot.TOP, getBlockTexture(ModBlocks.LIME_BUSH, "_top_stage" + level));
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
