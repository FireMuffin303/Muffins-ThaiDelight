package net.firemuffin303.muffinsthaidelightfabric.registry;


import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.block.*;
import net.firemuffin303.muffinsthaidelightfabric.common.block.butterfly_pea.BuddingButterflyPeaBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.butterfly_pea.ButterflyPeaVineBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.butterfly_pea.WallFlowerBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.cauldron.CoconutCauldron;
import net.firemuffin303.muffinsthaidelightfabric.common.block.cauldron.CoconutMilkCauldron;
import net.firemuffin303.muffinsthaidelightfabric.common.block.coconut.*;
import net.firemuffin303.muffinsthaidelightfabric.common.block.durian.*;
import net.firemuffin303.muffinsthaidelightfabric.common.block.feast.MangoStickyRiceFeastBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.lime.*;
import net.firemuffin303.muffinsthaidelightfabric.common.block.mango.HangingMangoBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.mango.MangoLeavesBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.mango.StackableMangoBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.papaya.*;
import net.firemuffin303.muffinsthaidelightfabric.common.block.pepper.BuddingPepperBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.pepper.PepperCropBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.world.trees.DurianTreeGrower;
import net.firemuffin303.muffinsthaidelightfabric.common.world.trees.MangoTreeGrower;
import net.firemuffin303.muffinsthaidelightfabric.common.world.trees.PapayaTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.block.WildCropBlock;

import java.util.ArrayList;

import static net.minecraft.world.level.block.Blocks.*;

