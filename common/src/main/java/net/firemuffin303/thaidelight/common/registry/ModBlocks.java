package net.firemuffin303.thaidelight.common.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.firemuffin303.muffinsmcapi.api.block.sign.OvenCeilingHangingSignBlock;
import net.firemuffin303.muffinsmcapi.api.block.sign.OvenStandSignBlock;
import net.firemuffin303.muffinsmcapi.api.block.sign.OvenWallHangingSignBlock;
import net.firemuffin303.muffinsmcapi.api.block.sign.OvenWallSignBlock;
import net.firemuffin303.muffinsmcapi.impl.registration.ResourceRegistry;
import net.firemuffin303.thaidelight.ThaiDelightCommon;
import net.firemuffin303.thaidelight.common.block.animals.CrabEggBlock;
import net.firemuffin303.thaidelight.common.block.cauldron.FermentedFishCauldronBlock;
import net.firemuffin303.thaidelight.common.block.SackBlock;
import net.firemuffin303.thaidelight.common.block.util.ModSaplingBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.butterfly_pea.ButterflyPeaVineBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.butterfly_pea.WallFlowerBlock;
import net.firemuffin303.thaidelight.common.block.cauldron.CoconutCauldron;
import net.firemuffin303.thaidelight.common.block.cauldron.CoconutMilkCauldron;
import net.firemuffin303.thaidelight.common.block.vegetations.basil.BasilCropBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.lime.LimeBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.lime.LimePlantBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.lime.LimeSaplingBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.mango.HangingMangoBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.mango.MangoLeavesBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.mango.StackableMangoBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.papaya.*;
import net.firemuffin303.thaidelight.common.block.vegetations.pepper.PepperCropBlock;
import net.firemuffin303.thaidelight.common.block.stations.MortarBlock;
import net.firemuffin303.thaidelight.common.block.util.ModDoorBlock;
import net.firemuffin303.thaidelight.common.block.util.ModPressurePlateBlock;
import net.firemuffin303.thaidelight.common.block.util.ModStairBlock;
import net.firemuffin303.thaidelight.common.block.util.ModTrapDoorBlock;
import net.firemuffin303.thaidelight.common.block.vegetations.coconut.*;
import net.firemuffin303.thaidelight.common.block.vegetations.durian.*;
import net.firemuffin303.thaidelight.common.world.trees.DurianTreeGrower;
import net.firemuffin303.thaidelight.common.world.trees.MangoTreeGrower;
import net.firemuffin303.thaidelight.common.world.trees.PapayaTreeGrower;
import net.firemuffin303.thaidelight.mixin.accessor.block.BlocksAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.ArrayList;
import java.util.function.Supplier;

import static net.firemuffin303.thaidelight.mixin.accessor.block.BlocksAccessor.log;
import static net.minecraft.world.level.block.Blocks.*;

public class ModBlocks {
    public static final ResourceRegistry<Block> BLOCK = ResourceRegistry.create(Registries.BLOCK,ThaiDelightCommon.MOD_ID);

    public static final ArrayList<Supplier<Block>> CRATES = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> CABINET = new ArrayList<>();

