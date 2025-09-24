package net.firemuffin303.muffinsthaidelightfabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.block.BasilCropBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.FermentedFishCauldronBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.coconut.CoconutLeafBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.lime.LimePlantBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.mango.MangoBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.papaya.PapayaLogBlock;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModBlocks;
import net.firemuffin303.muffinsthaidelightfabric.registry.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
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
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

import java.util.Optional;

import static net.minecraft.data.models.BlockModelGenerators.createHorizontalFacingDispatchAlt;
import static net.minecraft.data.models.BlockModelGenerators.createSimpleBlock;
import static net.minecraft.data.models.model.TextureMapping.getBlockTexture;

public class ModelDataGen extends FabricModelProvider {
    private static final TextureSlot FLOWER = TextureSlot.create("flower");

    private static final ModelTemplate PASTLE_3D = createModItem("pastle_3d_template", TextureSlot.LAYER0);
    private static final ModelTemplate SPAWN_EGG = createMincraftItem("template_spawn_egg");
    private static final ModelTemplate LIME_BUSH_STAGE2 = new ModelTemplate(Optional.of(new ResourceLocation(ThaiDelight.MOD_ID,"block/template_lime_bush_stage2")),Optional.empty(),TextureSlot.SIDE,TextureSlot.TOP);
    private static final ModelTemplate LIME_UPPER_TEMPLATE = new ModelTemplate(Optional.of(ThaiDelight.modid("block/lime/template_lime_upper")),Optional.empty(),TextureSlot.SIDE,TextureSlot.TOP,TextureSlot.PLANT);
    private static final ModelTemplate LIME_BOTTOM_TEMPLATE = new ModelTemplate(Optional.of(ThaiDelight.modid("block/lime/template_lime_bottom")),Optional.empty(),TextureSlot.SIDE,TextureSlot.BOTTOM,TextureSlot.STEM,TextureSlot.PLANT);

    private static final ModelTemplate BASIL_TEMPLATE = new ModelTemplate(Optional.of(ThaiDelight.modid("block/basil/template_basil")),Optional.empty(),TextureSlot.SIDE,TextureSlot.BOTTOM,TextureSlot.TOP,TextureSlot.STEM,FLOWER);

    public static  final  ModelTemplate HANGING_MANGO = new ModelTemplate(Optional.of(ThaiDelight.modid("block/template_hanging_mango")),Optional.empty(),TextureSlot.ALL);
    public static  final  ModelTemplate MANGO = new ModelTemplate(Optional.of(ThaiDelight.modid("block/template_mango")),Optional.empty(),TextureSlot.ALL);

    private static final BlockFamily DURIAN_PLANKS = BlockFamilies.familyBuilder(ModBlocks.DURIAN_PLANKS)
            .button(ModBlocks.DURIAN_BUTTON)
            .fence(ModBlocks.DURIAN_FENCE)
            .fenceGate(ModBlocks.DURIAN_FENCE_GATE)
            .pressurePlate(ModBlocks.DURIAN_PRESSURE_PLATE)
            .sign(ModBlocks.DURIAN_SIGN,ModBlocks.DURIAN_WALL_SIGN)
            .slab(ModBlocks.DURIAN_SLAB)
            .stairs(ModBlocks.DURIAN_STAIRS)
            .door(ModBlocks.DURIAN_DOOR)
            .trapdoor(ModBlocks.DURIAN_TRAPDOOR)
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();

    private static final BlockFamily COCONUT_PLANKS = BlockFamilies.familyBuilder(ModBlocks.COCONUT_PLANKS)
            .button(ModBlocks.COCONUT_BUTTON)
            .fence(ModBlocks.COCONUT_FENCE)
            .fenceGate(ModBlocks.COCONUT_FENCE_GATE)
            .pressurePlate(ModBlocks.COCONUT_PRESSURE_PLATE)
            .sign(ModBlocks.COCONUT_SIGN,ModBlocks.COCONUT_WALL_SIGN)
            .slab(ModBlocks.COCONUT_SLAB)
            .stairs(ModBlocks.COCONUT_STAIRS)
            .door(ModBlocks.COCONUT_DOOR)
            .trapdoor(ModBlocks.COCONUT_TRAPDOOR)
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();