public class ModBlocks {
    public static final ArrayList<Block> CRATES = new ArrayList<>();
    public static final ArrayList<Block> CABINET = new ArrayList<>();
    //Functional Block
    public static final Block MORTAR = register("mortar",new MortarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).strength(0.5f,6.0f).noOcclusion().sound(SoundType.DECORATED_POT)));
    public static final Block SACK = register("sack",new SackBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BANJO).strength(0.5F).sound(SoundType.GRASS)));

    //Crate
    public static final Block LIME_CRATE = registerCrate("lime_crate");
    public static final Block PEPPER_CRATE = registerCrate("pepper_crate");
    public static final Block RAW_PAPAYA_CRATE = registerCrate("raw_papaya_crate");
    public static final Block PAPAYA_CRATE = registerCrate("papaya_crate");
    public static final Block MANGO_CRATE = registerCrate("mango_crate");
    //public static final Block COCONUT_CRATE = registerCrate("coconut_crate");
    public static final Block HOLY_BASIL_CRATE = registerCrate("holy_basil_crate");
    public static final Block BASIL_CRATE = registerCrate("basil_crate");
    public static final Block BAMBOO_SHOOT_CRATE = registerCrate("bamboo_shoot_crate");
    public static final Block BUTTERFLY_PEA_CRATE = registerCrate("butterfly_pea_crate");

    //Eggs
    public static final Block CRAB_EGG = register("flower_crab_egg",new CrabEggBlock(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN)));

    //Wild Crops
    public static final Block WILD_PEPPER_CROP = register("wild_pepper",new WildCropBlock(MobEffects.CONFUSION,6,BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)));

    //# Crops
    //## Lime
    public static final Block LIME_PLANT = register("lime_plant",new LimePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().noCollission().sound(SoundType.AZALEA_LEAVES).ignitedByLava().pushReaction(PushReaction.DESTROY)));

    public static final Block LIME_SAPLING = register("lime_sapling",new LimeSaplingBlock(BlockBehaviour.Properties.copy(OAK_SAPLING)));
    public static final Block POTTED_LIME_SAPLING = register("potted_lime_sapling",Blocks.flowerPot(ModBlocks.LIME_SAPLING));
    public static final Block LIME_BLOCK = register("lime_block",new LimeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final Block PEPPER_CROP = register("pepper_crop",new PepperCropBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES)));
    public static final Block BUDDING_PEPPER_CROP = register("budding_pepper_crop",new BuddingPepperBlock(BlockBehaviour.Properties.copy(PEPPER_CROP)));

    //Durian
    public static final Block DURIAN_SAPLING = register("durian_sapling",new SaplingBlock(new DurianTreeGrower(), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final Block POTTED_DURIAN_SAPLING = register("potted_durian_sapling",Blocks.flowerPot(DURIAN_SAPLING));
    public static final Block DURIAN_FLOWER = register("durian_flower",new DurianFlowerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).instabreak().noCollission().sound(SoundType.SPORE_BLOSSOM).pushReaction(PushReaction.DESTROY).randomTicks()));

    public static final Block HANGING_DURIAN = register("hanging_durian",new HangingDurianBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY).randomTicks()));

    public static final Block SMALL_DURIAN_BLOCK = register("small_durian",new SmallDurianBlock(
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final Block DURIAN_BLOCK = register("durian",new DurianBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)));
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
    public static final Block DURIAN_FENCE_GATE = register("durian_fence_gate",new FenceGateBlock(BlockBehaviour.Properties.of()
            .mapColor(DURIAN_PLANKS.defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0f,3.0f)
            .ignitedByLava(), ModWoodTypes.DURIAN));
    public static final Block DURIAN_DOOR = register("durian_door",new DoorBlock(BlockBehaviour.Properties.of()
            .mapColor(DURIAN_PLANKS.defaultMapColor())
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0f)
            .noOcclusion()
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY), ModBlockSetTypes.DURIAN
    ));
    public static final Block DURIAN_TRAPDOOR = register("durian_trapdoor",new TrapDoorBlock(BlockBehaviour.Properties.of()
            .mapColor(DURIAN_PLANKS.defaultMapColor())
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0f)
            .noOcclusion()
            .isValidSpawn(Blocks::never)
            .ignitedByLava(),ModBlockSetTypes.DURIAN
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
                    .pushReaction(PushReaction.DESTROY),ModBlockSetTypes.DURIAN
    ));

    public static final Block DURIAN_BUTTON = register("durian_button",Blocks.woodenButton(ModBlockSetTypes.DURIAN));
    public static final Block DURIAN_SIGN = register("durian_sign",new TerraformSignBlock(
            ThaiDelight.modid("entity/signs/durian"),
            BlockBehaviour.Properties.of()
            .mapColor(ModBlocks.DURIAN_PLANKS.defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .noCollission()
            .sound(SoundType.WOOD)
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
            .sound(SoundType.WOOD)
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
            .sound(SoundType.WOOD)
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
            .sound(SoundType.WOOD)
            .strength(1.0f)
            .ignitedByLava()
            .dropsLike(DURIAN_HANGING_SIGN)
    ));

    public static final Block DURIAN_CABINET = registerCabinet("durian_cabinet");
    public static final Block DURIAN_PEEL_BLOCK = register("durian_peel_block",new Block(BlockBehaviour.Properties.copy(ModBlocks.DURIAN_PLANKS)));


    //----------------------Coconut
    public static final Block COCONUT = register("coconut",new CoconutBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final Block STRIPPED_COCONUT = register("stripped_coconut",new CoconutBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(1.0f).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final Block COCONUT_SAPLING_CROP = register("coconut_sapling_crop",new CoconutSaplingCropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));
    public static final Block COCONUT_SAPLING = register("coconut_sapling",new CoconutSaplingBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)){
        @Override
        public boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
            return super.mayPlaceOn(blockState, blockGetter, blockPos) || blockState.is(BlockTags.SAND);
        }
    });
    public static final Block POTTED_COCONUT_SAPLING = register("potted_coconut_sapling",Blocks.flowerPot(COCONUT_SAPLING));
    public static final Block COCONUT_LEAF = register("coconut_leaf",new CoconutLeafBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .strength(0.2F)
            .sound(SoundType.GRASS)
            .noOcclusion()
            .isValidSpawn(Blocks::ocelotOrParrot)
            .isSuffocating(Blocks::never)
            .isViewBlocking(Blocks::never)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(Blocks::never)));
    public static final Block COCONUT_LEAF_END = register("coconut_leaf_end",new CoconutLeafEndBlock(BlockBehaviour.Properties.copy(COCONUT_LEAF).dropsLike(ModBlocks.COCONUT_LEAF)));
    public static final Block BUDDING_COCONUT_LEAF = register("budding_coconut_leaf",new BuddingCoconutLeafBlock(BlockBehaviour.Properties.copy(COCONUT_LEAF).randomTicks()));

    public static final Block COCONUT_LEAF_BLOCK = register("coconut_leaf_block",new BundledCoconutLeafBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.5F, 2.5F).sound(SoundType.GRASS)));

    public static final Block COCONUT_LOG = register("coconut_log",Blocks.log(MapColor.COLOR_BROWN,MapColor.COLOR_YELLOW));
    public static final Block COCONUT_WOOD = register("coconut_wood",new RotatedPillarBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block STRIPPED_COCONUT_LOG = register("stripped_coconut_log",Blocks.log(MapColor.COLOR_RED,MapColor.COLOR_RED));
    public static final Block STRIPPED_COCONUT_WOOD = register("stripped_coconut_wood",new RotatedPillarBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
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
            ModWoodTypes.COCONUT
    ));
    public static final Block COCONUT_DOOR = register("coconut_door",new DoorBlock(BlockBehaviour.Properties.of()
            .mapColor(COCONUT_PLANKS.defaultMapColor())
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0f)
            .noOcclusion()
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY),
            ModBlockSetTypes.COCONUT
    ));
    public static final Block COCONUT_TRAPDOOR = register("coconut_trapdoor",new TrapDoorBlock(BlockBehaviour.Properties.of()
            .mapColor(COCONUT_PLANKS.defaultMapColor())
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0f)
            .noOcclusion()
            .isValidSpawn(Blocks::never)
            .ignitedByLava(),
            ModBlockSetTypes.COCONUT
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
            ModBlockSetTypes.COCONUT
    ));
    public static final Block COCONUT_BUTTON = register("coconut_button",Blocks.woodenButton(ModBlockSetTypes.COCONUT));
    public static final Block COCONUT_SIGN = register("coconut_sign",new TerraformSignBlock(
            ThaiDelight.modid("entity/signs/coconut"),
            BlockBehaviour.Properties.of()
            .mapColor(ModBlocks.COCONUT_PLANKS.defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .noCollission()
                    .sound(SoundType.WOOD)
            .strength(1.0f)
            .ignitedByLava()
    ));
    public static final Block COCONUT_WALL_SIGN = register("coconut_wall_sign",new TerraformWallSignBlock(
            ThaiDelight.modid("entity/signs/coconut"),
            BlockBehaviour.Properties.of()
            .mapColor(ModBlocks.COCONUT_PLANKS.defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .noCollission()
            .strength(1.0f)
            .sound(SoundType.WOOD)
            .dropsLike(ModBlocks.COCONUT_SIGN)
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
                    .sound(SoundType.WOOD)
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
                    .sound(SoundType.WOOD)
            .ignitedByLava()
            .dropsLike(COCONUT_HANGING_SIGN)
    ));
    public static final Block COCONUT_CABINET = registerCabinet("coconut_cabinet");
    public static final Block COCONUT_LEAF_CARPET = register("coconut_leaf_carpet",new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.1f).sound(SoundType.MOSS_CARPET).pushReaction(PushReaction.DESTROY)));

    //----------------- Mango -----------
    public static final Block MANGO_SAPLING = register("mango_sapling",new SaplingBlock(new MangoTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final Block POTTED_MANGO_SAPLING = register("potted_mango_sapling",Blocks.flowerPot(ModBlocks.MANGO_SAPLING));
    public static final Block MANGO_LEAVES = register("mango_leaves",new MangoLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final Block STACKABLE_MANGO_BLOCK = register("stackable_mango_block", new StackableMangoBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final Block HANGING_MANGO_BLOCK = register("mango_block",new HangingMangoBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .pushReaction(PushReaction.DESTROY)
                    .noOcclusion()
                    .instabreak()
                    .randomTicks()));

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
            ModWoodTypes.MANGO
    ));
    public static final Block MANGO_DOOR = register("mango_door",new DoorBlock(BlockBehaviour.Properties.of()
            .mapColor(MANGO_PLANKS.defaultMapColor())
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0f)
            .noOcclusion()
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY),
            ModBlockSetTypes.MANGO
    ));
    public static final Block MANGO_TRAPDOOR = register("mango_trapdoor",new TrapDoorBlock(BlockBehaviour.Properties.of()
            .mapColor(MANGO_PLANKS.defaultMapColor())
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0f)
            .noOcclusion()
            .isValidSpawn(Blocks::never)
            .ignitedByLava(),
            ModBlockSetTypes.MANGO
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
            ModBlockSetTypes.MANGO
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
                    .sound(SoundType.WOOD)
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
                    .sound(SoundType.WOOD)
            .dropsLike(ModBlocks.MANGO_SIGN)
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
                    .sound(SoundType.WOOD)
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
                    .sound(SoundType.WOOD)
            .ignitedByLava()
            .dropsLike(MANGO_HANGING_SIGN)
    ));
    public static final Block MANGO_CABINET = registerCabinet("mango_cabinet");

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
    public static final Block PAPAYA_LEAVES = register("papaya_leaves",new PapayaLeavesBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .strength(0.2F)
            .sound(SoundType.AZALEA_LEAVES)
            .noOcclusion()
            .isValidSpawn(Blocks::ocelotOrParrot)
            .isSuffocating(Blocks::never)
            .isViewBlocking(Blocks::never)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(Blocks::never)));

    public static final Block PAPAYA_LEAVES_STEM = register("papaya_leaves_stem",new PapayaLeavesStemBlock(BlockBehaviour.Properties.copy(PAPAYA_LEAVES)));

    public static final Block WALL_PAPAYA_LEAVES = register("wall_papaya_leaves",new WallPapayaLeavesBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .strength(0.2F)
            .sound(SoundType.AZALEA_LEAVES)
            .noOcclusion()
            .isValidSpawn(Blocks::ocelotOrParrot)
            .isSuffocating(Blocks::never)
            .isViewBlocking(Blocks::never)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(Blocks::never)));

    public static final Block BUDDING_PAPAYA_FLOWER = register("budding_papaya_flower",new BuddingPapayaFlowerBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .sound(SoundType.SPORE_BLOSSOM)
            .instabreak()
            .noCollission()
            .randomTicks()
            .pushReaction(PushReaction.DESTROY)));
    public static final Block PAPAYA = register("papaya",new PapayaBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().strength(0.2F, 3.0F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final Block PAPAYA_FLOWER = register("papaya_flower",new PapayaFlowerBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .noCollission()
            .instabreak()
            .sound(SoundType.SPORE_BLOSSOM)
            .pushReaction(PushReaction.DESTROY)
            .emissiveRendering((blockState, blockGetter, blockPos) -> blockState.getValue(PapayaFlowerBlock.LIT))
            .lightLevel(blockstate -> blockstate.getValue(PapayaFlowerBlock.LIT) ? 1 : 0)
    ));
    public static final Block WALL_PAPAYA_FLOWER = register("wall_papaya_flower",new WallPapayaFlowerBlock(BlockBehaviour.Properties.copy(PAPAYA_FLOWER)));

    public static final Block PAPAYA_SAPLING = register("papaya_sapling",new SaplingBlock(new PapayaTreeGrower(), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final Block PAPAYA_CROP = register("papaya_crop",new PapayaCropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));

    public static final Block STACKABLE_PAPAYA = register("stackable_papaya",new StackablePapayaBlock(BlockBehaviour.Properties.copy(ModBlocks.LIME_BLOCK),() -> ModItems.PAPAYA));
    public static final Block STACKABLE_RAW_PAPAYA = register("stackable_raw_papaya",new StackablePapayaBlock(BlockBehaviour.Properties.copy(ModBlocks.LIME_BLOCK),() -> ModItems.RAW_PAPAYA));

    //Cauldron
    public static final Block FERMENTED_FISH_CAULDRON = register("fermented_fish_cauldron",new FermentedFishCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON),ModCauldronInteraction.FERMENTED_FISH));
    public static final Block COCONUT_CAULDRON = register("coconut_cauldron",new CoconutCauldron(BlockBehaviour.Properties.copy(CAULDRON)));
    public static final Block COCONUT_MILK_CAULDRON = register("coconut_milk_cauldron",new CoconutMilkCauldron(BlockBehaviour.Properties.copy(CAULDRON)));


    // 🌿 BASIL 🌿
    public static final Block BASIL = register("basil",new BasilCropBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES),ModLootTables.BASIL_HARVEST,ModLootTables.BASIL_SHEARS){
        @Override
        protected ItemLike getBaseSeedId() {
            return ModItems.BASIL;
        }
    });
    public static final Block WILD_BASIL = register("wild_basil",new WildCropBlock(MobEffects.HUNGER,6,BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)));
    public static final Block POTTED_BASIL = register("potted_basil",Blocks.flowerPot(ModBlocks.WILD_BASIL));

    public static final Block HOLY_BASIL = register("holy_basil",new BasilCropBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES),ModLootTables.HOLY_BASIL_HARVEST,ModLootTables.HOLY_BASIL_SHEARS){
        @Override
        protected ItemLike getBaseSeedId() {
            return ModItems.HOLY_BASIL;
        }
    });
    public static final Block WILD_HOLY_BASIL = register("wild_holy_basil",new WildCropBlock(MobEffects.HUNGER,6,BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)));
    public static final Block POTTED_HOLY_BASIL = register("potted_holy_basil",Blocks.flowerPot(ModBlocks.WILD_HOLY_BASIL));


    public static final Block BUDDING_BUTTERFLY_PEA_BLOCK = register("budding_butterfly_pea",new BuddingButterflyPeaBlock(BlockBehaviour.Properties.copy(WHEAT)));
    public static final Block BUTTERFLY_PEA_BLOCK = register("butterfly_pea_vine",new ButterflyPeaVineBlock(BlockBehaviour.Properties.copy(WHEAT)));
    public static final Block BUTTERFLY_PEA_WALL = register("butterfly_pea_wall",new WallFlowerBlock(BlockBehaviour.Properties.copy(SPORE_BLOSSOM),MobEffects.HEAL,1));

    public static final Block DURIAN_CAKE = register("durian_cake",new DurianCakeBlock(BlockBehaviour.Properties.copy(CAKE)));
    public static final Block CANDLE_DURIAN_CAKE = register("candle_durian_cake",new CandleDurianCakeBlock(CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Block WHITE_CANDLE_DURIAN_CAKE = register("white_candle_durian_cake",new CandleDurianCakeBlock(WHITE_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Block ORANGE_CANDLE_DURIAN_CAKE = register("orange_candle_durian_cake",new CandleDurianCakeBlock(ORANGE_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Block MAGENTA_CANDLE_DURIAN_CAKE = register("magenta_candle_durian_cake",new CandleDurianCakeBlock(MAGENTA_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Block LIGHT_BLUE_CANDLE_DURIAN_CAKE = register("light_blue_candle_durian_cake",new CandleDurianCakeBlock(LIGHT_BLUE_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Block YELLOW_CANDLE_DURIAN_CAKE = register("yellow_candle_durian_cake",new CandleDurianCakeBlock(YELLOW_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Block LIME_CANDLE_DURIAN_CAKE = register("lime_candle_durian_cake",new CandleDurianCakeBlock(LIME_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Block PINK_CANDLE_DURIAN_CAKE = register("pink_candle_durian_cake",new CandleDurianCakeBlock(PINK_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Block GRAY_CANDLE_DURIAN_CAKE = register("gray_candle_durian_cake",new CandleDurianCakeBlock(GRAY_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Block LIGHT_GRAY_CANDLE_DURIAN_CAKE = register("light_gray_candle_durian_cake",new CandleDurianCakeBlock(LIGHT_GRAY_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Block CYAN_CANDLE_DURIAN_CAKE = register("cyan_candle_durian_cake",new CandleDurianCakeBlock(CYAN_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Block PURPLE_CANDLE_DURIAN_CAKE = register("purple_candle_durian_cake",new CandleDurianCakeBlock(PURPLE_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Block BLUE_CANDLE_DURIAN_CAKE = register("blue_candle_durian_cake",new CandleDurianCakeBlock(BLUE_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Block BROWN_CANDLE_DURIAN_CAKE = register("brown_candle_durian_cake",new CandleDurianCakeBlock(BROWN_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Block GREEN_CANDLE_DURIAN_CAKE = register("green_candle_durian_cake",new CandleDurianCakeBlock(GREEN_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Block RED_CANDLE_DURIAN_CAKE = register("red_candle_durian_cake",new CandleDurianCakeBlock(RED_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Block BLACK_CANDLE_DURIAN_CAKE = register("black_candle_durian_cake",new CandleDurianCakeBlock(BLACK_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));


    public static final Block MANGO_CHEESECAKE = register("mango_cheesecake",new PieBlock(BlockBehaviour.Properties.copy(CAKE),() -> ModItems.MANGO_CHEESECAKE_SLICE));
    public static final Block COCONUT_PIE = register("coconut_pie",new PieBlock(BlockBehaviour.Properties.copy(CAKE),() -> ModItems.COCONUT_PIE_SLICE){
        @Override
        public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
            ItemStack itemStack = player.getItemInHand(hand);
            if(level.isClientSide){
                if(itemStack.is(Items.HONEY_BOTTLE)){
                    return InteractionResult.SUCCESS;
                }
            }

            if(itemStack.is(Items.HONEY_BOTTLE)){
                level.setBlock(pos,ModBlocks.HONEY_COCONUT_PIE.defaultBlockState().setValue(BITES,state.getValue(BITES)).setValue(FACING,state.getValue(FACING)), 3);
                level.playSound(null, pos, SoundEvents.HONEY_BLOCK_PLACE, SoundSource.PLAYERS, 0.8F, 0.8F);
                player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
                player.setItemInHand(hand, ItemUtils.createFilledResult(itemStack,player,new ItemStack(Items.GLASS_BOTTLE)));

                return InteractionResult.SUCCESS;
            }

            return super.use(state, level, pos, player, hand, hit);
        }
    });
    public static final Block HONEY_COCONUT_PIE = register("honey_coconut_pie",new PieBlock(BlockBehaviour.Properties.copy(CAKE), () -> ModItems.HONEY_COCONUT_PIE_SLICE));

    public static final Block PHAT_KAPHRAO_FEAST = register("phat_kaphrao_feast",new FeastBlock(BlockBehaviour.Properties.copy(CAKE),() -> ModItems.PHAT_KAPHRAO,false));
    public static final Block MANGO_STICKY_RICE_FEAST = register("mango_sticky_rice_feast",new MangoStickyRiceFeastBlock(BlockBehaviour.Properties.copy(CAKE)));
    public static final Block OMELETTE_FEAST = register("omelette",new FeastBlock(BlockBehaviour.Properties.copy(CAKE),() -> ModItems.OMELETTE,false));
    public static final Block BASIL_OMELETTE_FEAST = register("basil_omelette",new FeastBlock(BlockBehaviour.Properties.copy(CAKE),() -> ModItems.BASIL_OMELETTE,false));
    public static final Block PINEAPPLE_FRIED_RICE_FEAST = register("pineapple_fried_rice_feast",new FeastBlock(BlockBehaviour.Properties.copy(CAKE),() -> ModItems.PINEAPPLE_FRIED_RICE,true));


    //public static final Block SAUCE_BOWL = register("sauce_bowl",new SauceBowlBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN)));

    //public static final BlockEntityType<SauceBowlBlockEntity> SAUCE_BOWL_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,ThaiDelight.modid("sauce_bowl"), FabricBlockEntityTypeBuilder.create(SauceBowlBlockEntity::new,ModBlocks.SAUCE_BOWL).build());

    public static void init() {
    }

    public static Block registerCabinet(String id){
        Block registeredBlock = register(id,new CabinetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.5f).sound(SoundType.WOOD).ignitedByLava()));
        CABINET.add(registeredBlock);
        return registeredBlock;
    }

    public static Block registerCrate(String id){
        Block blockSupplier = register(id,new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).destroyTime(2.0f).explosionResistance(3.0f).sound(SoundType.WOOD)));
        CRATES.add(blockSupplier);
        return blockSupplier;
    }

    public static Block register(String id, Block block){
        return Registry.register(BuiltInRegistries.BLOCK, ThaiDelight.modid(id), block);
    }

}
