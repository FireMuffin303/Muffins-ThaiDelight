package net.firemuffin303.thaidelight.common.registry;

import net.firemuffin303.thaidelight.common.block.CrabEggBlock;
import net.firemuffin303.thaidelight.common.block.MortarBlock;
import net.firemuffin303.thaidelight.common.block.crops.*;
import net.firemuffin303.thaidelight.common.block.grower.PapayaTreeGrower;
import net.firemuffin303.thaidelight.utils.ModPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModBlocks {
    //Functional Block
    public static final Block MORTAR = new MortarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).strength(0.5f,6.0f).noOcclusion().sound(SoundType.DECORATED_POT));

    //Crate
    public static final Block LIME_CRATE = new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).destroyTime(2.0f).explosionResistance(3.0f).sound(SoundType.WOOD));
    public static final Block PEPPER_CRATE = new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).destroyTime(2.0f).explosionResistance(3.0f).sound(SoundType.WOOD));
    public static final Block RAW_PAPAYA_CRATE = new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).destroyTime(2.0f).explosionResistance(3.0f).sound(SoundType.WOOD));
    public static final Block PAPAYA_CRATE = new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).destroyTime(2.0f).explosionResistance(3.0f).sound(SoundType.WOOD));

    //Eggs
    public static final Block CRAB_EGG = new CrabEggBlock(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN));

    //Wild Crops
    public static final Block WILD_PEPPER_CROP = ModPlatform.getWildCropBlock(MobEffects.CONFUSION,6,BlockBehaviour.Properties.copy(Blocks.TALL_GRASS));
    //Crops
    public static final Block LIME_CROP = new LimeCrop(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY));
    public static final Block LIME_SAPLING = new LimeSapling(BlockBehaviour.Properties.copy(ModBlocks.LIME_CROP).noCollission());
    public static final Block PEPPER_CROP = new PepperCropBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES));


    //Feast
    public static final Block CRAB_FRIED_RICE_FEAST = ModPlatform.getCrabFriedRice();
    public static final Block SOMTAM_FEAST = ModPlatform.getSomtamBlock();
    public static final Block SPICY_MINCED_MEAT_SALAD_FEAST = ModPlatform.getSpicyMincedPorkBlock();

    //Papaya
    public static final Block PAPAYA_LOG = new PapayaLog(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.NETHER_WOOD).ignitedByLava());
    public static final Block STRIPPED_PAPAYA_LOG = log(MapColor.COLOR_CYAN,MapColor.COLOR_CYAN);
    public static final Block PAPAYA_WOOD = new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
    public static final Block STRIPPED_PAPAYA_WOOD = new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
    public static final Block PAPAYA_LEAVES = leaves(SoundType.AZALEA_LEAVES);

    public static final Block PAPAYA = new PapayaBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().strength(0.2F, 3.0F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY));
    public static final Block PAPAYA_SAPLING = new ModSaplingBlock(new PapayaTreeGrower(), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY));
    public static final Block PAPAYA_CROPS = new PapayaCropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY));


    public static void init(){
        //Functional Block
        registerWithItem("mortar",MORTAR);

        //Crate
        registerWithItem("lime_crate",LIME_CRATE);
        registerWithItem("pepper_crate",PEPPER_CRATE);
        registerWithItem("raw_papaya_crate", RAW_PAPAYA_CRATE);
        registerWithItem("papaya_crate",PAPAYA_CRATE);

        //Eggs
        registerWithItem("flower_crab_egg",CRAB_EGG);

        //Wild Crops
        registerWithItem("wild_pepper",WILD_PEPPER_CROP);

        //Crops
        register("pepper",PEPPER_CROP);
        register("lime_bush", LIME_CROP);
        registerWithItem("lime_sapling", LIME_SAPLING);


        register("papaya",PAPAYA);
        registerWithItem("papaya_sapling", PAPAYA_SAPLING);
        register("papaya_crop", PAPAYA_CROPS);

        //Feast
        registerWithItem("somtam_feast",SOMTAM_FEAST);
        registerWithItem("spicy_minced_meat_salad_feast", SPICY_MINCED_MEAT_SALAD_FEAST);
        registerWithItem("crab_fried_rice_feast", CRAB_FRIED_RICE_FEAST);

        //Block
        registerWithItem("papaya_log",PAPAYA_LOG);
        registerWithItem("stripped_papaya_log",STRIPPED_PAPAYA_LOG);
        registerWithItem("papaya_wood",PAPAYA_WOOD);
        registerWithItem("stripped_papaya_wood",STRIPPED_PAPAYA_WOOD);
        registerWithItem("papaya_leaves",PAPAYA_LEAVES);
    }

    public static void register(String id, Block block){
        ModPlatform.registryBlock(id,()->block);
    }

    public static void registerWithItem(String id, Block block){
        register(id, block);
        Item item = new BlockItem(block,new Item.Properties());
        ModPlatform.registryItem(id,() -> item);
        ModItems.ITEMS.add(item);
    }

    private static RotatedPillarBlock log(MapColor mapColor, MapColor mapColor2) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor((blockState) -> {
            return blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? mapColor : mapColor2;
        }).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
    }

    private static LeavesBlock leaves(SoundType soundType) {
        return new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(soundType).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating((blockState, blockGetter, blockPos) -> false).isViewBlocking((blockState, blockGetter, blockPos) -> false).ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor((blockState, blockGetter, blockPos) -> false));
    }

    private static Boolean ocelotOrParrot(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {
        return entityType == EntityType.OCELOT || entityType == EntityType.PARROT;
    }

    public static class ModBlockEntityTypes{
        public static void init(){
        }


        @FunctionalInterface
        public interface BlockEntitySupplier<T extends BlockEntity> {
            T create(BlockPos blockPos, BlockState blockState);
        }
    }



}
