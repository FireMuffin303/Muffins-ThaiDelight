package net.firemuffin303.muffinsthaidelightfabric.registry;


import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.block.*;
import net.firemuffin303.muffinsthaidelightfabric.common.block.durian.DurianBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.durian.DurianFlowerBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.durian.DurianLeaveBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.lime.LimeBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.lime.LimeCropBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.lime.LimeLeavesBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.lime.LimePlantBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.mango.MangoBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.papaya.PapayaBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.papaya.PapayaCropBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.papaya.PapayaLogBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.world.trees.DurianTreeGrower;
import net.firemuffin303.muffinsthaidelightfabric.common.world.trees.LimeTreeGrower;
import net.firemuffin303.muffinsthaidelightfabric.common.world.trees.MangoTreeGrower;
import net.firemuffin303.muffinsthaidelightfabric.common.world.trees.PapayaTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.block.WildCropBlock;

import static net.minecraft.world.level.block.Blocks.*;

public class ModBlocks {

    //Functional Block
    public static final Block MORTAR = register("mortar",new MortarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).strength(0.5f,6.0f).noOcclusion().sound(SoundType.DECORATED_POT)));

    //Crate
    public static final Block LIME_CRATE = register("lime_crate",new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).destroyTime(2.0f).explosionResistance(3.0f).sound(SoundType.WOOD)));
    public static final Block PEPPER_CRATE = register("pepper_crate",new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).destroyTime(2.0f).explosionResistance(3.0f).sound(SoundType.WOOD)));
    public static final Block RAW_PAPAYA_CRATE = register("raw_papaya_crate",new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).destroyTime(2.0f).explosionResistance(3.0f).sound(SoundType.WOOD)));
    public static final Block PAPAYA_CRATE = register("papaya_crate",new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).destroyTime(2.0f).explosionResistance(3.0f).sound(SoundType.WOOD)));

    //Eggs
    public static final Block CRAB_EGG = register("flower_crab_egg",new CrabEggBlock(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN)));

    //Wild Crops
    public static final Block WILD_PEPPER_CROP = register("wild_pepper",new WildCropBlock(MobEffects.CONFUSION,6,BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)));

    //# Crops
    //## Lime
    public static final Block LIME_PLANT = register("lime_plant",new LimePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().noCollission().sound(SoundType.AZALEA_LEAVES).ignitedByLava().pushReaction(PushReaction.DESTROY)));

    public static final Block LIME_BUSH = register("lime_bush",new LimeCropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY)));
    public static final Block LIME_SAPLING = register("lime_sapling",new SaplingBlock(new LimeTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final Block POTTED_LIME_SAPLING = register("potted_lime_sapling",Blocks.flowerPot(ModBlocks.LIME_SAPLING));
    public static final Block LIME_LEAVES = register("lime_leaves",new LimeLeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().sound(SoundType.SWEET_BERRY_BUSH).ignitedByLava()));
    public static final Block LIME_BLOCK = register("lime_block",new LimeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).instabreak().noOcclusion().pushReaction(PushReaction.DESTROY).randomTicks().sound(SoundType.SWEET_BERRY_BUSH)));

    public static final Block PEPPER_CROP = register("pepper_crop",new PepperCropBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES)));

    //Durian
    public static final Block DURIAN_SAPLING = register("durian_sapling",new SaplingBlock(new DurianTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final Block POTTED_DURIAN_SAPLING = register("potted_durian_sapling",Blocks.flowerPot(DURIAN_SAPLING));
    public static final Block DURIAN_FLOWER = register("durian_flower",new DurianFlowerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).instabreak().noCollission().sound(SoundType.SPORE_BLOSSOM).pushReaction(PushReaction.DESTROY).randomTicks()));
    public static final Block DURIAN_BLOCK = register("durian_block",new DurianBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY).randomTicks()));
    public static final Block DURIAN_LEAVES = register("durian_leaves",new DurianLeaveBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    //Durian Woodset
    public static final Block DURIAN_LOG = register("durian_log",Blocks.log(MapColor.COLOR_LIGHT_GREEN, MapColor.COLOR_RED));
    public static final Block DURIAN_WOOD = register("durian_wood",new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block STRIPPED_DURIAN_LOG = register("stripped_durian_log",Blocks.log(MapColor.COLOR_RED,MapColor.COLOR_RED));
    public static final Block STRIPPED_DURIAN_WOOD = register("stripped_durian_wood",new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DURIAN_PLANKS = register("durian_planks",new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_GREEN).instrument(NoteBlockInstrument.BASS).strength(2.0f,3.0f).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block DURIAN_STAIRS = register("durian_stairs",new StairBlock(DURIAN_PLANKS.defaultBlockState(),BlockBehaviour.Properties.copy(DURIAN_PLANKS)));
    public static final Block DURIAN_SLAB = register("durian_slab",new SlabBlock(BlockBehaviour.Properties.copy(DURIAN_PLANKS)));
    public static final Block DURIAN_FENCE = register("durian_fence", new FenceBlock(BlockBehaviour.Properties.copy(DURIAN_PLANKS)));
    public static final Block DURIAN_FENCE_GATE = register("durian_fence_gate",new FenceGateBlock(BlockBehaviour.Properties.of().mapColor(DURIAN_PLANKS.defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0f,3.0f).ignitedByLava(), WoodType.CHERRY));
    public static final Block DURIAN_DOOR = register("durian_door",new DoorBlock(BlockBehaviour.Properties.of()
            .mapColor(DURIAN_PLANKS.defaultMapColor())
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0f)
            .noOcclusion()
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY),
            BlockSetType.CHERRY
    ));
    public static final Block DURIAN_TRAPDOOR = register("durian_trapdoor",new TrapDoorBlock(BlockBehaviour.Properties.of()
            .mapColor(DURIAN_PLANKS.defaultMapColor())
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0f)
            .noOcclusion()
            .isValidSpawn(Blocks::never)
            .ignitedByLava(),
            BlockSetType.CHERRY
    ));

    public static final Block DURIAN_PRESSURE_PLATE = register("durian_pressure_plate",new PressurePlateBlock(
            PressurePlateBlock.Sensitivity.EVERYTHING,
            BlockBehaviour.Properties.of()
                    .mapColor(DURIAN_PLANKS.defaultMapColor())
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(0.5f)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY),
            BlockSetType.CHERRY
    ));

    public static final Block DURIAN_BUTTON = register("durian_button",Blocks.woodenButton(BlockSetType.CHERRY));
    public static final Block DURIAN_SIGN = register("durian_sign",new TerraformSignBlock(
            ThaiDelight.modid("entity/signs/durian"),
            BlockBehaviour.Properties.of()
            .mapColor(ModBlocks.DURIAN_PLANKS.defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .noCollission()
            .strength(1.0f)
            .ignitedByLava()
    ));

    public static final Block DURIAN_WALL_SIGN = register("durian_wall_sign",new TerraformWallSignBlock(
            ThaiDelight.modid("entity/signs/durian"),
            BlockBehaviour.Properties.of()
            .mapColor(ModBlocks.DURIAN_PLANKS.defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .noCollission()
            .strength(1.0f)
            .dropsLike(ModBlocks.DURIAN_SIGN)
            .ignitedByLava()
    ));

    public static final Block DURIAN_HANGING_SIGN = register("durian_hanging_sign",new TerraformHangingSignBlock(
            ThaiDelight.modid("entity/signs/hanging/durian"),
            ThaiDelight.modid("textures/gui/hanging_signs/durian"),
            BlockBehaviour.Properties.of()
            .mapColor(ModBlocks.DURIAN_PLANKS.defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .noCollission()
            .strength(1.0f)
            .ignitedByLava()
    ));

    public static final Block DURIAN_WALL_HANGING_SIGN = register("durian_wall_hanging_sign",new TerraformWallHangingSignBlock(
            ThaiDelight.modid("entity/signs/hanging/durian"),
            ThaiDelight.modid("textures/gui/hanging_signs/durian"),
            BlockBehaviour.Properties.of()
            .mapColor(ModBlocks.DURIAN_PLANKS.defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .noCollission()
            .strength(1.0f)
            .ignitedByLava()
            .dropsLike(DURIAN_HANGING_SIGN)
    ));

    public static final Block DURIAN_CABINET = register("durian_cabinet",new CabinetBlock(BlockBehaviour.Properties.copy(BARREL)));

    //Coconut
    public static final Block COCONUT_SAPLING = register("coconut_sapling",new SaplingBlock(new DurianTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final Block POTTED_COCONUT_SAPLING = register("potted_coconut_sapling",Blocks.flowerPot(COCONUT_SAPLING));
    public static final Block COCONUT_LEAVES = register("coconut_leaves",new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    public static final Block COCONUT_LOG = register("coconut_log",Blocks.log(MapColor.COLOR_BROWN,MapColor.COLOR_YELLOW));
    public static final Block COCONUT_WOOD = register("coconut_wood",new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block STRIPPED_COCONUT_LOG = register("stripped_coconut_log",Blocks.log(MapColor.COLOR_RED,MapColor.COLOR_RED));
    public static final Block STRIPPED_COCONUT_WOOD = register("stripped_coconut_wood",new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block COCONUT_PLANKS = register("coconut_planks",new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_GREEN).instrument(NoteBlockInstrument.BASS).strength(2.0f,3.0f).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block COCONUT_STAIRS = register("coconut_stairs",new StairBlock(COCONUT_PLANKS.defaultBlockState(),BlockBehaviour.Properties.copy(COCONUT_PLANKS)));
    public static final Block COCONUT_SLAB = register("coconut_slab",new SlabBlock(BlockBehaviour.Properties.copy(COCONUT_PLANKS)));
    public static final Block COCONUT_FENCE = register("coconut_fence",new FenceBlock(BlockBehaviour.Properties.copy(COCONUT_PLANKS)));
    public static final Block COCONUT_FENCE_GATE = register("coconut_fence_gate",new FenceGateBlock(BlockBehaviour.Properties.of()
            .mapColor(COCONUT_PLANKS.defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0f,3.0f)
            .ignitedByLava(),
            WoodType.CHERRY
    ));
    public static final Block COCONUT_DOOR = register("coconut_door",new DoorBlock(BlockBehaviour.Properties.of()
            .mapColor(COCONUT_PLANKS.defaultMapColor())
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0f)
            .noOcclusion()
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY),
            BlockSetType.CHERRY
    ));
    public static final Block COCONUT_TRAPDOOR = register("coconut_trapdoor",new TrapDoorBlock(BlockBehaviour.Properties.of()
            .mapColor(COCONUT_PLANKS.defaultMapColor())
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0f)
            .noOcclusion()
            .isValidSpawn(Blocks::never)
            .ignitedByLava(),
            BlockSetType.CHERRY
    ));
    public static final Block COCONUT_PRESSURE_PLATE = register("coconut_pressure_plate",new PressurePlateBlock(
            PressurePlateBlock.Sensitivity.EVERYTHING,
            BlockBehaviour.Properties.of()
                    .mapColor(COCONUT_PLANKS.defaultMapColor())
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(0.5f)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY),
            BlockSetType.CHERRY
    ));
    public static final Block COCONUT_BUTTON = register("coconut_button",Blocks.woodenButton(BlockSetType.CHERRY));
    public static final Block COCONUT_SIGN = register("coconut_sign",new TerraformSignBlock(
            ThaiDelight.modid("entity/signs/coconut"),
            BlockBehaviour.Properties.of()
            .mapColor(ModBlocks.DURIAN_PLANKS.defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .noCollission()
            .strength(1.0f)
            .ignitedByLava()
    ));
    public static final Block COCONUT_WALL_SIGN = register("coconut_wall_sign",new TerraformWallSignBlock(
            ThaiDelight.modid("entity/signs/durian"),
            BlockBehaviour.Properties.of()
            .mapColor(ModBlocks.COCONUT_PLANKS.defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .noCollission()
            .strength(1.0f)
            .dropsLike(ModBlocks.DURIAN_SIGN)
            .ignitedByLava()
    ));
    public static final Block COCONUT_HANGING_SIGN = register("coconut_hanging_sign",new TerraformHangingSignBlock(
            ThaiDelight.modid("entity/signs/hanging/coconut"),
            ThaiDelight.modid("textures/gui/hanging_signs/coconut"),
            BlockBehaviour.Properties.of()
            .mapColor(ModBlocks.COCONUT_PLANKS.defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .noCollission()
            .strength(1.0f)
            .ignitedByLava()
    ));
    public static final Block COCONUT_WALL_HANGING_SIGN = register("coconut_wall_hanging_sign",new TerraformWallHangingSignBlock(
            ThaiDelight.modid("entity/signs/hanging/coconut"),
            ThaiDelight.modid("textures/gui/hanging_signs/coconut"),
            BlockBehaviour.Properties.of()
            .mapColor(ModBlocks.COCONUT_PLANKS.defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .noCollission()
            .strength(1.0f)
            .ignitedByLava()
            .dropsLike(DURIAN_HANGING_SIGN)
    ));
    public static final Block COCONUT_CABINET = register("coconut_cabinet",new CabinetBlock(BlockBehaviour.Properties.copy(BARREL)));

    //----------------- Mango -----------
    public static final Block MANGO_SAPLING = register("mango_sapling",new SaplingBlock(new MangoTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final Block POTTED_MANGO_SAPLING = register("potted_mango_sapling",Blocks.flowerPot(ModBlocks.MANGO_SAPLING));
    public static final Block MANGO_LEAVES = register("mango_leaves",new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final Block MANGO_BLOCK = register("mango_block",new MangoBlock(BlockBehaviour.Properties.copy(Blocks.MELON)));

    public static final Block MANGO_LOG = register("mango_log", Blocks.log(MapColor.COLOR_BROWN,MapColor.COLOR_YELLOW));
    public static final Block MANGO_WOOD = register("mango_wood",new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block STRIPPED_MANGO_LOG = register("stripped_mango_log",Blocks.log(MapColor.COLOR_YELLOW,MapColor.COLOR_YELLOW));
    public static final Block STRIPPED_MANGO_WOOD = register("stripped_mango_wood",new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block MANGO_PLANKS = register("mango_planks",new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_GREEN).instrument(NoteBlockInstrument.BASS).strength(2.0f,3.0f).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block MANGO_STAIRS = register("mango_stairs",new StairBlock(MANGO_PLANKS.defaultBlockState(),BlockBehaviour.Properties.copy(MANGO_PLANKS)));
    public static final Block MANGO_SLAB = register("mango_slab",new SlabBlock(BlockBehaviour.Properties.copy(MANGO_PLANKS)));
    public static final Block MANGO_FENCE = register("mango_fence",new FenceBlock(BlockBehaviour.Properties.copy(MANGO_PLANKS)));
    public static final Block MANGO_FENCE_GATE = register("mango_fence_gate",new FenceGateBlock(BlockBehaviour.Properties.of()
            .mapColor(MANGO_PLANKS.defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0f,3.0f)
            .ignitedByLava(),
            WoodType.CHERRY
    ));
    public static final Block MANGO_DOOR = register("mango_door",new DoorBlock(BlockBehaviour.Properties.of()
            .mapColor(MANGO_PLANKS.defaultMapColor())
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0f)
            .noOcclusion()
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY),
            BlockSetType.CHERRY
    ));
    public static final Block MANGO_TRAPDOOR = register("mango_trapdoor",new TrapDoorBlock(BlockBehaviour.Properties.of()
            .mapColor(MANGO_PLANKS.defaultMapColor())
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0f)
            .noOcclusion()
            .isValidSpawn(Blocks::never)
            .ignitedByLava(),
            BlockSetType.CHERRY
    ));
    public static final Block MANGO_PRESSURE_PLATE = register("mango_pressure_plate",new PressurePlateBlock(
            PressurePlateBlock.Sensitivity.EVERYTHING,
            BlockBehaviour.Properties.of()
                    .mapColor(MANGO_PLANKS.defaultMapColor())
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(0.5f)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY),
            BlockSetType.CHERRY
    ));
    public static final Block MANGO_BUTTON = register("mango_button",Blocks.woodenButton(BlockSetType.CHERRY));
    public static final Block MANGO_SIGN = register("mango_sign",new TerraformSignBlock(
            ThaiDelight.modid("entity/signs/mango"),
            BlockBehaviour.Properties.of()
            .mapColor(ModBlocks.MANGO_PLANKS.defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .noCollission()
            .strength(1.0f)
            .ignitedByLava()
    ));
    public static final Block MANGO_WALL_SIGN = register("mango_wall_sign",new TerraformWallSignBlock(
            ThaiDelight.modid("entity/signs/mango"),
            BlockBehaviour.Properties.of()
            .mapColor(ModBlocks.MANGO_PLANKS.defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .noCollission()
            .strength(1.0f)
            .dropsLike(ModBlocks.DURIAN_SIGN)
            .ignitedByLava()
    ));
    public static final Block MANGO_HANGING_SIGN = register("mango_hanging_sign",new TerraformHangingSignBlock(
            ThaiDelight.modid("entity/signs/hanging/mango"),
            ThaiDelight.modid("textures/gui/hanging_signs/mango"),
            BlockBehaviour.Properties.of()
            .mapColor(ModBlocks.MANGO_PLANKS.defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .noCollission()
            .strength(1.0f)
            .ignitedByLava()
    ));
    public static final Block MANGO_WALL_HANGING_SIGN = register("mango_wall_hanging_sign", new TerraformWallHangingSignBlock(
            ThaiDelight.modid("entity/signs/hanging/mango"),
            ThaiDelight.modid("textures/gui/hanging_signs/mango"),
            BlockBehaviour.Properties.of()
            .mapColor(ModBlocks.MANGO_PLANKS.defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .noCollission()
            .strength(1.0f)
            .ignitedByLava()
            .dropsLike(DURIAN_HANGING_SIGN)
    ));
    public static final Block MANGO_CABINET = register("mango_cabinet",new CabinetBlock(BlockBehaviour.Properties.copy(BARREL)));

    //Feast
    public static final Block SOMTAM_FEAST = register("somtam_feast",new FeastBlock(FabricBlockSettings.copyOf(Blocks.CAKE),() -> ModItems.SOMTAM,true){
        @Override
        public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
            final VoxelShape PLATE_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);
            final VoxelShape PIE_SHAPE = Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(2.0D, 2.0D, 2.0D, 14.0D, 7.0D, 14.0D), BooleanOp.OR);

            return (Integer)state.getValue(SERVINGS) == 0 ? PLATE_SHAPE : PIE_SHAPE;
        }
    }) ;
    public static final Block CRAB_FRIED_RICE_FEAST = register("crab_fried_rice_feast",new FeastBlock(FabricBlockSettings.copyOf(Blocks.CAKE),() ->ModItems.CRAB_FRIED_RICE,true){
        @Override
        public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
            final VoxelShape PLATE_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);
            final VoxelShape PIE_SHAPE = Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(2.0D, 2.0D, 2.0D, 14.0D, 7.0D, 14.0D), BooleanOp.OR);

            return (Integer)state.getValue(SERVINGS) == 0 ? PLATE_SHAPE : PIE_SHAPE;
        }
    });
    public static final Block LARB_FEAST = register("larb_feast",new FeastBlock(FabricBlockSettings.copyOf(Blocks.CAKE),() -> ModItems.LARB,true){
        @Override
        public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
            final VoxelShape PLATE_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D);
            final VoxelShape PIE_SHAPE = Shapes.joinUnoptimized(PLATE_SHAPE, Block.box(2.0D, 2.0D, 2.0D, 14.0D, 8.0D, 14.0D), BooleanOp.OR);

            return (Integer)state.getValue(SERVINGS) == 0 ? PLATE_SHAPE : PIE_SHAPE;
        }
    });

    //Papaya
    public static final Block PAPAYA_LOG = register("papaya_log",new PapayaLogBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.NETHER_WOOD).ignitedByLava()));
    public static final Block STRIPPED_PAPAYA_LOG = register("stripped_papaya_log",log(MapColor.COLOR_CYAN,MapColor.COLOR_CYAN));
    public static final Block PAPAYA_WOOD = register("papaya_wood",new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block STRIPPED_PAPAYA_WOOD = register("stripped_papaya_wood",new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block PAPAYA_LEAVES = register("papaya_leaves",leaves(SoundType.AZALEA_LEAVES));

    public static final Block PAPAYA = register("papaya",new PapayaBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().strength(0.2F, 3.0F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final Block PAPAYA_SAPLING = register("papaya_sapling",new SaplingBlock(new PapayaTreeGrower(), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final Block PAPAYA_CROP = register("papaya_crop",new PapayaCropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));

    //Cauldron
    public static final Block FERMENTED_FISH_CAULDRON = register("fermented_fish_cauldron",new FermentedFishCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON),ModCauldronInteraction.FERMENTED_FISH));

    //public static final Block SAUCE_BOWL = register("sauce_bowl",new SauceBowlBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN)));

    //public static final BlockEntityType<SauceBowlBlockEntity> SAUCE_BOWL_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,ThaiDelight.modid("sauce_bowl"), FabricBlockEntityTypeBuilder.create(SauceBowlBlockEntity::new,ModBlocks.SAUCE_BOWL).build());

    public static void init() {
        
    }

    public static Block register(String id, Block block){
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(ThaiDelight.MOD_ID,id), block);
    }
}