    //Functional Block
    public static final Supplier<Block> MORTAR = register("mortar",() -> new MortarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).strength(0.5f,6.0f).noOcclusion().sound(SoundType.DECORATED_POT)));
    public static final Supplier<Block> SACK = register("sack",() -> new SackBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .instrument(NoteBlockInstrument.BANJO)
                    .strength(0.5F).sound(SoundType.GRASS)
    ));

    //Crate
    public static final Supplier<Block> LIME_CRATE = registerCrate("lime_crate");
    public static final Supplier<Block> PEPPER_CRATE = registerCrate("pepper_crate");
    public static final Supplier<Block> RAW_PAPAYA_CRATE = registerCrate("raw_papaya_crate");
    public static final Supplier<Block> PAPAYA_CRATE = registerCrate("papaya_crate");
    public static final Supplier<Block> MANGO_CRATE = registerCrate("mango_crate");
    public static final Supplier<Block> HOLY_BASIL_CRATE = registerCrate("holy_basil_crate");
    public static final Supplier<Block> BASIL_CRATE = registerCrate("basil_crate");
    public static final Supplier<Block> BAMBOO_SHOOT_CRATE = registerCrate("bamboo_shoot_crate");
    public static final Supplier<Block> BUTTERFLY_PEA_CRATE = registerCrate("butterfly_pea_crate");

    //Eggs
    public static final Supplier<Block> CRAB_EGG = register("flower_crab_egg",() ->  new CrabEggBlock(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN)));

    //Wild Crops
    public static final Supplier<Block> WILD_PEPPER_CROP = register("wild_pepper", createWildCropBlock(MobEffects.CONFUSION,6,BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)));
    public static final Supplier<Block> WILD_BASIL = register("wild_basil",createWildCropBlock(MobEffects.HUNGER,6,BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)));
    public static final Supplier<Block> POTTED_BASIL = register("potted_basil",() -> BlocksAccessor.flowerPot(ModBlocks.WILD_BASIL.get()));
    public static final Supplier<Block> WILD_HOLY_BASIL = register("wild_holy_basil",createWildCropBlock(MobEffects.HUNGER,6,BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)));
    public static final Supplier<Block> POTTED_HOLY_BASIL = register("potted_holy_basil",() -> BlocksAccessor.flowerPot(ModBlocks.WILD_HOLY_BASIL.get()));



    //# Crops
    //## Lime
    public static final Supplier<Block> LIME_PLANT = register("lime_plant",() -> new LimePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().noCollission().sound(SoundType.AZALEA_LEAVES).ignitedByLava().pushReaction(PushReaction.DESTROY)));

    public static final Supplier<Block> LIME_SAPLING = register("lime_sapling",() -> new LimeSaplingBlock(BlockBehaviour.Properties.copy(OAK_SAPLING)));
    public static final Supplier<Block> POTTED_LIME_SAPLING = register("potted_lime_sapling",() ->  BlocksAccessor.flowerPot(ModBlocks.LIME_SAPLING.get()));
    public static final Supplier<Block> LIME_BLOCK = register("lime_block",() -> new LimeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final Supplier<Block> PEPPER_CROP = register("pepper_crop",() -> new PepperCropBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES)));
    public static final Supplier<Block> BUDDING_PEPPER_CROP = register("budding_pepper_crop",createBuddingPepperBlock(BlockBehaviour.Properties.copy(POTATOES)));

    //Durian
    public static final Supplier<Block> DURIAN_SAPLING = register("durian_sapling",() ->  new ModSaplingBlock(new DurianTreeGrower(), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> POTTED_DURIAN_SAPLING = register("potted_durian_sapling",() -> BlocksAccessor.flowerPot(DURIAN_SAPLING.get()));
    public static final Supplier<Block> DURIAN_FLOWER = register("durian_flower",() -> new DurianFlowerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).instabreak().noCollission().sound(SoundType.SPORE_BLOSSOM).pushReaction(PushReaction.DESTROY).randomTicks()));

    public static final Supplier<Block> HANGING_DURIAN = register("hanging_durian",() -> new HangingDurianBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY).randomTicks()));

    public static final Supplier<Block> SMALL_DURIAN_BLOCK = register("small_durian",() -> new SmallDurianBlock(
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> DURIAN_BLOCK = register("durian",() -> new DurianBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> DURIAN_LEAVES = register("durian_leaves",() -> new DurianLeaveBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    //Durian Woodset
    public static final Supplier<Block> DURIAN_LOG = register("durian_log",() ->  log(MapColor.COLOR_LIGHT_GREEN, MapColor.COLOR_RED));
    public static final Supplier<Block> DURIAN_WOOD = register("durian_wood",() -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Supplier<Block> STRIPPED_DURIAN_LOG = register("stripped_durian_log", () ->  log(MapColor.COLOR_RED,MapColor.COLOR_RED));
    public static final Supplier<Block> STRIPPED_DURIAN_WOOD = register("stripped_durian_wood",() -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Supplier<Block> DURIAN_PLANKS = register("durian_planks",() -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_GREEN).instrument(NoteBlockInstrument.BASS).strength(2.0f,3.0f).sound(SoundType.WOOD).ignitedByLava()));
    public static final Supplier<Block> DURIAN_STAIRS = register("durian_stairs",() ->  new ModStairBlock(DURIAN_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(DURIAN_PLANKS.get())));
    public static final Supplier<Block> DURIAN_SLAB = register("durian_slab",() -> new SlabBlock(BlockBehaviour.Properties.copy(DURIAN_PLANKS.get())));
    public static final Supplier<Block> DURIAN_FENCE = register("durian_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(DURIAN_PLANKS.get())));
    public static final Supplier<Block> DURIAN_FENCE_GATE = register("durian_fence_gate",() -> new FenceGateBlock(BlockBehaviour.Properties.of()
            .mapColor(DURIAN_PLANKS.get().defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0f,3.0f)
            .ignitedByLava(), ModBlockSetTypes.DURIAN_WOOD_TYPE));
    public static final Supplier<Block> DURIAN_DOOR = register("durian_door",() -> new ModDoorBlock(BlockBehaviour.Properties.of()
            .mapColor(DURIAN_PLANKS.get().defaultMapColor())
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0f)
            .noOcclusion()
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY), ModBlockSetTypes.DURIAN_BLOCK_SET
    ));
    public static final Supplier<Block> DURIAN_TRAPDOOR = register("durian_trapdoor",() -> new ModTrapDoorBlock(BlockBehaviour.Properties.of()
            .mapColor(DURIAN_PLANKS.get().defaultMapColor())
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0f)
            .noOcclusion()
            .isValidSpawn((a,b,c,d) -> false)
            .ignitedByLava(),ModBlockSetTypes.DURIAN_BLOCK_SET
    ));

    public static final Supplier<Block> DURIAN_PRESSURE_PLATE = register("durian_pressure_plate",() -> new ModPressurePlateBlock(
            PressurePlateBlock.Sensitivity.EVERYTHING,
            BlockBehaviour.Properties.of()
                    .mapColor(DURIAN_PLANKS.get().defaultMapColor())
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(0.5f)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY),ModBlockSetTypes.DURIAN_BLOCK_SET
    ));

    public static final Supplier<Block> DURIAN_BUTTON = register("durian_button",() -> BlocksAccessor.woodenButton(ModBlockSetTypes.DURIAN_BLOCK_SET));
    public static final Supplier<Block> DURIAN_SIGN = register("durian_sign", () -> new OvenStandSignBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .sound(SoundType.WOOD)
                    .strength(1.0f)
                    .ignitedByLava(),
            "durian"
    ));

    public static final Supplier<Block> DURIAN_WALL_SIGN = register("durian_wall_sign",() -> new OvenWallSignBlock(
            dropLike(ModBlocks.DURIAN_SIGN)
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .sound(SoundType.WOOD)
                    .strength(1.0f)
                    .ignitedByLava(),
            "durian"
    ));

    public static final Supplier<Block> DURIAN_HANGING_SIGN = register("durian_hanging_sign",() -> new OvenCeilingHangingSignBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .sound(SoundType.WOOD)
                    .strength(1.0f)
                    .ignitedByLava(),
            "durian"
    ));

    public static final Supplier<Block> DURIAN_WALL_HANGING_SIGN = register("durian_wall_hanging_sign",() -> new OvenWallHangingSignBlock(
            dropLike(ModBlocks.DURIAN_HANGING_SIGN).mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .sound(SoundType.WOOD)
                    .strength(1.0f)
                    .ignitedByLava(),
            "durian"
    ));

    public static final Supplier<Block> DURIAN_CABINET = registerCabinet("durian_cabinet");
    public static final Supplier<Block> DURIAN_PEEL_BLOCK = register("durian_peel_block",() ->new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_GREEN).instrument(NoteBlockInstrument.BASS).strength(2.0f,3.0f).sound(SoundType.WOOD).ignitedByLava()));


    //----------------------Coconut
    public static final Supplier<Block> COCONUT = register("coconut",() ->new CoconutBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> STRIPPED_COCONUT = register("stripped_coconut",() ->new CoconutBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(1.0f).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> COCONUT_SAPLING_CROP = register("coconut_sapling_crop",() ->new CoconutSaplingCropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> COCONUT_SAPLING = register("coconut_sapling",() ->new CoconutSaplingBlock(BlockBehaviour.Properties.copy(OAK_SAPLING)){
        @Override
        public boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
            return super.mayPlaceOn(blockState, blockGetter, blockPos) || blockState.is(BlockTags.SAND);
        }
    });
    public static final Supplier<Block> POTTED_COCONUT_SAPLING = register("potted_coconut_sapling",() ->BlocksAccessor.flowerPot(COCONUT_SAPLING.get()));
    public static final Supplier<Block> COCONUT_LEAF = register("coconut_leaf",() ->new CoconutLeafBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .strength(0.2F)
            .sound(SoundType.GRASS)
            .noOcclusion()
            .isValidSpawn(BlocksAccessor::ocelotOrParrot)
            .isSuffocating((b,a,c) -> false)
            .isViewBlocking((b,a,c) -> false)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor((b,a,c) -> false)));
    public static final Supplier<Block> COCONUT_LEAF_END = register("coconut_leaf_end",() ->new CoconutLeafEndBlock(dropLike(ModBlocks.COCONUT_LEAF)
            .mapColor(MapColor.PLANT)
            .strength(0.2F)
            .sound(SoundType.GRASS)
            .noOcclusion()
            .isValidSpawn(BlocksAccessor::ocelotOrParrot)
            .isSuffocating((b,a,c) -> false)
            .isViewBlocking((b,a,c) -> false)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor((b,a,c) -> false)));
    public static final Supplier<Block> BUDDING_COCONUT_LEAF = register("budding_coconut_leaf",() ->new BuddingCoconutLeafBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .strength(0.2F)
            .sound(SoundType.GRASS)
            .noOcclusion()
            .isValidSpawn(BlocksAccessor::ocelotOrParrot)
            .isSuffocating((b,a,c) -> false)
            .isViewBlocking((b,a,c) -> false)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor((b,a,c) -> false)
            .randomTicks()));

    public static final Supplier<Block> COCONUT_LEAF_BLOCK = register("coconut_leaf_block",() ->new BundledCoconutLeafBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.5F, 2.5F).sound(SoundType.GRASS)));

    public static final Supplier<Block> COCONUT_LOG = register("coconut_log",() -> log(MapColor.COLOR_BROWN,MapColor.COLOR_YELLOW));
    public static final Supplier<Block> COCONUT_WOOD = register("coconut_wood",() ->new RotatedPillarBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Supplier<Block> STRIPPED_COCONUT_LOG = register("stripped_coconut_log", () ->log(MapColor.COLOR_RED,MapColor.COLOR_RED));
    public static final Supplier<Block> STRIPPED_COCONUT_WOOD = register("stripped_coconut_wood",() ->new RotatedPillarBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Supplier<Block> COCONUT_PLANKS = register("coconut_planks",() ->new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_GREEN).instrument(NoteBlockInstrument.BASS).strength(2.0f,3.0f).sound(SoundType.WOOD).ignitedByLava()));
    public static final Supplier<Block> COCONUT_STAIRS = register("coconut_stairs",() ->new ModStairBlock(COCONUT_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(COCONUT_PLANKS.get())));
    public static final Supplier<Block> COCONUT_SLAB = register("coconut_slab",() ->new SlabBlock(BlockBehaviour.Properties.copy(COCONUT_PLANKS.get())));
    public static final Supplier<Block> COCONUT_FENCE = register("coconut_fence",() ->new FenceBlock(BlockBehaviour.Properties.copy(COCONUT_PLANKS.get())));
    public static final Supplier<Block> COCONUT_FENCE_GATE = register("coconut_fence_gate",() ->new FenceGateBlock(BlockBehaviour.Properties.of()
            .mapColor(COCONUT_PLANKS.get().defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0f,3.0f)
            .ignitedByLava(),
            ModBlockSetTypes.COCONUT_WOOD_TYPE
    ));
    public static final Supplier<Block> COCONUT_DOOR = register("coconut_door",() ->new ModDoorBlock(BlockBehaviour.Properties.of()
            .mapColor(COCONUT_PLANKS.get().defaultMapColor())
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0f)
            .noOcclusion()
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY),
            ModBlockSetTypes.COCONUT_BLOCK_SET
    ));
    public static final Supplier<Block> COCONUT_TRAPDOOR = register("coconut_trapdoor",() ->new ModTrapDoorBlock(BlockBehaviour.Properties.of()
            .mapColor(COCONUT_PLANKS.get().defaultMapColor())
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0f)
            .noOcclusion()
            .isValidSpawn(BlocksAccessor::never)
            .ignitedByLava(),
            ModBlockSetTypes.COCONUT_BLOCK_SET
    ));
    public static final Supplier<Block> COCONUT_PRESSURE_PLATE = register("coconut_pressure_plate",() ->new ModPressurePlateBlock(
            PressurePlateBlock.Sensitivity.EVERYTHING,
            BlockBehaviour.Properties.of()
                    .mapColor(COCONUT_PLANKS.get().defaultMapColor())
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(0.5f)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY),
            ModBlockSetTypes.COCONUT_BLOCK_SET
    ));
    public static final Supplier<Block> COCONUT_BUTTON = register("coconut_button",() ->BlocksAccessor.woodenButton(ModBlockSetTypes.COCONUT_BLOCK_SET));
    public static final Supplier<Block> COCONUT_SIGN = register("coconut_sign", () -> new OvenStandSignBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .sound(SoundType.WOOD)
                    .strength(1.0f)
                    .ignitedByLava(),
            "coconut"
    ));
    public static final Supplier<Block> COCONUT_WALL_SIGN = register("coconut_wall_sign",() -> new OvenWallSignBlock(
            dropLike(ModBlocks.COCONUT_SIGN).mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0f)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            "coconut"
    ));
    public static final Supplier<Block> COCONUT_HANGING_SIGN = register("coconut_hanging_sign", () -> new OvenCeilingHangingSignBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0f)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            "coconut"
    ));
    public static final Supplier<Block> COCONUT_WALL_HANGING_SIGN = register("coconut_wall_hanging_sign",() -> new OvenWallHangingSignBlock(
            dropLike(ModBlocks.COCONUT_HANGING_SIGN)
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0f)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            "coconut"
    ));
    public static final Supplier<Block> COCONUT_CABINET = registerCabinet("coconut_cabinet");
    public static final Supplier<Block> COCONUT_LEAF_CARPET = register("coconut_leaf_carpet",() ->new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.1f).sound(SoundType.MOSS_CARPET).pushReaction(PushReaction.DESTROY)));

    //----------------- Mango -----------
    public static final Supplier<Block> MANGO_SAPLING = register("mango_sapling",() ->new ModSaplingBlock(new MangoTreeGrower(),BlockBehaviour.Properties.copy(OAK_SAPLING)));
    public static final Supplier<Block> POTTED_MANGO_SAPLING = register("potted_mango_sapling",() ->BlocksAccessor.flowerPot(ModBlocks.MANGO_SAPLING.get()));
    public static final Supplier<Block> MANGO_LEAVES = register("mango_leaves",() ->new MangoLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final Supplier<Block> STACKABLE_MANGO_BLOCK = register("stackable_mango_block",() -> new StackableMangoBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> HANGING_MANGO_BLOCK = register("mango_block",() ->new HangingMangoBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .pushReaction(PushReaction.DESTROY)
                    .noOcclusion()
                    .instabreak()
                    .randomTicks()));

    public static final Supplier<Block> MANGO_LOG = register("mango_log",() -> log(MapColor.COLOR_BROWN,MapColor.COLOR_YELLOW));
    public static final Supplier<Block> MANGO_WOOD = register("mango_wood",() ->new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Supplier<Block> STRIPPED_MANGO_LOG = register("stripped_mango_log",() -> log(MapColor.COLOR_YELLOW,MapColor.COLOR_YELLOW));
    public static final Supplier<Block> STRIPPED_MANGO_WOOD = register("stripped_mango_wood",() -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Supplier<Block> MANGO_PLANKS = register("mango_planks",() -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_LIGHT_GREEN).instrument(NoteBlockInstrument.BASS).strength(2.0f,3.0f).sound(SoundType.WOOD).ignitedByLava()));
    public static final Supplier<Block> MANGO_STAIRS = register("mango_stairs",() ->new ModStairBlock(MANGO_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(MANGO_PLANKS.get())));
    public static final Supplier<Block> MANGO_SLAB = register("mango_slab",() ->new SlabBlock(BlockBehaviour.Properties.copy(MANGO_PLANKS.get())));
    public static final Supplier<Block> MANGO_FENCE = register("mango_fence",() ->new FenceBlock(BlockBehaviour.Properties.copy(MANGO_PLANKS.get())));
    public static final Supplier<Block> MANGO_FENCE_GATE = register("mango_fence_gate",() ->new FenceGateBlock(BlockBehaviour.Properties.of()
            .mapColor(MANGO_PLANKS.get().defaultMapColor())
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0f,3.0f)
            .ignitedByLava(),
            ModBlockSetTypes.MANGO_WOOD_TYPE
    ));
    public static final Supplier<Block> MANGO_DOOR = register("mango_door",() ->new ModDoorBlock(BlockBehaviour.Properties.of()
            .mapColor(MANGO_PLANKS.get().defaultMapColor())
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0f)
            .noOcclusion()
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY),
            ModBlockSetTypes.MANGO_BLOCK_SET
    ));
    public static final Supplier<Block> MANGO_TRAPDOOR = register("mango_trapdoor",() ->new ModTrapDoorBlock(BlockBehaviour.Properties.of()
            .mapColor(MANGO_PLANKS.get().defaultMapColor())
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0f)
            .noOcclusion()
            .isValidSpawn(BlocksAccessor::never)
            .ignitedByLava(),
            ModBlockSetTypes.MANGO_BLOCK_SET
    ));
    public static final Supplier<Block> MANGO_PRESSURE_PLATE = register("mango_pressure_plate",() ->new ModPressurePlateBlock(
            PressurePlateBlock.Sensitivity.EVERYTHING,
            BlockBehaviour.Properties.of()
                    .mapColor(MANGO_PLANKS.get().defaultMapColor())
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(0.5f)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY),
            ModBlockSetTypes.MANGO_BLOCK_SET
    ));
    public static final Supplier<Block> MANGO_BUTTON = register("mango_button",() ->BlocksAccessor.woodenButton(ModBlockSetTypes.MANGO_BLOCK_SET));
    public static final Supplier<Block> MANGO_SIGN = register("mango_sign",() -> new OvenStandSignBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0f)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            "mango"
    ));
    public static final Supplier<Block> MANGO_WALL_SIGN = register("mango_wall_sign",() -> new OvenWallSignBlock(
            dropLike(ModBlocks.MANGO_SIGN)
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0f)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            "mango"
    ));
    public static final Supplier<Block> MANGO_HANGING_SIGN = register("mango_hanging_sign",() -> new OvenCeilingHangingSignBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0f)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            "mango"
    ));
    public static final Supplier<Block> MANGO_WALL_HANGING_SIGN = register("mango_wall_hanging_sign", () -> new OvenWallHangingSignBlock(
            dropLike(ModBlocks.MANGO_HANGING_SIGN)
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0f)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            "mango"
    ));
    public static final Supplier<Block> MANGO_CABINET = registerCabinet("mango_cabinet");



    //Papaya
    public static final Supplier<Block> PAPAYA_LOG = register("papaya_log",() ->new PapayaLogBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.NETHER_WOOD).ignitedByLava()));
    public static final Supplier<Block> STRIPPED_PAPAYA_LOG = register("stripped_papaya_log",() ->log(MapColor.COLOR_CYAN,MapColor.COLOR_CYAN));
    public static final Supplier<Block> PAPAYA_WOOD = register("papaya_wood",() ->new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Supplier<Block> STRIPPED_PAPAYA_WOOD = register("stripped_papaya_wood",() ->new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Supplier<Block> PAPAYA_LEAVES = register("papaya_leaves",() ->new PapayaLeavesBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .strength(0.2F)
            .sound(SoundType.AZALEA_LEAVES)
            .noOcclusion()
            .isValidSpawn(BlocksAccessor::ocelotOrParrot)
            .isSuffocating((b,a,c) -> false)
            .isViewBlocking((b,a,c) -> false)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor((b,a,c) -> false)));

    public static final Supplier<Block> PAPAYA_LEAVES_STEM = register("papaya_leaves_stem",() -> new PapayaLeavesStemBlock(BlockBehaviour.Properties.copy(PAPAYA_LEAVES.get())));

    public static final Supplier<Block> WALL_PAPAYA_LEAVES = register("wall_papaya_leaves",() ->new WallPapayaLeavesBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .strength(0.2F)
            .sound(SoundType.AZALEA_LEAVES)
            .noOcclusion()
            .isValidSpawn(BlocksAccessor::ocelotOrParrot)
            .isSuffocating((b,a,c) -> false)
            .isViewBlocking((b,a,c) -> false)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor((b,a,c) -> false)));

    public static final Supplier<Block> BUDDING_PAPAYA_FLOWER = register("budding_papaya_flower",() ->new BuddingPapayaFlowerBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .sound(SoundType.SPORE_BLOSSOM)
            .instabreak()
            .noCollission()
            .randomTicks()
            .pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> PAPAYA = register("papaya",() ->new PapayaBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().strength(0.2F, 3.0F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> PAPAYA_FLOWER = register("papaya_flower",() ->new PapayaFlowerBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .noCollission()
            .instabreak()
            .sound(SoundType.SPORE_BLOSSOM)
            .pushReaction(PushReaction.DESTROY)
            .emissiveRendering((blockState, blockGetter, blockPos) -> blockState.getValue(PapayaFlowerBlock.LIT))
            .lightLevel(blockstate -> blockstate.getValue(PapayaFlowerBlock.LIT) ? 1 : 0)
    ));
    public static final Supplier<Block> WALL_PAPAYA_FLOWER = register("wall_papaya_flower",() -> new WallPapayaFlowerBlock(BlockBehaviour.Properties.copy(PAPAYA_FLOWER.get())));

    public static final Supplier<Block> PAPAYA_SAPLING = register("papaya_sapling",() ->new ModSaplingBlock(new PapayaTreeGrower(), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> PAPAYA_CROP = register("papaya_crop",() ->new PapayaCropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));

    public static final Supplier<Block> STACKABLE_PAPAYA = register("stackable_papaya",() -> new StackablePapayaBlock(BlockBehaviour.Properties.copy(ModBlocks.LIME_BLOCK.get()), () -> ModItems.PAPAYA.get()));
    public static final Supplier<Block> STACKABLE_RAW_PAPAYA = register("stackable_raw_papaya",() -> new StackablePapayaBlock(BlockBehaviour.Properties.copy(ModBlocks.LIME_BLOCK.get()),() -> ModItems.RAW_PAPAYA.get()));

    //Cauldron
    public static final Supplier<Block> FERMENTED_FISH_CAULDRON = register("fermented_fish_cauldron",() ->new FermentedFishCauldronBlock(BlockBehaviour.Properties.copy(CAULDRON),ModCauldronInteraction.FERMENTED_FISH));
    public static final Supplier<Block> COCONUT_CAULDRON = register("coconut_cauldron",() ->new CoconutCauldron(BlockBehaviour.Properties.copy(CAULDRON)));
    public static final Supplier<Block> COCONUT_MILK_CAULDRON = register("coconut_milk_cauldron",() ->new CoconutMilkCauldron(BlockBehaviour.Properties.copy(CAULDRON)));


    // 🌿 BASIL 🌿
    public static final Supplier<Block> BASIL = register("basil",() ->new BasilCropBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES),ModLootTables.BASIL_HARVEST,ModLootTables.BASIL_SHEARS){
        @Override
        protected ItemLike getBaseSeedId() {
            return ModItems.BASIL.get();
        }
    });

    public static final Supplier<Block> HOLY_BASIL = register("holy_basil",() ->new BasilCropBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES),ModLootTables.HOLY_BASIL_HARVEST,ModLootTables.HOLY_BASIL_SHEARS){
        @Override
        protected ItemLike getBaseSeedId() {
            return ModItems.HOLY_BASIL.get();
        }
    });


    public static final Supplier<Block> BUDDING_BUTTERFLY_PEA_BLOCK = register("budding_butterfly_pea",createButterflyPeaBlock());
    public static final Supplier<Block> BUTTERFLY_PEA_BLOCK = register("butterfly_pea_vine",() ->new ButterflyPeaVineBlock(BlockBehaviour.Properties.copy(WHEAT)));
    public static final Supplier<Block> BUTTERFLY_PEA_WALL = register("butterfly_pea_wall",() -> new WallFlowerBlock(BlockBehaviour.Properties.copy(SPORE_BLOSSOM),MobEffects.HEAL,1));

    public static final Supplier<Block> DURIAN_CAKE = register("durian_cake",() -> new DurianCakeBlock(BlockBehaviour.Properties.copy(CAKE)));
    public static final Supplier<Block> CANDLE_DURIAN_CAKE = register("candle_durian_cake",() ->new CandleDurianCakeBlock(CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Supplier<Block> WHITE_CANDLE_DURIAN_CAKE = register("white_candle_durian_cake",() ->new CandleDurianCakeBlock(WHITE_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Supplier<Block> ORANGE_CANDLE_DURIAN_CAKE = register("orange_candle_durian_cake",() ->new CandleDurianCakeBlock(ORANGE_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Supplier<Block> MAGENTA_CANDLE_DURIAN_CAKE = register("magenta_candle_durian_cake",() ->new CandleDurianCakeBlock(MAGENTA_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Supplier<Block> LIGHT_BLUE_CANDLE_DURIAN_CAKE = register("light_blue_candle_durian_cake",() ->new CandleDurianCakeBlock(LIGHT_BLUE_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Supplier<Block> YELLOW_CANDLE_DURIAN_CAKE = register("yellow_candle_durian_cake",() ->new CandleDurianCakeBlock(YELLOW_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Supplier<Block> LIME_CANDLE_DURIAN_CAKE = register("lime_candle_durian_cake",() ->new CandleDurianCakeBlock(LIME_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Supplier<Block> PINK_CANDLE_DURIAN_CAKE = register("pink_candle_durian_cake",() ->new CandleDurianCakeBlock(PINK_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Supplier<Block> GRAY_CANDLE_DURIAN_CAKE = register("gray_candle_durian_cake",() ->new CandleDurianCakeBlock(GRAY_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Supplier<Block> LIGHT_GRAY_CANDLE_DURIAN_CAKE = register("light_gray_candle_durian_cake",() ->new CandleDurianCakeBlock(LIGHT_GRAY_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Supplier<Block> CYAN_CANDLE_DURIAN_CAKE = register("cyan_candle_durian_cake",() ->new CandleDurianCakeBlock(CYAN_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Supplier<Block> PURPLE_CANDLE_DURIAN_CAKE = register("purple_candle_durian_cake",() ->new CandleDurianCakeBlock(PURPLE_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Supplier<Block> BLUE_CANDLE_DURIAN_CAKE = register("blue_candle_durian_cake",() ->new CandleDurianCakeBlock(BLUE_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Supplier<Block> BROWN_CANDLE_DURIAN_CAKE = register("brown_candle_durian_cake",() ->new CandleDurianCakeBlock(BROWN_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Supplier<Block> GREEN_CANDLE_DURIAN_CAKE = register("green_candle_durian_cake",() ->new CandleDurianCakeBlock(GREEN_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Supplier<Block> RED_CANDLE_DURIAN_CAKE = register("red_candle_durian_cake",() ->new CandleDurianCakeBlock(RED_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));
    public static final Supplier<Block> BLACK_CANDLE_DURIAN_CAKE = register("black_candle_durian_cake",() ->new CandleDurianCakeBlock(BLACK_CANDLE,BlockBehaviour.Properties.copy(CANDLE_CAKE)));


    //Feast
    public static final Supplier<Block> SOMTAM_FEAST = register("somtam_feast",createSomtamFeast());
    public static final Supplier<Block> LARB_FEAST = register("larb_feast",createLarbFeast());
    public static final Supplier<Block> CRAB_FRIED_RICE_FEAST = register("crab_fried_rice_feast",createCrabFriedRice());

    public static final Supplier<Block> MANGO_CHEESECAKE = register("mango_cheesecake",createPieBlock(BlockBehaviour.Properties.copy(CAKE), ModItems.MANGO_CHEESECAKE_SLICE));
    public static final Supplier<Block> COCONUT_PIE = register("coconut_pie",createCoconutPieBlock());
    public static final Supplier<Block> HONEY_COCONUT_PIE = register("honey_coconut_pie",createPieBlock(BlockBehaviour.Properties.copy(CAKE),ModItems.HONEY_COCONUT_PIE_SLICE));

    public static final Supplier<Block> PHAT_KAPHRAO_FEAST = register("phat_kaphrao_feast",createPhatKaphraoBlock());
    public static final Supplier<Block> MANGO_STICKY_RICE_FEAST = register("mango_sticky_rice_feast",createMangoStickyRiceBlock());
    public static final Supplier<Block> OMELETTE_FEAST = register("omelette",createOmeletteBlock(ModItems.OMELETTE));
    public static final Supplier<Block> BASIL_OMELETTE_FEAST = register("basil_omelette",createOmeletteBlock(ModItems.BASIL_OMELETTE));
    public static final Supplier<Block> PINEAPPLE_FRIED_RICE_FEAST = register("pineapple_fried_rice_feast",createPineappleFeastBlock());

    public static void init(){
        BLOCK.init();
    }

    public static Supplier<Block> registerCabinet(String id){
        Supplier<Block> block = register(id, cabinetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.5f).sound(SoundType.WOOD).ignitedByLava()));
        CABINET.add(block);
        return block;
    }

    public static Supplier<Block> registerCrate(String id){
        Supplier<Block> block = register(id,() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).destroyTime(2.0f).explosionResistance(3.0f).sound(SoundType.WOOD)));
        CRATES.add(block);
        return block;
    }

    public static Supplier<Block> register(String id,Supplier<Block> block){
        return BLOCK.register(id, block);
    }


    @ExpectPlatform
    public static Supplier<Block> createSomtamFeast(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Block> createLarbFeast(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Block> createCrabFriedRice(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Block> createCoconutPieBlock(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Block> createPhatKaphraoBlock(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Block> createMangoStickyRiceBlock(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Block> createOmeletteBlock(Supplier<Item> itemSupplier){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Block> createPineappleFeastBlock(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Block> createWildCropBlock(MobEffect stewEffect, int effectDuration, BlockBehaviour.Properties properties){
        throw new AssertionError();
    }




    @ExpectPlatform
    public static Supplier<Block> createButterflyPeaBlock(){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Block> cabinetBlock(BlockBehaviour.Properties properties){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Block> createBuddingPepperBlock(BlockBehaviour.Properties properties){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Block> createPieBlock(BlockBehaviour.Properties properties, Supplier<Item> supplier){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Block> getThaiDelightBlock(String id, Supplier<Block> blockSupplier){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static BlockBehaviour.Properties dropLike(Supplier<Block> blockSupplier){
        throw new AssertionError();
    }
}
