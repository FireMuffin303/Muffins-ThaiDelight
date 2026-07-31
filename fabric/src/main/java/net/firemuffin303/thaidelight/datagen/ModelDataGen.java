package net.firemuffin303.thaidelight.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.block.SackBlock;
import net.firemuffin303.thaidelight.common.block.cauldron.FermentedFishCauldronBlock;
import net.firemuffin303.thaidelight.common.block.feast.MangoStickyRiceFeastBlock;
import net.firemuffin303.thaidelight.common.block.vegetation.FabricBuddingButterflyPeaBlock;
import net.firemuffin303.thaidelight.common.block.vegetation.pepper.FabricBuddingPepperBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.basil.BasilCropBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.butterfly_pea.ButterflyPeaVineBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.coconut.BuddingCoconutLeafBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.coconut.CoconutLeafBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.durian.SmallDurianBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.lime.LimeBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.lime.LimePlantBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.mango.HangingMangoBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.mango.StackableMangoBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.papaya.*;
import net.firemuffin303.thaidelight.common.block.vegetations.pepper.PepperCropBlock;
import net.firemuffin303.thaidelight.common.registry.ModBlocks;
import net.firemuffin303.thaidelight.common.registry.ModItems;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.Optional;
import java.util.function.Supplier;

import static net.minecraft.data.models.BlockModelGenerators.*;
import static net.minecraft.data.models.model.TextureMapping.getBlockTexture;

public class ModelDataGen extends FabricModelProvider {
    public static final ResourceLocation CUT_OUT = ResourceLocation.fromNamespaceAndPath("minecraft","cutout");

    private static final TextureSlot FLOWER = TextureSlot.create("flower");
    private static final TextureSlot VINE = TextureSlot.create("vine");
    private static final TextureSlot ROPE_SIDE = TextureSlot.create("rope_side");
    private static final TextureSlot ROPE_TOP = TextureSlot.create("rope_top");
    private static final TextureSlot INNER = TextureSlot.create("inner");
    private static final TextureSlot OMELETTE = TextureSlot.create("omelette");