    private static final BlockFamily MANGO_PLANKS = BlockFamilies.familyBuilder(ModBlocks.MANGO_PLANKS)
            .button(ModBlocks.MANGO_BUTTON)
            .fence(ModBlocks.MANGO_FENCE)
            .fenceGate(ModBlocks.MANGO_FENCE_GATE)
            .pressurePlate(ModBlocks.MANGO_PRESSURE_PLATE)
            .sign(ModBlocks.MANGO_SIGN,ModBlocks.MANGO_WALL_SIGN)
            .slab(ModBlocks.MANGO_SLAB)
            .stairs(ModBlocks.MANGO_STAIRS)
            .door(ModBlocks.MANGO_DOOR)
            .trapdoor(ModBlocks.MANGO_TRAPDOOR)
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();

    public ModelDataGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        createCrateBlock(ModBlocks.LIME_CRATE,blockStateModelGenerator);
        createCrateBlock(ModBlocks.PEPPER_CRATE,blockStateModelGenerator);
        createCrateBlock(ModBlocks.RAW_PAPAYA_CRATE,blockStateModelGenerator);
        createCrateBlock(ModBlocks.PAPAYA_CRATE,blockStateModelGenerator);
        createCrateBlock(ModBlocks.DURIAN_CRATE,blockStateModelGenerator);
        createCrateBlock(ModBlocks.MANGO_CRATE,blockStateModelGenerator);
        createCrateBlock(ModBlocks.COCONUT_CRATE,blockStateModelGenerator);
        createCrateBlock(ModBlocks.HOLY_BASIL_CRATE,blockStateModelGenerator);
        createCrateBlock(ModBlocks.BASIL_CRATE,blockStateModelGenerator);

        blockStateModelGenerator.createSimpleFlatItemModel(ModBlocks.CRAB_EGG);
        blockStateModelGenerator.blockStateOutput.accept(createSimpleBlock(ModBlocks.CRAB_EGG,ModelLocationUtils.getModelLocation(ModBlocks.CRAB_EGG)));

