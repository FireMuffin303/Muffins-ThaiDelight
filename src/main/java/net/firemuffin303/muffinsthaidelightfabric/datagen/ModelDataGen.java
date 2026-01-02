package net.firemuffin303.muffinsthaidelightfabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.block.BasilCropBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.FermentedFishCauldronBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.SackBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.coconut.BuddingCoconutLeafBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.durian.SmallDurianBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.butterfly_pea.ButterflyPeaVineBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.coconut.CoconutLeafBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.lime.LimeBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.lime.LimePlantBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.mango.HangingMangoBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.mango.StackableMangoBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.papaya.*;
import net.firemuffin303.muffinsthaidelightfabric.common.block.pepper.BuddingPepperBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.pepper.PepperCropBlock;
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
import vectorwing.farmersdelight.common.block.FeastBlock;

import java.util.Optional;

import static net.minecraft.data.models.BlockModelGenerators.*;
import static net.minecraft.data.models.model.TextureMapping.getBlockTexture;

public class ModelDataGen extends FabricModelProvider {
    private static final TextureSlot FLOWER = TextureSlot.create("flower");
    private static final TextureSlot VINE = TextureSlot.create("vine");
    private static final TextureSlot ROPE_SIDE = TextureSlot.create("rope_side");
    private static final TextureSlot ROPE_TOP = TextureSlot.create("rope_top");

    private static final ModelTemplate PASTLE_3D = createModItem("pastle_3d_template", TextureSlot.LAYER0);
    private static final ModelTemplate SPAWN_EGG = createMincraftItem("template_spawn_egg");
    private static final ModelTemplate LIME_BUSH_STAGE2 = new ModelTemplate(Optional.of(new ResourceLocation(ThaiDelight.MOD_ID,"block/template_lime_bush_stage2")),Optional.empty(),TextureSlot.SIDE,TextureSlot.TOP);
    private static final ModelTemplate LIME_UPPER_TEMPLATE = new ModelTemplate(Optional.of(ThaiDelight.modid("block/lime/template_lime_upper")),Optional.empty(),TextureSlot.SIDE,TextureSlot.TOP,TextureSlot.PLANT);
    private static final ModelTemplate LIME_BOTTOM_TEMPLATE = new ModelTemplate(Optional.of(ThaiDelight.modid("block/lime/template_lime_bottom")),Optional.empty(),TextureSlot.SIDE,TextureSlot.BOTTOM,TextureSlot.STEM,TextureSlot.PLANT);

    private static final ModelTemplate BASIL_TEMPLATE = new ModelTemplate(Optional.of(ThaiDelight.modid("block/basil/template_basil")),Optional.empty(),TextureSlot.SIDE,TextureSlot.BOTTOM,TextureSlot.TOP,TextureSlot.STEM,FLOWER);

    public static  final  ModelTemplate HANGING_MANGO = new ModelTemplate(Optional.of(ThaiDelight.modid("block/template_hanging_mango")),Optional.empty(),TextureSlot.ALL);
    public static  final  ModelTemplate MANGO = new ModelTemplate(Optional.of(ThaiDelight.modid("block/template_mango")),Optional.empty(),TextureSlot.ALL);

    private static final ModelTemplate WALL_FLOWER = new ModelTemplate(Optional.of(ThaiDelight.modid("block/template_wall_flower")),Optional.empty(),VINE,FLOWER);

    private static final ModelTemplate CROP_WITH_ROPE = new ModelTemplate(Optional.of(new ResourceLocation("farmersdelight","block/crop_with_rope")),Optional.empty(),TextureSlot.CROP,ROPE_SIDE,ROPE_TOP);
    private static final ModelTemplate CROP_CROSS = new ModelTemplate(Optional.of(new ResourceLocation("farmersdelight","block/crop_cross")),Optional.empty(),TextureSlot.CROSS);

    private static final ModelTemplate STACKABLE_PAPAYA_1 = new ModelTemplate(Optional.of(ThaiDelight.modid("block/papaya/template_stackable_papaya_1")),Optional.empty(),TextureSlot.ALL);
    private static final ModelTemplate STACKABLE_PAPAYA_2 = new ModelTemplate(Optional.of(ThaiDelight.modid("block/papaya/template_stackable_papaya_2")),Optional.empty(),TextureSlot.ALL);

    private static final ModelTemplate TEMPLATE_WALL_PAPAYA_FLOWER = new ModelTemplate(Optional.of(ThaiDelight.modid("block/papaya/template_wall_papaya_flower")),Optional.empty(),FLOWER);
    private static final ModelTemplate TEMPLATE_PAPAYA_FLOWER = new ModelTemplate(Optional.of(ThaiDelight.modid("block/papaya/template_papaya_flower")),Optional.empty(),FLOWER);
    private static final ModelTemplate TEMPLATE_HANGING_PAPAYA_FLOWER = new ModelTemplate(Optional.of(ThaiDelight.modid("block/papaya/template_hanging_papaya_flower")),Optional.empty(),FLOWER);