    private static final ModelTemplate PASTLE_3D = createModItem("pastle_3d_template", TextureSlot.LAYER0);
    private static final ModelTemplate SPAWN_EGG = createMincraftItem("template_spawn_egg");
    private static final ModelTemplate LIME_BUSH_STAGE2 = new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(ThaiDelightCommon.MOD_ID,"block/template_lime_bush_stage2")),Optional.empty(),TextureSlot.SIDE,TextureSlot.TOP);
    private static final ModModelTemplate LIME_UPPER_TEMPLATE = new ModModelTemplate(Optional.of(ThaiDelightCommon.modid("block/lime/template_lime_upper")),Optional.empty(),CUT_OUT,TextureSlot.SIDE,TextureSlot.TOP,TextureSlot.PLANT);
    private static final ModModelTemplate LIME_BOTTOM_TEMPLATE = new ModModelTemplate(Optional.of(ThaiDelightCommon.modid("block/lime/template_lime_bottom")),Optional.empty(),CUT_OUT,TextureSlot.SIDE,TextureSlot.BOTTOM,TextureSlot.STEM,TextureSlot.PLANT);

    private static final ModelTemplate BASIL_TEMPLATE = new ModelTemplate(Optional.of(ThaiDelightCommon.modid("block/basil/template_basil")),Optional.empty(),TextureSlot.SIDE,TextureSlot.BOTTOM,TextureSlot.TOP,TextureSlot.STEM,FLOWER);

    public static  final  ModModelTemplate HANGING_MANGO = new ModModelTemplate(Optional.of(ThaiDelightCommon.modid("block/template_hanging_mango")),Optional.empty(), CUT_OUT,TextureSlot.ALL);
    public static  final  ModelTemplate MANGO = new ModelTemplate(Optional.of(ThaiDelightCommon.modid("block/template_mango")),Optional.empty(),TextureSlot.ALL);

    private static final ModModelTemplate WALL_FLOWER = new ModModelTemplate(Optional.of(ThaiDelightCommon.modid("block/template_wall_flower")),Optional.empty(), CUT_OUT,VINE,FLOWER);

    private static final ModModelTemplate CROP_WITH_ROPE = new ModModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath("farmersdelight","block/crop_with_rope")),Optional.empty(), CUT_OUT,TextureSlot.CROP,ROPE_SIDE,ROPE_TOP);
    private static final ModModelTemplate CROP_CROSS = new ModModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath("farmersdelight","block/crop_cross")),Optional.empty(), CUT_OUT,TextureSlot.CROSS);

    private static final ModelTemplate STACKABLE_PAPAYA_1 = new ModelTemplate(Optional.of(ThaiDelightCommon.modid("block/papaya/template_stackable_papaya_1")),Optional.empty(),TextureSlot.ALL);
    private static final ModelTemplate STACKABLE_PAPAYA_2 = new ModelTemplate(Optional.of(ThaiDelightCommon.modid("block/papaya/template_stackable_papaya_2")),Optional.empty(),TextureSlot.ALL);

    private static final ModelTemplate TEMPLATE_WALL_PAPAYA_FLOWER = new ModelTemplate(Optional.of(ThaiDelightCommon.modid("block/papaya/template_wall_papaya_flower")),Optional.empty(),FLOWER);
    private static final ModelTemplate TEMPLATE_PAPAYA_FLOWER = new ModelTemplate(Optional.of(ThaiDelightCommon.modid("block/papaya/template_papaya_flower")),Optional.empty(),FLOWER);
    private static final ModelTemplate TEMPLATE_HANGING_PAPAYA_FLOWER = new ModelTemplate(Optional.of(ThaiDelightCommon.modid("block/papaya/template_hanging_papaya_flower")),Optional.empty(),FLOWER);

    private static final ModelTemplate FULL_SACK_BLOCK = new ModelTemplate(Optional.of(ThaiDelightCommon.modid("block/template_full_sack")),Optional.empty(),TextureSlot.TOP);

    private static final ModelTemplate CAKE = new ModelTemplate(Optional.of(ThaiDelightCommon.modid("block/feast/template_cake")),Optional.empty(),TextureSlot.TOP,TextureSlot.SIDE,TextureSlot.BOTTOM);
    private static final ModelTemplate CAKE_SLICE1 = new ModelTemplate(Optional.of(ThaiDelightCommon.modid("block/feast/template_cake_slice1")),Optional.empty(),TextureSlot.TOP,TextureSlot.SIDE,TextureSlot.BOTTOM,TextureSlot.INSIDE);
    private static final ModelTemplate CAKE_SLICE2 = new ModelTemplate(Optional.of(ThaiDelightCommon.modid("block/feast/template_cake_slice2")),Optional.empty(),TextureSlot.TOP,TextureSlot.SIDE,TextureSlot.BOTTOM,TextureSlot.INSIDE);
    private static final ModelTemplate CAKE_SLICE3 = new ModelTemplate(Optional.of(ThaiDelightCommon.modid("block/feast/template_cake_slice3")),Optional.empty(),TextureSlot.TOP,TextureSlot.SIDE,TextureSlot.BOTTOM,TextureSlot.INSIDE);
    private static final ModelTemplate CAKE_SLICE4 = new ModelTemplate(Optional.of(ThaiDelightCommon.modid("block/feast/template_cake_slice4")),Optional.empty(),TextureSlot.TOP,TextureSlot.SIDE,TextureSlot.BOTTOM,TextureSlot.INSIDE);
    private static final ModelTemplate CAKE_SLICE5 = new ModelTemplate(Optional.of(ThaiDelightCommon.modid("block/feast/template_cake_slice5")),Optional.empty(),TextureSlot.TOP,TextureSlot.SIDE,TextureSlot.BOTTOM,TextureSlot.INSIDE);
    private static final ModelTemplate CAKE_SLICE6 = new ModelTemplate(Optional.of(ThaiDelightCommon.modid("block/feast/template_cake_slice6")),Optional.empty(),TextureSlot.TOP,TextureSlot.SIDE,TextureSlot.BOTTOM,TextureSlot.INSIDE);

    private static final ModelTemplate PIE = new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie")),Optional.empty(),TextureSlot.TOP,TextureSlot.SIDE,TextureSlot.BOTTOM,TextureSlot.PARTICLE);
    private static final ModelTemplate PIE_SLICE1 = new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie_slice1")),Optional.empty(),TextureSlot.TOP,TextureSlot.SIDE,TextureSlot.BOTTOM,TextureSlot.PARTICLE,INNER);
    private static final ModelTemplate PIE_SLICE2 = new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie_slice2")),Optional.empty(),TextureSlot.TOP,TextureSlot.SIDE,TextureSlot.BOTTOM,TextureSlot.PARTICLE,INNER);
    private static final ModelTemplate PIE_SLICE3 = new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie_slice3")),Optional.empty(),TextureSlot.TOP,TextureSlot.SIDE,TextureSlot.BOTTOM,TextureSlot.PARTICLE,INNER);

    private static final ModelTemplate TEMPLATE_OMELETTE = new ModelTemplate(Optional.of(ThaiDelightCommon.modid("block/feast/template_omelette")),Optional.empty(),OMELETTE);
    private static final ModelTemplate TEMPLATE_OMELETTE_1 = new ModelTemplate(Optional.of(ThaiDelightCommon.modid("block/feast/template_omelette_1")),Optional.empty(),OMELETTE);
    private static final ModelTemplate TEMPLATE_OMELETTE_2 = new ModelTemplate(Optional.of(ThaiDelightCommon.modid("block/feast/template_omelette_2")),Optional.empty(),OMELETTE);
    private static final ModelTemplate TEMPLATE_OMELETTE_3 = new ModelTemplate(Optional.of(ThaiDelightCommon.modid("block/feast/template_omelette_3")),Optional.empty(),OMELETTE);

    public static final BlockFamily DURIAN_PLANKS = BlockFamilies.familyBuilder(ModBlocks.DURIAN_PLANKS.get())
            .button(ModBlocks.DURIAN_BUTTON.get())
            .fence(ModBlocks.DURIAN_FENCE.get())
            .fenceGate(ModBlocks.DURIAN_FENCE_GATE.get())
            .pressurePlate(ModBlocks.DURIAN_PRESSURE_PLATE.get())
            .sign(ModBlocks.DURIAN_SIGN.get(),ModBlocks.DURIAN_WALL_SIGN.get())
            .slab(ModBlocks.DURIAN_SLAB.get())
            .stairs(ModBlocks.DURIAN_STAIRS.get())
            .door(ModBlocks.DURIAN_DOOR.get())
            .trapdoor(ModBlocks.DURIAN_TRAPDOOR.get())
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();

    public static final BlockFamily COCONUT_PLANKS = BlockFamilies.familyBuilder(ModBlocks.COCONUT_PLANKS.get())
            .button(ModBlocks.COCONUT_BUTTON.get())
            .fence(ModBlocks.COCONUT_FENCE.get())
            .fenceGate(ModBlocks.COCONUT_FENCE_GATE.get())
            .pressurePlate(ModBlocks.COCONUT_PRESSURE_PLATE.get())
            .sign(ModBlocks.COCONUT_SIGN.get(),ModBlocks.COCONUT_WALL_SIGN.get())
            .slab(ModBlocks.COCONUT_SLAB.get())
            .stairs(ModBlocks.COCONUT_STAIRS.get())
            .door(ModBlocks.COCONUT_DOOR.get())
            .trapdoor(ModBlocks.COCONUT_TRAPDOOR.get())
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();

    public static final BlockFamily MANGO_PLANKS = BlockFamilies.familyBuilder(ModBlocks.MANGO_PLANKS.get())
            .button(ModBlocks.MANGO_BUTTON.get())
            .fence(ModBlocks.MANGO_FENCE.get())
            .fenceGate(ModBlocks.MANGO_FENCE_GATE.get())
            .pressurePlate(ModBlocks.MANGO_PRESSURE_PLATE.get())
            .sign(ModBlocks.MANGO_SIGN.get(),ModBlocks.MANGO_WALL_SIGN.get())
            .slab(ModBlocks.MANGO_SLAB.get())
            .stairs(ModBlocks.MANGO_STAIRS.get())
            .door(ModBlocks.MANGO_DOOR.get())
            .trapdoor(ModBlocks.MANGO_TRAPDOOR.get())
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();

    public ModelDataGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        skipItemBlock(blockStateModelGenerator);

        for(Supplier<Block> blockSupplier : ModBlocks.CRATES){
            createCrateBlock(blockSupplier.get(),blockStateModelGenerator);
        }

        for (Supplier<Block> cabinetBlock : ModBlocks.CABINET){
            createCabinet(cabinetBlock.get(),blockStateModelGenerator);
        }


        blockStateModelGenerator.createTrivialCube(ModBlocks.DURIAN_PEEL_BLOCK.get());
        createFeastBlock(blockStateModelGenerator);

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.LIME_BLOCK.get())
                .with(PropertyDispatch.property(LimeBlock.STACKS)
                        .generate(integer -> Variant.variant().with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/lime/stackable_lime_%d".formatted(integer))))
                ).with(createHorizontalFacingDispatch()));



        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.STACKABLE_MANGO_BLOCK.get())
                .with(PropertyDispatch.property(StackableMangoBlock.STACKS)
                        .generate(integer -> Variant.variant().with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/mango/stackable_mango_%d".formatted(integer))))
                ).with(createHorizontalFacingDispatch()));



        blockStateModelGenerator.blockStateOutput.accept(createSimpleBlock(ModBlocks.COCONUT.get(),ThaiDelightCommon.modid("block/coconut/coconut_block")));
        blockStateModelGenerator.modelOutput.accept(ModelLocationUtils.getModelLocation(ModBlocks.COCONUT.get().asItem()),new DelegatedModel(ThaiDelightCommon.modid("block/coconut/coconut_block")));
        blockStateModelGenerator.blockStateOutput.accept(createSimpleBlock(ModBlocks.STRIPPED_COCONUT.get(),ThaiDelightCommon.modid("block/coconut/stripped_coconut_block")));
        blockStateModelGenerator.modelOutput.accept(ModelLocationUtils.getModelLocation(ModBlocks.STRIPPED_COCONUT.get().asItem()),new DelegatedModel(ThaiDelightCommon.modid("block/coconut/stripped_coconut_block")));

        ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(ModBlocks.COCONUT_SAPLING.get()),
                TextureMapping.layer0(ThaiDelightCommon.modid("block/coconut/coconut_sapling")),
                blockStateModelGenerator.modelOutput
                );

        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ModBlocks.COCONUT_LEAF_CARPET.get(),
                ModelTemplates.CARPET.create(ModBlocks.COCONUT_LEAF_CARPET.get(),TextureMapping.wool(ThaiDelightCommon.modid("block/coconut_leaf_block_top")), blockStateModelGenerator.modelOutput)));

        ModelTemplates.FLAT_ITEM
                .create(
                        ModelLocationUtils.getModelLocation(ModBlocks.CRAB_EGG.get().asItem()),
                        TextureMapping.layer0(getBlockTexture(ModBlocks.CRAB_EGG.get())),
                        blockStateModelGenerator.modelOutput
                );
        blockStateModelGenerator.blockStateOutput.accept(createSimpleBlock(ModBlocks.CRAB_EGG.get(),ModelLocationUtils.getModelLocation(ModBlocks.CRAB_EGG.get())));

        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ModBlocks.MORTAR.get(),ModelLocationUtils.getModelLocation(ModBlocks.MORTAR.get())).with(createHorizontalFacingDispatch()));


        ResourceLocation resourceLocation = ModelTemplates.CUBE_COLUMN.create(ModBlocks.COCONUT_LEAF_BLOCK.get(), TextureMapping.logColumn(ModBlocks.COCONUT_LEAF_BLOCK.get()), blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createAxisAlignedPillarBlock(ModBlocks.COCONUT_LEAF_BLOCK.get(), resourceLocation));


        //Durian Model
        blockStateModelGenerator.woodProvider(ModBlocks.DURIAN_LOG.get()).logWithHorizontal(ModBlocks.DURIAN_LOG.get()).wood(ModBlocks.DURIAN_WOOD.get());
        blockStateModelGenerator.woodProvider(ModBlocks.STRIPPED_DURIAN_LOG.get()).logWithHorizontal(ModBlocks.STRIPPED_DURIAN_LOG.get()).wood(ModBlocks.STRIPPED_DURIAN_WOOD.get());
        blockStateModelGenerator.createHangingSign(ModBlocks.STRIPPED_DURIAN_LOG.get(),ModBlocks.DURIAN_HANGING_SIGN.get(),ModBlocks.DURIAN_WALL_HANGING_SIGN.get());
        blockStateModelGenerator.createTrivialBlock(ModBlocks.DURIAN_LEAVES.get(),TexturedModel.LEAVES);
        blockStateModelGenerator.createPlant(ModBlocks.DURIAN_SAPLING.get(),ModBlocks.POTTED_DURIAN_SAPLING.get(), TintState.NOT_TINTED);
        blockStateModelGenerator.family(ModBlocks.DURIAN_PLANKS.get()).generateFor(DURIAN_PLANKS);

        blockStateModelGenerator.woodProvider(ModBlocks.COCONUT_LOG.get()).logWithHorizontal(ModBlocks.COCONUT_LOG.get()).wood(ModBlocks.COCONUT_WOOD.get());
        blockStateModelGenerator.woodProvider(ModBlocks.STRIPPED_COCONUT_LOG.get()).logWithHorizontal(ModBlocks.STRIPPED_COCONUT_LOG.get()).wood(ModBlocks.STRIPPED_COCONUT_WOOD.get());
        blockStateModelGenerator.createHangingSign(ModBlocks.STRIPPED_COCONUT_LOG.get(),ModBlocks.COCONUT_HANGING_SIGN.get(),ModBlocks.COCONUT_WALL_HANGING_SIGN.get());

        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.COCONUT_LEAF.get())
                        .with(createBooleanModelDispatch(CoconutLeafBlock.END,
                                BuiltInRegistries.BLOCK.getKey(ModBlocks.COCONUT_LEAF.get()).withPrefix("block/coconut/").withSuffix("_end"),
                                BuiltInRegistries.BLOCK.getKey(ModBlocks.COCONUT_LEAF.get()).withPrefix("block/coconut/")
                                ))
                        .with(createHorizontalFacingDispatch())
        );

        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.BUDDING_COCONUT_LEAF.get())
                        .with(createBooleanModelDispatch(BuddingCoconutLeafBlock.COCONUT,
                                BuiltInRegistries.BLOCK.getKey(ModBlocks.BUDDING_COCONUT_LEAF.get()).withPrefix("block/coconut/").withSuffix("_coconut"),
                                BuiltInRegistries.BLOCK.getKey(ModBlocks.BUDDING_COCONUT_LEAF.get()).withPrefix("block/coconut/")
                        ))
                        .with(createHorizontalFacingDispatch())
        );

        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.COCONUT_LEAF_END.get(),
                                Variant.variant().with(VariantProperties.MODEL,BuiltInRegistries.BLOCK.getKey(ModBlocks.COCONUT_LEAF.get()).withPrefix("block/coconut/").withSuffix("_end")))
                        .with(createHorizontalFacingDispatch())
        );

        blockStateModelGenerator.family(ModBlocks.COCONUT_PLANKS.get()).generateFor(COCONUT_PLANKS);

        blockStateModelGenerator.woodProvider(ModBlocks.MANGO_LOG.get()).logWithHorizontal(ModBlocks.MANGO_LOG.get()).wood(ModBlocks.MANGO_WOOD.get());
        blockStateModelGenerator.woodProvider(ModBlocks.STRIPPED_MANGO_LOG.get()).logWithHorizontal(ModBlocks.STRIPPED_MANGO_LOG.get()).wood(ModBlocks.STRIPPED_MANGO_WOOD.get());
        blockStateModelGenerator.createHangingSign(ModBlocks.STRIPPED_MANGO_LOG.get(),ModBlocks.MANGO_HANGING_SIGN.get(),ModBlocks.MANGO_WALL_HANGING_SIGN.get());
        blockStateModelGenerator.createTrivialBlock(ModBlocks.MANGO_LEAVES.get(),TexturedModel.LEAVES);
        blockStateModelGenerator.family(ModBlocks.MANGO_PLANKS.get()).generateFor(MANGO_PLANKS);



        blockStateModelGenerator.createSimpleFlatItemModel(ModBlocks.DURIAN_FLOWER.get());
        ResourceLocation durian_flower_resource = TintState.NOT_TINTED.getCross().create(ModBlocks.DURIAN_FLOWER.get(), TextureMapping.cross(ModBlocks.DURIAN_FLOWER.get()), blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.DURIAN_FLOWER.get())
                        .with(PropertyDispatch.property(BlockStateProperties.HANGING)
                                .select(false,Variant.variant().with(VariantProperties.MODEL,durian_flower_resource))
                                .select(true,Variant.variant().with(VariantProperties.MODEL,durian_flower_resource).with(VariantProperties.X_ROT,VariantProperties.Rotation.R180))
                        )
        );

        createPapaya(blockStateModelGenerator);
        createMangoBlock(blockStateModelGenerator);
        createDurianBlocks(blockStateModelGenerator);
        createPepperCrop(blockStateModelGenerator);
        createLimeCrop(blockStateModelGenerator);
        createBasil(blockStateModelGenerator,ModBlocks.BASIL.get(),ModBlocks.WILD_BASIL.get(),ModBlocks.POTTED_BASIL.get());
        createButterflyPeaBlocks(blockStateModelGenerator);

        createSackBlock(blockStateModelGenerator);

        createFermentedFishCauldron(blockStateModelGenerator);
        createCauldron(ModBlocks.COCONUT_CAULDRON.get(),blockStateModelGenerator);
        createCauldron(ModBlocks.COCONUT_MILK_CAULDRON.get(),blockStateModelGenerator);

        blockStateModelGenerator.createPlant(ModBlocks.LIME_SAPLING.get(),ModBlocks.POTTED_LIME_SAPLING.get(), TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(ModBlocks.MANGO_SAPLING.get(),ModBlocks.POTTED_MANGO_SAPLING.get(), TintState.NOT_TINTED);
        blockStateModelGenerator.blockStateOutput.accept(createSimpleBlock(ModBlocks.COCONUT_SAPLING.get(),ThaiDelightCommon.modid("block/coconut/coconut_sapling")));
        blockStateModelGenerator.blockStateOutput.accept(createSimpleBlock(ModBlocks.COCONUT_SAPLING_CROP.get(),ThaiDelightCommon.modid("block/coconut/coconut_sapling_crop")));

        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ModBlocks.POTTED_COCONUT_SAPLING.get(),
                TintState.NOT_TINTED.getCrossPot().create(
                        ModBlocks.POTTED_COCONUT_SAPLING.get(),
                        TextureMapping.plant(ThaiDelightCommon.modid("block/coconut/coconut_sapling")),
                        blockStateModelGenerator.modelOutput)));
    }



    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        for(Supplier<Item> flatItem : ModItems.FLAT_ITEMS){
            itemModelGenerator.generateFlatItem(flatItem.get(),ModelTemplates.FLAT_ITEM);
        }

        itemModelGenerator.generateFlatItem(ModItems.CRAB_SPAWN_EGG.get(),SPAWN_EGG);
        itemModelGenerator.generateFlatItem(ModItems.DRAGONFLY_SPAWN_EGG.get(),SPAWN_EGG);

        itemModelGenerator.generateFlatItem(ModItems.DURIAN_BOAT.get(),ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DURIAN_CHEST_BOAT.get(),ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MANGO_BOAT.get(),ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MANGO_CHEST_BOAT.get(),ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COCONUT_BOAT.get(),ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COCONUT_CHEST_BOAT.get(),ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateLayeredItem(
                ModelLocationUtils.getModelLocation(ModItems.COCONUT_MILK_ICE_CREAM.get()),
                ThaiDelightCommon.modid("item/coconut_milk_ice_cream"),
                ThaiDelightCommon.modid("item/coconut_milk_ice_cream_bowl"));

    }

    private static void skipItemBlock(BlockModelGenerators blockStateModelGenerator){
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.SACK.get());
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.COCONUT_LEAF.get());
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.BUDDING_COCONUT_LEAF.get());
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.DURIAN_BLOCK.get());
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.SMALL_DURIAN_BLOCK.get());
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.DURIAN_CAKE.get());
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.MANGO_CHEESECAKE.get());
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.CRAB_EGG.get());
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.PAPAYA_LEAVES.get());
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.COCONUT.get());
        blockStateModelGenerator.skipAutoItemBlock(ModBlocks.STRIPPED_COCONUT.get());

    }

    private void createSackBlock(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.SACK.get())
                .with(PropertyDispatch.property(SackBlock.FILLED)
                        .generate((fullness) -> {
                            if(fullness){
                                return Variant.variant().with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/sack/sack_full_close"));
                            }
                            return Variant.variant().with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/sack/sack"));

                        })
                )
                .with(createHorizontalFacingDispatch()));
    }

    private static void createFeastBlock(BlockModelGenerators blockModelGenerators){
        createOmeletteModel(ModBlocks.OMELETTE_FEAST.get(),blockModelGenerators);
        createOmeletteModel(ModBlocks.BASIL_OMELETTE_FEAST.get(),blockModelGenerators);

        createCommonFeastState(ModBlocks.SOMTAM_FEAST.get(),blockModelGenerators);
        createCommonFeastState(ModBlocks.LARB_FEAST.get(),blockModelGenerators);
        createCommonFeastState(ModBlocks.CRAB_FRIED_RICE_FEAST.get(),blockModelGenerators);
        createCommonFeastState(ModBlocks.OMELETTE_FEAST.get(),blockModelGenerators);
        createCommonFeastState(ModBlocks.BASIL_OMELETTE_FEAST.get(),blockModelGenerators);
        createCommonFeastState(ModBlocks.PHAT_KAPHRAO_FEAST.get(),blockModelGenerators);
        createMangoStickRice(blockModelGenerators);
        createCommonFeastState(ModBlocks.PINEAPPLE_FRIED_RICE_FEAST.get(),blockModelGenerators);
        createCommonFeastState(ModBlocks.DURIAN_SLICE_FEAST.get(),blockModelGenerators);
        createMangoCheesecake(blockModelGenerators);
        createDurianCake(blockModelGenerators);
        createCoconutPies(blockModelGenerators);
    }

    private static void createCandleDurianCake(Block candleCakeBlock,Block candle,BlockModelGenerators blockModelGenerators){
        TextureMapping litTextureMapping = new TextureMapping()
                .put(TextureSlot.PARTICLE,ThaiDelightCommon.modid("block/durian_cake_side"))
                .put(TextureSlot.BOTTOM,ThaiDelightCommon.modid("block/durian_cake_bottom"))
                .put(TextureSlot.TOP,ThaiDelightCommon.modid("block/durian_cake_top"))
                .put(TextureSlot.SIDE,ThaiDelightCommon.modid("block/durian_cake_side"))
                .put(TextureSlot.CANDLE,TextureMapping.getBlockTexture(candle,"_lit"));

        TextureMapping unlitTextureMapping = new TextureMapping()
                .put(TextureSlot.PARTICLE,ThaiDelightCommon.modid("block/durian_cake_side"))
                .put(TextureSlot.BOTTOM,ThaiDelightCommon.modid("block/durian_cake_bottom"))
                .put(TextureSlot.TOP,ThaiDelightCommon.modid("block/durian_cake_top"))
                .put(TextureSlot.SIDE,ThaiDelightCommon.modid("block/durian_cake_side"))
                .put(TextureSlot.CANDLE,TextureMapping.getBlockTexture(candle));

        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(candleCakeBlock)
                .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT,
                        ModelTemplates.CANDLE_CAKE.createWithSuffix(candleCakeBlock,"_lit", litTextureMapping,blockModelGenerators.modelOutput),
                        ModelTemplates.CANDLE_CAKE.create(candleCakeBlock,unlitTextureMapping,blockModelGenerators.modelOutput)
                ))
        );
    }

    private static void createCoconutPies(BlockModelGenerators blockStateModelGenerator){
        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.COCONUT_PIE.get())
                        .with(PropertyDispatch.property(PieBlock.BITES)
                                .select(0,Variant.variant().with(VariantProperties.MODEL,PIE.create(ThaiDelightCommon.modid("block/feast/coconut_pie"),
                                        new TextureMapping()
                                                .put(TextureSlot.TOP,ThaiDelightCommon.modid("block/coconut_pie_top"))
                                                .put(TextureSlot.SIDE,ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie_side"))
                                                .put(TextureSlot.BOTTOM,ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie_bottom"))
                                                .put(TextureSlot.PARTICLE,ThaiDelightCommon.modid("block/coconut_pie_top"))
                                        ,blockStateModelGenerator.modelOutput))
                                )
                                .select(1,Variant.variant().with(VariantProperties.MODEL,PIE_SLICE1.create(ThaiDelightCommon.modid("block/feast/coconut_pie_slice1"),
                                        new TextureMapping()
                                                .put(TextureSlot.TOP,ThaiDelightCommon.modid("block/coconut_pie_top"))
                                                .put(TextureSlot.SIDE,ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie_side"))
                                                .put(TextureSlot.BOTTOM,ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie_bottom"))
                                                .put(INNER,ThaiDelightCommon.modid("block/coconut_pie_inside"))
                                                .put(TextureSlot.PARTICLE,ThaiDelightCommon.modid("block/coconut_pie_top"))
                                        ,blockStateModelGenerator.modelOutput))
                                )
                                .select(2,Variant.variant().with(VariantProperties.MODEL,PIE_SLICE2.create(ThaiDelightCommon.modid("block/feast/coconut_pie_slice2"),
                                        new TextureMapping()
                                                .put(TextureSlot.TOP,ThaiDelightCommon.modid("block/coconut_pie_top"))
                                                .put(TextureSlot.SIDE,ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie_side"))
                                                .put(TextureSlot.BOTTOM,ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie_bottom"))
                                                .put(INNER,ThaiDelightCommon.modid("block/coconut_pie_inside"))
                                                .put(TextureSlot.PARTICLE,ThaiDelightCommon.modid("block/coconut_pie_top"))
                                        ,blockStateModelGenerator.modelOutput))
                                )
                                .select(3,Variant.variant().with(VariantProperties.MODEL,PIE_SLICE3.create(ThaiDelightCommon.modid("block/feast/coconut_pie_slice3"),
                                        new TextureMapping()
                                                .put(TextureSlot.TOP,ThaiDelightCommon.modid("block/coconut_pie_top"))
                                                .put(TextureSlot.SIDE,ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie_side"))
                                                .put(TextureSlot.BOTTOM,ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie_bottom"))
                                                .put(INNER,ThaiDelightCommon.modid("block/coconut_pie_inside"))
                                                .put(TextureSlot.PARTICLE,ThaiDelightCommon.modid("block/coconut_pie_top"))
                                        ,blockStateModelGenerator.modelOutput))
                                )
                        ).with(createHorizontalFacingDispatch())
        );

        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.HONEY_COCONUT_PIE.get())
                        .with(PropertyDispatch.property(PieBlock.BITES)
                                .select(0,Variant.variant().with(VariantProperties.MODEL,PIE.create(ThaiDelightCommon.modid("block/feast/honey_coconut_pie"),
                                        new TextureMapping()
                                                .put(TextureSlot.TOP,ThaiDelightCommon.modid("block/honey_coconut_pie_top"))
                                                .put(TextureSlot.SIDE,ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie_side"))
                                                .put(TextureSlot.BOTTOM,ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie_bottom"))
                                                .put(TextureSlot.PARTICLE,ThaiDelightCommon.modid("block/honey_coconut_pie_top"))
                                        ,blockStateModelGenerator.modelOutput))
                                )
                                .select(1,Variant.variant().with(VariantProperties.MODEL,PIE_SLICE1.create(ThaiDelightCommon.modid("block/feast/honey_coconut_pie_slice1"),
                                        new TextureMapping()
                                                .put(TextureSlot.TOP,ThaiDelightCommon.modid("block/honey_coconut_pie_top"))
                                                .put(TextureSlot.SIDE,ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie_side"))
                                                .put(TextureSlot.BOTTOM,ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie_bottom"))
                                                .put(INNER,ThaiDelightCommon.modid("block/coconut_pie_inside"))
                                                .put(TextureSlot.PARTICLE,ThaiDelightCommon.modid("block/honey_coconut_pie_top"))
                                        ,blockStateModelGenerator.modelOutput))
                                )
                                .select(2,Variant.variant().with(VariantProperties.MODEL,PIE_SLICE2.create(ThaiDelightCommon.modid("block/feast/honey_coconut_pie_slice2"),
                                        new TextureMapping()
                                                .put(TextureSlot.TOP,ThaiDelightCommon.modid("block/honey_coconut_pie_top"))
                                                .put(TextureSlot.SIDE,ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie_side"))
                                                .put(TextureSlot.BOTTOM,ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie_bottom"))
                                                .put(INNER,ThaiDelightCommon.modid("block/coconut_pie_inside"))
                                                .put(TextureSlot.PARTICLE,ThaiDelightCommon.modid("block/honey_coconut_pie_top"))
                                        ,blockStateModelGenerator.modelOutput))
                                )
                                .select(3,Variant.variant().with(VariantProperties.MODEL,PIE_SLICE3.create(ThaiDelightCommon.modid("block/feast/honey_coconut_pie_slice3"),
                                        new TextureMapping()
                                                .put(TextureSlot.TOP,ThaiDelightCommon.modid("block/honey_coconut_pie_top"))
                                                .put(TextureSlot.SIDE,ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie_side"))
                                                .put(TextureSlot.BOTTOM,ResourceLocation.fromNamespaceAndPath("farmersdelight","block/pie_bottom"))
                                                .put(INNER,ThaiDelightCommon.modid("block/coconut_pie_inside"))
                                                .put(TextureSlot.PARTICLE,ThaiDelightCommon.modid("block/honey_coconut_pie_top"))
                                        ,blockStateModelGenerator.modelOutput))
                                )
                        ).with(createHorizontalFacingDispatch())
        );
    }

    private static void createDurianBlocks(BlockModelGenerators blockModelGenerators){
        blockModelGenerators.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.HANGING_DURIAN.get())
                        .with(PropertyDispatch.property(BlockStateProperties.AGE_1)
                                .select(0,Variant.variant()
                                        .with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/durian/durian_block_stage0_hanging")))
                                .select(1,Variant.variant()
                                        .with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/durian/durian")))

                        )
        );

        blockModelGenerators.blockStateOutput.accept(
                BlockModelGenerators.createSimpleBlock(
                        ModBlocks.DURIAN_BLOCK.get(),
                        ThaiDelightCommon.modid("block/durian/durian")
                ));


        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.SMALL_DURIAN_BLOCK.get())
                .with(PropertyDispatch.property(SmallDurianBlock.STACKS)
                        .generate(integer -> {
                            if(integer == 1){
                                return Variant.variant().with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/durian/durian_block_stage0"));
                            }
                            return Variant.variant().with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/durian/stackable_durian_%d".formatted(integer)));
                        })
                ).with(createHorizontalFacingDispatch()));
    }

    private static void createDurianCake(BlockModelGenerators blockStateModelGenerator){
        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.DURIAN_CAKE.get())
                        .with(PropertyDispatch.property(BlockStateProperties.BITES)
                                .select(0, Variant.variant().with(VariantProperties.MODEL, CAKE.create(ThaiDelightCommon.modid("block/feast/durian_cake"),
                                        new TextureMapping()
                                                .put(TextureSlot.TOP,ThaiDelightCommon.modid("block/durian_cake_top"))
                                                .put(TextureSlot.SIDE,ThaiDelightCommon.modid("block/durian_cake_side"))
                                                .put(TextureSlot.BOTTOM,ResourceLocation.fromNamespaceAndPath("minecraft","block/cake_bottom")),blockStateModelGenerator.modelOutput)
                                ))
                                .select(1, Variant.variant().with(VariantProperties.MODEL, CAKE_SLICE1.create(ThaiDelightCommon.modid("block/feast/durian_cake_slice1"),
                                        new TextureMapping()
                                                .put(TextureSlot.TOP,ThaiDelightCommon.modid("block/durian_cake_top"))
                                                .put(TextureSlot.SIDE,ThaiDelightCommon.modid("block/durian_cake_side"))
                                                .put(TextureSlot.BOTTOM,ResourceLocation.fromNamespaceAndPath("minecraft","block/cake_bottom"))
                                                .put(TextureSlot.INSIDE,ThaiDelightCommon.modid("block/durian_cake_inside")),blockStateModelGenerator.modelOutput)
                                ))
                                .select(2, Variant.variant().with(VariantProperties.MODEL, CAKE_SLICE2.create(ThaiDelightCommon.modid("block/feast/durian_cake_slice2"),
                                        new TextureMapping()
                                                .put(TextureSlot.TOP,ThaiDelightCommon.modid("block/durian_cake_top"))
                                                .put(TextureSlot.SIDE,ThaiDelightCommon.modid("block/durian_cake_side"))
                                                .put(TextureSlot.BOTTOM,ResourceLocation.fromNamespaceAndPath("minecraft","block/cake_bottom"))
                                                .put(TextureSlot.INSIDE,ThaiDelightCommon.modid("block/durian_cake_inside")),blockStateModelGenerator.modelOutput)
                                ))
                                .select(3, Variant.variant().with(VariantProperties.MODEL, CAKE_SLICE3.create(ThaiDelightCommon.modid("block/feast/durian_cake_slice3"),
                                        new TextureMapping()
                                                .put(TextureSlot.TOP,ThaiDelightCommon.modid("block/durian_cake_top"))
                                                .put(TextureSlot.SIDE,ThaiDelightCommon.modid("block/durian_cake_side"))
                                                .put(TextureSlot.BOTTOM,ResourceLocation.fromNamespaceAndPath("minecraft","block/cake_bottom"))
                                                .put(TextureSlot.INSIDE,ThaiDelightCommon.modid("block/durian_cake_inside")),blockStateModelGenerator.modelOutput)))
                                .select(4, Variant.variant().with(VariantProperties.MODEL, CAKE_SLICE4.create(ThaiDelightCommon.modid("block/feast/durian_cake_slice4"),
                                        new TextureMapping()
                                                .put(TextureSlot.TOP,ThaiDelightCommon.modid("block/durian_cake_top"))
                                                .put(TextureSlot.SIDE,ThaiDelightCommon.modid("block/durian_cake_side"))
                                                .put(TextureSlot.BOTTOM,ResourceLocation.fromNamespaceAndPath("minecraft","block/cake_bottom"))
                                                .put(TextureSlot.INSIDE,ThaiDelightCommon.modid("block/durian_cake_inside")),blockStateModelGenerator.modelOutput)))
                                .select(5, Variant.variant().with(VariantProperties.MODEL, CAKE_SLICE5.create(ThaiDelightCommon.modid("block/feast/durian_cake_slice5"),
                                        new TextureMapping()
                                                .put(TextureSlot.TOP,ThaiDelightCommon.modid("block/durian_cake_top"))
                                                .put(TextureSlot.SIDE,ThaiDelightCommon.modid("block/durian_cake_side"))
                                                .put(TextureSlot.BOTTOM,ResourceLocation.fromNamespaceAndPath("minecraft","block/cake_bottom"))
                                                .put(TextureSlot.INSIDE,ThaiDelightCommon.modid("block/durian_cake_inside")),blockStateModelGenerator.modelOutput)))
                                .select(6, Variant.variant().with(VariantProperties.MODEL, CAKE_SLICE6.create(ThaiDelightCommon.modid("block/feast/durian_cake_slice6"),
                                        new TextureMapping()
                                                .put(TextureSlot.TOP,ThaiDelightCommon.modid("block/durian_cake_top"))
                                                .put(TextureSlot.SIDE,ThaiDelightCommon.modid("block/durian_cake_side"))
                                                .put(TextureSlot.BOTTOM,ResourceLocation.fromNamespaceAndPath("minecraft","block/cake_bottom"))
                                                .put(TextureSlot.INSIDE,ThaiDelightCommon.modid("block/durian_cake_inside")),blockStateModelGenerator.modelOutput)))
                        )
        );

        createCandleDurianCake(ModBlocks.CANDLE_DURIAN_CAKE.get(),Blocks.CANDLE,blockStateModelGenerator);
        createCandleDurianCake(ModBlocks.WHITE_CANDLE_DURIAN_CAKE.get(),Blocks.WHITE_CANDLE,blockStateModelGenerator);
        createCandleDurianCake(ModBlocks.LIGHT_GRAY_CANDLE_DURIAN_CAKE.get(),Blocks.LIGHT_GRAY_CANDLE,blockStateModelGenerator);
        createCandleDurianCake(ModBlocks.GRAY_CANDLE_DURIAN_CAKE.get(),Blocks.GRAY_CANDLE,blockStateModelGenerator);
        createCandleDurianCake(ModBlocks.BLACK_CANDLE_DURIAN_CAKE.get(),Blocks.BLACK_CANDLE,blockStateModelGenerator);
        createCandleDurianCake(ModBlocks.BROWN_CANDLE_DURIAN_CAKE.get(),Blocks.BROWN_CANDLE,blockStateModelGenerator);
        createCandleDurianCake(ModBlocks.RED_CANDLE_DURIAN_CAKE.get(),Blocks.RED_CANDLE,blockStateModelGenerator);
        createCandleDurianCake(ModBlocks.ORANGE_CANDLE_DURIAN_CAKE.get(),Blocks.ORANGE_CANDLE,blockStateModelGenerator);
        createCandleDurianCake(ModBlocks.YELLOW_CANDLE_DURIAN_CAKE.get(),Blocks.YELLOW_CANDLE,blockStateModelGenerator);
        createCandleDurianCake(ModBlocks.LIME_CANDLE_DURIAN_CAKE.get(),Blocks.LIME_CANDLE,blockStateModelGenerator);
        createCandleDurianCake(ModBlocks.GREEN_CANDLE_DURIAN_CAKE.get(),Blocks.GREEN_CANDLE,blockStateModelGenerator);
        createCandleDurianCake(ModBlocks.CYAN_CANDLE_DURIAN_CAKE.get(),Blocks.CYAN_CANDLE,blockStateModelGenerator);
        createCandleDurianCake(ModBlocks.LIGHT_BLUE_CANDLE_DURIAN_CAKE.get(),Blocks.LIGHT_BLUE_CANDLE,blockStateModelGenerator);
        createCandleDurianCake(ModBlocks.BLUE_CANDLE_DURIAN_CAKE.get(),Blocks.BLUE_CANDLE,blockStateModelGenerator);
        createCandleDurianCake(ModBlocks.MAGENTA_CANDLE_DURIAN_CAKE.get(),Blocks.MAGENTA_CANDLE,blockStateModelGenerator);
        createCandleDurianCake(ModBlocks.PURPLE_CANDLE_DURIAN_CAKE.get(),Blocks.PURPLE_CANDLE,blockStateModelGenerator);
        createCandleDurianCake(ModBlocks.PINK_CANDLE_DURIAN_CAKE.get(),Blocks.PINK_CANDLE,blockStateModelGenerator);
    }

    private static void createMangoCheesecake(BlockModelGenerators blockModelGenerators){
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.MANGO_CHEESECAKE.get())
                .with(PropertyDispatch.property(PieBlock.BITES)
                        .generate(integer -> {
                            if(integer == 0){
                                return Variant.variant().with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/feast/mango_cheesecake/mango_cheesecake"));
                            }
                            return Variant.variant().with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/feast/mango_cheesecake/mango_cheesecake_slice%d".formatted(integer)));
                        })
                ).with(createHorizontalFacingDispatch())
        );
    }

    private static void createCommonFeastState(Block block,BlockModelGenerators blockModelGenerators){
        ResourceLocation blockID = BuiltInRegistries.BLOCK.getKey(block);
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.property(FeastBlock.SERVINGS)
                        .generate(integer -> {
                            if(integer == 0 ){
                                if(!((FeastBlock)block).hasLeftovers){
                                    return Variant.variant().with(VariantProperties.MODEL,
                                            ThaiDelightCommon.modid("block/feast/%s/%s_stage0".formatted(blockID.getPath(),blockID.getPath())));
                                }
                                return Variant.variant().with(VariantProperties.MODEL,
                                        ThaiDelightCommon.modid("block/feast/%s/%s_leftover".formatted(blockID.getPath(),blockID.getPath()))
                                );
                            }
                            return Variant.variant().with(VariantProperties.MODEL,
                                    ThaiDelightCommon.modid("block/feast/%s/%s_stage%d".formatted(blockID.getPath(),blockID.getPath(),integer-1))
                            );
                        })

                ).with(createHorizontalFacingDispatch())
        );
    }

    private static void createMangoStickRice(BlockModelGenerators blockModelGenerators){
        Block block = ModBlocks.MANGO_STICKY_RICE_FEAST.get();
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.property(MangoStickyRiceFeastBlock.MANGO_SERVINGS)
                        .generate(integer -> {
                            if(integer == 0){
                                return Variant.variant().with(VariantProperties.MODEL,
                                        ThaiDelightCommon.modid("block/feast/mango_sticky_rice_feast/mango_sticky_rice_feast_leftover")
                                );
                            }
                            return Variant.variant().with(VariantProperties.MODEL,
                                    ThaiDelightCommon.modid("block/feast/mango_sticky_rice_feast/mango_sticky_rice_feast_stage%d".formatted(integer-1))
                            );
                        })

                ).with(createHorizontalFacingDispatch())
        );
    }

    private static void createOmeletteModel(Block block,BlockModelGenerators blockModelGenerators){
        ModelTemplate[] modelTemplates = {TEMPLATE_OMELETTE_3,TEMPLATE_OMELETTE_2,TEMPLATE_OMELETTE_1,TEMPLATE_OMELETTE};
        ResourceLocation blockID = BuiltInRegistries.BLOCK.getKey(block);
        for(int i = 0; i < 4; i++){
            modelTemplates[i].create(
                    ThaiDelightCommon.modid("block/feast/%s/%s_stage%d".formatted(blockID.getPath(),blockID.getPath(),i)),
                    new TextureMapping().put(OMELETTE,ThaiDelightCommon.modid("block/%s".formatted(blockID.getPath().replaceAll("_feast","")))),blockModelGenerators.modelOutput
                    );
        }
    }

    private static void createFermentedFishCauldron(BlockModelGenerators blockStateModelGenerator){
        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.FERMENTED_FISH_CAULDRON.get())
                        .with(PropertyDispatch.properties(FermentedFishCauldronBlock.LEVEL,FermentedFishCauldronBlock.FERMENT)
                                .generate((heightLevel, fermentedLevel) -> {
                                    ModelTemplate cauldronLevel = ModelTemplates.CAULDRON_LEVEL1;
                                    switch (heightLevel){
                                        case 2 -> cauldronLevel = ModelTemplates.CAULDRON_LEVEL2;
                                        case 3 -> cauldronLevel = ModelTemplates.CAULDRON_FULL;
                                    }

                                    return Variant.variant().with(VariantProperties.MODEL,
                                            cauldronLevel.createWithSuffix(ModBlocks.FERMENTED_FISH_CAULDRON.get(),"_fermented%d_level%d".formatted(fermentedLevel,heightLevel),
                                                    TextureMapping.cauldron(ThaiDelightCommon.modid("block/fermented_fish_cauldron_fermented%d".formatted(fermentedLevel))),
                                                    blockStateModelGenerator.modelOutput));
                                })
                        )
        );
    }

    private static void createCropRope(Block block,BlockModelGenerators blockModelGenerators){
        blockModelGenerators.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(block)
                        .with(PropertyDispatch.properties(ButterflyPeaVineBlock.VINE_AGE, ButterflyPeaVineBlock.ROPELOGGED)
                                .generate((integer, aBoolean) -> {
                                    if(aBoolean){
                                        return Variant.variant().with(VariantProperties.MODEL,CROP_WITH_ROPE.createWithSuffix(block,"_stage%d_with_rope".formatted(integer),
                                                new TextureMapping()
                                                        .put(TextureSlot.CROP,BuiltInRegistries.BLOCK.getKey(block).withPrefix("block/butterfly_pea/").withSuffix("_stage%d".formatted(integer)))
                                                        .put(ROPE_SIDE,ResourceLocation.fromNamespaceAndPath("farmersdelight","block/tomatoes_coiled_rope"))
                                                        .put(ROPE_TOP,ResourceLocation.fromNamespaceAndPath("farmersdelight","block/rope_top"))
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

        STACKABLE_PAPAYA_1.create(ThaiDelightCommon.modid("block/papaya/stackable_papaya_1"),new TextureMapping().put(TextureSlot.ALL,ThaiDelightCommon.modid("block/papaya/papaya_fruit")), blockStateModelGenerator.modelOutput);
        STACKABLE_PAPAYA_2.create(ThaiDelightCommon.modid("block/papaya/stackable_papaya_2"),new TextureMapping().put(TextureSlot.ALL,ThaiDelightCommon.modid("block/papaya/papaya_fruit")), blockStateModelGenerator.modelOutput);
        STACKABLE_PAPAYA_1.create(ThaiDelightCommon.modid("block/papaya/stackable_raw_papaya_1"),new TextureMapping().put(TextureSlot.ALL,ThaiDelightCommon.modid("block/papaya/unripe_papaya_fruit")), blockStateModelGenerator.modelOutput);
        STACKABLE_PAPAYA_2.create(ThaiDelightCommon.modid("block/papaya/stackable_raw_papaya_2"),new TextureMapping().put(TextureSlot.ALL,ThaiDelightCommon.modid("block/papaya/unripe_papaya_fruit")), blockStateModelGenerator.modelOutput);

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.STACKABLE_PAPAYA.get())
                .with(PropertyDispatch.property(StackablePapayaBlock.STACKS)
                        .generate(integer -> Variant.variant().with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/papaya/stackable_papaya_%d".formatted(integer))))
                ).with(createHorizontalFacingDispatch()));

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.STACKABLE_RAW_PAPAYA.get())
                .with(PropertyDispatch.property(StackablePapayaBlock.STACKS)
                        .generate(integer -> Variant.variant().with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/papaya/stackable_raw_papaya_%d".formatted(integer))))
                ).with(createHorizontalFacingDispatch()));

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.PAPAYA_LOG.get())
                .with(PropertyDispatch.property(PapayaLogBlock.BOTTOM)
                        .select(true,Variant.variant()
                                .with(VariantProperties.MODEL,
                                        ModelTemplates.CUBE_COLUMN.createWithSuffix(ModBlocks.PAPAYA_LOG.get(),"_bottom", new TextureMapping()
                                                        .put(TextureSlot.SIDE, ThaiDelightCommon.modid("block/papaya/papaya_log_bottom"))
                                                        .put(TextureSlot.END, ThaiDelightCommon.modid("block/papaya/papaya_log_top"))
                                                        .put(TextureSlot.PARTICLE, ThaiDelightCommon.modid("block/papaya/papaya_log_bottom")),
                                                blockStateModelGenerator.modelOutput))
                        )
                        .select(false,Variant.variant()
                                .with(VariantProperties.MODEL,
                                        ModelTemplates.CUBE_COLUMN.create(ModBlocks.PAPAYA_LOG.get(),new TextureMapping()
                                                .put(TextureSlot.SIDE, ThaiDelightCommon.modid("block/papaya/papaya_log"))
                                                .put(TextureSlot.END, ThaiDelightCommon.modid("block/papaya/papaya_log_top"))
                                                .put(TextureSlot.PARTICLE, ThaiDelightCommon.modid("block/papaya/papaya_log")), blockStateModelGenerator.modelOutput))
                        )
                )
                .with(createRotatedPillar())
        );

        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createAxisAlignedPillarBlock(ModBlocks.PAPAYA_WOOD.get(),
                ModelTemplates.CUBE_COLUMN.create(ModBlocks.PAPAYA_WOOD.get(),
                        new TextureMapping()
                                .put(TextureSlot.END,ThaiDelightCommon.modid("block/papaya/papaya_log"))
                                .put(TextureSlot.SIDE,ThaiDelightCommon.modid("block/papaya/papaya_log")),
                        blockStateModelGenerator.modelOutput)));

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.STRIPPED_PAPAYA_LOG.get(),Variant.variant()
                        .with(VariantProperties.MODEL,
                                ModelTemplates.CUBE_COLUMN.create(ModBlocks.STRIPPED_PAPAYA_LOG.get(),new TextureMapping()
                                        .put(TextureSlot.SIDE, ThaiDelightCommon.modid("block/papaya/stripped_papaya_log"))
                                        .put(TextureSlot.END, ThaiDelightCommon.modid("block/papaya/papaya_log_top"))
                                        .put(TextureSlot.PARTICLE, ThaiDelightCommon.modid("block/papaya/stripped_papaya_log")), blockStateModelGenerator.modelOutput)))
                .with(createRotatedPillar())
        );

        blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createAxisAlignedPillarBlock(ModBlocks.STRIPPED_PAPAYA_WOOD.get(),
                ModelTemplates.CUBE_COLUMN.create(ModBlocks.STRIPPED_PAPAYA_WOOD.get(),
                        new TextureMapping()
                                .put(TextureSlot.END,ThaiDelightCommon.modid("block/papaya/stripped_papaya_log"))
                                .put(TextureSlot.SIDE,ThaiDelightCommon.modid("block/papaya/stripped_papaya_log")),
                        blockStateModelGenerator.modelOutput)));

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.PAPAYA_LEAVES_STEM.get(),Variant.variant()
                .with(VariantProperties.MODEL,ModelTemplates.CROSS.create(
                        ThaiDelightCommon.modid("block/papaya/papaya_leaves_stem"),
                        new TextureMapping().put(TextureSlot.CROSS,ThaiDelightCommon.modid("block/papaya/papaya_leaves_stem")),
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

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.WALL_PAPAYA_LEAVES.get(),Variant.variant()
                .with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/papaya/wall_papaya_leaves"))
        ).with(createHorizontalFacingDispatch()));

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.PAPAYA_LEAVES.get())
                .with(PropertyDispatch.property(PapayaLeavesStemBlock.PAPAYA_LEAVES_FACING)
                        .select(Direction.UP,Variant.variant()
                                .with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/papaya/papaya_leaves"))
                        )
                        .select(Direction.NORTH,Variant.variant()
                                .with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/papaya/wall_papaya_leaves"))
                        )
                        .select(Direction.EAST,Variant.variant()
                                .with(VariantProperties.Y_ROT,VariantProperties.Rotation.R90)
                                .with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/papaya/wall_papaya_leaves"))
                        )
                        .select(Direction.WEST,Variant.variant()
                                .with(VariantProperties.Y_ROT,VariantProperties.Rotation.R270)
                                .with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/papaya/wall_papaya_leaves"))
                        )
                        .select(Direction.SOUTH,Variant.variant()
                                .with(VariantProperties.Y_ROT,VariantProperties.Rotation.R180)
                                .with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/papaya/wall_papaya_leaves"))
                        )
        ));

        ModelTemplates.FLAT_ITEM.create(
                ModelLocationUtils.getModelLocation(ModItems.PAPAYA_FLOWER.get()),
                TextureMapping.layer0(ThaiDelightCommon.modid("block/papaya/papaya_flower")),
                blockStateModelGenerator.modelOutput);

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.BUDDING_PAPAYA_FLOWER.get(),
                Variant.variant().with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/papaya/papaya_flower1"))
        ).with(createHorizontalFacingDispatchAlt()));

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.PAPAYA_FLOWER.get())
                .with(PropertyDispatch.properties(PapayaFlowerBlock.FLOWERS,PapayaFlowerBlock.HANGING)
                        .generate((integer, hanging) -> {
                            if(hanging){
                                return Variant.variant()
                                        .with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/papaya/papaya_flower%d".formatted(integer)))
                                        .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90);
                            }
                            return Variant.variant()
                                    .with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/papaya/papaya_flower%d".formatted(integer)))
                                    .with(VariantProperties.X_ROT, VariantProperties.Rotation.R270);
                        })
                )
                .with(createHorizontalFacingDispatch()));

        blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.WALL_PAPAYA_FLOWER.get())
                .with(PropertyDispatch.property(WallPapayaFlowerBlock.FLOWERS)
                        .generate(integer -> {
                            return Variant.variant().with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/papaya/papaya_flower%d".formatted(integer)));

                        })
                )
                .with(createHorizontalFacingDispatch()));

        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.PAPAYA.get())
                        .with(PropertyDispatch.property(PapayaBlock.AGE).generate(integer -> {
                            return Variant.variant().with(VariantProperties.MODEL,
                                    ThaiDelightCommon.modid("block/papaya/papaya_age%d".formatted(integer)));
                        }))
                        .with(createHorizontalFacingDispatchAlt()));


        blockStateModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.PAPAYA_CROP.get())
                        .with(PropertyDispatch.property(BlockStateProperties.AGE_1).generate((integer) -> {
            return Variant.variant().with(VariantProperties.MODEL,
                    CROP_CROSS.create(
                            ThaiDelightCommon.modid("block/papaya/papaya_crop_stage%d".formatted(integer)),
                            new TextureMapping().put(TextureSlot.CROSS,ThaiDelightCommon.modid("block/papaya/papaya_crop_stage%d".formatted(integer))),
                            blockStateModelGenerator.modelOutput));
        })));

        Block block = ModBlocks.PAPAYA_SAPLING.get();
        ResourceLocation resourceLocation = ((IModelRenderType)TintState.NOT_TINTED.getCross()).muffins_thaidelight$setRenderType(CUT_OUT).create(block, TextureMapping.cross(block), blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.blockStateOutput.accept(createSimpleBlock(block, resourceLocation));
        blockStateModelGenerator.createSimpleFlatItemModel(block);
    }

    private static void createBlock(Block block, ModelTemplate modelTemplate, TextureMapping textureMapping, BlockModelGenerators blockModelGenerator){
        blockModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block,modelTemplate.create(block,textureMapping, blockModelGenerator.modelOutput)));

    }

    private static void createPepperCrop(BlockModelGenerators blockModelGenerators){

        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.BUDDING_PEPPER_CROP.get())
                .with(PropertyDispatch.property(FabricBuddingPepperBlock.PEPPER_AGE).generate(integer -> {
                    return Variant.variant().with(VariantProperties.MODEL,
                            CROP_CROSS.create(
                                    ThaiDelightCommon.modid("block/pepper/budding_pepper_age%d".formatted(integer)),
                                    TextureMapping.cross(ThaiDelightCommon.modid("block/pepper/budding_pepper_age%d".formatted(integer))),
                                    blockModelGenerators.modelOutput
                            )
                    );
                })
        ));

        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.PEPPER_CROP.get())
                .with(PropertyDispatch.property(PepperCropBlock.AGE).generate((integer) -> {
            return Variant.variant().with(VariantProperties.MODEL,
                        CROP_CROSS.create(
                                ThaiDelightCommon.modid("block/pepper/pepper_age%d".formatted(integer)),
                                TextureMapping.cross(ThaiDelightCommon.modid("block/pepper/pepper_age%d".formatted(integer))),
                                blockModelGenerators.modelOutput
                        )
                    );
        })));

        ModelTemplates.FLAT_ITEM
                .create(
                        ModelLocationUtils.getModelLocation(ModBlocks.WILD_PEPPER_CROP.get().asItem()),
                        TextureMapping.layer0(ThaiDelightCommon.modid("block/pepper/pepper_age2")),
                        blockModelGenerators.modelOutput
                );

        blockModelGenerators.blockStateOutput.accept(createSimpleBlock(ModBlocks.WILD_PEPPER_CROP.get(),
                ((IModelRenderType)ModelTemplates.CROSS).muffins_thaidelight$setRenderType(CUT_OUT).create(
                        ThaiDelightCommon.modid("block/pepper/wild_pepper_crop"),
                        TextureMapping.cross(ThaiDelightCommon.modid("block/pepper/pepper_age2")),
                        blockModelGenerators.modelOutput)

        ));
    }

    private static void createButterflyPeaBlocks(BlockModelGenerators blockModelGenerators){
        createCropRope(ModBlocks.BUTTERFLY_PEA_BLOCK.get(),blockModelGenerators);

        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.BUDDING_BUTTERFLY_PEA_BLOCK.get())
                .with(PropertyDispatch.property(FabricBuddingButterflyPeaBlock.BUDDING_AGE)
                        .generate(integer ->
                                Variant.variant().with(VariantProperties.MODEL,CROP_CROSS
                                        .create(
                                                ThaiDelightCommon.modid("block/butterfly_pea/budding_butterfly_pea_stage%d".formatted(integer) ),
                                                new TextureMapping().put(TextureSlot.CROSS,ThaiDelightCommon.modid("block/butterfly_pea/budding_butterfly_pea_stage%d".formatted(integer))),
                                                blockModelGenerators.modelOutput
                                                )
                                )
                        )
                )
        );

        ResourceLocation wall_block = WALL_FLOWER.create(ModBlocks.BUTTERFLY_PEA_WALL.get(),new TextureMapping()
                        .put(VINE,ThaiDelightCommon.modid("block/butterfly_pea/butterfly_pea_wall_vine"))
                        .put(FLOWER,ThaiDelightCommon.modid("block/butterfly_pea/butterfly_pea_flower")),
                blockModelGenerators.modelOutput);

        ModModelUtils.createCustomModelMultiFace(ModBlocks.BUTTERFLY_PEA_WALL.get(),wall_block,blockModelGenerators);
    }

    private static void createCauldron(Block block,BlockModelGenerators blockModelGenerators) {

        blockModelGenerators.blockStateOutput.accept(
            MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.property(LayeredCauldronBlock.LEVEL)
                    .select(1, Variant.variant()
                        .with(VariantProperties.MODEL, ModelTemplates.CAULDRON_LEVEL1
                            .createWithSuffix(block, "_level1",
                                TextureMapping.cauldron(getBlockTexture(block).withSuffix("_content")),
                                    blockModelGenerators.modelOutput
                            )
                        )
                    ).select(2, Variant.variant()
                        .with(VariantProperties.MODEL, ModelTemplates.CAULDRON_LEVEL2
                            .createWithSuffix(
                                    block, "_level2",
                                    TextureMapping.cauldron(getBlockTexture(block).withSuffix("_content")),
                                    blockModelGenerators.modelOutput
                            )
                        )
                    ).select(3, Variant.variant()
                        .with(VariantProperties.MODEL, ModelTemplates.CAULDRON_FULL
                            .createWithSuffix(
                                    block, "_full",
                                    TextureMapping.cauldron(getBlockTexture(block).withSuffix("_content")),
                                    blockModelGenerators.modelOutput))
                                )
                )
        );
    }

    private static TextureMapping upperLimeMapping(int age){
        return new TextureMapping()
                .put(TextureSlot.SIDE,ThaiDelightCommon.modid("block/lime/lime_leaves_side_age"+ age))
                .put(TextureSlot.TOP,ThaiDelightCommon.modid("block/lime/lime_leaves_top"))
                .put(TextureSlot.PLANT,ThaiDelightCommon.modid("block/lime/lime_leaves_layer"));
    }

    private static TextureMapping bottomLimeMapping(int age){
        return new TextureMapping()
                .put(TextureSlot.SIDE,ThaiDelightCommon.modid("block/lime/lime_leaves_side_age"+ age))
                .put(TextureSlot.BOTTOM,ThaiDelightCommon.modid("block/lime/lime_leaves_bottom"))
                .put(TextureSlot.STEM,ThaiDelightCommon.modid("block/lime/lime_stem"))
                .put(TextureSlot.PLANT,ThaiDelightCommon.modid("block/lime/lime_leaves_layer"));
    }

    private static void createBasil(BlockModelGenerators blockModelGenerators,Block block,Block wildBlock,Block potBlock){
        ResourceLocation cropBlockID = BuiltInRegistries.BLOCK.getKey(block);
        ResourceLocation wildBlockID = BuiltInRegistries.BLOCK.getKey(wildBlock);
        ResourceLocation potBlockID = BuiltInRegistries.BLOCK.getKey(potBlock);

        ResourceLocation modelResourceLocation = cropBlockID.withPath(string2 -> "block/" + string2 + "/" + string2);

        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.property(BasilCropBlock.AGE).generate(integer -> {
                    return Variant.variant().with(VariantProperties.MODEL,CROP_CROSS.create(modelResourceLocation.withSuffix("_age"+integer),
                            new TextureMapping().put(TextureSlot.CROSS,modelResourceLocation.withSuffix("_age"+integer)),blockModelGenerators.modelOutput));
                        })
                )
        );

        ModelTemplates.FLAT_ITEM
                .create(
                        ModelLocationUtils.getModelLocation(wildBlock.asItem()),
                        TextureMapping.layer0(modelResourceLocation.withSuffix("_age3")),
                        blockModelGenerators.modelOutput
                );

        blockModelGenerators.blockStateOutput.accept(createSimpleBlock(wildBlock,
                ((IModelRenderType)ModelTemplates.CROSS).muffins_thaidelight$setRenderType(CUT_OUT).create(
                        wildBlockID.withPath(s -> "block/%s/%s".formatted(cropBlockID.getPath(),s) ),
                        TextureMapping.cross(ThaiDelightCommon.modid("block/%s/%s_age3".formatted(cropBlockID.getPath(),cropBlockID.getPath()) )),
                        blockModelGenerators.modelOutput
                )));

        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(potBlock,
                ((IModelRenderType)TintState.NOT_TINTED.getCrossPot()).muffins_thaidelight$setRenderType(CUT_OUT).create(
                        potBlockID.withPath(s -> "block/%s/%s".formatted(cropBlockID.getPath(),s)),
                        TextureMapping.plant(ThaiDelightCommon.modid("block/%s/%s_age3".formatted(cropBlockID.getPath(),cropBlockID.getPath()) )),
                        blockModelGenerators.modelOutput)));
    }

    private static void createLimeCrop( BlockModelGenerators blockModelGenerator){
        ResourceLocation LIME_UPPER_AGE0 = LIME_UPPER_TEMPLATE.create(ThaiDelightCommon.modid("block/lime/lime_upper_age0"),upperLimeMapping(0), blockModelGenerator.modelOutput);
        ResourceLocation LIME_UPPER_AGE1 = LIME_UPPER_TEMPLATE.create(ThaiDelightCommon.modid("block/lime/lime_upper_age1"),upperLimeMapping(1), blockModelGenerator.modelOutput);
        ResourceLocation LIME_BOTTOM_AGE0 = LIME_BOTTOM_TEMPLATE.create(ThaiDelightCommon.modid("block/lime/lime_bottom_age0"),bottomLimeMapping(0),blockModelGenerator.modelOutput);
        ResourceLocation LIME_BOTTOM_AGE1 = LIME_BOTTOM_TEMPLATE.create(ThaiDelightCommon.modid("block/lime/lime_bottom_age1"),bottomLimeMapping(1),blockModelGenerator.modelOutput);


        blockModelGenerator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.LIME_PLANT.get())
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
                                .with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/lime/lime_bottom_age2"))
                        )
                        .select(2,DoubleBlockHalf.UPPER,Variant.variant()
                                .with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/lime/lime_upper_age2"))
                        )
                )
        );


    }

    private static void createMangoBlock(BlockModelGenerators blockModelGenerators){
        TextureMapping raw_mango_texture = TextureMapping.cube(ThaiDelightCommon.modid("block/raw_mango"));
        TextureMapping mango_texture = TextureMapping.cube(ThaiDelightCommon.modid("block/mango"));

        ResourceLocation hanging_raw_mango = HANGING_MANGO.create(ThaiDelightCommon.modid("block/hanging_mango_age1"),raw_mango_texture, blockModelGenerators.modelOutput);
        ResourceLocation hanging_mango = HANGING_MANGO.create(ThaiDelightCommon.modid("block/hanging_mango_age2"),mango_texture, blockModelGenerators.modelOutput);

        blockModelGenerators.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(ModBlocks.HANGING_MANGO_BLOCK.get())
                        .with(PropertyDispatch.property(HangingMangoBlock.AGE)
                                //Ground Raw Mango
                                .select(0,Variant.variant()
                                        .with(VariantProperties.MODEL,ThaiDelightCommon.modid("block/hanging_mango_age0"))
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

    private static void createCrateBlock(Block block, BlockModelGenerators blockModelGenerator){
        TextureMapping textureMapping = new TextureMapping()
                .put(TextureSlot.SIDE, getBlockTexture(block, "_side"))
                .put(TextureSlot.TOP, getBlockTexture(block, "_top"))
                .put(TextureSlot.BOTTOM, ResourceLocation.fromNamespaceAndPath("farmersdelight","block/crate_bottom"));
        createBlock(block,ModelTemplates.CUBE_BOTTOM_TOP,textureMapping,blockModelGenerator);
    }

    private static ModelTemplate createModItem(String string, TextureSlot... textureSlots) {
        return new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(ThaiDelightCommon.MOD_ID, "item/" + string)),Optional.empty(), textureSlots);
    }

    private static ModelTemplate createMincraftItem(String string, TextureSlot... textureSlots) {
        return new ModelTemplate(Optional.of(ResourceLocation.withDefaultNamespace("item/" + string)),Optional.empty(), textureSlots);
    }

}
