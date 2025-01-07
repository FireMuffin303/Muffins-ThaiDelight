package net.firemuffin303.muffinsthaidelightfabric.registry;


import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.firemuffin303.muffinsthaidelightfabric.ThaiDelight;
import net.firemuffin303.muffinsthaidelightfabric.common.block.*;
import net.firemuffin303.muffinsthaidelightfabric.common.block.lime.LimeBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.lime.LimeCropBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.lime.LimeLeavesBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.lime.LimeSaplingBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.papaya.PapayaBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.papaya.PapayaCropBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.block.papaya.PapayaLogBlock;
import net.firemuffin303.muffinsthaidelightfabric.common.trees.PapayaTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.block.WildCropBlock;

import static net.minecraft.world.level.block.Blocks.leaves;
import static net.minecraft.world.level.block.Blocks.log;

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
    //Crops
    public static final Block LIME_BUSH = register("lime_bush",new LimeCropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY)));
    public static final Block LIME_SAPLING = register("lime_sapling",new LimeSaplingBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY).noCollission()));
    public static final Block LIME_LEAVES = register("lime_leaves",new LimeLeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().sound(SoundType.SWEET_BERRY_BUSH).ignitedByLava()));
    public static final Block LIME_BLOCK = register("lime_block",new LimeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).instabreak().noOcclusion().pushReaction(PushReaction.DESTROY).randomTicks().sound(SoundType.SWEET_BERRY_BUSH)));

    public static final Block PEPPER_CROP = register("pepper_crop",new PepperCropBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES)));


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

    public static void init() {
        
    }

    public static Block register(String id, Block block){
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(ThaiDelight.MOD_ID,id), block);
    }
}