    private static final ModelTemplate FULL_SACK_BLOCK = new ModelTemplate(Optional.of(ThaiDelight.modid("block/template_full_sack")),Optional.empty(),TextureSlot.TOP);

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
        skipItemBlock(blockStateModelGenerator);

        for(Block blockSupplier : ModBlocks.CRATES){
            createCrateBlock(blockSupplier,blockStateModelGenerator);
        }

        for (Block cabinetBlock : ModBlocks.CABINET){
            createCabinet(cabinetBlock,blockStateModelGenerator);
        }

        blockStateModelGenerator.createTrivialCube(ModBlocks.DURIAN_PEEL_BLOCK);

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.SMALL_DURIAN_BLOCK)
                .with(PropertyDispatch.property(SmallDurianBlock.STACKS)
                        .generate(integer -> Variant.variant().with(VariantProperties.MODEL,ThaiDelight.modid("block/durian/stackable_durian_%d".formatted(integer))))
                ).with(createHorizontalFacingDispatch()));

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.LIME_BLOCK)
                .with(PropertyDispatch.property(LimeBlock.STACKS)
                        .generate(integer -> Variant.variant().with(VariantProperties.MODEL,ThaiDelight.modid("block/lime/stackable_lime_%d".formatted(integer))))
                ).with(createHorizontalFacingDispatch()));


        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.PINEAPPLE_FRIED_RICE_FEAST)
                .with(PropertyDispatch.property(FeastBlock.SERVINGS)
                        .generate(integer -> Variant.variant().with(VariantProperties.MODEL,ThaiDelight.modid("block/pineapple_fried_rice")))
                ).with(createHorizontalFacingDispatch())
        );

        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createFence(ModBlocks.FENCE_LOGGED_BUTTERFLY_PEA,ThaiDelight.modid("block/butterfly_pea_fence_post"),ThaiDelight.modid("block/butterfly_pea_fence_side")));

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.STACKABLE_MANGO_BLOCK)
                .with(PropertyDispatch.property(StackableMangoBlock.STACKS)
                        .generate(integer -> Variant.variant().with(VariantProperties.MODEL,ThaiDelight.modid("block/mango/stackable_mango_%d".formatted(integer))))
                ).with(createHorizontalFacingDispatch()));

        blockStateModelGenerator.blockStateOutput.accept(createSimpleBlock(ModBlocks.COCONUT,ThaiDelight.modid("block/coconut/coconut_block")));
        blockStateModelGenerator.modelOutput.accept(ModelLocationUtils.getModelLocation(ModBlocks.COCONUT.asItem()),new DelegatedModel(ThaiDelight.modid("block/coconut/coconut_block")));
        blockStateModelGenerator.blockStateOutput.accept(createSimpleBlock(ModBlocks.STRIPPED_COCONUT,ThaiDelight.modid("block/coconut/stripped_coconut_block")));
        blockStateModelGenerator.modelOutput.accept(ModelLocationUtils.getModelLocation(ModBlocks.STRIPPED_COCONUT.asItem()),new DelegatedModel(ThaiDelight.modid("block/coconut/stripped_coconut_block")));

        ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(ModBlocks.COCONUT_SAPLING),
                TextureMapping.layer0(ThaiDelight.modid("block/coconut/coconut_sapling")),
                blockStateModelGenerator.modelOutput
                );

        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ModBlocks.COCONUT_LEAF_CARPET,
                ModelTemplates.CARPET.create(ModBlocks.COCONUT_LEAF_CARPET,TextureMapping.wool(ThaiDelight.modid("block/coconut_leaf_block_top")), blockStateModelGenerator.modelOutput)));

        ModelTemplates.FLAT_ITEM
                .create(
                        ModelLocationUtils.getModelLocation(ModBlocks.CRAB_EGG.asItem()),
                        TextureMapping.layer0(getBlockTexture(ModBlocks.CRAB_EGG)),
                        blockStateModelGenerator.modelOutput
                );
        blockStateModelGenerator.blockStateOutput.accept(createSimpleBlock(ModBlocks.CRAB_EGG,ModelLocationUtils.getModelLocation(ModBlocks.CRAB_EGG)));

        createCropRope(ModBlocks.BUTTERFLY_PEA_BLOCK,blockStateModelGenerator);

        ResourceLocation butterfly_pea_model = WALL_FLOWER.create(ModBlocks.BUTTERFLY_PEA_WALL,new TextureMapping()
                .put(VINE,ThaiDelight.modid("block/butterfly_pea/butterfly_pea_wall_vine"))
                .put(FLOWER,ThaiDelight.modid("block/butterfly_pea/butterfly_pea_flower")),blockStateModelGenerator.modelOutput);

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.BUTTERFLY_PEA_WALL,
                Variant.variant().with(VariantProperties.MODEL,butterfly_pea_model)
        ).with(createHorizontalFacingDispatch()));

        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ModBlocks.MORTAR,ModelLocationUtils.getModelLocation(ModBlocks.MORTAR)).with(createHorizontalFacingDispatch()));


        ResourceLocation resourceLocation = ModelTemplates.CUBE_COLUMN.create(ModBlocks.COCONUT_LEAF_BLOCK, TextureMapping.logColumn(ModBlocks.COCONUT_LEAF_BLOCK), blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createAxisAlignedPillarBlock(ModBlocks.COCONUT_LEAF_BLOCK, resourceLocation));


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
                        .with(createBooleanModelDispatch(CoconutLeafBlock.END,
                                BuiltInRegistries.BLOCK.getKey(ModBlocks.COCONUT_LEAF).withPrefix("block/coconut/").withSuffix("_end"),
                                BuiltInRegistries.BLOCK.getKey(ModBlocks.COCONUT_LEAF).withPrefix("block/coconut/")
                                ))
                        .with(createHorizontalFacingDispatch())
        );

        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.BUDDING_COCONUT_LEAF)
                        .with(createBooleanModelDispatch(BuddingCoconutLeafBlock.COCONUT,
                                BuiltInRegistries.BLOCK.getKey(ModBlocks.BUDDING_COCONUT_LEAF).withPrefix("block/coconut/").withSuffix("_coconut"),
                                BuiltInRegistries.BLOCK.getKey(ModBlocks.BUDDING_COCONUT_LEAF).withPrefix("block/coconut/")
                        ))
                        .with(createHorizontalFacingDispatch())
        );

        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.COCONUT_LEAF_END,
                                Variant.variant().with(VariantProperties.MODEL,BuiltInRegistries.BLOCK.getKey(ModBlocks.COCONUT_LEAF).withPrefix("block/coconut/").withSuffix("_end")))
                        .with(createHorizontalFacingDispatch())
        );

        blockStateModelGenerator.family(ModBlocks.COCONUT_PLANKS).generateFor(COCONUT_PLANKS);

        blockStateModelGenerator.woodProvider(ModBlocks.MANGO_LOG).logWithHorizontal(ModBlocks.MANGO_LOG).wood(ModBlocks.MANGO_WOOD);
        blockStateModelGenerator.woodProvider(ModBlocks.STRIPPED_MANGO_LOG).logWithHorizontal(ModBlocks.STRIPPED_MANGO_LOG).wood(ModBlocks.STRIPPED_MANGO_WOOD);
        blockStateModelGenerator.createHangingSign(ModBlocks.STRIPPED_MANGO_LOG,ModBlocks.MANGO_HANGING_SIGN,ModBlocks.MANGO_WALL_HANGING_SIGN);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.MANGO_LEAVES,TexturedModel.LEAVES);
        blockStateModelGenerator.family(ModBlocks.MANGO_PLANKS).generateFor(MANGO_PLANKS);

        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.HANGING_DURIAN)
                        .with(PropertyDispatch.property(BlockStateProperties.AGE_1)
                                .select(0,Variant.variant()
                                        .with(VariantProperties.MODEL,ModelLocationUtils.getModelLocation(ModBlocks.DURIAN_BLOCK,"_stage0_hanging")))
                                .select(1,Variant.variant()
                                        .with(VariantProperties.MODEL,ModelLocationUtils.getModelLocation(ModBlocks.DURIAN_BLOCK,"_stage2_hanging")))

                        )
        );

        blockStateModelGenerator.blockStateOutput.accept(
                BlockModelGenerators.createSimpleBlock(
                        ModBlocks.DURIAN_BLOCK,
                        ThaiDelight.modid("block/durian_block_stage2")
                ));

        blockStateModelGenerator.createSimpleFlatItemModel(ModBlocks.DURIAN_FLOWER);
        ResourceLocation durian_flower_resource = BlockModelGenerators.TintState.NOT_TINTED.getCross().create(ModBlocks.DURIAN_FLOWER, TextureMapping.cross(ModBlocks.DURIAN_FLOWER), blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.DURIAN_FLOWER)
                        .with(PropertyDispatch.property(BlockStateProperties.HANGING)
                                .select(false,Variant.variant().with(VariantProperties.MODEL,durian_flower_resource))
                                .select(true,Variant.variant().with(VariantProperties.MODEL,durian_flower_resource).with(VariantProperties.X_ROT,VariantProperties.Rotation.R180))
                        )
        );


        createPapaya(blockStateModelGenerator);
        createMangoBlock(blockStateModelGenerator);
        createPepperCrop(blockStateModelGenerator);
        createLimeCrop(blockStateModelGenerator);
        createBasil(blockStateModelGenerator,ModBlocks.HOLY_BASIL);
        createBasil(blockStateModelGenerator,ModBlocks.BASIL);

        createSackBlock(blockStateModelGenerator);


        createFermentedFishCauldron(blockStateModelGenerator);

        blockStateModelGenerator.createPlant(ModBlocks.LIME_SAPLING,ModBlocks.POTTED_LIME_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
        //blockStateModelGenerator.createPlant(ModBlocks.COCONUT_SAPLING,ModBlocks.POTTED_COCONUT_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(ModBlocks.MANGO_SAPLING,ModBlocks.POTTED_MANGO_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.blockStateOutput.accept(createSimpleBlock(ModBlocks.COCONUT_SAPLING,ThaiDelight.modid("block/coconut/coconut_sapling")));
        blockStateModelGenerator.blockStateOutput.accept(createSimpleBlock(ModBlocks.COCONUT_SAPLING_CROP,ThaiDelight.modid("block/coconut/coconut_sapling_crop")));
    }

    private void createSackBlock(BlockModelGenerators blockStateModelGenerator) {
        ResourceLocation closeModel = FULL_SACK_BLOCK.create(ThaiDelight.modid("block/full_sack"),new TextureMapping().put(TextureSlot.TOP,ThaiDelight.modid("block/full_sack_top")),blockStateModelGenerator.modelOutput);
        ResourceLocation openModel =  FULL_SACK_BLOCK.create(ThaiDelight.modid("block/full_sack_open"),new TextureMapping().put(TextureSlot.TOP,ThaiDelight.modid("block/full_sack_top_open")),blockStateModelGenerator.modelOutput);

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.SACK)
                        .with(PropertyDispatch.property(SackBlock.OPEN)
                                .select(true,Variant.variant().with(VariantProperties.MODEL,openModel))
                                .select(false,Variant.variant().with(VariantProperties.MODEL,closeModel))
                        )
                .with(createHorizontalFacingDispatch()));
    }


    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        for(Item flatItem : ModItems.FLAT_ITEMS){
            itemModelGenerator.generateFlatItem(flatItem,ModelTemplates.FLAT_ITEM);
        }

        itemModelGenerator.generateFlatItem(ModItems.CRAB_SPAWN_EGG,SPAWN_EGG);
        itemModelGenerator.generateFlatItem(ModItems.DRAGONFLY_SPAWN_EGG,SPAWN_EGG);
        //itemModelGenerator.generateFlatItem(ModItems.BUFFALO_SPAWN_EGG,SPAWN_EGG);

        itemModelGenerator.generateFlatItem(ModItems.DURIAN_BOAT,ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DURIAN_CHEST_BOAT,ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MANGO_BOAT,ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MANGO_CHEST_BOAT,ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COCONUT_BOAT,ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COCONUT_CHEST_BOAT,ModelTemplates.FLAT_ITEM);

    }

    private static void skipItemBlock(BlockModelGenerators blockStateModelGenerator){
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.COCONUT_LEAF);
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.BUDDING_COCONUT_LEAF);
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.DURIAN_BLOCK);
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.SMALL_DURIAN_BLOCK);
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.DURIAN_CAKE);
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.MANGO_PUDDING);
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.CRAB_EGG);
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.PAPAYA_LEAVES);
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.COCONUT);
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.STRIPPED_COCONUT);

    }

    private static void createFermentedFishCauldron(BlockModelGenerators blockStateModelGenerator){
        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.FERMENTED_FISH_CAULDRON)
                        .with(PropertyDispatch.properties(FermentedFishCauldronBlock.LEVEL,FermentedFishCauldronBlock.FERMENT)
                                .generate((heightLevel, fermentedLevel) -> {
                                    ModelTemplate cauldronLevel = ModelTemplates.CAULDRON_LEVEL1;
                                    switch (heightLevel){
                                        case 2 -> cauldronLevel = ModelTemplates.CAULDRON_LEVEL2;
                                        case 3 -> cauldronLevel = ModelTemplates.CAULDRON_FULL;
                                    }

                                    return Variant.variant().with(VariantProperties.MODEL,
                                            cauldronLevel.createWithSuffix(ModBlocks.FERMENTED_FISH_CAULDRON,"_fermented%d_level%d".formatted(fermentedLevel,heightLevel),
                                                    TextureMapping.cauldron(ThaiDelight.modid("block/fermented_fish_cauldron_fermented%d".formatted(fermentedLevel))),
                                                    blockStateModelGenerator.modelOutput));
                                })
                        )
        );
    }

    private static void createCropRope(Block block,BlockModelGenerators blockModelGenerators){
        blockModelGenerators.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(block)
                        .with(PropertyDispatch.properties(ButterflyPeaVineBlock.VINE_AGE,ButterflyPeaVineBlock.ROPELOGGED)
                                .generate((integer, aBoolean) -> {
                                    if(aBoolean){
                                        return Variant.variant().with(VariantProperties.MODEL,CROP_WITH_ROPE.createWithSuffix(block,"_stage%d_with_rope".formatted(integer),
                                                new TextureMapping()
                                                        .put(TextureSlot.CROP,BuiltInRegistries.BLOCK.getKey(block).withPrefix("block/butterfly_pea/").withSuffix("_stage%d".formatted(integer)))
                                                        .put(ROPE_SIDE,new ResourceLocation("farmersdelight","block/tomatoes_coiled_rope"))
                                                        .put(ROPE_TOP,new ResourceLocation("farmersdelight","block/rope_top"))
                                                ,blockModelGenerators.modelOutput)
                                        );
                                    }
                                    return Variant.variant().with(VariantProperties.MODEL,CROP_CROSS.createWithSuffix(block,"_stage%d".formatted(integer),
                                            new TextureMapping()
                                                    .put(TextureSlot.CROSS,BuiltInRegistries.BLOCK.getKey(block).withPrefix("block/butterfly_pea/").withSuffix("_stage%d".formatted(integer)))
                                            ,blockModelGenerators.modelOutput));
                                })
                        )
        );
    }

    public static void createCabinet(Block block,BlockModelGenerators blockModelGenerators){
        ResourceLocation cabinet = ModelTemplates.CUBE_ORIENTABLE.create(block,TextureMapping.orientableCube(block), blockModelGenerators.modelOutput);
        ResourceLocation cabinet_open = ModelTemplates.CUBE_ORIENTABLE.createWithSuffix(block,"_open",
                new TextureMapping()
                        .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"))
                        .put(TextureSlot.FRONT, TextureMapping.getBlockTexture(block, "_front_open"))
                        .put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top"))
                        .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(block, "_bottom"))
                , blockModelGenerators.modelOutput);
        blockModelGenerators.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(block)
                        .with(PropertyDispatch.properties(BlockStateProperties.HORIZONTAL_FACING,BlockStateProperties.OPEN)
                                .select(Direction.NORTH,false, Variant.variant()
                                        .with(VariantProperties.MODEL,cabinet)
                                )
                                .select(Direction.SOUTH,false, Variant.variant()
                                        .with(VariantProperties.MODEL,cabinet)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                )
                                .select(Direction.EAST,false, Variant.variant()
                                        .with(VariantProperties.MODEL,cabinet)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                )
                                .select(Direction.WEST,false, Variant.variant()
                                        .with(VariantProperties.MODEL,cabinet)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                )
                                //Open
                                .select(Direction.NORTH,true, Variant.variant()
                                        .with(VariantProperties.MODEL,cabinet_open)
                                )
                                .select(Direction.SOUTH,true, Variant.variant()
                                        .with(VariantProperties.MODEL,cabinet_open)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
                                )
                                .select(Direction.EAST,true, Variant.variant()
                                        .with(VariantProperties.MODEL,cabinet_open)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                )
                                .select(Direction.WEST,true, Variant.variant()
                                        .with(VariantProperties.MODEL,cabinet_open)
                                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
                                )

        ));
    }

    private static void createPapaya(BlockModelGenerators blockStateModelGenerator){

        STACKABLE_PAPAYA_1.create(ThaiDelight.modid("block/papaya/stackable_papaya_1"),new TextureMapping().put(TextureSlot.ALL,ThaiDelight.modid("block/papaya/papaya_fruit")), blockStateModelGenerator.modelOutput);
        STACKABLE_PAPAYA_2.create(ThaiDelight.modid("block/papaya/stackable_papaya_2"),new TextureMapping().put(TextureSlot.ALL,ThaiDelight.modid("block/papaya/papaya_fruit")), blockStateModelGenerator.modelOutput);
        STACKABLE_PAPAYA_1.create(ThaiDelight.modid("block/papaya/stackable_raw_papaya_1"),new TextureMapping().put(TextureSlot.ALL,ThaiDelight.modid("block/papaya/unripe_papaya_fruit")), blockStateModelGenerator.modelOutput);
        STACKABLE_PAPAYA_2.create(ThaiDelight.modid("block/papaya/stackable_raw_papaya_2"),new TextureMapping().put(TextureSlot.ALL,ThaiDelight.modid("block/papaya/unripe_papaya_fruit")), blockStateModelGenerator.modelOutput);

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.STACKABLE_PAPAYA)
                .with(PropertyDispatch.property(StackablePapayaBlock.STACKS)
                        .generate(integer -> Variant.variant().with(VariantProperties.MODEL,ThaiDelight.modid("block/papaya/stackable_papaya_%d".formatted(integer))))
                ).with(createHorizontalFacingDispatch()));

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.STACKABLE_RAW_PAPAYA)
                .with(PropertyDispatch.property(StackablePapayaBlock.STACKS)
                        .generate(integer -> Variant.variant().with(VariantProperties.MODEL,ThaiDelight.modid("block/papaya/stackable_raw_papaya_%d".formatted(integer))))
                ).with(createHorizontalFacingDispatch()));

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.PAPAYA_LOG)
                .with(PropertyDispatch.property(PapayaLogBlock.BOTTOM)
                        .select(true,Variant.variant()
                                .with(VariantProperties.MODEL,
                                        ModelTemplates.CUBE_COLUMN.createWithSuffix(ModBlocks.PAPAYA_LOG,"_bottom", new TextureMapping()
                                                        .put(TextureSlot.SIDE, ThaiDelight.modid("block/papaya/papaya_log_bottom"))
                                                        .put(TextureSlot.END, ThaiDelight.modid("block/papaya/papaya_log_top"))
                                                        .put(TextureSlot.PARTICLE, ThaiDelight.modid("block/papaya/papaya_log_bottom")),
                                                blockStateModelGenerator.modelOutput))
                        )
                        .select(false,Variant.variant()
                                .with(VariantProperties.MODEL,
                                        ModelTemplates.CUBE_COLUMN.create(ModBlocks.PAPAYA_LOG,new TextureMapping()
                                                .put(TextureSlot.SIDE, ThaiDelight.modid("block/papaya/papaya_log"))
                                                .put(TextureSlot.END, ThaiDelight.modid("block/papaya/papaya_log_top"))
                                                .put(TextureSlot.PARTICLE, ThaiDelight.modid("block/papaya/papaya_log")), blockStateModelGenerator.modelOutput))
                        )
                )
                .with(createRotatedPillar())
        );

        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createAxisAlignedPillarBlock(ModBlocks.PAPAYA_WOOD,
                ModelTemplates.CUBE_COLUMN.create(ModBlocks.PAPAYA_WOOD,
                        new TextureMapping()
                                .put(TextureSlot.END,ThaiDelight.modid("block/papaya/papaya_log"))
                                .put(TextureSlot.SIDE,ThaiDelight.modid("block/papaya/papaya_log")),
                        blockStateModelGenerator.modelOutput)));

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.STRIPPED_PAPAYA_LOG,Variant.variant()
                        .with(VariantProperties.MODEL,
                                ModelTemplates.CUBE_COLUMN.create(ModBlocks.STRIPPED_PAPAYA_LOG,new TextureMapping()
                                        .put(TextureSlot.SIDE, ThaiDelight.modid("block/papaya/stripped_papaya_log"))
                                        .put(TextureSlot.END, ThaiDelight.modid("block/papaya/papaya_log_top"))
                                        .put(TextureSlot.PARTICLE, ThaiDelight.modid("block/papaya/stripped_papaya_log")), blockStateModelGenerator.modelOutput)))
                .with(createRotatedPillar())
        );

        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createAxisAlignedPillarBlock(ModBlocks.STRIPPED_PAPAYA_WOOD,
                ModelTemplates.CUBE_COLUMN.create(ModBlocks.STRIPPED_PAPAYA_WOOD,
                        new TextureMapping()
                                .put(TextureSlot.END,ThaiDelight.modid("block/papaya/stripped_papaya_log"))
                                .put(TextureSlot.SIDE,ThaiDelight.modid("block/papaya/stripped_papaya_log")),
                        blockStateModelGenerator.modelOutput)));

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.PAPAYA_LEAVES_STEM,Variant.variant()
                .with(VariantProperties.MODEL,ModelTemplates.CROSS.create(
                        ThaiDelight.modid("block/papaya/papaya_leaves_stem"),
                        new TextureMapping().put(TextureSlot.CROSS,ThaiDelight.modid("block/papaya/papaya_leaves_stem")),
                        blockStateModelGenerator.modelOutput
                ))
        ).with(PropertyDispatch.property(PapayaLeavesStemBlock.PAPAYA_LEAVES_FACING)
                .select(Direction.UP,Variant.variant())
                .select(Direction.NORTH,Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
                .select(Direction.EAST,Variant.variant()
                        .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                        .with(VariantProperties.Y_ROT,VariantProperties.Rotation.R90)
                )
                .select(Direction.WEST,Variant.variant()
                        .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                        .with(VariantProperties.Y_ROT,VariantProperties.Rotation.R270)
                )
                .select(Direction.SOUTH,Variant.variant()
                        .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
                        .with(VariantProperties.Y_ROT,VariantProperties.Rotation.R180)
                )
        ));

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.WALL_PAPAYA_LEAVES,Variant.variant()
                .with(VariantProperties.MODEL,ThaiDelight.modid("block/papaya/wall_papaya_leaves"))
        ).with(createHorizontalFacingDispatch()));

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.PAPAYA_LEAVES)
                .with(PropertyDispatch.property(PapayaLeavesStemBlock.PAPAYA_LEAVES_FACING)
                        .select(Direction.UP,Variant.variant()
                                .with(VariantProperties.MODEL,ThaiDelight.modid("block/papaya/papaya_leaves"))
                        )
                        .select(Direction.NORTH,Variant.variant()
                                .with(VariantProperties.MODEL,ThaiDelight.modid("block/papaya/wall_papaya_leaves"))
                        )
                        .select(Direction.EAST,Variant.variant()
                                .with(VariantProperties.Y_ROT,VariantProperties.Rotation.R90)
                                .with(VariantProperties.MODEL,ThaiDelight.modid("block/papaya/wall_papaya_leaves"))
                        )
                        .select(Direction.WEST,Variant.variant()
                                .with(VariantProperties.Y_ROT,VariantProperties.Rotation.R270)
                                .with(VariantProperties.MODEL,ThaiDelight.modid("block/papaya/wall_papaya_leaves"))
                        )
                        .select(Direction.SOUTH,Variant.variant()
                                .with(VariantProperties.Y_ROT,VariantProperties.Rotation.R180)
                                .with(VariantProperties.MODEL,ThaiDelight.modid("block/papaya/wall_papaya_leaves"))
                        )
        ));

        ModelTemplates.FLAT_ITEM.create(
                ModelLocationUtils.getModelLocation(ModItems.PAPAYA_FLOWER),
                TextureMapping.layer0(ThaiDelight.modid("block/papaya/papaya_flower")),
                blockStateModelGenerator.modelOutput);

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.BUDDING_PAPAYA_FLOWER,
                Variant.variant().with(VariantProperties.MODEL,ThaiDelight.modid("block/papaya/papaya_flower"))
        ).with(createHorizontalFacingDispatchAlt()));

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.PAPAYA_FLOWER)
                .with(PropertyDispatch.properties(PapayaFlowerBlock.FLOWERS,PapayaFlowerBlock.HANGING)
                        .generate((integer, hangning) -> {

                            if(hangning){
                                return Variant.variant().with(VariantProperties.MODEL,TEMPLATE_HANGING_PAPAYA_FLOWER.create(
                                        ThaiDelight.modid("block/papaya/hanging_papaya_flower%d".formatted(integer)),

                                        integer == 1 ? new TextureMapping().put(FLOWER,ThaiDelight.modid("block/papaya/papaya_flower")) :
                                                new TextureMapping().put(FLOWER,ThaiDelight.modid("block/papaya/papaya_flower%d".formatted(integer))),
                                        blockStateModelGenerator.modelOutput));
                            }
                            return Variant.variant().with(VariantProperties.MODEL,TEMPLATE_PAPAYA_FLOWER.create(
                                    ThaiDelight.modid("block/papaya/papaya_flower%d".formatted(integer)),

                                    integer == 1 ? new TextureMapping().put(FLOWER,ThaiDelight.modid("block/papaya/papaya_flower")) :
                                            new TextureMapping().put(FLOWER,ThaiDelight.modid("block/papaya/papaya_flower%d".formatted(integer))),
                                    blockStateModelGenerator.modelOutput));
                        })
                )
                .with(createHorizontalFacingDispatch()));

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.WALL_PAPAYA_FLOWER)
                .with(PropertyDispatch.property(WallPapayaFlowerBlock.FLOWERS)
                        .generate(integer -> {
                            if(integer == 1){
                                return Variant.variant().with(VariantProperties.MODEL,ThaiDelight.modid("block/papaya/papaya_flower"));
                            }
                            return Variant.variant().with(VariantProperties.MODEL,TEMPLATE_WALL_PAPAYA_FLOWER.create(
                                    ThaiDelight.modid("block/papaya/wall_papaya_flower%d".formatted(integer)),
                                    new TextureMapping().put(FLOWER,ThaiDelight.modid("block/papaya/papaya_flower%d".formatted(integer))),
                                    blockStateModelGenerator.modelOutput));
                        })
                )
                .with(createHorizontalFacingDispatch()));

        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.PAPAYA)
                        .with(PropertyDispatch.property(PapayaBlock.AGE).generate(integer -> {
                            return Variant.variant().with(VariantProperties.MODEL,
                                    ThaiDelight.modid("block/papaya/papaya_age%d".formatted(integer)));
                        }))
                        .with(createHorizontalFacingDispatchAlt()));


        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.PAPAYA_CROP)
                        .with(PropertyDispatch.property(BlockStateProperties.AGE_1).generate((integer) -> {
            return Variant.variant().with(VariantProperties.MODEL,
                    CROP_CROSS.create(
                            ThaiDelight.modid("block/papaya/papaya_crop_stage%d".formatted(integer)),
                            new TextureMapping().put(TextureSlot.CROSS,ThaiDelight.modid("block/papaya/papaya_crop_stage%d".formatted(integer))),
                            blockStateModelGenerator.modelOutput));
        })));

        blockStateModelGenerator.createCrossBlockWithDefaultItem(ModBlocks.PAPAYA_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
    }

    private static void createBlock(Block block, ModelTemplate modelTemplate, TextureMapping textureMapping, BlockModelGenerators blockModelGenerator){
        blockModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block,modelTemplate.create(block,textureMapping, blockModelGenerator.modelOutput)));

    }

    private static void createPepperCrop(BlockModelGenerators blockModelGenerators){

        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.BUDDING_PEPPER_CROP)
                .with(PropertyDispatch.property(BuddingPepperBlock.PEPPER_AGE).generate(integer -> {
                    return Variant.variant().with(VariantProperties.MODEL,
                            CROP_CROSS.create(
                                    ThaiDelight.modid("block/pepper/budding_pepper_age%d".formatted(integer)),
                                    TextureMapping.cross(ThaiDelight.modid("block/pepper/budding_pepper_age%d".formatted(integer))),
                                    blockModelGenerators.modelOutput
                            )
                    );
                })
        ));

        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.PEPPER_CROP)
                .with(PropertyDispatch.property(PepperCropBlock.AGE).generate((integer) -> {
            return Variant.variant().with(VariantProperties.MODEL,
                        CROP_CROSS.create(
                                ThaiDelight.modid("block/pepper/pepper_age%d".formatted(integer)),
                                TextureMapping.cross(ThaiDelight.modid("block/pepper/pepper_age%d".formatted(integer))),
                                blockModelGenerators.modelOutput
                        )
                    );
        })));

        ModelTemplates.FLAT_ITEM
                .create(
                        ModelLocationUtils.getModelLocation(ModBlocks.WILD_PEPPER_CROP.asItem()),
                        TextureMapping.layer0(ThaiDelight.modid("block/pepper/pepper_age2")),
                        blockModelGenerators.modelOutput
                );

        blockModelGenerators.blockStateOutput.accept(createSimpleBlock(ModBlocks.WILD_PEPPER_CROP,
                ModelTemplates.CROSS.create(
                        ThaiDelight.modid("block/pepper/wild_pepper_crop"),
                        TextureMapping.cross(ThaiDelight.modid("block/pepper/pepper_age2")),
                        blockModelGenerators.modelOutput
                )
        ));
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
        ResourceLocation resourceLocation = BuiltInRegistries.BLOCK.getKey(block);
        ResourceLocation modelResourceLocation = resourceLocation.withPath(string2 -> "block/" + string2 + "/" + string2);

        ResourceLocation BASIL_AGE0 = BASIL_TEMPLATE.create(modelResourceLocation.withSuffix("_age0"),new TextureMapping()
                .put(TextureSlot.STEM,modelResourceLocation.withSuffix("_stem"))
                .put(TextureSlot.SIDE,modelResourceLocation.withSuffix("_leaves_side"))
                .put(TextureSlot.TOP,modelResourceLocation.withSuffix("_leaves_top"))
                .put(TextureSlot.BOTTOM,modelResourceLocation.withSuffix("_leaves_bottom"))
                .put(FLOWER,modelResourceLocation.withSuffix("_flower_age0")), blockModelGenerators.modelOutput);

        ResourceLocation BASIL_AGE1 = BASIL_TEMPLATE.create(modelResourceLocation.withSuffix("_age1"),new TextureMapping()
                .put(TextureSlot.STEM,modelResourceLocation.withSuffix("_stem"))
                .put(TextureSlot.SIDE,modelResourceLocation.withSuffix("_leaves_side"))
                .put(TextureSlot.TOP,modelResourceLocation.withSuffix("_leaves_top"))
                .put(TextureSlot.BOTTOM,modelResourceLocation.withSuffix("_leaves_bottom"))
                .put(FLOWER,modelResourceLocation.withSuffix("_flower_age1")), blockModelGenerators.modelOutput);

        ResourceLocation BASIL_AGE2 = BASIL_TEMPLATE.create(modelResourceLocation.withSuffix("_age2"),new TextureMapping()
                .put(TextureSlot.STEM,modelResourceLocation.withSuffix("_stem"))
                .put(TextureSlot.SIDE,modelResourceLocation.withSuffix("_leaves_side"))
                .put(TextureSlot.TOP,modelResourceLocation.withSuffix("_leaves_top"))
                .put(TextureSlot.BOTTOM,modelResourceLocation.withSuffix("_leaves_bottom"))
                .put(FLOWER,modelResourceLocation.withSuffix("_flower_age2")), blockModelGenerators.modelOutput);

        ResourceLocation BASIL_AGE3 = BASIL_TEMPLATE.create(modelResourceLocation.withSuffix("_age3"),new TextureMapping()
                .put(TextureSlot.STEM,modelResourceLocation.withSuffix("_stem"))
                .put(TextureSlot.SIDE,modelResourceLocation.withSuffix("_leaves_side"))
                .put(TextureSlot.TOP,modelResourceLocation.withSuffix("_leaves_top"))
                .put(TextureSlot.BOTTOM,modelResourceLocation.withSuffix("_leaves_bottom"))
                .put(FLOWER,modelResourceLocation.withSuffix("_flower_age3")), blockModelGenerators.modelOutput);

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
                MultiVariantGenerator.multiVariant(ModBlocks.HANGING_MANGO_BLOCK)
                        .with(PropertyDispatch.property(HangingMangoBlock.AGE)
                                //Ground Raw Mango
                                .select(0,Variant.variant()
                                        .with(VariantProperties.MODEL,ThaiDelight.modid("block/hanging_mango_age0"))
                                )

                                //Ground Raw Mango
                                .select(1,Variant.variant()
                                        .with(VariantProperties.MODEL,hanging_raw_mango)
                                )

                                //Hanging Mango
                                .select(2,Variant.variant()
                                        .with(VariantProperties.MODEL,hanging_mango)
                                )
                        ).with(createHorizontalFacingDispatch())
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