        TextureMapping papaya_bottom = new TextureMapping()
                .put(TextureSlot.SIDE, getBlockTexture(ModBlocks.PAPAYA_LOG,"_bottom"))
                .put(TextureSlot.END, getBlockTexture(ModBlocks.PAPAYA_LOG, "_top"))
                .put(TextureSlot.PARTICLE, getBlockTexture(ModBlocks.PAPAYA_LOG,"_bottom"));
        TextureMapping papaya_log_mapping = new TextureMapping()
                .put(TextureSlot.SIDE, getBlockTexture(ModBlocks.PAPAYA_LOG))
                .put(TextureSlot.END, getBlockTexture(ModBlocks.PAPAYA_LOG, "_top"))
                .put(TextureSlot.PARTICLE, getBlockTexture(ModBlocks.PAPAYA_LOG));
        ResourceLocation papaya_log_bottom = ModelTemplates.CUBE_COLUMN.createWithSuffix(ModBlocks.PAPAYA_LOG,"_bottom", papaya_bottom, blockStateModelGenerator.modelOutput);
        ResourceLocation papaya_log = ModelTemplates.CUBE_COLUMN.create(ModBlocks.PAPAYA_LOG,papaya_log_mapping, blockStateModelGenerator.modelOutput);

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.PAPAYA_LOG)
                .with(PropertyDispatch.properties(PapayaLogBlock.AXIS,PapayaLogBlock.BOTTOM)
                        .select(Direction.Axis.X,true,Variant.variant()
                                .with(VariantProperties.MODEL,papaya_log_bottom)
                                .with(VariantProperties.X_ROT,VariantProperties.Rotation.R90)
                                .with(VariantProperties.Y_ROT,VariantProperties.Rotation.R90)
                        )
                        .select(Direction.Axis.X,false,Variant.variant()
                                .with(VariantProperties.MODEL,papaya_log)
                                .with(VariantProperties.X_ROT,VariantProperties.Rotation.R90)
                                .with(VariantProperties.Y_ROT,VariantProperties.Rotation.R90)
                        )
                        .select(Direction.Axis.Y,true,Variant.variant()
                                .with(VariantProperties.MODEL,papaya_log_bottom)
                        )
                        .select(Direction.Axis.Y,false,Variant.variant()
                                .with(VariantProperties.MODEL,papaya_log)
                        )
                        .select(Direction.Axis.Z,true,Variant.variant()
                                .with(VariantProperties.MODEL,papaya_log_bottom)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                        )
                        .select(Direction.Axis.Z,false,Variant.variant()
                                .with(VariantProperties.MODEL,papaya_log)
                                .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                        )
                )
        );

        blockStateModelGenerator.woodProvider(ModBlocks.PAPAYA_LOG).wood(ModBlocks.PAPAYA_WOOD);
        blockStateModelGenerator.woodProvider(ModBlocks.STRIPPED_PAPAYA_LOG).logWithHorizontal(ModBlocks.STRIPPED_PAPAYA_LOG).wood(ModBlocks.STRIPPED_PAPAYA_WOOD);

        //Durian Model
        blockStateModelGenerator.woodProvider(ModBlocks.DURIAN_LOG).logWithHorizontal(ModBlocks.DURIAN_LOG).wood(ModBlocks.DURIAN_WOOD);
        blockStateModelGenerator.woodProvider(ModBlocks.STRIPPED_DURIAN_LOG).logWithHorizontal(ModBlocks.STRIPPED_DURIAN_LOG).wood(ModBlocks.STRIPPED_DURIAN_WOOD);
        blockStateModelGenerator.createHangingSign(ModBlocks.STRIPPED_DURIAN_LOG,ModBlocks.DURIAN_HANGING_SIGN,ModBlocks.DURIAN_WALL_HANGING_SIGN);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.DURIAN_LEAVES,TexturedModel.LEAVES);
        blockStateModelGenerator.createPlant(ModBlocks.DURIAN_SAPLING,ModBlocks.POTTED_DURIAN_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.family(ModBlocks.DURIAN_PLANKS).generateFor(DURIAN_PLANKS);

        blockStateModelGenerator.woodProvider(ModBlocks.COCONUT_LOG).logWithHorizontal(ModBlocks.COCONUT_LOG).wood(ModBlocks.COCONUT_WOOD);
        blockStateModelGenerator.woodProvider(ModBlocks.STRIPPED_COCONUT_LOG).logWithHorizontal(ModBlocks.STRIPPED_COCONUT_LOG).wood(ModBlocks.STRIPPED_COCONUT_WOOD);
        blockStateModelGenerator.createHangingSign(ModBlocks.STRIPPED_COCONUT_LOG,ModBlocks.COCONUT_HANGING_SIGN,ModBlocks.COCONUT_WALL_HANGING_SIGN);
        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.COCONUT_LEAF)
                        .with(PropertyDispatch.properties(CoconutLeafBlock.FACING,CoconutLeafBlock.END)
                                .select(Direction.NORTH,false,Variant.variant()
                                        .with(VariantProperties.MODEL, BuiltInRegistries.BLOCK.getKey(ModBlocks.COCONUT_LEAF).withPrefix("block/coconut/"))
                                )
                                .select(Direction.NORTH,true,Variant.variant()
                                        .with(VariantProperties.MODEL,BuiltInRegistries.BLOCK.getKey(ModBlocks.COCONUT_LEAF).withPrefix("block/coconut/").withSuffix("_end"))
                                )
                                .select(Direction.SOUTH,false,Variant.variant()
                                        .with(VariantProperties.MODEL,BuiltInRegistries.BLOCK.getKey(ModBlocks.COCONUT_LEAF).withPrefix("block/coconut/"))
                                        .with(VariantProperties.Y_ROT,VariantProperties.Rotation.R180)
                                )
                                .select(Direction.SOUTH,true,Variant.variant()
                                        .with(VariantProperties.MODEL,BuiltInRegistries.BLOCK.getKey(ModBlocks.COCONUT_LEAF).withPrefix("block/coconut/").withSuffix("_end"))
                                        .with(VariantProperties.Y_ROT,VariantProperties.Rotation.R180)
                                )
                                .select(Direction.EAST,false,Variant.variant()
                                        .with(VariantProperties.MODEL,BuiltInRegistries.BLOCK.getKey(ModBlocks.COCONUT_LEAF).withPrefix("block/coconut/"))
                                        .with(VariantProperties.Y_ROT,VariantProperties.Rotation.R90)
                                )
                                .select(Direction.EAST,true,Variant.variant()
                                        .with(VariantProperties.MODEL,BuiltInRegistries.BLOCK.getKey(ModBlocks.COCONUT_LEAF).withPrefix("block/coconut/").withSuffix("_end"))
                                        .with(VariantProperties.Y_ROT,VariantProperties.Rotation.R90)
                                )
                                .select(Direction.WEST,false,Variant.variant()
                                        .with(VariantProperties.MODEL,BuiltInRegistries.BLOCK.getKey(ModBlocks.COCONUT_LEAF).withPrefix("block/coconut/"))
                                        .with(VariantProperties.Y_ROT,VariantProperties.Rotation.R270)
                                )
                                .select(Direction.WEST,true,Variant.variant()
                                        .with(VariantProperties.MODEL,BuiltInRegistries.BLOCK.getKey(ModBlocks.COCONUT_LEAF).withPrefix("block/coconut/").withSuffix("_end"))
                                        .with(VariantProperties.Y_ROT,VariantProperties.Rotation.R270)
                                )
                        )
        );
        blockStateModelGenerator.family(ModBlocks.COCONUT_PLANKS).generateFor(COCONUT_PLANKS);

        blockStateModelGenerator.woodProvider(ModBlocks.MANGO_LOG).logWithHorizontal(ModBlocks.MANGO_LOG).wood(ModBlocks.MANGO_WOOD);
        blockStateModelGenerator.woodProvider(ModBlocks.STRIPPED_MANGO_LOG).logWithHorizontal(ModBlocks.STRIPPED_MANGO_LOG).wood(ModBlocks.STRIPPED_MANGO_WOOD);
        blockStateModelGenerator.createHangingSign(ModBlocks.STRIPPED_MANGO_LOG,ModBlocks.MANGO_HANGING_SIGN,ModBlocks.MANGO_WALL_HANGING_SIGN);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.MANGO_LEAVES,TexturedModel.LEAVES);
        blockStateModelGenerator.family(ModBlocks.MANGO_PLANKS).generateFor(MANGO_PLANKS);


        createCubeAll(ModBlocks.PAPAYA_LEAVES,blockStateModelGenerator);
        //createCubeAll(ModBlocks.LIME_LEAVES,blockStateModelGenerator);

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



        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.DURIAN_BLOCK);
        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.DURIAN_BLOCK)
                        .with(PropertyDispatch.properties(BlockStateProperties.AGE_2,BlockStateProperties.HANGING)
                                .select(0,true,Variant.variant()
                                        .with(VariantProperties.MODEL,ModelLocationUtils.getModelLocation(ModBlocks.DURIAN_BLOCK,"_stage0_hanging")))
                                .select(1,true,Variant.variant()
                                        .with(VariantProperties.MODEL,ModelLocationUtils.getModelLocation(ModBlocks.DURIAN_BLOCK,"_stage1_hanging")))
                                .select(2,true,Variant.variant()
                                        .with(VariantProperties.MODEL,ModelLocationUtils.getModelLocation(ModBlocks.DURIAN_BLOCK,"_stage2_hanging")))
                                .select(0,false,Variant.variant()
                                        .with(VariantProperties.MODEL,ModelLocationUtils.getModelLocation(ModBlocks.DURIAN_BLOCK,"_stage0")))
                                .select(1,false,Variant.variant()
                                        .with(VariantProperties.MODEL,ModelLocationUtils.getModelLocation(ModBlocks.DURIAN_BLOCK,"_stage1")))
                                .select(2,false,Variant.variant()
                                        .with(VariantProperties.MODEL,ModelLocationUtils.getModelLocation(ModBlocks.DURIAN_BLOCK,"_stage2")))
                        )
        );



        blockStateModelGenerator.createSimpleFlatItemModel(ModBlocks.DURIAN_FLOWER);
        ResourceLocation durian_flower_resource = BlockModelGenerators.TintState.NOT_TINTED.getCross().create(ModBlocks.DURIAN_FLOWER, TextureMapping.cross(ModBlocks.DURIAN_FLOWER), blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.DURIAN_FLOWER)
                        .with(PropertyDispatch.property(BlockStateProperties.HANGING)
                                .select(false,Variant.variant().with(VariantProperties.MODEL,durian_flower_resource))
                                .select(true,Variant.variant().with(VariantProperties.MODEL,durian_flower_resource).with(VariantProperties.X_ROT,VariantProperties.Rotation.R180))
                        )
        );

        blockStateModelGenerator.createCrossBlockWithDefaultItem(ModBlocks.WILD_PEPPER_CROP, BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createCrossBlockWithDefaultItem(ModBlocks.PAPAYA_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);

        createMangoBlock(blockStateModelGenerator);
        createPepperCrop(blockStateModelGenerator);
        createLimeCrop(blockStateModelGenerator);
        //createBasil(blockStateModelGenerator,ModBlocks.HOLY_BASIL);
        createBasil(blockStateModelGenerator,ModBlocks.BASIL);

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




        blockStateModelGenerator.createPlant(ModBlocks.LIME_SAPLING,ModBlocks.POTTED_LIME_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(ModBlocks.COCONUT_SAPLING,ModBlocks.POTTED_COCONUT_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(ModBlocks.MANGO_SAPLING,ModBlocks.POTTED_MANGO_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);

        blockStateModelGenerator.createTrivialBlock(ModBlocks.LIME_LEAVES,TexturedModel.LEAVES);
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
        //itemModelGenerator.generateFlatItem(ModItems.BUFFALO_SPAWN_EGG,SPAWN_EGG);

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

        itemModelGenerator.generateFlatItem(ModItems.FRIED_DURIAN,ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.SOMTAM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.LARB, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CRAB_FRIED_RICE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STIR_FRIED_NOODLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PHAT_KAPHRAO,ModelTemplates.FLAT_ITEM);


        itemModelGenerator.generateFlatItem(Item.byBlock(ModBlocks.SOMTAM_FEAST), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Item.byBlock(ModBlocks.LARB_FEAST), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Item.byBlock(ModBlocks.CRAB_FRIED_RICE_FEAST), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.DURIAN_PULP,ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DURIAN_BOAT,ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DURIAN_CHEST_BOAT,ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.MANGO,ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MANGO_SLICE,ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MANGO_STICKY_RICE,ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COCONUT,ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COCONUT_SLICE,ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COCONUT_MILK_BOTTLE,ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.HOLY_BASIL_SAPLING,ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.HOLY_BASIL,ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BASIL_SAPLING,ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BASIL,ModelTemplates.FLAT_ITEM);
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

    private static TextureMapping upperLimeMapping(int age){
        return new TextureMapping()
                .put(TextureSlot.SIDE,ThaiDelight.modid("block/lime/lime_leaves_side_age"+ age))
                .put(TextureSlot.TOP,ThaiDelight.modid("block/lime/lime_leaves_top"))
                .put(TextureSlot.PLANT,ThaiDelight.modid("block/lime/lime_leaves_layer"));
    }

    private static TextureMapping bottomLimeMapping(int age){
        return new TextureMapping()
                .put(TextureSlot.SIDE,ThaiDelight.modid("block/lime/lime_leaves_side_age"+ age))
                .put(TextureSlot.BOTTOM,ThaiDelight.modid("block/lime/lime_leaves_bottom"))
                .put(TextureSlot.STEM,ThaiDelight.modid("block/lime/lime_stem"))
                .put(TextureSlot.PLANT,ThaiDelight.modid("block/lime/lime_leaves_layer"));
    }

    private static void createBasil(BlockModelGenerators blockModelGenerators,Block block){
        TextureMapping textureMapping = new TextureMapping()
                .put(TextureSlot.STEM,ThaiDelight.modid("block/basil/basil_stem"))
                .put(TextureSlot.SIDE,ThaiDelight.modid("block/basil/basil_leaves_side"))
                .put(TextureSlot.TOP,ThaiDelight.modid("block/basil/basil_leaves_top"))
                .put(TextureSlot.BOTTOM,ThaiDelight.modid("block/basil/basil_leaves_bottom"))
                .put(FLOWER,ThaiDelight.modid("block/basil/basil_flower"));

        ResourceLocation BASIL_AGE0 = BASIL_TEMPLATE.create(ThaiDelight.modid("block/basil/basil_age0"),textureMapping, blockModelGenerators.modelOutput);
        ResourceLocation BASIL_AGE1 = BASIL_TEMPLATE.create(ThaiDelight.modid("block/basil/basil_age1"),textureMapping, blockModelGenerators.modelOutput);
        ResourceLocation BASIL_AGE2 = BASIL_TEMPLATE.create(ThaiDelight.modid("block/basil/basil_age2"),textureMapping, blockModelGenerators.modelOutput);
        ResourceLocation BASIL_AGE3 = BASIL_TEMPLATE.create(ThaiDelight.modid("block/basil/basil_age3"),textureMapping, blockModelGenerators.modelOutput);

        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.property(BasilCropBlock.AGE)
                        .select(0,Variant.variant().with(VariantProperties.MODEL,BASIL_AGE0))
                        .select(1,Variant.variant().with(VariantProperties.MODEL,BASIL_AGE1))
                        .select(2,Variant.variant().with(VariantProperties.MODEL,BASIL_AGE2))
                        .select(3,Variant.variant().with(VariantProperties.MODEL,BASIL_AGE3))
                )
        );
    }

    private static void createLimeCrop( BlockModelGenerators blockModelGenerator){


        ResourceLocation LIME_UPPER_AGE0 = LIME_UPPER_TEMPLATE.create(ThaiDelight.modid("block/lime/lime_upper_age0"),upperLimeMapping(0), blockModelGenerator.modelOutput);
        ResourceLocation LIME_UPPER_AGE1 = LIME_UPPER_TEMPLATE.create(ThaiDelight.modid("block/lime/lime_upper_age1"),upperLimeMapping(1), blockModelGenerator.modelOutput);
        ResourceLocation LIME_BOTTOM_AGE0 = LIME_BOTTOM_TEMPLATE.create(ThaiDelight.modid("block/lime/lime_bottom_age0"),bottomLimeMapping(0),blockModelGenerator.modelOutput);
        ResourceLocation LIME_BOTTOM_AGE1 = LIME_BOTTOM_TEMPLATE.create(ThaiDelight.modid("block/lime/lime_bottom_age1"),bottomLimeMapping(1),blockModelGenerator.modelOutput);

        //blockModelGenerator.createSimpleFlatItemModel(ModBlocks.LIME_SAPLING);
        //createBlock(ModBlocks.LIME_SAPLING,ModelTemplates.CROSS,TextureMapping.cross(ModBlocks.LIME_SAPLING),blockModelGenerator);

        blockModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.LIME_PLANT)
                .with(PropertyDispatch.properties(LimePlantBlock.AGE,LimePlantBlock.HALF)
                        .select(0, DoubleBlockHalf.LOWER,Variant.variant()
                                .with(VariantProperties.MODEL,LIME_BOTTOM_AGE0)
                        )
                        .select(0,DoubleBlockHalf.UPPER,Variant.variant()
                                .with(VariantProperties.MODEL,LIME_UPPER_AGE0)
                        )
                        .select(1, DoubleBlockHalf.LOWER,Variant.variant()
                                .with(VariantProperties.MODEL,LIME_BOTTOM_AGE1)
                        )
                        .select(1,DoubleBlockHalf.UPPER,Variant.variant()
                                .with(VariantProperties.MODEL,LIME_UPPER_AGE1)
                        )
                        .select(2, DoubleBlockHalf.LOWER,Variant.variant()
                                .with(VariantProperties.MODEL,ThaiDelight.modid("block/lime/lime_bottom_age2"))
                        )
                        .select(2,DoubleBlockHalf.UPPER,Variant.variant()
                                .with(VariantProperties.MODEL,ThaiDelight.modid("block/lime/lime_upper_age2"))
                        )
                )
        );


    }

    private static void createMangoBlock(BlockModelGenerators blockModelGenerators){
        TextureMapping raw_mango_texture = TextureMapping.cube(ThaiDelight.modid("block/raw_mango"));
        TextureMapping mango_texture = TextureMapping.cube(ThaiDelight.modid("block/mango"));

        ResourceLocation hanging_raw_mango = HANGING_MANGO.create(ThaiDelight.modid("block/hanging_mango_age1"),raw_mango_texture, blockModelGenerators.modelOutput);
        ResourceLocation raw_mango = MANGO.create(ThaiDelight.modid("block/mango_age1"),raw_mango_texture, blockModelGenerators.modelOutput);

        ResourceLocation mango = MANGO.create(ThaiDelight.modid("block/mango_age2"),mango_texture, blockModelGenerators.modelOutput);
        ResourceLocation hanging_mango = HANGING_MANGO.create(ThaiDelight.modid("block/hanging_mango_age2"),mango_texture, blockModelGenerators.modelOutput);

        blockModelGenerators.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.MANGO_BLOCK)
                        .with(PropertyDispatch.properties(MangoBlock.AGE,MangoBlock.HANGING,MangoBlock.FACING)
                                //Hanging Raw Mango
                                .select(0,false, Direction.NORTH,Variant.variant()
                                        .with(VariantProperties.MODEL,ThaiDelight.modid("block/mango_age0"))
                                )
                                .select(0,false,Direction.EAST,Variant.variant()
                                        .with(VariantProperties.MODEL,ThaiDelight.modid("block/mango_age0"))
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                )
                                .select(0,false,Direction.SOUTH,Variant.variant()
                                        .with(VariantProperties.MODEL,ThaiDelight.modid("block/mango_age0"))
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                )
                                .select(0,false,Direction.WEST,Variant.variant()
                                        .with(VariantProperties.MODEL,ThaiDelight.modid("block/mango_age0"))
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                )

                                //Ground Raw Mango
                                .select(0,true, Direction.NORTH,Variant.variant()
                                        .with(VariantProperties.MODEL,ThaiDelight.modid("block/hanging_mango_age0"))
                                )
                                .select(0,true,Direction.EAST,Variant.variant()
                                        .with(VariantProperties.MODEL,ThaiDelight.modid("block/hanging_mango_age0"))
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                )
                                .select(0,true,Direction.SOUTH,Variant.variant()
                                        .with(VariantProperties.MODEL,ThaiDelight.modid("block/hanging_mango_age0"))
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                )
                                .select(0,true,Direction.WEST,Variant.variant()
                                        .with(VariantProperties.MODEL,ThaiDelight.modid("block/hanging_mango_age0"))
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                )


                                //Hanging Raw Mango
                                .select(1,false, Direction.NORTH,Variant.variant()
                                        .with(VariantProperties.MODEL,raw_mango)
                                )
                                .select(1,false,Direction.EAST,Variant.variant()
                                        .with(VariantProperties.MODEL,raw_mango)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                )
                                .select(1,false,Direction.SOUTH,Variant.variant()
                                        .with(VariantProperties.MODEL,raw_mango)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                )
                                .select(1,false,Direction.WEST,Variant.variant()
                                        .with(VariantProperties.MODEL,raw_mango)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                )

                                //Ground Raw Mango
                                .select(1,true, Direction.NORTH,Variant.variant()
                                        .with(VariantProperties.MODEL,hanging_raw_mango)
                                )
                                .select(1,true,Direction.EAST,Variant.variant()
                                        .with(VariantProperties.MODEL,hanging_raw_mango)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                )
                                .select(1,true,Direction.SOUTH,Variant.variant()
                                        .with(VariantProperties.MODEL,hanging_raw_mango)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                )
                                .select(1,true,Direction.WEST,Variant.variant()
                                        .with(VariantProperties.MODEL,hanging_raw_mango)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                )
                                //--------------------
                                //Ground Mango
                                .select(2,false, Direction.NORTH,Variant.variant()
                                        .with(VariantProperties.MODEL,mango)
                                )
                                .select(2,false,Direction.EAST,Variant.variant()
                                        .with(VariantProperties.MODEL,mango)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                )
                                .select(2,false,Direction.SOUTH,Variant.variant()
                                        .with(VariantProperties.MODEL,mango)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                )
                                .select(2,false,Direction.WEST,Variant.variant()
                                        .with(VariantProperties.MODEL,mango)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                )

                                //Hanging Mango
                                .select(2,true, Direction.NORTH,Variant.variant()
                                        .with(VariantProperties.MODEL,hanging_mango)
                                )
                                .select(2,true,Direction.EAST,Variant.variant()
                                        .with(VariantProperties.MODEL,hanging_mango)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                )
                                .select(2,true,Direction.SOUTH,Variant.variant()
                                        .with(VariantProperties.MODEL,hanging_mango)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                )
                                .select(2,true,Direction.WEST,Variant.variant()
                                        .with(VariantProperties.MODEL,hanging_mango)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                )
                        )
        );
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
